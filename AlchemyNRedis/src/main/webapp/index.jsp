<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="Bean.AlchemyConnector" %>
<%@page import="Bean.RedisConnector"%>
<%@page import="Bean.SetOperations"%>
<%@page import="java.util.*"%>
<%@page import="javax.servlet.jsp.*"%>
<%@page import="java.io.*"%>

<%!
// initialize Jedis
SetOperations so = new SetOperations("candidates");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alchemy & Redis</title>
    </head>
    <body>
		<%
        session.setAttribute("setoper", so);
        %>
                <div>
                    <form action="ALServlet" method="GET">
                        <input type="text" name="gurl" size="80">
                        <input type="submit" value="Extract Information">
                    </form>
					<%
						if (request.getAttribute("face") != null){
								out.println("<h3>" + request.getAttribute("face") + "</h3>");
							}
						out.println("<h3>" + request.getAttribute("agerange") + "</h3>");
						out.println("<h3>" + request.getAttribute("gender") + "</h3>");
					%>
                </div>
    </body>
</html>