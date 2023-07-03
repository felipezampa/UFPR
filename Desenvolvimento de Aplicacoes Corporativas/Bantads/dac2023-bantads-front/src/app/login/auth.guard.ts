import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from './services/login.service';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    const url = state.url;
    const usuarioLogado = this.loginService.usuarioLogado;

    // if (!usuarioLogado) {
    //   this.router.navigate(['/login'], {
    //     queryParams: {
    //       error: 'Realize login antes de acessar ' + url + '.',
    //     },
    //   });

    //   return false;
    // }

    // if (
    //   route.data?.['role'] &&
    //   route.data?.['role'].indexOf(usuarioLogado.perfil) == -1
    // ) {
    //   this.router.navigate(['/login'], {
    //     queryParams: {
    //       error: 'Acesso à ' + url + ' negado.',
    //     },
    //   });
    //   this.toastr.error('Acesso à ' + url + ' negado. Verifique as permissões e tente novamente');

    //   return false;
    // }

    return true;
  }

  constructor(
    private loginService: LoginService,
    private toastr: ToastrService,
    private router: Router
  ) {}
}
