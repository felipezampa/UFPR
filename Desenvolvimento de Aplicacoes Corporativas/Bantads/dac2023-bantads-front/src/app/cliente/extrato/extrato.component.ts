import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ExtratoBancario } from 'src/app/shared/models/extrato-bancario.model';
import { ExtratoService } from '../services/extrato.service';
import BalancoPorDia from 'src/app/shared/models/balanco-por-dia.model';
import ItemExtrato from 'src/app/shared/models/itemextrato.model';
import ResponseExtrato from 'src/app/shared/models/response-extrato.model';
import {
  animate,
  state,
  style,
  transition,
  trigger,
} from '@angular/animations';
import { Transacao } from 'src/app/shared/models/transacao.model';
import { LoginService } from 'src/app/login/services/login.service';

@Component({
  selector: 'app-extrato',
  templateUrl: './extrato.component.html',
  styleUrls: ['./extrato.component.css'],
  animations: [
    trigger('detailExpand', [
      state(
        'collapsed',
        style({ height: '0px', minHeight: '0', display: 'none' })
      ),
      state('expanded', style({ height: '*' })),
      transition(
        'expanded <=> collapsed',
        animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')
      ),
    ]),
  ],
})
export class ExtratoComponent implements OnInit {
  extratoBancario: ExtratoBancario = new ExtratoBancario();
  dataInicial: string | number = 0;
  dataFinal: string | number = 0;
  balancoCompleto: BalancoPorDia[] = [];
  idContaAtual: number = 0;

  range = new FormGroup({
    start: new FormControl(null),
    end: new FormControl(null),
  });

  expandedElement: Transacao[] = [];

  displayedColumns: string[] = ['data', 'balanco'];
  displayedColumns2: string[] = [
    'dataHoraCriacao',
    'valor',
    'tipo',
    'origem',
    'destino',
  ];

  constructor(
    private statementService: ExtratoService,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    this.getTransactions();
  }

  checkExpanded(element: Transacao): boolean {
    let flag = false;
    this.expandedElement.forEach((e) => {
      if (e === element) {
        flag = true;
      }
    });
    return flag;
  }

  pushPopElement(element: Transacao) {
    const index = this.expandedElement.indexOf(element);
    if (index === -1) {
      this.expandedElement.push(element);
    } else {
      this.expandedElement.splice(index, 1);
    }
  }

  getDate(date: string) {
    return new Date(date).toLocaleDateString('pt-BR');
  }

  getHours(date: string) {
    const data = new Date(date);
    const hours =
      data.getHours() < 10 ? '0' + data.getHours() : data.getHours();
    const minutes =
      data.getMinutes() < 10 ? '0' + data.getMinutes() : data.getMinutes();

    return hours + ':' + minutes;
  }

  getOrigemDestino(tipo: string, origemDestino: string) {
    console.log(tipo);
    if (tipo === 'transfer')
      if (origemDestino === 'Origem') {
        return 'Matheus Chaves';
      } else {
        return 'Razer razer';
      }

    return '';
  }

  getTransactionType(type: number) {
    if (type == 3) {
      return 'Transferência';
    } else if (type == 1) {
      return 'Depósito';
    }
    return 'Saque';
  }

  async getIntervalTransactions() {
    this.extratoBancario.dataFinal = this.dataFinal
      ? new Date(this.dataFinal).getTime()
      : new Date();
    this.extratoBancario.dataInicial = this.dataInicial
      ? new Date(this.dataInicial).getTime()
      : new Date().getDate() - 30;

    this.statementService
      .getBuscaExtrato(this.extratoBancario)
      .subscribe((response) => {
        const dateFrom = new Date(+response['dataInicial']);
        const dataFinal = new Date(+response['dataFinal']);
        const days = response['dias'];
        this.extratoBancario = new ExtratoBancario(+dateFrom, +dataFinal, days);
      });
  }

  calcularBalancoPorDia(
    dataInicial: Date,
    dataFinal: Date,
    itens: ItemExtrato[]
  ): BalancoPorDia[] {
    const balancoPorDia: BalancoPorDia[] = [];

    // Inicializa o objeto balancoPorDia com as datas do intervalo
    let dataAtual = new Date(dataInicial);
    while (dataAtual <= dataFinal) {
      const dataFormatada = dataAtual.toISOString().split('T')[0];

      // Calcula o balanço do dia com base nos itens fornecidos
      const balancoDoDia: BalancoPorDia = {
        data: dataFormatada,
        balanco: 0,
        items: [],
      };

//filtrar itens que não são transferencia
 this.idContaAtual = itens.filter((item) => item.tipoMovimentacao != 3)[0].contaOrigem;

      itens.forEach((item) => {
        const dataItem = new Date(item.dataHoraCriacao)
          .toISOString()
          .split('T')[0];
        if (dataItem === dataFormatada) {
          if (item.tipoMovimentacao == 3) {
            if (item.contaOrigem == this.idContaAtual) {
              balancoDoDia.balanco -= item.valor;
            } else {
              balancoDoDia.balanco += item.valor;
            }
            balancoDoDia.items.push(item);
          } else if (item.tipoMovimentacao == 1) {
            balancoDoDia.balanco += item.valor;
            balancoDoDia.items.push(item);
          } else if(item.tipoMovimentacao == 2){
            balancoDoDia.balanco -= item.valor;
            balancoDoDia.items.push(item);
        }

        }
      });

      balancoPorDia.push(balancoDoDia);

      dataAtual.setDate(dataAtual.getDate() + 1);
    }

    return balancoPorDia;
  }

  async getTransactions() {
    let d = new Date();
    d.setDate(d.getDate() - 7);


    this.statementService
      .getExtratoPorData(
        this.loginService.usuarioLogado.id!,
        this.dataInicial
          ? new Date(this.dataInicial).toLocaleDateString()
          : d.toLocaleDateString(),
        this.dataFinal
          ? new Date(this.dataFinal).toLocaleDateString()
          : new Date().toLocaleDateString()
      )
      .subscribe((response: ResponseExtrato) => {
        this.balancoCompleto = this.calcularBalancoPorDia(
         this.dataInicial ? new Date(this.dataInicial) : d,
          this.dataFinal ? new Date(this.dataFinal) : new Date(),
          response['movimentacoes']
        );
        console.log(this.balancoCompleto);
      });
  }
}
