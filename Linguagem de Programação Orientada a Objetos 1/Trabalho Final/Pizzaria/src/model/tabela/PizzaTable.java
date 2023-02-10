package model.tabela;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.formatos.Pizza;
import model.pedido.Pedido;

public class PizzaTable extends AbstractTableModel{

	private List<Pizza> pizzas = new ArrayList<Pizza>();
	private String[] colunas = {"Pizza", "Valor"};
	 
	@Override
	public int getRowCount() {
		return pizzas.size();
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
        Pizza pizza = pizzas.get(rowIndex);
        switch (columnIndex) {
            case 0: return pizza.recuperarNomePizza();
            case 1: return pizza.getValor();
            default : return null;
        }
	}
	
    public void adicionarPizza(Pizza pizza) {
        this.pizzas.add(pizza);
        this.fireTableRowsInserted(pizzas.size()-1,pizzas.size()-1);
    }
    
    public boolean removePizza(Pizza pizza) {
        int linha = this.pizzas.indexOf(pizza);
        boolean result = this.pizzas.remove(pizza);
        this.fireTableRowsDeleted(linha,linha);
        return result;
    }

    public void atualizarTabela(List<Pizza> lista){
        this.pizzas = new ArrayList<Pizza>();
        this.pizzas.addAll(lista);
        this.fireTableDataChanged();
    }

    public Pizza getPizza(int linha){
        return pizzas.get(linha);
    }

}
