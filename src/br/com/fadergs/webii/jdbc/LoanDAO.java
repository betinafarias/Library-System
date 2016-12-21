package br.com.fadergs.webii.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

import com.sun.jmx.snmp.Timestamp;

import br.com.fadergs.webii.entidades.Book;
import br.com.fadergs.webii.entidades.Loan;
import br.com.fadergs.webii.entidades.Student;

public class LoanDAO {
	private Connection con = Conexao.getConnection();
	
	public void insert (Loan loan) {
		
		System.out.println("DAO loan!!!");
		
		String sql = "INSERT INTO loan (id_student, id_book, date_loan, date_expected) values (?, ?, ?, ?)";
		System.out.println(sql);
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, loan.getStudent().getId());
			preparador.setInt(2, loan.getBook().getId());
			
			Date currentDate = new Date();
			
			preparador.setDate(3, new java.sql.Date(currentDate.getTime()));
	    
		    Calendar calendar = Calendar.getInstance();
		    int days = 10;
		    for(int i=0;i<days;)
		    {
		        calendar.add(Calendar.DAY_OF_MONTH, 1);
		        //here even sat and sun are added
		        //but at the end it goes to the correct week day.
		        //because i is only increased if it is week day
		        if(calendar.get(Calendar.DAY_OF_WEEK)<=5)
		        {
		            i++;
		        }

		    }
		    Date date_expected = new Date();
		    date_expected = calendar.getTime(); 			
			
			
			preparador.setDate(4, new java.sql.Date(date_expected.getTime()));
			preparador.execute();
			preparador.close();
			
		} catch (SQLException ex){
			ex.printStackTrace();
		}
		
		//deixar livro indisponivel
		String sqlBook = "UPDATE book SET is_available = ? WHERE id = ?";
		try {
			PreparedStatement preparador = con.prepareStatement(sqlBook);
			preparador.setBoolean(1, false);
			preparador.setInt(2, loan.getBook().getId());
			preparador.execute();
			preparador.close();
			
		} catch (SQLException ex){
			ex.printStackTrace();
		}

	}
	
	public void devolution (Loan loan) {
		String sql = "UPDATE loan SET date_devolution = ?  WHERE id = ?";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			Date currentDate = new Date();
			preparador.setDate(1, new java.sql.Date(currentDate.getTime()));
			preparador.setInt(2, loan.getId());
			preparador.execute();
			preparador.close();
			
		} catch (SQLException ex){
			ex.printStackTrace();
		}
		
		
		System.out.println("BOOK ID!!!");
		System.out.println(loan.getBook().getId());
		//deixar livro disponivel
		String sqlBook = "UPDATE book SET is_available = ? WHERE id = ?";
		try {
			PreparedStatement preparador = con.prepareStatement(sqlBook);
			preparador.setBoolean(1, true);
			preparador.setInt(2, loan.getBook().getId());
			preparador.execute();
			preparador.close();
			
		} catch (SQLException ex){
			ex.printStackTrace();
		}		
		
	}
		
	public List<Loan> fetchAll () {
		String sql = "SELECT loan.*, student.name, book.title FROM loan INNER JOIN student ON student.id = loan.id_student INNER JOIN book ON book.id = loan.id_book ORDER BY date_loan";	
		
		List<Loan> loans = new ArrayList<Loan>();

		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet result = preparador.executeQuery();
			
			while(result.next()){
				Loan loan = new Loan();
				loan.setId(result.getInt("id"));
				

			    java.sql.Date dbSqlDate = result.getDate("date_loan");
			    java.util.Date date_loan = new java.util.Date(dbSqlDate.getTime());				
				loan.setDateLoan(date_loan);		
				
			    java.sql.Date exptDate = result.getDate("date_expected");
			    java.util.Date date_expected = new java.util.Date(exptDate.getTime());				
				loan.setDateExpected(date_expected);
				
			    java.sql.Date devDate = result.getDate("date_devolution");
			    if(devDate != null) {
				    java.util.Date date_devolution = new java.util.Date(devDate.getTime());				
					loan.setDateDevolution(date_devolution);			    	
			    }



				loan.setStudent(new Student());
				loan.getStudent().setId(result.getInt("id_student"));
				loan.getStudent().setName(result.getString("name"));
				
				loan.setBook(new Book());
				loan.getBook().setId(result.getInt("id_book"));
				loan.getBook().setTitle(result.getString("title"));
				
				loans.add(loan);
			}
			
			preparador.close();
			
		} catch (SQLException ex){
			ex.printStackTrace();
		}
		
		return loans;
	}

}
