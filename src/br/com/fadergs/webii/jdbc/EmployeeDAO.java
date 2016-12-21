package br.com.fadergs.webii.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fadergs.webii.entidades.Employee;
import br.com.fadergs.webii.entidades.Library;

public class EmployeeDAO {
	private Connection con = Conexao.getConnection();
	public void insert (Employee employee) {

		String sql = "INSERT INTO employee (name, address, phone, salary, id_library) values (?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, employee.getName());
			preparador.setString(2, employee.getAddress());
			preparador.setInt(3, employee.getPhone());
			preparador.setDouble(4, employee.getSalary());
			preparador.setInt(5, employee.getLibrary().getId());
			preparador.execute();
			preparador.close();
			
		} catch (SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public void update (Employee employee) {
		String sql = "UPDATE employee SET name = ?, address = ?, phone = ?, salary = ?, id_library = ? WHERE id = ?";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, employee.getName());
			preparador.setString(2, employee.getAddress());
			preparador.setInt(3, employee.getPhone());
			preparador.setDouble(4, employee.getSalary());
			preparador.setInt(5, employee.getLibrary().getId());
			preparador.setInt(6, employee.getId());
			preparador.execute();
			preparador.close();
			
		} catch (SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public void delete (Employee category) {	
		System.out.println("DELETAR ID");
		System.out.println(category.getId());
		String sql = "DELETE FROM employee WHERE id = ?";
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
	
	
	public List<Employee> fetchAll () {
		String sql = "SELECT employee.*, library.name as library_name FROM employee INNER JOIN library on library.id = employee.id_library";
		List<Employee> employees = new ArrayList<Employee>();
		
		try {
			
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet result = preparador.executeQuery();
			
			while(result.next()){
				Employee employee = new Employee();
				employee.setId(result.getInt("id"));
				employee.setName(result.getString("name"));
				employee.setAddress(result.getString("address"));
				employee.setPhone(Integer.parseInt(result.getString("phone")));
				employee.setSalary(Double.parseDouble(result.getString("salary")));
				employee.setLibrary(new Library());				
				employee.getLibrary().setId(Integer.parseInt(result.getString("id_library")));
				employee.getLibrary().setName(result.getString("library_name"));
				employees.add(employee);
			}
			
			
			preparador.close();
			
		} catch (SQLException ex){
			ex.printStackTrace();
		}
		
		return employees;
	}
}
