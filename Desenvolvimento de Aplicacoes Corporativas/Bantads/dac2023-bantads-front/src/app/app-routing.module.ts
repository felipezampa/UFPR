import { AutocadastroComponent } from './login/autocadastro/autocadastro.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminRouting } from './admin/admin-routing.module';
import { LoginComponent } from './login/login/login.component';
import { ClienteRouting } from './cliente/ cliente-routing.module';
import { GerenteRouting } from './gerente/gerente-routing.module';
import { ResumoComponent } from './gerente/resumo/resumo.component'
import { ConsultaComponent } from './gerente/consulta/consulta.component';
import { ListarClientesComponent } from './gerente/listar-clientes/listar-clientes.component';
import { MelhoresClientesComponent } from './gerente/melhores-clientes/melhores-clientes.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'autocadastro', component: AutocadastroComponent },
  ...AdminRouting,
  ...ClienteRouting,
  ...GerenteRouting
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
