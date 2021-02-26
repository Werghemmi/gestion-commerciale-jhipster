import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WerghemmiGestionCommerclaieSharedModule } from 'app/shared/shared.module';
import { DevisComponent } from './devis.component';
import { DevisDetailComponent } from './devis-detail.component';
import { DevisUpdateComponent } from './devis-update.component';
import { DevisDeleteDialogComponent } from './devis-delete-dialog.component';
import { devisRoute } from './devis.route';

@NgModule({
  imports: [WerghemmiGestionCommerclaieSharedModule, RouterModule.forChild(devisRoute)],
  declarations: [DevisComponent, DevisDetailComponent, DevisUpdateComponent, DevisDeleteDialogComponent],
  entryComponents: [DevisDeleteDialogComponent],
})
export class WerghemmiGestionCommerclaieDevisModule {}
