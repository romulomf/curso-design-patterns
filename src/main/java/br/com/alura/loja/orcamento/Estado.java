package br.com.alura.loja.orcamento;

import java.math.BigDecimal;

public interface Estado {

	BigDecimal aplicaDescontoExtra(Orcamento orcamento);

	void aprovar(Orcamento orcamento);

	void reprovar(Orcamento orcamento);

	void finalizar(Orcamento orcamento);
}
