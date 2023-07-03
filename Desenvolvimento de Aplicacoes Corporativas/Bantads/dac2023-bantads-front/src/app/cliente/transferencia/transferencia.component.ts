import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Transacao } from 'src/app/shared/models/transacao.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Route, Router } from '@angular/router';
import { ClienteService } from './../services/cliente.service';
import { Resumo } from 'src/app/shared/models/resumo.model';

import { ExtratoDiario } from 'src/app/shared/models/extrato-diario.model';
import { ExtratoBancario } from 'src/app/shared/models/extrato-bancario.model';
import { ExtratoService } from '../services/extrato.service';
import { LoginService } from 'src/app/login/services/login.service';
import { Movimentacao } from 'src/app/shared/models/movimentacao.model';
import { ContaService } from 'src/app/conta/conta.service';
@Component({
  selector: 'app-transferencia',
  templateUrl: './transferencia.component.html',
  styleUrls: ['./transferencia.component.css'],
})
export class TransferenciaComponent implements OnInit {
  // instancia grupo de campos do formulario
  transferenciaForm!: FormGroup;

  resumo!: Resumo;
  @ViewChild('formTransfer') formTransfer!: NgForm;

  // injeta FormBuilder e toastr no construtor
  constructor(
    private formBuilder: FormBuilder,
    private toastr: ToastrService,
    private router: Router,
    private clienteService: ClienteService,
    private extratoService: ExtratoService,
    private loginService: LoginService,
    private contaService: ContaService
  ) {}

  transacao!: Transacao;

  ngOnInit(): void {
    this.transacao = new Transacao();
    this.transferenciaForm = this.formBuilder.group({
      // numero da conta com valor inicial vazio e obrigatorio
      valor: ['', Validators.required],
    });
    this.getUserInfo();
  }

  async getUserInfo() {
    this.clienteService
      .getContaPorIdCliente(this.loginService.usuarioLogado.id!)
      .subscribe(
        (response) => {
          this.resumo = new Resumo(
            response['data'].idCliente,
            response['data'].idGerente,
            response['data'].numero,
            response['data'].saldo,
            response['data'].dataCriacao,
            response['data'].limite
          );
          console.log(this.resumo);
        },
        (error) => console.log(error)
      );
  }

  transferir(valor: string, contaDestino: string): void {
    // replace remove o prefixo do numero
    let amountValue = Number(valor.replace('R$ ', ''));
    console.log(this.transferenciaForm);

    if (!this.transferenciaForm.valid || !contaDestino) {
      this.toastr.error('Verifique os dados informados e tente novamente');
      return;
    }
    if (this.resumo.saldo + this.resumo.limite < amountValue) {
      this.toastr.error(
        'Não é possível efetuar a transferência, valor excede o limite!!!'
      );
      return;
    }
    let movimentacao: Movimentacao = new Movimentacao(
      3,
      amountValue,
      this.resumo.numero,
      Number(contaDestino)
    );
    this.contaService.insereMovimentacao(movimentacao).subscribe(
      (response) => {
        // toast de aviso
        this.toastr.success('Transferência efetuada com sucesso!');
        // redireciona novamente para tela cliente após enviar transferência
        this.router.navigate(['/cliente']);
      },
      (error) => {this.toastr.error(error);}
    );
  }
}
