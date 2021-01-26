package br.com.alura.loja.desconto;

import java.math.BigDecimal;

import br.com.alura.loja.orcamento.Orcamento;

public class DescontoPorMaisDeCincoItens extends Desconto {

	public DescontoPorMaisDeCincoItens(Desconto proximo) {
		super(proximo);
	}

	@Override
	public boolean oDescontoPodeSerUsado(Orcamento orcamento) {
		return orcamento.getQuantidadeItens() > 5;
	}

	@Override
	protected BigDecimal aplicarDesconto(Orcamento orcamento) {
		return orcamento.getValor().multiply(new BigDecimal("0.1"));
	}
}