<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>朕要注册</h1>
	<span style="color:red;">${errorMsg}</span>
	<form action="/upload" method="POST" enctype="multipart/form-data">
	    姓名：<input type="text" name="username" required/><br/>
	    邮箱：<input type="text" name="email" required/><br/>
	    头像：<input type="file" name="headImg" accept="image/*" readonly/><br/><br/>
	    <input type="submit" value="朕要注册"/>
    </form>
</body>
</html>