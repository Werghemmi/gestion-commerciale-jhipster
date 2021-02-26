import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WerghemmiGestionCommerclaieSharedModule } from 'app/shared/shared.module';
import { DetaisCommandeComponent } from './detais-commande.component';
import { DetaisCommandeDetailComponent } from './detais-commande-detail.component';
import { DetaisCommandeUpdateComponent } from './detais-commande-update.component';
import { DetaisCommandeDeleteDialogComponent } from './detais-commande-delete-dialog.component';
import { detaisCommandeRoute } from './detais-commande.route';

@NgModule({
  imports: [WerghemmiGestionCommerclaieSharedModule, RouterModule.forChild(detaisCommandeRoute)],
  declarations: [
    DetaisCommandeComponent,
    DetaisCommandeDetailComponent,
    DetaisCommandeUpdateComponent,
    DetaisCommandeDeleteDialogComponent,
  ],
  entryComponents: [DetaisCommandeDeleteDialogComponent],
})
export class WerghemmiGestionCommerclaieDetaisCommandeModule {}
