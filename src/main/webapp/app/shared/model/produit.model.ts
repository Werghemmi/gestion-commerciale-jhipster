import { IMouvement } from 'app/shared/model/mouvement.model';
import { IDetaisDevis } from 'app/shared/model/detais-devis.model';
import { IDetaisCommande } from 'app/shared/model/detais-commande.model';
import { IDetaisVente } from 'app/shared/model/detais-vente.model';
import { Type } from 'app/shared/model/enumerations/type.model';

export interface IProduit {
  id?: number;
  nom?: string;
  description?: string;
  prixAchat?: number;
  prixVente?: number;
  qteStock?: number;
  marge?: number;
  typeMarge?: Type;
  mouvements?: IMouvement[];
  detaisDevis?: IDetaisDevis[];
  detaisCommandes?: IDetaisCommande[];
  detaisVentes?: IDetaisVente[];
  categorieId?: number;
}

export class Produit implements IProduit {
  constructor(
    public id?: number,
    public nom?: string,
    public description?: string,
    public prixAchat?: number,
    public prixVente?: number,
    public qteStock?: number,
    public marge?: number,
    public typeMarge?: Type,
    public mouvements?: IMouvement[],
    public detaisDevis?: IDetaisDevis[],
    public detaisCommandes?: IDetaisCommande[],
    public detaisVentes?: IDetaisVente[],
    public categorieId?: number
  ) {}
}
