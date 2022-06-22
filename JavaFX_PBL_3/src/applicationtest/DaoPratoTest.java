package applicationtest;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pblversaofinal.Pratos;
import pblversaofinal.execeptions.EntidadeComValoresNegativoException;
import pblversaofinal.execeptions.IdInvalidoException;
import pblversaofinal.execeptions.PratoComProdutoInvalidoException;
import pblversaofinal.DaoPratos;
import pblversaofinal.DaoProdutos;
import pblversaofinal.Ingredientes;

public class DaoPratoTest {

	Pratos pratoA;
	Pratos pratoB;
	Pratos pratoC;
	DaoPratos gerenciamento;
	DaoProdutos gerenciamento1;
	ArrayList<Ingredientes> composicaoPrato;

	@BeforeEach
	// Inicilizando os dados necess�rios aos testes anteriormente
	public void init() {

		gerenciamento1 = new DaoProdutos();
		gerenciamento = new DaoPratos();
		DaoPratos.limparLista();
		DaoPratos.setIdSeq(0);
		

		Ingredientes ingrediente1 = new Ingredientes("0", 10.74);
		Ingredientes ingrediente2 = new Ingredientes("1", 3.30);
		Ingredientes ingrediente3 = new Ingredientes("2", 7.5);

		composicaoPrato = new ArrayList<Ingredientes>();
		composicaoPrato.add(ingrediente1);
		composicaoPrato.add(ingrediente2);
		composicaoPrato.add(ingrediente3);

		pratoA = new Pratos("pratoA", "descricaoA", 40, "categoriaA", composicaoPrato);
		pratoB = new Pratos("pratoB", "descricaoB", 150, "categoriaB", composicaoPrato);
		pratoC = new Pratos("pratoC", "descricaoC", 89, "categoriaC", composicaoPrato);

	}

	@AfterEach
	// limpar a lista e definir o a sequencia do id pra 0 para reiniciar o processo
	public void posTest() {

		DaoPratos.limparLista();
		DaoPratos.setIdSeq(0);
		DaoProdutos.limparLista();
		DaoProdutos.setIdSeq(0);
		
		
	}

	@Test
	// Testando adicionar um prato em rela��o a posi��o ao qual devem ocupar
	// na lista de pratos
	public void testAddPratoPosicaoNaLista()
			throws EntidadeComValoresNegativoException, IdInvalidoException, PratoComProdutoInvalidoException {
		
		gerenciamento.addEditDados(pratoA, null);
		gerenciamento.addEditDados(pratoB, null);

		assertEquals(pratoA, DaoPratos.getListaPratos().get(0));
		assertEquals(pratoB, DaoPratos.getListaPratos().get(1));

	}
	
	@Test
	//Testando adicionar um prato com pre�o negativo
	public void testAddPratoComPrecoNegativoNaLista()
			throws EntidadeComValoresNegativoException, IdInvalidoException, PratoComProdutoInvalidoException {
		
		pratoA.setPreco(-15);
		assertThrows(EntidadeComValoresNegativoException.class,() -> gerenciamento.addEditDados(pratoA, null));

	}
	
	@Test
	//Testando adicionar um prato com produto invalido
	public void testAddPratoComProdutoInvalidoNaLista()
			throws EntidadeComValoresNegativoException, IdInvalidoException, PratoComProdutoInvalidoException {
		
		ArrayList<Ingredientes> listaProd = pratoA.getComposicaoPrato();
		Ingredientes ingrediente = new Ingredientes("30",30);
		listaProd.add(ingrediente);
		pratoA.setComposicaoPrato(listaProd);
		
		assertThrows(PratoComProdutoInvalidoException.class,() -> gerenciamento.addEditDados(pratoA, null));

	}
	
	@Test
	//Testando editar um prato utilizando um preco negativo
	public void testEditPratoComPrecoNegativoNaLista()throws EntidadeComValoresNegativoException, IdInvalidoException, PratoComProdutoInvalidoException {
		
		gerenciamento.addEditDados(pratoB, null);
		pratoB.setPreco(-15);
		assertThrows(EntidadeComValoresNegativoException.class,() -> gerenciamento.addEditDados(pratoB, "0"));

	}
	
	@Test
	//Testando editar prato com produto invalido
	public void testEditPratoComProdutoInvalidoNaLista()
			throws EntidadeComValoresNegativoException, IdInvalidoException, PratoComProdutoInvalidoException {
		
		gerenciamento.addEditDados(pratoB, null);
		ArrayList<Ingredientes> listaProd = pratoA.getComposicaoPrato();
		Ingredientes ingrediente = new Ingredientes("5",30);
		listaProd.add(ingrediente);
		pratoA.setComposicaoPrato(listaProd);
		

		assertThrows(PratoComProdutoInvalidoException.class,() -> gerenciamento.addEditDados(pratoA, "0"));

	}
	

	@Test
	// Testando adicionar pratos utilizando o tamanho da lista como efeito de
	// compara��o
	public void testAddDadosPratosTamanhoLista()
			throws EntidadeComValoresNegativoException, IdInvalidoException, PratoComProdutoInvalidoException {

		gerenciamento.addEditDados(pratoA, null);
		gerenciamento.addEditDados(pratoB, null);

		assertEquals(2, DaoPratos.getListaPratos().size());

	}


	@Test
	// Testando a remo��o de prato se existem na lista de pratos
	public void removerPratoDaListaDePratosSeOPratoExistirNalista()
			throws EntidadeComValoresNegativoException, IdInvalidoException, PratoComProdutoInvalidoException {

		gerenciamento.addEditDados(pratoA, null);
		gerenciamento.addEditDados(pratoB, null);

		Pratos prato0 = DaoPratos.getListaPratos().get(0);

		gerenciamento.removerDados("0");

		assertNotEquals(prato0, DaoPratos.getListaPratos().get(0));

		gerenciamento.addEditDados(pratoC, null);

		Pratos prato1 = DaoPratos.getListaPratos().get(0);

		gerenciamento.removerDados("1");

		assertNotEquals(prato1, DaoPratos.getListaPratos().get(0));
		Pratos prato2 = DaoPratos.getListaPratos().get(0);

		gerenciamento.removerDados("2");
		
		assertFalse(DaoPratos.getListaPratos().contains(prato2));

	}

	@Test
	// teste de remoção de pratos pelo tamanho para caso ele exista
	public void testRemoverDadosDoPratoSeExistirPeloTamanho()
			throws EntidadeComValoresNegativoException, IdInvalidoException, PratoComProdutoInvalidoException {

		gerenciamento.addEditDados(pratoA, null);
		gerenciamento.addEditDados(pratoB, null);
		gerenciamento.addEditDados(pratoC, null);

		gerenciamento.removerDados("0");
		assertEquals(2, DaoPratos.getListaPratos().size());

		gerenciamento.removerDados("1");
		assertEquals(1, DaoPratos.getListaPratos().size());

		gerenciamento.removerDados("2");
		assertEquals(0, DaoPratos.getListaPratos().size());

	}

	@Test
	// teste de remoção para caso o prato não exista na lista
	public void testRemoverDadosDoPratoSeNaoExistir()
			throws EntidadeComValoresNegativoException, IdInvalidoException, PratoComProdutoInvalidoException {

		gerenciamento.addEditDados(pratoA, null);
		gerenciamento.addEditDados(pratoB, null);
		
		assertThrows(IdInvalidoException.class,() ->gerenciamento.removerDados("2"));
		assertEquals(2, DaoPratos.getListaPratos().size());

	}

	@Test
	// teste de edição de prato para caso ele existir na lista
	public void testEditarDadosDoPratoSeExistirNaLista()
			throws EntidadeComValoresNegativoException, IdInvalidoException, PratoComProdutoInvalidoException {

		gerenciamento.addEditDados(pratoA, null);
		gerenciamento.addEditDados(pratoB, "0");
		gerenciamento.addEditDados(pratoC, null);

		assertEquals(pratoB, DaoPratos.getListaPratos().get(0));

	}

	@Test
	// teste de edição de pratos na lista para caso ele não exista
	public void testEditarDadosDoPratoSeNaoExistirNaLista()
			throws EntidadeComValoresNegativoException, IdInvalidoException, PratoComProdutoInvalidoException {

	
		
		gerenciamento.addEditDados(pratoA, null);
		gerenciamento.addEditDados(pratoC, null);
		
		assertThrows(IdInvalidoException.class, () -> gerenciamento.addEditDados(pratoB, "3"));

	}

	@Test
	// teste para listar a lista de pratos
	public void testListagemDados()
			throws EntidadeComValoresNegativoException, IdInvalidoException, PratoComProdutoInvalidoException {

		gerenciamento.addEditDados(pratoA, null);

		assertEquals("pratoA", DaoPratos.getListaPratos().get(0).getNome());
		assertEquals("descricaoA", DaoPratos.getListaPratos().get(0).getDescricao());
		assertEquals(40, DaoPratos.getListaPratos().get(0).getPreco());
		assertEquals("categoriaA", DaoPratos.getListaPratos().get(0).getCategoria());
		assertEquals(composicaoPrato, DaoPratos.getListaPratos().get(0).getComposicaoPrato());

	}
	
	
	
}