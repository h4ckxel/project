<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.Date" %>
<%@ page import="mx.edu.uacm.web.programming.bean.CarreraBean" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Jps in class</title>
</head>
<body>
	<% for (int i=0; i<10; i++){ %>				
		<p> cont = <%=i%> </p> 	
	<% } %>
	
	<%  Date fecha = new Date(); %>
	<%= fecha %>

      <% CarreraBean beanCarrera = new CarreraBean("clave", "Nombre carrera");  %>
      <%= beanCarrera %>
      
</body>
</html>