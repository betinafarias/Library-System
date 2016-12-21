package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fadergs.webii.entidades.Category;
import br.com.fadergs.webii.entidades.Library;
import br.com.fadergs.webii.jdbc.CategoryDAO;
import br.com.fadergs.webii.jdbc.LibraryDAO;

/**
 * Servlet implementation class LibraryController
 */
@WebServlet("/LibraryController")
public class LibraryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibraryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET Library");
		
		LibraryDAO dao = new LibraryDAO();
		List<Library> lista = dao.fetchAll();
		
		request.setAttribute("list", lista);
		
		RequestDispatcher saida = request.getRequestDispatcher("library.jsp");
		saida.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		LibraryDAO dao = new LibraryDAO();
	
		if(action.equals("delete")) {
			String id = request.getParameter("id_obj");
			Library library = new Library();
			library.setId(Integer.parseInt(id));
			dao.delete(library);
		}
		else {
			System.out.println("Editar LIBRARY");
			String idEdit = request.getParameter("id_edit");
			System.out.println(idEdit);
			String name = request.getParameter("txtName");
			String address = request.getParameter("txtAddress");
			Library library = new Library();
			library.setName(name);		
			library.setAddress(address);
			if(idEdit != null && !idEdit.isEmpty()){
				library.setId(Integer.parseInt(idEdit));
				dao.update(library);
			}
			else{ //cadastrar
				System.out.println("CADASTRO LIBRARY");
				dao.insert(library);	
			}			
		}


		doGet(request, response);
	}

}
