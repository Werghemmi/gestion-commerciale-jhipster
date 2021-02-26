import { Moment } from 'moment';
import { TypeReglement } from 'app/shared/model/enumerations/type-reglement.model';

export interface IReglementAchat {
  id?: number;
  dateReglement?: Moment;
  typeReglement?: TypeReglement;
}

export class ReglementAchat implements IReglementAchat {
  constructor(public id?: number, public dateReglement?: Moment, public typeReglement?: TypeReglement) {}
}
