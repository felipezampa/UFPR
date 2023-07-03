import { Gerente } from './../../shared/models/gerente.model';
import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-modal-gerente',
  templateUrl: './modal-gerente.component.html',
  styleUrls: ['./modal-gerente.component.css']
})
export class ModalGerenteComponent {

  @Input() gerente!: Gerente;
  
  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
  }

}
