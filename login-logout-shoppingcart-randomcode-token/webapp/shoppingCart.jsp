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
	<h3>购物车中的商品信息</h3>
	<table border="1" cellpadding="10" cellspacing="0" width="500">
		<tr style="background-color:orange;">
			<th>商品名称</th>
			<th>单价</th>
			<th>数量</th>
			<th>操作</th>
		</tr>
		<c:if test="${not empty SHOPPINGCART_IN_SESSION.items}">
			<c:forEach items="${SHOPPINGCART_IN_SESSION.items}" var="item">
				<tr>
					<td>${item.name}</td>
					<td>${item.price}</td>
					<td>${item.num}</td>
					<td><a href="shoppingCart?cmd=delete&id=${item.id}">删除</a></td>
				</tr>
			</c:forEach>
			<tr align="right">
				<td colspan="4">
					所有商品的总价值：${SHOPPINGCART_IN_SESSION.totalPrice}元
					[<a href="/product_list.jsp">继续购物</a>]
				</td>
			</tr>
		</c:if>
		<c:if test="${empty SHOPPINGCART_IN_SESSION.items}">
			<tr align="center">
				<td colspan="4">亲，你的购物车是空的，请先<a href="/product_list.jsp">购物</a></td>
			</tr>
		</c:if>
	</table>
</body>
</html>