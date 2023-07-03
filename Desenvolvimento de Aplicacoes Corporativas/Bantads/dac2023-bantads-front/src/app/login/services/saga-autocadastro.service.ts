import { Injectable } from '@angular/core';
import { Usuario } from '../../shared/models/usuario.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const LS_CHAVE: string = 'usuarios';

import { Conta } from '../../shared/models/conta.model';
import axios from 'axios';
import { Autocadastro } from 'src/app/shared/models/autocadastro.model';

@Injectable({
  providedIn: 'root',
})
export class SagaAutocadastroService {
  constructor(private httpClient: HttpClient) {}

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  inserir(usuario: Autocadastro): Observable<Autocadastro> {
    return new Observable((observer) => {
      axios({
        method: 'post',
        url: '/api/customers',
        data: usuario,
        //headers: {"Authorization": this.usuarioLogado.token}
      }).then((response) => {
        observer.next(new Autocadastro());
        observer.complete();
      });
    });
  }
  editar(usuario: Autocadastro): Observable<Autocadastro> {
    return new Observable((observer) => {
      axios({
        method: 'put',
        url: '/api/customers',
        data: usuario,
        //headers: {"Authorization": this.usuarioLogado.token}
      }).then((response) => {
        observer.next(new Autocadastro());
        observer.complete();
      });
    });
  }
}
