import { Moment } from 'moment';
import { IDetaisVente } from 'app/shared/model/detais-vente.model';

export interface IVente {
  id?: number;
  dateCommande?: Moment;
  factureVenteId?: number;
  detaisVentes?: IDetaisVente[];
  clientId?: number;
}

export class Vente implements IVente {
  constructor(
    public id?: number,
    public dateCommande?: Moment,
    public factureVenteId?: number,
    public detaisVentes?: IDetaisVente[],
    public clientId?: number
  ) {}
}
