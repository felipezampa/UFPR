import { Transacao } from './transacao.model';

export class ExtratoDiario {
  public balancoDiario!: number;
  public transacoes!: Array<Transacao>;
  public dia!: Date | number;


  constructor() {}
}
