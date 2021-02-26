import { IProduit } from 'app/shared/model/produit.model';
import { ITaxe } from 'app/shared/model/taxe.model';

export interface ICategorie {
  id?: number;
  titre?: string;
  produits?: IProduit[];
  taxes?: ITaxe[];
}

export class Categorie implements ICategorie {
  constructor(public id?: number, public titre?: string, public produits?: IProduit[], public taxes?: ITaxe[]) {}
}
