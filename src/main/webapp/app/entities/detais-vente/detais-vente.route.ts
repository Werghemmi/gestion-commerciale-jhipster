import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDetaisVente, DetaisVente } from 'app/shared/model/detais-vente.model';
import { DetaisVenteService } from './detais-vente.service';
import { DetaisVenteComponent } from './detais-vente.component';
import { DetaisVenteDetailComponent } from './detais-vente-detail.component';
import { DetaisVenteUpdateComponent } from './detais-vente-update.component';

@Injectable({ providedIn: 'root' })
export class DetaisVenteResolve implements Resolve<IDetaisVente> {
  constructor(private service: DetaisVenteService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDetaisVente> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((detaisVente: HttpResponse<DetaisVente>) => {
          if (detaisVente.body) {
            return of(detaisVente.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DetaisVente());
  }
}

export const detaisVenteRoute: Routes = [
  {
    path: '',
    component: DetaisVenteComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'DetaisVentes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DetaisVenteDetailComponent,
    resolve: {
      detaisVente: DetaisVenteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'DetaisVentes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DetaisVenteUpdateComponent,
    resolve: {
      detaisVente: DetaisVenteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'DetaisVentes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DetaisVenteUpdateComponent,
    resolve: {
      detaisVente: DetaisVenteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'DetaisVentes',
    },
    canActivate: [UserRouteAccessService],
  },
];
