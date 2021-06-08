package vexpenses_desafiotecnico_agenda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vexpenses_desafiotecnico_agenda.conexao.GerenciadorPool;
import vexpenses_desafiotecnico_agenda.model.Contato;

public class ContatoDAO {

	public GerenciadorPool con = null;

	public void salvarContato(Contato contato) {

		Connection con = null;
		PreparedStatement pStat = null;
		String sql = "insert into contato (id, nomecompleto, idade) values (pessoa_seq.nextval, ?, ?)";

		try {
			con = GerenciadorPool.getInstance().getConnection();
			pStat = con.prepareStatement(sql);

			pStat.setInt(1, contato.getCodigo());
			pStat.setString(2, contato.getNome());
			pStat.setString(3, contato.getTelefone());
			pStat.setString(4, contato.getEmail());
			pStat.setString(5, contato.getCep());
			pStat.executeUpdate();

			System.out.println("Foi inserido com sucesso");

		} catch (SQLException e) {

			System.out.println("Problema ao inserir uma pessoa");
			e.printStackTrace();
		}
	}

	public List<Contato> consultaTodos() {

		List<Contato> listaContatos = new ArrayList<Contato>();
		String sql = "select * from contato";
		Connection con = null;
		PreparedStatement pStat = null;

		try {
			con = GerenciadorPool.getInstance().getConnection();
			pStat = con.prepareStatement(sql);

			ResultSet rs = pStat.executeQuery();

			int id = -1;
			String nomeCompleto = "";
			String telefone = "";
			String email = "";
			String cep = "";

			while (rs.next()) {

				id = rs.getInt("codigo");
				nomeCompleto = rs.getString("nomecompleto");
				telefone = rs.getString("telefone");
				email = rs.getString("E-Mail");
				cep = rs.getString("Cep");

				listaContatos.add(new Contato(id, nomeCompleto, telefone, email, cep));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaContatos;

	}

	public Contato consultarContatoPorId(int id) {

		Connection con = null;
		PreparedStatement pStat = null;
		String sql = "select * from contato where id = ?";
		Contato contato = null;

		try {
			con = GerenciadorPool.getInstance().getConnection();
			pStat = con.prepareStatement(sql);
			pStat.setInt(1, id);
			ResultSet rs = pStat.executeQuery();

			int idP = -1;
			String nomeCompleto = "";
			String telefone = "";
			String email = "";
			String cep = "";

			if (rs.next()) {
				id = rs.getInt("id");
				nomeCompleto = rs.getString("nomecompleto");
				telefone = rs.getString("telefone");
				email = rs.getString("E-Mail");
				cep = rs.getString("Cep");
				contato = new Contato(idP, nomeCompleto, telefone, email, cep);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return contato;
	}

	public void consultarContatoPorNome(String nome) {

		Connection con = null;
		PreparedStatement pStat = null;
		String sql = "select * from contato nome like %";
		Contato contato = null;

	}

	public void editarContatoPorId(Contato contato) {

		Connection con = null;
		PreparedStatement pStat = null;
		String sql = "update contato set nomecompleto = ?, telefone = ?, email = ?, cep = ? where id = ?";

		try {
			con = GerenciadorPool.getInstance().getConnection();
			pStat = con.prepareStatement(sql);

			pStat.setInt(1, contato.getCodigo());
			pStat.setString(2, contato.getNome());
			pStat.setString(3, contato.getTelefone());
			pStat.setString(4, contato.getEmail());
			pStat.setString(5, contato.getCep());

			pStat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void removerContatoPorId(int id) {
		Connection con = null;
		PreparedStatement pStat = null;
		String sql = "delete from contato where id = ?";
		
		try {
			con = GerenciadorPool.getInstance().getConnection();
			pStat = con.prepareStatement(sql);	
			pStat.setInt(1, id);
			
			pStat.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
