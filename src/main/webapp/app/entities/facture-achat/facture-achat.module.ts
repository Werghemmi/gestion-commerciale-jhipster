import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WerghemmiGestionCommerclaieSharedModule } from 'app/shared/shared.module';
import { FactureAchatComponent } from './facture-achat.component';
import { FactureAchatDetailComponent } from './facture-achat-detail.component';
import { FactureAchatUpdateComponent } from './facture-achat-update.component';
import { FactureAchatDeleteDialogComponent } from './facture-achat-delete-dialog.component';
import { factureAchatRoute } from './facture-achat.route';

@NgModule({
  imports: [WerghemmiGestionCommerclaieSharedModule, RouterModule.forChild(factureAchatRoute)],
  declarations: [FactureAchatComponent, FactureAchatDetailComponent, FactureAchatUpdateComponent, FactureAchatDeleteDialogComponent],
  entryComponents: [FactureAchatDeleteDialogComponent],
})
export class WerghemmiGestionCommerclaieFactureAchatModule {}
