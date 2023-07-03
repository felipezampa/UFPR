import { Conta } from './conta.model';
export interface Endereco {
  id: null | string;
  cep: string;
  logradouro: string;
  numero: number;
  complemento: string;
  bairro: string;
  cidade: string;
  estado: string;
}

export interface UsuarioAdmin {
  cpf: string;
  email: string;
  endereco: Endereco;
  nome: string;
  salario: number;
  id: string;
  telefone: string | null;
  conta: ContaAdmin | null;
}


export interface ContaAdmin {
  idCliente: string;
  idGerente: string;
  numero: number;
  saldo: number;
  dataCriacao: string;
  limite: number;
}
