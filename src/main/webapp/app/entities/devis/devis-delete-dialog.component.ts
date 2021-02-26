import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDevis } from 'app/shared/model/devis.model';
import { DevisService } from './devis.service';

@Component({
  templateUrl: './devis-delete-dialog.component.html',
})
export class DevisDeleteDialogComponent {
  devis?: IDevis;

  constructor(protected devisService: DevisService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.devisService.delete(id).subscribe(() => {
      this.eventManager.broadcast('devisListModification');
      this.activeModal.close();
    });
  }
}
