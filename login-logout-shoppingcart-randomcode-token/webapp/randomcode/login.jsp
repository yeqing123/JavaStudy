<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
		function change() {
			//重新刷新验证码，显示新的内容
			document.getElementById("randomcodeImg").src = "/randomCode?" + new Date().getTime();
		}
	</script>
</head>
<body>
	<%
		session.invalidate();  //每次登录前都注销原来的Session对象
	%>
	<h3>登录页面</h3>
	<span style="color:red;">${errorMsg}</span>
	<form action="/randomcodeLogin" method="POST">
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>账户：</th>
				<td><input type="text" name="username" required/></td>
			</tr>
			<tr>
				<th>密码：</th>
				<td><input type="password" name="password" required/></td>
			</tr>
			<tr>
				<th>验证码：</th>
				<td>
					<input type="text" name="randomcode" required size="4" maxlength="4"/>
					<img id="randomcodeImg" src="/randomCode" title="看不清，换一张" style="cursor: pointer;" onclick="change();"/>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="朕要登录"/></td>
			</tr>
		</table>
	</form>
</body>
</html>