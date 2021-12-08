<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/upload" method="post" enctype="multipart/form-data">
		姓名：<input type="text" name="username"/><br/>
		密码：<input type="password" name="password"/><br/>
		文件上传：<input type="file" name="pic"/><br/>
		<input type="submit" value="提交"/>
		
		<a href="/download?fileName=panda.rar">panda.rar</a>
	</form>
</body>
</html>