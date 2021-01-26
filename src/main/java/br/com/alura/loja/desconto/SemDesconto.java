package br.com.alura.loja.desconto;

import java.math.BigDecimal;

import br.com.alura.loja.orcamento.Orcamento;

public class SemDesconto extends Desconto {

	public SemDesconto(Desconto desconto) {
		super(desconto);
	}

	@Override
	public boolean oDescontoPodeSerUsado(Orcamento orcamento) {
		return true;
	}

	@Override
	protected BigDecimal aplicarDesconto(Orcamento orcamento) {
		return BigDecimal.ZERO;
	}
}