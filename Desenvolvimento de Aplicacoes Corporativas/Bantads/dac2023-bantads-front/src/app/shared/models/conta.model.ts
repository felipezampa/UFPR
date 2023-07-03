import { Gerente } from "./gerente.model";
import { Usuario } from "./usuario.model";

export class Conta {
  constructor(
    public id?: number,
    public usuario: Usuario = new Usuario(),
    public numeroConta?: number,
    public dataCriacao?: Date,
    public dataResposta?: Date,
    public limite: number = 0,
    public estaAtivo: boolean = false,
    public status: number = 0,
    public gerente?: Gerente,
    public saldo?: number,
    public motivoReprovacao: string = '',
    public numero?: number
  ){}
}
