import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDetaisCommande, DetaisCommande } from 'app/shared/model/detais-commande.model';
import { DetaisCommandeService } from './detais-commande.service';
import { IProduit } from 'app/shared/model/produit.model';
import { ProduitService } from 'app/entities/produit/produit.service';
import { ICommande } from 'app/shared/model/commande.model';
import { CommandeService } from 'app/entities/commande/commande.service';

type SelectableEntity = IProduit | ICommande;

@Component({
  selector: 'jhi-detais-commande-update',
  templateUrl: './detais-commande-update.component.html',
})
export class DetaisCommandeUpdateComponent implements OnInit {
  isSaving = false;
  produits: IProduit[] = [];
  commandes: ICommande[] = [];

  editForm = this.fb.group({
    id: [],
    qteProduit: [],
    prix: [],
    produitId: [],
    commandeId: [],
  });

  constructor(
    protected detaisCommandeService: DetaisCommandeService,
    protected produitService: ProduitService,
    protected commandeService: CommandeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ detaisCommande }) => {
      this.updateForm(detaisCommande);

      this.produitService.query().subscribe((res: HttpResponse<IProduit[]>) => (this.produits = res.body || []));

      this.commandeService.query().subscribe((res: HttpResponse<ICommande[]>) => (this.commandes = res.body || []));
    });
  }

  updateForm(detaisCommande: IDetaisCommande): void {
    this.editForm.patchValue({
      id: detaisCommande.id,
      qteProduit: detaisCommande.qteProduit,
      prix: detaisCommande.prix,
      produitId: detaisCommande.produitId,
      commandeId: detaisCommande.commandeId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const detaisCommande = this.createFromForm();
    if (detaisCommande.id !== undefined) {
      this.subscribeToSaveResponse(this.detaisCommandeService.update(detaisCommande));
    } else {
      this.subscribeToSaveResponse(this.detaisCommandeService.create(detaisCommande));
    }
  }

  private createFromForm(): IDetaisCommande {
    return {
      ...new DetaisCommande(),
      id: this.editForm.get(['id'])!.value,
      qteProduit: this.editForm.get(['qteProduit'])!.value,
      prix: this.editForm.get(['prix'])!.value,
      produitId: this.editForm.get(['produitId'])!.value,
      commandeId: this.editForm.get(['commandeId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDetaisCommande>>): void {
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
