package br.com.alura.loja.orcamento;

import java.math.BigDecimal;

public class EmAnalise implements Estado {

	private boolean oDescontoJaFoiAplicado;

	public EmAnalise() {
		oDescontoJaFoiAplicado = false;
	}

	@Override
	public BigDecimal obterDescontoExtra(Orcamento orcamento) {
		if (!oDescontoJaFoiAplicado) {
			oDescontoJaFoiAplicado = true;
			return orcamento.getValor().multiply(new BigDecimal("0.05"));
		}
		return BigDecimal.ZERO;
	}

	@Override
	public void aprovar(Orcamento orcamento) {
		orcamento.troca(new Aprovado());
	}

	@Override
	public void reprovar(Orcamento orcamento) {
		orcamento.troca(new Reprovado());
	}

	@Override
	public void finalizar(Orcamento orcamento) {
		throw new IllegalStateException("O orçamento em análise não pode ser finalizado diretamente");
	}
}