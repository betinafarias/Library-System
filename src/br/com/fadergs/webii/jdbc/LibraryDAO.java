package br.com.fadergs.webii.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fadergs.webii.entidades.Library;

public class LibraryDAO {
	private Connection con = Conexao.getConnection();
	
	public void insert (Library library) {
		
		System.out.println("DAO Library!!!");
		System.out.println(library);
		
		String sql = "INSERT INTO library (name, address) values (?, ?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, library.getName());
			preparador.setString(2, library.getAddress());
			preparador.execute();
			preparador.close();
			
		} catch (SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public void update (Library library) {
		String sql = "UPDATE library SET name = ?, address = ? WHERE id = ?";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, library.getName());
			preparador.setString(2, library.getAddress());
			preparador.setInt(3, library.getId());
			preparador.execute();
			preparador.close();
			
		} catch (SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public void delete (Library library) {	
		System.out.println("DELETAR ID");
		System.out.println(library.getId());
		String sql = "DELETE FROM library WHERE id = ?";
		System.out.println(sql);
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, library.getId());
			preparador.execute();
			preparador.close();
			
		} catch (SQLException ex){
			ex.printStackTrace();
		}
	}
	
	
	public List<Library> fetchAll () {
		String sql = "SELECT * FROM library";
		List<Library> libraries = new ArrayList<Library>();
		
		try {
			
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet result = preparador.executeQuery();
			
			while(result.next()){
				Library library = new Library();
				library.setName(result.getString("name"));
				library.setAddress(result.getString("address"));
				library.setId(result.getInt("id"));
				libraries.add(library);
			}
			
			
			preparador.close();
			
		} catch (SQLException ex){
			ex.printStackTrace();
		}
		
		return libraries;
	}
}
