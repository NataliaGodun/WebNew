<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form action="Controller" method="post">
		<input type="hidden" name="command" value="deleteBook" /> Nazvanie knigi:<br />
		<input type="text" name="nazvanie" value="" /><br /> Avtor:<br /> 
		<input type="text" name="avtor" value="" /><br />
		 <input type="submit" value="Delete book" /> <br />
	
	</form>
</body>
</html>