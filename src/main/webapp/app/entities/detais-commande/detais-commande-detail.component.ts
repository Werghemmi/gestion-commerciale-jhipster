import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDetaisCommande } from 'app/shared/model/detais-commande.model';

@Component({
  selector: 'jhi-detais-commande-detail',
  templateUrl: './detais-commande-detail.component.html',
})
export class DetaisCommandeDetailComponent implements OnInit {
  detaisCommande: IDetaisCommande | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ detaisCommande }) => (this.detaisCommande = detaisCommande));
  }

  previousState(): void {
    window.history.back();
  }
}
