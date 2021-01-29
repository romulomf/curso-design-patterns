package br.com.alura.loja.imposto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import br.com.alura.loja.orcamento.Orcamento;

public abstract class Imposto {

	private List<Imposto> impostos;

	protected Imposto(Imposto ...impostos) {
		this.impostos = Arrays.asList(impostos);
	}

	public abstract BigDecimal aliquota();

	public final BigDecimal calcular(Orcamento orcamento) {
		BigDecimal valor = aliquota();
		for (Imposto imposto : impostos) {
			valor = valor.add(imposto.aliquota());
		}
		return orcamento.getValor().multiply(valor);
	}
}