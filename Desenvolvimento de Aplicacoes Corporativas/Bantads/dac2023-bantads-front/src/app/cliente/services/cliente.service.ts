import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Resumo } from 'src/app/shared/models/resumo.model';
import { LoginService } from 'src/app/login/services/login.service';
import axios from 'axios';
import { UsuarioAdmin } from 'src/app/shared/models/admin-response.model';

@Injectable({
  providedIn: 'root',
})
export class ClienteService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };
  constructor(
    private httpClient: HttpClient,
    private loginService: LoginService
  ) {}

  getUserData(userId: string | number) {
    return this.httpClient.get<Record<string, any>>('/api/clientes/' + userId);
  }

  editarUsuario(usuario: any, userId: string | number): Observable<any> {
    return this.httpClient.put<any>('/api/clientes/' + userId, usuario);
  }

  getAll() {
    return this.httpClient.get<Record<string, any>>('/api/resumo');
  }
  getAllClientes(): Observable<UsuarioAdmin[]> {
    return new Observable((observer) => {
      axios({
        method: 'GET',
        url: '/api/clientes',
        headers: { 'x-access-token': this.loginService.usuarioLogado.token },
      }).then((response) => {
        let objBody = response.data.data;

        console.log(objBody);
        observer.next(objBody);
        observer.complete();
      });
    });
  }

  getClienteByCpf(cpf: string): Observable<UsuarioAdmin| null> {
    return new Observable((observer) => {
      axios({
        method: 'GET',
        url: '/api/clientes/by-cpf/' + cpf,
        headers: { 'x-access-token': this.loginService.usuarioLogado.token },
      })
        .then((response) => {
          let objBody = response.data.data;

          console.log(objBody);
          observer.next(objBody);
          observer.complete();
        })
        .catch((err) => {
          observer.next(null);
          observer.complete();
        });
    });
  }

  getContaPorIdCliente(idUser: string) {
    return this.httpClient.get<Record<string, any>>(
      '/api/contas/cliente/' + idUser
    );
  }

  // Resumo do saldo e limite da Conta
  depositar(resumo: Resumo): Observable<Resumo> {
    return this.httpClient.put<Resumo>('/api/resumo', resumo);
  }

  sacar(resumo: Resumo): Observable<Resumo> {
    return this.httpClient.put<Resumo>('/api/resumo', resumo);
  }

  transferir(resumo: Resumo): Observable<Resumo> {
    return this.httpClient.put<Resumo>('/api/resumo', resumo);
  }
}
