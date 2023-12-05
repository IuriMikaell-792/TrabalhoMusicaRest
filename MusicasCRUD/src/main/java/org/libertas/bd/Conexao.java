package org.libertas.bd;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	private Connection connection;
	
	public Conexao() {
		try {
			Class.forName("org.postgresql.Driver");
			
			String usuario = "postgres";
			String senha = "123";
			String url = "jdbc:postgresql://localhost/musicas?user=" + usuario + "&password=" + senha + "&ssl=false";
			
			try {
				connection = DriverManager.getConnection(url);
				System.out.println("Conexão realizada com sucesso!");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public void desconectar() {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}