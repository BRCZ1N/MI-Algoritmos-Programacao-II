package applicationmodeldao;

import java.util.ArrayList;

import applicationmodel.Ingredientes;
import applicationmodel.Pratos;
import applicationexeceptions.EntidadeComValoresNegativoException;

/**
 * Classe para gerenciamento de objetos do tipo Pratos.
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @author Alex da Fonseca Dantas Junior
 * @version 1.0
 * @since 2022
 */

public class DaoPratos {

	private static ArrayList<Pratos> listaPratos = new ArrayList<Pratos>();
	private static int idSeq = 0;

	/**
	 * Construtor para popular a estrutura de dados referente a pratos no menu.
	 */

	public DaoPratos() {

		Ingredientes ingrediente1 = new Ingredientes("0", 10.74);
		Ingredientes ingrediente2 = new Ingredientes("1", 3.30);
		Ingredientes ingrediente3 = new Ingredientes("2", 7.5);

		ArrayList<Ingredientes> composicaoPrato = new ArrayList<Ingredientes>();
		composicaoPrato.add(ingrediente1);
		composicaoPrato.add(ingrediente2);
		composicaoPrato.add(ingrediente3);

		Pratos pratoA = new Pratos("pratoA", "descricaoA", 40, "categoriaA", composicaoPrato);
		Pratos pratoB = new Pratos("pratoB", "descricaoB", 150, "categoriaB", composicaoPrato);
		Pratos pratoC = new Pratos("pratoC", "descricaoC", 89, "categoriaC", composicaoPrato);

		try {
			addEditDados(pratoA, null);
			addEditDados(pratoB, null);
			addEditDados(pratoC, null);
		} catch (EntidadeComValoresNegativoException e) {

			e.getMessage();
		}

	}

	/**
	 * Metododo para obter o retorno do id sequencial
	 * 
	 * @return int idSeq - retorno do id
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

		DaoPratos.idSeq = idSeq;

	}

	/**
	 * Metodo para retorno de uma lista de Pratos.
	 * 
	 * @return Arraylist<Pratos> listaPratos
	 */
	public static ArrayList<Pratos> getListaPratos() {
		return listaPratos;
	}

	/**
	 * Metodo para setar uma lista de Pratos.
	 * 
	 * @param listaPratos Arraylist<Pratos>
	 */
	public static void setListaPratos(ArrayList<Pratos> listaPratos) {
		DaoPratos.listaPratos = listaPratos;
	}

	/**
	 * Metodo para acessar o m�todo de editar caso exista um prato a ser editado, ou
	 * ent�o para adicionar um novo prato.
	 * 
	 * @param prato   Pratos
	 * @param chaveId String
	 * @throws IdInvalidoException
	 * @throws EntidadeComValoresNegativoException
	 * @throws PratoComProdutoInvalidoException
	 */
	public static void addEditDados(Pratos prato, String chaveId) throws EntidadeComValoresNegativoException {

		if (chaveId == null) {

			addDados(prato);

		} else {

			editarDados(prato, chaveId);

		}

	}

	/**
	 * M�todo para adicionar um prato na lista de pratos.
	 * 
	 * @param prato Pratos
	 * @throws EntidadeComValoresNegativoException
	 * @throws PratoComProdutoInvalidoException
	 */

	private static void addDados(Pratos prato) throws EntidadeComValoresNegativoException {

		if (prato.getPreco() > 0 && !verificaProdutoInexistente(prato.getComposicaoPrato())) {

			prato.setId(Integer.toString(idSeq));
			listaPratos.add(prato);
			idSeq++;

		} else if (prato.getPreco() < 0) {

			throw new EntidadeComValoresNegativoException();

		}
	}

	/**
	 * M�todo para remover um prato na lista de pratos.
	 * 
	 * @param chaveId String - Id para remover
	 * @throws IdInvalidoException
	 */

	public static void removerDados(String chaveId) {

		int idExiste = buscarDado(0, listaPratos.size() - 1, chaveId, listaPratos);
		if (idExiste != -1) {

			listaPratos.remove(idExiste);

		}

	}

	/**
	 * M�todo para editar um prato na lista de Pratos,
	 * 
	 * @param pratoEditado Pratos - Objeto do tipo Pratos
	 * @param chaveId      String - Id para editar
	 * @throws EntidadeComValoresNegativoException
	 * @throws IdInvalidoException
	 * @throws PratoComProdutoInvalidoException
	 */

	private static void editarDados(Pratos pratoEditado, String chaveId) throws EntidadeComValoresNegativoException {

		int idExiste = buscarDado(0, listaPratos.size() - 1, chaveId, listaPratos);

		if (idExiste != -1 && pratoEditado.getPreco() > 0
				&& !verificaProdutoInexistente(pratoEditado.getComposicaoPrato())) {

			pratoEditado.setId(listaPratos.get(idExiste).getId());
			removerDados(Integer.toString(idExiste));
			listaPratos.add(idExiste, pratoEditado);

		} else if (idExiste != -1 && pratoEditado.getPreco() < 0
				&& !verificaProdutoInexistente(pratoEditado.getComposicaoPrato())) {

			throw new EntidadeComValoresNegativoException();

		}

	}

	/**
	 * M�todo para exibir a lista de pratos.
	 */

	public void listarDados() {

		if (!listaPratos.isEmpty()) {
			System.out.println("---------------- Listagem dos pratos --------------");
			for (Pratos dadoPrato : listaPratos) {

				System.out.println("Id do prato:" + dadoPrato.getId());
				System.out.println("Nome do prato:" + dadoPrato.getNome());
				System.out.println("Descriçãoo do prato:" + dadoPrato.getDescricao());
				System.out.println("Preço do prato:" + dadoPrato.getPreco());
				System.out.println("Categoria do prato:" + dadoPrato.getCategoria());
				System.out.println("Id's dos itens da composição do prato:");

				for (Ingredientes itemComposicao : dadoPrato.getComposicaoPrato()) {

					System.out.println(itemComposicao.getId());

				}

				System.out.println("\n");

			}
		} else {

			System.out.print("A lista de pratos est� vazia");

		}

	}

	/**
	 * M�todo limpar a lista de Pratos
	 */
	public static void limparLista() {

		DaoPratos.listaPratos.clear();

	}

	/**
	 * metodo para gerar a lista de categorias do sistema
	 */
	public static ArrayList<String> gerarListaCategoria() {
		ArrayList<String> listaCategoria = new ArrayList<String>();
		for (Pratos prato : DaoPratos.getListaPratos()) {
			if (!listaCategoria.contains(prato.getCategoria())) {
				listaCategoria.add(prato.getCategoria());

			}

		}
		return listaCategoria;
	}
	/**
	 * metodo para gerar uma lista de pratos a depender da categoria
	 * @param categoria String 
	 * @return ArrayList<Pratos> listaPratosCategoria
	 */
	public static ArrayList<Pratos> gerarListaPratosCategoria(String categoria) {
		ArrayList<Pratos> listaPratosCategoria = new ArrayList<Pratos>();
		for (Pratos prato : DaoPratos.getListaPratos()) {
			if(prato.getCategoria().equals(categoria)) {
				listaPratosCategoria.add(prato);
				
			}
			
		}
		
		
		
		
		return listaPratosCategoria;
	
	}

	/**
	 * M�todo de busca bin�ria recursiva pelo id, que retorna a posi��o do objeto
	 * caso exista na lista.
	 * 
	 * @param inicio      Integer - Index inicial da lista
	 * @param fim         Integer - Index final da lista
	 * @param chaveId     String - Id a ser buscado
	 * @param listaPratos ArrayList<Pratos> - lista de pratos
	 * @return Integer - Posi��o do objeto buscado na lista
	 */

	public static int buscarDado(int inicio, int fim, String chaveId, ArrayList<Pratos> listaPratos) {

		int meio = (inicio + fim) / 2;

		if (inicio <= fim) {

			if (listaPratos.get(meio).getId().equals(chaveId)) {

				return meio;

			} else if (Integer.parseInt(listaPratos.get(meio).getId()) > Integer.parseInt(chaveId)) {

				return buscarDado(inicio, meio - 1, chaveId, listaPratos);

			} else {

				return buscarDado(meio + 1, fim, chaveId, listaPratos);

			}

		}

		return -1;
	}

	/**
	 * Metodo para verificar um produto inexistente
	 * 
	 * @param idProdutosPratos Arraylist<Ingredientes> - Lista de Id dos produtos
	 *                         que necessitam de analise
	 * 
	 * @return Boolean <code>true</code> Se o Produto não existe na lista de Ids
	 *         fornecidos <code>false</code> Se o Produto existe na lista
	 */
	public static boolean verificaProdutoInexistente(ArrayList<Ingredientes> idProdutosPratos) {

		for (Ingredientes produto : idProdutosPratos) {

			if (DaoProdutos.buscarDado(0, DaoProdutos.getListaProdutos().size() - 1, produto.getId(),
					DaoProdutos.getListaProdutos()) == -1) {

				return true;

			}

		}

		return false;

	}

	/**
	 * M�todo para obter a lista de pratos da venda
	 * 
	 * idPratoVenda
	 * 
	 * @param idPratoVenda ArrayList<String> - lista de id's de pratos da venda
	 * 
	 * @return ArrayList<Pratos> pratosVenda - lista de pratos da venda
	 */

//	public static ArrayList<Pratos> getListaPratosVenda(ArrayList<String> idPratoVenda) {
//
//		ArrayList<Pratos> pratosVenda = new ArrayList<Pratos>();
//
//		for (String pratoId : idPratoVenda) {
//
//			pratosVenda.add(listaPratos.get(Integer.parseInt(pratoId)));
//
//		}
//
//		return pratosVenda;
//
//	}
//	
//	
//	public static ArrayList<String> listaIdPratos(ArrayList<Pratos> listaPratosCarrinho){
//		
//		ArrayList<String> listaIdPratos = new ArrayList<String>();
//		
//		for(Pratos prato:listaPratosCarrinho) {
//	
//			listaIdPratos.add(prato.getId());
//			
//		}
//		
//		return listaIdPratos;
//		
//	}

	public static ArrayList<Pratos> getListaPratos(ArrayList<String> listaIdPratos) {

		ArrayList<Pratos> pratos = new ArrayList<Pratos>();

		for (String pratoId : listaIdPratos) {

			pratos.add(getPrato(pratoId));

		}

		return pratos;

	}

	public static ArrayList<String> getListaIdPratos(ArrayList<Pratos> listaPratos) {

		ArrayList<String> pratosId = new ArrayList<String>();

		for (Pratos prato : listaPratos) {

			pratosId.add(prato.getId());

		}

		return pratosId;

	}
	

	/**
	 * Metodo para obter as informações de um prato caso ele exista na lista de
	 * pratos
	 * 
	 * @param chaveId int - chave que sera buscada na lista de pratos
	 * 
	 * @return Pratos listaPratos.get(idExiste) - Objeto do tipo Prato
	 */

	public static Pratos getPrato(String chaveId) {

		int idExiste = buscarDado(0, listaPratos.size() - 1, chaveId, listaPratos);
		if (idExiste != -1) {

			return listaPratos.get(idExiste);

		}

		return null;

	}
	
}
