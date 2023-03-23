import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CalculadoraComponent  } from './calculadora';
import { CalculadoraService } from './service';



@NgModule({
  declarations: [
    CalculadoraComponent
  ],
  imports: [
    CommonModule
  ],
  exports:[
    CalculadoraComponent
  ],
  providers:[
    CalculadoraService
  ]
})
export class CalculadoraModule { }