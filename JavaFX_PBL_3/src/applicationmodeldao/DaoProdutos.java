package applicationmodeldao;

import java.util.ArrayList;
import java.util.Calendar;

import applicationexeceptions.EntidadeComValoresNegativoException;
import applicationexeceptions.EstoqueInsuficienteException;
import applicationexeceptions.IdInvalidoException;
import applicationmodel.Produtos;

/**
 * Classe para gerenciamento de objetos do tipo Produtos.
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @author Alex da Fonseca Dantas Junior
 * @version 1.0
 * @since 2022
 */

public class DaoProdutos {

	private static ArrayList<Produtos> listaProdutos = new ArrayList<Produtos>();
	private static int idSeq = 0;

	/**
	 * Construtor para popular a estrutura de dados referente a produtos no menu.
	 */

	public DaoProdutos() {

		Calendar validade1 = Calendar.getInstance();
		Calendar validade2 = Calendar.getInstance();
		Calendar validade3 = Calendar.getInstance();

		Produtos produtoA = new Produtos("ProdutoA", validade1, 140.0, 128.88, "Kg");
		Produtos produtoB = new Produtos("ProdutoB", validade2, 80.2, 90.1, "L");
		Produtos produtoC = new Produtos("ProdutoC", validade3, 80.2, 180.3, "Kg");

		try {

			addEditDados(produtoA, null);
			addEditDados(produtoB, null);
			addEditDados(produtoC, null);

		} catch (EntidadeComValoresNegativoException | IdInvalidoException e) {

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

		DaoProdutos.idSeq = idSeq;

	}

	/**
	 * Metodo para retorno de uma lista de Produtos.
	 * 
	 * @return Arraylist<Produto> listaProdutos
	 */
	public static ArrayList<Produtos> getListaProdutos() {
		return listaProdutos;
	}

	/**
	 * Metodo para setar uma lista de produtos.
	 * 
	 * @param listaProdutos Arraylist<Produto>
	 */
	public static void setListaProdutos(ArrayList<Produtos> listaProdutos) {
		DaoProdutos.listaProdutos = listaProdutos;
	}

	/**
	 * Metodo para acessar o m�todo de editar caso exista um produto a ser
	 * editado, ou ent�o para adicionar um novo produto.
	 * 
	 * @param produto Produtos
	 * @param chaveId String
	 * @throws EntidadeComValoresNegativoException
	 * @throws IdInvalidoException
	 */
	public static void addEditDados(Produtos produto, String chaveId)
			throws EntidadeComValoresNegativoException, IdInvalidoException {

		if (chaveId == null) {

			addDados(produto);

		} else {

			editarDados(produto, chaveId);

		}

	}

	/**
	 * M�todo para adicionar um produto na lista de produtos.
	 * 
	 * @param produto Produtos
	 * @throws EntidadeComValoresNegativoException
	 * 
	 */

	private static void addDados(Produtos produto) throws EntidadeComValoresNegativoException {

		if (produto.getPreco() > 0 && produto.getQtdProduto() > 0) {

			produto.setId(Integer.toString(idSeq));
			listaProdutos.add(produto);
			idSeq++;

		} else {

			throw new EntidadeComValoresNegativoException();

		}

	}

	/**
	 * M�todo para remover um produto na lista de produtos.
	 * 
	 * @param chaveId String - Id para remover
	 * @throws IdInvalidoException
	 */

	public static void removerDados(String chaveId) throws IdInvalidoException {

		int idExiste = buscarDado(0, listaProdutos.size() - 1, chaveId, listaProdutos);
		if (idExiste != -1) {

			listaProdutos.remove(idExiste);

		} else {

			throw new IdInvalidoException(chaveId);

		}

	}

	/**
	 * M�todo para editar um produto na lista de produtos.
	 * 
	 * @param produtoEditado Produtos - Objeto do tipo Produtos
	 * @param chaveId        String - Id para editar
	 * @throws IdInvalidoException
	 * @throws EntidadeComValoresNegativosException
	 */

	private static void editarDados(Produtos produtoEditado, String chaveId)
			throws EntidadeComValoresNegativoException, IdInvalidoException {

		int idExiste = buscarDado(0, listaProdutos.size() - 1, chaveId, listaProdutos);
		if (idExiste != -1 && (produtoEditado.getPreco() > 0 && produtoEditado.getQtdProduto() > 0)) {

			produtoEditado.setId(listaProdutos.get(idExiste).getId());
			removerDados(Integer.toString(idExiste));
			listaProdutos.add(idExiste, produtoEditado);

		} else if (idExiste == -1) {

			throw new IdInvalidoException(chaveId);

		} else {

			throw new EntidadeComValoresNegativoException();

		}

	}

	/**
	 * M�todo para exibir a lista de produtos.
	 */

	public void listarDados() {

		if (!listaProdutos.isEmpty()) {

			System.out.println("---------------- Listagem dos produtos --------------");
			for (Produtos dadoProduto : listaProdutos) {

				System.out.println("Id do produto:" + dadoProduto.getId());
				System.out.println("Nome do produto:" + dadoProduto.getNome());
				System.out.println("Validade do produto:" + dadoProduto.getValidadeString());
				System.out.println("Preco do produto:" + dadoProduto.getPreco());

			}

		} else {

			System.out.println("A lista de produtos esta vazia");

		}

	}

	/**
	 * M�todo para busca do id de um produto
	 * 
	 * @param idProduto String - Id do produto que devera ser buscado
	 * @return int idExiste - retornar caso o id exista
	 */
	public static Integer getIdProduto(String idProduto) {

		int idExiste = buscarDado(0, listaProdutos.size() - 1, idProduto, listaProdutos);
		if (idExiste != -1) {

			return idExiste;

		}

		return -1;

	}

	/**
	 * M�todo para retorna a quantidade de produtos na lista de produtos
	 * 
	 * @return qtdTotalProduto - quantidade total de produtos
	 * 
	 */
	public static int qtdTotalProdutos() {

		int qtdTotalProduto = 0;

		if (listaProdutos.isEmpty()) {

			return qtdTotalProduto;

		} else {

			qtdTotalProduto = listaProdutos.size();
			return qtdTotalProduto;

		}

	}

	/*
	 * M�todo para o retorno dos nomes dos produtos que est�o no prato
	 * 
	 * @param listaIdProdutos Arraylist<String> - lista com o Id dos produtos
	 * 
	 * @return ArrayList<String> listaNomeProdutos - lista dos nomes dos produtos
	 * que compoem um prato
	 */
	public static ArrayList<String> getNomeProdutos(ArrayList<String> listaIdProdutos) {

		ArrayList<String> listaNomeProdutos = new ArrayList<>();

		for (String idProduto : listaIdProdutos) {

			int posiProduto = buscarDado(0, listaIdProdutos.size() - 1, idProduto, listaProdutos);

			if (posiProduto != -1) {

				listaNomeProdutos.add(listaProdutos.get(posiProduto).getNome());

			}

		}

		return listaNomeProdutos;

	}

	/**
	 * Metodo para limpar a lista de Produtos
	 */
	public static void limparLista() {

		DaoProdutos.listaProdutos.clear();

	}

	/**
	 * M�todo de busca bin�ria recursiva pelo id, que retorna a posi��o do
	 * objeto caso exista na lista.
	 * 
	 * @param inicio        Integer - Index inicial da lista
	 * @param fim           Integer - Index final da lista
	 * @param chaveId       String - Id a ser buscado
	 * @param listaProdutos ArrayList<Produtos> - lista de produtos
	 * @return Integer - Posi��o do objeto buscado na lista
	 */

	public static int buscarDado(int inicio, int fim, String chaveId, ArrayList<Produtos> listaProdutos) {

		int meio = (inicio + fim) / 2;

		if (inicio <= fim) {

			if (listaProdutos.get(meio).getId().equals(chaveId)) {

				return meio;

			} else if (Integer.parseInt(listaProdutos.get(meio).getId()) > Integer.parseInt(chaveId)) {

				return buscarDado(inicio, meio - 1, chaveId, listaProdutos);

			} else {

				return buscarDado(meio + 1, fim, chaveId, listaProdutos);

			}

		}

		return -1;

	}

	/**
	 * Metodo de redução da quantidade total de produtos apos uma venda
	 * 
	 * @param id         String - id do item que será reduzido
	 * @param qtdProduto double - quantidade do item que será reduzida
	 * @throws EstoqueInsuficienteException
	 */
	public static void reduzirEstoque(String id, double qtdProduto) throws EstoqueInsuficienteException {

		if (listaProdutos.get(Integer.parseInt(id)).getQtdProduto() > qtdProduto) {

			DaoProdutos.listaProdutos.get(Integer.parseInt(id))
					.setQtdProduto(listaProdutos.get(Integer.parseInt(id)).getQtdProduto() - qtdProduto);

		} else {

			throw new EstoqueInsuficienteException(listaProdutos.get(Integer.parseInt(id)).getQtdProduto(), qtdProduto);

		}

	}

}
