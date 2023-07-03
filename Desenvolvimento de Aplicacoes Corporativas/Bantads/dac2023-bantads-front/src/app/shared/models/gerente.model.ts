import { Conta } from './conta.model';

export class Gerente {
  constructor(
    public id?: number,
    public nome?: string,
    public email?: string,
    public cpf?: string,
    public phone?: string,
    public contas?: Conta[],
    public numClientes?: any
  ) {}
}
