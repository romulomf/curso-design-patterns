package br.com.alura.loja.desconto;

import java.math.BigDecimal;

import br.com.alura.loja.orcamento.Orcamento;

public abstract class Desconto {

	protected Desconto proximo;

	public Desconto(Desconto proximo) {
		this.proximo = proximo;
	}

	public BigDecimal calcular(Orcamento orcamento) {
		if (oDescontoPodeSerUsado(orcamento)) {
			return obterValorDesconto(orcamento);
		}
		return proximo.calcular(orcamento);
	}

	public abstract boolean oDescontoPodeSerUsado(Orcamento orcamento);

	protected abstract BigDecimal obterValorDesconto(Orcamento orcamento);
}