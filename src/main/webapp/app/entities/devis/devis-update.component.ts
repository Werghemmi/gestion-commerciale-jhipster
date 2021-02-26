import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IDevis, Devis } from 'app/shared/model/devis.model';
import { DevisService } from './devis.service';

@Component({
  selector: 'jhi-devis-update',
  templateUrl: './devis-update.component.html',
})
export class DevisUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    dateDevis: [],
  });

  constructor(protected devisService: DevisService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ devis }) => {
      if (!devis.id) {
        const today = moment().startOf('day');
        devis.dateDevis = today;
      }

      this.updateForm(devis);
    });
  }

  updateForm(devis: IDevis): void {
    this.editForm.patchValue({
      id: devis.id,
      dateDevis: devis.dateDevis ? devis.dateDevis.format(DATE_TIME_FORMAT) : null,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const devis = this.createFromForm();
    if (devis.id !== undefined) {
      this.subscribeToSaveResponse(this.devisService.update(devis));
    } else {
      this.subscribeToSaveResponse(this.devisService.create(devis));
    }
  }

  private createFromForm(): IDevis {
    return {
      ...new Devis(),
      id: this.editForm.get(['id'])!.value,
      dateDevis: this.editForm.get(['dateDevis'])!.value ? moment(this.editForm.get(['dateDevis'])!.value, DATE_TIME_FORMAT) : undefined,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDevis>>): void {
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
