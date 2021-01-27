package br.com.alura.loja.pedido.teste;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import br.com.alura.loja.pedido.ComandoGerarPedido;
import br.com.alura.loja.pedido.DadosPedido;
import br.com.alura.loja.pedido.RegistrarGeracaoPedido;

@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("Pedidos")
class PedidoTest {

	PedidoTest() {
		// construtor padrão
	}

	@Test
	@DisplayName("Testa a geração de um pedido")
	void testaGeracaoPedido() {
		DadosPedido dados = new DadosPedido("Rômulo", new BigDecimal(50), 2);
		ComandoGerarPedido comando = new ComandoGerarPedido(new RegistrarGeracaoPedido());
		Assertions.assertDoesNotThrow(() -> {			
			comando.executar(dados);
		});
	}
}