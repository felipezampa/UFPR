import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { IConfig, NgxMaskModule } from 'ngx-mask';
import { MaterialModule } from 'src/material.module';
import { EditarGerenteComponent, ExcluirGerenteComponent, HomeComponent, InserirGerenteComponent, ListarClienteComponent, ListarGerenteComponent, ModalGerenteComponent } from './index';

export const options: Partial<IConfig> | (() => Partial<IConfig>) = {};

@NgModule({
  declarations: [
    ListarGerenteComponent,
    EditarGerenteComponent,
    InserirGerenteComponent,
    HomeComponent,
    ListarClienteComponent,
    ModalGerenteComponent,
    ExcluirGerenteComponent
  ],
  imports: [
    CommonModule,
    BrowserAnimationsModule,
    RouterModule,
    FormsModule,
    MaterialModule,
    NgxMaskModule.forRoot()
  ],
  providers: []
})
export class GerenteAdminModule { }
