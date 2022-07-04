package applicationmodeldao;

import java.time.LocalDate;
import java.util.ArrayList;
import applicationexeceptions.CamposNulosException;
import applicationexeceptions.CnpjJaExisteException;
import applicationexeceptions.CpfJaExisteException;
import applicationexeceptions.EntidadeComValoresNegativoException;
import applicationexeceptions.EstoqueInsuficienteException;
import applicationexeceptions.LoginExistenteException;
import applicationmodel.Clientes;
import applicationmodel.Fornecedores;
import applicationmodel.Ingredientes;
import applicationmodel.Pratos;
import applicationmodel.Produtos;
import applicationmodel.Usuarios;
import applicationmodel.Vendas;

public class DaoFacade {

	private DaoUsuarios daoUsuarios;
	private DaoClientes daoCliente;
	private DaoFornecedores daoFornecedor;
	private DaoPratos daoPrato;
	private DaoProdutos daoProduto;
	private DaoVendas daoVenda;

	public void daoInicializarSubsistemas() {

		daoUsuarios = new DaoUsuarios();
		daoProduto = new DaoProdutos();
		daoFornecedor = new DaoFornecedores();
		daoPrato = new DaoPratos();
		daoVenda = new DaoVendas();
		daoCliente = new DaoClientes();

	}

	/**
	 * Metodo para adição ou edição de um cliente por meio do facade
	 * 
	 * @param id                 String - Id do cliente
	 * @param cpf                String - Cpf do cliente
	 * @param email              String - Email do cliente
	 * @param telefone           String - telefone do cliente
	 * @param idHistoricoCompras ArrayList<String> - Lista de id's das compras do
	 *                           cliente
	 * @throws CamposNulosException
	 * @throws CpfJaExisteException
	 */
	public static void addEditCliente(String id, String nome, String cpf, String email, String telefone,
			ArrayList<String> idHistoricoCompras) throws CpfJaExisteException, CamposNulosException {

		Clientes cliente = new Clientes(nome, cpf, email, telefone, idHistoricoCompras);
		DaoClientes.addEditDados(cliente, id);

	}

	/**
	 * Metodo para adição ou edição de um usuario por meio do facade
	 * 
	 * @param id           String - Id do usuario
	 * @param loginUsuario String - Nome do usuario
	 * @param senhaUsuario String - Senha do usuario
	 * @param nomeUsuario  String - Nome do usuario
	 * @throws CamposNulosException
	 */
	public static void addEditUsuarios(String id, String loginUsuario, String senhaUsuario, String nomeUsuario)
			throws LoginExistenteException, CamposNulosException {

		Usuarios usuario = new Usuarios(loginUsuario, senhaUsuario, nomeUsuario);
		DaoUsuarios.addEditDados(usuario, id);

	}

	/**
	 * Metodo para adição ou edição de um fornecedores por meio do facade
	 * 
	 * @param id                   String - Id do fornecedor
	 * @param cnpj                 String - Cnpj do fornecedor
	 * @param nome                 String - Nome do fornecedor
	 * @param endereco             String - Endere�o do fornecedor
	 * @param idProdutosFornecedor ArrayList<String> - Lista de id's dos produtos do
	 *                             fornecedor
	 * @throws CamposNulosException
	 */
	public static void addEditFornecedores(String id, String cnpj, String nome, String endereco,
			ArrayList<String> idProdutosFornecedor) throws CnpjJaExisteException, CamposNulosException {

		Fornecedores fornecedor = new Fornecedores(cnpj, nome, endereco, idProdutosFornecedor);
		DaoFornecedores.addEditDados(fornecedor, id);
	}

	/**
	 * Metodo para adição ou edição de um produtos por meio do facade
	 * 
	 * @param id         String - Id do produto
	 * @param nome       String - Nome do produto.
	 * @param validade   String - Validade do produto.
	 * @param preco      Double - Pre�o do produto
	 * @param qtdProduto Double - Quantidade fisica do produto
	 * @param tipoQtd    String - Unidade de medida do produto
	 * @throws CamposNulosException
	 */
	public static void addEditProdutos(String id, String nome, LocalDate validade, double preco, double qtdProduto,
			String tipoQtd) throws EntidadeComValoresNegativoException, CamposNulosException {

		Produtos produtos = new Produtos(nome, validade, preco, qtdProduto, tipoQtd);
		DaoProdutos.addEditDados(produtos, id);
	}

	/**
	 * Metodo para adição ou edição de um prato por meio do facade
	 * 
	 * @param id              String - Id do prato
	 * @param nome            String - Nome do prato
	 * @param descricao       String - Descri��o do prato
	 * @param preco           Double - Pre�o do prato
	 * @param categoria       String - Categoria do prato
	 * @param composicaoPrato ArrayList<String> - Composi��o do prato
	 * @throws CamposNulosException
	 */

	public static void addEditPratos(String id, String nome, String descricao, double preco, String categoria,
			ArrayList<Ingredientes> composicaoPrato) throws EntidadeComValoresNegativoException, CamposNulosException {

		Pratos prato = new Pratos(nome, descricao, preco, categoria, composicaoPrato);
		DaoPratos.addEditDados(prato, id);

	}

	/**
	 * Metodo para adição ou edição de uma venda por meio do facade
	 * 
	 * @param id            String - Id da venda
	 * @param tipoPagamento String - Tipo de pagamento da venda.
	 * @param listaIdItens  ArrayList<String> - Itens da venda
	 * @throws CamposNulosException
	 */

	public static void addEditVendas(String id, ArrayList<String> listaIdItens, String tipoPagamento)
			throws EstoqueInsuficienteException, CamposNulosException {

		Vendas venda = new Vendas(listaIdItens, tipoPagamento);
		DaoVendas.addEditDados(venda, id);

	}

	/**
	 * M�todo para remover um cliente na lista de clientes por meio do facade
	 * 
	 * @param id String - Id para remover
	 * 
	 * 
	 */
	public static void removerDadosCliente(String id) {
		DaoClientes.removerDados(id);
	}

	/**
	 * M�todo para remover um usuario na lista de usuarios por meio do facade
	 * 
	 * @param id String - Id para remover
	 * 
	 * 
	 */
	public static void removerDadosUsuarios(String id) {
		DaoUsuarios.removerDados(id);
	}

	/**
	 * M�todo para remover um fornecedor na lista de fornecedores por meio do facade
	 * 
	 * @param id String - Id para remover
	 * 
	 * 
	 */
	public static void removerDadosFornecedores(String id) {
		DaoFornecedores.removerDados(id);
	}

	/**
	 * M�todo para remover um produto na lista de produtos por meio do facade
	 * 
	 * @param id String - Id para remover
	 * 
	 * 
	 */
	public static void removerDadosProdutos(String id) {
		DaoProdutos.removerDados(id);
	}

	/**
	 * M�todo para remover um prato na lista de pratos por meio do facade
	 * 
	 * @param id String - Id para remover
	 * 
	 * 
	 */
	public static void removerDadosPratos(String id) {
		DaoPratos.removerDados(id);
	}

	/**
	 * M�todo para remover uma venda na lista de vendas por meio do facade
	 * 
	 * @param id String - Id para remover
	 * 
	 * 
	 */
	public static void removerDadosVendas(String id) {
		DaoVendas.removerDados(id);
	}

}
