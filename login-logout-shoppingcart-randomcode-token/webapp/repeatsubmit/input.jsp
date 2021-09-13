<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/transfer" method="POST">
	<input type="hidden" name="token" value="${token}"/>
	   输入转账金额：<input type="number" name="money" required/>
	   <input type="submit" value="转账"/>
	</form>
</body>
</html>