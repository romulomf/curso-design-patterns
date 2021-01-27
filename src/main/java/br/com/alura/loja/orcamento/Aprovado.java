package br.com.alura.loja.orcamento;

import java.math.BigDecimal;

public class Aprovado implements Estado {

	private boolean oDescontoJaFoiAplicado;

	public Aprovado() {
		oDescontoJaFoiAplicado = false;
	}

	@Override
	public BigDecimal aplicaDescontoExtra(Orcamento orcamento) {
		if (!oDescontoJaFoiAplicado) {
			oDescontoJaFoiAplicado = true;
			return orcamento.getValor().multiply(new BigDecimal("0.02"));
		}
		return BigDecimal.ZERO;
	}

	@Override
	public void aprovar(Orcamento orcamento) {
		throw new IllegalStateException("O orçamento já está aprovado");
	}

	@Override
	public void reprovar(Orcamento orcamento) {
		throw new IllegalStateException("O orçamento aprovado não pode ser reprovado");
	}

	@Override
	public void finalizar(Orcamento orcamento) {
		orcamento.troca(new Finalizado());
	}
}
