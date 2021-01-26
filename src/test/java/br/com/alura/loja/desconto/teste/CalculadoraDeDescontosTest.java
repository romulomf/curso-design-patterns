package br.com.alura.loja.desconto.teste;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import br.com.alura.loja.desconto.CalculadoraDeDescontos;
import br.com.alura.loja.orcamento.Orcamento;

@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("Calculadora de Descontos")
class CalculadoraDeDescontosTest {

	private BigDecimal valorDescontoComMaisDeCincoItens;

	private BigDecimal valorDescontoComValorMaiorQueQuinhentos;

	@BeforeAll
	void configuracao() {
		valorDescontoComMaisDeCincoItens = new BigDecimal("0.1");
		valorDescontoComValorMaiorQueQuinhentos = new BigDecimal("0.05");
	}

	@Test
	@DisplayName("Calcular orçamento sem desconto")
	void testeCalcularOrcamentoSemDesconto() {
		BigDecimal esperado = BigDecimal.ZERO;
		Orcamento orcamento = new Orcamento(new BigDecimal("150"), 1);
		BigDecimal desconto = CalculadoraDeDescontos.calcular(orcamento);
		Assertions.assertEquals(esperado, desconto);
	}

	@Test
	@DisplayName("Calcular orçamento com mais de 5 itens")
	void testeCalcularOrcamentoComCincoItens() {
		BigDecimal valor = new BigDecimal("100");
		BigDecimal esperado = valor.multiply(valorDescontoComMaisDeCincoItens);
		Orcamento orcamento = new Orcamento(valor, 8);
		BigDecimal desconto = CalculadoraDeDescontos.calcular(orcamento);
		Assertions.assertEquals(esperado, desconto);
	}

	@Test
	@DisplayName("Calcular orçamento com valor maior do que 500")
	void testeCalcularOrcamentoValorMaiorQueQuinhentos() {
		BigDecimal valor = new BigDecimal("750");
		BigDecimal esperado = valor.multiply(valorDescontoComValorMaiorQueQuinhentos);
		Orcamento orcamento = new Orcamento(valor, 3);
		BigDecimal desconto = CalculadoraDeDescontos.calcular(orcamento);
		Assertions.assertEquals(esperado, desconto);
	}

	@Test
	@DisplayName("Calcular orçamento que pode cair em mais de uma regra")
	void testeCalcularDescontosCumulativos() {
		BigDecimal valor = new BigDecimal("1500");
		/*
		 * O que é esperado aqui depende de qual é a ordem que a cadeia de responsabilidade
		 * está ordenada, pois a ordem dela pode afetar o resultado que é esperado aqui.
		 */
		BigDecimal esperado = valor.multiply(valorDescontoComValorMaiorQueQuinhentos);
		Orcamento orcamento = new Orcamento(valor, 10);
		BigDecimal desconto = CalculadoraDeDescontos.calcular(orcamento);
		Assertions.assertEquals(esperado, desconto);
	}
}