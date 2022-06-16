package applicationmodeldao;

public class DaoFacade {
	
	private DaoUsuarios daoUsuarios;
	private DaoClientes daoCliente;
	private DaoFornecedores daoFornecedor;
	private DaoPratos daoPrato;
	private DaoProdutos daoProduto;
	private DaoVendas daoVenda;
	
	
	
	public void daoInicializar() {
		
		daoUsuarios = new DaoUsuarios();
		daoProduto = new DaoProdutos();
		daoFornecedor = new DaoFornecedores();
		daoPrato = new DaoPratos();
		daoVenda = new DaoVendas();
		daoCliente = new DaoClientes();
		
	}
}
