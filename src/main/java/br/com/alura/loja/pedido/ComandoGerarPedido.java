package br.com.alura.loja.pedido;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import br.com.alura.loja.orcamento.Orcamento;

public class ComandoGerarPedido {

	private List<AcaoAposGerarPedido> acoes;

	public ComandoGerarPedido(Collection<AcaoAposGerarPedido> acoes) {
		this.acoes = new ArrayList<>(acoes);
	}

	public ComandoGerarPedido(AcaoAposGerarPedido ... acoes) {
		this.acoes = Arrays.asList(acoes);
	}

	public void executar(DadosPedido dados) {
		Orcamento orcamento = new Orcamento(dados.getValor(), dados.getQuantidadeItens());
		Pedido pedido = new Pedido(dados.getCliente(), LocalDateTime.now(), orcamento);

		acoes.forEach(a -> a.executar(pedido));
	}
}