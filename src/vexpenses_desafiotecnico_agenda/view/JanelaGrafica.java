package vexpenses_desafiotecnico_agenda.view;

import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vexpenses_desafiotecnico_agenda.controller.GerenciadorContatos;

public class JanelaGrafica extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel painelFundo;
	private JPanel painelBotoes;
	private JTable tabelaContatos;
	private JScrollPane scroll;
	private JButton btnInserir;
	private JButton btnExcluir;
	private JButton btnEditar;
	private DefaultTableModel modelo;

	private GerenciadorContatos gerenciador;

	public JanelaGrafica() {
		gerenciador = new GerenciadorContatos();
		modelo = new DefaultTableModel();
		criarTabela();
		criarComponentes();
	}

	private void criarTabela() {
		tabelaContatos = new JTable(modelo);
		modelo.addColumn("Código");
		modelo.addColumn("Nome");
		modelo.addColumn("Telefone");
		modelo.addColumn("Email");
		modelo.addColumn("Cep");

		tabelaContatos.getColumnModel().getColumn(0).setPreferredWidth(10);
		tabelaContatos.getColumnModel().getColumn(1).setPreferredWidth(120);
		tabelaContatos.getColumnModel().getColumn(2).setPreferredWidth(80);
		tabelaContatos.getColumnModel().getColumn(3).setPreferredWidth(120);
		tabelaContatos.getColumnModel().getColumn(4).setPreferredWidth(80);
		carregarDados(modelo);

	}

	private void criarComponentes() {
		btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(this::inserirContato);

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(this::removerContato);

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(this::editarContato);

		painelBotoes = new JPanel();
		painelBotoes.setBorder(BorderFactory.createTitledBorder("Ações"));
		scroll = new JScrollPane(tabelaContatos);
		painelFundo = new JPanel();
		painelFundo.add(scroll);
		painelBotoes.add(btnInserir);
		painelBotoes.add(btnEditar);
		painelBotoes.add(btnExcluir);
		painelFundo.add(painelBotoes);
		add(painelFundo);

		setTitle("contatos");
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void carregarDados(DefaultTableModel modelo) {
		modelo.setNumRows(0);

		gerenciador.getContatos().forEach(c -> {
			modelo.addRow(new Object[] { 
					c.getCodigo(), 
					c.getNome(), 
					c.getTelefone(), 
					c.getEmail(), 
					c.getCep() 
			});
		});

	}

	public GerenciadorContatos getGerenciador() {
		return gerenciador;
	}

	private void inserirContato(ActionEvent e) {
		JanelaCadastroContato janelaCadastro = new JanelaCadastroContato(modelo, this);
		janelaCadastro.setVisible(true);

	}

	private void editarContato(ActionEvent e) {
		int linhaSelecionada = -1;
		linhaSelecionada = tabelaContatos.getSelectedRow();
		if (linhaSelecionada >= 0) {
			int codigo = (int) tabelaContatos.getValueAt(linhaSelecionada, 0);
			JanelaCadastroContato janelaCadastro = new JanelaCadastroContato(modelo, this, codigo, linhaSelecionada);
			janelaCadastro.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "Por Favor, Selecione o Contato");
		}

	}

	private void removerContato(ActionEvent e) {
		int linhaSelecionada = -1;
		linhaSelecionada = tabelaContatos.getSelectedRow();
		if (linhaSelecionada >= 0) {
			int codigo = (int) tabelaContatos.getValueAt(linhaSelecionada, 0);
			if (gerenciador.remover(codigo)) {
				modelo.removeRow(linhaSelecionada);
				JOptionPane.showMessageDialog(null, "Contato removido com sucesso!");
			} else {
				JOptionPane.showMessageDialog(null, "É necessário selecionar uma linha.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "É necessário selecionar uma linha.");
		}

	}

}
