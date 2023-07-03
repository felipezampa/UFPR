export class Usuario {
  constructor(
    public id?: number | string,
    public email?: string,
    public senha?: string,
    public nome?: string,
    public ultimoNome?: string,
    public cpf?: string,
    public telefone?: string,
    public dataAniversario?: Date,
    public cidade?: string,
    public estado?: string,
    public pais?: string,
    public bairro?: string,
    public endereco?: string,
    public salario?: number,
    public cep?: string,
    public cargo?: string,
    public ativo?: boolean,
    public estaAprovado?: boolean
  ) {}
}
