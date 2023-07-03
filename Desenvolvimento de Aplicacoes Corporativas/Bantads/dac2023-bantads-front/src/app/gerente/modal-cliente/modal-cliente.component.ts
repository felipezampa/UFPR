import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Component, Input } from '@angular/core';
import { Conta } from 'src/app/shared/models/conta.model';
import { Pessoa } from 'src/app/shared/models/gerente-response.model';

@Component({
  selector: 'app-modal-cliente',
  templateUrl: './modal-cliente.component.html',
  styleUrls: ['./modal-cliente.component.css']
})
export class ModalClienteComponent {

  @Input() itemConta!: Pessoa;
  @Input() conta!: Conta;


  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
    console.log(this.itemConta)
  }

}
