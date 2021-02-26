export interface IDetaisCommande {
  id?: number;
  qteProduit?: number;
  prix?: number;
  produitId?: number;
  commandeId?: number;
}

export class DetaisCommande implements IDetaisCommande {
  constructor(
    public id?: number,
    public qteProduit?: number,
    public prix?: number,
    public produitId?: number,
    public commandeId?: number
  ) {}
}
