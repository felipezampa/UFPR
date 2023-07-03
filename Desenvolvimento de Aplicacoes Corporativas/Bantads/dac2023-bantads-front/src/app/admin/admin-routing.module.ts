import { AuthGuard } from '../login/auth.guard';
import { HomeComponent } from './home/home.component';
import { ListarClienteComponent } from './listar-cliente/listar-cliente.component';
import { ListarGerenteComponent } from './listar-gerente/listar-gerente.component';
import { Routes } from '@angular/router';

export const AdminRouting: Routes = [
  {
    path: 'admin',
    component: HomeComponent,
    canActivate: [AuthGuard],
    data: { role: 'admin' },
  },
  {
    path: 'gerentes',
    redirectTo: 'gerentes/listar',
    data: { role: 'admin' },
  },
  {
    path: 'gerentes/listar',
    component: ListarGerenteComponent,
    canActivate: [AuthGuard],
    data: { role: 'admin' },
  },
  {
    path: 'admin/listar-cliente',
    component: ListarClienteComponent,
    canActivate: [AuthGuard],
    data: { role: 'admin' },
  },
];
