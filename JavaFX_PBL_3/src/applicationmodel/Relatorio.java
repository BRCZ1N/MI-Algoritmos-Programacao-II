package applicationmodel;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import applicationmodeldao.DaoClientes;
import applicationmodeldao.DaoFornecedores;
import applicationmodeldao.DaoPratos;
import applicationmodeldao.DaoProdutos;
import applicationmodeldao.DaoVendas;

/**
 * Classe para gerar relatorio de venda, pratos e produtos
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @author Alex da Fonseca Dantas Junior
 * @version 1.0
 * @since 2022
 */
public class Relatorio {

	private static String dHRelatorio;
	private static int idProdutosPdf = 0;
	private static int idFornecedoresPdf = 0;
	private static int idVendasPdf = 0;
	private static int idClientesPdf = 0;

	/**
	 * Metodo para definir o dia e o horario do relatorio
	 * 
	 * @param diaHorario LocalDateTime
	 */
	public static void setDiaHorario(LocalDateTime diaHorario) {

		DateTimeFormatter dataH = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String dHRelatorio = diaHorario.format(dataH);
		Relatorio.dHRelatorio = dHRelatorio;

	}

	/**
	 * Metodo para a criação do arquivo PDF de produtos
	 */

	public static void gerarRelatorioProdutos(ArrayList<Produtos> listaProdutos) {

		setDiaHorario(LocalDateTime.now());
		Document d = new Document(PageSize.A4);
		d.setMargins(40f, 40f, 40f, 40f);

		try {

			if (idProdutosPdf >= 1) {

				PdfWriter.getInstance(d, new FileOutputStream("RelatorioProdutos(" + idProdutosPdf + ").pdf"));

			} else {

				PdfWriter.getInstance(d, new FileOutputStream("RelatorioProdutos.pdf"));

			}

			d.open();
			Paragraph p = new Paragraph("Relatorio de produtos - " + dHRelatorio);
			d.add(p);
			p = new Paragraph(" ");
			d.add(p);

			p = new Paragraph("Quantidade total de produtos:" + DaoProdutos.qtdTotalProdutos(listaProdutos));
			d.add(p);
			p = new Paragraph(" ");
			d.add(p);
			
			p = new Paragraph("Quantidade total de produtos expirados:" + DaoProdutos.qtdTotalProdutosExpirados(listaProdutos));
			d.add(p);
			

			PdfPTable tabela = new PdfPTable(5);
			tabela.setTotalWidth(100f);
			tabela.setWidths(new float[] { 12f, 22f, 22f, 22f, 22f });

			PdfPCell celulaPDF1 = new PdfPCell(new Paragraph("Id"));
			celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF2 = new PdfPCell(new Paragraph("Nome"));
			celulaPDF2.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF3 = new PdfPCell(new Paragraph("Quantidade(Unidade de medida)"));
			celulaPDF3.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF4 = new PdfPCell(new Paragraph("Validade"));
			celulaPDF4.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF5 = new PdfPCell(new Paragraph("Preco"));
			celulaPDF5.setHorizontalAlignment(Element.ALIGN_CENTER);

			tabela.addCell(celulaPDF1);
			tabela.addCell(celulaPDF2);
			tabela.addCell(celulaPDF3);
			tabela.addCell(celulaPDF4);
			tabela.addCell(celulaPDF5);

			for (Produtos produto : listaProdutos) {

				celulaPDF1 = new PdfPCell(new Paragraph(produto.getId()));
				celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);
				celulaPDF2 = new PdfPCell(new Paragraph(produto.getNome()));
				celulaPDF2.setHorizontalAlignment(Element.ALIGN_CENTER);
				celulaPDF3 = new PdfPCell(
						new Paragraph(Double.toString(produto.getQtdProduto()) + " " + produto.getTipoQtd()));
				celulaPDF3.setHorizontalAlignment(Element.ALIGN_CENTER);
				celulaPDF4 = new PdfPCell(new Paragraph(produto.getValidade().toString()));
				celulaPDF4.setHorizontalAlignment(Element.ALIGN_CENTER);
				celulaPDF5 = new PdfPCell(new Paragraph("R$ " + Double.toString((produto.getPreco()))));
				celulaPDF5.setHorizontalAlignment(Element.ALIGN_CENTER);

				tabela.addCell(celulaPDF1);
				tabela.addCell(celulaPDF2);
				tabela.addCell(celulaPDF3);
				tabela.addCell(celulaPDF4);
				tabela.addCell(celulaPDF5);

			}

			d.add(tabela);
			d.close();
			if (idProdutosPdf >= 1) {

				Desktop.getDesktop().open(new File("RelatorioProdutos(" + idProdutosPdf + ").pdf"));

			} else {

				Desktop.getDesktop().open(new File("RelatorioProdutos.pdf"));

			}

			idProdutosPdf += 1;

		} catch (Exception e) {

		}

	}

	/**
	 * Metodo para a criação do arquivo PDF de fornecedores
	 */
	public static void gerarRelatorioFornecedores(ArrayList<Fornecedores> listaFornecedores) {

		setDiaHorario(LocalDateTime.now());
		Document d = new Document();

		try {

			if (idFornecedoresPdf >= 1) {

				PdfWriter.getInstance(d, new FileOutputStream("RelatorioFornecedores(" + idFornecedoresPdf + ").pdf"));

			} else {

				PdfWriter.getInstance(d, new FileOutputStream("RelatorioFornecedores.pdf"));

			}

			d.open();
			Paragraph p = new Paragraph("Relatorio fornecedores - " + dHRelatorio);
			d.add(p);

			p = new Paragraph(" ");
			d.add(p);

			p = new Paragraph("Quantidade total de fornecedores:" + DaoFornecedores.getQtdTotalFornecedores());
			d.add(p);

			p = new Paragraph(" ");
			d.add(p);

			PdfPTable tabela = new PdfPTable(5);
			tabela.setTotalWidth(100f);
			tabela.setWidths(new float[] { 12f, 22f, 22f, 22f, 22f });

			PdfPCell celulaPDF1 = new PdfPCell(new Paragraph("Id"));
			celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF2 = new PdfPCell(new Paragraph("Nome"));
			celulaPDF2.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF3 = new PdfPCell(new Paragraph("CNPJ"));
			celulaPDF3.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF4 = new PdfPCell(new Paragraph("Endereco"));
			celulaPDF4.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF5 = new PdfPCell(new Paragraph("Produtos"));
			celulaPDF5.setHorizontalAlignment(Element.ALIGN_CENTER);

			tabela.addCell(celulaPDF1);
			tabela.addCell(celulaPDF2);
			tabela.addCell(celulaPDF3);
			tabela.addCell(celulaPDF4);
			tabela.addCell(celulaPDF5);

			for (Fornecedores fornecedores : listaFornecedores) {

				celulaPDF1 = new PdfPCell(new Paragraph(fornecedores.getId()));
				celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);
				celulaPDF2 = new PdfPCell(new Paragraph(fornecedores.getNome()));
				celulaPDF2.setHorizontalAlignment(Element.ALIGN_CENTER);
				celulaPDF3 = new PdfPCell(new Paragraph(fornecedores.getCnpj()));
				celulaPDF3.setHorizontalAlignment(Element.ALIGN_CENTER);
				celulaPDF4 = new PdfPCell(new Paragraph(fornecedores.getEndereco()));
				celulaPDF4.setHorizontalAlignment(Element.ALIGN_CENTER);
				celulaPDF5 = new PdfPCell(new Paragraph("Produtos"));
				celulaPDF5.setHorizontalAlignment(Element.ALIGN_CENTER);

				List lista = new List();

				for (String nomeFornecedorProduto : DaoProdutos
						.getNomeProdutos(fornecedores.getIdProdutosFornecedor())) {

					lista.add(new ListItem(nomeFornecedorProduto));

				}

				celulaPDF5.addElement(lista);

				tabela.addCell(celulaPDF1);
				tabela.addCell(celulaPDF2);
				tabela.addCell(celulaPDF3);
				tabela.addCell(celulaPDF4);
				tabela.addCell(celulaPDF5);

			}

			d.add(tabela);
			d.close();
			if (idFornecedoresPdf >= 1) {

				Desktop.getDesktop().open(new File("RelatorioFornecedores(" + idFornecedoresPdf + ").pdf"));

			} else {

				Desktop.getDesktop().open(new File("RelatorioFornecedores.pdf"));

			}
			idFornecedoresPdf++;

		} catch (Exception e) {

		}
	}


	/**
	 * Metodo para a criação do arquivo PDF de vendas
	 */
	public static void gerarRelatorioVendas(ArrayList<Vendas> listaVendas) {

		setDiaHorario(LocalDateTime.now());
		Document d = new Document();

		try {

			if (idVendasPdf >= 1) {

				PdfWriter.getInstance(d, new FileOutputStream("RelatorioVendas(" + idVendasPdf + ").pdf"));

			} else {

				PdfWriter.getInstance(d, new FileOutputStream("RelatorioVendas.pdf"));

			}

			d.open();
			Paragraph p = new Paragraph("Relatorio Vendas - " + dHRelatorio);
			d.add(p);
			p = new Paragraph(" ");
			d.add(p);

			p = new Paragraph("Quantidade total de pratos vendidos: " + DaoVendas.numTotalPratosVendidos());
			d.add(p);

			p = new Paragraph(" ");
			d.add(p);

			p = new Paragraph("Valor total das Vendas(R$): " + DaoVendas.valorTotalVendas());
			d.add(p);

			p = new Paragraph(" ");
			d.add(p);

			PdfPTable tabela = new PdfPTable(5);
			tabela.setTotalWidth(100f);
			tabela.setWidths(new float[] { 12f, 20f, 20f, 20f, 28f });

			PdfPCell celulaPDF1 = new PdfPCell(new Paragraph("Id"));
			celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF2 = new PdfPCell(new Paragraph("Data/horario"));
			celulaPDF2.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF3 = new PdfPCell(new Paragraph("Quantidade de Itens"));
			celulaPDF3.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF4 = new PdfPCell(new Paragraph("Valor"));
			celulaPDF4.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF5 = new PdfPCell(new Paragraph("Pratos"));
			celulaPDF5.setHorizontalAlignment(Element.ALIGN_CENTER);

			tabela.addCell(celulaPDF1);
			tabela.addCell(celulaPDF2);
			tabela.addCell(celulaPDF3);
			tabela.addCell(celulaPDF4);
			tabela.addCell(celulaPDF5);

			for (Vendas venda : listaVendas) {

				celulaPDF1 = new PdfPCell(new Paragraph(venda.getId()));
				celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);
				celulaPDF2 = new PdfPCell(new Paragraph(venda.getDiaHorario().toString()));
				celulaPDF2.setHorizontalAlignment(Element.ALIGN_CENTER);
				celulaPDF3 = new PdfPCell(new Paragraph(Integer.toString(venda.getQtdPratosVenda())));
				celulaPDF3.setHorizontalAlignment(Element.ALIGN_CENTER);
				celulaPDF4 = new PdfPCell(new Paragraph("R$" + Double.toString(venda.getPrecoTotal())));
				celulaPDF4.setHorizontalAlignment(Element.ALIGN_CENTER);

				List lista = new List();

				for (String idPrato : venda.getListaIdItens()) {

					lista.add(new ListItem(DaoPratos.getPrato(idPrato).getNome() + "("
							+ (DaoPratos.getPrato(idPrato).getCategoria() + ")")));

				}

				celulaPDF5 = new PdfPCell();
				celulaPDF5.addElement(lista);
				celulaPDF5.setHorizontalAlignment(Element.ALIGN_CENTER);

				tabela.addCell(celulaPDF1);
				tabela.addCell(celulaPDF2);
				tabela.addCell(celulaPDF3);

				tabela.addCell(celulaPDF4);
				tabela.addCell(celulaPDF5);

			}

			d.add(tabela);
			d.close();
			if (idVendasPdf >= 1) {

				Desktop.getDesktop().open(new File("RelatorioVendas(" + idVendasPdf + ").pdf"));

			} else {

				Desktop.getDesktop().open(new File("RelatorioVendas.pdf"));

			}
			idVendasPdf++;

		} catch (Exception e) {

		}

	}

	public static void gerarRelatorioClientes(Clientes cliente) {

		setDiaHorario(LocalDateTime.now());
		Document d = new Document();
		DateTimeFormatter formatarDHorario = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

		try {

			if (idClientesPdf >= 1) {

				PdfWriter.getInstance(d, new FileOutputStream(
						"RelatorioCliente(" + idClientesPdf + ") - " + cliente.getNome() + ".pdf"));

			} else {

				PdfWriter.getInstance(d, new FileOutputStream("RelatorioCliente - " + cliente.getNome() + ".pdf"));

			}

			d.open();
			Paragraph p = new Paragraph("Relatorio de cliente - " + dHRelatorio);
			d.add(p);

			p = new Paragraph(" ");
			d.add(p);

			p = new Paragraph("Dados do cliente:");
			d.add(p);

			p = new Paragraph(" ");
			d.add(p);

			p = new Paragraph("Id: " + cliente.getId());
			d.add(p);

			p = new Paragraph("Nome: " + cliente.getNome());
			d.add(p);

			p = new Paragraph("Cpf: " + cliente.getCpf());
			d.add(p);

			p = new Paragraph(" ");
			d.add(p);

			p = new Paragraph("Valor total gasto pelo cliente: "
					+ DaoClientes.valorTotalVendasCliente(cliente.getIdHistoricoCompras()));
			d.add(p);

			p = new Paragraph(" ");
			d.add(p);

			PdfPTable tabela = new PdfPTable(5);
			tabela.setTotalWidth(100f);
			tabela.setWidths(new float[] { 12f, 20f, 20f, 20f, 28f });

			PdfPCell celulaPDF1 = new PdfPCell(new Paragraph("Id"));
			celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF2 = new PdfPCell(new Paragraph("Data/horario"));
			celulaPDF2.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF3 = new PdfPCell(new Paragraph("Quantidade de Itens"));
			celulaPDF3.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF4 = new PdfPCell(new Paragraph("Valor"));
			celulaPDF4.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell celulaPDF5 = new PdfPCell(new Paragraph("Pratos"));
			celulaPDF5.setHorizontalAlignment(Element.ALIGN_CENTER);

			tabela.addCell(celulaPDF1);
			tabela.addCell(celulaPDF2);
			tabela.addCell(celulaPDF3);
			tabela.addCell(celulaPDF4);
			tabela.addCell(celulaPDF5);

			for (Vendas venda : DaoVendas.getListaVenda(cliente.getIdHistoricoCompras())) {

				celulaPDF1 = new PdfPCell(new Paragraph(venda.getId()));
				celulaPDF1.setHorizontalAlignment(Element.ALIGN_CENTER);
				celulaPDF2 = new PdfPCell(new Paragraph(formatarDHorario.format(venda.getDiaHorario())));
				celulaPDF2.setHorizontalAlignment(Element.ALIGN_CENTER);
				celulaPDF3 = new PdfPCell(new Paragraph(Integer.toString(venda.getQtdPratosVenda())));
				celulaPDF3.setHorizontalAlignment(Element.ALIGN_CENTER);
				celulaPDF4 = new PdfPCell(new Paragraph("R$" + Double.toString(venda.getPrecoTotal())));
				celulaPDF4.setHorizontalAlignment(Element.ALIGN_CENTER);

				List lista = new List();

				for (String idPrato : venda.getListaIdItens()) {

					lista.add(new ListItem(DaoPratos.getPrato(idPrato).getNome() + "("
							+ (DaoPratos.getPrato(idPrato).getCategoria() + ")")));

				}

				celulaPDF5 = new PdfPCell();
				celulaPDF5.addElement(lista);
				celulaPDF5.setHorizontalAlignment(Element.ALIGN_CENTER);

				tabela.addCell(celulaPDF1);
				tabela.addCell(celulaPDF2);
				tabela.addCell(celulaPDF3);

				tabela.addCell(celulaPDF4);
				tabela.addCell(celulaPDF5);

			}

			d.add(tabela);
			d.close();
			if (idClientesPdf >= 1) {

				Desktop.getDesktop()
						.open(new File("RelatorioCliente(" + idClientesPdf + ") - " + cliente.getNome() + ".pdf"));

			} else {

				Desktop.getDesktop().open(new File("RelatorioCliente - " + cliente.getNome() + ".pdf"));

			}
			idClientesPdf++;

		} catch (Exception e) {

		}

	}

}
