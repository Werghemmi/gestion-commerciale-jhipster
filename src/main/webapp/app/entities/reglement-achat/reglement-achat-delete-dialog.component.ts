import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IReglementAchat } from 'app/shared/model/reglement-achat.model';
import { ReglementAchatService } from './reglement-achat.service';

@Component({
  templateUrl: './reglement-achat-delete-dialog.component.html',
})
export class ReglementAchatDeleteDialogComponent {
  reglementAchat?: IReglementAchat;

  constructor(
    protected reglementAchatService: ReglementAchatService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.reglementAchatService.delete(id).subscribe(() => {
      this.eventManager.broadcast('reglementAchatListModification');
      this.activeModal.close();
    });
  }
}
