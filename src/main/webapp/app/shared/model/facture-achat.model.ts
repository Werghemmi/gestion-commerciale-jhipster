import { Moment } from 'moment';

export interface IFactureAchat {
  id?: number;
  dateFacture?: Moment;
  totalHT?: number;
  totalTVA?: number;
  totalTTC?: number;
  totalRemise?: number;
  totalNet?: number;
  timbreFiscale?: boolean;
  reglementAchatId?: number;
}

export class FactureAchat implements IFactureAchat {
  constructor(
    public id?: number,
    public dateFacture?: Moment,
    public totalHT?: number,
    public totalTVA?: number,
    public totalTTC?: number,
    public totalRemise?: number,
    public totalNet?: number,
    public timbreFiscale?: boolean,
    public reglementAchatId?: number
  ) {
    this.timbreFiscale = this.timbreFiscale || false;
  }
}
