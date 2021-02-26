import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WerghemmiGestionCommerclaieSharedModule } from 'app/shared/shared.module';
import { TaxeComponent } from './taxe.component';
import { TaxeDetailComponent } from './taxe-detail.component';
import { TaxeUpdateComponent } from './taxe-update.component';
import { TaxeDeleteDialogComponent } from './taxe-delete-dialog.component';
import { taxeRoute } from './taxe.route';

@NgModule({
  imports: [WerghemmiGestionCommerclaieSharedModule, RouterModule.forChild(taxeRoute)],
  declarations: [TaxeComponent, TaxeDetailComponent, TaxeUpdateComponent, TaxeDeleteDialogComponent],
  entryComponents: [TaxeDeleteDialogComponent],
})
export class WerghemmiGestionCommerclaieTaxeModule {}
