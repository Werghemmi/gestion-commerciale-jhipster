import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WerghemmiGestionCommerclaieSharedModule } from 'app/shared/shared.module';
import { MouvementComponent } from './mouvement.component';
import { MouvementDetailComponent } from './mouvement-detail.component';
import { MouvementUpdateComponent } from './mouvement-update.component';
import { MouvementDeleteDialogComponent } from './mouvement-delete-dialog.component';
import { mouvementRoute } from './mouvement.route';

@NgModule({
  imports: [WerghemmiGestionCommerclaieSharedModule, RouterModule.forChild(mouvementRoute)],
  declarations: [MouvementComponent, MouvementDetailComponent, MouvementUpdateComponent, MouvementDeleteDialogComponent],
  entryComponents: [MouvementDeleteDialogComponent],
})
export class WerghemmiGestionCommerclaieMouvementModule {}
