<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function confirmDel() {
		return confirm("是否确认删除？");
	}
</script>
</head>
<body>
	<h2>用户信息列表</h2>
	<a href="/user/input">Add new user</a>
	<table border="1" cellspacing="0" cellpadding="10" width="80%">
		<tr>
			<th>ID</th>
			<th>NAME</th>
			<th>AGE</th>
			<th>BORNDATE</th>
			<th>HEADIMG</th>
			<th colspan=2>OPERATE</th>
		</tr>
		<c:forEach items="${list}" var="u">
			<tr>
				<td>${u.id}</td>
				<td>${u.name}</td>
				<td>${u.age}</td>
				<td><fmt:formatDate value="${u.bornDate}" pattern="yyyy-MM-dd"/></td>
				<td>${u.headImg}</td>
				<td><a href="/user/delete?id=${u.id}" onclick="return confirmDel()">DELETE</a></td>
				<td><a href="/user/input?id=${u.id}">UPDATE</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>