package applicationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import applicationexeceptions.EntidadeComValoresNegativoException;
import applicationexeceptions.EstoqueInsuficienteException;
import applicationmodel.Produtos;
import applicationmodeldao.DaoProdutos;

public class DaoProdutosTest {

	Produtos produtoA;
	Produtos produtoB;
	Produtos produtoC;
	Produtos produtoD;
	DaoProdutos gerenciamento;
	LocalDate validade1;
	LocalDate validade2;
	LocalDate validade3;
	LocalDate validade4;

	@BeforeEach
	public void init() {

		gerenciamento = new DaoProdutos();
		DaoProdutos.limparLista();
		DaoProdutos.setIdSeq(0);

		validade1 = LocalDate.now();
		validade2 = LocalDate.now();
		validade3 = LocalDate.now();
		validade4 = LocalDate.now();

		produtoA = new Produtos("ProdutoA", validade1, 140.0, 80.3, "Kg");
		produtoB = new Produtos("ProdutoB", validade2, 80.2, 90.1, "L");
		produtoC = new Produtos("ProdutoC", validade3, 70.9, 150.5, "Kg");
		produtoD = new Produtos("ProdutoD", validade4, 80.3, 145.3, "Kg");

	}

	@AfterEach
	// limpar a lista e definir o id sequencial em 0
	public void posTest() {

		DaoProdutos.limparLista();
		DaoProdutos.setIdSeq(0);

	}

	@Test
	// Testando adicionar um produto em rela��o a posi��o ao qual devem
	// ocupar na lista de produtos
	public void testAddProdutoNaListaDeProdutosPosicaoNaLista() throws EntidadeComValoresNegativoException {

		DaoProdutos.addEditDados(produtoA, null);
		DaoProdutos.addEditDados(produtoB, null);

		assertEquals(produtoA, DaoProdutos.getListaProdutos().get(0));
		assertEquals(produtoB, DaoProdutos.getListaProdutos().get(1));

	}

	@Test
	// Testando adicionar produtos utilizando o tamanho da lista como efeito de
	// compara��o
	public void testAddDadosProdutosTamanhoDaLista() throws EntidadeComValoresNegativoException {

		DaoProdutos.addEditDados(produtoA, null);
		DaoProdutos.addEditDados(produtoB, null);

		assertEquals(2, DaoProdutos.getListaProdutos().size());

	}

	@Test
	// Testando a remo��o de produtos se existem na lista de produtos
	public void testRemoverProdutoNaListaDeProdutosSeExistir() throws EntidadeComValoresNegativoException {

		DaoProdutos.addEditDados(produtoA, null);
		DaoProdutos.addEditDados(produtoB, null);
		DaoProdutos.addEditDados(produtoC, null);

		Produtos produto1 = DaoProdutos.getListaProdutos().get(0);

		DaoProdutos.removerDados("0");

		assertNotEquals(produto1, DaoProdutos.getListaProdutos().get(0));

		DaoProdutos.addEditDados(produtoD, null);

		Produtos produto2 = DaoProdutos.getListaProdutos().get(0);

		DaoProdutos.removerDados("1");

		assertNotEquals(produto2, DaoProdutos.getListaProdutos().get(0));
		Produtos produto3 = DaoProdutos.getListaProdutos().get(0);

		DaoProdutos.removerDados("2");

		assertNotEquals(produto3, DaoProdutos.getListaProdutos().get(0));
		Produtos produto4 = DaoProdutos.getListaProdutos().get(0);

		DaoProdutos.removerDados("3");

		assertFalse(DaoProdutos.getListaProdutos().contains(produto4));

	}

	@Test
	// Testando a remoção de produtos pelo tamanho para caso ele exista
	public void testRemoverProdutoNaListaDeProdutosSeExistirTamanhoDaLista()
			throws EntidadeComValoresNegativoException {

		DaoProdutos.addEditDados(produtoA, null);
		DaoProdutos.addEditDados(produtoB, null);
		DaoProdutos.addEditDados(produtoC, null);

		DaoProdutos.removerDados("0");

		assertEquals(2, DaoProdutos.getListaProdutos().size());

		DaoProdutos.removerDados("1");

		assertEquals(1, DaoProdutos.getListaProdutos().size());

		DaoProdutos.removerDados("2");

		assertEquals(0, DaoProdutos.getListaProdutos().size());

	}

	@Test
	// teste de edição de produto para caso ele existir na lista
	public void testEditarProdutoNaListaDeProdutosCasoExista() throws EntidadeComValoresNegativoException {

		DaoProdutos.addEditDados(produtoA, null);
		DaoProdutos.addEditDados(produtoB, null);
		DaoProdutos.addEditDados(produtoC, "1");

		assertEquals(produtoC, DaoProdutos.getListaProdutos().get(1));

	}

	@Test
	// teste para listar a lista de produtos
	public void testListagemDados() throws EntidadeComValoresNegativoException {

		DaoProdutos.addEditDados(produtoA, null);

		assertEquals("ProdutoA", DaoProdutos.getListaProdutos().get(0).getNome());
		assertEquals(validade1, DaoProdutos.getListaProdutos().get(0).getValidade());
		assertEquals(140, DaoProdutos.getListaProdutos().get(0).getPreco());
		assertEquals(80.3, DaoProdutos.getListaProdutos().get(0).getQtdProduto());
		assertEquals("Kg", DaoProdutos.getListaProdutos().get(0).getTipoQtd());

	}

	@Test
	// teste adicao de produto com valor negativo
	public void testAddProdutoComValorNegativoNaListaDeProdutos() throws EntidadeComValoresNegativoException {

		produtoA.setPreco(-15);
		produtoB.setQtdProduto(-20);

		assertThrows(EntidadeComValoresNegativoException.class, () -> DaoProdutos.addEditDados(produtoA, null));
		assertThrows(EntidadeComValoresNegativoException.class, () -> DaoProdutos.addEditDados(produtoB, null));

	}

	@Test
	// teste de edição de produtos com valor negativo
	public void testEditProdutosComValorNegativoNaListaDeProdutos() throws EntidadeComValoresNegativoException {

		DaoProdutos.addEditDados(produtoC, null);

		produtoA.setPreco(-15);
		produtoB.setQtdProduto(-20);

		assertThrows(EntidadeComValoresNegativoException.class, () -> DaoProdutos.addEditDados(produtoA, "0"));
		assertThrows(EntidadeComValoresNegativoException.class, () -> DaoProdutos.addEditDados(produtoB, "0"));

	}

	@Test
	// teste de estoque com saldo disponivel
	public void testAlterarEstoqueComSaldoLivre()
			throws EntidadeComValoresNegativoException, EstoqueInsuficienteException {

		DaoProdutos.addEditDados(produtoA, null);
		produtoA.setQtdProduto(20);

		DaoProdutos.reduzirEstoque("0", 15);

		assertEquals(5, produtoA.getQtdProduto());

	}

	@Test
	// teste de estoque sem saldo disponivel
	public void testAlterarEstoqueSemSaldoLivre() throws EntidadeComValoresNegativoException {

		DaoProdutos.addEditDados(produtoA, null);
		produtoA.setQtdProduto(0);

		assertThrows(EstoqueInsuficienteException.class, () -> DaoProdutos.reduzirEstoque("0", 15));

	}

}
