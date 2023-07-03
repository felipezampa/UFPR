import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from 'src/material.module';

import { NgxMaskModule } from 'ngx-mask';
import { CpfCnpjPipeModule } from '../shared/pipes/cpfCnpj.module';
import { ConsultaComponent } from './consulta/consulta.component';
import { ListarClientesComponent } from './listar-clientes/listar-clientes.component';
import { MelhoresClientesComponent } from './melhores-clientes/melhores-clientes.component';
import { ModalClienteComponent } from './modal-cliente/modal-cliente.component';
import { ResumoComponent } from './resumo/resumo.component';
import { GerenteService } from './services/gerente.service';

@NgModule({
  declarations: [
    ResumoComponent,
    ConsultaComponent,
    ListarClientesComponent,
    MelhoresClientesComponent,
    ModalClienteComponent,
  ],
  imports: [
    CommonModule,
    BrowserAnimationsModule,
    FormsModule,
    MaterialModule,
    RouterModule,
    NgxMaskModule.forRoot(),
    CpfCnpjPipeModule
  ],
  providers:[
    GerenteService
  ]
})
export class GerenteModule { }

