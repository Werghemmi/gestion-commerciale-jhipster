import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IReglementVente, ReglementVente } from 'app/shared/model/reglement-vente.model';
import { ReglementVenteService } from './reglement-vente.service';

@Component({
  selector: 'jhi-reglement-vente-update',
  templateUrl: './reglement-vente-update.component.html',
})
export class ReglementVenteUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    dateReglement: [],
    typeReglement: [],
  });

  constructor(protected reglementVenteService: ReglementVenteService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reglementVente }) => {
      if (!reglementVente.id) {
        const today = moment().startOf('day');
        reglementVente.dateReglement = today;
      }

      this.updateForm(reglementVente);
    });
  }

  updateForm(reglementVente: IReglementVente): void {
    this.editForm.patchValue({
      id: reglementVente.id,
      dateReglement: reglementVente.dateReglement ? reglementVente.dateReglement.format(DATE_TIME_FORMAT) : null,
      typeReglement: reglementVente.typeReglement,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const reglementVente = this.createFromForm();
    if (reglementVente.id !== undefined) {
      this.subscribeToSaveResponse(this.reglementVenteService.update(reglementVente));
    } else {
      this.subscribeToSaveResponse(this.reglementVenteService.create(reglementVente));
    }
  }

  private createFromForm(): IReglementVente {
    return {
      ...new ReglementVente(),
      id: this.editForm.get(['id'])!.value,
      dateReglement: this.editForm.get(['dateReglement'])!.value
        ? moment(this.editForm.get(['dateReglement'])!.value, DATE_TIME_FORMAT)
        : undefined,
      typeReglement: this.editForm.get(['typeReglement'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReglementVente>>): void {
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
}
