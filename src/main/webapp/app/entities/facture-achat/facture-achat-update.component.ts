import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IFactureAchat, FactureAchat } from 'app/shared/model/facture-achat.model';
import { FactureAchatService } from './facture-achat.service';
import { IReglementAchat } from 'app/shared/model/reglement-achat.model';
import { ReglementAchatService } from 'app/entities/reglement-achat/reglement-achat.service';

@Component({
  selector: 'jhi-facture-achat-update',
  templateUrl: './facture-achat-update.component.html',
})
export class FactureAchatUpdateComponent implements OnInit {
  isSaving = false;
  reglementachats: IReglementAchat[] = [];

  editForm = this.fb.group({
    id: [],
    dateFacture: [],
    totalHT: [],
    totalTVA: [],
    totalTTC: [],
    totalRemise: [],
    totalNet: [],
    timbreFiscale: [],
    reglementAchatId: [],
  });

  constructor(
    protected factureAchatService: FactureAchatService,
    protected reglementAchatService: ReglementAchatService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ factureAchat }) => {
      if (!factureAchat.id) {
        const today = moment().startOf('day');
        factureAchat.dateFacture = today;
      }

      this.updateForm(factureAchat);

      this.reglementAchatService
        .query({ filter: 'factureachat-is-null' })
        .pipe(
          map((res: HttpResponse<IReglementAchat[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IReglementAchat[]) => {
          if (!factureAchat.reglementAchatId) {
            this.reglementachats = resBody;
          } else {
            this.reglementAchatService
              .find(factureAchat.reglementAchatId)
              .pipe(
                map((subRes: HttpResponse<IReglementAchat>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IReglementAchat[]) => (this.reglementachats = concatRes));
          }
        });
    });
  }

  updateForm(factureAchat: IFactureAchat): void {
    this.editForm.patchValue({
      id: factureAchat.id,
      dateFacture: factureAchat.dateFacture ? factureAchat.dateFacture.format(DATE_TIME_FORMAT) : null,
      totalHT: factureAchat.totalHT,
      totalTVA: factureAchat.totalTVA,
      totalTTC: factureAchat.totalTTC,
      totalRemise: factureAchat.totalRemise,
      totalNet: factureAchat.totalNet,
      timbreFiscale: factureAchat.timbreFiscale,
      reglementAchatId: factureAchat.reglementAchatId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const factureAchat = this.createFromForm();
    if (factureAchat.id !== undefined) {
      this.subscribeToSaveResponse(this.factureAchatService.update(factureAchat));
    } else {
      this.subscribeToSaveResponse(this.factureAchatService.create(factureAchat));
    }
  }

  private createFromForm(): IFactureAchat {
    return {
      ...new FactureAchat(),
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
      reglementAchatId: this.editForm.get(['reglementAchatId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFactureAchat>>): void {
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

  trackById(index: number, item: IReglementAchat): any {
    return item.id;
  }
}
