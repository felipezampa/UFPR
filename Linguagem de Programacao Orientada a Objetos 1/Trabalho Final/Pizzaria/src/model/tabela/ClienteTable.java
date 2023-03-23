package model.tabela;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.pedido.Cliente;

public class ClienteTable extends AbstractTableModel{

	private List<Cliente> clientes = new ArrayList<Cliente>();
	private String[] colunas = {"Nome", "Sobrenome", "Telefone"};
	
	@Override
	public int getRowCount() {
		return clientes.size();
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
        Cliente cliente = clientes.get(rowIndex);
        switch (columnIndex) {
            case 0: return cliente.getNome();
            case 1: return cliente.getSobrenome();
            case 2: return cliente.getTelefone();
            default : return null;
        }
	}
	
    public void adicionarCliente(Cliente customer) {
        this.clientes.add(customer);
        this.fireTableRowsInserted(clientes.size()-1,clientes.size()-1);
    }
    
    public boolean removeCliente(Cliente customer) {
        int linha = this.clientes.indexOf(customer);
        boolean result = this.clientes.remove(customer);
        this.fireTableRowsDeleted(linha,linha);
        return result;
    }

    public void atualizarTabela(List<Cliente> lista){
        this.clientes = new ArrayList<Cliente>();
        this.clientes.addAll(lista);
        this.fireTableDataChanged();
    }

    public Cliente getCliente(int linha){
        return clientes.get(linha);
    }

}
