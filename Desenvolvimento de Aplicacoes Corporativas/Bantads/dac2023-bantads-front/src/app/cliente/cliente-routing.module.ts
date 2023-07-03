import { ClienteComponent } from './cliente/cliente.component';
import { DepositoComponent } from './deposito/deposito.component';
import { SaqueComponent } from './saque/saque.component';
import { AuthGuard } from '../login/auth.guard';
import { TransferenciaComponent } from './transferencia/transferencia.component';
import { ExtratoComponent } from './extrato/extrato.component';
import { Routes } from '@angular/router';
import { EditarComponent } from './editar/editar.component';
import { SenhaComponent } from './senha/senha.component';

export const ClienteRouting: Routes = [
  {
    path: 'cliente',
    component: ClienteComponent,
    canActivate: [AuthGuard],
    data: { role: 'user' },
  },
  {
    path: 'cliente/deposito',
    component: DepositoComponent,
    canActivate: [AuthGuard],
    data: { role: 'user' },
  },
  {
    path: 'cliente/saque',
    component: SaqueComponent,
    canActivate: [AuthGuard],
    data: { role: 'user' },
  },
  {
    path: 'cliente/editar',
    component: EditarComponent,
    canActivate: [AuthGuard],
    data: { role: 'user' },
  },
  {
    path: 'cliente/senha',
    component: SenhaComponent,
    canActivate: [AuthGuard],
    data: { role: 'user' },
  },
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },

  {
    path: 'cliente/transferencia',
    component: TransferenciaComponent,
    canActivate: [AuthGuard],
    data: { role: 'user' },
  },
  {
    path: 'cliente/extrato',
    component: ExtratoComponent,
    canActivate: [AuthGuard],
    data: { role: 'user' },
  },
];
