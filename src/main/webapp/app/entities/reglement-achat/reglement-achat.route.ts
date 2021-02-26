import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IReglementAchat, ReglementAchat } from 'app/shared/model/reglement-achat.model';
import { ReglementAchatService } from './reglement-achat.service';
import { ReglementAchatComponent } from './reglement-achat.component';
import { ReglementAchatDetailComponent } from './reglement-achat-detail.component';
import { ReglementAchatUpdateComponent } from './reglement-achat-update.component';

@Injectable({ providedIn: 'root' })
export class ReglementAchatResolve implements Resolve<IReglementAchat> {
  constructor(private service: ReglementAchatService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IReglementAchat> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((reglementAchat: HttpResponse<ReglementAchat>) => {
          if (reglementAchat.body) {
            return of(reglementAchat.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ReglementAchat());
  }
}

export const reglementAchatRoute: Routes = [
  {
    path: '',
    component: ReglementAchatComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'ReglementAchats',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ReglementAchatDetailComponent,
    resolve: {
      reglementAchat: ReglementAchatResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ReglementAchats',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ReglementAchatUpdateComponent,
    resolve: {
      reglementAchat: ReglementAchatResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ReglementAchats',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ReglementAchatUpdateComponent,
    resolve: {
      reglementAchat: ReglementAchatResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ReglementAchats',
    },
    canActivate: [UserRouteAccessService],
  },
];
