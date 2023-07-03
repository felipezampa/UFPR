export default interface ItemExtrato {
  id: number;
  tipoMovimentacao: number;
  valor: number;
  dataHoraCriacao: string;
  contaOrigem: number;
  contaDestino: number | null;
}
