<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@ page import="mx.edu.uacm.web.programming.bean.AlumnoBean" %>
<%@ page import="mx.edu.uacm.web.programming.bean.MateriaBean" %>

<%@ page import ="java.util.List" %>
<%@ page import ="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<% 	if ( new Boolean ( request.getParameter("default") ) ){ %>
			
		<form action='materiasAlumno' method='POST'>
		
		<table>						
			<tr>						
				<td> Matricula : </td>	
				<td> <input type='text' name='matricula'> <td>  
			</tr>						
		</table>						
		
		<input  type='hidden' name='consultaAlumno_jsp'  value='true'> 
		
		<input type='submit' value='Consulta'> 
		
		</form>
			
	<%	} %>


	<% if ( request.getAttribute("alumnoBean") != null ) { 
		
		AlumnoBean alumnoBean =  (AlumnoBean)request.getAttribute("alumnoBean");
		List<MateriaBean> listMaterias =  (List<MateriaBean>)request.getAttribute("listMaterias");
	
	%>
		
		<H1> Informacion Alumno </H1>
		
		<form action='materiasAlumno' method='POST'>
		
		<table>						
			<tr>						
				<td> Matricula : </td>	
				<td> <%=  alumnoBean.getMatricula() %> <td>  
			</tr>						
			
			<tr>						
				<td> Nombre : </td>		
				<td> <%=  alumnoBean.getNombre() %> <td> 
			</tr>						
			
			<tr>						
				<td> Apellido 1 : </td> 
				<td> <%=  alumnoBean.getAp1()  %><td>	 
			</tr>						
		
			<tr>						
				<td> Apellido 2 : </td>	
			<td>   <%=  alumnoBean.getAp2()  %><td>	 
			</tr>						
				
			<tr>						
				<td> Semestre : </td>	
				<td> <%=  alumnoBean.getSemestre() %> <td>	 
			</tr>						
			
			<tr>						
				<td> carrera : </td>	
				<td>  <%=  alumnoBean.getCarrera() %> <td>	
			</tr>						
				
			<tr>						
			</tr>						
		</table>						
		
		<br>						
		<br>						
		
		<table >						
			<tr>						
				<td Colspan=2> Materias </td>
			</tr>
			
			<% for ( MateriaBean materia : listMaterias) { %>
			<tr>
				<td> <%= materia.getNombre()%></td>
				<td> <input type='checkbox' name='materiaList'  value= '<%= materia.getCve_materia()%>' > </td>
			<tr>
			<% } %> 

		</table>						
		<br>						
		
		<input  type='hidden' name='matricula'  value='<%=alumnoBean.getMatricula() %>'> 
		<input  type='hidden' name='regMaterias'  value='true'> 
		
		<input type='submit' value='Registrar'> 
		
		</form>

	<% } %>
	















</body>
</html>