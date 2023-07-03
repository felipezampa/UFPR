import { Gerente } from './../../shared/models/gerente.model';
import { Component, OnInit } from '@angular/core';
import { Conta } from 'src/app/shared/models/conta.model';
import { GerenteService } from '../services/gerente.service';
import {
  ContaAdmin,
  UsuarioAdmin,
} from 'src/app/shared/models/admin-response.model';
import axios from 'axios';
import { ClienteService } from 'src/app/cliente/services/cliente.service';
import { LoginService } from 'src/app/login/services/login.service';

@Component({
  selector: 'app-melhores-clientes',
  templateUrl: './melhores-clientes.component.html',
  styleUrls: ['./melhores-clientes.component.css'],
})
export class MelhoresClientesComponent implements OnInit {
  contas!: UsuarioAdmin[];
  gerente!: Gerente;
  displayedColumns: string[] = ['nome', 'cpf', 'email', 'salario', 'limite'];

  constructor(
    private gerenteService: GerenteService,
    private clienteService: ClienteService,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    // Flag para mostrar que esta carregando os dados
    // Chama o metodo do service
    this.clienteService.getAllClientes().subscribe((response) => {
      console.log(response);
      this.contas = response;
      this.contas.forEach(async (element, index) => {
        let link = 'http://localhost:8083/contas/cliente/' + element.id;
        await axios({
          method: 'get',
          url: link,
        })
          .then((response) => {
            this.contas[index]['conta'] = response.data;
            this.contas.sort((a, b) => {
              if (a.conta == null) return 1;
              if (b.conta == null) return -1;
              return b.conta.saldo - a.conta.saldo;
            });
          })
          .catch(() => {
            this.contas[index]['conta'] = null;
          })
          .finally(() => {
            this.contas.sort((a, b) => {
              if (a.conta == null) return 1;
              if (b.conta == null) return -1;
              return b.conta.saldo - a.conta.saldo;
            });
          });
      });
      this.contas = this.contas
        .sort((a, b) => {
          if (a.conta == null) return 1;
          if (b.conta == null) return -1;
          return b.conta.saldo - a.conta.saldo;
        }).filter((conta)=>{
          return !conta.conta || conta.conta?.idGerente == this.loginService.usuarioLogado.id;
        })
        .filter((conta, index) => {
          if (index < 3) {
            return conta;
          }
        });
    });
    //filtrar apenas 3 primeiros clientes com o id de gerente igual ao logado
    this.contas = this.contas;
    //Ordena os clientes pelo saldo
  }
}
