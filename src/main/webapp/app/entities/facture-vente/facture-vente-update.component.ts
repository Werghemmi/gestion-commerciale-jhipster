import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IFactureVente, FactureVente } from 'app/shared/model/facture-vente.model';
import { FactureVenteService } from './facture-vente.service';
import { IReglementVente } from 'app/shared/model/reglement-vente.model';
import { ReglementVenteService } from 'app/entities/reglement-vente/reglement-vente.service';

@Component({
  selector: 'jhi-facture-vente-update',
  templateUrl: './facture-vente-update.component.html',
})
export class FactureVenteUpdateComponent implements OnInit {
  isSaving = false;
  reglementventes: IReglementVente[] = [];

  editForm = this.fb.group({
    id: [],
    dateFacture: [],
    totalHT: [],
    totalTVA: [],
    totalTTC: [],
    totalRemise: [],
    totalNet: [],
    timbreFiscale: [],
    reglementVenteId: [],
  });

  constructor(
    protected factureVenteService: FactureVenteService,
    protected reglementVenteService: ReglementVenteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ factureVente }) => {
      if (!factureVente.id) {
        const today = moment().startOf('day');
        factureVente.dateFacture = today;
      }

      this.updateForm(factureVente);

      this.reglementVenteService
        .query({ filter: 'facturevente-is-null' })
        .pipe(
          map((res: HttpResponse<IReglementVente[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IReglementVente[]) => {
          if (!factureVente.reglementVenteId) {
            this.reglementventes = resBody;
          } else {
            this.reglementVenteService
              .find(factureVente.reglementVenteId)
              .pipe(
                map((subRes: HttpResponse<IReglementVente>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IReglementVente[]) => (this.reglementventes = concatRes));
          }
        });
    });
  }

  updateForm(factureVente: IFactureVente): void {
    this.editForm.patchValue({
      id: factureVente.id,
      dateFacture: factureVente.dateFacture ? factureVente.dateFacture.format(DATE_TIME_FORMAT) : null,
      totalHT: factureVente.totalHT,
      totalTVA: factureVente.totalTVA,
      totalTTC: factureVente.totalTTC,
      totalRemise: factureVente.totalRemise,
      totalNet: factureVente.totalNet,
      timbreFiscale: factureVente.timbreFiscale,
      reglementVenteId: factureVente.reglementVenteId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const factureVente = this.createFromForm();
    if (factureVente.id !== undefined) {
      this.subscribeToSaveResponse(this.factureVenteService.update(factureVente));
    } else {
      this.subscribeToSaveResponse(this.factureVenteService.create(factureVente));
    }
  }

  private createFromForm(): IFactureVente {
    return {
      ...new FactureVente(),
      id: this.editForm.get(['id'])!.value,
      dateFacture: this.editForm.get(['dateFacture'])!.value
        ? moment(this.editForm.get(['dateFacture'])!.value, DATE_TIME_FORMAT)
        : undefined,
      totalHT: this.editForm.get(['totalHT'])!.value,
      totalTVA: this.editForm.get(['totalTVA'])!.value,
      totalTTC: this.editForm.get(['totalTTC'])!.value,
      totalRemise: this.editForm.get(['totalRemise'])!.value,
      totalNet: this.editForm.get(['totalNet'])!.value,
      timbreFiscale: this.editForm.get(['timbreFiscale'])!.value,
      reglementVenteId: this.editForm.get(['reglementVenteId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFactureVente>>): void {
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

  trackById(index: number, item: IReglementVente): any {
    return item.id;
  }
}
