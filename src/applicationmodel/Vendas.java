package applicationmodel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import applicationmodeldao.DaoPratos;


/**
 * Classe para objetos do tipo Vendas.
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @author Alex da Fonseca Dantas Junior
 * @version 1.0
 * @since 2022
 */

public class Vendas {

	private String id;
	private LocalDateTime diaHorario;
	private ArrayList<String> listaIdItens;
	private double precoTotal;
	private String tipoPagamento;

	/**
	 * O Construtor da classe Vendas cria uma venda a partir de um pre�o total,
	 * tipo de pagamento e uma lista de pratos, o atributo id receber� valor no
	 * gereciamento de vendas, assim como o atributo diaHorario.
	 * 
	 * @param tipoPagamento String - Tipo de pagamento da venda.
	 * @param listaIdItens  ArrayList<String> - Itens da venda
	 */

	public Vendas(ArrayList<String> listaIdItens, String tipoPagamento) {

		this.listaIdItens = listaIdItens;
		this.tipoPagamento = tipoPagamento;
		this.precoTotal = adicaoPrecoTotalVenda();
		this.diaHorario = LocalDateTime.now();

	}

	public Vendas() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * M�todo para retorno de id da venda.
	 * 
	 * @return String id
	 */

	public String getId() {
		return id;
	}

	/**
	 * M�todo para setar o valor de id da venda.
	 * 
	 * @param id String
	 */

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * M�todo para retorno do dia e hor�rio da venda.
	 * 
	 * @return Calendar diaHorario
	 */

	public LocalDateTime getDiaHorario() {
		return diaHorario;
	}

	/**
	 * M�todo para retorno da lista de itens da venda.
	 * 
	 * @return ArrayList<Pratos> itens
	 */

	public ArrayList<String> getListaIdItens() {
		return listaIdItens;
	}

	/**
	 * M�todo para setar a lista de itens da venda.
	 * 
	 * @param listaIdItens ArrayList<String>
	 */

	public void setListaIdItens(ArrayList<String> listaIdItens) {
		this.listaIdItens = listaIdItens;
	}

	/**
	 * M�todo para retorno do pre�o total da venda.
	 * 
	 * @return Double precoTotal
	 */

	public double getPrecoTotal() {
		return precoTotal;
	}

	/**
	 * M�todo para setar o pre�o total da venda.
	 * 
	 * @param precoTotal Double
	 */

	public void setPrecoTotal(double precoTotal) {
		this.precoTotal = precoTotal;
	}

	/**
	 * M�todo para retorno do tipo de pagamento da venda.
	 * 
	 * @return String tipoPagamento
	 */

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	/**
	 * M�todo para setar o tipo de pagamento da venda.
	 * 
	 * @param tipoPagamento String
	 */

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	/**
	 * M�todo para somar todos os itens da venda a partir da lista de
	 * itens(pratos) do gerenciamento de pratos, utilizando os id's armazenados na
	 * lista de itens da venda.
	 * 
	 * @return int qtdPratos
	 */

	public int getQtdPratosVenda() {

		int qtdPratos = listaIdItens.size();
		
		return qtdPratos; 

	}

	/**
	 * Metodo para fazer a adição dos preços dos itens que serão vendidos
	 * 
	 * @return double precoTotal
	 */

	public double adicaoPrecoTotalVenda() {

		double precoTotal = 0;

		for (String chaveId : listaIdItens) {

			if (buscarDado(0, DaoPratos.getListaPratos().size() - 1, chaveId) != -1) {

				precoTotal += DaoPratos.getListaPratos().get(Integer.parseInt(chaveId)).getPreco();

			}

		}

		return precoTotal;

	}

	/**
	 * M�todo para buscar o prato na lista do gerenciamento de pratos, para
	 * efetuar a soma do valor total.
	 * 
	 * @param inicio  Integer - Index inicial da lista
	 * @param fim     Integer - Index final da lista
	 * @param chaveId String - Id a ser buscado
	 * @return double precoTotal
	 */

	public int buscarDado(int inicio, int fim, String chaveId) {

		int meio = (inicio + fim)/ 2;

		if (inicio <= fim) {

			if (DaoPratos.getListaPratos().get(meio).getId().equals(chaveId)) {

				return meio;

			} else if (Integer.parseInt(DaoPratos.getListaPratos().get(meio).getId()) > Integer.parseInt(chaveId)) {

				return buscarDado(inicio, meio - 1, chaveId);

			} else {

				return buscarDado(meio + 1, fim, chaveId);

			}

		}

		return -1;
	}

}
