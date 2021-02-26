import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IReglementAchat } from 'app/shared/model/reglement-achat.model';

type EntityResponseType = HttpResponse<IReglementAchat>;
type EntityArrayResponseType = HttpResponse<IReglementAchat[]>;

@Injectable({ providedIn: 'root' })
export class ReglementAchatService {
  public resourceUrl = SERVER_API_URL + 'api/reglement-achats';

  constructor(protected http: HttpClient) {}

  create(reglementAchat: IReglementAchat): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(reglementAchat);
    return this.http
      .post<IReglementAchat>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(reglementAchat: IReglementAchat): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(reglementAchat);
    return this.http
      .put<IReglementAchat>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IReglementAchat>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IReglementAchat[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(reglementAchat: IReglementAchat): IReglementAchat {
    const copy: IReglementAchat = Object.assign({}, reglementAchat, {
      dateReglement:
        reglementAchat.dateReglement && reglementAchat.dateReglement.isValid() ? reglementAchat.dateReglement.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateReglement = res.body.dateReglement ? moment(res.body.dateReglement) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((reglementAchat: IReglementAchat) => {
        reglementAchat.dateReglement = reglementAchat.dateReglement ? moment(reglementAchat.dateReglement) : undefined;
      });
    }
    return res;
  }
}
