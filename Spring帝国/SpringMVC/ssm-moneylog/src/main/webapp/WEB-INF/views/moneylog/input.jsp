<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>编辑写礼支出信息</h2>
	<form action="/moneylog/saveOrUpdate" method="POST">
		<input type="hidden" name="id" value="${moneylog.id}"/><br/>
		姓名：<input type="text" name="name" value="${moneylog.name}"/><br/>
		时间：<input type="date" name="actionDate" value="<fmt:formatDate value="${moneylog.actionDate}" pattern="yyyy-MM-dd"/>"/><br/>
		地点：<input type="text" name="locale" value="${moneylog.locale}"/><br/>
		事由：<textarea name="event" cols="15" rows="4" style="resize: none;">${moneylog.event}</textarea><br/>
		支出金额：<input type="number" name="money" value="${moneylog.money}"/><br/>
		<input type="submit" value="保存"/>
	</form>
</body>
</html>