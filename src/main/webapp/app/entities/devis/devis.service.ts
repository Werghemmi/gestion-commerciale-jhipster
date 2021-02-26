import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDevis } from 'app/shared/model/devis.model';

type EntityResponseType = HttpResponse<IDevis>;
type EntityArrayResponseType = HttpResponse<IDevis[]>;

@Injectable({ providedIn: 'root' })
export class DevisService {
  public resourceUrl = SERVER_API_URL + 'api/devis';

  constructor(protected http: HttpClient) {}

  create(devis: IDevis): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(devis);
    return this.http
      .post<IDevis>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(devis: IDevis): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(devis);
    return this.http
      .put<IDevis>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDevis>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDevis[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(devis: IDevis): IDevis {
    const copy: IDevis = Object.assign({}, devis, {
      dateDevis: devis.dateDevis && devis.dateDevis.isValid() ? devis.dateDevis.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateDevis = res.body.dateDevis ? moment(res.body.dateDevis) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((devis: IDevis) => {
        devis.dateDevis = devis.dateDevis ? moment(devis.dateDevis) : undefined;
      });
    }
    return res;
  }
}
