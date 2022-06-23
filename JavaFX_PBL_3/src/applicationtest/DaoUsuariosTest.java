package applicationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import applicationexeceptions.LoginExistenteException;
import applicationmodel.Usuarios;
import applicationmodeldao.DaoUsuarios;

public class DaoUsuariosTest {

	Usuarios usuarioA;
	Usuarios usuarioB;
	Usuarios usuarioC;
	Usuarios usuarioD;
	DaoUsuarios gerenciamento;

	@BeforeEach
	// Inicializando os dados necess�rios anteriormente aos testes
	public void init() {

		gerenciamento = new DaoUsuarios();
		DaoUsuarios.limparLista();
		DaoUsuarios.setIdSeq(0);

		usuarioA = new Usuarios("usuarioA", "5879463", "nomeUsuarioA");
		usuarioB = new Usuarios("usuarioB", "da7848da8da", "nomeUsuarioB");
		usuarioC = new Usuarios("usuarioC", "7ei9aa9eqa7", "nomeUsuarioC");
		usuarioD = new Usuarios("usuarioD", "21vatie1", "nomeUsuarioD");

	}

	@AfterEach
	// Inicilizando os dados necess�rios aos testes
	public void posTest() {

		DaoUsuarios.limparLista();
		DaoUsuarios.setIdSeq(0);

	}

	@Test
	// Testando adicionar usuario em rela��o a posi��o ao qual devem ocupar
	// na lista de usuarios
	public void testAddUsuarioNaListaDeUsuariosPosicaoNaLista() throws LoginExistenteException {

		DaoUsuarios.addEditDados(usuarioA, null);
		DaoUsuarios.addEditDados(usuarioB, null);

		assertEquals(usuarioA, DaoUsuarios.getListaUsuarios().get(0));
		assertEquals(usuarioB, DaoUsuarios.getListaUsuarios().get(1));

	}

	@Test
	// Testando adicionar usuarios utilizando o tamanho da lista como efeito de
	// compara��o
	public void testAddDadosUsuariosTamanhoDaLista() throws LoginExistenteException {

		DaoUsuarios.addEditDados(usuarioA, null);
		DaoUsuarios.addEditDados(usuarioB, null);

		assertEquals(2, DaoUsuarios.getListaUsuarios().size());

	}

	@Test
	// Testando a adi��o de usuarios com logins id�nticos
	public void testAddDadosUsuarioComLoginIgual() throws LoginExistenteException {

		DaoUsuarios.addEditDados(usuarioA, null);

		assertThrows(LoginExistenteException.class, () -> DaoUsuarios.addEditDados(usuarioA, null));
		assertEquals(1, DaoUsuarios.getListaUsuarios().size());

	}

	@Test
	// Testando a remoção de usuarios se existem na lista de usuarios, usando como
	// base o objeto atual e o objeto antigo
	public void testRemoverUsuarioNaListaDeusuariosSeExistir() throws LoginExistenteException {

		DaoUsuarios.addEditDados(usuarioA, null);
		DaoUsuarios.addEditDados(usuarioB, null);
		DaoUsuarios.addEditDados(usuarioC, null);

		Usuarios usuario1 = DaoUsuarios.getListaUsuarios().get(0);
		DaoUsuarios.removerDados("0");
		assertNotEquals(usuario1, DaoUsuarios.getListaUsuarios().get(0));
		DaoUsuarios.addEditDados(usuarioD, null);
		Usuarios usuario2 = DaoUsuarios.getListaUsuarios().get(0);
		DaoUsuarios.removerDados("1");
		assertNotEquals(usuario2, DaoUsuarios.getListaUsuarios().get(0));
		Usuarios usuario3 = DaoUsuarios.getListaUsuarios().get(0);
		DaoUsuarios.removerDados("2");
		assertNotEquals(usuario3, DaoUsuarios.getListaUsuarios().get(0));
		Usuarios usuario4 = DaoUsuarios.getListaUsuarios().get(0);
		DaoUsuarios.removerDados("3");
		assertFalse(DaoUsuarios.getListaUsuarios().contains(usuario4));

	}

	@Test
	// Testando a busca de usuarios na lista se o usuario existe
	public void testBuscaUsuariosSeExiste() throws LoginExistenteException {

		DaoUsuarios.addEditDados(usuarioA, null);
		assertEquals(usuarioA, DaoUsuarios.buscaUsuarioLS("usuarioA", "5879463"));

	}

	@Test
	// Testando a busca de usuarios na lista se o usuario nao existe
	public void testBuscaUsuariosSeNaoExiste() throws LoginExistenteException {

		DaoUsuarios.addEditDados(usuarioA, null);

		assertEquals(null, DaoUsuarios.buscaUsuarioLS("usuario58", "5879463"));

	}

	@Test
	// Testando a remo��o de usuarios se existem na lista de usuarios pelo
	// tamanho da lista
	public void testRemoverUsuarioNaListaDeUsuariosSeExistirTamanhoDaLista() throws LoginExistenteException {

		DaoUsuarios.addEditDados(usuarioA, null);
		DaoUsuarios.addEditDados(usuarioB, null);
		DaoUsuarios.addEditDados(usuarioC, null);

		DaoUsuarios.removerDados("0");
		assertEquals(2, DaoUsuarios.getListaUsuarios().size());
		DaoUsuarios.removerDados("1");
		assertEquals(1, DaoUsuarios.getListaUsuarios().size());
		DaoUsuarios.removerDados("2");
		assertEquals(0, DaoUsuarios.getListaUsuarios().size());

	}

	@Test
	// Testando a edi��o de usuarios se existem na lista de usuarios
	public void testEditarUsuarioNaListaDeusuariosCasoExista() throws LoginExistenteException {

		DaoUsuarios.addEditDados(usuarioA, null);
		DaoUsuarios.addEditDados(usuarioB, null);
		DaoUsuarios.addEditDados(usuarioC, "1");

		assertEquals(usuarioC, DaoUsuarios.getListaUsuarios().get(1));

	}

	@Test
	// Testando a listagem do dados do elemento da lista de usuario
	public void testListagemDados() throws LoginExistenteException {

		DaoUsuarios.addEditDados(usuarioA, null);

		assertEquals("nomeUsuarioA", DaoUsuarios.getListaUsuarios().get(0).getNomeUsuario());
		assertEquals("usuarioA", DaoUsuarios.getListaUsuarios().get(0).getLoginUsuario());
		assertEquals("5879463", DaoUsuarios.getListaUsuarios().get(0).getSenhaUsuario());

	}

	@Test
	// Testando a edicao de usuarios com login existente na lista de usuarios
	public void testEditarUsuarioNaListaDeUsuariosCasoExistaComLoginIgual() throws LoginExistenteException {

		DaoUsuarios.addEditDados(usuarioA, null);
		DaoUsuarios.addEditDados(usuarioB, null);
		DaoUsuarios.addEditDados(usuarioC, null);

		Usuarios copiaUsuarioB = new Usuarios("usuarioB", "da7848da8da", "nomeUsuarioB");

		assertThrows(LoginExistenteException.class, () -> DaoUsuarios.addEditDados(copiaUsuarioB, "0"));

	}

}
