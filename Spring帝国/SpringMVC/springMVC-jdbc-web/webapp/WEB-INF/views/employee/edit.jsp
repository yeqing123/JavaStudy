<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>编辑员工信息</h2>

	<form action="/employee/saveOrUpdate" method="post">
		<input type="hidden" name="id" value="${e.id}">
		<table border="1" cellspacing="0" cellpadding="10">
			<tr>
				<td>员工姓名</td>
				<td><input type="text" name="username" value="${e.username}"></td>
			</tr>
			<tr>
				<td>员工年龄</td>
				<td><input type="number" name="age" value="${e.age}"></td>
			</tr>
			<tr>
				<td>雇佣时间</td>
				<td><input type="date" name="hiredate" value='<fmt:formatDate value="${e.hiredate}" pattern="yyyy-MM-dd"/>'></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="${e.id==null?'添加新员工':'修改员工信息'}"></td>
			</tr>
		</table>
	</form>
</body>
</html>