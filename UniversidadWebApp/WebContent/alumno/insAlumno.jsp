<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@ page import="mx.edu.uacm.web.programming.db.AlumnoDB" %>
<%@ page import="mx.edu.uacm.web.programming.bean.CarreraBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>


<%! AlumnoDB alumnoDB = new AlumnoDB(); %>
<%! List<CarreraBean> listCarreras = new ArrayList<CarreraBean>(); %>

<% listCarreras = alumnoDB.loadCarreras(); %>


<!DOCTYPE html>
<html>
<body>
	<H1>  Inscripción Alumno </H1>

	<form action='inscAlumnoServlet' method='POST'>

			<table>
				<tr>
					<td>Matricula :</td>
					<td><input type='text' name='matricula'>
					<td>
				</tr>

				<tr>
					<td>Nombre :</td>
					<td><input type='text' name='nombre'>
					<td>
				</tr>

				<tr>
					<td>Apellido 1 :</td>
					<td><input type='text' name='ap1'>
					<td>
				</tr>

				<tr>
					<td>Apellido 2 :</td>
					<td><input type='text' name='ap2'>
					<td>
				</tr>

				<tr>
					<td>Semestre :</td>
					<td><input type='text' name='semestre'>
					<td>
				</tr>

				<tr>
					<td>carrera :</td>
					<td><select name='carrera'> 
						<% for ( CarreraBean bean : listCarreras) {%>
							<option value='<%= bean.getCarrera() %>'> <%= bean.getNombre() %> </option> 
						<%}%>
						</select>
					<td>
				</tr>
			</table>


			<input type='hidden' name='regAlumno' value='true'> 
			
			<input type='submit' value='Registrar'>

		</form>
</body>
</html>