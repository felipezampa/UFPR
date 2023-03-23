package model.sabor;

public class PrecoSabor {
    private Double valorSimples;
    private Double valorPremium;
    private Double valorEspecial;
    
    
    public PrecoSabor(double valorSimples, double valorPremium, double valorEspecial) {
    	this.valorSimples = valorSimples;
    	this.valorPremium = valorPremium;
    	this.valorEspecial = valorEspecial;
    }
    
	public PrecoSabor() {
	}
	

	public void setValorSimples(Double valorSimples) {
		this.valorSimples = valorSimples;
	}

	public void setValorPremium(Double valorPremium) {
		this.valorPremium = valorPremium;
	}

	public void setValorEspecial(Double valorEspecial) {
		this.valorEspecial = valorEspecial;
	}

	public Double getValorSimples() {
		return valorSimples;
	}

	public Double getValorPremium() {
		return valorPremium;
	}

	public Double getValorEspecial() {
		return valorEspecial;
	}

	public Double getValorPorTipo(String tipo) {
		Double valorPizza = 0.0;
		switch(tipo) {
			case "SIMPLES":
				valorPizza = getValorSimples();
				break;
			case "PREMIUM":
				valorPizza = getValorPremium();
				break;
			case "ESPECIAL":
				valorPizza = getValorEspecial();
				break;				
		}
		return valorPizza;
	}

}
