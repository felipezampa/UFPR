import { Gerente } from './../../shared/models/gerente.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import axios from 'axios';
import { Observable, Subject, tap } from 'rxjs';
import { Conta } from 'src/app/shared/models/conta.model';
import { Pessoa } from 'src/app/shared/models/gerente-response.model';
import { LoginService } from 'src/app/login/services/login.service';

const LS_CHAVE: string = 'gerente';

@Injectable({
  providedIn: 'root',
})
export class GerenteService {
  BASE_URL = 'http://localhost:3000/gerentes/';
  private _refreshPage$ = new Subject<void>();

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  constructor(
    private httpClient: HttpClient,
    private loginService: LoginService
  ) {}

  get refreshPage$() {
    // O metodo que permite a atualizacao automatica toda vez que acontecer alguma alteracao
    return this._refreshPage$;
  }

  listarClientes(): Observable<Pessoa[]> {
    return new Observable((observer) => {
      axios({
        method: 'get',
        url: '/api/clientes-gerente',
        headers: { 'x-access-token': this.loginService.usuarioLogado.token },
      }).then((response) => {
        let objBody = response.data.data;

        console.log(objBody);
        observer.next(objBody);
        observer.complete();
      });
    });
  }

  listarClientesAprovados(): Observable<Pessoa[]> {
    return new Observable((observer) => {
      axios({
        method: 'get',
        url: '/api/clientes-gerente?aprovado=true',
        headers: { 'x-access-token': this.loginService.usuarioLogado.token },
      }).then((response) => {
        let objBody = response.data.data;

        console.log(objBody);
        observer.next(objBody);
        observer.complete();
      });
    });
  }

  listarGerentes(): Observable<Gerente[]> {
    return new Observable((observer) => {
      axios({
        method: 'get',
        url: '/api/gerentes',
        headers: { 'x-access-token': this.loginService.usuarioLogado.token },
      }).then((response) => {
        let objBody = response.data.data;

        console.log(objBody);
        observer.next(objBody);
        observer.complete();
      });
    });
  }

  aprovar(pessoa: Pessoa): Observable<Pessoa> {
    return new Observable((observer) => {
      axios({
        method: 'put',
        url: '/api/clientes-gerente/' + pessoa.id,
        headers: { 'x-access-token': this.loginService.usuarioLogado.token },
      }).then((response) => {
        let objBody = response.data.data;

        console.log(objBody);
        observer.next(objBody);
        observer.complete();
      });
    });
  }

  listarTodos(): Observable<Gerente[]> {
    return this.httpClient.get<Gerente[]>(this.BASE_URL, this.httpOptions);
  }

  buscarPorId(id: number): Observable<Gerente> {
    return this.httpClient.get<Gerente>(this.BASE_URL + id, this.httpOptions);
  }

  buscarContas(id: number): Observable<Gerente[]> {
    return this.httpClient.get<Conta[]>(
      this.BASE_URL + id + '/conta',
      this.httpOptions
    );
  }

  inserirContas(cc: Conta): Observable<Conta> {
    return this.httpClient.put<Conta>(this.BASE_URL + '1' + '/conta', cc);
  }

  inserir(gerente: Gerente): Observable<Gerente> {
    return new Observable((observer) => {
      axios({
        method: 'post',
        url: '/api/gerentes',
        data: gerente,
        headers: { 'x-access-token': this.loginService.usuarioLogado.token },
      }).then((response) => {
        let objBody = response.data.data;

        console.log(objBody);
        observer.next(objBody);
        observer.complete();
      });
    });
  }

  remover(numeroGerente: number | undefined): Observable<Gerente> {
    return new Observable((observer) => {
      axios({
        method: 'delete',
        url: '/api/gerentes/' + numeroGerente,
        headers: { 'x-access-token': this.loginService.usuarioLogado.token },
      }).then((response) => {
        let objBody = response.data.data;

        console.log(objBody);
        observer.next(objBody);
        observer.complete();
      });
    });
  }

  alterar(gerente: Gerente): Observable<Gerente> {
    return new Observable((observer) => {
      axios({
        method: 'put',
        url: '/api/gerentes/' + gerente.id,
        data: gerente,
        headers: { 'x-access-token': this.loginService.usuarioLogado.token },
      }).then((response) => {
        let objBody = response.data.data;

        console.log(objBody);
        observer.next(objBody);
        observer.complete();
      });
    });
  }
}
