package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fadergs.webii.entidades.Student;
import br.com.fadergs.webii.jdbc.StudentDAO;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET Student");
		
		StudentDAO dao = new StudentDAO();
		List<Student> lista = dao.fetchAll();
		
		request.setAttribute("list", lista);
		
		RequestDispatcher saida = request.getRequestDispatcher("students.jsp");
		saida.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		StudentDAO dao = new StudentDAO();
	

		if(action.equals("search")) {

		}
		else {
			System.out.println("Editar STUDENT");
			String idEdit = request.getParameter("id_edit");
			System.out.println(idEdit);
			String name = request.getParameter("txtName");
			String address = request.getParameter("txtAddress");
			Student student = new Student();
			student.setName(name);		
			student.setAddress(address);
			if(idEdit != null && !idEdit.isEmpty()){
				student.setId(Integer.parseInt(idEdit));
				dao.update(student);
			}
			else{ //cadastrar
				System.out.println("CADASTRO LIBRARY");
				dao.insert(student);	
			}			
		}


		doGet(request, response);
	}

}
