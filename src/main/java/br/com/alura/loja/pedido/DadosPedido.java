package br.com.alura.loja.pedido;

import java.math.BigDecimal;

public class DadosPedido {

	private String cliente;

	private BigDecimal valor;

	private int quantidadeItens;

	public DadosPedido(String cliente, BigDecimal valor, int quantidadeItens) {
		super();
		this.cliente = cliente;
		this.valor = valor;
		this.quantidadeItens = quantidadeItens;
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

	public int getQuantidadeItens() {
		return quantidadeItens;
	}

	public void setQuantidadeItens(int quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
	}
}