import { Gerente } from './../../shared/models/gerente.model';
import { Conta } from '../../shared/models/conta.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ContaService } from 'src/app/conta/conta.service';
import { GerenteService } from '../services/gerente.service';
import { Pessoa } from 'src/app/shared/models/gerente-response.model';
import { LoginService } from 'src/app/login/services/login.service';

@Component({
  selector: 'app-resumo',
  templateUrl: './resumo.component.html',
  styleUrls: ['./resumo.component.css'],
})
export class ResumoComponent implements OnInit {
  contas!: Pessoa[];
  gerente!: Gerente;
  loading!: boolean;
  displayedColumns: string[] = ['name', 'cpf', 'salario', 'action'];

  constructor(
    private contaService: ContaService,
    private gerenteService: GerenteService,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    this.gerenteService.buscarPorId(1).subscribe((gerente) => {
      this.gerente = gerente;
    });
    this.listarTodos();
    this.loading = false;
  }

  listarTodos() {
    this.gerenteService.listarClientes().subscribe({
      next: (data: Pessoa[]) => {
        if (data == null) {
          this.contas = [];
        } else {
          this.contas = data.filter((pessoa:Pessoa)=>{
            return pessoa.gerente.id == this.loginService.usuarioLogado.id;
          }).filter((pessoa: Pessoa) => {
            return pessoa.aprovado != true;
          });
        }
      },
    });
  }

  aprovar($event: any, conta: Pessoa): void {
    const dataHora = new Date(); // cria um objeto Date com a data e hora atuais
    $event.preventDefault();
    if (confirm(`Deseja realmente aprovar a conta de ${conta.nome}?`)) {
      // conta.estaAtivo = true;
      // conta.status = 2;
      // conta.usuario.estaAprovado = true;
      // conta.dataResposta = new Date(Date.now());
      // this.contaService.alterar(conta).subscribe(() => {
      //   this.loading = false;
      //   this.listarTodos();
      // });
      this.gerenteService.aprovar(conta).subscribe(() => {
        this.loading = false;
        this.listarTodos();
      });
    }
  }

  recusar($event: any, conta: Pessoa): void {
    const dataHora = new Date(); // cria um objeto Date com a data e hora atuais
    $event.preventDefault();
    console.log(conta);
    const motivo = window.prompt(
      `Por favor, informe o motivo da reprovação da conta de ${conta.nome}:`
    );
    if (motivo !== null) {
    }
  }
}
