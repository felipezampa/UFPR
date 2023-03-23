import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class CalculadoraService {

  constructor() { }

  somar(n1: number, n2: number): number {
    let resultado: number = (n1 + n2);
    return resultado;
  }

  subtracao(n1: number, n2: number): number {
    let resultado: number = (n1 - n2);
    return resultado;
  }

  multiplicacao(n1: number, n2: number): number {
    let resultado: number = (n1 * n2);
    return resultado;
  }

  divisao(n1: number, n2: number): number {
    let resultado: number = (n1 / n2);
    return resultado;
  }
}