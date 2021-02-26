import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IReglementAchat } from 'app/shared/model/reglement-achat.model';

@Component({
  selector: 'jhi-reglement-achat-detail',
  templateUrl: './reglement-achat-detail.component.html',
})
export class ReglementAchatDetailComponent implements OnInit {
  reglementAchat: IReglementAchat | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reglementAchat }) => (this.reglementAchat = reglementAchat));
  }

  previousState(): void {
    window.history.back();
  }
}
