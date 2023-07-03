import ItemExtrato from "./itemextrato.model";

export default interface BalancoPorDia {
  data: string;
  balanco: number;
  items: ItemExtrato[];
}
