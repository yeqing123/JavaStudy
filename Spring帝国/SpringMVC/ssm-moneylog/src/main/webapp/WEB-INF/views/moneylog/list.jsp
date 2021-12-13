<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>写礼支出信息列表</h2>
	<a href="/moneylog/input">新增</a>
	<table border="1" width="80%" cellpadding="10" cellspacing="0">
		<tr>
			<th>编号</th>
			<th>姓名</th>
			<th>时间</th>
			<th>地点</th>
			<th>事由</th>
			<th>支出金额</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${all}" var="ml">
			<tr>
				<td>${ml.id}</td>
				<td>${ml.name}</td>
				<td><fmt:formatDate value="${ml.actionDate}" pattern="yyyy-MM-dd"/></td>
				<td>${ml.locale}</td>
				<td>${ml.event}</td>
				<td>${ml.money}</td>
				<td><a href="/moneylog/delete?id=${ml.id}">删除</a>&nbsp;<a href="/moneylog/input?id=${ml.id}">编辑</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>