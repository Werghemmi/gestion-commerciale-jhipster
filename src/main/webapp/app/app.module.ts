import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { WerghemmiGestionCommerclaieSharedModule } from 'app/shared/shared.module';
import { WerghemmiGestionCommerclaieCoreModule } from 'app/core/core.module';
import { WerghemmiGestionCommerclaieAppRoutingModule } from './app-routing.module';
import { WerghemmiGestionCommerclaieHomeModule } from './home/home.module';
import { WerghemmiGestionCommerclaieEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    WerghemmiGestionCommerclaieSharedModule,
    WerghemmiGestionCommerclaieCoreModule,
    WerghemmiGestionCommerclaieHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    WerghemmiGestionCommerclaieEntityModule,
    WerghemmiGestionCommerclaieAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class WerghemmiGestionCommerclaieAppModule {}
