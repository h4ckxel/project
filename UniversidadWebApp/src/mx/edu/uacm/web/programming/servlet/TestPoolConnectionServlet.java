package mx.edu.uacm.web.programming.servlet;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.edu.uacm.web.programming.db.DbUtil;

public class TestPoolConnectionServlet extends HttpServlet  {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("<HTML>");
		out.println("	<body>");

		if ( testPoolConnection() ) {
			out.println("		<h1> Database connection OK! </h1>");
		}else {
			out.println("		<h1> Database connection NO OK! </h1>");	
		}
		out.println("	</body>");
		out.println("</HTML>");	
	}
	
	private boolean testJDBCConnection() {
		return DbUtil.testJDBCConnection();
	}
	
	private boolean testPoolConnection() {
		return DbUtil.testPoolConnection();
	}
	
}
