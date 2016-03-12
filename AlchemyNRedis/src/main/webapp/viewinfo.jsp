<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alchemy & Redis</title>
    </head>
    <body>
                <div>
					<h1> Age and gender successfully stored in Redis Service</h1>
					<%
						out.println("<h3> Age:" + request.getAttribute("agerange") + "</h3>");
						out.println("<h3> Gender:" + request.getAttribute("gender") + "</h3>");
					%>
                </div>
    </body>
</html>