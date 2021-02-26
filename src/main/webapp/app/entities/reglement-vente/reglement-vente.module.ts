import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WerghemmiGestionCommerclaieSharedModule } from 'app/shared/shared.module';
import { ReglementVenteComponent } from './reglement-vente.component';
import { ReglementVenteDetailComponent } from './reglement-vente-detail.component';
import { ReglementVenteUpdateComponent } from './reglement-vente-update.component';
import { ReglementVenteDeleteDialogComponent } from './reglement-vente-delete-dialog.component';
import { reglementVenteRoute } from './reglement-vente.route';

@NgModule({
  imports: [WerghemmiGestionCommerclaieSharedModule, RouterModule.forChild(reglementVenteRoute)],
  declarations: [
    ReglementVenteComponent,
    ReglementVenteDetailComponent,
    ReglementVenteUpdateComponent,
    ReglementVenteDeleteDialogComponent,
  ],
  entryComponents: [ReglementVenteDeleteDialogComponent],
})
export class WerghemmiGestionCommerclaieReglementVenteModule {}
