import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IVente, Vente } from 'app/shared/model/vente.model';
import { VenteService } from './vente.service';
import { IFactureVente } from 'app/shared/model/facture-vente.model';
import { FactureVenteService } from 'app/entities/facture-vente/facture-vente.service';
import { IClient } from 'app/shared/model/client.model';
import { ClientService } from 'app/entities/client/client.service';

type SelectableEntity = IFactureVente | IClient;

@Component({
  selector: 'jhi-vente-update',
  templateUrl: './vente-update.component.html',
})
export class VenteUpdateComponent implements OnInit {
  isSaving = false;
  factureventes: IFactureVente[] = [];
  clients: IClient[] = [];

  editForm = this.fb.group({
    id: [],
    dateCommande: [],
    factureVenteId: [],
    clientId: [],
  });

  constructor(
    protected venteService: VenteService,
    protected factureVenteService: FactureVenteService,
    protected clientService: ClientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ vente }) => {
      if (!vente.id) {
        const today = moment().startOf('day');
        vente.dateCommande = today;
      }

      this.updateForm(vente);

      this.factureVenteService
        .query({ filter: 'vente-is-null' })
        .pipe(
          map((res: HttpResponse<IFactureVente[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IFactureVente[]) => {
          if (!vente.factureVenteId) {
            this.factureventes = resBody;
          } else {
            this.factureVenteService
              .find(vente.factureVenteId)
              .pipe(
                map((subRes: HttpResponse<IFactureVente>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IFactureVente[]) => (this.factureventes = concatRes));
          }
        });

      this.clientService.query().subscribe((res: HttpResponse<IClient[]>) => (this.clients = res.body || []));
    });
  }

  updateForm(vente: IVente): void {
    this.editForm.patchValue({
      id: vente.id,
      dateCommande: vente.dateCommande ? vente.dateCommande.format(DATE_TIME_FORMAT) : null,
      factureVenteId: vente.factureVenteId,
      clientId: vente.clientId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const vente = this.createFromForm();
    if (vente.id !== undefined) {
      this.subscribeToSaveResponse(this.venteService.update(vente));
    } else {
      this.subscribeToSaveResponse(this.venteService.create(vente));
    }
  }

  private createFromForm(): IVente {
    return {
      ...new Vente(),
      id: this.editForm.get(['id'])!.value,
      dateCommande: this.editForm.get(['dateCommande'])!.value
        ? moment(this.editForm.get(['dateCommande'])!.value, DATE_TIME_FORMAT)
        : undefined,
      factureVenteId: this.editForm.get(['factureVenteId'])!.value,
      clientId: this.editForm.get(['clientId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVente>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
