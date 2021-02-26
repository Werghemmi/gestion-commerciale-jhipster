import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDetaisVente, DetaisVente } from 'app/shared/model/detais-vente.model';
import { DetaisVenteService } from './detais-vente.service';
import { IProduit } from 'app/shared/model/produit.model';
import { ProduitService } from 'app/entities/produit/produit.service';
import { IVente } from 'app/shared/model/vente.model';
import { VenteService } from 'app/entities/vente/vente.service';

type SelectableEntity = IProduit | IVente;

@Component({
  selector: 'jhi-detais-vente-update',
  templateUrl: './detais-vente-update.component.html',
})
export class DetaisVenteUpdateComponent implements OnInit {
  isSaving = false;
  produits: IProduit[] = [];
  ventes: IVente[] = [];

  editForm = this.fb.group({
    id: [],
    qteProduit: [],
    prix: [],
    produitId: [],
    venteId: [],
  });

  constructor(
    protected detaisVenteService: DetaisVenteService,
    protected produitService: ProduitService,
    protected venteService: VenteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ detaisVente }) => {
      this.updateForm(detaisVente);

      this.produitService.query().subscribe((res: HttpResponse<IProduit[]>) => (this.produits = res.body || []));

      this.venteService.query().subscribe((res: HttpResponse<IVente[]>) => (this.ventes = res.body || []));
    });
  }

  updateForm(detaisVente: IDetaisVente): void {
    this.editForm.patchValue({
      id: detaisVente.id,
      qteProduit: detaisVente.qteProduit,
      prix: detaisVente.prix,
      produitId: detaisVente.produitId,
      venteId: detaisVente.venteId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const detaisVente = this.createFromForm();
    if (detaisVente.id !== undefined) {
      this.subscribeToSaveResponse(this.detaisVenteService.update(detaisVente));
    } else {
      this.subscribeToSaveResponse(this.detaisVenteService.create(detaisVente));
    }
  }

  private createFromForm(): IDetaisVente {
    return {
      ...new DetaisVente(),
      id: this.editForm.get(['id'])!.value,
      qteProduit: this.editForm.get(['qteProduit'])!.value,
      prix: this.editForm.get(['prix'])!.value,
      produitId: this.editForm.get(['produitId'])!.value,
      venteId: this.editForm.get(['venteId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDetaisVente>>): void {
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
