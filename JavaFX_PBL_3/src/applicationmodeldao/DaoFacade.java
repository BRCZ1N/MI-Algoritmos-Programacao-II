package applicationmodeldao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
	
	
	public void daoInicializar() {
		
		daoUsuarios = new DaoUsuarios();
		daoProduto = new DaoProdutos();
		daoFornecedor = new DaoFornecedores();
		daoPrato = new DaoPratos();
		daoVenda = new DaoVendas();
		daoCliente = new DaoClientes();
		
	}
	public void addEditCliente(String id, String nome, String cpf, String email, String telefone, ArrayList<String> idHistoricoCompras) throws CpfJaExisteException {
		Clientes cliente = new Clientes(nome,cpf, email, telefone, idHistoricoCompras);
		DaoClientes.addEditDados(cliente, id);
		
	}
	public void addEditUsuarios(String id, String loginUsuario, String senhaUsuario, String nomeUsuario) throws LoginExistenteException {
		Usuarios usuario = new Usuarios(loginUsuario,senhaUsuario, nomeUsuario );
		DaoUsuarios.addEditDados(usuario, id);
	
	}
	public void addEditFornecedores(String id, String cnpj, String nome, String endereco,  ArrayList<String> idProdutosFornecedor) throws CnpjJaExisteException {
		Fornecedores  fornecedor = new Fornecedores(cnpj, nome, endereco, idProdutosFornecedor);
		DaoFornecedores.addEditDados(fornecedor, id);
	}
	public void addEditProdutos(String id,String nome, LocalDate validade, double preco, double qtdProduto, String tipoQtd) throws EntidadeComValoresNegativoException {
		Produtos produtos = new Produtos(nome, validade, preco, qtdProduto,tipoQtd);
		DaoProdutos.addEditDados(produtos, id);
	}
	public void addEditPratos(String id, String nome, String descricao, double preco, String categoria, ArrayList<Ingredientes> composicaoPrato) throws EntidadeComValoresNegativoException {
		Pratos prato = new Pratos(nome, descricao, preco, categoria,composicaoPrato  );
		DaoPratos.addEditDados(prato, id);
		
	}
	public void addEditVendas( String id, LocalDateTime diaHorario,ArrayList<String> listaIdItens, double precoTotal, String tipoPagamento) throws EstoqueInsuficienteException {
		Vendas venda = new Vendas( listaIdItens,  tipoPagamento);
		DaoVendas.addEditDados(venda, id);
		
	}
	
	public void removerDadosCliente(String id) {
		DaoClientes.removerDados(id);
	}
	public void removerDadosUsuarios(String id) {
		DaoUsuarios.removerDados(id);
	}
	public void removerDadosFornecedores(String id) {
		DaoFornecedores.removerDados(id);
	}
	public void removerDadosProdutos(String id) {
		DaoProdutos.removerDados(id);
	}
	public void removerDadosPratos(String id) {
		DaoPratos.removerDados(id);
	}
	public void removerDadosVendas(String id) {
		DaoVendas.removerDados(id);
	}
	
}
