import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WerghemmiGestionCommerclaieSharedModule } from 'app/shared/shared.module';
import { FactureVenteComponent } from './facture-vente.component';
import { FactureVenteDetailComponent } from './facture-vente-detail.component';
import { FactureVenteUpdateComponent } from './facture-vente-update.component';
import { FactureVenteDeleteDialogComponent } from './facture-vente-delete-dialog.component';
import { factureVenteRoute } from './facture-vente.route';

@NgModule({
  imports: [WerghemmiGestionCommerclaieSharedModule, RouterModule.forChild(factureVenteRoute)],
  declarations: [FactureVenteComponent, FactureVenteDetailComponent, FactureVenteUpdateComponent, FactureVenteDeleteDialogComponent],
  entryComponents: [FactureVenteDeleteDialogComponent],
})
export class WerghemmiGestionCommerclaieFactureVenteModule {}
