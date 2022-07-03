package applicationmodeldao;

import java.util.ArrayList;
import applicationexeceptions.CpfJaExisteException;
import applicationmodel.Clientes;
import applicationmodel.Vendas;

/**
 * Classe para gerenciamento de objetos do tipo Pratos.
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @author Alex da Fonseca Dantas Junior
 * @version 1.0
 * @since 2022
 */

public class DaoClientes {

	private static ArrayList<Clientes> listaClientes = new ArrayList<Clientes>();
	private static int idSeq = 0;

	/**
	 * Construtor para popular a estrutura de dados referente a clientes no menu.
	 */

	public DaoClientes() {

		ArrayList<String> idHistoricoCompras = new ArrayList<String>();

		idHistoricoCompras.add("0");
		idHistoricoCompras.add("1");

		Clientes clienteA = new Clientes("Robenilson", "018.236.120/0001-58", "RobenilsonPatriota@yahoo.com",
				"4002-8922", idHistoricoCompras);
		Clientes clienteB = new Clientes("Carlos", "018.232.120/0001-58", "robinPat@yahoo.com", "4002-8922",
				idHistoricoCompras);
		Clientes clienteC = new Clientes("Claudinho", "018.231.120/0001-58", "Cocada@yahoo.com", "4002-8922",
				idHistoricoCompras);

		try {

			addEditDados(clienteA, null);
			addEditDados(clienteB, null);
			addEditDados(clienteC, null);

		} catch (CpfJaExisteException e) {

			e.getMessage();
		}

	}

	/**
	 * Metodo para retorno de uma lista de clientes.
	 * 
	 * @return Arraylist<Clientes> listaClientes
	 */

	public static ArrayList<Clientes> getListaClientes() {
		return listaClientes;
	}

	/**
	 * Metodo para setar uma lista de clientes.
	 * 
	 * @param listaClientes Arraylist<Clientes>
	 */
	public static void setListaClientes(ArrayList<Clientes> listaClientes) {
		DaoClientes.listaClientes = listaClientes;
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
		DaoClientes.idSeq = idSeq;
	}

	/**
	 * Metodo para acessar o m�todo de editar caso exista um cliente a ser editado,
	 * ou ent�o para adicionar um novo cliente.
	 * 
	 * @param cliente Clientes
	 * @param chaveId String
	 * @throws CpfJaExisteException
	 * @throws IdInvalidoException
	 * @throws VendaInexistenteException
	 */
	public static void addEditDados(Clientes cliente, String chaveId) throws CpfJaExisteException {

		if (chaveId == null) {

			addDados(cliente);

		} else {

			editarDados(cliente, chaveId);

		}

	}

	/**
	 * M�todo para adicionar um cliente na lista de cliente
	 * 
	 * @param cliente Clientes- Objeto do tipo Clientes
	 * @throws CpfJaExisteException
	 * @throws IdInvalidoException
	 */
	private static void addDados(Clientes cliente) throws CpfJaExisteException {

		boolean cpfExiste = buscarCpf(0, listaClientes.size() - 1, cliente.getCpf());

		if (!cpfExiste) {

			cliente.setId(Integer.toString(idSeq));
			listaClientes.add(cliente);
			idSeq++;

		} else {

			throw new CpfJaExisteException(cliente.getCpf());

		}
	}

	/**
	 * M�todo para editar um cliente na lista de clientes.
	 * 
	 * @param chaveId        String - Id para editar
	 * @param clienteEditado Clientes - Cliente a ser editado
	 * @throws CpfJaExisteException
	 * @throws IdInvalidoException
	 */

	private static void editarDados(Clientes clienteEditado, String chaveId) throws CpfJaExisteException {

		int idExiste = buscarDado(0, listaClientes.size() - 1, chaveId);
		if (idExiste != -1) {
			Clientes clienteSalvo;
			clienteEditado.setId(listaClientes.get(idExiste).getId());
			clienteSalvo = listaClientes.get(idExiste);
			removerDados(Integer.toString(idExiste));

			if (buscarCpf(0, listaClientes.size() - 1, clienteEditado.getCpf()) == false) {

				listaClientes.add(idExiste, clienteEditado);

			} else {

				listaClientes.add(idExiste, clienteSalvo);
				throw new CpfJaExisteException(clienteEditado.getCpf());

			}

		}
	}

	/**
	 * M�todo para remover um cliente na lista de clientes.
	 * 
	 * @param chaveId String - Id para remover
	 * @throws IdInvalidoException
	 * 
	 */

	public static void removerDados(String chaveId) {

		int idExiste = buscarDado(0, listaClientes.size() - 1, chaveId);
		if (idExiste != -1) {

			listaClientes.remove(idExiste);

		}

	}

	/**
	 * M�todo de busca bin�ria recursiva pelo cpf, que retorna a posi��o do objeto
	 * caso exista na lista.
	 * 
	 * @param inicio  Integer - Index inicial da lista
	 * @param fim     Integer - Index final da lista
	 * @param chaveId String - Id a ser buscado
	 * @return Integer - Posi��o do objeto buscado na lista
	 */
	public static boolean buscarCpf(int inicio, int fim, String cpf) {

		if (inicio <= fim) {

			if (listaClientes.get(inicio).getCpf().equals(cpf)) {

				return true;

			} else {

				return buscarCpf(inicio + 1, fim, cpf);

			}

		}

		return false;

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

			if (listaClientes.get(meio).getId().equals(chaveId)) {

				return meio;

			} else if (Integer.parseInt(DaoClientes.getListaClientes().get(meio).getId()) > Integer.parseInt(chaveId)) {

				return buscarDado(inicio, meio - 1, chaveId);

			} else {

				return buscarDado(meio + 1, fim, chaveId);

			}

		}

		return -1;
	}

	/**
	 * M�todo de soma, para calcular o valor total gasto pelos clientes
	 * 
	 * @param listaClientes ArrayList<Clientes>
	 * @return double valorTotal
	 */
	public static double valorTotalVendasClientes(ArrayList<Clientes> listaClientes) {

		double valorTotalVendasClientes = 0;

		for (Clientes cliente : listaClientes) {

			valorTotalVendasClientes += valorTotalVendasCliente(cliente.getIdHistoricoCompras());

		}

		return valorTotalVendasClientes;

	}

	public static void limparLista() {

		DaoClientes.listaClientes.clear();
	}

	/**
	 * M�todo de soma, para calcular o valor total gasto pelo cliente
	 * 
	 * @param listaIdVendas ArrayList<String>
	 * @return double valorTotal
	 */
	public static double valorTotalVendasCliente(ArrayList<String> listaIdVendas) {

		double valorTotalVendasCliente = 0;

		for (Vendas venda : DaoVendas.getListaVenda(listaIdVendas)) {

			valorTotalVendasCliente += venda.getPrecoTotal();

		}

		return valorTotalVendasCliente;

	}

	/**
	 * M�todo de obtenção do id dos pratos das vendas feitas para determinado
	 * cliente
	 * 
	 * @param listaIdVendas ArrayList<String>
	 * @return ArrayList<String> clientesLista
	 */
	public static ArrayList<String> listaPratosIdCliente(ArrayList<String> listaIdVendas) {

		ArrayList<String> clientesLista = new ArrayList<String>();

		for (Vendas venda : DaoVendas.getListaVenda(listaIdVendas)) {

			clientesLista.addAll(venda.getListaIdItens());

		}

		return clientesLista;

	}
	
	public static int numTotalPratosCliente(Clientes cliente) {

		int numTotalPratosCliente = 0;

		numTotalPratosCliente += DaoVendas.numTotalPratosVendidos(DaoVendas.getListaVenda(cliente.getIdHistoricoCompras()));

		return numTotalPratosCliente;

	}
	
	

	public static int numTotalPratosClientes(ArrayList<Clientes> listaCliente) {

		int numTotalPratosCliente = 0;

		for (Clientes cliente : listaCliente) {

			numTotalPratosCliente += DaoVendas
					.numTotalPratosVendidos(DaoVendas.getListaVenda(cliente.getIdHistoricoCompras()));

		}

		return numTotalPratosCliente;

	}

	public static Clientes getCliente(String idCliente) {

		
		int idExiste = buscarDado(0, listaClientes.size() - 1, idCliente);
		
		if(idExiste != -1) {
			
			return DaoClientes.getListaClientes().get(idExiste);
			
		}
		
		return null;

	}

}
