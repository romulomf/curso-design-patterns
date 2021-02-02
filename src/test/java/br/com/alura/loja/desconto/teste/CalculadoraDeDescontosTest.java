package br.com.alura.loja.desconto.teste;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import br.com.alura.loja.desconto.CalculadoraDeDescontos;
import br.com.alura.loja.orcamento.Item;
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
		Orcamento orcamento = new Orcamento(new Item(new BigDecimal("150")));
		BigDecimal desconto = CalculadoraDeDescontos.calcular(orcamento);
		Assertions.assertEquals(esperado, desconto);
	}

	@Test
	@DisplayName("Calcular orçamento com mais de 5 itens")
	void testeCalcularOrcamentoComCincoItens() {
		BigDecimal valor = BigDecimal.TEN;
		Item item0 = new Item(valor);
		Item item1 = new Item(valor);
		Item item2 = new Item(valor);
		Item item3 = new Item(valor);
		Item item4 = new Item(valor);
		Item item5 = new Item(valor);
		Item item6 = new Item(valor);
		Item item7 = new Item(valor);
		Item item8 = new Item(valor);
		Item item9 = new Item(valor);
		Orcamento orcamento = new Orcamento(item0, item1, item2, item3, item4, item5, item6, item7, item8, item9);
		BigDecimal esperado = orcamento.getValor().multiply(valorDescontoComMaisDeCincoItens);
		BigDecimal desconto = CalculadoraDeDescontos.calcular(orcamento);
		Assertions.assertEquals(esperado, desconto);
	}

	@Test
	@DisplayName("Calcular orçamento com valor maior do que 500")
	void testeCalcularOrcamentoValorMaiorQueQuinhentos() {
		BigDecimal valor = new BigDecimal("250");
		Item item1 = new Item(valor);
		Item item2 = new Item(valor);
		Item item3 = new Item(valor);
		Orcamento orcamento = new Orcamento(item1, item2, item3);
		BigDecimal esperado = orcamento.getValor().multiply(valorDescontoComValorMaiorQueQuinhentos);
		BigDecimal desconto = CalculadoraDeDescontos.calcular(orcamento);
		Assertions.assertEquals(esperado, desconto);
	}

	@Test
	@DisplayName("Calcular orçamento que pode cair em mais de uma regra")
	void testeCalcularDescontosCumulativos() {
		BigDecimal valor = new BigDecimal("150");
		Item item0 = new Item(valor);
		Item item1 = new Item(valor);
		Item item2 = new Item(valor);
		Item item3 = new Item(valor);
		Item item4 = new Item(valor);
		Item item5 = new Item(valor);
		Item item6 = new Item(valor);
		Item item7 = new Item(valor);
		Item item8 = new Item(valor);
		Item item9 = new Item(valor);
		/*
		 * O que é esperado aqui depende de qual é a ordem que a cadeia de responsabilidade
		 * está ordenada, pois a ordem dela pode afetar o resultado que é esperado aqui.
		 */
		Orcamento orcamento = new Orcamento(item0, item1, item2, item3, item4, item5, item6, item7, item8, item9);
		BigDecimal esperado = orcamento.getValor().multiply(valorDescontoComValorMaiorQueQuinhentos);
		BigDecimal desconto = CalculadoraDeDescontos.calcular(orcamento);
		Assertions.assertEquals(esperado, desconto);
	}
}