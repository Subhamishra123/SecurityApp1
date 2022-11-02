<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1 style="color: red;text-align: center;">Access is Denied for <%=SecurityContextHolder.getContext().getAuthentication().getName() %></h1>
<a href="./">Home</a>
</body>
</html>