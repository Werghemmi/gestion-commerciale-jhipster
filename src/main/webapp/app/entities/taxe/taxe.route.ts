import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITaxe, Taxe } from 'app/shared/model/taxe.model';
import { TaxeService } from './taxe.service';
import { TaxeComponent } from './taxe.component';
import { TaxeDetailComponent } from './taxe-detail.component';
import { TaxeUpdateComponent } from './taxe-update.component';

@Injectable({ providedIn: 'root' })
export class TaxeResolve implements Resolve<ITaxe> {
  constructor(private service: TaxeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITaxe> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((taxe: HttpResponse<Taxe>) => {
          if (taxe.body) {
            return of(taxe.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Taxe());
  }
}

export const taxeRoute: Routes = [
  {
    path: '',
    component: TaxeComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Taxes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TaxeDetailComponent,
    resolve: {
      taxe: TaxeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Taxes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TaxeUpdateComponent,
    resolve: {
      taxe: TaxeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Taxes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TaxeUpdateComponent,
    resolve: {
      taxe: TaxeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Taxes',
    },
    canActivate: [UserRouteAccessService],
  },
];
