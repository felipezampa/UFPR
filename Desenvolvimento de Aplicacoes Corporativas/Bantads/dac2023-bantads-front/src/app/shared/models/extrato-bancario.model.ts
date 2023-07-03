import { ExtratoDiario } from './extrato-diario.model';

export class ExtratoBancario {
  public dataInicial!: Date | number;
  public dataFinal!: Date | number;
  public dias!: Array<ExtratoDiario>;

  //create constructor
  constructor(dataInicial: number = +new Date(), dataFinal: number = +new Date(), dias: Array<ExtratoDiario> = []) {
    this.dataInicial = dataInicial;
    this.dataFinal = dataFinal;
    this.dias = dias;
  }
}
