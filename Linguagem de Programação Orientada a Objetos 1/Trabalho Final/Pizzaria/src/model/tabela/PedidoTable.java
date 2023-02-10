package model.tabela;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Util;
import model.pedido.Pedido;

public class PedidoTable extends AbstractTableModel{

	private List<Pedido> pedidos = new ArrayList<Pedido>();
	private String[] colunas = {"Nome Completo", "Qtd. de Pizza", "Valor Total", "Status"};
	
	@Override
	public int getRowCount() {
		return pedidos.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}
	

    @Override
    public String getColumnName(int index) {
        return this.colunas[index];
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
        Pedido pedido = pedidos.get(rowIndex);
        switch (columnIndex) {
            case 0: return pedido.getCliente().getNomeCompleto();
            case 1: return pedido.getPizzas().size();
            case 2: return Util.formatarDecimal(pedido.getValorTotal());
            case 3: return pedido.getStatus();
            default : return null;
        }
	}
	
    public void adicionarPedido(Pedido pedido) {
        this.pedidos.add(pedido);
        this.fireTableRowsInserted(pedidos.size()-1,pedidos.size()-1);
    }
    
    public boolean removePedido(Pedido pedido) {
        int linha = this.pedidos.indexOf(pedido);
        boolean result = this.pedidos.remove(pedido);
        this.fireTableRowsDeleted(linha,linha);
        return result;
    }

    public void atualizarTabela(List<Pedido> lista){
        this.pedidos = new ArrayList<Pedido>();
        this.pedidos.addAll(lista);
        this.fireTableDataChanged();
    }

    public Pedido getPedido(int linha){
        return pedidos.get(linha);
    }

}
