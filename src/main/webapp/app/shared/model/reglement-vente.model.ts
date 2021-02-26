import { Moment } from 'moment';
import { TypeReglement } from 'app/shared/model/enumerations/type-reglement.model';

export interface IReglementVente {
  id?: number;
  dateReglement?: Moment;
  typeReglement?: TypeReglement;
}

export class ReglementVente implements IReglementVente {
  constructor(public id?: number, public dateReglement?: Moment, public typeReglement?: TypeReglement) {}
}
