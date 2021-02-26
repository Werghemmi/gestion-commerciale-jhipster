import { IVente } from 'app/shared/model/vente.model';

export interface IClient {
  id?: number;
  nom?: string;
  adresse?: string;
  telephone?: number;
  email?: string;
  matriculeFiscale?: string;
  ventes?: IVente[];
}

export class Client implements IClient {
  constructor(
    public id?: number,
    public nom?: string,
    public adresse?: string,
    public telephone?: number,
    public email?: string,
    public matriculeFiscale?: string,
    public ventes?: IVente[]
  ) {}
}
