package br.com.alura.loja.orcamento.teste;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import br.com.alura.loja.orcamento.Aprovado;
import br.com.alura.loja.orcamento.EmAnalise;
import br.com.alura.loja.orcamento.Finalizado;
import br.com.alura.loja.orcamento.Orcamento;
import br.com.alura.loja.orcamento.Reprovado;

@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("Desconto extra baseado no estado de um orçamento")
class EstadoOrcamentoTest {

	private BigDecimal descontoEmAnalise = new BigDecimal("0.05");
	
	private BigDecimal descontoAprovado = new BigDecimal("0.02");

	EstadoOrcamentoTest() {
		// construtor padrão
	}

	@Test
	@DisplayName("Testa desconto extra em orçamento em análise")
	void testaDescontoExtraEmOrcamentoEmAnalise() {
		final BigDecimal valor = new BigDecimal("100");
		final BigDecimal esperado = valor.multiply(descontoEmAnalise);
		Orcamento orcamento = new Orcamento(valor, 1);
		BigDecimal desconto = orcamento.aplicaDescontoExtra();
		Assertions.assertEquals(esperado, desconto);
		Assertions.assertTrue(orcamento.getEstado() instanceof EmAnalise);
	}

	@Test
	@DisplayName("Testa desconto extra em orçamento aprovado")
	void testaDescontoExtraEmOrcamentoAprovado() {
		final BigDecimal valor = new BigDecimal("100");
		final BigDecimal esperado = valor.multiply(descontoAprovado);
		Orcamento orcamento = new Orcamento(valor, 1);
		// faz a transição de estados do orçamento.
		orcamento.aprovar();
		BigDecimal desconto = orcamento.aplicaDescontoExtra();
		Assertions.assertEquals(esperado, desconto);
		Assertions.assertTrue(orcamento.getEstado() instanceof Aprovado);
	}

	@Test
	@DisplayName("Testa desconto extra em orçamento reprovado")
	void testaDescontoExtraEmOrcamentoReprovado() {
		final BigDecimal valor = new BigDecimal("100");
		Orcamento orcamento = new Orcamento(valor, 1);
		// faz a transição de estados do orçamento.
		orcamento.reprovar();
		// testa se a validação que impede a concessão de desconto extra em orçamento reprovado está sendo aplicada.
		Assertions.assertThrows(UnsupportedOperationException.class, () -> orcamento.aplicaDescontoExtra());
		Assertions.assertTrue(orcamento.getEstado() instanceof Reprovado);
	}

	@Test
	@DisplayName("Testa desconto extra em orçamento finalizado")
	void testaDescontoExtraEmOrcamentoFinalizado() {
		final BigDecimal valor = new BigDecimal("100");
		Orcamento orcamento = new Orcamento(valor, 1);
		// faz a transição de estados do orçamento. Um orçamento não pode ser finalizado diretamente, logo ele tem que ser aprovado ou reprovado antes de ser finalizado.
		orcamento.aprovar();
		orcamento.finalizar();
		// testa se a validação que impede a concessão de desconto extra em orçamento reprovado está sendo aplicada.
		Assertions.assertThrows(UnsupportedOperationException.class, () -> orcamento.aplicaDescontoExtra());
		Assertions.assertTrue(orcamento.getEstado() instanceof Finalizado);
	}
}