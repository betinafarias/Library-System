package br.com.fadergs.webii.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fadergs.webii.entidades.Book;
import br.com.fadergs.webii.entidades.Category;
import br.com.fadergs.webii.entidades.Library;
import br.com.fadergs.webii.entidades.Loan;

public class BookDAO {
	private Connection con = Conexao.getConnection();
	
	public void insert (Book book) {
		
		System.out.println("DAO book!!!");
		
		
		String sql = "INSERT INTO book (title, publisher, price, id_category, id_library, is_available) values (?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, book.getTitle());
			preparador.setString(2, book.getPublisher());
			preparador.setDouble(3, book.getPrice());
			preparador.setInt(4, book.getCategory().getId());
			preparador.setInt(5, book.getLibrary().getId());
			preparador.setBoolean(6, true);
			preparador.execute();
			preparador.close();
			
		} catch (SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public void update (Book book) {
		String sql = "UPDATE book SET title = ?, publisher = ?, price = ?, id_category = ?, id_library = ?  WHERE id = ?";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, book.getTitle());
			preparador.setString(2, book.getPublisher());
			preparador.setDouble(3, book.getPrice());
			preparador.setInt(4, book.getCategory().getId());
			preparador.setInt(5, book.getLibrary().getId());
			preparador.setInt(6, book.getId());
			preparador.execute();
			preparador.close();
			
		} catch (SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public void delete (Book book) {	
		System.out.println("DELETAR ID");
		System.out.println(book.getId());
		String sql = "DELETE FROM book WHERE id = ?";
		System.out.println(sql);
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, book.getId());
			preparador.execute();
			preparador.close();
			
		} catch (SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public List<Book> fetchAll () {
		String sql = "SELECT "
				+ "book.*, category.id as id_category, category.description as category_description,"
				+ "library.id as id_library, library.name as library_name,"
				+ "loan.date_expected"
				+ " FROM book"
				+ " LEFT JOIN category ON category.id = book.id_category"
				+ " LEFT JOIN library ON library.id = book.id_library"
				+ " LEFT JOIN loan ON loan.id_book = book.id";	
		System.out.println(sql);
		List<Book> books = new ArrayList<Book>();

		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet result = preparador.executeQuery();
			
			while(result.next()){
				Book book = new Book();
				book.setTitle(result.getString("title"));
				book.setPublisher(result.getString("publisher"));
				book.setPrice(result.getDouble("price"));
				book.setIsAvailable(result.getBoolean("is_available"));
				book.setId(result.getInt("id"));
				book.setCategory(new Category());
				book.getCategory().setDescription(result.getString("category_description"));
				book.getCategory().setId(result.getInt("id_category"));
				book.setLibrary(new Library());
				book.getLibrary().setName(result.getString("library_name"));
				book.setLoan(new Loan());
				book.getLoan().setDateExpected(result.getDate("date_expected"));
				books.add(book);
			}
			
			preparador.close();
			
		} catch (SQLException ex){
			ex.printStackTrace();
		}
		
		return books;
	}
	
	public List<Book> searchTitle (String title) {
		String sql = "SELECT "
				+ "book.*, category.id as id_category, category.description as category_description,"
				+ "library.id as id_library, library.name as library_name,"
				+ "loan.date_expected"
				+ "FROM book"
				+ "INNER JOIN category ON category.id = book.id_category"
				+ "INNER JOIN library ON library.id = book.id_library"
				+ "INNER JOIN loan ON loan.id_book = book.id"
				+ "WHERE title LIKE '%?%'";		

		List<Book> books = new ArrayList<Book>();
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, title);
			ResultSet result = preparador.executeQuery();
			while(result.next()){
				Book book = new Book();
				book.setTitle(result.getString("title"));
				book.setPublisher(result.getString("publisher"));
				book.setPrice(result.getDouble("price"));
				book.setIsAvailable(result.getBoolean("is_available"));
				book.setId(result.getInt("id"));
				book.setCategory(new Category());
				book.getCategory().setDescription(result.getString("category_description"));
				book.getCategory().setId(result.getInt("id_category"));
				Library lib = new Library();
				book.setLibrary(new Library());
				book.getLibrary().setName(result.getString("library_name"));
				book.setLoan(new Loan());
				book.getLoan().setDateExpected(result.getDate("date_expected"));
				books.add(book);
			}
			preparador.close();
		} catch (SQLException ex){
			ex.printStackTrace();
		}
		
		return books;
	}
}
