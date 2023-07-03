import { Conta } from './conta.model';

export interface Gerente {
  id: string;
  nome: string;
  email: string;
  cpf: string;
  phone: string;
  numClientes: number;
}

export interface Pessoa {
  id: number;
  nome: string;
  cpf: string;
  salario: number;
  saga: string;
  aprovado: Boolean;
  gerente: Gerente;
  conta: Conta | null;
}
