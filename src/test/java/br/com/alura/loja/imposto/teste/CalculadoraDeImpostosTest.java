package br.com.alura.loja.imposto.teste;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import br.com.alura.loja.imposto.CalculadoraDeImpostos;
import br.com.alura.loja.imposto.ICMS;
import br.com.alura.loja.imposto.ISS;
import br.com.alura.loja.orcamento.Orcamento;

@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("Calculadora de Impostos")
class CalculadoraDeImpostosTest {

	private BigDecimal aliquotaIcms;

	private BigDecimal aliquotaIss;

	@BeforeAll
	void configuracao() {
		aliquotaIcms = new BigDecimal("0.1");
		aliquotaIss = new BigDecimal("0.06");
	}

	@Test
	@DisplayName("Cálculo do ICMS")
	void testeCalculoICMS() {
		Orcamento orcamento = new Orcamento(new BigDecimal("100"), 4);
		BigDecimal imposto = CalculadoraDeImpostos.calcular(orcamento, new ICMS());
		BigDecimal esperado = orcamento.getValor().multiply(aliquotaIcms);
		Assertions.assertEquals(esperado, imposto);
	}

	@Test
	@DisplayName("Cálculo do ISS")
	void testeCalculoISS() {
		Orcamento orcamento = new Orcamento(new BigDecimal("250"), 2);
		BigDecimal imposto = CalculadoraDeImpostos.calcular(orcamento, new ISS());
		BigDecimal esperado = orcamento.getValor().multiply(aliquotaIss);
		Assertions.assertEquals(esperado, imposto);
	}
}