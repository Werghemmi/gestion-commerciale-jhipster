import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IReglementVente } from 'app/shared/model/reglement-vente.model';

@Component({
  selector: 'jhi-reglement-vente-detail',
  templateUrl: './reglement-vente-detail.component.html',
})
export class ReglementVenteDetailComponent implements OnInit {
  reglementVente: IReglementVente | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reglementVente }) => (this.reglementVente = reglementVente));
  }

  previousState(): void {
    window.history.back();
  }
}
