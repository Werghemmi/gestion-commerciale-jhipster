import { ICommande } from 'app/shared/model/commande.model';

export interface IFournisseur {
  id?: number;
  nom?: string;
  adresse?: string;
  telephone?: number;
  email?: string;
  matriculeFiscale?: string;
  commandes?: ICommande[];
}

export class Fournisseur implements IFournisseur {
  constructor(
    public id?: number,
    public nom?: string,
    public adresse?: string,
    public telephone?: number,
    public email?: string,
    public matriculeFiscale?: string,
    public commandes?: ICommande[]
  ) {}
}
