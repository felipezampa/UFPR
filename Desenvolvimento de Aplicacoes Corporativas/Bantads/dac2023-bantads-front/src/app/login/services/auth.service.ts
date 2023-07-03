import { Injectable } from '@angular/core';
import { Usuario } from '../../shared/models/usuario.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const LS_CHAVE: string = 'usuarios';
const LS_CHAVEA: string = 'conta';

import { Conta } from '../../shared/models/conta.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private httpClient: HttpClient) {}

  BASE_URL = 'http://localhost:3000/conta';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  listarTodos(): Usuario[] {
    const usuarios = localStorage.getItem(LS_CHAVE);
    if (usuarios) {
      return JSON.parse(usuarios);
    }
    return [];
  }

  inserir(usuario: Usuario):  Observable<Conta> {
    let acc = new Conta(Math.floor(Math.random() * (10000) + 1), usuario, Math.floor(Math.random() * (10000) + 1), new Date(), new Date(), usuario.salario, false, 0)
    return this.httpClient.post<Conta>(this.BASE_URL, acc);

  }

  buscarPorId(id: number): Usuario | undefined {
    const usuarios: Usuario[] = this.listarTodos();
    return usuarios.find((p) => p.id === id);
  }

  listarTodosCli(): Observable<Conta[]> {
    return this.httpClient.get<Conta[]>(this.BASE_URL, this.httpOptions);
  }

  buscarPorCPF(cpf: string): Conta | undefined {
    console.log(cpf);
    let usuarios: Conta[] = [];
    let usuario: Conta | undefined;
    this.listarTodosCli().subscribe((response) => {
      usuarios = response;
      usuario = usuarios.find((p) => p.usuario.cpf === cpf);
    });
    return (usuario = usuarios.find((p) => p.usuario.cpf === cpf));
  }

  atualizar(usuario: Usuario): void {
    const usuarios = this.listarTodos();
    usuarios.forEach((obj, index, objs) => {
      if (obj.id === usuario.id) {
        objs[index] = usuario;
      }
    });
    localStorage.setItem(LS_CHAVE, JSON.stringify(usuarios));
  }

  remover(id: number): void {
    let usuarios = this.listarTodos();
    usuarios = usuarios.filter((p) => p.id !== id);
    localStorage.setItem(LS_CHAVE, JSON.stringify(usuarios));
  }
}
