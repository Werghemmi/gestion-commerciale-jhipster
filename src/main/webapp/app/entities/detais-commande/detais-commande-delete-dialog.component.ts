import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDetaisCommande } from 'app/shared/model/detais-commande.model';
import { DetaisCommandeService } from './detais-commande.service';

@Component({
  templateUrl: './detais-commande-delete-dialog.component.html',
})
export class DetaisCommandeDeleteDialogComponent {
  detaisCommande?: IDetaisCommande;

  constructor(
    protected detaisCommandeService: DetaisCommandeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.detaisCommandeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('detaisCommandeListModification');
      this.activeModal.close();
    });
  }
}
