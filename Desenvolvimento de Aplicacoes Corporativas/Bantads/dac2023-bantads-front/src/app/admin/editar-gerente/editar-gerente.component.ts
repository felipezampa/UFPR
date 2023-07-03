import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { GerenteService } from 'src/app/gerente/services/gerente.service';
import { Gerente } from './../../shared/models/gerente.model';

@Component({
  selector: 'app-editar-gerente',
  templateUrl: './editar-gerente.component.html',
  styleUrls: ['./editar-gerente.component.css']
})
export class EditarGerenteComponent implements OnInit {

  @ViewChild("formGerente") formGerente!: NgForm;
  @Input() gerente!: Gerente;

  constructor(private gerenteService: GerenteService, public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
    // Metodo que vai preencher os dados na instancia de gerente para preencher o formulario

  }

  atualizar(): void {
    if (this.formGerente.form.valid) {
      // Se estiver valido faz a insercao atraves do service
      this.gerenteService.alterar(this.gerente).subscribe();
      // Fecha o modal
      this.activeModal.close();
    }
  }

}
