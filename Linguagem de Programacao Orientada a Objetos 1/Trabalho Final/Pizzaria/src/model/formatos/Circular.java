package model.formatos;

import model.Util;

public class Circular extends Pizza {

	@Override
	public double calcularArea(Double medida) {
		double raio = medida;
        return Util.formatarDecimal(Math.pow(raio, 2) * Math.PI);
	}

	@Override
	public double calcularMedida(Double area) {
		return Util.formatarDecimal(Math.sqrt( area / Math.PI)); 
	}

	@Override
	public boolean validarMedida(Double medida) {
		return medida >= 7 && medida <= 23;
	}
}
