<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="br.com.fadergs.webii.entidades.Category" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Categories</title>

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
	      <li><a href="index.jsp">Loans</a></li>
	      <li><a href="books.jsp">Books</a></li>
	      <li class="active"><a href="categories.jsp">Categories</a></li>
	      <li><a href="students.jsp">Students</a></li>
	      <li><a href="employees.jsp">Employees</a></li>
	      <li><a href="library.jsp">Library</a></li>
	    </ul>
	  </div>
	</nav>
	
	<br><BR>
	
	<div class="container">
	  <div class="row center">
		<h4>Categories</h4>
		
<br>
  <ul class="collapsible" data-collapsible="accordion">
    <li>
      <div class="collapsible-header purple white-text active"><i class="material-icons">add</i>Criar/editar categoria</div>
      <div class="collapsible-body"><p>
      	<div class="row">
	      	<form class="col s12" name="add" action="CategoryController" method="post">
	      		<input type="hidden" name="action" value="cadastrar">
		      	<div class="input-field col s12 left text-left">
		          <input type="text" placeholder="Id" name="id_edit" class="validate" value="" >
		          	      		
		          <input placeholder="Descri��o da categoria" id="txtDescription" name="txtDescription" type="text" class="validate">

			     </div>
	
			     <a class="waves-effect waves-light btn light-blue" onclick="add.submit();"><i class="material-icons left">check</i>Salvar</a>
	      	</form>
      	</div>
      </p></div>
    </li>

  </ul>
		
		<br>
		<form name="admin" method="post" action="CategoryController">
	  <input type="hidden" name="action" value="">
      <input type="hidden" name="id_obj" value="">
		<table class="highlight centered">
        <thead>
          <tr>
           	  <th data-field="id">Id</th>
              <th data-field="id">Title</th>
              <th data-field="price">Delete</th>
              <th data-field="price">Edit</th>
          </tr>
        </thead>
         
        <tbody>
        <%
        List<Category> list = (List<Category>) request.getAttribute("list");
		for(Category cat: list){        		  
        %>
          <tr>
            <td><input name="" type="text" value="<%=cat.getId() %>" style="width:50px;" disabled></td>
            <td><%=cat.getDescription() %></td>
            <td><i class=" material-icons cursor" onclick="admin.action.value='delete'; admin.id_obj.value='<%=cat.getId()%>'; admin.submit()">delete</i> </td>
            <td><i class=" material-icons cursor" onclick=" add.id_edit.value=<%=cat.getId()%>;">mode_edit</i> </td>
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