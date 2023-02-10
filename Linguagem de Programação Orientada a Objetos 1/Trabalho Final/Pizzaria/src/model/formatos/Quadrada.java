package model.formatos;

import model.Util;

public class Quadrada extends Pizza {

	@Override
	public double calcularArea(Double medida) {
        double lado = medida;
        return Util.formatarDecimal(lado * lado);
	}

	@Override
	public double calcularMedida(Double area) {
		return Util.formatarDecimal(Math.sqrt(area));
	}

	@Override
	public boolean validarMedida(Double medida) {
		return medida >= 10 && medida <= 40;
	}
}
