package br.com.alura.loja.orcamento;

import java.math.BigDecimal;

public class Finalizado implements Estado {

	public Finalizado() {
		// construtor padrão
	}

	@Override
	public BigDecimal aplicaDescontoExtra(Orcamento orcamento) {
		throw new UnsupportedOperationException("O orçamento finalizado não pode ter desconto");
	}

	@Override
	public void aprovar(Orcamento orcamento) {
		throw new IllegalStateException("O orçamento finalizado não pode ser aprovado");
	}

	@Override
	public void reprovar(Orcamento orcamento) {
		throw new IllegalStateException("O orçamento finalizado não pode ser reprovado");
	}

	@Override
	public void finalizar(Orcamento orcamento) {
		throw new IllegalStateException("O orçamento já está finalizado");
	}
}