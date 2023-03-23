package model.formatos;

import model.Util;

public class Triangular extends Pizza {

	@Override
	public double calcularArea(Double medida) {
		double lado = medida;
        return Util.formatarDecimal((Math.pow(lado, 2) * Math.sqrt(3.0)) / 4);
	}

	@Override
	public double calcularMedida(Double area) {
		double primeiraOperaco = area * 4 / Math.sqrt(3.0);
		return Util.formatarDecimal(Math.sqrt(primeiraOperaco));
	}

	@Override
	public boolean validarMedida(Double medida) {
		return medida >= 20 && medida <= 60;
	}
}
