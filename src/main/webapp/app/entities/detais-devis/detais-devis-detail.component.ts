import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDetaisDevis } from 'app/shared/model/detais-devis.model';

@Component({
  selector: 'jhi-detais-devis-detail',
  templateUrl: './detais-devis-detail.component.html',
})
export class DetaisDevisDetailComponent implements OnInit {
  detaisDevis: IDetaisDevis | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ detaisDevis }) => (this.detaisDevis = detaisDevis));
  }

  previousState(): void {
    window.history.back();
  }
}
