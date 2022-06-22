package applicationmodel;

/**
 * Classe que possui atributos e metodos acessores para definir os tipos de pagamento.
 * @author Bruno Campos de Oliveira Rocha
 * @author Alex da Fonseca Dantas Junior
 * @version 1.0
 * @since 2022
 */


public class TipoPagamento {

	private static final String TIPO_DE_PAGAMENTO1 = "Cartao de débito";
	private static final String TIPO_DE_PAGAMENTO2 = "Cartao de crédito";
	private static final String TIPO_DE_PAGAMENTO3 = "Á vista";
	private static final String TIPO_DE_PAGAMENTO4 = "Pix";
	
	/**
	 *M�todo para retorno do nome do tipo de pagamento referente ao Cart�o de debito.
	 *@return String TIPO_DE_PAGAMENTO1
	 */
	public static String getTipoDePagamento1() {
		return TIPO_DE_PAGAMENTO1;
	}
	
	/**
	 *M�todo para retorno do nome do tipo de pagamento referente ao cart�o de cr�dito.
	 *@return String TIPO_DE_PAGAMENTO2
	 */
	
	public static String getTipoDePagamento2() {
		return TIPO_DE_PAGAMENTO2;
	}
	
	/**
	 *M�todo para retorno do nome do tipo de pagamento referente ao pagamento � vista.
	 *@return String TIPO_DE_PAGAMENTO3
	 */
	
	public static String getTipoDePagamento3() {
		return TIPO_DE_PAGAMENTO3;
	}
	
	/**
	 *M�todo para retorno do nome do tipo de pagamento referente ao pagamento pix.
	 *@return String TIPO_DE_PAGAMENTO4
	 */
	public static String getTipoDePagamento4() {
		return TIPO_DE_PAGAMENTO4;
	}

	
}
