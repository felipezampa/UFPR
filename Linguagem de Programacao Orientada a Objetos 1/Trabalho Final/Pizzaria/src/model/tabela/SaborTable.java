package model.tabela;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.formatos.Pizza;
import model.pedido.Pedido;
import model.sabor.Sabor;

public class SaborTable extends AbstractTableModel{

	private List<Sabor> sabores = new ArrayList<Sabor>();
	private String[] colunas = {"Sabor", "Tipo"};
	
	@Override
	public int getRowCount() {
		return sabores.size();
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
        Sabor sabor = sabores.get(rowIndex);
        switch (columnIndex) {
            case 0: return sabor.getNome();
            case 1: return sabor.getTipo();
            default : return null;
        }
	}
	
    public void adicionarSabor(Sabor sabor) {
        this.sabores.add(sabor);
        this.fireTableRowsInserted(sabores.size()-1,sabores.size()-1);
    }
    
    public boolean removeSabor(Sabor sabor) {
        int linha = this.sabores.indexOf(sabor);
        boolean result = this.sabores.remove(sabor);
        this.fireTableRowsDeleted(linha,linha);
        return result;
    }

    public void atualizarTabela(List<Sabor> lista){
        this.sabores = lista;
        this.fireTableDataChanged();
    }

    public Sabor getSabor(int linha){
        return sabores.get(linha);
    }

}
