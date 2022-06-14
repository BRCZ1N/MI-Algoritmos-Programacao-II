package applicationmodeldao;

public class DaoFacade {
	private DaoUsuarios daoUsuarios;
//	private DaoClientes daoCliente;
//	private DaoFornecedores daoFornecedor;
//	private DaoPratos daoPrato;
//	private DaoProdutos daoProduto;
//	private DaoVendas daoVenda;
	
	
	
	public void daoInicializar() {
		daoUsuarios = new DaoUsuarios();
//		daoCliente = new DaoClientes();
//		daoFornecedor = new DaoFornecedor();
//		daoPrato = new DaoPratos();
//		daoProduto = new DaoProdutos();
//		daoVenda = new DaoVendas();
		
	}
}
