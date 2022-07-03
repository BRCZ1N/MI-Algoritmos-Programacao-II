package applicationmodeldao;

import java.util.ArrayList;

import applicationexeceptions.CamposNulosException;
import applicationexeceptions.LoginExistenteException;
import applicationmodel.Funcionario;
import applicationmodel.Gerente;
import applicationmodel.Usuarios;

/**
 * Classe para gerenciamento de objetos do tipo Usuarios.
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @author Alex da Fonseca Dantas Junior
 * @version 1.0
 * @since 2022
 */

public class DaoUsuarios {

	private static ArrayList<Usuarios> listaUsuarios = new ArrayList<Usuarios>();
	private static int idSeq = 0;

	/**
	 * Construtor para popular a estrutura de dados referente a usuarios no menu.
	 * 
	 * @throws CamposNulosException
	 */

	public DaoUsuarios() {

		Gerente gerente = new Gerente("ADMIN", "ADMIN", "ADMIN");
		Funcionario funcionario = new Funcionario("ogueloo", "1234", "Alex Junior");
		Funcionario funcionarioB = new Funcionario("brcz", "789321", "Brn");

		try {
			addEditDados(gerente, null);
			addEditDados(funcionario, null);
			addEditDados(funcionarioB, null);
		} catch (LoginExistenteException | CamposNulosException e) {

			e.getMessage();
		}

	}

	/**
	 * Metododo para obter o retorno do id sequencial
	 * 
	 * @return int idSeq - id sequencial
	 */
	public static int getIdSeq() {
		return idSeq;
	}

	/**
	 * Metodo para setar um id sequencial
	 * 
	 * @param idSeq int - id sequencial
	 */
	public static void setIdSeq(int idSeq) {
		DaoUsuarios.idSeq = idSeq;
	}

	/**
	 * Metodo para retorno de uma lista de Usuarios.
	 * 
	 * @return Arraylist<Usuarios> listaUsuarios
	 */
	public static ArrayList<Usuarios> getListaUsuarios() {
		return listaUsuarios;
	}

	/**
	 * Metodo para setar uma lista de Usuarios.
	 * 
	 * @param listaUsuarios Arraylist<Usuarios>
	 */
	public void setListaUsuarios(ArrayList<Usuarios> listaUsuarios) {
		DaoUsuarios.listaUsuarios = listaUsuarios;
	}

	/**
	 * Metodo para acessar o m�todo de editar caso exista um usu�rio a ser editado,
	 * ou ent�o para adicionar um novo usu�rio.
	 * 
	 * @param usuario Usuarios
	 * @param chaveId String
	 * @throws IdInvalidoException
	 * @throws LoginExistenteException
	 * @throws CamposNulosException
	 */

	public static void addEditDados(Usuarios usuario, String chaveId)
			throws LoginExistenteException, CamposNulosException {

		if (chaveId == null) {

			addDados(usuario);

		} else {

			editarDados(usuario, chaveId);

		}

	}

	/**
	 * M�todo para adicionar um usu�rio na lista de usu�rios
	 * 
	 * @param usuario Usuarios- Objeto do tipo Usuarios
	 * @throws LoginExistenteException
	 * @throws CamposNulosException
	 */

	private static void addDados(Usuarios usuario) throws LoginExistenteException, CamposNulosException {

		boolean loginExiste = buscarLogin(0, listaUsuarios.size() - 1, usuario.getLoginUsuario());
		if (!loginExiste && !usuarioCampoVazio(usuario)) {

			usuario.setId(Integer.toString(idSeq));
			listaUsuarios.add(usuario);
			idSeq++;

		} else if (loginExiste) {

			throw new LoginExistenteException();

		} else {

			throw new CamposNulosException();

		}

	}

	/**
	 * M�todo para remover um usu�rio na lista de usu�rios.
	 * 
	 * @param chaveId String - Id para remover
	 * @throws IdInvalidoException
	 * 
	 */

	public static void removerDados(String chaveId) {

		int idExiste = buscarDado(0, listaUsuarios.size() - 1, chaveId);
		if (idExiste != -1) {

			listaUsuarios.remove(idExiste);

		}

	}

	/**
	 * M�todo para editar um usu�rio na lista de usu�rios.
	 * 
	 * @param chaveId        String - Id para editar
	 * @param usuarioEditado Usuarios - Usu�rio a ser editado
	 * 
	 * @throws IdInvalidoException
	 * @throws LoginExistenteException
	 * @throws CamposNulosException
	 */

	private static void editarDados(Usuarios usuarioEditado, String chaveId)
			throws LoginExistenteException, CamposNulosException {

		int idExiste = buscarDado(0, listaUsuarios.size() - 1, chaveId);

		if (idExiste != -1) {

			Usuarios usuarioSalvo;
			usuarioEditado.setId(listaUsuarios.get(idExiste).getId());
			usuarioSalvo = listaUsuarios.get(idExiste);
			removerDados(Integer.toString(idExiste));

			if (buscarLogin(0, listaUsuarios.size() - 1, usuarioEditado.getLoginUsuario()) == false
					&& !usuarioCampoVazio(usuarioEditado)) {

				listaUsuarios.add(idExiste, usuarioEditado);

			} else if (buscarLogin(0, listaUsuarios.size() - 1, usuarioEditado.getLoginUsuario()) == true) {

				listaUsuarios.add(idExiste, usuarioSalvo);
				throw new LoginExistenteException();

			} else {

				listaUsuarios.add(idExiste, usuarioSalvo);
				throw new CamposNulosException();

			}

		}

	}

	/**
	 * M�todo para exibir a lista de usu�rios.
	 */

	public static void listarDados() {

		if (!listaUsuarios.isEmpty()) {

			System.out.println("---------------- Listagem dos Usu�rios --------------");
			for (Usuarios dadoUsuario : listaUsuarios) {

				System.out.println("Id do usu�rio:" + dadoUsuario.getId());
				System.out.println("Login do usu�rio:" + dadoUsuario.getLoginUsuario());
				System.out.println("Senha do usu�rio:" + dadoUsuario.getSenhaUsuario());

			}

		} else {

			System.out.println("A lista de usuarios est� vazia");

		}

	}

	/**
	 * M�todo de busca bin�ria recursiva pelo id, que retorna a posi��o do objeto
	 * caso exista na lista.
	 * 
	 * @param inicio  Integer - Index inicial da lista
	 * @param fim     Integer - Index final da lista
	 * @param chaveId String - Id a ser buscado
	 * @return Integer - Posi��o do objeto buscado na lista
	 */

	public static int buscarDado(int inicio, int fim, String chaveId) {

		int meio = (inicio + fim) / 2;

		if (inicio <= fim) {

			if (listaUsuarios.get(meio).getId().equals(chaveId)) {

				return meio;

			} else if (Integer.parseInt(DaoUsuarios.getListaUsuarios().get(meio).getId()) > Integer.parseInt(chaveId)) {

				return buscarDado(inicio, meio - 1, chaveId);

			} else {

				return buscarDado(meio + 1, fim, chaveId);

			}

		}

		return -1;
	}

	/**
	 * Metodo para buscar usuarios na lista de usuarios atraves do login e senha
	 * 
	 * @param usuarioLogin String - login a ser buscado
	 * @param senhaLogin   String - senha a ser buscada
	 * @return Usuarios usuario - retorna o usuario encontrado
	 */

	public static Usuarios buscaUsuarioLS(String usuarioLogin, String senhaLogin) {

		for (Usuarios usuario : DaoUsuarios.getListaUsuarios()) {

			if (usuario.getLoginUsuario().equals(usuarioLogin) && usuario.getSenhaUsuario().equals(senhaLogin)) {

				return usuario;

			}

		}
		return null;

	}

	/**
	 * M�todo para verificar a existencia de um login na lista de usu�rios.
	 * 
	 * @param inicio       Integer - Index inicial da lista
	 * @param fim          Integer - Index final da lista
	 * @param loginUsuario String - Login a ser verificado a exist�ncia
	 * @return Boolean <code>true</code> Se o login existir na lista de fornecedores
	 *         <code>false</code> Se o login não existir na lista de fornecedores
	 */

	public static boolean buscarLogin(int inicio, int fim, String loginUsuario) {

		if (inicio <= fim) {

			if (listaUsuarios.get(inicio).getLoginUsuario().equals(loginUsuario)) {

				return true;

			} else {

				return buscarLogin(inicio + 1, fim, loginUsuario);

			}

		}

		return false;

	}

	/**
	 * Metodo limpar a lista de usu�rios
	 */
	public static void limparLista() {

		DaoUsuarios.listaUsuarios.clear();

	}

//	/**
//	 * M�todo para determinar que um usuario é gerente
//	 * 
//	 * @param usuario Usuarios - usuario a ser revisado
//	 * @return Boolean <code>true</code> Se o usuario for do tipo Gerente
//	 *         <code>false</code> Se o usuario não for do tipo Gerente
//	 */
//
//	public static boolean isGerente(Usuarios usuario) {
//
//		if (usuario instanceof Gerente) {
//
//			return true;
//		} else {
//
//			return false;
//		}
//
//	}

	public static boolean usuarioCampoVazio(Usuarios usuario) {

		if (usuario.getLoginUsuario().isBlank() || usuario.getNomeUsuario().isBlank()
				|| usuario.getSenhaUsuario().isBlank()) {

			return true;

		}

		return false;

	}

}
