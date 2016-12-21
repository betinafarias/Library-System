<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="br.com.fadergs.webii.entidades.Book" %>
<%@ page import="br.com.fadergs.webii.entidades.Loan" %>
<%@ page import="br.com.fadergs.webii.entidades.Category" %>
<%@ page import="br.com.fadergs.webii.entidades.Library" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loans</title>

<!--Import Google Icon Font-->
<link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="materialize.css"  media="screen,projection"/>


<meta name="viewport" content="width=device-width, initial-scale=1.0"/>

<link href="https://fonts.googleapis.com/css?family=Roboto:400,700" rel="stylesheet">


</head>
<body>

	<!-- Dropdown Structure -->
	<ul id="dropdown1" class="dropdown-content">
	  <li><a href="#!">one</a></li>
	  <li><a href="#!">two</a></li>
	  <li class="divider"></li>
	  <li><a href="#!">three</a></li>
	</ul>
	<nav>
	  <div class="nav-wrapper light-blue">
	    <a href="#!" class="brand-logo">Library System</a>
	    <ul class="right hide-on-med-and-down light-blue">
	      <li><a href="/LoanController">Loans</a></li>
	      <li class="active"><a href="/BookController">Books</a></li>
	      <li ><a href="/CategoryController">Categories</a></li>
	      <li><a href="students.jsp">Students</a></li>
	      <li><a href="employees.jsp">Employees</a></li>
	      <li><a href="/LibraryController">Library</a></li>
	    </ul>
	  </div>
	</nav>
	
	<br><BR>
	
	<div class="container">
	  <div class="row center">
		<h4>Loans</h4>
		
<br>
  <ul class="collapsible" data-collapsible="accordion">
    <li>
      <div class="collapsible-header purple white-text active"><i class="material-icons">add</i>Create/edit book</div>
      <div class="collapsible-body"><p>
      	<div class="row">
	      	<form class="col s12" name="add" action="LoanController" method="post">
	      		<input type="hidden" name="action" value="cadastrar">
		      	<div class="input-field col s12 left text-left">		        
		      	
		      	  <label>Browser Select</label>
				  <select class="browser-default">
				    <option value="" disabled selected>Choose your option</option>
				    <option value="1">Option 1</option>
				    <option value="2">Option 2</option>
				    <option value="3">Option 3</option>
				  </select>
		      	
		      	       				               		
		          <input placeholder="Id Student" id="txtIdStudent" name="txtIdStudent" type="text" class="validate" required>
		          <input placeholder="Id Book" id="txtIdBook" name="txtIdBook" type="text" class="validate" required>
			     </div>
	
			     <a class="waves-effect waves-light btn light-blue" onclick="add.submit();"><i class="material-icons left">check</i>Salvar</a>
	      	</form>
      	</div>
      </p></div>
    </li>

  </ul>
		
		<br>
		<form name="admin" method="post" action="LoanController">
	  <input type="hidden" name="action" value="devolution">
      <input type="hidden" name="id_obj" value="">
      <input type="hidden" name="id_book" value="">
      
		<table class="highlight centered">
        <thead>
          <tr>
           	  <th data-field="id">Id</th>
              <th data-field="id">Book</th>
              <th data-field="price">Student</th>
              <th data-field="price">Date loan</th>
              <th data-field="price">Date expected</th>
              <th data-field="price">Date devolution</th>
              <th data-field="price">Devolution</th>
          </tr>
        </thead>
         
        <tbody>

        <%
        List<Loan> list = (List<Loan>) request.getAttribute("list");
		for(Loan obj: list){        		  
        %>
          <tr>
            <td><input name="" type="text" value="<%=obj.getId() %>" style="width:50px;" disabled></td>
            <td><%=obj.getBook().getTitle() %></td>
            <td><%=obj.getStudent().getName() %></td>
			<td><%=obj.convertDateToString(obj.getDateLoan()) %></td>
			<td><%=obj.convertDateToString(obj.getDateExpected()) %></td>
			<td><%=obj.convertDateToString(obj.getDateDevolution()) %></td>
            <td><i class=" material-icons cursor" onclick="admin.id_obj.value=<%=obj.getId()%>; admin.id_book.value=<%=obj.getBook().getId()%>; admin.submit();">input</i> </td>
          </tr>
         <%
		}
         %>
        </tbody>
      </table>

      </form>
		
	</div>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="materialize.min.js"></script>
	
	<script>

	  $(document).ready(function(){
	    $('.collapsible').collapsible();
	    Materialize.updateTextFields();
	  });
	  
	</script>
		

</body>
</html>