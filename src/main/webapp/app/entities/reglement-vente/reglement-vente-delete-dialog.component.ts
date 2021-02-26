import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IReglementVente } from 'app/shared/model/reglement-vente.model';
import { ReglementVenteService } from './reglement-vente.service';

@Component({
  templateUrl: './reglement-vente-delete-dialog.component.html',
})
export class ReglementVenteDeleteDialogComponent {
  reglementVente?: IReglementVente;

  constructor(
    protected reglementVenteService: ReglementVenteService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.reglementVenteService.delete(id).subscribe(() => {
      this.eventManager.broadcast('reglementVenteListModification');
      this.activeModal.close();
    });
  }
}
