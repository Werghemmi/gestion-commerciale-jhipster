import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDetaisDevis } from 'app/shared/model/detais-devis.model';

type EntityResponseType = HttpResponse<IDetaisDevis>;
type EntityArrayResponseType = HttpResponse<IDetaisDevis[]>;

@Injectable({ providedIn: 'root' })
export class DetaisDevisService {
  public resourceUrl = SERVER_API_URL + 'api/detais-devis';

  constructor(protected http: HttpClient) {}

  create(detaisDevis: IDetaisDevis): Observable<EntityResponseType> {
    return this.http.post<IDetaisDevis>(this.resourceUrl, detaisDevis, { observe: 'response' });
  }

  update(detaisDevis: IDetaisDevis): Observable<EntityResponseType> {
    return this.http.put<IDetaisDevis>(this.resourceUrl, detaisDevis, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDetaisDevis>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDetaisDevis[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
