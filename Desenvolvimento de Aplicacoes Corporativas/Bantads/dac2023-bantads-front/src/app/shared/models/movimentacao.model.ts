export class Movimentacao {
  constructor(
    public tipoMovimentacao: number,
    public valor: number,
    public contaOrigem: number,
    public contaDestino?: number,
    public dataHoraCriacao?: string
  ) {}
}
