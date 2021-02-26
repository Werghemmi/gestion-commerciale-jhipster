import { ICategorie } from 'app/shared/model/categorie.model';
import { Type } from 'app/shared/model/enumerations/type.model';

export interface ITaxe {
  id?: number;
  nomTaxe?: string;
  taux?: number;
  typeTaxe?: Type;
  categories?: ICategorie[];
}

export class Taxe implements ITaxe {
  constructor(
    public id?: number,
    public nomTaxe?: string,
    public taux?: number,
    public typeTaxe?: Type,
    public categories?: ICategorie[]
  ) {}
}
