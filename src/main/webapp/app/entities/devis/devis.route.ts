import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDevis, Devis } from 'app/shared/model/devis.model';
import { DevisService } from './devis.service';
import { DevisComponent } from './devis.component';
import { DevisDetailComponent } from './devis-detail.component';
import { DevisUpdateComponent } from './devis-update.component';

@Injectable({ providedIn: 'root' })
export class DevisResolve implements Resolve<IDevis> {
  constructor(private service: DevisService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDevis> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((devis: HttpResponse<Devis>) => {
          if (devis.body) {
            return of(devis.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Devis());
  }
}

export const devisRoute: Routes = [
  {
    path: '',
    component: DevisComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Devis',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DevisDetailComponent,
    resolve: {
      devis: DevisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Devis',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DevisUpdateComponent,
    resolve: {
      devis: DevisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Devis',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DevisUpdateComponent,
    resolve: {
      devis: DevisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Devis',
    },
    canActivate: [UserRouteAccessService],
  },
];
