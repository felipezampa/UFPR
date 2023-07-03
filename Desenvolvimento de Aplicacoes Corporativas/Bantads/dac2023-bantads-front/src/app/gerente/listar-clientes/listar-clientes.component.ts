import { Gerente } from './../../shared/models/gerente.model';
import { Component, OnInit } from '@angular/core';
import { Conta } from 'src/app/shared/models/conta.model';
import { ContaService } from 'src/app/conta/conta.service';
import { GerenteService } from '../services/gerente.service';
import { Usuario } from 'src/app/shared/models/usuario.model';
import { AuthService } from 'src/app/login/services/auth.service';
import { ModalClienteComponent } from '../modal-cliente/modal-cliente.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Pessoa } from 'src/app/shared/models/gerente-response.model';
import axios from 'axios';
import { LoginService } from 'src/app/login/services/login.service';

@Component({
  selector: 'app-listar-clientes',
  templateUrl: './listar-clientes.component.html',
  styleUrls: ['./listar-clientes.component.css'],
})
export class ListarClientesComponent implements OnInit {
  contas!: Pessoa[];
  filteredAccounts!: Pessoa[];

  gerente!: Gerente;
  displayedColumns: string[] = ['nome', 'cpf', 'cidade', 'estado', 'salario'];
  pesquisaUsuario!: Usuario | undefined;
  hidden: boolean = true;

  constructor(
    private gerenteService: GerenteService,
    private authService: AuthService,
    private modalService: NgbModal,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    this.gerenteService.listarClientes().subscribe({
      next: (data: Pessoa[]) => {
        if (data == null) {
          this.contas = [];
        } else {
          this.contas = data;
        }
        console.log(this.contas);

        if (this.contas.length > 0) {
          this.contas.forEach(async (element, index) => {
            let link = 'http://localhost:8083/contas/cliente/' + element.saga;
            await axios({
              method: 'get',
              url: link,
            })
              .then((response) => {
                console.log(this.contas[index]);
                this.contas[index]['conta'] = response.data;
                //ordenar contas em ordem alfabetica de nome
              })
              .catch((err) => {
                console.log(err);
              });
          });
          this.contas = this.contas
            .sort((a, b) => {
              if (
                a.nome &&
                b.nome &&
                a?.nome.toLowerCase() < b?.nome.toLowerCase()
              )
                return -1;
              if (
                a.nome &&
                b.nome &&
                a?.nome.toLowerCase() > b?.nome.toLowerCase()
              )
                return 1;
              return 0;
            })
            .filter((pessoa: Pessoa) => {
              return pessoa.aprovado == true;
            })
            .filter((conta) => {
              return (
                !conta.conta ||
                conta.gerente?.id == this.loginService.usuarioLogado.id
              );
            });
          this.filteredAccounts = this.contas;
        }
        console.log(this.contas);
      },
    });

    this.pesquisaUsuario = new Usuario();
  }

  ordenaContas(): Pessoa[] {
    if (this.contas) {
      return this.contas.sort((a, b) => {
        if (a.nome && b.nome && a?.nome.toLowerCase() < b?.nome.toLowerCase())
          return -1;
        if (a.nome && b.nome && a?.nome.toLowerCase() > b?.nome.toLowerCase())
          return 1;
        return 0;
      });
    } else {
      return [];
    }
  }

  consultarNome(nome: string) {
    this.filteredAccounts = this.contas;
    if (nome) {
      this.filteredAccounts = this.contas.filter((conta) => {
        return conta.nome?.toLowerCase().includes(nome.toLowerCase());
      });
    }
  }

  //   this.gerenteService.listarClientes().subscribe({
  //     next: (data: Pessoa[]) => {
  //       if (data == null) {
  //         this.contas = [];
  //       } else {
  //         this.contas.forEach(async (element, index) => {
  //           let link = 'http://localhost:8083/contas/cliente/' + element.saga;
  //           await axios({
  //             method: 'get',
  //             url: link,
  //           })
  //             .then((response) => {
  //               this.contas[index]['conta'] = response.data;
  //               //ordenar contas em ordem alfabetica de nome
  //             })
  //             .catch((err) => {
  //               console.log(err);
  //             });
  //         });
  //         this.contas = this.contas
  //           .sort((a, b) => {
  //             if (
  //               a.nome &&
  //               b.nome &&
  //               a?.nome.toLowerCase() < b?.nome.toLowerCase()
  //             )
  //               return -1;
  //             if (
  //               a.nome &&
  //               b.nome &&
  //               a?.nome.toLowerCase() > b?.nome.toLowerCase()
  //             )
  //               return 1;
  //             return 0;
  //           })
  //           .filter((pessoa: Pessoa) => {
  //             return pessoa.aprovado == true;
  //           })
  //           .filter((conta) => {
  //             return (
  //               !conta.conta ||
  //               conta.gerente?.id == this.loginService.usuarioLogado.id
  //             );
  //           })
  //           .filter((conta) => {
  //             return conta.nome?.toLowerCase().includes(nome.toLowerCase());
  //           });
  //       }
  //     },
  //   });
  // }
  consultarCpf(cpf: string) {
    this.filteredAccounts = this.contas;
    if (cpf) {
      this.filteredAccounts = this.contas.filter((conta) => {
        return conta.cpf?.toLowerCase().includes(cpf.toLowerCase());
      });
    }
  }
  //   this.gerenteService.listarClientes().subscribe({
  //     next: (data: Pessoa[]) => {
  //       if (data == null) {
  //         this.contas = [];
  //       } else {

  //         this.contas.forEach(async (element, index) => {
  //           let link = 'http://localhost:8083/contas/cliente/' + element.saga;
  //           await axios({
  //             method: 'get',
  //             url: link,
  //           })
  //             .then((response) => {
  //               console.log(this.contas[index]);
  //               this.contas[index]['conta'] = response.data;
  //               //ordenar contas em ordem alfabetica de nome
  //             })
  //             .catch((err) => {
  //               console.log(err);
  //             });
  //         });
  //         this.contas = this.contas
  //           .sort((a, b) => {
  //             if (
  //               a.nome &&
  //               b.nome &&
  //               a?.nome.toLowerCase() < b?.nome.toLowerCase()
  //             )
  //               return -1;
  //             if (
  //               a.nome &&
  //               b.nome &&
  //               a?.nome.toLowerCase() > b?.nome.toLowerCase()
  //             )
  //               return 1;
  //             return 0;
  //           })
  //           .filter((pessoa: Pessoa) => {
  //             return pessoa.aprovado == true;
  //           })
  //           .filter((conta) => {
  //             return conta.cpf?.toLowerCase().includes(cpf.toLowerCase());
  //           });
  //       }
  //     },
  //   });
  // }

  verCliente(itemConta: Conta) {
    // Lógica para navegar para a página de detalhes do cliente
  }

  async verModal(cliente: Pessoa) {
    let link = '/api/contas/cliente/' + cliente.saga;
    const modalRef = this.modalService.open(ModalClienteComponent, {
      size: 'xl',
    });

    // Abre o Modal

    // Adiciona o gerente a ser editado como argumento ao abrir o modal
    modalRef.componentInstance.itemConta = cliente;
  }
}
