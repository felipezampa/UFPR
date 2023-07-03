import { EnderecoCompleto } from "./endereco-completo.model";


export class Autocadastro {
  constructor(
    public email?: string,
    public nome?: string,
    public ultimoNome?: string,
    public cpf?: string,
    public telefone?: string,
    public endereco?: EnderecoCompleto,
    public salario?: number,
    public id?: number | string
   ) {}
}
