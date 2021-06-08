package vexpenses_desafiotecnico_agenda.view;

import java.awt.event.ActionEvent;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import vexpenses_desafiotecnico_agenda.model.Contato;

public class JanelaCadastroContato extends JFrame {


	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelo;
	private JPanel painelFundo;
	private JButton btnSalvar;
	private JButton btnLimpar;
	private JLabel lblCodigo;
	private JLabel lblNome;
	private JLabel lblTelefone;
	private JLabel lblEmail;
	private JLabel lblCep;
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private JTextField txtCep;

	private JanelaGrafica janelaGrafica;

	private int linhaSelecao;

	
	public JanelaCadastroContato(DefaultTableModel modelo, JanelaGrafica janelaGrafica)  {
		criarComponentesInsercao();
		this.modelo = modelo;
		this.janelaGrafica = janelaGrafica;
	}

	public JanelaCadastroContato(DefaultTableModel modelo, JanelaGrafica janelaGrafica, int codigo, int linha) {
		criarComponentesEdicao();
		this.modelo = modelo;
		this.janelaGrafica = janelaGrafica;
		this.linhaSelecao = linha;
		carregarDados(codigo);

	}

	private void criarComponentesInsercao() {
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(this::inserirContato);

		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(this::limparContato);

		lblNome = new JLabel("Nome");
		lblTelefone = new JLabel("Telefone");
		lblEmail = new JLabel("E-mail");
		lblCep = new JLabel("Cep");
		txtNome = new JTextField(20);
		txtTelefone = new JTextField(10);
		txtEmail = new JTextField(20);
		txtCep = new JTextField(10);

		painelFundo = new JPanel();
		painelFundo.add(lblNome);
		painelFundo.add(txtNome);
		painelFundo.add(lblTelefone);
		painelFundo.add(txtTelefone);
		painelFundo.add(lblEmail);
		painelFundo.add(txtEmail);
		painelFundo.add(lblCep);
		painelFundo.add(txtCep);
		painelFundo.add(btnSalvar);
		painelFundo.add(btnLimpar);

		add(painelFundo);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);

	}

	private void criarComponentesEdicao() {
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(this::editarContato);

		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(this::limparContato);

		lblCodigo = new JLabel("Código");
		lblNome = new JLabel("Nome");
		lblTelefone = new JLabel("Telefone");
		lblEmail = new JLabel("E-mail");
		lblCep = new JLabel("Cep");
		txtCodigo = new JTextField(3);
		txtCodigo.setEditable(false);//Não permite editar o código
		txtNome = new JTextField(20);
		txtTelefone = new JTextField(10);
		txtEmail = new JTextField(20);
		txtCep = new JTextField(10);

		painelFundo = new JPanel();
		painelFundo.add(lblCodigo);
		painelFundo.add(txtCodigo);
		painelFundo.add(lblNome);
		painelFundo.add(txtNome);
		painelFundo.add(lblTelefone);
		painelFundo.add(txtTelefone);
		painelFundo.add(lblEmail);
		painelFundo.add(txtEmail);
		painelFundo.add(lblCep);
		painelFundo.add(txtCep);
		painelFundo.add(btnSalvar);
		painelFundo.add(btnLimpar);

		add(painelFundo);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);

	}

	private void inserirContato(ActionEvent e) {
		Contato contato = new Contato();
		contato.setNome(txtNome.getText());
		contato.setTelefone(txtTelefone.getText());
		contato.setEmail(txtEmail.getText());
		contato.setCep(txtCep.getText());

		setVisible(false);
		if (janelaGrafica.getGerenciador().inserir(contato)) {
			janelaGrafica.carregarDados(modelo);
			JOptionPane.showMessageDialog(null, "Contato inserido com sucesso!");
		} else {
			JOptionPane.showMessageDialog(null, "Erro ao inserir o contato, Verifique.");

		}

	}

	private void carregarDados(int codigo) {
		Optional<Contato> op = janelaGrafica.getGerenciador().buscarPeloCodigo(codigo);
		if (op.isPresent()) {
			Contato contato = op.get();
			txtCodigo.setText(Integer.toString(contato.getCodigo()));
			txtNome.setText(contato.getNome());
			txtTelefone.setText(contato.getTelefone());
			txtEmail.setText(contato.getEmail());
			txtCep.setText(contato.getCep());
		}

	}

	private void editarContato(ActionEvent e) {
		Contato contato = new Contato();
		contato.setCodigo(Integer.parseInt(txtCodigo.getText()));
		contato.setNome(txtNome.getText());
		contato.setTelefone(txtTelefone.getText());
		contato.setEmail(txtEmail.getText());
		contato.setCep(txtCep.getText());

		setVisible(false);

		if (janelaGrafica.getGerenciador().editar(contato)) {
			modelo.removeRow(linhaSelecao);
			modelo.addRow(new Object[] { 
					contato.getCodigo(), 
					contato.getNome(), 
					contato.getTelefone(),
					contato.getEmail(), 
					contato.getCep(), 
					});
			JOptionPane.showMessageDialog(null, "Contato Editado com Sucesso!");
		} else {
			JOptionPane.showMessageDialog(null, "Erro ao editar contato!");
		}

	}

	private void limparContato(ActionEvent e) {
		txtNome.setText("");
		txtTelefone.setText("");
		txtEmail.setText("");
		txtCep.setText("");

	}

}
