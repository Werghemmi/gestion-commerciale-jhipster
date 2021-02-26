import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFactureVente } from 'app/shared/model/facture-vente.model';

type EntityResponseType = HttpResponse<IFactureVente>;
type EntityArrayResponseType = HttpResponse<IFactureVente[]>;

@Injectable({ providedIn: 'root' })
export class FactureVenteService {
  public resourceUrl = SERVER_API_URL + 'api/facture-ventes';

  constructor(protected http: HttpClient) {}

  create(factureVente: IFactureVente): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(factureVente);
    return this.http
      .post<IFactureVente>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(factureVente: IFactureVente): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(factureVente);
    return this.http
      .put<IFactureVente>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFactureVente>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFactureVente[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(factureVente: IFactureVente): IFactureVente {
    const copy: IFactureVente = Object.assign({}, factureVente, {
      dateFacture: factureVente.dateFacture && factureVente.dateFacture.isValid() ? factureVente.dateFacture.toJSON() : undefined,
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
      res.body.forEach((factureVente: IFactureVente) => {
        factureVente.dateFacture = factureVente.dateFacture ? moment(factureVente.dateFacture) : undefined;
      });
    }
    return res;
  }
}
