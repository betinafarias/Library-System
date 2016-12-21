package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fadergs.webii.entidades.Category;
import br.com.fadergs.webii.jdbc.CategoryDAO;


@WebServlet("/CategoryController")
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public CategoryController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("GET Category");
		
		CategoryDAO categoryDAO = new CategoryDAO();
		java.util.List<Category> lista = categoryDAO.fetchAll();
		
		request.setAttribute("list", lista);
		
		RequestDispatcher saida = request.getRequestDispatcher("categories.jsp");
		saida.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		CategoryDAO dao = new CategoryDAO();

		if(action.equals("delete")) {
			String id = request.getParameter("id_obj");
			Category category = new Category();
			category.setId(Integer.parseInt(id));
			dao.delete(category);
		}
		else {
			System.out.println("EDITAR ID");
			
			String idEdit = request.getParameter("id_edit");
			System.out.println(idEdit);
			String description = request.getParameter("txtDescription");
			Category category = new Category();
			category.setDescription(description);			
			if(idEdit != null && !idEdit.isEmpty()){
				category.setId(Integer.parseInt(idEdit));
				dao.update(category);
			}
			else{ //cadastrar								
				dao.insert(category);	
			}			
		}


		doGet(request, response);
	}
    
    
}
