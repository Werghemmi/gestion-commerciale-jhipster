import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDetaisVente } from 'app/shared/model/detais-vente.model';
import { DetaisVenteService } from './detais-vente.service';

@Component({
  templateUrl: './detais-vente-delete-dialog.component.html',
})
export class DetaisVenteDeleteDialogComponent {
  detaisVente?: IDetaisVente;

  constructor(
    protected detaisVenteService: DetaisVenteService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.detaisVenteService.delete(id).subscribe(() => {
      this.eventManager.broadcast('detaisVenteListModification');
      this.activeModal.close();
    });
  }
}
