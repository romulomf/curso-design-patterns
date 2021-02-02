package br.com.alura.loja.orcamento;

import java.math.BigDecimal;

public class Item {

	private BigDecimal valor;

	public Item(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValor() {
		return valor;
	}
}