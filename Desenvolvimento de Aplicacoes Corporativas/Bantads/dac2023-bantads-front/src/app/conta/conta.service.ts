import { Conta } from '../shared/models/conta.model';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Movimentacao } from '../shared/models/movimentacao.model';

const LS_CHAVE: string = 'conta';

@Injectable({
  providedIn: 'root',
})
export class ContaService {
  constructor(private httpClient: HttpClient) {}

  BASE_URL = '/api/';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  listarTodos(): Observable<Conta[]> {
    return this.httpClient.get<Conta[]>(this.BASE_URL, this.httpOptions);
  }


  inserir(conta: Conta): Observable<Conta> {
    return this.httpClient.post<Conta>(
      this.BASE_URL,
      JSON.stringify(conta),
      this.httpOptions
    );
  }


  insereMovimentacao(movimentacao: Movimentacao): Observable<Movimentacao> {
    return this.httpClient.post<Movimentacao>(this.BASE_URL + 
      'movimentacoes', movimentacao);
  }


  remover(numeroConta: number | undefined): Observable<Conta> {
    return this.httpClient.delete<Conta>(
      this.BASE_URL + "/" + numeroConta,
      this.httpOptions
    );
  }

  alterar(conta: Conta): Observable<Conta> {
    return this.httpClient.put<Conta>(`${this.BASE_URL}/${conta.id}`, JSON.stringify(conta),this.httpOptions);
  }

}
