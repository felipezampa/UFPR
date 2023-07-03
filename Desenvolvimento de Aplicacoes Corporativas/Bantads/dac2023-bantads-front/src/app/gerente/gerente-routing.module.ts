import { ResumoComponent } from './resumo/resumo.component';
import { ConsultaComponent } from './consulta/consulta.component';
import { AuthGuard } from '../login/auth.guard';
import { ListarClientesComponent } from './listar-clientes/listar-clientes.component';
import { MelhoresClientesComponent } from './melhores-clientes/melhores-clientes.component';

import { Routes } from '@angular/router';

export const GerenteRouting: Routes = [
  {
    path: 'gerente',
    component: ResumoComponent,
    canActivate: [AuthGuard],
    data: { role: 'gerente' },
  },
  {
    path: 'gerente/consulta',
    component: ConsultaComponent,
    canActivate: [AuthGuard],
    data: { role: 'gerente' },
  },
  {
    path: 'gerente/listar-clientes',
    component: ListarClientesComponent,
    data: { role: 'gerente' },
  },
  {
    path: 'gerente/melhores-clientes',
    component: MelhoresClientesComponent,
    data: { role: 'gerente' },
  },
];
