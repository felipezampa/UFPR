import { Component, OnInit } from '@angular/core';
import { CalculadoraService } from '../service';

@Component({
  selector: 'app-calculadora',
  templateUrl: './calculadora.component.html',
  styleUrls: ['./calculadora.component.css']
})
export class CalculadoraComponent implements OnInit {

  private res : number = 0;
  private num1: number = 0;
  private num2: number = 0;

  constructor(private calculadoraService : CalculadoraService) { }

  ngOnInit(): void {
  }

  somar(n1:string,n2:string) : void {
    this.num1 = parseFloat(n1);
    this.num2 = parseFloat(n2);

    this.res = this.calculadoraService.somar(this.num1,this.num2);
  }

  subtracao(n1:string,n2:string) : void {
    this.num1 = parseFloat(n1);
    this.num2 = parseFloat(n2);

    this.res = this.calculadoraService.subtracao(this.num1,this.num2);
  }

  multiplicacao(n1:string,n2:string) : void {
    this.num1 = parseFloat(n1);
    this.num2 = parseFloat(n2);

    this.res = this.calculadoraService.multiplicacao(this.num1,this.num2);
  }

  divisao(n1:string,n2:string) : void {
    this.num1 = parseFloat(n1);
    this.num2 = parseFloat(n2);

    this.res = this.calculadoraService.divisao(this.num1,this.num2);
  }

  get resultado() : string{
    return this.res.toString();
  }
}