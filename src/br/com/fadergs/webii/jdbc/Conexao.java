package br.com.fadergs.webii.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
	public static Connection getConnection() {
		Connection con = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Biblioteca", "postgres", "fadergs");
			System.out.println("Conectado com sucesso");
		} catch(SQLException e) {
			System.out.println("Falha na conexão");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
}
