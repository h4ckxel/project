package mx.edu.uacm.web.programming.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.edu.uacm.web.programming.bean.AlumnoBean;
import mx.edu.uacm.web.programming.bean.CarreraBean;
import mx.edu.uacm.web.programming.bean.MateriaBean;
import mx.edu.uacm.web.programming.db.AlumnoDB;

@WebServlet(
			name ="MateriasAlumno",
			description ="Servlet que atiende peticiones de la logica del alumno",
			urlPatterns = {"/alumno/materiasAlumno" , "/cool" })
public class MateriasAlumnoServlet extends HttpServlet{

	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		PrintWriter out = res.getWriter();
		
		if ( new Boolean (req.getParameter("default")) ) {
			defaulMateriasalumno( out );
		}
		
		
		if ( new Boolean (req.getParameter("consultaAlumno_jsp")) ) {
			
			String matricula = req.getParameter("matricula");
			
			AlumnoDB alumnoDb = new AlumnoDB();
			
			AlumnoBean alumnoBean = null;
			List<MateriaBean> listMAterias = null;
			
			try {
				alumnoBean = alumnoDb.consultaAlumno(matricula);
				listMAterias = alumnoDb.loadMateriasBySemestre( alumnoBean.getSemestre() );
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			req.setAttribute("alumnoBean", alumnoBean );
			req.setAttribute("listMaterias", listMAterias );
			
			req.getRequestDispatcher("materiasAlumno.jsp").forward(req, res);
		}
		
		if ( new Boolean (req.getParameter("consultaAlumno"))) {
		
			String matricula = req.getParameter("matricula");
			
			AlumnoDB alumnoDb = new AlumnoDB();
			
			try {
				AlumnoBean alumnoBean = alumnoDb.consultaAlumno(matricula);
				
				List<MateriaBean> listMAterias = alumnoDb.loadMateriasBySemestre( alumnoBean.getSemestre() );
				
				
				if (alumnoBean != null)
					alumnoFound(out, alumnoBean, listMAterias );
				
			}catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
		if ( new Boolean(req.getParameter("regMaterias")) ) {
			
			String matricula = req.getParameter("matricula");
			String[] cves_materias = req.getParameterValues("materiaList");
			
			for (String str : cves_materias)
				System.out.println(str);
			
			AlumnoDB alumnoDb = new AlumnoDB();
			
			boolean ret = false;
			try {
				ret = alumnoDb.storeMateriasAlumno(matricula, cves_materias);
			} catch (SQLException e) {
				e.printStackTrace();
				ServletUtil.sendErrorPage(out);
			}
			
			if (ret) {
				materiasRegistradasOK( out, matricula );
			}else {
				ServletUtil.sendErrorPage(out);
			}
		}
	}

	private void materiasRegistradasOK( PrintWriter out, String matricula ) {
		
		out.println("<html>");
		out.println("<body>");

		out.println("<H1> Las materias para el alumno con matricula: " + matricula + " fueron registradas correctamente <H1>");

		out.println("<a href='../index.html'> Regresar a pagina principal</a>");
		
		out.println("</body>");
		out.println("</html>");
	}
	
	private void alumnoFound(PrintWriter out, AlumnoBean alumnobean, List<MateriaBean> listMaterias) {
		
		out.println("<html>");
		out.println("<body>");
		
		out.println("<H1> Informacion Alumno <H1>");
		
		out.println("<form action='materiasAlumno' method='POST'>");
		
		out.println("<table>						");
		out.println("	<tr>						");
		out.println("		<td> Matricula : </td>	");
		out.println("		<td> " + alumnobean.getMatricula() +"<td> "); 
		out.println("	</tr>						");
			
		out.println("	<tr>						");
		out.println("		<td> Nombre : </td>		");
		out.println("		<td> " + alumnobean.getNombre() + "<td>"); 
		out.println("	</tr>						");
			
		out.println("	<tr>						");
		out.println("		<td> Apellido 1 : </td> ");
		out.println("		<td> " + alumnobean.getAp1()  + "<td>	"); 
		out.println("	</tr>						");
		
		out.println("	<tr>						");
		out.println("		<td> Apellido 2 : </td>	");
		out.println("	<td> " +  alumnobean.getAp2()  +" <td>	"); 
		out.println("	</tr>						");
				
		out.println("	<tr>						");
		out.println("		<td> Semestre : </td>	");
		out.println("		<td> " + alumnobean.getSemestre() + " <td>	"); 
		out.println("	</tr>						");
			
		out.println("	<tr>						");
		out.println("		<td> carrera : </td>	");
		out.println("		<td> " + alumnobean.getCarrera() + "<td>	");
		out.println("	</tr>						");
				
		out.println("	<tr>						");
		out.println("	</tr>						");
		out.println("</table>						");
		
		out.println("<br>						");
		out.println("<br>						");
		
		out.println("<table >						");
		out.println("	<tr>						");
		out.println("		<td Colspan=2> Materias </td>	");
		out.println("	</tr>						");
		
		for(MateriaBean materia : listMaterias) {
			out.println("	<tr>						");
			out.println("		<td> "+ materia.getNombre() +" </td>	");
			out.println("		<td> <input type='checkbox' name='materiaList' value='"+ materia.getCve_materia() +"' ><td>	");
			out.println("	</tr>						");
		}
		
		out.println("</table>						");
		out.println("<br>						");
		
		out.println("<input  type='hidden' name='matricula'  value='"+ alumnobean.getMatricula() +"'> ");
		out.println("<input  type='hidden' name='regMaterias'  value='true'> ");
		
		out.println("<input type='submit' value='Registrar'> ");
		
		out.println("</form>");
		
		out.println("</body>");
		out.println("</html>"); 
	
	}
	
	
	private void defaulMateriasalumno(PrintWriter out) {
	
		out.println("<html>");
		out.println("<body>");
		
		out.println("<H1> </H1>");
		
		out.println("<form action='materiasAlumno' method='POST'>");
		
		out.println("<table>						");
		out.println("	<tr>						");
		out.println("		<td> Matricula : </td>	");
		out.println("		<td> <input type='text' name='matricula'> <td> "); 
		out.println("	</tr>						");
		out.println("</table>						");
		
		out.println("<input  type='hidden' name='consultaAlumno'  value='true'> ");
		
		out.println("<input type='submit' value='Consulta'> ");
		
		out.println("</form>");
		
		out.println("</body>");
		out.println("</html>");
		
	}
}
