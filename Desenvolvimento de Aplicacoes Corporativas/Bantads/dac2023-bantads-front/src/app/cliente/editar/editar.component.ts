import { ClienteService } from '../services/cliente.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { Resumo } from 'src/app/shared/models/resumo.model';
import { ExtratoService } from '../services/extrato.service';
import { Usuario } from 'src/app/shared/models/usuario.model';
import { Autocadastro } from 'src/app/shared/models/autocadastro.model';
import { EnderecoCompleto } from 'src/app/shared/models/endereco-completo.model';
import { SagaAutocadastroService } from 'src/app/login/services/saga-autocadastro.service';
import { auto } from '@popperjs/core';

@Component({
  selector: 'app-editar',
  templateUrl: './editar.component.html',
  styleUrls: ['./editar.component.css'],
})
export class EditarComponent implements OnInit {
  // instancia grupo de campos do formulario
  depositoForm!: FormGroup;
  usuario!: Usuario;

  hide = true;
  userId!: number;
  completeResponse: any;
  // injeta FormBuilder e toastr no construtor
  constructor(
    private formBuilder: FormBuilder,
    private toastr: ToastrService,
    private router: Router,
    private clienteService: ClienteService,
    private extratoService: ExtratoService,
    private sagaAutocadastro: SagaAutocadastroService
  ) {}

  ngOnInit(): void {
    this.usuario = new Usuario();
    this.userId = JSON.parse(localStorage.getItem('usuarioLogado')!).id;

    this.getUserInfo();
  }

  getUserInfo() {
    this.clienteService.getUserData(this.userId).subscribe(
      (response) => {
        this.completeResponse = response;
        this.usuario = new Usuario(
          response['data']['id'],
          response['data']['email'],
          '',
          response['data']['nome'],
          response['data']['ultimoNome'],
          response['data']['cpf'],
          response['data']['telefone'],
          response['data']['telefone'],
          response['data']['cidade'],
          response['data']['estado'],
          response['data']['pais'],
          response['data']['bairro'],
          response['data']['logradouro'],
          response['data']['salario'],
          response['data']['cep'],
          'user',
          response['estaAtivo'],
          response['estaAtivo']
        );
        this.usuario.bairro = response['data']['endereco']['bairro'] ?? '';
        this.usuario.cep = response['data']['endereco']['cep'] ?? '';
        this.usuario.cidade = response['data']['endereco']['cidade'] ?? '';
        this.usuario.endereco =
          response['data']['endereco']['logradouro'] ?? '';
        this.usuario.estado = response['data']['endereco']['estado'] ?? '';
      },
      (error) => console.log(error)
    );
  }

  editarDados() {
    this.completeResponse.usuario = this.usuario;

    let autocadastro: Autocadastro = new Autocadastro();
    autocadastro.email = this.usuario.email;
    autocadastro.nome = this.usuario.nome;
    autocadastro.cpf = this.usuario.cpf;
    autocadastro.telefone = this.usuario.telefone;
    autocadastro.endereco = new EnderecoCompleto();
    autocadastro.endereco!.bairro = this.usuario.bairro ?? '';
    autocadastro.endereco!.cep = this.usuario.cep!;
    autocadastro.endereco!.cidade = this.usuario.cidade!;
    autocadastro.endereco!.complemento = '';
    autocadastro.endereco!.logradouro = this.usuario.endereco!;
    autocadastro.endereco!.estado = this.usuario.estado!;
    autocadastro.endereco!.numero = 0;
    autocadastro.salario = this.usuario.salario;
    autocadastro.id = this.userId;

    this.sagaAutocadastro.editar(autocadastro).subscribe((response) => {
      console.log(response);
        history.back();
      },
      (error) => console.log(error)
    );
  }

  abrirConta() {
    // this.accountService.abrirConta(this.conta, this.usuario);
  }
}
