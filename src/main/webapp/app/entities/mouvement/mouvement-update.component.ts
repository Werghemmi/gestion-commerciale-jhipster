import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IMouvement, Mouvement } from 'app/shared/model/mouvement.model';
import { MouvementService } from './mouvement.service';
import { IProduit } from 'app/shared/model/produit.model';
import { ProduitService } from 'app/entities/produit/produit.service';

@Component({
  selector: 'jhi-mouvement-update',
  templateUrl: './mouvement-update.component.html',
})
export class MouvementUpdateComponent implements OnInit {
  isSaving = false;
  produits: IProduit[] = [];

  editForm = this.fb.group({
    id: [],
    dateMvt: [],
    typeMvt: [],
    qteMvt: [],
    ancienQte: [],
    nvQte: [],
    ancienPrix: [],
    nvPrix: [],
    prix: [],
    produitId: [],
  });

  constructor(
    protected mouvementService: MouvementService,
    protected produitService: ProduitService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mouvement }) => {
      if (!mouvement.id) {
        const today = moment().startOf('day');
        mouvement.dateMvt = today;
      }

      this.updateForm(mouvement);

      this.produitService.query().subscribe((res: HttpResponse<IProduit[]>) => (this.produits = res.body || []));
    });
  }

  updateForm(mouvement: IMouvement): void {
    this.editForm.patchValue({
      id: mouvement.id,
      dateMvt: mouvement.dateMvt ? mouvement.dateMvt.format(DATE_TIME_FORMAT) : null,
      typeMvt: mouvement.typeMvt,
      qteMvt: mouvement.qteMvt,
      ancienQte: mouvement.ancienQte,
      nvQte: mouvement.nvQte,
      ancienPrix: mouvement.ancienPrix,
      nvPrix: mouvement.nvPrix,
      prix: mouvement.prix,
      produitId: mouvement.produitId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const mouvement = this.createFromForm();
    if (mouvement.id !== undefined) {
      this.subscribeToSaveResponse(this.mouvementService.update(mouvement));
    } else {
      this.subscribeToSaveResponse(this.mouvementService.create(mouvement));
    }
  }

  private createFromForm(): IMouvement {
    return {
      ...new Mouvement(),
      id: this.editForm.get(['id'])!.value,
      dateMvt: this.editForm.get(['dateMvt'])!.value ? moment(this.editForm.get(['dateMvt'])!.value, DATE_TIME_FORMAT) : undefined,
      typeMvt: this.editForm.get(['typeMvt'])!.value,
      qteMvt: this.editForm.get(['qteMvt'])!.value,
      ancienQte: this.editForm.get(['ancienQte'])!.value,
      nvQte: this.editForm.get(['nvQte'])!.value,
      ancienPrix: this.editForm.get(['ancienPrix'])!.value,
      nvPrix: this.editForm.get(['nvPrix'])!.value,
      prix: this.editForm.get(['prix'])!.value,
      produitId: this.editForm.get(['produitId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMouvement>>): void {
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

  trackById(index: number, item: IProduit): any {
    return item.id;
  }
}
