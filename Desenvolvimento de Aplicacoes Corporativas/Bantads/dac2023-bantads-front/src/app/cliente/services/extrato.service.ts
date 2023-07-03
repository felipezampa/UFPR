import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import axios from 'axios';
import { Observable } from 'rxjs';
import { ExtratoBancario } from 'src/app/shared/models/extrato-bancario.model';
import { UsuarioLogado } from 'src/app/shared/models/usuario-logado.model';
import ResponseExtrato from 'src/app/shared/models/response-extrato.model';

const LS_CHAVE: string = 'usuarioLogado';
const BASE_URL = 'http://localhost:3000/extrato';
const HEADERS = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  }),
};

@Injectable({
  providedIn: 'root',
})
export class ExtratoService {
  constructor(private httpClient: HttpClient) {}

  getAll(bankStatement: ExtratoBancario) {
    return this.httpClient.get<Record<string, any>>(BASE_URL);
  }

  setExtrato(bankStatement: ExtratoBancario): Observable<ExtratoBancario> {
    return this.httpClient.put<ExtratoBancario>(BASE_URL, bankStatement);
  }

  getBuscaExtrato(bankStatement: ExtratoBancario) {
    if (bankStatement.dataInicial == 0 || bankStatement.dataFinal == 0) {
      return this.httpClient.get<Record<string, any>>(BASE_URL); //aqui retorna tudo, caso dataIncio ou fim forem vazias
    }
    return this.httpClient.get<Record<string, any>>(''); // aqui retorna com base nas datas preenchidas
  }

  public get usuarioLogado(): UsuarioLogado {
    let usuario = localStorage[LS_CHAVE];
    return usuario ? JSON.parse(usuario) : null;
  }

  getExtratoPorData(idCliente: String, dataInicio: String, dataFim: String): Observable<ResponseExtrato>{
    return new Observable((observer) => {
      axios({
        method: 'post',
        url: 'api/movimentacoes/data',
        data: {idCliente, dataInicio, dataFim },
        headers: {"x-access-token": this.usuarioLogado.token}
      }).then((response) => {

        if (response.data) {
          console.log(response.data.data)
          observer.next(response.data.data);
          observer.complete();
        }
        // observer.next(response);
        observer.complete();
      });
    });

  }
}
