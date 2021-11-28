<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	
		<form id="logform" method="post" name="logform" action="LoginServlet">
			<table>
				<tr>
					<td>Username: </td><td><input name="username" type="text"></td>
				</tr>
				<tr>
					<td>Password: </td><td><input name="username" type="password"></td>
				</tr>
				<tr>
					<td><input type="submit" name="submit" value="Connect"></td><td><a href="registration.jsp">Create account</a></td>
				</tr>
			</table>
		</form>
	
	<%@ include file="footer.jsp" %>
</body>
</html>