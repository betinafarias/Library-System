package br.com.fadergs.webii.jdbc;
import br.com.fadergs.webii.entidades.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
	private Connection con = Conexao.getConnection();
	
	public void insert (Category category) {
		
		System.out.println("DAO Category!!!");
		System.out.println(category);
		
		String sql = "INSERT INTO category (description) values (?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, category.getDescription());
			preparador.execute();
			preparador.close();
			
		} catch (SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public void update (Category category) {
		String sql = "UPDATE category SET description = ? WHERE id = ?";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, category.getDescription());
			preparador.setInt(2, category.getId());
			preparador.execute();
			preparador.close();
			
		} catch (SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public void delete (Category category) {	
		System.out.println("DELETAR ID");
		System.out.println(category.getId());
		String sql = "DELETE FROM category WHERE id = ?";
		System.out.println(sql);
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, category.getId());
			preparador.execute();
			preparador.close();
			
		} catch (SQLException ex){
			ex.printStackTrace();
		}
	}
	
	
	public List<Category> fetchAll () {
		String sql = "SELECT * FROM category";
		List<Category> categories = new ArrayList<Category>();
		
		try {
			
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet result = preparador.executeQuery();
			
			while(result.next()){
				Category category = new Category();
				category.setDescription(result.getString("description"));
				category.setId(result.getInt("id"));
				categories.add(category);
			}
			
			
			preparador.close();
			
		} catch (SQLException ex){
			ex.printStackTrace();
		}
		
		return categories;
	}
}
