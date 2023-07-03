import { Component, OnInit } from '@angular/core';
import axios from 'axios';
import 'jspdf-autotable';
import { ClienteService } from 'src/app/cliente/services/cliente.service';
import { ContaService } from 'src/app/conta/conta.service';
import { GerenteService } from 'src/app/gerente/services/gerente.service';
import { UsuarioAdmin , ContaAdmin} from 'src/app/shared/models/admin-response.model';
import { Gerente } from 'src/app/shared/models/gerente.model';
import * as XLSX from 'xlsx';

@Component({
  selector: 'app-listar-cliente',
  templateUrl: './listar-cliente.component.html',
  styleUrls: ['./listar-cliente.component.css'],
})
export class ListarClienteComponent implements OnInit {
  clientes!: UsuarioAdmin[];
  gerentes!: Gerente[];
  isLoading: boolean = false;

  constructor(
    private contaService: ContaService,
    private clienteService: ClienteService,
    private gerenteService: GerenteService
  ) {}

  ngOnInit(): void {
    this.isLoading = true;
    this.listarGerentes();
    this.isLoading = false;
  }

  listarGerentes(): void {
    // Flag para mostrar que esta carregando os dados
    this.isLoading = true;
    // Chama o metodo do service
    this.clienteService.getAllClientes().subscribe((response) => {
      console.log(response);
      this.clientes = response;
      this.isLoading = false;
      this.clientes.forEach(async (element, index) => {
        let link = 'http://localhost:8083/contas/cliente/' + element.id;
        await axios({
          method: 'get',
          url: link,
        })
          .then((response) => {
             this.clientes[index]["conta"] = response.data;

            console.log(response.data);
          })
          .catch((err) => {
             this.clientes[index]["conta"] = null;
          });
      });
    });


  }

  salvarPDF(tableData: Array<UsuarioAdmin>) {
    // Garante que o jsPDF foi importado e instancia um objeto novo
    const { jsPDF } = require('jspdf');
    const pdf = new jsPDF();
    const now = new Date();
    // Variavéis para o relatorio
    const columns = ['Nome', 'CPF', 'Limite', 'Saldo'];
    const rows = tableData.map((data) => [
      data.nome,
      data.cpf,
      data.conta != null ? data.conta.limite : 0,
      data.conta != null ? data.conta.saldo : 0,
    ]);
    // Criação do relatório
    pdf.setFontSize(20).setFont(undefined, 'bold');
    pdf.setbo;
    pdf.text('Relatório de Clientes', 15, 20);
    pdf.setFontSize(10).setFont(undefined, 'normal');
    pdf.text('Relatório Feito em:  ' + now.toLocaleString(), 15, 26);
    pdf.autoTable({
      head: [columns],
      body: rows,
      theme: 'plain',
      startY: 35,
    });
    pdf.save('RelatorioClientes.pdf');
  }

  salvarExcel(tableData: Array<UsuarioAdmin>) {
    const columns = ['Nome', 'CPF', 'Limite', 'Saldo'];
    const rows = tableData.map((data) => [
      data.nome,
      data.cpf,
      data.conta != null ? data.conta.limite : 0,
      data.conta != null ? data.conta.saldo : 0,
    ]);
    const worksheet = XLSX.utils.aoa_to_sheet([columns, ...rows]);
    const workbook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(workbook, worksheet, 'Sheet1');
    XLSX.writeFile(workbook, 'RelatorioClientes.xlsx');
  }
}
