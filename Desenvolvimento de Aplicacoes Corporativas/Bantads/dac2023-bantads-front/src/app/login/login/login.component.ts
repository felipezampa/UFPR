import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Usuario } from 'src/app/shared/models/usuario.model';
import { ToastrService } from 'ngx-toastr';

import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { LoginService } from '../services/login.service';
import { Login } from 'src/app/shared/models/login.model';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AutocadastroComponent } from '../autocadastro/autocadastro.component';

const LS_CHAVE: string = 'usuarioLogado';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  constructor(
    private authService: AuthService,
    private toastr: ToastrService,
    private router: Router,
    private loginService: LoginService,
    private modalService: NgbModal
  ) {}

  @ViewChild('userAuth') userAuth!: NgForm;

  usuario!: Usuario;
  hide = true;
  hasLoginError = false;

  ngOnInit(): void {
    this.usuario = new Usuario();
    // quando for p/ login, deleta o Local storage
    delete localStorage[LS_CHAVE];
  }

  login() {
    let login = new Login(this.usuario.email, this.usuario.senha);
    this.loginService.login(login).subscribe((usu) => {
      if (usu != null) {
        this.loginService.usuarioLogado = usu;
        if (usu.perfil == 'admin') {
          window.location.href = '/admin';
        } else if (usu.perfil == 'gerente') {
          window.location.href = '/gerente';
        } else {
          window.location.href = '/cliente';
        }
      } else {
        this.toastr.error('Usuário ou senha inválidos.');
      }
    });
  }

  inserir(): void {
    if (this.userAuth.form.valid) {
      this.authService.inserir(this.usuario);
      window.location.href = '/pessoas';
    }
  }

  verModal() {
    // Abre o Modal
    const modalRef = this.modalService.open(AutocadastroComponent, {
      size: 'lg',
    });
  }
}
