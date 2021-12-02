<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>登录页面</h2>
	<span style="color:red;">${errorMsg_in_session}</span>
	<% 
		session.invalidate();
	%>
	<form action="/login" method="post">
		<table border="1" cellspacing="0" cellpadding="10">
			<tr>
				<td>账户</td>
				<td><input type="text" name="username"required></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="password" name="password" required></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="登录"></td>
			</tr>
		</table>
	</form>
</body>
</html>