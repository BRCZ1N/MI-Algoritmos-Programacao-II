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
import applicationmodel.Ingredientes;
import applicationmodel.Pratos;
import applicationmodeldao.DaoPratos;
import applicationmodeldao.DaoProdutos;

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
	public void testAddPratoPosicaoNaLista() throws EntidadeComValoresNegativoException {

		DaoPratos.addEditDados(pratoA, null);
		DaoPratos.addEditDados(pratoB, null);

		assertEquals(pratoA, DaoPratos.getListaPratos().get(0));
		assertEquals(pratoB, DaoPratos.getListaPratos().get(1));

	}

	@Test
	// Testando adicionar um prato com pre�o negativo
	public void testAddPratoComPrecoNegativoNaLista() throws EntidadeComValoresNegativoException {

		pratoA.setPreco(-15);
		assertThrows(EntidadeComValoresNegativoException.class, () -> DaoPratos.addEditDados(pratoA, null));

	}

	@Test
	// Testando editar um prato utilizando um preco negativo
	public void testEditPratoComPrecoNegativoNaLista() throws EntidadeComValoresNegativoException {

		DaoPratos.addEditDados(pratoB, null);
		pratoB.setPreco(-15);
		assertThrows(EntidadeComValoresNegativoException.class, () -> DaoPratos.addEditDados(pratoB, "0"));

	}

	@Test
	// Testando adicionar pratos utilizando o tamanho da lista como efeito de
	// compara��o
	public void testAddDadosPratosTamanhoLista() throws EntidadeComValoresNegativoException {

		DaoPratos.addEditDados(pratoA, null);
		DaoPratos.addEditDados(pratoB, null);

		assertEquals(2, DaoPratos.getListaPratos().size());

	}

	@Test
	// Testando a remo��o de prato se existem na lista de pratos
	public void removerPratoDaListaDePratosSeOPratoExistirNalista() throws EntidadeComValoresNegativoException {

		DaoPratos.addEditDados(pratoA, null);
		DaoPratos.addEditDados(pratoB, null);

		Pratos prato0 = DaoPratos.getListaPratos().get(0);

		DaoPratos.removerDados("0");

		assertNotEquals(prato0, DaoPratos.getListaPratos().get(0));

		DaoPratos.addEditDados(pratoC, null);

		Pratos prato1 = DaoPratos.getListaPratos().get(0);

		DaoPratos.removerDados("1");

		assertNotEquals(prato1, DaoPratos.getListaPratos().get(0));
		Pratos prato2 = DaoPratos.getListaPratos().get(0);

		DaoPratos.removerDados("2");

		assertFalse(DaoPratos.getListaPratos().contains(prato2));

	}

	@Test
	// teste de remoção de pratos pelo tamanho para caso ele exista
	public void testRemoverDadosDoPratoSeExistirPeloTamanho() throws EntidadeComValoresNegativoException {

		DaoPratos.addEditDados(pratoA, null);
		DaoPratos.addEditDados(pratoB, null);
		DaoPratos.addEditDados(pratoC, null);

		DaoPratos.removerDados("0");
		assertEquals(2, DaoPratos.getListaPratos().size());

		DaoPratos.removerDados("1");
		assertEquals(1, DaoPratos.getListaPratos().size());

		DaoPratos.removerDados("2");
		assertEquals(0, DaoPratos.getListaPratos().size());

	}

	@Test
	// teste de edição de prato para caso ele existir na lista
	public void testEditarDadosDoPratoSeExistirNaLista() throws EntidadeComValoresNegativoException {

		DaoPratos.addEditDados(pratoA, null);
		DaoPratos.addEditDados(pratoB, "0");
		DaoPratos.addEditDados(pratoC, null);

		assertEquals(pratoB, DaoPratos.getListaPratos().get(0));

	}

	@Test
	// teste para listar a lista de pratos
	public void testListagemDados() throws EntidadeComValoresNegativoException {

		DaoPratos.addEditDados(pratoA, null);

		assertEquals("pratoA", DaoPratos.getListaPratos().get(0).getNome());
		assertEquals("descricaoA", DaoPratos.getListaPratos().get(0).getDescricao());
		assertEquals(40, DaoPratos.getListaPratos().get(0).getPreco());
		assertEquals("categoriaA", DaoPratos.getListaPratos().get(0).getCategoria());
		assertEquals(composicaoPrato, DaoPratos.getListaPratos().get(0).getComposicaoPrato());

	}

}