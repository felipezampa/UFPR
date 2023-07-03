import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Login } from 'src/app/shared/models/login.model';
import { UsuarioLogado } from 'src/app/shared/models/usuario-logado.model';
import axios from 'axios';

const LS_CHAVE: string = 'usuarioLogado';
const BASE_URL: string = '/api/login'
@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(private httpClient: HttpClient) {}

  public get usuarioLogado(): UsuarioLogado {
    let usuario = localStorage[LS_CHAVE];
    return usuario ? JSON.parse(usuario) : null;
  }

  public set usuarioLogado(usuario: UsuarioLogado) {
    localStorage[LS_CHAVE] = JSON.stringify(usuario);
  }

  login(login: Login): Observable<UsuarioLogado | null> {
    let usuarioLogado = null;
    return new Observable((observer) => {
      axios({
        method: 'post',
        url: BASE_URL,
        data: login
        //headers: {"Authorization": this.usuarioLogado.token}
      }).then((response) => {
        
        if (response.data) {
          let usuarioData: UsuarioLogado = new UsuarioLogado();

          usuarioData.id = response.data.data.id;
          usuarioData.nome = response.data.data.nome;
          usuarioData.email = response.data.data.email;
          usuarioData.perfil = response.data.data.perfil;
          usuarioData.token = response.data.token;

          observer.next(usuarioData);
          this.usuarioLogado = usuarioData;
          observer.complete();
        }
        observer.next(null);
        observer.complete();
      });
    });
  }
 

  logout() {
    delete localStorage[LS_CHAVE];
  }
}
