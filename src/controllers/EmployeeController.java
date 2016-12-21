package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fadergs.webii.entidades.Employee;
import br.com.fadergs.webii.entidades.Library;
import br.com.fadergs.webii.jdbc.CategoryDAO;
import br.com.fadergs.webii.jdbc.EmployeeDAO;

/**
 * Servlet implementation class EmployeeController
 */
@WebServlet("/EmployeeController")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("GET Category");
		
		EmployeeDAO dao = new EmployeeDAO();
		java.util.List<Employee> lista = dao.fetchAll();
		
		request.setAttribute("list", lista);
		
		RequestDispatcher saida = request.getRequestDispatcher("Employee.jsp");
		saida.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		EmployeeDAO dao = new EmployeeDAO();

		if(action.equals("delete")) {
			String id = request.getParameter("id_obj");
			Employee employee = new Employee();
			employee.setId(Integer.parseInt(id));
			dao.delete(employee);
		}
		else {
			System.out.println("EDITAR ID");
			
			String idEdit = request.getParameter("id_edit");
			System.out.println(idEdit);
			
			Employee employee = new Employee();
			employee.setName(request.getParameter("txtName"));
			employee.setAddress(request.getParameter("txtAddress"));
			employee.setPhone(Integer.parseInt(request.getParameter("txtPhone")));
			employee.setSalary(Double.parseDouble(request.getParameter("txtSalary")));
			employee.setLibrary(new Library());
			employee.getLibrary().setId(Integer.parseInt(request.getParameter("txtIdLibrary")));
		
			if(idEdit != null && !idEdit.isEmpty()){
				employee.setId(Integer.parseInt(idEdit));
				dao.update(employee);
			}
			else{ //cadastrar								
				dao.insert(employee);	
			}			
		}


		doGet(request, response);
	}
}
