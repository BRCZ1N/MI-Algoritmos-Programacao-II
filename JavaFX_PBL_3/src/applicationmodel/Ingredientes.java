package applicationmodel;

/**
 * Classe associativa para armazenar e manipular dados da associação de pratos e venda
 * @author Bruno Campos de Oliveira Rocha
 * @author Alex da Fonseca Dantas Junior
 * @version 1.0
 * @since 2022
 */
public class Ingredientes {

	private String id;
	private double qtd;

	public Ingredientes(String id, double qtd) {

		this.id = id;
		this.qtd = qtd;

	}

	/**
	 * Metododo para obter o Id
	 * 
	 * @return String id
	 */
	public String getId() {

		return id;

	}

	/**
	 * Metododo para construir um id
	 * 
	 * @param id String
	 */
	public void setId(String id) {

		this.id = id;

	}

	/**
	 * Metododo para obter a quantidade de ingredientes
	 * 
	 * @return double qtd
	 */
	public double getQtd() {

		return qtd;

	}

	/**
	 * Metododo para criar a quantidade de ingredientes
	 * 
	 * @param qtd double
	 */
	public void setQtd(double qtd) {

		this.qtd = qtd;

	}

}
