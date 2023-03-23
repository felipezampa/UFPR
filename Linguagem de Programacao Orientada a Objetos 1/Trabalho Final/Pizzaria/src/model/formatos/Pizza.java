package model.formatos;

import java.util.List;

import model.Util;
import model.sabor.PrecoSabor;
import model.sabor.Sabor;

public abstract class Pizza {
	private int id;
    private Double area;
	private List<Sabor> sabores;
    private Double valor;
    
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Sabor> getSabores() {
		return sabores;
	}

	public void setSabores(List<Sabor> sabores) {
		this.sabores = sabores;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

    public Double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public abstract double calcularArea(Double medida);
    public abstract double calcularMedida(Double area);
    public abstract boolean validarMedida(Double medida);

    
    public void calcularPreco(PrecoSabor configPreco) {
    	if(area != null && area > 0) {
    		Double somaValores = 0.0;
    		for(Sabor sabor: getSabores()) {
    			somaValores += configPreco.getValorPorTipo(sabor.getTipo());
    		}
    		
    		Double mediaValor = somaValores / getSabores().size();
    		
    		this.valor = Util.formatarDecimal(area * mediaValor);
    	}else{
    		this.valor = 0.0;    		
    	}
    }
    
    public boolean validarArea(Double area) {
    	return area >= 100 && area <= 1600;
    }
    
    public String recuperarNomePizza() {
    	String nomeCompleto = "";
    	for(Sabor pizza: getSabores()) {
    		nomeCompleto = nomeCompleto.concat(pizza.getNome() + " ");
    	}
    	return nomeCompleto;
    }

}
