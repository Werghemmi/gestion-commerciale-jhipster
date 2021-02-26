import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITaxe } from 'app/shared/model/taxe.model';
import { TaxeService } from './taxe.service';

@Component({
  templateUrl: './taxe-delete-dialog.component.html',
})
export class TaxeDeleteDialogComponent {
  taxe?: ITaxe;

  constructor(protected taxeService: TaxeService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.taxeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('taxeListModification');
      this.activeModal.close();
    });
  }
}
