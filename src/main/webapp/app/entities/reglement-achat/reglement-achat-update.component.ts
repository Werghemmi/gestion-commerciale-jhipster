import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IReglementAchat, ReglementAchat } from 'app/shared/model/reglement-achat.model';
import { ReglementAchatService } from './reglement-achat.service';

@Component({
  selector: 'jhi-reglement-achat-update',
  templateUrl: './reglement-achat-update.component.html',
})
export class ReglementAchatUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    dateReglement: [],
    typeReglement: [],
  });

  constructor(protected reglementAchatService: ReglementAchatService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reglementAchat }) => {
      if (!reglementAchat.id) {
        const today = moment().startOf('day');
        reglementAchat.dateReglement = today;
      }

      this.updateForm(reglementAchat);
    });
  }

  updateForm(reglementAchat: IReglementAchat): void {
    this.editForm.patchValue({
      id: reglementAchat.id,
      dateReglement: reglementAchat.dateReglement ? reglementAchat.dateReglement.format(DATE_TIME_FORMAT) : null,
      typeReglement: reglementAchat.typeReglement,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const reglementAchat = this.createFromForm();
    if (reglementAchat.id !== undefined) {
      this.subscribeToSaveResponse(this.reglementAchatService.update(reglementAchat));
    } else {
      this.subscribeToSaveResponse(this.reglementAchatService.create(reglementAchat));
    }
  }

  private createFromForm(): IReglementAchat {
    return {
      ...new ReglementAchat(),
      id: this.editForm.get(['id'])!.value,
      dateReglement: this.editForm.get(['dateReglement'])!.value
        ? moment(this.editForm.get(['dateReglement'])!.value, DATE_TIME_FORMAT)
        : undefined,
      typeReglement: this.editForm.get(['typeReglement'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReglementAchat>>): void {
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
