import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDetaisDevis } from 'app/shared/model/detais-devis.model';
import { DetaisDevisService } from './detais-devis.service';

@Component({
  templateUrl: './detais-devis-delete-dialog.component.html',
})
export class DetaisDevisDeleteDialogComponent {
  detaisDevis?: IDetaisDevis;

  constructor(
    protected detaisDevisService: DetaisDevisService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.detaisDevisService.delete(id).subscribe(() => {
      this.eventManager.broadcast('detaisDevisListModification');
      this.activeModal.close();
    });
  }
}
