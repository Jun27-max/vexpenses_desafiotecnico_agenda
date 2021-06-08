package vexpenses_desafiotecnico_agenda.conexao;

import java.sql.Connection;
import java.sql.SQLException;
//import org.apache.commons.dbcp2.BasicDataSource;



public class GerenciadorPool {
	
	//private BasicDataSource ds;
	
	
	private static GerenciadorPool instance;
	
	public static GerenciadorPool getInstance() {
		if (instance == null){
			instance = new GerenciadorPool();
		}
		return instance;
	}
	
	private GerenciadorPool() {
		/*ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/Image","username","password");
		ds.setUsername("Vexpenses_2021");
		ds.setPassword("root");*/
	}

	public Connection getConnection() throws SQLException{
		//return ds.getConnection();
		return getConnection();
	}

	
	
	
	
	
	

}
