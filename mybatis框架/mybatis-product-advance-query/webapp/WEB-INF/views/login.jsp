<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<form action="check" method="POST">
		<table border="0" cellpadding="10" cellspacing="0" width="30%" align="center">
			<tr>
				<td colspan="2" align="center"><font style="color:red;">${msg}</font></td>
			</tr>
			<tr>
				<td align="right">姓名：</td>
				<td><input type="text" name="username" value="${username}" required/></td>
		    </tr>
			<tr>
				<td align="right">密码：</td>
				<td><input type="password" name="password" value="${password}" required/></td>
		    </tr>
            <tr>
            	<td colspan="2" align="center"><input type="submit" value="朕要登录"/></td>
            </tr>		
        </table>
	</form>
</body>
</html>