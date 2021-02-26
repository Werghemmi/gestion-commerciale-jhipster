export interface IDetaisVente {
  id?: number;
  qteProduit?: number;
  prix?: number;
  produitId?: number;
  venteId?: number;
}

export class DetaisVente implements IDetaisVente {
  constructor(public id?: number, public qteProduit?: number, public prix?: number, public produitId?: number, public venteId?: number) {}
}
