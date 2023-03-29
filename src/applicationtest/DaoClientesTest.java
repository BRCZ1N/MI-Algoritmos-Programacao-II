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
import applicationexeceptions.CpfJaExisteException;
import applicationmodel.Clientes;
import applicationmodeldao.DaoClientes;
import applicationmodeldao.DaoPratos;
import applicationmodeldao.DaoProdutos;
import applicationmodeldao.DaoVendas;

public class DaoClientesTest {

	Clientes clienteA;
	Clientes clienteB;
	Clientes clienteC;
	DaoVendas gerenciamento;
	DaoPratos gerenciamento2;
	DaoProdutos gerenciamento3;
	DaoClientes gerenciamento4;
	ArrayList<String> listaCompras;

	@BeforeEach
	// Inicilizando os dados necess�rios aos testes
	public void init() {

		gerenciamento3 = new DaoProdutos();
		gerenciamento2 = new DaoPratos();
		gerenciamento = new DaoVendas();
		DaoClientes.limparLista();
		DaoClientes.setIdSeq(0);

		listaCompras = new ArrayList<String>();

		listaCompras.add("0");
		listaCompras.add("1");

		clienteA = new Clientes("Robenilson", "018.236.120/0001-58", "RobenilsonPatriota@yahoo.com", "4002-8922",listaCompras);
		clienteB = new Clientes("Carlos", "018.232.120/0001-58", "robinPat@yahoo.com", "4002-8922", listaCompras);
		clienteC = new Clientes("Claudinho", "018.231.120/0001-58", "Cocada@yahoo.com", "4002-8922", listaCompras);

	}

	@AfterEach
	// limpar a lista e definir o a sequencia do id pra 0 para reiniciar o processo
	public void posTest() {

		DaoClientes.limparLista();
		DaoClientes.setIdSeq(0);
		DaoVendas.limparLista();
		DaoVendas.setIdSeq(0);
		DaoPratos.limparLista();
		DaoPratos.setIdSeq(0);
		DaoProdutos.limparLista();
		DaoProdutos.setIdSeq(0);

	}

	@Test
	// Testando adicionar venda em rela��o a posi��o ao qual devem ocupar na
	// lista de clientes
	public void testaddClientePosicaoNaLista() throws CpfJaExisteException, CamposNulosException {

		DaoClientes.addEditDados(clienteA, null);
		DaoClientes.addEditDados(clienteB, null);

		assertEquals(clienteA, DaoClientes.getListaClientes().get(0));
		assertEquals(clienteB, DaoClientes.getListaClientes().get(1));

	}

	@Test
	// Testando adicionar vendas em rela��o a posi��o ao qual devem ocupar
	// na lista de clientes
	public void testAddClienteTamanhoDaLista() throws CpfJaExisteException, CamposNulosException {

		DaoClientes.addEditDados(clienteA, null);
		DaoClientes.addEditDados(clienteB, null);

		assertEquals(2, DaoClientes.getListaClientes().size());

	}

	@Test
	// Testando a remoção de vendas se existem na lista de clientes, usando como
	// base o objeto atual e o objeto antigo
	public void testRemoverClienteSeExistirNaLista() throws CpfJaExisteException, CamposNulosException {

		DaoClientes.addEditDados(clienteA, null);
		DaoClientes.addEditDados(clienteB, null);

		Clientes clienteRemovido0 = DaoClientes.getListaClientes().get(0);
		DaoClientes.removerDados("0");
		assertNotEquals(clienteRemovido0, DaoClientes.getListaClientes().get(0));
		DaoClientes.addEditDados(clienteC, null);
		Clientes clienteRemovido1 = DaoClientes.getListaClientes().get(0);
		DaoClientes.removerDados("1");
		assertNotEquals(clienteRemovido1.getId(), DaoClientes.getListaClientes().get(0));
		Clientes clienteRemovido2 = DaoClientes.getListaClientes().get(0);
		DaoClientes.removerDados("2");
		assertFalse(DaoClientes.getListaClientes().contains(clienteRemovido2));

	}

	@Test
	// Testando a remo��o de vendas se existem na lista de clientes pelo tamanho
	// da lista
	public void testRemoverClienteSeExistirTamanhoDaLista() throws CpfJaExisteException, CamposNulosException {

		DaoClientes.addEditDados(clienteA, null);
		DaoClientes.addEditDados(clienteB, null);
		DaoClientes.addEditDados(clienteC, null);

		DaoClientes.removerDados("0");
		assertEquals(2, DaoClientes.getListaClientes().size());
		DaoClientes.removerDados("1");
		assertEquals(1, DaoClientes.getListaClientes().size());
		DaoClientes.removerDados("2");
		assertEquals(0, DaoClientes.getListaClientes().size());

	}

	@Test
	// Testando a edi��o de clientes se existem na lista de clientes
	public void testEditarClienteNaListaDeClientesCasoExista() throws CpfJaExisteException, CamposNulosException {

		DaoClientes.addEditDados(clienteA, null);
		DaoClientes.addEditDados(clienteB, null);
		DaoClientes.addEditDados(clienteC, "1");

		assertEquals(clienteC, DaoClientes.getListaClientes().get(1));

	}
	
	
	@Test
	// Testando a edi��o de cliente se existir na lista de clientes com cpf igual
	public void testAddClienteNaListaDeClientesComCpfIgual() throws CpfJaExisteException, CamposNulosException {

		DaoClientes.addEditDados(clienteA, null);
		
		assertThrows(CpfJaExisteException.class, () -> DaoClientes.addEditDados(clienteA, null));

	}
	
	@Test
	// Testando a edi��o de clientes se existem na lista de clientes com cpf igual
	public void testEditClienteNaListaDeClientesComCpfIgual() throws CpfJaExisteException, CamposNulosException {

		DaoClientes.addEditDados(clienteA, null);
		DaoClientes.addEditDados(clienteB, null);

		assertThrows(CpfJaExisteException.class, () -> DaoClientes.addEditDados(clienteB, "0"));
		
	}

	@Test
	// Testando a listagem do dados do elemento da lista de clientes
	public void testListagemDados() throws CpfJaExisteException, CamposNulosException {

		DaoClientes.addEditDados(clienteA, null);

		assertEquals(clienteA.getIdHistoricoCompras(), DaoClientes.getListaClientes().get(0).getIdHistoricoCompras());
		assertEquals(clienteA.getCpf(), DaoClientes.getListaClientes().get(0).getCpf());
		assertEquals(clienteA.getEmail(), DaoClientes.getListaClientes().get(0).getEmail());
		assertEquals(clienteA.getId(), DaoClientes.getListaClientes().get(0).getId());
		assertEquals(clienteA.getNome(), DaoClientes.getListaClientes().get(0).getNome());
		assertEquals(clienteA.getTelefone(), DaoClientes.getListaClientes().get(0).getTelefone());

	}

}
