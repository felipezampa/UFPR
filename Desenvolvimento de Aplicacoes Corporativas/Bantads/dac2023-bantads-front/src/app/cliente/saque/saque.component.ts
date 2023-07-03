import { ClienteService } from './../services/cliente.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Route, Router } from '@angular/router';
import { Resumo } from 'src/app/shared/models/resumo.model';
import { Transacao } from 'src/app/shared/models/transacao.model';
import { ExtratoBancario } from 'src/app/shared/models/extrato-bancario.model';
import { ExtratoService } from '../services/extrato.service';
import { ExtratoDiario } from 'src/app/shared/models/extrato-diario.model';
import { LoginService } from 'src/app/login/services/login.service';
import { Movimentacao } from 'src/app/shared/models/movimentacao.model';
import { ContaService } from 'src/app/conta/conta.service';

@Component({
  selector: 'app-saque',
  templateUrl: './saque.component.html',
  styleUrls: ['./saque.component.css'],
})
export class SaqueComponent implements OnInit {
  // instancia grupo de campos do formulario
  saqueForm!: FormGroup;
  resumo!: Resumo;

  // injeta FormBuilder e toastr no construtor
  constructor(
    private formBuilder: FormBuilder,
    private toastr: ToastrService,
    private router: Router,
    private clienteService: ClienteService,
    private extratoService: ExtratoService,
    private loginService : LoginService,
    private contaService: ContaService
  ) {}

  ngOnInit(): void {
    this.saqueForm = this.formBuilder.group({
      valorSaque: ['', Validators.required],
    });
    this.getUserInfo();
  }

  async getUserInfo() {
    this.clienteService.getContaPorIdCliente(this.loginService.usuarioLogado.id!).subscribe(
      (response) => {
        this.resumo = new Resumo(response['data'].idCliente, response['data'].idGerente, response['data'].numero, response['data'].saldo, response['data'].dataCriacao, response['data'].limite);
        console.log(this.resumo);
      },
      (error) => console.log(error)
    );
  }

  
  sacar(value: string): void {
    // replace remove o prefixo do numero
    let amountValue = Number(value.replace('R$ ', ''));
        // saldo + limite deve ser maior que o valor a ser sacado
    if (
      this.saqueForm.valid &&
      this.resumo.saldo + this.resumo.limite >= amountValue
    ) {
      // saldo - saque
      let movimentacao: Movimentacao = new Movimentacao(
        2,
        amountValue,
        this.resumo.numero
      );
      this.contaService.insereMovimentacao(movimentacao).subscribe(
        (response) => {
          // toast de aviso
          this.toastr.success('Depósito efetuado com sucesso!');
          // redireciona novamente para tela cliente após enviar transferência
          this.router.navigate(['/cliente']);
        },
        (error) => console.log(error)
      );
      
    } else
      this.toastr.warning(
        'Não é possível efetuar o saque, valor excede o limite!!!'
      );
  }
}
