import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITaxe } from 'app/shared/model/taxe.model';

type EntityResponseType = HttpResponse<ITaxe>;
type EntityArrayResponseType = HttpResponse<ITaxe[]>;

@Injectable({ providedIn: 'root' })
export class TaxeService {
  public resourceUrl = SERVER_API_URL + 'api/taxes';

  constructor(protected http: HttpClient) {}

  create(taxe: ITaxe): Observable<EntityResponseType> {
    return this.http.post<ITaxe>(this.resourceUrl, taxe, { observe: 'response' });
  }

  update(taxe: ITaxe): Observable<EntityResponseType> {
    return this.http.put<ITaxe>(this.resourceUrl, taxe, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITaxe>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITaxe[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
