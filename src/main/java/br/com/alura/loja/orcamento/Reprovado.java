package br.com.alura.loja.orcamento;

import java.math.BigDecimal;

public class Reprovado implements Estado {

	public Reprovado() {
		// construtor padrão
	}

	@Override
	public BigDecimal obterDescontoExtra(Orcamento orcamento) {
		throw new UnsupportedOperationException("O orçamento reprovado não pode ter desconto");
	}

	@Override
	public void aprovar(Orcamento orcamento) {
		throw new IllegalStateException("Orçamento reprovado não pode ser aprovado");
	}

	@Override
	public void reprovar(Orcamento orcamento) {
		throw new IllegalStateException("Orçamento já está reprovado");
	}

	@Override
	public void finalizar(Orcamento orcamento) {
		orcamento.troca(new Finalizado());
	}
}