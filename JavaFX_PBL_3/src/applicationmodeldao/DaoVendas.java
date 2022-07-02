package applicationmodeldao;

import java.time.LocalDate;
import java.util.ArrayList;

import applicationexeceptions.EstoqueInsuficienteException;
import applicationmodel.Ingredientes;
import applicationmodel.Pratos;
import applicationmodel.TipoPagamento;
import applicationmodel.Vendas;

/**
 * Classe para gerenciamento de objetos do tipo Vendas.
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @author Alex da Fonseca Dantas Junior
 * @version 1.0
 * @since 2022
 */

public class DaoVendas {

	private static ArrayList<Vendas> listaVendas = new ArrayList<Vendas>();
	private static int idSeq = 0;

	/**
	 * Construtor para popular a estrutura de dados referente a vendas no menu.
	 */

	public DaoVendas() {

		ArrayList<String> listaIdItens = new ArrayList<String>();

		listaIdItens.add("0");
		listaIdItens.add("1");

		Vendas vendaA = new Vendas(listaIdItens, TipoPagamento.getTipoDePagamento1());
		Vendas vendaB = new Vendas(listaIdItens, TipoPagamento.getTipoDePagamento2());
		Vendas vendaC = new Vendas(listaIdItens, TipoPagamento.getTipoDePagamento3());

		try {

			addEditDados(vendaA, null);
			addEditDados(vendaB, null);
			addEditDados(vendaC, null);

		} catch ( EstoqueInsuficienteException  e) {

			e.getMessage();
		}

	}

	/**
	 * Metodo para obter o retorno do id sequencial
	 * 
	 * @return int idSeq - id sequencial
	 */
	public static int getIdSeq() {
		return idSeq;
	}

	/**
	 * Metodo para setar o id sequencial
	 * 
	 * @param idSeq int - id sequencial
	 */
	public static void setIdSeq(int idSeq) {
		DaoVendas.idSeq = idSeq;
	}

	/**
	 * Metodo para retorno de uma lista de Vendas.
	 * 
	 * @return Arraylist<Vendas> listaVendas
	 */
	public static ArrayList<Vendas> getListaVendas() {
		return listaVendas;
	}

	/**
	 * Metodo para setar uma lista de Vendas.
	 * 
	 * @param listaVendas Arraylist<Vendas>
	 */
	public void setListaVendas(ArrayList<Vendas> listaVendas) {
		DaoVendas.listaVendas = listaVendas;
	}

	/**
	 * Metodo para acessar o m�todo de editar caso exista uma venda a ser editada,
	 * ou ent�o para adicionar um nova venda.
	 * 
	 * @param venda   Vendas
	 * @param chaveId String
	 * @throws IdInvalidoException
	 * @throws EstoqueInsuficienteException
	 * @throws VendaComPratoInvalidoException
	 */
	public static void addEditDados(Vendas venda, String chaveId)
			throws EstoqueInsuficienteException {

		if (chaveId == null) {

			addDados(venda);

		} else {

			editarDados(venda, chaveId);

		}

	}

	/**
	 * M�todo para adicionar uma venda na lista de Vendas
	 * 
	 * @param venda Vendas - Objeto do tipo Vendas.
	 * @throws EstoqueInsuficienteException
	 * @throws VendaComPratoInvalidoException
	 */

	private static void addDados(Vendas venda) throws EstoqueInsuficienteException {

		if (!verificaPratoInexistente(venda.getListaIdItens())) {

			venda.setId(Integer.toString(idSeq));
			alterarEstoque(venda);
			listaVendas.add(venda);
			idSeq++;

		} 

	}

	/**
	 * M�todo para remover uma venda na lista de vendas.
	 * 
	 * @param chaveId String - Id para remover
	 * 
	 * @throws IdInvalidoException
	 */

	public static void removerDados(String chaveId) {

		int idExiste = buscarDado(0, listaVendas.size() - 1, chaveId, listaVendas);
		if (idExiste != -1) {

			listaVendas.remove(idExiste);

		} 

	}

	/**
	 * M�todo para editar uma venda na lista de vendas.
	 * 
	 * @param vendaEditada Vendas - Objeto do tipo Vendas
	 * @param chaveId      String - Id para editar
	 * @throws IdInvalidoException
	 */

	private static void editarDados(Vendas vendaEditada, String chaveId) {

		int idExiste = buscarDado(0, listaVendas.size() - 1, chaveId, listaVendas);
		if (idExiste != -1) {

			vendaEditada.setId(listaVendas.get(idExiste).getId());
			removerDados(Integer.toString(idExiste));
			listaVendas.add(idExiste, vendaEditada);

		} 

	}

	/**
	 * Metodo para exibir a lista de vendas.
	 */

	public void listarDados() {

		if (!listaVendas.isEmpty()) {
			System.out.println("---------------- Listagem das Vendas--------------");
			for (Vendas dadoVenda : listaVendas) {

				System.out.println("Id da Venda:" + dadoVenda.getId());
				System.out.println("A data e o horario da venda sao:" + dadoVenda.getDiaHorario());
				System.out.println("Preco total da venda:" + dadoVenda.getPrecoTotal());
				System.out.println("Forma de pagamento da venda:" + dadoVenda.getTipoPagamento());
				System.out.println("Id's dos itens da venda:");

				for (String itensDaVenda : dadoVenda.getListaIdItens()) {

					System.out.println(itensDaVenda);

				}

				System.out.println("\n");
			}
		} else {

			System.out.println("A lista de vendas est� vazia");

		}

	}

	/**
	 * M�todo limpar a lista de Vendas
	 */
	public static void limparLista() {

		DaoVendas.listaVendas.clear();

	}

	/**
	 * M�todo para definir o numero total de pratos vendidos
	 * 
	 * @return int numTotalPVendidos - numero total de vendas
	 */

	public static int numTotalPratosVendidos() {

		int numTotalPVendidos = 0;

		if (listaVendas.isEmpty()) {

			return numTotalPVendidos;

		} else {

			for (Vendas venda : listaVendas) {

				numTotalPVendidos += venda.getListaIdItens().size();

			}

			return numTotalPVendidos;

		}

	}

	/**
	 * M�todo para definir o valor total da venda
	 * 
	 * @return double valorVendas - valor total da venda
	 */
	public static double valorTotalVendas() {

		double valorVendas = 0;

		if (listaVendas.isEmpty()) {

			return valorVendas;

		} else {

			for (Vendas venda : listaVendas) {

				valorVendas += venda.getPrecoTotal();

			}

			return valorVendas;

		}

	}
	/**
	 * metodo que ira obter uma lista de vendas feitas em um determinado periodo
	 * @param dataInicial LocalDate 
	 * @param dataFinal LocalDate
	 * @return ArrayList<Vendas> listaVendaP
	 */

	public static ArrayList<Vendas> getListaVendasPeriodo(LocalDate dataInicial,LocalDate dataFinal) {
		
		ArrayList<Vendas> listaVendaP = new ArrayList<Vendas>();
		
		for(Vendas venda:DaoVendas.getListaVendas()) {
					//22 							//26
			if(dataInicial.compareTo(venda.getDiaHorario().toLocalDate()) <= 0 && dataFinal.compareTo(venda.getDiaHorario().toLocalDate()) >= 0) {
				
				listaVendaP.add(venda);
	
			}
			
		}
		
		return listaVendaP;
		
	}

	/**
	 * M�todo de busca bin�ria recursiva pelo id, que retorna a posi��o do
	 * objeto caso exista na lista.
	 * 
	 * @param inicio      Integer - Index inicial da lista
	 * @param fim         Integer - Index final da lista
	 * @param chaveId     String - Id a ser buscado
	 * @param listaVendas ArrayList<Vendas> - lista de vendas
	 * @return Integer - Posi��o do objeto buscado na lista
	 */

	public static int buscarDado(int inicio, int fim, String chaveId, ArrayList<Vendas> listaVendas) {

		int meio = (inicio + fim) / 2;

		if (inicio <= fim) {

			if (listaVendas.get(meio).getId().equals(chaveId)) {

				return meio;

			} else if (Integer.parseInt(listaVendas.get(meio).getId()) > Integer.parseInt(chaveId)) {

				return buscarDado(inicio, meio - 1, chaveId, listaVendas);

			} else {

				return buscarDado(meio + 1, fim, chaveId, listaVendas);

			}

		}

		return -1;

	}
	
	

	/**
	 * Metodo para verificação da existencia de um prato
	 * 
	 * @param listaIdItens Arraylist<String> - Lista de Id dos pratos que necessitam
	 *                     de analise
	 * 
	 * @return Boolean <code>true</code> Se o Prato não existir na lista
	 *         <code>false</code> Se o Produto existe na lista
	 */
	public static boolean verificaPratoInexistente(ArrayList<String> listaIdItens) {

		for (String prato : listaIdItens) {

			if (DaoPratos.buscarDado(0, DaoPratos.getListaPratos().size() - 1, prato,
					DaoPratos.getListaPratos()) == -1) {

				return true;

			}

		}

		return false;

	}

	/**
	 * M�todo de redução de estoque de produtos usados nos pratos vendidos
	 * 
	 * @param venda Vendas - venda efetuada
	 * @throws EstoqueInsuficienteException
	 */
	private static void alterarEstoque(Vendas venda) throws EstoqueInsuficienteException {

		ArrayList<Pratos> pratosVenda = DaoPratos.getListaPratos(venda.getListaIdItens());

		for (Pratos prato : pratosVenda) {

			for (Ingredientes produto : prato.getComposicaoPrato()) {

				DaoProdutos.reduzirEstoque(produto.getId(), produto.getQtd());

			}

		}

	}
	/**
	 * metodo que  criara uma lista de vendas por meio de uma lista de id's
	 * 
	 * @param listaIdVenda ArrayList<String> - lista dos ids das vendas
	 * @return ArrayList<Vendas> vendas
	 */
	public static ArrayList<Vendas> getListaVenda(ArrayList<String> listaIdVenda) {

		ArrayList<Vendas> vendas = new ArrayList<Vendas>();

		for (String vendaId : listaIdVenda) {

			vendas.add(getVenda(vendaId));

		}

		return vendas;

	}
	/**
	 * metodo que  criara uma lista de id's de vendas por meio da lista de vendas
	 * 
	 * @param listaVenda ArrayList<Vendas> - lista das vendas
	 * @return ArrayList<String> vendas
	 */
	
	public static ArrayList<String> getListaIdVenda(ArrayList<Vendas> listaVenda){
		
		
		ArrayList<String> vendas = new ArrayList<String>();

		for (Vendas venda : listaVenda) {

			vendas.add(venda.getId());

		}

		return vendas;
		
	}
	/**
	 * metodo que  criara uma lista de vendas feitas com a composição de um determinado prato 
	 * 
	 * @param idPrato String - id do prato
	 * @return ArrayList<Vendas> listaVendas
	 */
	public static ArrayList<Vendas> getListaVendasPrato(String idPrato){
		
		ArrayList<Vendas> listaVendasPrato = new ArrayList<Vendas>();
		
		for(Vendas venda:DaoVendas.getListaVendas()) {
			
			if(venda.getListaIdItens().contains(idPrato)) {
				
				listaVendasPrato.add(venda);
				
			}
			
			
			
		}
		return listaVendasPrato;
		
		
		
	}
	
	/**
	 * metodo que ira obter uma lista de vendas do tipo Venda
	 * 
	 * @param idPrato String - id do prato
	 * @return Vendas venda
	 */
	public static Vendas getVenda(String idVenda) {
		
		Vendas venda =  new Vendas();
		
		int idExiste = DaoVendas.buscarDado(0, DaoVendas.getListaVendas().size() - 1, idVenda,DaoVendas.getListaVendas());
	
		if(idExiste != -1) {
				
			venda = DaoVendas.getListaVendas().get(idExiste);
			
		}
		
		return venda;
		
	}
	

}
