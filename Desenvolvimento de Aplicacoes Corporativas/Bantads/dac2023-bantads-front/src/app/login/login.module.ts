import { AutocadastroComponent } from './autocadastro/autocadastro.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from 'src/material.module';
import { NgxMaskModule, IConfig  } from 'ngx-mask';
import { NgxCurrencyModule } from 'ngx-currency';

@NgModule({
  declarations: [LoginComponent, AutocadastroComponent],
  imports: [
    CommonModule,
    BrowserAnimationsModule,
    FormsModule,
    MaterialModule,
    RouterModule,
    NgxMaskModule,
    NgxCurrencyModule
  ],
})
export class LoginModule {}
