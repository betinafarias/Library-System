package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.fadergs.webii.jdbc.BookDAO;
import br.com.fadergs.webii.entidades.Book;
import br.com.fadergs.webii.entidades.Category;
import br.com.fadergs.webii.entidades.Library;



/**
 * Servlet implementation class BookController
 */
@WebServlet("/BookController")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("GET Book");
		
		BookDAO bookDAO = new BookDAO();
		List<Book> lista = bookDAO.fetchAll();
		
		request.setAttribute("list", lista);
		
		RequestDispatcher saida = request.getRequestDispatcher("books.jsp");
		saida.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		BookDAO dao = new BookDAO();
		
		if(action.equals("search")) {
			//String searchTitle = request.getParameter("txtSearchTitle");
			//List <Book> lista = dao.searchTitle(searchTitle);
			
		}
		else {
			System.out.println("EDITAR ID");

			String idEdit = request.getParameter("id_edit");
			System.out.println(idEdit);
			
			Book book = new Book();
			book.setCategory(new Category());
			book.setLibrary(new Library());

			book.getCategory().setId(Integer.parseInt(request.getParameter("txtIdCategory")));
			book.getLibrary().setId(Integer.parseInt(request.getParameter("txtIdLibrary")));
			book.setPrice(Double.parseDouble(request.getParameter("txtPrice")));
			book.setPublisher(request.getParameter("txtPublisher"));
			book.setTitle(request.getParameter("txtTitle"));
			
			//editar
			if(idEdit != null && !idEdit.isEmpty()){
				book.setId(Integer.parseInt(idEdit));
				dao.update(book);
			}
			else{ //cadastrar	
				book.setIsAvailable(true);
				dao.insert(book);	
			}				
		}
		
		doGet(request, response);
	}

}
