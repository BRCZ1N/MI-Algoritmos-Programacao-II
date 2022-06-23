package applicationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import applicationexeceptions.EntidadeComValoresNegativoException;
import applicationexeceptions.EstoqueInsuficienteException;
import applicationmodel.TipoPagamento;
import applicationmodel.Vendas;
import applicationmodeldao.DaoPratos;
import applicationmodeldao.DaoProdutos;
import applicationmodeldao.DaoVendas;

public class DaoVendasTest {

	Vendas vendaA;
	Vendas vendaB;
	Vendas vendaC;
	DaoVendas gerenciamento;
	DaoPratos gerenciamento2;
	ArrayList<String> listaIdItens;
	DaoProdutos gerenciamento3;

	@BeforeEach
	// Inicilizando os dados necess�rios aos testes
	public void init() throws EntidadeComValoresNegativoException {

		gerenciamento3 = new DaoProdutos();
		gerenciamento2 = new DaoPratos();
		gerenciamento = new DaoVendas();
		DaoVendas.limparLista();
		DaoVendas.setIdSeq(0);

		listaIdItens = new ArrayList<String>();

		listaIdItens.add("0");
		listaIdItens.add("1");

		vendaA = new Vendas(listaIdItens, TipoPagamento.getTipoDePagamento1());
		vendaB = new Vendas(listaIdItens, TipoPagamento.getTipoDePagamento2());
		vendaC = new Vendas(listaIdItens, TipoPagamento.getTipoDePagamento3());

	}

	@AfterEach
	// limpar a lista e definir o a sequencia do id pra 0 para reiniciar o processo
	public void posTest() {

		DaoVendas.limparLista();
		DaoVendas.setIdSeq(0);
		DaoPratos.limparLista();
		DaoPratos.setIdSeq(0);
		DaoProdutos.limparLista();
		DaoProdutos.setIdSeq(0);

	}

	@Test
	// Testando adicionar venda em rela��o a posi��o ao qual devem ocupar na
	// lista de vendas
	public void testaddDadosVendasPosicaoNaLista() throws EstoqueInsuficienteException {

		DaoVendas.addEditDados(vendaA, null);
		DaoVendas.addEditDados(vendaB, null);

		assertEquals(vendaA, DaoVendas.getListaVendas().get(0));
		assertEquals(vendaB, DaoVendas.getListaVendas().get(1));

	}

	@Test
	// Testando adicionar vendas em rela��o a posi��o ao qual devem ocupar
	// na lista de vendas
	public void testAddDadosVendasTamanhoDaLista() throws EstoqueInsuficienteException {

		DaoVendas.addEditDados(vendaA, null);
		DaoVendas.addEditDados(vendaB, null);

		assertEquals(2, DaoVendas.getListaVendas().size());

	}

	@Test
	// Testando a remoção de vendas se existem na lista de vendas, usando como
	// base o objeto atual e o objeto antigo
	public void testRemoverDadosVendasSeExistirNaLista() throws EstoqueInsuficienteException {

		DaoVendas.addEditDados(vendaA, null);
		DaoVendas.addEditDados(vendaB, null);

		Vendas vendaRemovido0 = DaoVendas.getListaVendas().get(0);
		DaoVendas.removerDados("0");
		assertNotEquals(vendaRemovido0, DaoVendas.getListaVendas().get(0));
		DaoVendas.addEditDados(vendaC, null);
		Vendas vendaRemovido1 = DaoVendas.getListaVendas().get(0);
		DaoVendas.removerDados("1");
		assertNotEquals(vendaRemovido1.getId(), DaoVendas.getListaVendas().get(0));
		Vendas vendaRemovido2 = DaoVendas.getListaVendas().get(0);
		DaoVendas.removerDados("2");
		assertFalse(DaoVendas.getListaVendas().contains(vendaRemovido2));

	}

	@Test
	// Testando a remo��o de vendas se existem na lista de vendas pelo tamanho
	// da lista
	public void testRemoverDadosDaVendaSeExistirTamanhoDaLista() throws EstoqueInsuficienteException {

		DaoVendas.addEditDados(vendaA, null);
		DaoVendas.addEditDados(vendaB, null);
		DaoVendas.addEditDados(vendaC, null);

		DaoVendas.removerDados("0");
		assertEquals(2, DaoVendas.getListaVendas().size());
		DaoVendas.removerDados("1");
		assertEquals(1, DaoVendas.getListaVendas().size());
		DaoVendas.removerDados("2");
		assertEquals(0, DaoVendas.getListaVendas().size());

	}

	@Test
	// Testando a edi��o de vendas se existem na lista de vendas
	public void testEditarVendaNaListaDeVendasCasoExista() throws EstoqueInsuficienteException {

		DaoVendas.addEditDados(vendaA, null);
		DaoVendas.addEditDados(vendaB, null);
		DaoVendas.addEditDados(vendaC, "1");

		assertEquals(vendaC, DaoVendas.getListaVendas().get(1));

	}

	@Test
	// Testando a listagem do dados do elemento da lista de vendas
	public void testListagemDados() throws EstoqueInsuficienteException {

		DaoVendas.addEditDados(vendaA, null);

		assertEquals(vendaA.getListaIdItens(), DaoVendas.getListaVendas().get(0).getListaIdItens());
		assertEquals(vendaA.getDiaHorario(), DaoVendas.getListaVendas().get(0).getDiaHorario());
		assertEquals(vendaA.getPrecoTotal(), DaoVendas.getListaVendas().get(0).getPrecoTotal());
		assertEquals(vendaA.getTipoPagamento(), DaoVendas.getListaVendas().get(0).getTipoPagamento());

	}

	@Test
	// Testando realizacao de venda com estoque insuficiente
	public void testDeEstoqueInsuficienteParaVenda() throws EstoqueInsuficienteException {

		DaoVendas.addEditDados(vendaA, null);
		ArrayList<String> vendaZ = vendaB.getListaIdItens();
		vendaZ.add("0");
		vendaZ.add("1");
		vendaZ.add("2");
		vendaZ.add("0");
		vendaZ.add("0");
		vendaB.setListaIdItens(vendaZ);
		assertThrows(EstoqueInsuficienteException.class, () -> DaoVendas.addEditDados(vendaB, null));

	}


}
