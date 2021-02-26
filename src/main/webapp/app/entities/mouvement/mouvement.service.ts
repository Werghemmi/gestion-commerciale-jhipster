import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMouvement } from 'app/shared/model/mouvement.model';

type EntityResponseType = HttpResponse<IMouvement>;
type EntityArrayResponseType = HttpResponse<IMouvement[]>;

@Injectable({ providedIn: 'root' })
export class MouvementService {
  public resourceUrl = SERVER_API_URL + 'api/mouvements';

  constructor(protected http: HttpClient) {}

  create(mouvement: IMouvement): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(mouvement);
    return this.http
      .post<IMouvement>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(mouvement: IMouvement): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(mouvement);
    return this.http
      .put<IMouvement>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMouvement>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IMouvement[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(mouvement: IMouvement): IMouvement {
    const copy: IMouvement = Object.assign({}, mouvement, {
      dateMvt: mouvement.dateMvt && mouvement.dateMvt.isValid() ? mouvement.dateMvt.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateMvt = res.body.dateMvt ? moment(res.body.dateMvt) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((mouvement: IMouvement) => {
        mouvement.dateMvt = mouvement.dateMvt ? moment(mouvement.dateMvt) : undefined;
      });
    }
    return res;
  }
}
