package applicationmodel;
import java.time.LocalDate;

/**
 * Classe para objetos do tipo Produtos.
 * @author Bruno Campos de Oliveira Rocha
 * @author Alex da Fonseca Dantas Junior
 * @version 1.0
 * @since 2022
 */

public class Produtos {
	
	private String id;
	private String nome;
	private LocalDate validade;
	private double qtdProduto;
	private String tipoQtd;
	private double preco;
	
	/**
	 *O Construtor da classe Produtos cria um produto a partir de um nome, validade e pre�o, o atributo id receber� valor no gereciamento de produtos.
	 *@param nome String - Nome do produto.
	 *@param validade String - Validade do produto.
	 *@param preco Double - Pre�o do produto
	 *@param qtdProduto Double - Quantidade fisica do produto
	 *@param tipoQtd String - Unidade de medida do produto
	 */
	
	public Produtos(String nome, LocalDate validade, double preco, double qtdProduto, String tipoQtd){
		
		this.nome = nome;
		this.validade = validade;
		this.preco = preco;
		this.qtdProduto = qtdProduto;
		this.tipoQtd = tipoQtd;
		
	}
	
	public Produtos() {
		
	}
	
	/**
	 *M�todo para retorno da quantidade do produto.
	 *@return Double qtdProduto
	 */
	
	public double getQtdProduto() {
		return qtdProduto;
	}
	/**
	 *M�todo para setar a quantidade do produto.
	 *@param qtdProduto Double
	 */

	public void setQtdProduto(double qtdProduto) {
		this.qtdProduto = qtdProduto;
	}
	/**
	 *M�todo para retorno do tipo do produto.
	 *@return String tipoQtd
	 */
	
	public String getTipoQtd() {
		return tipoQtd;
	}
	/**
	 *M�todo para setar a unidade de medida do produto.
	 *@param tipoQtd String
	 */

	public void setTipoQtd(String tipoQtd) {
		this.tipoQtd = tipoQtd;
	}

	/**
	 *M�todo para retorno de id do produto.
	 *@return String id
	 */
	
	public String getId() {
		return id;
	}
	
	/**
	 *M�todo para setar o valor de id do produto.
	 *@param id String
	 */
	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 *M�todo para retorno do nome do produto.
	 *@return String nome
	 */
	
	public String getNome() {
		return nome;
	}
	
	/**
	 *M�todo para setar o nome do produto.
	 *@param nome String
	 */
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 *M�todo para retorno da validade do produto.
	 *@return Calendar validade
	 */
	
	public LocalDate getValidade() {
		return validade;
	}
	
	/**
	 *M�todo para setar a validade do produto.
	 *@param validade Calendar
	 */
	
	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}
	
	/**
	 *M�todo para retorno de pre�o do produto.
	 *@return Double preco
	 */
	
	public double getPreco() {
		return preco;
	}
	
	/**
	 *M�todo para setar o valor de pre�o do produto.
	 *@param preco Double
	 */
	public void setPreco(double preco) {
		this.preco = preco;
	}
	


}
