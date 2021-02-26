import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IReglementVente, ReglementVente } from 'app/shared/model/reglement-vente.model';
import { ReglementVenteService } from './reglement-vente.service';
import { ReglementVenteComponent } from './reglement-vente.component';
import { ReglementVenteDetailComponent } from './reglement-vente-detail.component';
import { ReglementVenteUpdateComponent } from './reglement-vente-update.component';

@Injectable({ providedIn: 'root' })
export class ReglementVenteResolve implements Resolve<IReglementVente> {
  constructor(private service: ReglementVenteService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IReglementVente> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((reglementVente: HttpResponse<ReglementVente>) => {
          if (reglementVente.body) {
            return of(reglementVente.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ReglementVente());
  }
}

export const reglementVenteRoute: Routes = [
  {
    path: '',
    component: ReglementVenteComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'ReglementVentes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ReglementVenteDetailComponent,
    resolve: {
      reglementVente: ReglementVenteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ReglementVentes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ReglementVenteUpdateComponent,
    resolve: {
      reglementVente: ReglementVenteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ReglementVentes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ReglementVenteUpdateComponent,
    resolve: {
      reglementVente: ReglementVenteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ReglementVentes',
    },
    canActivate: [UserRouteAccessService],
  },
];
