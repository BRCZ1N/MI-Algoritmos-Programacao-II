package applicationmodeldao;

import java.util.ArrayList;

import applicationexeceptions.CnpjJaExisteException;
import applicationexeceptions.FornecedorComProdutoInvalidoException;
import applicationexeceptions.IdInvalidoException;
import applicationmodel.Fornecedores;

/**
 * Classe para gerenciamento de objetos do tipo Fornecedores.
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @author Alex da Fonseca Dantas Junior
 * @version 1.0
 * @since 2022
 */
public class DaoFornecedores {

	private static ArrayList<Fornecedores> listaFornecedores = new ArrayList<Fornecedores>();
	private static int idSeq = 0;

	/**
	 * Construtor para popular a estrutura de dados referente a fornecedores no
	 * menu.
	 */
	
	public DaoFornecedores() {

		ArrayList<String> idProdutosFornecedor = new ArrayList<String>();

		idProdutosFornecedor.add("0");
		idProdutosFornecedor.add("1");
		idProdutosFornecedor.add("2");

		Fornecedores fornecedorA = new Fornecedores("018.236.120/0001-58", "EmpresaATest", "Rua UEFSA",idProdutosFornecedor);
		Fornecedores fornecedorA2 = new Fornecedores("Sem CNPJ", "EmpresaATest", "Rua UEFSA", idProdutosFornecedor);
		Fornecedores fornecedorB = new Fornecedores("Sem CNPJ", "EmpresaBTest", "Rua UEFSB", idProdutosFornecedor);
		Fornecedores fornecedorC = new Fornecedores("019.579.305/0001-79", "EmpresaCTest", "Rua UEFSC",
				idProdutosFornecedor);

		try {
			addEditDados(fornecedorA, null);
			addEditDados(fornecedorA2, null);
			addEditDados(fornecedorB, null);
			addEditDados(fornecedorC, null);
		} catch (CnpjJaExisteException | IdInvalidoException | FornecedorComProdutoInvalidoException e) {

			e.printStackTrace();
		}

	}

	/**
	 * Metodo para obter o retorno de uma sequencia de Id
	 * 
	 * @return int idSeq - sequencia de id
	 */
	public static int getIdSeq() {
		return idSeq;
	}

	/**
	 * Metodo para construir uma sequencia de Id
	 * 
	 * @param idSeq int
	 */
	public static void setIdSeq(int idSeq) {
		DaoFornecedores.idSeq = idSeq;
	}

	/**
	 * Metodo para retorno de uma lista de Fornecedores.
	 * 
	 * @return Arraylist<Fornecedores> listaFornecedores
	 */

	public static ArrayList<Fornecedores> getListaFornecedores() {
		return listaFornecedores;
	}

	/**
	 * Metodo para setar uma lista de Fornecedores.
	 * 
	 * @param listaFornecedores ArrayList<Fornecedores>
	 */
	public static void setListaFornecedores(ArrayList<Fornecedores> listaFornecedores) {
		DaoFornecedores.listaFornecedores = listaFornecedores;
	}

	/**
	 * Metodo para acessar o m�todo de editar caso exista um fornecedor a ser
	 * editado, ou ent�o para adicionar um novo fornecedor.
	 * 
	 * @param fornecedor Fornecedores
	 * @param chaveId    String
	 * @throws CnpjJaExisteException
	 * @throws IdInvalidoException
	 * @throws FornecedorComProdutoInvalidoException
	 */
	public static void addEditDados(Fornecedores fornecedor, String chaveId)
			throws CnpjJaExisteException, IdInvalidoException, FornecedorComProdutoInvalidoException {

		if (chaveId == null) {

			addDados(fornecedor);

		} else {

			editarDados(fornecedor, chaveId);

		}

	}

	/**
	 * M�todo para adicionar um fornecedor na lista de fornecedores
	 * 
	 * @param fornecedor Fornecedores - Objeto do tipo Fornecedores
	 * @throws CnpjJaExisteException
	 * @throws FornecedorComProdutoInvalidoException
	 */

	private static void addDados(Fornecedores fornecedor) throws CnpjJaExisteException, FornecedorComProdutoInvalidoException {

		boolean cnpjExiste = buscarCnpj(0, listaFornecedores.size() - 1, fornecedor.getCnpj());
		if ((!cnpjExiste || fornecedor.getCnpj().equals("Sem CNPJ"))
				&& !verificaProdutoInexistente(fornecedor.getIdProdutosFornecedor())) {

			fornecedor.setId(Integer.toString(idSeq));
			listaFornecedores.add(fornecedor);
			idSeq++;

		} else if (cnpjExiste) {

			throw new CnpjJaExisteException(fornecedor.getCnpj());

		} else if (verificaProdutoInexistente(fornecedor.getIdProdutosFornecedor())) {

			throw new FornecedorComProdutoInvalidoException();

		}

	}

	/**
	 * M�todo para remover um fornecedor na lista de fornecedores.
	 * 
	 * @param chaveId String - Id para remover
	 * @throws IdInvalidoException
	 *
	 */

	public static void removerDados(String chaveId) throws IdInvalidoException {

		int idExiste = buscarDado(0, listaFornecedores.size() - 1, chaveId, listaFornecedores);
		if (idExiste != -1) {

			listaFornecedores.remove(idExiste);

		} else {

			throw new IdInvalidoException(chaveId);

		}

	}

	/**
	 * M�todo para editar um fornecedor na lista de fornecedores.
	 * 
	 * @param fornecedorEditado Fornecedores - Objeto do tipo Fornecedores
	 * @param chaveId           String - Id para editar
	 * @throws IdInvalidoException
	 * @throws CnpjJaExisteException
	 * @throws FornecedorComProdutoInvalidoException
	 */

	private static void editarDados(Fornecedores fornecedorEditado, String chaveId)
			throws CnpjJaExisteException, IdInvalidoException, FornecedorComProdutoInvalidoException {

		boolean cnpjExiste = buscarCnpj(0, listaFornecedores.size() - 1, fornecedorEditado.getCnpj());
		int idExiste = buscarDado(0, listaFornecedores.size() - 1, chaveId, listaFornecedores);

		if (idExiste != -1 && (!cnpjExiste || fornecedorEditado.getCnpj().equals("Sem CNPJ"))
				&& !verificaProdutoInexistente(fornecedorEditado.getIdProdutosFornecedor())) {

			fornecedorEditado.setId(listaFornecedores.get(idExiste).getId());
			removerDados(Integer.toString(idExiste));
			listaFornecedores.add(idExiste, fornecedorEditado);

		} else if (idExiste == -1) {

			throw new IdInvalidoException(chaveId);

		} else if (cnpjExiste && !fornecedorEditado.getCnpj().equals("Sem CNPJ")) {

			throw new CnpjJaExisteException(fornecedorEditado.getCnpj());

		} else if (verificaProdutoInexistente(fornecedorEditado.getIdProdutosFornecedor())) {

			throw new FornecedorComProdutoInvalidoException();

		}

	}

	/**
	 * M�todo para exibir a lista de fornecedores.
	 */

	public void listarDados() {

		if (!listaFornecedores.isEmpty()) {
			System.out.println("---------------- Listagem dos fornecedores --------------");
			for (Fornecedores dadoFornecedor : listaFornecedores) {

				System.out.println("Id do fornecedor:" + dadoFornecedor.getId());
				System.out.println("Nome do fornecedor:" + dadoFornecedor.getNome());
				System.out.println("CNPJ do fornecedor:" + dadoFornecedor.getCnpj());
				System.out.println("Endere�o do fornecedor:" + dadoFornecedor.getEndereco());
				System.out.println("Lista de IDs dos produtos dos fornecedor:");

				for (String idProdutosF : dadoFornecedor.getIdProdutosFornecedor()) {

					System.out.println(idProdutosF);

				}

			}
		} else {

			System.out.print("A lista de fornecedores esta vazia");

		}

	}

	/**
	 * M�todo limpar uma lista, no caso a de Fornecedores
	 */

	public static void limparLista() {

		DaoFornecedores.listaFornecedores.clear();

	}

	/**
	 * M�todo para obter a quantidade total de fornecedores se a lista não
	 * estiver vazia
	 * 
	 * @return int listaFornecedores.size()
	 */

	public static int getQtdTotalFornecedores() {

		if (listaFornecedores.isEmpty()) {

			return 0;

		} else {

			return listaFornecedores.size();

		}

	}

	/**
	 * M�todo de busca bin�ria recursiva pelo id, que retorna a posi��o do
	 * objeto caso exista na lista.
	 * 
	 * @param inicio  Integer - Index inicial da lista
	 * @param fim     Integer - Index final da lista
	 * @param chaveId String - Id a ser buscado
	 * @return Integer - Posi��o do objeto buscado na lista
	 */

	public static int buscarDado(int inicio, int fim, String chaveId, ArrayList<Fornecedores> lista) {

		int meio = (inicio + fim) / 2;

		if (inicio <= fim) {

			if (listaFornecedores.get(meio).getId().equals(chaveId)) {

				return meio;

			} else if (Integer.parseInt(listaFornecedores.get(meio).getId()) > Integer.parseInt(chaveId)) {

				return buscarDado(inicio, meio - 1, chaveId, listaFornecedores);

			} else {

				return buscarDado(meio + 1, fim, chaveId, listaFornecedores);

			}

		}

		return -1;
	}

	/**
	 * M�todo de busca para verificar a existencia do cnpj de um fornecedor na
	 * lista de fornecedores.
	 * 
	 * @param inicio Integer - Index inicial da lista
	 * @param fim    Integer - Index final da lista
	 * @param cnpj   String - Cnpj a ser verificado a exist�ncia
	 * @return Boolean <code>true</code> Se o CPNJ existir na lista de fornecedores
	 *         <code>false</code> Se o CNPJ não existir na lista de fornecedores
	 */
	public static boolean buscarCnpj(int inicio, int fim, String cnpj) {

		if (inicio <= fim) {

			if (listaFornecedores.get(inicio).getCnpj().equals(cnpj)) {

				return true;

			} else {

				return buscarCnpj(inicio + 1, fim, cnpj);

			}
		}
		return false;
	}

	/**
	 * Metodo para verificação da existencia de um produto
	 * 
	 * @param idProdutosFornecedor ArrayList <String> - Lista de Id dos produtos quenecessitam de analise
	 * 
	 * @return Boolean <code>true</code> Se o Produto não na lista de Ids dos
	 *         produtos fornecidos <code>false</code> Se o Produto existe na lista
	 *         de Ids dos produtos fornecidos
	 */

	public static boolean verificaProdutoInexistente(ArrayList<String> idProdutosFornecedor) {

		for (String produto : idProdutosFornecedor) {

			if (DaoProdutos.buscarDado(0, DaoProdutos.getListaProdutos().size() - 1, produto,
					DaoProdutos.getListaProdutos()) == -1) {

				return true;

			}

		}

		return false;

	}

}
