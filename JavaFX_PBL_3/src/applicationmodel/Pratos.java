package applicationmodel;
import java.util.ArrayList;

/**
 * Classe para objetos do tipo Pratos.
 * @author Bruno Campos de Oliveira Rocha 
 * @author Alex da Fonseca Dantas Junior
 * @version 1.0
 * @since 2022
 */

public class Pratos {
	
	private String id;
	private String nome;
	private String descricao;
	private double preco;
	private String categoria;
	private ArrayList<Ingredientes> composicaoPrato;

	
	/**
	 *O Construtor da classe Pratos cria um prato a partir de um nome, com descri��o, pre�o, categoria e uma lista de produtos, o atributo id receber� valor no gereciamento de pratos.
	 *@param nome String - Nome do prato
	 *@param descricao String - Descri��o do prato
	 *@param preco Double - Pre�o do prato
	 *@param categoria String - Categoria do prato
	 *@param composicaoPrato ArrayList<String> - Composi��o do prato
	 */
	
	public Pratos(String nome, String descricao, double preco, String categoria, ArrayList<Ingredientes> composicaoPrato){
		
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
		this.composicaoPrato = composicaoPrato;
		
	}
	
	public Pratos() {
		
	}

	/**
	 *Metodo para retorno de id do prato.
	 *@return String id
	 */

	public String getId() {
		return id;
	}
	
	/**
	 *Metodo para setar o valor de id do fornecedor.
	 *@param id String
	 */

	public void setId(String id) {
		this.id = id;
	}

	/**
	 *Metodo para retorno de nome do prato.
	 *@return String nome
	 */
	
	public String getNome() {
		return nome;
	}
	
	/**
	 *Metodo para setar o valor de nome do prato.
	 *@param nome String
	 */

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 *Metodo para retorno de descri��o do prato.
	 *@return String descricao
	 */

	public String getDescricao() {
		return descricao;
	}
	
	/**
	 *Metodo para setar o valor de descri��o do prato.
	 *@param descricao String
	 */

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	/**
	 *Metodo para retorno de pre�o do prato.
	 *@return Double preco
	 */

	public double getPreco() {
		return preco;
	}
	
	/**
	 *Metodo para setar o valor de pre�o do prato.
	 *@param preco Double
	 */

	public void setPreco(double preco) {
		this.preco = preco;
	}

	/**
	 *Metodo para retorno de categoria do prato.
	 *@return String categoria
	 */
	
	public String getCategoria() {
		return categoria;
	}
	
	/**
	 *Metodo para setar o valor de categoria do prato.
	 *@param categoria String
	 */

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 *Metodo para retornar a lista da composicaoo de produtos do prato.
	 *@return ArrayList<Ingredientes> composicaoIdPratos
	 */
	
	public ArrayList<Ingredientes> getComposicaoPrato() {
		return composicaoPrato;
	}
	
	/**
	 *Metodo para setar a lista de composicaoo de produtos do prato.
	 *@param composicaoPrato ArrayList<Ingredientes>
	 */

	public void setComposicaoPrato(ArrayList<Ingredientes> composicaoPrato) {
		this.composicaoPrato = composicaoPrato;
	}	
	

}
