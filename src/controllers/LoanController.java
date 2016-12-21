package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fadergs.webii.entidades.Book;
import br.com.fadergs.webii.entidades.Loan;
import br.com.fadergs.webii.entidades.Student;
import br.com.fadergs.webii.jdbc.BookDAO;
import br.com.fadergs.webii.jdbc.LoanDAO;
import br.com.fadergs.webii.jdbc.StudentDAO;

/**
 * Servlet implementation class LoanController
 */
@WebServlet("/LoanController")
public class LoanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoanController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LoanDAO dao = new LoanDAO();
		
		
		if(request.getParameter("filter_id_book") != null){ 
			Integer idBook = Integer.parseInt(request.getParameter("filter_id_book"));
			List<Loan> lista = dao.fetchAllByBook(idBook);
			request.setAttribute("list", lista);
		}
		
		else if(request.getParameter("filter_status_student") != null) {
			Integer status = Integer.parseInt(request.getParameter("filter_status_student"));
			List<Loan> lista = dao.fetchAllByStudentStatus(status);
			request.setAttribute("list", lista);
		}
		
		else if(request.getParameter("filter_period") != null) {
			String period = request.getParameter("filter_period");
			List<Loan> lista = dao.fetchAllByLastMonth();
			request.setAttribute("list", lista);			
		}
		else{ //sem filtro
			
			List<Loan> lista = dao.fetchAll();
			request.setAttribute("list", lista);
		}
		

		/*
		if(bookId != null && !bookId.isEmpty()){
			//filtra 
		}
		else {
			//busca todos os emprestimos
		}
		
		else if(bookId != null && !bookId.isEmpty()){
			
		}
		else if(bookId != null && !bookId.isEmpty()){
			
		}*/
		
		
		System.out.println("GET Loan");
		
		BookDAO bookDao = new BookDAO();
		List<Book> books = bookDao.fetchAll();
		request.setAttribute("books", books);
		
		StudentDAO studentDAO = new StudentDAO();
		List<Student> students = studentDAO.fetchAll();
		request.setAttribute("students", students);
		
		RequestDispatcher saida = request.getRequestDispatcher("loan.jsp");
		saida.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		LoanDAO dao = new LoanDAO();
		Loan loan = new Loan();
		
		if(action.equals("devolution")) {
			String id = request.getParameter("id_obj");
			String idBook = request.getParameter("id_book");
			loan.setId(Integer.parseInt(id));
			loan.setBook(new Book());
			loan.getBook().setId(Integer.parseInt(idBook));
			dao.devolution(loan);
		}
		else {System.out.println("cadastryo Loan");
			loan.setStudent(new Student());
			loan.setBook(new Book());
			loan.getStudent().setId(Integer.parseInt(request.getParameter("txtIdStudent")));
			loan.getBook().setId(Integer.parseInt(request.getParameter("txtIdBook")));			
			dao.insert(loan);				
		}
		

		
		doGet(request, response);
	}

}
