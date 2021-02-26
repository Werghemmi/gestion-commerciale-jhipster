import { Moment } from 'moment';
import { TypeMouvement } from 'app/shared/model/enumerations/type-mouvement.model';

export interface IMouvement {
  id?: number;
  dateMvt?: Moment;
  typeMvt?: TypeMouvement;
  qteMvt?: number;
  ancienQte?: number;
  nvQte?: number;
  ancienPrix?: number;
  nvPrix?: number;
  prix?: number;
  produitId?: number;
}

export class Mouvement implements IMouvement {
  constructor(
    public id?: number,
    public dateMvt?: Moment,
    public typeMvt?: TypeMouvement,
    public qteMvt?: number,
    public ancienQte?: number,
    public nvQte?: number,
    public ancienPrix?: number,
    public nvPrix?: number,
    public prix?: number,
    public produitId?: number
  ) {}
}
