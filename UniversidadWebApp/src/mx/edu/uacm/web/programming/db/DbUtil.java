package mx.edu.uacm.web.programming.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbUtil {

	public static DataSource ds;

	static {
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc/webprogramming");
		}catch(NamingException ne) {
			ne.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	
	
	public static boolean testPoolConnection() {
		boolean toReturn = false;
		
		try {
				Connection con = ds.getConnection();
				toReturn = executeTestStatement( con );
				
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return toReturn ;
	}

	
	public static boolean testJDBCConnection() {
		
		boolean toReturn = false; 
		
		String DRIVER = "com.mysql.cj.jdbc.Driver";
	    String URL_CONEXION = "jdbc:mysql://127.0.0.10:3306/webprogramming?serverTimezone=UTC";
	    
	    String usuario = "mysql";
        String password = "mysql";
        
        try {
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
            
            toReturn = executeTestStatement( conn );

        }catch(ClassNotFoundException cnfe) {
        	cnfe.printStackTrace();
        }catch (SQLException sqle) {
        	sqle.printStackTrace();
		}

        return toReturn;
	}
	
	
	private static boolean executeTestStatement(Connection conn) {
		  String selectTableSQL = "SELECT * FROM materias";
		  boolean toRet = false; 
		  try {
			  Statement statement = conn.createStatement();
	          
			  ResultSet rs = statement.executeQuery(selectTableSQL);
	
			  int cont = 0;
	          while (rs.next()) {
	        	  cont++;
	          }
	          
	          if (cont > 0 )
	        	  toRet = true;
          
		  	} catch (SQLException e) {
			  System.out.println(e.getMessage());
		  	}
		  
		  return toRet;
	}
}
