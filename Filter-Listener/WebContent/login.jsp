<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/show" method="POST">
	    姓名：<input type="text" name="name" required/><br/>
	    密码：<input type="text" name="password" required/><br/>
	    <input type="submit" value="朕要登录"/>
    </form>
</body>
</html>