package model.pedido;

import model.formatos.Pizza;
import java.util.ArrayList;

public class Pedido {
    private int id;
    private Cliente cliente;
    private ArrayList<Pizza> pizzas;
    private Double valorTotal;
    private Status status;
    
	public Pedido(int id, Cliente cliente, ArrayList<Pizza> pizzas, double valorTotal, Status status) {
		this.id = id;
		this.cliente = cliente;
		this.pizzas = pizzas;
		this.valorTotal = valorTotal;
		this.status = status;
	}
	
	public Pedido() {}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public ArrayList<Pizza> getPizzas() {
		if(pizzas == null) {
			return new ArrayList<Pizza>();
		}
		return pizzas;
	}
	public void setPizzas(ArrayList<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	
	public void setPizza(Pizza pizza) {
		if(this.pizzas == null) {
			this.pizzas = new ArrayList<Pizza>();
		}
		pizza.setId(this.pizzas.size() + 1);
		this.pizzas.add(pizza);
	}

}
