package br.com.alura.loja.imposto;

import java.math.BigDecimal;

public class ICMS extends Imposto {

	private final BigDecimal aliquota;

	public ICMS() {
		super();
		aliquota = new BigDecimal("0.1");
	}

	@Override
	public BigDecimal aliquota() {
		return aliquota;
	}
}