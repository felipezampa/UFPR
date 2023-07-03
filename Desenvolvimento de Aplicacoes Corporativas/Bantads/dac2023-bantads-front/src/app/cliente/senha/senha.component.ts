import { ClienteService } from '../services/cliente.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { Resumo } from 'src/app/shared/models/resumo.model';
import { ExtratoService } from '../services/extrato.service';
import { Usuario } from 'src/app/shared/models/usuario.model';

@Component({
  selector: 'app-senha',
  templateUrl: './senha.component.html',
  styleUrls: ['./senha.component.css'],
})
export class SenhaComponent implements OnInit {
  // instancia grupo de campos do formulario
  senhaForm!: FormGroup;

  hide = true;
  hide2 = true;
  hide3 = true;
  userId!: number;
  completeResponse: any;
  // injeta FormBuilder e toastr no construtor
  constructor(
    private formBuilder: FormBuilder,
    private toastr: ToastrService,
    private router: Router,
    private clienteService: ClienteService,
    private extratoService: ExtratoService
  ) {}

  ngOnInit(): void {
    this.userId = +JSON.parse(localStorage.getItem('usuarioLogado')!).id;

  }



  editarSenha() {
    history.back();

  }

  abrirConta() {
    // this.accountService.abrirConta(this.conta, this.usuario);
  }
}
