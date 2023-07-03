import { Conta } from '../../shared/models/conta.model';
import { ContaService } from '../../conta/conta.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Usuario } from 'src/app/shared/models/usuario.model';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { GerenteService } from 'src/app/gerente/services/gerente.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { SagaAutocadastroService } from '../services/saga-autocadastro.service';
import { Autocadastro } from 'src/app/shared/models/autocadastro.model';
import { EnderecoCompleto } from 'src/app/shared/models/endereco-completo.model';

@Component({
  selector: 'app-login',
  templateUrl: './autocadastro.component.html',
  styleUrls: ['./autocadastro.component.css'],
})
export class AutocadastroComponent implements OnInit {
  constructor(
    private authService: AuthService,
    private router: Router,
    private contaService: ContaService,
    private gerenteService: GerenteService,
    public activeModal: NgbActiveModal,
    public toastr: ToastrService,
    private sagaAutocadastro: SagaAutocadastroService
  ) { }

  @ViewChild('cadastroUsuario') cadastroUsuario!: NgForm;

  usuario!: Usuario;
  conta!: Conta;
  hide = true;

  ngOnInit(): void {
    this.usuario = new Usuario();
    this.conta = new Conta();
  }

  cadastrarClick() {
    if (this.cadastroUsuario.form.valid) {
      let autocadastro: Autocadastro = new Autocadastro()
      autocadastro.email = this.usuario.email
      autocadastro.nome = this.usuario.nome
      autocadastro.cpf = this.usuario.cpf
      autocadastro.telefone = this.usuario.telefone
      autocadastro.endereco = new EnderecoCompleto()
      autocadastro.endereco!.bairro = this.usuario.bairro ?? ""
      autocadastro.endereco!.cep = this.usuario.cep!
      autocadastro.endereco!.cidade = this.usuario.cidade!
      autocadastro.endereco!.complemento = ""
      autocadastro.endereco!.logradouro = this.usuario.endereco!
      autocadastro.endereco!.estado = this.usuario.estado!
      autocadastro.endereco!.numero = 0

      autocadastro.salario = this.usuario.salario

      this.sagaAutocadastro.inserir(autocadastro).subscribe((response) => {
        console.log(response);

        // Toaste de aviso confirmação de cadastro
        this.toastr.success('Aguarde um e-mail com a confirmação do seu cadastro.', 'Solicitação efetuada com sucesso!', {
          timeOut: 5000,
        });

        this.fecharModal();
      });
    }
  }

  // abrirConta() {
  //   this.conta.usuario = this.usuario;
  //   this.conta.numeroConta = new Date().getTime();
  //   this.conta.id = new Date().getTime();
  //   this.contaService.inserir(this.conta).subscribe((response) => {
  //     this.gerenteService.inserirContas(response).subscribe((response) => { });
  //   });
  // }

  fecharModal() {
    this.activeModal.close();
  }
}
