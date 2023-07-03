export class UsuarioLogado {
  constructor(
    public id?: string,
    public nome?: string,
    public email?: string,
    public senha?: string,
    public perfil?: string,
    public token?: string,
  ) {}
}
