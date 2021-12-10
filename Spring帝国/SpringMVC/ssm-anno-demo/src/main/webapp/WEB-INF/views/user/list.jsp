<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>用户信息列表</h2>
	<form action="" method="post">
		<table border="1" cellspacing="10" cellpadding="0">
			<tr>
				<th>ID</th>
				<th>NAME</th>
				<th>AGE</th>
				<th>BORNDATE</th>
				<th>HEADIMG</th>
			</tr>
			<c:forEach items="${list}" var="u">
				<tr>
					<td>${u.id}</td>
					<td>${u.name}</td>
					<td>${u.age}</td>
					<td>${u.bornDate}</td>
					<td>${u.headImg}</td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>