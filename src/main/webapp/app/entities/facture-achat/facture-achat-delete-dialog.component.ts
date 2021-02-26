import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFactureAchat } from 'app/shared/model/facture-achat.model';
import { FactureAchatService } from './facture-achat.service';

@Component({
  templateUrl: './facture-achat-delete-dialog.component.html',
})
export class FactureAchatDeleteDialogComponent {
  factureAchat?: IFactureAchat;

  constructor(
    protected factureAchatService: FactureAchatService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.factureAchatService.delete(id).subscribe(() => {
      this.eventManager.broadcast('factureAchatListModification');
      this.activeModal.close();
    });
  }
}
