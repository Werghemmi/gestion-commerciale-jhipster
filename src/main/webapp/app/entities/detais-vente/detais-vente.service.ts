import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDetaisVente } from 'app/shared/model/detais-vente.model';

type EntityResponseType = HttpResponse<IDetaisVente>;
type EntityArrayResponseType = HttpResponse<IDetaisVente[]>;

@Injectable({ providedIn: 'root' })
export class DetaisVenteService {
  public resourceUrl = SERVER_API_URL + 'api/detais-ventes';

  constructor(protected http: HttpClient) {}

  create(detaisVente: IDetaisVente): Observable<EntityResponseType> {
    return this.http.post<IDetaisVente>(this.resourceUrl, detaisVente, { observe: 'response' });
  }

  update(detaisVente: IDetaisVente): Observable<EntityResponseType> {
    return this.http.put<IDetaisVente>(this.resourceUrl, detaisVente, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDetaisVente>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDetaisVente[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
