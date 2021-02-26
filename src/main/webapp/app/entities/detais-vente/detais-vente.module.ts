import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WerghemmiGestionCommerclaieSharedModule } from 'app/shared/shared.module';
import { DetaisVenteComponent } from './detais-vente.component';
import { DetaisVenteDetailComponent } from './detais-vente-detail.component';
import { DetaisVenteUpdateComponent } from './detais-vente-update.component';
import { DetaisVenteDeleteDialogComponent } from './detais-vente-delete-dialog.component';
import { detaisVenteRoute } from './detais-vente.route';

@NgModule({
  imports: [WerghemmiGestionCommerclaieSharedModule, RouterModule.forChild(detaisVenteRoute)],
  declarations: [DetaisVenteComponent, DetaisVenteDetailComponent, DetaisVenteUpdateComponent, DetaisVenteDeleteDialogComponent],
  entryComponents: [DetaisVenteDeleteDialogComponent],
})
export class WerghemmiGestionCommerclaieDetaisVenteModule {}
