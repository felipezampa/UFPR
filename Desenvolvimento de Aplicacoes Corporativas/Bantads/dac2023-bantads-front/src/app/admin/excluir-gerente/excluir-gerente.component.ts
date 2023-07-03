import { Component, Input } from '@angular/core';
import { Gerente } from 'src/app/shared/models/gerente.model';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { GerenteService } from 'src/app/gerente/services/gerente.service';

@Component({
  selector: 'app-excluir-gerente',
  templateUrl: './excluir-gerente.component.html',
  styleUrls: ['./excluir-gerente.component.css'],
})
export class ExcluirGerenteComponent {
  @Input() gerente!: Gerente;
  @Input() gerentes!: Gerente[];
  mensagemErro: string = '';

  constructor(
    private gerenteService: GerenteService,
    public activeModal: NgbActiveModal
  ) {}

  excluir(id: number | undefined) {
    try {
      if (this.gerentes.length === 1) {
        // Gatilho do catch para mostrar a excecao na tela
        throw new Error('Não é possivel remover o ultimo gerente!');
      } else if (id == undefined || id == null) {
        // Gatilho do catch para mostrar a excecao na tela
        throw new Error(
          'O gerente não pode ser excluído pois seu id é nulo ou undefined'
        );
      } else {
        // Chama o service
        this.gerenteService.remover(id).subscribe({
          next: () => {
            this.activeModal.close();
            window.location.reload();
          },
        });
        // Fecha o modal
      }
    } catch (e) {
      // Caso excecao tenha ocorrido adiciona uma tag h4 contendo o erro
      this.mensagemErro =
        '<h4 class="alert alert-danger strong">' + e + '</h4>';
    }
  }
}
