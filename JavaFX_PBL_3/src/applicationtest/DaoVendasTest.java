package applicationtest;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pblversaofinal.TipoPagamento;
import pblversaofinal.DaoPratos;
import pblversaofinal.DaoProdutos;
import pblversaofinal.DaoVendas;
import pblversaofinal.Vendas;
import pblversaofinal.execeptions.EntidadeComValoresNegativoException;
import pblversaofinal.execeptions.EstoqueInsuficienteException;
import pblversaofinal.execeptions.IdInvalidoException;
import pblversaofinal.execeptions.PratoComProdutoInvalidoException;
import pblversaofinal.execeptions.VendaComPratoInvalidoException;

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
	public void init()
			throws EntidadeComValoresNegativoException, IdInvalidoException, PratoComProdutoInvalidoException {

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
	public void testaddDadosVendasPosicaoNaLista()
			throws IdInvalidoException, EstoqueInsuficienteException, VendaComPratoInvalidoException {

		gerenciamento.addEditDados(vendaA, null);
		gerenciamento.addEditDados(vendaB, null);

		assertEquals(vendaA, DaoVendas.getListaVendas().get(0));
		assertEquals(vendaB, DaoVendas.getListaVendas().get(1));

	}

	@Test
	// Testando adicionar vendas em rela��o a posi��o ao qual devem ocupar
	// na lista de vendas
	public void testAddDadosVendasTamanhoDaLista()
			throws IdInvalidoException, EstoqueInsuficienteException, VendaComPratoInvalidoException {

		gerenciamento.addEditDados(vendaA, null);
		gerenciamento.addEditDados(vendaB, null);

		assertEquals(2, DaoVendas.getListaVendas().size());

	}

	@Test
	// Testando a remoção de vendas se existem na lista de vendas, usando como
	// base o objeto atual e o objeto antigo
	public void testRemoverDadosVendasSeExistirNaLista()
			throws IdInvalidoException, EstoqueInsuficienteException, VendaComPratoInvalidoException {

		gerenciamento.addEditDados(vendaA, null);
		gerenciamento.addEditDados(vendaB, null);

		Vendas vendaRemovido0 = DaoVendas.getListaVendas().get(0);
		gerenciamento.removerDados("0");
		assertNotEquals(vendaRemovido0, DaoVendas.getListaVendas().get(0));
		gerenciamento.addEditDados(vendaC, null);
		Vendas vendaRemovido1 = DaoVendas.getListaVendas().get(0);
		gerenciamento.removerDados("1");
		assertNotEquals(vendaRemovido1.getId(), DaoVendas.getListaVendas().get(0));
		Vendas vendaRemovido2 = DaoVendas.getListaVendas().get(0);
		gerenciamento.removerDados("2");
		assertFalse(DaoVendas.getListaVendas().contains(vendaRemovido2));

	}

	@Test
	// Testando a remo��o de vendas se existem na lista de vendas pelo tamanho
	// da lista
	public void testRemoverDadosDaVendaSeExistirTamanhoDaLista()
			throws IdInvalidoException, EstoqueInsuficienteException, VendaComPratoInvalidoException {

		gerenciamento.addEditDados(vendaA, null);
		gerenciamento.addEditDados(vendaB, null);
		gerenciamento.addEditDados(vendaC, null);

		gerenciamento.removerDados("0");
		assertEquals(2, DaoVendas.getListaVendas().size());
		gerenciamento.removerDados("1");
		assertEquals(1, DaoVendas.getListaVendas().size());
		gerenciamento.removerDados("2");
		assertEquals(0, DaoVendas.getListaVendas().size());

	}

	@Test
	// Testando a remo��o de vendas se n�o existe na lista de vendas
	public void testRemoverDadosDaVendaSeNaoExistir()
			throws IdInvalidoException, EstoqueInsuficienteException, VendaComPratoInvalidoException {

		gerenciamento.addEditDados(vendaA, null);
		gerenciamento.addEditDados(vendaB, null);

		assertThrows(IdInvalidoException.class, () -> gerenciamento.removerDados("2"));
		assertEquals(2, DaoVendas.getListaVendas().size());

	}

	@Test
	// Testando a edi��o de vendas se existem na lista de vendas
	public void testEditarProdutoNaListaDeProdutosCasoExista()
			throws IdInvalidoException, EstoqueInsuficienteException, VendaComPratoInvalidoException {

		gerenciamento.addEditDados(vendaA, null);
		gerenciamento.addEditDados(vendaB, null);
		gerenciamento.addEditDados(vendaC, "1");

		assertEquals(vendaC, DaoVendas.getListaVendas().get(1));

	}

	@Test
	// Testando a edi��o de vendas se n�o existem na lista de vendas
	public void testEditarDadosVendasSeNaoExistirNaLista()
			throws IdInvalidoException, EstoqueInsuficienteException, VendaComPratoInvalidoException {

		gerenciamento.addEditDados(vendaA, null);
		gerenciamento.addEditDados(vendaC, null);

		assertThrows(IdInvalidoException.class, () -> gerenciamento.addEditDados(vendaB, "2"));
		assertFalse(DaoVendas.getListaVendas().contains(vendaB));

	}

	@Test
	// Testando a listagem do dados do elemento da lista de vendas
	public void testListagemDados()
			throws IdInvalidoException, EstoqueInsuficienteException, VendaComPratoInvalidoException {

		gerenciamento.addEditDados(vendaA, null);

		assertEquals(vendaA.getListaIdItens(), DaoVendas.getListaVendas().get(0).getListaIdItens());
		assertEquals(vendaA.getDiaHorario(), DaoVendas.getListaVendas().get(0).getDiaHorario());
		assertEquals(vendaA.getPrecoTotal(), DaoVendas.getListaVendas().get(0).getPrecoTotal());
		assertEquals(vendaA.getTipoPagamento(), DaoVendas.getListaVendas().get(0).getTipoPagamento());

	}

	@Test
	//Testando realizacao de venda com estoque insuficiente
	public void testDeEstoqueInsuficienteParaVenda()
			throws IdInvalidoException, EstoqueInsuficienteException, VendaComPratoInvalidoException {

		gerenciamento.addEditDados(vendaA, null);
		ArrayList<String> vendaZ = vendaB.getListaIdItens();
		vendaZ.add("0");
		vendaZ.add("1");
		vendaZ.add("2");
		vendaZ.add("0");
		vendaZ.add("0");
		vendaB.setListaIdItens(vendaZ);
		assertThrows(EstoqueInsuficienteException.class, () -> gerenciamento.addEditDados(vendaB, null));

	}

	@Test
	//Testando realizacao de venda com prato invalido
	public void testVendaComPratoInvalidoDeEstoqueInsuficienteParaVenda()
			throws IdInvalidoException, EstoqueInsuficienteException, VendaComPratoInvalidoException {

		gerenciamento.addEditDados(vendaA, null);
		ArrayList<String> vendaZ = vendaB.getListaIdItens();
		vendaZ.add("0");
		vendaZ.add("7");
		vendaB.setListaIdItens(vendaZ);
		assertThrows(VendaComPratoInvalidoException.class, () -> gerenciamento.addEditDados(vendaB, null));

	}

}
