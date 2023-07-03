import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'jspdf-autotable';
import { Subscription } from 'rxjs';
import { GerenteService } from 'src/app/gerente/services/gerente.service';
import * as XLSX from 'xlsx';
import {
  EditarGerenteComponent,
  ExcluirGerenteComponent,
  InserirGerenteComponent,
  ModalGerenteComponent,
} from '../index';
import { Gerente } from './../../shared/models/gerente.model';
import { Pessoa } from 'src/app/shared/models/gerente-response.model';

@Component({
  selector: 'app-listar-gerente',
  templateUrl: './listar-gerente.component.html',
  styleUrls: ['./listar-gerente.component.css'],
})
export class ListarGerenteComponent implements OnInit {
  gerentes!: Gerente[];
  isLoading: boolean = false;
  subscription: Subscription | undefined;

  constructor(
    private gerenteService: GerenteService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.listarTodos();
    // Metodo com variavel assincrona para fazer atualizacao toda vez que ocorrer um subscribe
    this.subscription = this.gerenteService.refreshPage$.subscribe(() => {
      this.listarTodos();
    });
  }

  listarTodos(): void {
    // Flag para mostrar que esta carregando os dados
    this.isLoading = true;
    // Chama o metodo do service
    this.gerenteService.listarGerentes().subscribe({
      next: (data: Gerente[]) => {
        if (data == null) {
          this.gerentes = [];
        } else {
          this.gerentes = data;

          this.isLoading = false;
        }
      },
    });
  }

  abrirFormCadastro() {
    // Abre o modal com o formulario de cadastro
    const modalRef = this.modalService.open(InserirGerenteComponent, {
      size: 'xl',
    });
  }

  abrirFormAtualizacao(id: Gerente) {
    // Abre o modal com o formulario de edicao
    const modalRef = this.modalService.open(EditarGerenteComponent, {
      size: 'xl',
    });
    // Passa o Id para o modal
    modalRef.componentInstance.gerente = id;
  }

  verModal(gerente: Gerente) {
    // Abre o Modal
    const modalRef = this.modalService.open(ModalGerenteComponent, {
      size: 'xl',
    });
    // Adiciona o gerente a ser editado como argumento ao abrir o modal
    modalRef.componentInstance.gerente = gerente;
  }

  deletarModal(gerente: Gerente) {
    // Abre o Modal
    const modalRef = this.modalService.open(ExcluirGerenteComponent, {
      size: 'xl',
    });
    // Adiciona o gerente a ser excluido como argumento ao abrir o modal
    modalRef.componentInstance.gerente = gerente;
    // Adiciona o array de gerentes como argumento ao abrir o modal
    modalRef.componentInstance.gerentes = this.gerentes;
  }

  salvarPDF(tableData: Array<Gerente>) {
    console.log('Não implementada, o código tá comentado lá no componente');

    // Garante que o jsPDF foi importado e instancia um objeto novo
    const { jsPDF } = require('jspdf');
    const pdf = new jsPDF();
    const now = new Date();
    // Variavéis para o relatorio
    const columns = ['Nome', 'Email', 'CPF', 'Telefone'];
    const rows = tableData.map((data) => [
      data.nome,
      data.email,
      data.cpf,
      data.phone,
    ]);
    // Criação do relatório
    pdf.setFontSize(20).setFont(undefined, 'bold');
    pdf.setbo;
    pdf.text('Relatório de Gerentes', 15, 20);
    pdf.setFontSize(10).setFont(undefined, 'normal');
    pdf.text('Relatório Feito em:  ' + now.toLocaleString(), 15, 26);
    pdf.autoTable({
      head: [columns],
      body: rows,
      theme: 'plain',
      startY: 35,
    });
    pdf.save('RelatorioGerentes.pdf');
  }

  salvarExcel(tableData: Array<Gerente>) {
    console.log('Não implementada, o código tá comentado lá no componente');

    const columns = ['Nome', 'Email', 'CPF', 'Telefone'];
    const rows = tableData.map((data) => [
      data.nome,
      data.email,
      data.cpf,
      data.phone,
    ]);
    const worksheet = XLSX.utils.aoa_to_sheet([columns, ...rows]);
    const workbook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(workbook, worksheet, 'Sheet1');
    XLSX.writeFile(workbook, 'RelatorioGerentes.xlsx');
  }
}
