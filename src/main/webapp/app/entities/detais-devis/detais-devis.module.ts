import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WerghemmiGestionCommerclaieSharedModule } from 'app/shared/shared.module';
import { DetaisDevisComponent } from './detais-devis.component';
import { DetaisDevisDetailComponent } from './detais-devis-detail.component';
import { DetaisDevisUpdateComponent } from './detais-devis-update.component';
import { DetaisDevisDeleteDialogComponent } from './detais-devis-delete-dialog.component';
import { detaisDevisRoute } from './detais-devis.route';

@NgModule({
  imports: [WerghemmiGestionCommerclaieSharedModule, RouterModule.forChild(detaisDevisRoute)],
  declarations: [DetaisDevisComponent, DetaisDevisDetailComponent, DetaisDevisUpdateComponent, DetaisDevisDeleteDialogComponent],
  entryComponents: [DetaisDevisDeleteDialogComponent],
})
export class WerghemmiGestionCommerclaieDetaisDevisModule {}
