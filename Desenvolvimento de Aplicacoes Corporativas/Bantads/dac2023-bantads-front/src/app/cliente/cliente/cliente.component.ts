import { Component, OnInit } from '@angular/core';
import { ClienteService } from '../services/cliente.service';
import { Resumo } from 'src/app/shared/models/resumo.model';
import { LoginService } from 'src/app/login/services/login.service';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.css'],
})
export class ClienteComponent implements OnInit {
  resumo!: Resumo;

  constructor(private clienteService: ClienteService, private loginService : LoginService) {}

  ngOnInit(): void {
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

  getColor() {
    if (this.resumo.saldo != null && this.resumo.saldo >= 0) {
      return 'green-background';
    }
    return 'red-background';
  }
}
