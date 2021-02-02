package br.com.alura.loja.desconto;

import java.math.BigDecimal;

import br.com.alura.loja.orcamento.Orcamento;

public class DescontoValorMaiorQuinhentos extends Desconto {

	public DescontoValorMaiorQuinhentos(Desconto proximo) {
		super(proximo);
	}

	@Override
	public boolean oDescontoPodeSerUsado(Orcamento orcamento) {
		return orcamento.getValor().compareTo(new BigDecimal("500")) > 0;
	}

	@Override
	protected BigDecimal obterValorDesconto(Orcamento orcamento) {
		return orcamento.getValor().multiply(new BigDecimal("0.05"));
	}
}