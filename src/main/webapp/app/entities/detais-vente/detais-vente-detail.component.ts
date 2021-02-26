import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDetaisVente } from 'app/shared/model/detais-vente.model';

@Component({
  selector: 'jhi-detais-vente-detail',
  templateUrl: './detais-vente-detail.component.html',
})
export class DetaisVenteDetailComponent implements OnInit {
  detaisVente: IDetaisVente | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ detaisVente }) => (this.detaisVente = detaisVente));
  }

  previousState(): void {
    window.history.back();
  }
}
