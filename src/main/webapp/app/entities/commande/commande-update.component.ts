import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICommande, Commande } from 'app/shared/model/commande.model';
import { CommandeService } from './commande.service';
import { IFactureAchat } from 'app/shared/model/facture-achat.model';
import { FactureAchatService } from 'app/entities/facture-achat/facture-achat.service';
import { IFournisseur } from 'app/shared/model/fournisseur.model';
import { FournisseurService } from 'app/entities/fournisseur/fournisseur.service';

type SelectableEntity = IFactureAchat | IFournisseur;

@Component({
  selector: 'jhi-commande-update',
  templateUrl: './commande-update.component.html',
})
export class CommandeUpdateComponent implements OnInit {
  isSaving = false;
  factureachats: IFactureAchat[] = [];
  fournisseurs: IFournisseur[] = [];

  editForm = this.fb.group({
    id: [],
    dateCommande: [],
    factureAchatId: [],
    fournisseurId: [],
  });

  constructor(
    protected commandeService: CommandeService,
    protected factureAchatService: FactureAchatService,
    protected fournisseurService: FournisseurService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ commande }) => {
      if (!commande.id) {
        const today = moment().startOf('day');
        commande.dateCommande = today;
      }

      this.updateForm(commande);

      this.factureAchatService
        .query({ filter: 'commande-is-null' })
        .pipe(
          map((res: HttpResponse<IFactureAchat[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IFactureAchat[]) => {
          if (!commande.factureAchatId) {
            this.factureachats = resBody;
          } else {
            this.factureAchatService
              .find(commande.factureAchatId)
              .pipe(
                map((subRes: HttpResponse<IFactureAchat>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IFactureAchat[]) => (this.factureachats = concatRes));
          }
        });

      this.fournisseurService.query().subscribe((res: HttpResponse<IFournisseur[]>) => (this.fournisseurs = res.body || []));
    });
  }

  updateForm(commande: ICommande): void {
    this.editForm.patchValue({
      id: commande.id,
      dateCommande: commande.dateCommande ? commande.dateCommande.format(DATE_TIME_FORMAT) : null,
      factureAchatId: commande.factureAchatId,
      fournisseurId: commande.fournisseurId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const commande = this.createFromForm();
    if (commande.id !== undefined) {
      this.subscribeToSaveResponse(this.commandeService.update(commande));
    } else {
      this.subscribeToSaveResponse(this.commandeService.create(commande));
    }
  }

  private createFromForm(): ICommande {
    return {
      ...new Commande(),
      id: this.editForm.get(['id'])!.value,
      dateCommande: this.editForm.get(['dateCommande'])!.value
        ? moment(this.editForm.get(['dateCommande'])!.value, DATE_TIME_FORMAT)
        : undefined,
      factureAchatId: this.editForm.get(['factureAchatId'])!.value,
      fournisseurId: this.editForm.get(['fournisseurId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICommande>>): void {
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
