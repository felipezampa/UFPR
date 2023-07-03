import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { GerenteService } from 'src/app/gerente/services/gerente.service';
import { Gerente } from './../../shared/models/gerente.model';

@Component({
  selector: 'app-inserir-gerente',
  templateUrl: './inserir-gerente.component.html',
  styleUrls: ['./inserir-gerente.component.css'],
})
export class InserirGerenteComponent implements OnInit {
  @ViewChild('formGerente') formGerente!: NgForm;
  gerente!: Gerente;
  todosGerentes!: Gerente[];
  gerenteComMaisContas!: Gerente;
  constructor(
    private gerenteService: GerenteService,
    public activeModal: NgbActiveModal
  ) {}

  ngOnInit(): void {
    this.gerente = new Gerente();
    this.listarGerentes();
    this.compararGerentes();
  }

  inserir(): void {
    if (this.formGerente.form.valid) {
      // Atribui um array vazio de contas ao gerente
      this.gerente.contas = [];
      // Se estiver valido faz a insercao atraves do service
      this.gerenteService.inserir(this.gerente).subscribe({
        next: (data: Gerente) => {
          window.location.reload();
        },
      });
      // Fecha o modal
      this.activeModal.close();
    }
  }

  listarGerentes() {
    // Lista todos os gerentes para comparar o gerente com mais contas
    this.gerenteService.listarTodos().subscribe({
      next: (data: Gerente[]) => {
        if (data == null) {
          this.todosGerentes = [];
        } else {
          this.todosGerentes = data;
          // Primeiro objeto que vier vai receber o valor de gerente com mais contas
          this.gerenteComMaisContas = data[0];
          for (let i = 0; i < this.todosGerentes!.length; i++) {
            // Tem que usar o ! em tudo porque o typescript eh uma desgraca e ficou dando erro de
            // objeto undefined toda hora, entao tem que colocar em todo lugar essa droga
            if (
              this.gerenteComMaisContas!.contas!.length <
              this.todosGerentes[i]!.contas!.length
            ) {
              // Comparacao simples que todo mundo sabe e nao precisa explicar
              this.gerenteComMaisContas = this.todosGerentes[i];
            }
          }
        }
      },
    });
  }

  compararGerentes() {}
}
