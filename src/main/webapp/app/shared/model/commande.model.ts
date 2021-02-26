import { Moment } from 'moment';
import { IDetaisCommande } from 'app/shared/model/detais-commande.model';

export interface ICommande {
  id?: number;
  dateCommande?: Moment;
  factureAchatId?: number;
  detaisCommandes?: IDetaisCommande[];
  fournisseurId?: number;
}

export class Commande implements ICommande {
  constructor(
    public id?: number,
    public dateCommande?: Moment,
    public factureAchatId?: number,
    public detaisCommandes?: IDetaisCommande[],
    public fournisseurId?: number
  ) {}
}
