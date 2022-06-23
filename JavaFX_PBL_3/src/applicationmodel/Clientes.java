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

	public Clientes(String nome, String cpf, String email, String telefone, ArrayList<String> idHistoricoCompras) {

		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
		this.idHistoricoCompras = idHistoricoCompras;

	}

	public Clientes() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

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
