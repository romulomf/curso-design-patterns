package br.com.alura.loja.orcamento;

import java.math.BigDecimal;

public class Orcamento {

	private BigDecimal valor;

	private int quantidadeItens;

	private Estado estado;

	public Orcamento(BigDecimal valor, int quantidadeItens) {
		this.valor = valor;
		this.quantidadeItens = quantidadeItens;
		estado = new EmAnalise();
	}

	public BigDecimal getValor() {
		return valor;
	}

	public int getQuantidadeItens() {
		return quantidadeItens;
	}

	public Estado getEstado() {
		return estado;
	}

	public BigDecimal aplicaDescontoExtra() {
		return estado.aplicaDescontoExtra(this);
	}

	public void aprovar() {
		estado.aprovar(this);
	}

	public void reprovar() {
		estado.reprovar(this);
	}

	public void finalizar() {
		estado.finalizar(this);
	}

	void troca(Estado estado) {
		this.estado = estado;
	}
}