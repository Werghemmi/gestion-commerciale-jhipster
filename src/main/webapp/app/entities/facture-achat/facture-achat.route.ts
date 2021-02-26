import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFactureAchat, FactureAchat } from 'app/shared/model/facture-achat.model';
import { FactureAchatService } from './facture-achat.service';
import { FactureAchatComponent } from './facture-achat.component';
import { FactureAchatDetailComponent } from './facture-achat-detail.component';
import { FactureAchatUpdateComponent } from './facture-achat-update.component';

@Injectable({ providedIn: 'root' })
export class FactureAchatResolve implements Resolve<IFactureAchat> {
  constructor(private service: FactureAchatService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFactureAchat> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((factureAchat: HttpResponse<FactureAchat>) => {
          if (factureAchat.body) {
            return of(factureAchat.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FactureAchat());
  }
}

export const factureAchatRoute: Routes = [
  {
    path: '',
    component: FactureAchatComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'FactureAchats',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FactureAchatDetailComponent,
    resolve: {
      factureAchat: FactureAchatResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'FactureAchats',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FactureAchatUpdateComponent,
    resolve: {
      factureAchat: FactureAchatResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'FactureAchats',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FactureAchatUpdateComponent,
    resolve: {
      factureAchat: FactureAchatResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'FactureAchats',
    },
    canActivate: [UserRouteAccessService],
  },
];
