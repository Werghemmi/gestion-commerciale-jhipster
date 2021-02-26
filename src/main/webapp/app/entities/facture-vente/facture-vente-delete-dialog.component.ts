import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFactureVente } from 'app/shared/model/facture-vente.model';
import { FactureVenteService } from './facture-vente.service';

@Component({
  templateUrl: './facture-vente-delete-dialog.component.html',
})
export class FactureVenteDeleteDialogComponent {
  factureVente?: IFactureVente;

  constructor(
    protected factureVenteService: FactureVenteService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.factureVenteService.delete(id).subscribe(() => {
      this.eventManager.broadcast('factureVenteListModification');
      this.activeModal.close();
    });
  }
}
