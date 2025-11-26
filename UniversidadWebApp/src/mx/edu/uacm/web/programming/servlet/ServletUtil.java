package mx.edu.uacm.web.programming.servlet;

import java.io.PrintWriter;

public class ServletUtil {

		public static void sendErrorPage(PrintWriter out ) {
			
			out.println("<html>");
			out.println("<body>");
			
			out.println("<H1> Hubo un error! Disculpa!");
			
			out.println("</body>");
			out.println("</html>");
		}
}
