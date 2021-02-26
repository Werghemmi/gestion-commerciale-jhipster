import { Moment } from 'moment';

export interface IFactureVente {
  id?: number;
  dateFacture?: Moment;
  totalHT?: number;
  totalTVA?: number;
  totalTTC?: number;
  totalRemise?: number;
  totalNet?: number;
  timbreFiscale?: boolean;
  reglementVenteId?: number;
}

export class FactureVente implements IFactureVente {
  constructor(
    public id?: number,
    public dateFacture?: Moment,
    public totalHT?: number,
    public totalTVA?: number,
    public totalTTC?: number,
    public totalRemise?: number,
    public totalNet?: number,
    public timbreFiscale?: boolean,
    public reglementVenteId?: number
  ) {
    this.timbreFiscale = this.timbreFiscale || false;
  }
}
