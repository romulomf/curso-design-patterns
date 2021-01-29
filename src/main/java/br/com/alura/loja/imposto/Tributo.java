package br.com.alura.loja.imposto;

import java.math.BigDecimal;

public class Tributo extends Imposto {

	public Tributo() {
		super(new ISS(), new ICMS());
	}

	@Override
	public BigDecimal aliquota() {
		return BigDecimal.ZERO;
	}
}
