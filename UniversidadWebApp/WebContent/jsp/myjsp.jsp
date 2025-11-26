<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@ page import="java.util.Date" %>

<%! Date date = new Date(); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My JSP</title>
</head>
<body>
	
	<p>  <%=date %> <p>
	
	<%
	 for ( int i = 0; i < 10; i++)
		 if ( i % 2 == 0)
			 out.println( i + " es par <br>");
		 else
			 out.println( i + " es im par <br>");
	%>

</body>
</html>