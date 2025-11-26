package mx.edu.uacm.web.programming.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.edu.uacm.web.programming.bussiness.BusinessService;
import mx.edu.uacm.web.programming.db.AlumnoDB;
import mx.edu.uacm.web.programming.bean.AlumnoBean;
import mx.edu.uacm.web.programming.bean.CarreraBean;
import mx.edu.uacm.web.programming.bussiness.BusinessDelegate;

/*@WebServlet(
		name ="InsAlumno_2",
		description ="Servlet que atiende peticiones de la logica del alumno",
		urlPatterns = {"/cool_2" })*/
public class InscAlumnoServlet  extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		BusinessDelegate bd =  new BusinessDelegate();
		
		List<CarreraBean> listCarreras = new ArrayList<CarreraBean>();
		
		try {
			if ( new Boolean (req.getParameter("lc")) ) {		
				AlumnoDB alumnoDB = new AlumnoDB();
				listCarreras = alumnoDB.loadCarreras();
				responseLoadCarreras(out, listCarreras);
			}	
			if ( new Boolean(req.getParameter("regAlumno")) ) {
				String matricula = req.getParameter("matricula");
				String nombre  = req.getParameter("nombre");
				String ap1  = req.getParameter("ap1");
				String ap2  = req.getParameter("ap2");
				String semestre  = req.getParameter("semestre");
				String carrera  = req.getParameter("carrera"); 
		
				AlumnoBean alumnoBean = new AlumnoBean ( matricula, nombre, ap1, ap2, semestre, carrera);
				
				AlumnoDB alumnoDB = new AlumnoDB();
				
				if ( alumnoDB.storeAlumno(alumnoBean) ) {
					responseStoreOK(out);
				} else {
					responseStoreNotOK(out);
				}
			}
		} catch(SQLException  sqle) {
			sqle.printStackTrace();
			
			if (new Boolean(req.getParameter("regAlumno")) )
				responseStoreNotOK(out);
			else
				ServletUtil.sendErrorPage( out );	
		}
	}
	
	private void responseStoreOK(PrintWriter out) {
		
		out.println("<html>");
		out.println("<body>");

		out.println("<H1> Alumno Registrado exitosamente <H1>");

		out.println("<a href='../index.html'> Regresar a pagina principal</a>");
		
		out.println("</body>");
		out.println("</html>");
	}
	
	private void responseStoreNotOK(PrintWriter out) {
		
		out.println("<html>");
		out.println("<body>");

		out.println("<H1> Hubo un problema al registrar el alumno! <H1>");

		out.println("<a href='../index.html'> Regresar a pagina principal</a>");
		
		out.println("</body>");
		out.println("</html>");
	}
	
	private void responseLoadCarreras(PrintWriter out, List<CarreraBean> listCarreras) {
		
		out.println("<html>");
		out.println("<body>");
		
		out.println("<H1> Inscripción Alumno");
		
		out.println("<form action='inscAlumnoServlet' method='POST'>");
		
		out.println("<table>						");
		out.println("	<tr>						");
		out.println("		<td> Matricula : </td>	");
		out.println("		<td> <input type='text' name='matricula'> <td> "); 
		out.println("	</tr>						");
			
		out.println("	<tr>						");
		out.println("		<td> Nombre : </td>		");
		out.println("		<td> <input type='text' name='nombre'> <td>"); 
		out.println("	</tr>						");
			
		out.println("	<tr>						");
		out.println("		<td> Apellido 1 : </td> ");
		out.println("		<td> <input type='text' name='ap1'> <td>	"); 
		out.println("	</tr>						");
		
		out.println("	<tr>						");
		out.println("		<td> Apellido 2 : </td>	");
		out.println("	<td> <input type='text' name='ap2'> <td>	"); 
		out.println("	</tr>						");
				
		out.println("	<tr>						");
		out.println("		<td> Semestre : </td>	");
		out.println("		<td> <input type='text' name='semestre'> <td>	"); 
		out.println("	</tr>						");
			
		out.println("	<tr>						");
		out.println("		<td> carrera : </td>	");
		out.println("		<td> 					");
				
		out.println("	<select name='carrera' >	");
		
		for (CarreraBean cb : listCarreras) {
			out.println("		<option value='"+ cb.getCarrera()+"'>" +cb.getNombre() + "</option> ");
		}
				  
		out.println("		<td> 					");
		out.println("	</tr>						");
		out.println("</table>						");
		
		
		out.println("<input  type='hidden' name='regAlumno'  value='true'> ");
		
		out.println("<input type='submit' value='Registrar'> ");
		
		out.println("</form>");
		
		out.println("</body>");
		out.println("</html>");
		
	}
	
}
