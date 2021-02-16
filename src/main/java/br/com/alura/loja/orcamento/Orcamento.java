package br.com.alura.loja.orcamento;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Orcamento implements Orcavel {

	private List<Orcavel> itens;

	private Estado estado;

	public Orcamento(Item ...items) {
		estado = new EmAnalise();
		this.itens = Arrays.asList(items);
	}

	@Override
	public BigDecimal getValor() {
		BigDecimal valor = BigDecimal.ZERO;
		for (Orcavel item : itens) {
			valor = valor.add(item.getValor());
		}
		return valor;
	}

	public void adicionar(Orcavel orcavel) {
		itens.add(orcavel);
	}

	public int getQuantidadeItens() {
		return itens.size();
	}

	public Estado getEstado() {
		return estado;
	}

	public BigDecimal obterDescontoExtra() {
		return estado.obterDescontoExtra(this);
	}

	public void aprovar() {
		estado.aprovar(this);
	}

	public void reprovar() {
		estado.reprovar(this);
	}

	public void finalizar() {
		estado.finalizar(this);
	}

	public boolean isFinalizado() {
		return estado instanceof Finalizado;
	}

	void troca(Estado estado) {
		this.estado = estado;
	}
}