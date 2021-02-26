import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WerghemmiGestionCommerclaieSharedModule } from 'app/shared/shared.module';
import { ReglementAchatComponent } from './reglement-achat.component';
import { ReglementAchatDetailComponent } from './reglement-achat-detail.component';
import { ReglementAchatUpdateComponent } from './reglement-achat-update.component';
import { ReglementAchatDeleteDialogComponent } from './reglement-achat-delete-dialog.component';
import { reglementAchatRoute } from './reglement-achat.route';

@NgModule({
  imports: [WerghemmiGestionCommerclaieSharedModule, RouterModule.forChild(reglementAchatRoute)],
  declarations: [
    ReglementAchatComponent,
    ReglementAchatDetailComponent,
    ReglementAchatUpdateComponent,
    ReglementAchatDeleteDialogComponent,
  ],
  entryComponents: [ReglementAchatDeleteDialogComponent],
})
export class WerghemmiGestionCommerclaieReglementAchatModule {}
