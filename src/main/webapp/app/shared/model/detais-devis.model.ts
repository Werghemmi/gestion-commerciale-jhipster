export interface IDetaisDevis {
  id?: number;
  qteProduit?: number;
  totalHT?: number;
  totalTVA?: number;
  totalTTC?: number;
  produitId?: number;
  devisId?: number;
}

export class DetaisDevis implements IDetaisDevis {
  constructor(
    public id?: number,
    public qteProduit?: number,
    public totalHT?: number,
    public totalTVA?: number,
    public totalTTC?: number,
    public produitId?: number,
    public devisId?: number
  ) {}
}
