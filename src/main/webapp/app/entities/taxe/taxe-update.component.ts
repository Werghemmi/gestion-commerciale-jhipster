import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITaxe, Taxe } from 'app/shared/model/taxe.model';
import { TaxeService } from './taxe.service';

@Component({
  selector: 'jhi-taxe-update',
  templateUrl: './taxe-update.component.html',
})
export class TaxeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nomTaxe: [],
    taux: [],
    typeTaxe: [],
  });

  constructor(protected taxeService: TaxeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ taxe }) => {
      this.updateForm(taxe);
    });
  }

  updateForm(taxe: ITaxe): void {
    this.editForm.patchValue({
      id: taxe.id,
      nomTaxe: taxe.nomTaxe,
      taux: taxe.taux,
      typeTaxe: taxe.typeTaxe,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const taxe = this.createFromForm();
    if (taxe.id !== undefined) {
      this.subscribeToSaveResponse(this.taxeService.update(taxe));
    } else {
      this.subscribeToSaveResponse(this.taxeService.create(taxe));
    }
  }

  private createFromForm(): ITaxe {
    return {
      ...new Taxe(),
      id: this.editForm.get(['id'])!.value,
      nomTaxe: this.editForm.get(['nomTaxe'])!.value,
      taux: this.editForm.get(['taux'])!.value,
      typeTaxe: this.editForm.get(['typeTaxe'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITaxe>>): void {
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
