<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		session.invalidate();  //每次登录前都注销原来的Session对象
	%>
	<h3>登录页面</h3>
	<span style="color:red;">${errorMsg}</span>
	<form action="/login" method="POST">
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>账户：</th>
				<td><input type="text" name="username" required/>
			</tr>
			<tr>
				<th>密码：</th>
				<td><input type="password" name="password" required/>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="朕要登录"/>
			</tr>
		</table>
	</form>
</body>
</html>