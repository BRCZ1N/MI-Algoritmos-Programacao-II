package applicationmodel;

import java.util.ArrayList;

/**
 * Classe para objetos do tipo Pratos.
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @author Alex da Fonseca Dantas Junior
 * @version 1.0
 * @since 2022
 */

public class Clientes {
	
	private String id;
	private String nome;
	private String cpf;
	private String email;
	private String telefone;
	private ArrayList<String> idHistoricoCompras;
	
	
	/**
	 *Primeiro construtor da classe Clientes cria um cliente
	 *@param nome String - nome do cliente
	 *@param cpf String - Cpf do cliente
	 *@param email String - Email do cliente
	 *@param telefone String - telefone do cliente 
	 *@param idHistoricoCompras ArrayList<String> - Lista de id's das compras do cliente
	 */
	public Clientes(String nome, String cpf, String email, String telefone, ArrayList<String> idHistoricoCompras) {

		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
		this.idHistoricoCompras = idHistoricoCompras;

	}

	public Clientes() {

	}
	/**
	 *M�todo para retorno de id do cliente.
	 *@return String id
	 */
	
	public String getId() {
		return id;
	}
	/**
	 *M�todo para setar o valor de id do Cliente.
	 *@param id String
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 *M�todo para retorno de nome do cliente.
	 *@return String nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 *M�todo para setar o valor de nome do Cliente.
	 *@param nome String
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 *M�todo para retorno de cpf do cliente.
	 *@return String cpf
	 */
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public ArrayList<String> getIdHistoricoCompras() {
		return idHistoricoCompras;
	}

	public void setHistoricoCompras(ArrayList<String> idHistoricoCompras) {
		this.idHistoricoCompras = idHistoricoCompras;
	}

}
