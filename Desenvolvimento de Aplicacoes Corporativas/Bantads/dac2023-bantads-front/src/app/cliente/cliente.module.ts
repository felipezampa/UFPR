import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClienteComponent } from './cliente/cliente.component';
import { ClienteService } from './services/cliente.service';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from 'src/material.module';
import { DepositoComponent } from './deposito/deposito.component';
import { NgxCurrencyModule } from 'ngx-currency';
import { NgxMaskModule } from 'ngx-mask';
import { SaqueComponent } from './saque/saque.component';
import { TransferenciaComponent } from './transferencia/transferencia.component';
import { ExtratoComponent } from './extrato/extrato.component';
import { EditarComponent } from './editar/editar.component';
import { SenhaComponent } from './senha/senha.component';
import { MAT_DATE_LOCALE } from '@angular/material/core';

@NgModule({
  declarations: [
    ClienteComponent,
    DepositoComponent,
    SaqueComponent,
    TransferenciaComponent,
    ExtratoComponent,
    EditarComponent,
    SenhaComponent,
  ],
  imports: [
    CommonModule,
    BrowserAnimationsModule,
    FormsModule,
    MaterialModule,
    RouterModule,
    ReactiveFormsModule,
    NgxCurrencyModule,
    NgxMaskModule,
  ],
  exports: [ClienteComponent],
  providers: [ClienteService, { provide: MAT_DATE_LOCALE, useValue: 'pt-BR' }],
})
export class ClienteModule {}
