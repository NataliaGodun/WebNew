<%@ page language="java" contentType="text/html; charset=utf-8"
	import="by.htp.library.domain.Book" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>
		Books: <br />
		<c:forEach items="${requestScope.List}" var="List">
			<c:out value=" ${List.id}" />
			<c:out value=" ${List.nazvanie}" />
			<c:out value=" ${List.avtor}" />
			<form action="Controller" method="get">
			<input type="hidden" name="command" value="viewBook"/>
			<input type="hidden" name="id" value=" ${List.id}" />
			<input type="submit" value="view book"/>
			</form>
			<br />
		</c:forEach>
	</h1>
</body>
</html>













