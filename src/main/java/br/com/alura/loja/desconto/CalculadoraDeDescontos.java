package br.com.alura.loja.desconto;

import java.math.BigDecimal;

import br.com.alura.loja.orcamento.Orcamento;

public class CalculadoraDeDescontos {

	private CalculadoraDeDescontos() {
		// construtor padrão
	}

	public static BigDecimal calcular(Orcamento orcamento) {
		Desconto d1 = new SemDesconto(null);
		Desconto d2 = new DescontoPorMaisDeCincoItens(d1);
		Desconto d3 = new DescontoValorMaiorQuinhentos(d2);
		return d3.calcular(orcamento);
	}
}