package applicationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import applicationexeceptions.CamposNulosException;
import applicationexeceptions.CnpjJaExisteException;
import applicationmodel.Fornecedores;
import applicationmodeldao.DaoFornecedores;
import applicationmodeldao.DaoProdutos;

public class DaoFornecedoresTest {

	Fornecedores fornecedorA;
	Fornecedores fornecedorA2;
	Fornecedores fornecedorB;
	Fornecedores fornecedorC;
	DaoFornecedores gerenciamento2;
	DaoProdutos gerenciamento1;
	ArrayList<String> idProdutosFornecedor;

	@BeforeEach
	// Inicilizando os dados necessarios aos testes
	public void init() throws CnpjJaExisteException {

		gerenciamento1 = new DaoProdutos();
		gerenciamento2 = new DaoFornecedores();
		DaoFornecedores.limparLista();
		DaoFornecedores.setIdSeq(0);

		idProdutosFornecedor = new ArrayList<String>();
		idProdutosFornecedor.add("0");
		idProdutosFornecedor.add("1");
		idProdutosFornecedor.add("2");
		fornecedorA = new Fornecedores("018.236.120/0001-58", "EmpresaATest", "Rua UEFSA", idProdutosFornecedor);
		fornecedorA2 = new Fornecedores("Sem CNPJ", "EmpresaATest", "Rua UEFSA", idProdutosFornecedor);
		fornecedorB = new Fornecedores("Sem CNPJ", "EmpresaBTest", "Rua UEFSB", idProdutosFornecedor);
		fornecedorC = new Fornecedores("019.579.305/0001-79", "EmpresaCTest", "Rua UEFSC", idProdutosFornecedor);

	}

	@AfterEach
	public void posTest() {

		DaoFornecedores.limparLista();
		DaoFornecedores.setIdSeq(0);
		DaoProdutos.limparLista();
		DaoProdutos.setIdSeq(0);

	}

	@Test
	// Testando adicionar fornecedores em relacao a posicao ao qual devem ocupar na
	// lista de fornecedores
	public void testAddDadosFornecedorPosicaoNaLista()
			throws CnpjJaExisteException, CamposNulosException {

		DaoFornecedores.addEditDados(fornecedorA, null);
		DaoFornecedores.addEditDados(fornecedorB, null);

		assertEquals(fornecedorA, DaoFornecedores.getListaFornecedores().get(0));
		assertEquals(fornecedorB, DaoFornecedores.getListaFornecedores().get(1));

	}

	@Test
	// Testando adicionar fornecedores utilizando o tamanho da lista como efeito de
	// compara��o
	public void testAddDadosFornecedorTamanhoDaLista()
			throws CnpjJaExisteException, CamposNulosException {

		DaoFornecedores.addEditDados(fornecedorA, null);
		DaoFornecedores.addEditDados(fornecedorB, null);

		assertEquals(2, DaoFornecedores.getListaFornecedores().size());

	}

	@Test
	// Testando a adi��o de fornecedores com Cnpj's id�nticos
	public void testAddDadosFornecedorComCnpjIgual() {

		gerenciamento2 = new DaoFornecedores();

		assertThrows(CnpjJaExisteException.class, () -> DaoFornecedores.addEditDados(fornecedorA, null));

	}

	@Test
	// Testando a adi��o de v�rios fornecedores que n�o possuem Cnpj
	public void testAddDadosVariosFornecedoresComCnpjIgualASemCnpj()
			throws CnpjJaExisteException, CamposNulosException {

		DaoFornecedores.addEditDados(fornecedorA2, null);
		DaoFornecedores.addEditDados(fornecedorB, null);

		assertEquals(fornecedorB, DaoFornecedores.getListaFornecedores().get(1));
		assertEquals(2, DaoFornecedores.getListaFornecedores().size());

	}

	@Test
	// Testando a remo��o de fornecedores se existem na lista de fornecedores
	public void testRemoverDadosDoFornecedorSeExistirNaLista()
		throws CnpjJaExisteException, CamposNulosException {

		DaoFornecedores.addEditDados(fornecedorA, null);
		DaoFornecedores.addEditDados(fornecedorB, null);

		Fornecedores fornecedorRemovido0 = DaoFornecedores.getListaFornecedores().get(0);
		DaoFornecedores.removerDados("0");

		assertNotEquals(fornecedorRemovido0, DaoFornecedores.getListaFornecedores().get(0));
		DaoFornecedores.addEditDados(fornecedorC, null);

		Fornecedores fornecedorRemovido1 = DaoFornecedores.getListaFornecedores().get(0);
		DaoFornecedores.removerDados("1");

		assertNotEquals(fornecedorRemovido1.getId(), DaoFornecedores.getListaFornecedores().get(0));
		Fornecedores fornecedorRemovido2 = DaoFornecedores.getListaFornecedores().get(0);

		DaoFornecedores.removerDados("2");
		assertFalse(DaoFornecedores.getListaFornecedores().contains(fornecedorRemovido2));

	}

	@Test
	// Testando a remo��o de fornecedores se existem na lista de fornecedores pelo
	// tamanho da lista
	public void testRemoverDadosDoFornecedorSeExistirTamanhoDaLista()
			throws CnpjJaExisteException, CamposNulosException {

		DaoFornecedores.addEditDados(fornecedorA, null);
		DaoFornecedores.addEditDados(fornecedorB, null);
		DaoFornecedores.addEditDados(fornecedorC, null);

		assertEquals(3, DaoFornecedores.getListaFornecedores().size());
		DaoFornecedores.removerDados("0");
		assertEquals(2, DaoFornecedores.getListaFornecedores().size());
		DaoFornecedores.removerDados("1");
		assertEquals(1, DaoFornecedores.getListaFornecedores().size());
		DaoFornecedores.removerDados("2");
		assertEquals(0, DaoFornecedores.getListaFornecedores().size());

	}

	@Test
	// Testando a edi��o de fornecedores se existem na lista de fornecedores
	public void testEditarDadosDoFornecedorSeExistirNaLista()
			throws CnpjJaExisteException, CamposNulosException {

		DaoFornecedores.addEditDados(fornecedorA, null);
		DaoFornecedores.addEditDados(fornecedorC, null);
		DaoFornecedores.addEditDados(fornecedorB, "0");

		assertEquals(fornecedorB, DaoFornecedores.getListaFornecedores().get(0));

	}

	@Test
	// Testando a listagem do dados do elemento da lista de fornecedor
	public void testListagemDados()
			throws CnpjJaExisteException, CamposNulosException {
		
		DaoFornecedores.addEditDados(fornecedorA, null);

		assertEquals("018.236.120/0001-58", DaoFornecedores.getListaFornecedores().get(0).getCnpj());
		assertEquals("EmpresaATest", DaoFornecedores.getListaFornecedores().get(0).getNome());
		assertEquals("Rua UEFSA", DaoFornecedores.getListaFornecedores().get(0).getEndereco());
		assertEquals(idProdutosFornecedor, DaoFornecedores.getListaFornecedores().get(0).getIdProdutosFornecedor());

	}
	
	
	
	

}
