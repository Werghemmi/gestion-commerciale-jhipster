import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFactureVente, FactureVente } from 'app/shared/model/facture-vente.model';
import { FactureVenteService } from './facture-vente.service';
import { FactureVenteComponent } from './facture-vente.component';
import { FactureVenteDetailComponent } from './facture-vente-detail.component';
import { FactureVenteUpdateComponent } from './facture-vente-update.component';

@Injectable({ providedIn: 'root' })
export class FactureVenteResolve implements Resolve<IFactureVente> {
  constructor(private service: FactureVenteService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFactureVente> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((factureVente: HttpResponse<FactureVente>) => {
          if (factureVente.body) {
            return of(factureVente.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FactureVente());
  }
}

export const factureVenteRoute: Routes = [
  {
    path: '',
    component: FactureVenteComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'FactureVentes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FactureVenteDetailComponent,
    resolve: {
      factureVente: FactureVenteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'FactureVentes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FactureVenteUpdateComponent,
    resolve: {
      factureVente: FactureVenteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'FactureVentes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FactureVenteUpdateComponent,
    resolve: {
      factureVente: FactureVenteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'FactureVentes',
    },
    canActivate: [UserRouteAccessService],
  },
];
