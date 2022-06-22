package applicationtest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Calendar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pblversaofinal.DaoProdutos;
import pblversaofinal.Produtos;
import pblversaofinal.execeptions.EntidadeComValoresNegativoException;
import pblversaofinal.execeptions.EstoqueInsuficienteException;
import pblversaofinal.execeptions.IdInvalidoException;

public class DaoProdutosTest {

	Produtos produtoA;
	Produtos produtoB;
	Produtos produtoC;
	Produtos produtoD;
	DaoProdutos gerenciamento;
	Calendar validade1 = Calendar.getInstance();
	Calendar validade2 = Calendar.getInstance();
	Calendar validade3 = Calendar.getInstance();
	Calendar validade4 = Calendar.getInstance();

	@BeforeEach
	public void init() {

		gerenciamento = new DaoProdutos();
		DaoProdutos.limparLista();
		DaoProdutos.setIdSeq(0);

		validade1 = Calendar.getInstance();
		validade2 = Calendar.getInstance();
		validade3 = Calendar.getInstance();
		validade4 = Calendar.getInstance();

		validade1.set(Calendar.DAY_OF_MONTH, 10);
		validade1.set(Calendar.MONTH, 9);
		validade1.set(Calendar.YEAR, 2022);

		validade2.set(Calendar.DAY_OF_MONTH, 20);
		validade2.set(Calendar.MONTH, 7);
		validade3.set(Calendar.YEAR, 2022);

		validade3.set(Calendar.DAY_OF_MONTH, 30);
		validade3.set(Calendar.MONTH, 12);
		validade3.set(Calendar.YEAR, 2022);

		validade4.set(Calendar.DAY_OF_MONTH, 7);
		validade4.set(Calendar.MONTH, 4);
		validade4.set(Calendar.YEAR, 2022);

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
	public void testAddProdutoNaListaDeProdutosPosicaoNaLista() throws EntidadeComValoresNegativoException, IdInvalidoException {

		gerenciamento.addEditDados(produtoA, null);
		gerenciamento.addEditDados(produtoB, null);

		assertEquals(produtoA, DaoProdutos.getListaProdutos().get(0));
		assertEquals(produtoB, DaoProdutos.getListaProdutos().get(1));

	}

	@Test
	// Testando adicionar produtos utilizando o tamanho da lista como efeito de
	// compara��o
	public void testAddDadosProdutosTamanhoDaLista() throws EntidadeComValoresNegativoException, IdInvalidoException {

		gerenciamento.addEditDados(produtoA, null);
		gerenciamento.addEditDados(produtoB, null);

		assertEquals(2, DaoProdutos.getListaProdutos().size());

	}

	@Test
	// Testando a remo��o de produtos se existem na lista de produtos
	public void testRemoverProdutoNaListaDeProdutosSeExistir() throws EntidadeComValoresNegativoException, IdInvalidoException {

		gerenciamento.addEditDados(produtoA, null);
		gerenciamento.addEditDados(produtoB, null);
		gerenciamento.addEditDados(produtoC, null);

		Produtos produto1 = DaoProdutos.getListaProdutos().get(0);

		gerenciamento.removerDados("0");

		assertNotEquals(produto1, DaoProdutos.getListaProdutos().get(0));

		gerenciamento.addEditDados(produtoD, null);

		Produtos produto2 = DaoProdutos.getListaProdutos().get(0);

		gerenciamento.removerDados("1");

		assertNotEquals(produto2, DaoProdutos.getListaProdutos().get(0));
		Produtos produto3 = DaoProdutos.getListaProdutos().get(0);

		gerenciamento.removerDados("2");

		assertNotEquals(produto3, DaoProdutos.getListaProdutos().get(0));
		Produtos produto4 = DaoProdutos.getListaProdutos().get(0);

		gerenciamento.removerDados("3");

		assertFalse(DaoProdutos.getListaProdutos().contains(produto4));

	}

	@Test
	// Testando a remoção de produtos pelo tamanho para caso ele exista
	public void testRemoverProdutoNaListaDeProdutosSeExistirTamanhoDaLista() throws EntidadeComValoresNegativoException, IdInvalidoException {

		gerenciamento.addEditDados(produtoA, null);
		gerenciamento.addEditDados(produtoB, null);
		gerenciamento.addEditDados(produtoC, null);

		gerenciamento.removerDados("0");

		assertEquals(2, DaoProdutos.getListaProdutos().size());

		gerenciamento.removerDados("1");

		assertEquals(1, DaoProdutos.getListaProdutos().size());

		gerenciamento.removerDados("2");

		assertEquals(0, DaoProdutos.getListaProdutos().size());

	}

	@Test
	// teste de remoção para caso o produto não exista na lista
	public void testRemoverProdutoNaListaDeProdutosSeNaoExistir() throws EntidadeComValoresNegativoException, IdInvalidoException {

		gerenciamento.addEditDados(produtoA, null);
		gerenciamento.addEditDados(produtoB, null);

		assertThrows(IdInvalidoException.class,() -> gerenciamento.removerDados("2"));
		assertEquals(2, DaoProdutos.getListaProdutos().size());

	}

	@Test
	// teste de edição de produto para caso ele existir na lista
	public void testEditarProdutoNaListaDeProdutosCasoExista() throws EntidadeComValoresNegativoException, IdInvalidoException {

		gerenciamento.addEditDados(produtoA, null);
		gerenciamento.addEditDados(produtoB, null);
		gerenciamento.addEditDados(produtoC, "1");
	
		assertEquals(produtoC, DaoProdutos.getListaProdutos().get(1));

	}

	@Test
	// teste de edição de produtos na lista para caso ele não exista
	public void testEditarProdutoNaListaDeProdutosCasoNaoExista() throws EntidadeComValoresNegativoException, IdInvalidoException {

		gerenciamento.addEditDados(produtoA, null);
		gerenciamento.addEditDados(produtoC, null);
		

		assertThrows(IdInvalidoException.class,() -> gerenciamento.addEditDados(produtoB, "3"));
		assertEquals(produtoC, DaoProdutos.getListaProdutos().get(1));

	}

	@Test
	// teste para listar a lista de produtos
	public void testListagemDados() throws EntidadeComValoresNegativoException, IdInvalidoException {

		gerenciamento.addEditDados(produtoA, null);

		assertEquals("ProdutoA", DaoProdutos.getListaProdutos().get(0).getNome());
		assertEquals(validade1, DaoProdutos.getListaProdutos().get(0).getValidade());
		assertEquals(140, DaoProdutos.getListaProdutos().get(0).getPreco());
		assertEquals(80.3, DaoProdutos.getListaProdutos().get(0).getQtdProduto());
		assertEquals("Kg", DaoProdutos.getListaProdutos().get(0).getTipoQtd());

	}
	
	@Test
	// teste adicao de produto com valor negativo
	public void testAddProdutoComValorNegativoNaListaDeProdutos() throws EntidadeComValoresNegativoException, IdInvalidoException {

		produtoA.setPreco(-15);
		produtoB.setQtdProduto(-20);
		
		assertThrows(EntidadeComValoresNegativoException.class,() -> gerenciamento.addEditDados(produtoA, null));
		assertThrows(EntidadeComValoresNegativoException.class,() -> gerenciamento.addEditDados(produtoB, null));

	}
	
	@Test
	// teste de edição de produtos com valor negativo
	public void testEditProdutosComValorNegativoNaListaDeProdutos() throws EntidadeComValoresNegativoException, IdInvalidoException {

		
		gerenciamento.addEditDados(produtoC, null);
		
		produtoA.setPreco(-15);
		produtoB.setQtdProduto(-20);
		
		assertThrows(EntidadeComValoresNegativoException.class,() -> gerenciamento.addEditDados(produtoA, "0"));
		assertThrows(EntidadeComValoresNegativoException.class,() -> gerenciamento.addEditDados(produtoB, "0"));

	}
	
	@Test
	// teste de estoque com saldo disponivel
	public void testAlterarEstoqueComSaldoLivre() throws EntidadeComValoresNegativoException, IdInvalidoException, EstoqueInsuficienteException {

		
		gerenciamento.addEditDados(produtoA, null);
		produtoA.setQtdProduto(20);
		
		DaoProdutos.reduzirEstoque("0", 15);
		
		assertEquals(5,produtoA.getQtdProduto());

	}
	
	
	@Test
	// teste de estoque sem saldo disponivel
	public void testAlterarEstoqueSemSaldoLivre() throws EntidadeComValoresNegativoException, IdInvalidoException {

		
		gerenciamento.addEditDados(produtoA, null);
		produtoA.setQtdProduto(0);
		
		assertThrows(EstoqueInsuficienteException.class,() -> DaoProdutos.reduzirEstoque("0", 15));
		

	}
	
	
	
	

}
