package br.com.alura.loja.orcamento.teste;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import br.com.alura.loja.desconto.CalculadoraDeDescontos;
import br.com.alura.loja.orcamento.Aprovado;
import br.com.alura.loja.orcamento.EmAnalise;
import br.com.alura.loja.orcamento.Finalizado;
import br.com.alura.loja.orcamento.Item;
import br.com.alura.loja.orcamento.Orcamento;
import br.com.alura.loja.orcamento.Reprovado;

@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("Desconto extra baseado no estado de um orçamento")
class EstadoOrcamentoTest {

	private BigDecimal valorDescontoExtraEmAnalise;

	private BigDecimal valorDescontoExtraAprovado;

	EstadoOrcamentoTest() {
		// construtor padrão
	}

	@BeforeAll
	void configuracao() {
		valorDescontoExtraEmAnalise = new BigDecimal("0.05");
		valorDescontoExtraAprovado = new BigDecimal("0.02");
	}

	@Test
	@DisplayName("Testa desconto extra em orçamento em análise")
	void testaDescontoExtraEmOrcamentoEmAnalise() {
		final BigDecimal valor = new BigDecimal("100");
		Orcamento orcamento = new Orcamento(new Item(valor));
		final BigDecimal esperado = orcamento.getValor().multiply(valorDescontoExtraEmAnalise);
		orcamento.obterDescontoExtra();
		BigDecimal desconto = orcamento.getValor().multiply(valorDescontoExtraEmAnalise);
		// não é esperado nenhum desconto normal, apenas o valor do desconto extra.
		Assertions.assertEquals(esperado, desconto);
		Assertions.assertTrue(orcamento.getEstado() instanceof EmAnalise);
	}

	@Test
	@DisplayName("Testa desconto extra em orçamento aprovado")
	void testaDescontoExtraEmOrcamentoAprovado() {
		final BigDecimal valor = new BigDecimal("100");
		Orcamento orcamento = new Orcamento(new Item(valor));
		// descontos esperados para o orçamento
		final BigDecimal descontoComumEsperado = BigDecimal.ZERO;
		final BigDecimal descontoExtraEsperado = orcamento.getValor().multiply(valorDescontoExtraAprovado);
		final BigDecimal descontoTotal = descontoComumEsperado.add(descontoExtraEsperado);
		// faz o cálculo do desconto normal.
		BigDecimal descontoComum = CalculadoraDeDescontos.calcular(orcamento);
		// faz a transição de estados do orçamento.
		orcamento.aprovar();
		// concede um desconto extra ao orçamento.
		BigDecimal descontoExtra = orcamento.obterDescontoExtra();
		// soma todo o acúmulo de descontos que podem ser oferecidos para este orçamento.
		final BigDecimal desconto = descontoComum.add(descontoExtra);
		// obtém o resultado dos descontos
		BigDecimal total = orcamento.getValor().multiply(desconto);
		final BigDecimal esperado = orcamento.getValor().multiply(descontoTotal);
		Assertions.assertEquals(esperado, total);
		Assertions.assertTrue(orcamento.getEstado() instanceof Aprovado);
	}

	@Test
	@DisplayName("Testa desconto extra em orçamento reprovado")
	void testaDescontoExtraEmOrcamentoReprovado() {
		final BigDecimal valor = new BigDecimal("100");
		Orcamento orcamento = new Orcamento(new Item(valor));
		// faz a transição de estados do orçamento.
		orcamento.reprovar();
		// testa se a validação que impede a concessão de desconto extra em orçamento reprovado está sendo aplicada.
		Assertions.assertThrows(UnsupportedOperationException.class, () -> orcamento.obterDescontoExtra());
		Assertions.assertTrue(orcamento.getEstado() instanceof Reprovado);
	}

	@Test
	@DisplayName("Testa desconto extra em orçamento finalizado")
	void testaDescontoExtraEmOrcamentoFinalizado() {
		final BigDecimal valor = new BigDecimal("100");
		Orcamento orcamento = new Orcamento(new Item(valor));
		// faz a transição de estados do orçamento. Um orçamento não pode ser finalizado diretamente, logo ele tem que ser aprovado ou reprovado antes de ser finalizado.
		orcamento.aprovar();
		orcamento.finalizar();
		// testa se a validação que impede a concessão de desconto extra em orçamento reprovado está sendo aplicada.
		Assertions.assertThrows(UnsupportedOperationException.class, () -> orcamento.obterDescontoExtra());
		Assertions.assertTrue(orcamento.getEstado() instanceof Finalizado);
	}
}