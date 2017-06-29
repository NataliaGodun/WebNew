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
Books:
<br/>
		<c:out value=" ${requestScope.book.nazvanie}"  />
		<c:out value=" ${requestScope.book.avtor}"  />
		</h1>
</body>
</html>













