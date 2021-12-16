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
		return confirm("是否确定删除？");
	}
</script>
</head>
<body>
	<h2>写礼支出信息列表</h2>
	<a href="/moneylog/input">新增</a>
	<br />
	<form action="/moneylog/query" method="POST">
		姓名：<input type="text" name="name" value="${qo.name}"
			style="width: 80px;" />&nbsp; 时间区间：从<input type="date"
			name="startDate"
			value='<fmt:formatDate value="${qo.startDate}" pattern="yyyy-MM-dd"/>' />
		到<input type="date" name="endDate"
			value='<fmt:formatDate value="${qo.endDate}" pattern="yyyy-MM-dd"/>' />&nbsp;
		支出金额：<input type="number" name="minMoney" value="${qo.minMoney}" min=0
			style="width: 80px;" />- <input type="number" name="maxMoney"
			value="${qo.maxMoney}" min=0 style="width: 80px;" />&nbsp; 关键词：<input
			type="text" name="keyword" value="${qo.keyword}" style="width: 80px;" />&nbsp;
		<input type="submit" value="查询" style="background-color: orange;" />

		<table border="1" width="80%" cellpadding="10" cellspacing="0">
			<tr style="background-color: orange;">
				<th>编号</th>
				<th>姓名</th>
				<th>时间</th>
				<th>地点</th>
				<th>事由</th>
				<th>支出金额</th>
				<th>操作</th>
			</tr>
			<c:choose>
				<c:when test="${pageInfo.total==0}">
					<tr>
						<td colspan=7 align=center style="font:bold;font-size:30px;">不好意思，没有您要查询的数据！</td>
						
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${pageInfo.list}" var="ml" varStatus="vs">
						<tr style="background-color:${vs.count%2==0?'gray':''};">
							<td>${ml.id}</td>
							<td>${ml.name}</td>
							<td><fmt:formatDate value="${ml.actionDate}"
									pattern="yyyy-MM-dd" /></td>
							<td>${ml.locale}</td>
							<td>${ml.event}</td>
							<td>${ml.money}元</td>
							<td><a href="/moneylog/delete?id=${ml.id}"
								onclick="return confirmDel()">删除</a>&nbsp; <a
								href="/moneylog/input?id=${ml.id}">编辑</a></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		<jsp:include page="/WEB-INF/views/commons/common_page.jsp" />
	</form>
</body>
</html>