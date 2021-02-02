package br.com.alura.loja.pedido;

import java.math.BigDecimal;

public class DadosPedido {

	private String cliente;

	private BigDecimal valor;

	public DadosPedido(String cliente, BigDecimal valor) {
		super();
		this.cliente = cliente;
		this.valor = valor;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}