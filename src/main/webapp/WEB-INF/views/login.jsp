<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login page</title>
</head>
<body>
<form action="/login" method="post">
<p><font color="red"> ${error}</font></p>
Enter Your Name:
<input type="text" name="name"/>
<input type="text" name="password">
<input type="submit" value="login"/>
</form>
</body>
</html>