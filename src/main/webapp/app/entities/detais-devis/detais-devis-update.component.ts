import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDetaisDevis, DetaisDevis } from 'app/shared/model/detais-devis.model';
import { DetaisDevisService } from './detais-devis.service';
import { IProduit } from 'app/shared/model/produit.model';
import { ProduitService } from 'app/entities/produit/produit.service';
import { IDevis } from 'app/shared/model/devis.model';
import { DevisService } from 'app/entities/devis/devis.service';

type SelectableEntity = IProduit | IDevis;

@Component({
  selector: 'jhi-detais-devis-update',
  templateUrl: './detais-devis-update.component.html',
})
export class DetaisDevisUpdateComponent implements OnInit {
  isSaving = false;
  produits: IProduit[] = [];
  devis: IDevis[] = [];

  editForm = this.fb.group({
    id: [],
    qteProduit: [],
    totalHT: [],
    totalTVA: [],
    totalTTC: [],
    produitId: [],
    devisId: [],
  });

  constructor(
    protected detaisDevisService: DetaisDevisService,
    protected produitService: ProduitService,
    protected devisService: DevisService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ detaisDevis }) => {
      this.updateForm(detaisDevis);

      this.produitService.query().subscribe((res: HttpResponse<IProduit[]>) => (this.produits = res.body || []));

      this.devisService.query().subscribe((res: HttpResponse<IDevis[]>) => (this.devis = res.body || []));
    });
  }

  updateForm(detaisDevis: IDetaisDevis): void {
    this.editForm.patchValue({
      id: detaisDevis.id,
      qteProduit: detaisDevis.qteProduit,
      totalHT: detaisDevis.totalHT,
      totalTVA: detaisDevis.totalTVA,
      totalTTC: detaisDevis.totalTTC,
      produitId: detaisDevis.produitId,
      devisId: detaisDevis.devisId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const detaisDevis = this.createFromForm();
    if (detaisDevis.id !== undefined) {
      this.subscribeToSaveResponse(this.detaisDevisService.update(detaisDevis));
    } else {
      this.subscribeToSaveResponse(this.detaisDevisService.create(detaisDevis));
    }
  }

  private createFromForm(): IDetaisDevis {
    return {
      ...new DetaisDevis(),
      id: this.editForm.get(['id'])!.value,
      qteProduit: this.editForm.get(['qteProduit'])!.value,
      totalHT: this.editForm.get(['totalHT'])!.value,
      totalTVA: this.editForm.get(['totalTVA'])!.value,
      totalTTC: this.editForm.get(['totalTTC'])!.value,
      produitId: this.editForm.get(['produitId'])!.value,
      devisId: this.editForm.get(['devisId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDetaisDevis>>): void {
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
