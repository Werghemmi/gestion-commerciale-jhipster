import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDetaisDevis, DetaisDevis } from 'app/shared/model/detais-devis.model';
import { DetaisDevisService } from './detais-devis.service';
import { DetaisDevisComponent } from './detais-devis.component';
import { DetaisDevisDetailComponent } from './detais-devis-detail.component';
import { DetaisDevisUpdateComponent } from './detais-devis-update.component';

@Injectable({ providedIn: 'root' })
export class DetaisDevisResolve implements Resolve<IDetaisDevis> {
  constructor(private service: DetaisDevisService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDetaisDevis> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((detaisDevis: HttpResponse<DetaisDevis>) => {
          if (detaisDevis.body) {
            return of(detaisDevis.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DetaisDevis());
  }
}

export const detaisDevisRoute: Routes = [
  {
    path: '',
    component: DetaisDevisComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'DetaisDevis',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DetaisDevisDetailComponent,
    resolve: {
      detaisDevis: DetaisDevisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'DetaisDevis',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DetaisDevisUpdateComponent,
    resolve: {
      detaisDevis: DetaisDevisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'DetaisDevis',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DetaisDevisUpdateComponent,
    resolve: {
      detaisDevis: DetaisDevisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'DetaisDevis',
    },
    canActivate: [UserRouteAccessService],
  },
];
