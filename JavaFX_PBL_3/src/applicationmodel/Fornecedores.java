package applicationmodel;
import java.util.ArrayList;

/**
 * Classe para objetos do tipo Fornecedores.
 * @author Bruno Campos de Oliveira Rocha
 * @author Alex da Fonseca Dantas Junior
 * @version 1.0
 * @since 2022
 */
public class Fornecedores {
	
	private String id;
	private String cnpj;
	private String nome;
	private String endereco;
	private ArrayList<String> idProdutosFornecedor;
	
	
	public Fornecedores() {
		
		
	}
	
	/**
	 *Primeiro construtor da classe Fornecedores cria um fornecedor a partir de um nome, com cnpj existente, endere�o e uma lista de id's dos produtos do fornecedor.
	 *@param cnpj String - Cnpj do fornecedor
	 *@param nome String - Nome do fornecedor
	 *@param endereco String - Endere�o do fornecedor
	 *@param idProdutosFornecedor ArrayList<String> - Lista de id's dos produtos do fornecedor
	 */
	public Fornecedores(String cnpj, String nome, String endereco, ArrayList<String> idProdutosFornecedor){
		
		this.cnpj = cnpj;
		this.nome = nome;
		this.endereco = endereco;
		this.idProdutosFornecedor = idProdutosFornecedor;
		
	}
	
	/**
	 *Segundo construtor da classe Fornecedores cria um fornecedor a partir de um nome, endereco e uma lista de id's dos produtos do fornecedor, al�m disso esse construtor
	 *realiza a atribui��o do cnpj para casos em que o fornecedor n�o possua.
	 *@param nome String - Nome do fornecedor
	 *@param endereco String - Endere�o do fornecedor
	 *@param idProdutosFornecedor ArrayList<String> - Lista de id's dos produtos do fornecedor
	 */
	public Fornecedores(String nome, String endereco, ArrayList<String> idProdutosFornecedor){
		
		this.cnpj = "Sem CNPJ";
		this.nome = nome;
		this.endereco = endereco;
		this.idProdutosFornecedor = idProdutosFornecedor;
		
	}

	/**
	 *M�todo para retorno de id do fornecedor.
	 *@return String id
	 */
	

	public String getId() {
		return id;
	}
	
	/**
	 *M�todo para setar o valor de id do fornecedor.
	 *@param id String
	 */

	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 *M�todo para retorno do cnpj do fornecedor.
	 *@return String cnpj
	 */

	public String getCnpj() {
		return cnpj;
	}

	/**
	 *M�todo para setar o valor do cnpj do fornecedor.
	 *@param cnpj String
	 */
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	/**
	 *M�todo para retorno do nome do fornecedor.
	 *@return String nome
	 */

	public String getNome() {
		return nome;
	}

	/**
	 *M�todo para setar o valor nome do fornecedor.
	 *@param nome String
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 *M�todo para retorno do endere�o do fornecedor.
	 *@return String endereco
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 *M�todo para setar o valor endere�o do fornecedor.
	 *@param endereco String
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 *M�todo para retorno da lista de id's de produtos do fornecedor.
	 *@return ArrayList<String> idProdutosFornecedor
	 */
	public ArrayList<String> getIdProdutosFornecedor() {
		return idProdutosFornecedor;
	}

	/**
	 *M�todo para setar a lista de id's de produtos do fornecedor.
	 *@param idProdutosFornecedor ArrayList<String>
	 */
	public void setIdProdutosFornecedor(ArrayList<String> idProdutosFornecedor) {
		this.idProdutosFornecedor = idProdutosFornecedor;
	}
	

}
