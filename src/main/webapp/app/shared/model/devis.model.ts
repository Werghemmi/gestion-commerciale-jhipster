import { Moment } from 'moment';
import { IDetaisDevis } from 'app/shared/model/detais-devis.model';

export interface IDevis {
  id?: number;
  dateDevis?: Moment;
  detaisDevis?: IDetaisDevis[];
}

export class Devis implements IDevis {
  constructor(public id?: number, public dateDevis?: Moment, public detaisDevis?: IDetaisDevis[]) {}
}
