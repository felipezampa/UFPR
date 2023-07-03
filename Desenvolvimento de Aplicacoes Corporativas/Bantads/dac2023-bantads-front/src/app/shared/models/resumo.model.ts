export class Resumo{
    constructor(
        public idCliente: String, 
        public idGerente: String,
        public numero: number,
        public saldo: number,
        public dataCriacao: String,
        public limite: number        
        ){
    }
}