export class Transacao{
  constructor(
    public id?: number,
    public numeroConta?: number,
    public valor?: number,
    public tipo?: string, //deposit/withdraw/transfer
    public data?: Date,
    public origem?: string

  ){}
}
