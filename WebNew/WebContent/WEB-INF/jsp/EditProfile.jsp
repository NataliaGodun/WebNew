<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	Select :
	<br/>
	<br/>
	<form action="Controller" method="get">
		<input type="hidden" name="command" value="editProfileNameForm" /> 
		<input type="submit" value="Changes name" />
	</form>
	<br/>
	<br/>
	<form action="Controller" method="get">
		<input type="hidden" name="command" value="editProfilePasswordForm" /> 
		<input type="submit" value="Changes password" />
	</form>
</body>
</html>