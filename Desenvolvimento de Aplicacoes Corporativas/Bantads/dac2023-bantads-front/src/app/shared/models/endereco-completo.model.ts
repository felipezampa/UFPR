export class EnderecoCompleto {
    constructor(
        public cep?: String,
        public logradouro?: String,
        public numero?: Number,
        public complemento?: String,
        public bairro?: String,
        public cidade?: String,
        public estado?: String
    ){}
} 