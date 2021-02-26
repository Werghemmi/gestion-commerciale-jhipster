import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFactureAchat } from 'app/shared/model/facture-achat.model';

type EntityResponseType = HttpResponse<IFactureAchat>;
type EntityArrayResponseType = HttpResponse<IFactureAchat[]>;

@Injectable({ providedIn: 'root' })
export class FactureAchatService {
  public resourceUrl = SERVER_API_URL + 'api/facture-achats';

  constructor(protected http: HttpClient) {}

  create(factureAchat: IFactureAchat): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(factureAchat);
    return this.http
      .post<IFactureAchat>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(factureAchat: IFactureAchat): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(factureAchat);
    return this.http
      .put<IFactureAchat>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFactureAchat>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFactureAchat[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(factureAchat: IFactureAchat): IFactureAchat {
    const copy: IFactureAchat = Object.assign({}, factureAchat, {
      dateFacture: factureAchat.dateFacture && factureAchat.dateFacture.isValid() ? factureAchat.dateFacture.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateFacture = res.body.dateFacture ? moment(res.body.dateFacture) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((factureAchat: IFactureAchat) => {
        factureAchat.dateFacture = factureAchat.dateFacture ? moment(factureAchat.dateFacture) : undefined;
      });
    }
    return res;
  }
}
