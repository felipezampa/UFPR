import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { GerenteService } from 'src/app/gerente/services/gerente.service';
import { ModalGerenteComponent } from '../index';
import { Gerente } from './../../shared/models/gerente.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  gerentes!: Gerente[];

  constructor(
    private gerenteService: GerenteService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.listarGerentes();
  }

  verModal(gerente: Gerente) {
    // Abre o Modal
    const modalRef = this.modalService.open(ModalGerenteComponent, {
      size: 'xl',
    });
    // Adiciona o gerente a ser editado como argumento ao abrir o modal
    modalRef.componentInstance.gerente = gerente;
  }

  listarGerentes() {
    this.gerenteService.listarGerentes().subscribe({
      next: (data: Gerente[]) => {
        if (data == null) {
          this.gerentes = [];
        } else {
          this.gerentes = data;
        }
      },
    });
  }
  totalAmount(gerente: Gerente): String {
    const formatter = new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL',

      //minimumFractionDigits: 0, // (this suffices for whole numbers, but will print 2500.10 as $2,500.1)
      maximumFractionDigits: 0, // (causes 2500.99 to be printed as $2,501)
    });

    let acc = gerente.contas!.reduce((acc, current) => {
      return acc + current.limite;
    }, 0);
    return formatter.format(acc);
  }
  saldosNegativos(gerente: Gerente): String {
    const formatter = new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL',
      maximumFractionDigits: 0,
    });
    let saldo = 0;
    const contas = gerente.contas;
    if (contas) {
      for (let i = 0; i < contas.length; i++) {
        const conta = contas[i];
        if (conta?.saldo && conta.saldo < 0) {
          saldo += conta.saldo;
        }
      }
    }
    return formatter.format(saldo);
    // return saldo.toString();
  }
  saldosPositivos(gerente: Gerente): String {
    const formatter = new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL',

      //minimumFractionDigits: 0, // (this suffices for whole numbers, but will print 2500.10 as $2,500.1)
      maximumFractionDigits: 0, // (causes 2500.99 to be printed as $2,501)
    });
    let saldo = 0;
    const contas = gerente.contas;
    if (contas) {
      for (let i = 0; i < contas.length; i++) {
        const conta = contas[i];
        if (conta?.saldo && conta.saldo > 0) {
          saldo += conta.saldo;
        }
      }
    }
    return formatter.format(saldo);
  }
}
