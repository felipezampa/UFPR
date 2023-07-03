import { Component, OnInit } from '@angular/core';
import axios from 'axios';
import { ToastrService } from 'ngx-toastr';
import { ClienteService } from 'src/app/cliente/services/cliente.service';
import { AuthService } from 'src/app/login/services/auth.service';
import { UsuarioAdmin } from 'src/app/shared/models/admin-response.model';
import { Conta } from 'src/app/shared/models/conta.model';
import { Usuario } from 'src/app/shared/models/usuario.model';

@Component({
  selector: 'app-consulta',
  templateUrl: './consulta.component.html',
  styleUrls: ['./consulta.component.css'],
})
export class ConsultaComponent implements OnInit {
  hidden: boolean = true;
  conta!: UsuarioAdmin;
  pesquisaUsuario!: Usuario | undefined;
  mostrarItens = false;

  constructor(
    private authService: AuthService,
    private clienteService: ClienteService,
    public toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.pesquisaUsuario = new Usuario();
  }

  consultarCpf(cpf: string) {
    this.hidden = false;
    this.clienteService.getClienteByCpf(cpf).subscribe({
      next: async (data: UsuarioAdmin | null) => {
        if (data == null) {
          this.toastr.error('Não foi encontrado nenhum cliente com esse CPF.');
        } else {
          this.conta = data;
          let
          link = 'http://localhost:8083/contas/cliente/' + this.conta.id;
          await axios({
            method: 'get',
            url: link,
          })
            .then((response) => {
               this.conta["conta"] = response.data;

              console.log(response.data);
            })
            .catch((err) => {
               this.conta["conta"] = null;
            });
          console.log(this.conta);
        }
      },
      error: (err) => {
        this.toastr.error('Não foi encontrado nenhum cliente com esse CPF.');
      },
    });
    if (this.pesquisaUsuario?.nome) this.pesquisaUsuario.nome = '';
  }

  consultarNome(nome: string) {
    this.hidden = false;
    // this.authService.listarTodosCli().subscribe((usuarioR) => {
    //   usuarioR.map((p) => {
    //     if (p.usuario.nome?.toLowerCase().includes(nome.toLowerCase()))
    //       this.conta.push(p);
    //   });
    // });
    // this.conta = this.conta.sort((a, b) => {
    //   if (
    //     a.usuario.nome &&
    //     b.usuario.nome &&
    //     a.usuario?.nome.toLowerCase() < b.usuario?.nome.toLowerCase()
    //   )
    //     return -1;
    //   if (
    //     a.usuario.nome &&
    //     b.usuario.nome &&
    //     a.usuario?.nome.toLowerCase() > b.usuario?.nome.toLowerCase()
    //   )
    //     return 1;
    //   return 0;
    // });
    if (this.pesquisaUsuario?.cpf) this.pesquisaUsuario.cpf = '';
  }

  buscarPorCPF(cpf: string): Conta | undefined {
    console.log(cpf);
    let usuarios: Conta[] = [];
    let usuario: Conta | undefined;
    this.authService.listarTodosCli().subscribe((response) => {
      usuarios = response;
      usuario = usuarios.find((p) => p.usuario.cpf === cpf);
    });
    return (usuario = usuarios.find((p) => p.usuario.cpf === cpf));
  }
}
