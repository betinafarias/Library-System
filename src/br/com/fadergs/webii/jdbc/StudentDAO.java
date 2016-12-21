package br.com.fadergs.webii.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fadergs.webii.entidades.Library;
import br.com.fadergs.webii.entidades.Student;

public class StudentDAO {
	private Connection con = Conexao.getConnection();
	
	public void insert (Student student) {
		
		System.out.println("DAO Student!!!");
		System.out.println(student);
		
		String sql = "INSERT INTO student (name, address, is_active) values (?, ?, ?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, student.getName());
			preparador.setString(2, student.getAddress());
			preparador.setBoolean(3, true);
			preparador.execute();
			preparador.close();
			
		} catch (SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public void update (Student student) {
		String sql = "UPDATE student SET name = ?, address = ? WHERE id = ?";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, student.getName());
			preparador.setString(2, student.getAddress());
			preparador.setInt(3, student.getId());
			preparador.execute();
			preparador.close();
			
		} catch (SQLException ex){
			ex.printStackTrace();
		}
	}
	
	
	public List<Student> fetchAll () {
		String sql = "SELECT * FROM student";
		List<Student> students = new ArrayList<Student>();
		
		try {
			
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet result = preparador.executeQuery();
			
			while(result.next()){
				Student student = new Student();
				student.setName(result.getString("name"));
				student.setAddress(result.getString("address"));
				student.setId(result.getInt("id"));
				
				Integer numberOfArrears = 0;
				
			    String sqlArrears = " SELECT count(*) as num FROM loan WHERE date_expected < now() AND date_devolution IS NULL AND id_student = ?";
				
				try {

					PreparedStatement preparador2 = con.prepareStatement(sqlArrears);
					preparador2.setInt(1, student.getId());
					ResultSet resultArrears = preparador2.executeQuery();
					resultArrears.next();
					numberOfArrears =  resultArrears.getInt("num");
					
					if(numberOfArrears > 0){ //tem atrasos
						student.setIsActive(false);
					}
					else{
						student.setIsActive(true);
					}
					
					preparador2.close();
					
				} catch (SQLException ex){
					ex.printStackTrace();
				}
				
				students.add(student);
			}
			
			
			preparador.close();
			
		} catch (SQLException ex){
			ex.printStackTrace();
		}
		
		return students;
	}
	
}
