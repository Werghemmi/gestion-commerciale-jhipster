import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDetaisCommande, DetaisCommande } from 'app/shared/model/detais-commande.model';
import { DetaisCommandeService } from './detais-commande.service';
import { DetaisCommandeComponent } from './detais-commande.component';
import { DetaisCommandeDetailComponent } from './detais-commande-detail.component';
import { DetaisCommandeUpdateComponent } from './detais-commande-update.component';

@Injectable({ providedIn: 'root' })
export class DetaisCommandeResolve implements Resolve<IDetaisCommande> {
  constructor(private service: DetaisCommandeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDetaisCommande> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((detaisCommande: HttpResponse<DetaisCommande>) => {
          if (detaisCommande.body) {
            return of(detaisCommande.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DetaisCommande());
  }
}

export const detaisCommandeRoute: Routes = [
  {
    path: '',
    component: DetaisCommandeComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'DetaisCommandes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DetaisCommandeDetailComponent,
    resolve: {
      detaisCommande: DetaisCommandeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'DetaisCommandes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DetaisCommandeUpdateComponent,
    resolve: {
      detaisCommande: DetaisCommandeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'DetaisCommandes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DetaisCommandeUpdateComponent,
    resolve: {
      detaisCommande: DetaisCommandeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'DetaisCommandes',
    },
    canActivate: [UserRouteAccessService],
  },
];
