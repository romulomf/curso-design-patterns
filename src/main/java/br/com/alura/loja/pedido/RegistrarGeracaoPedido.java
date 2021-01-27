package br.com.alura.loja.pedido;

import br.com.alura.loja.orcamento.Orcamento;

public class RegistrarGeracaoPedido implements AcaoAposGerarPedido {

	@Override
	public void executar(Pedido pedido) {
		Orcamento orcamento = pedido.getOrcamento();
		System.out.format("Pedido de %s com %d item(s) gerado a partir de um orçamento no valor de %.2f", pedido.getCliente(), orcamento.getQuantidadeItens(), orcamento.getValor());
	}
}