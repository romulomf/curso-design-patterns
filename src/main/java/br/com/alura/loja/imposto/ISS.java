package br.com.alura.loja.imposto;

import java.math.BigDecimal;

public class ISS extends Imposto {

	private final BigDecimal aliquota;

	public ISS() {
		super();
		aliquota = new BigDecimal("0.06");
	}

	public BigDecimal aliquota() {
		return aliquota;
	}
}