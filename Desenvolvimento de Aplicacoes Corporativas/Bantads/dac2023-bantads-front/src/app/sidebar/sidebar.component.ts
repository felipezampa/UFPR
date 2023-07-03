import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login/services/login.service';
import { UsuarioLogado } from '../shared/models/usuario-logado.model';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {
  usuarioLogado: UsuarioLogado | null;

  constructor(private loginService: LoginService, private router: Router) {
    this.usuarioLogado = this.loginService.usuarioLogado;
  }

  logout() {
    this.usuarioLogado = null;
    this.loginService.logout();
    this.router.navigate(['/login']);
    // window.location.reload();
  }
}
