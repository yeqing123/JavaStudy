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
		function confirmDelete() {
			return confirm("是否确认删除？");
		}
	</script>
</head>
<body>
    <h2>员工信息列表</h2>
    <form action="" method="post">
    	<a href="/employee/edit">新增员工</a>
    	<table border="1" cellspacing="0" cellpadding="10" style="width:80%;">
    		<tr style="background-color:orange">
    			<th>编号</th>
    			<th>姓名</th>
    			<th>年龄</th>
    			<th>雇佣日期</th>
    			<th>操作</th>
    		</tr>
    		<c:forEach items="${list}" var="e" varStatus="vs">
	    		<tr style="background-color:${vs.count%2==0?'gray':''}; ">
	    			<td>${e.id}</td>
	    			<td>${e.username}</td>
	    			<td>${e.age}</td>
	    			<td><fmt:formatDate value="${e.hiredate}" pattern="yyyy-MM-dd"/></td>
	    			<td style="width:80px;"><a href="/employee/delete?id=${e.id}" onclick="return confirmDelete()">删除</a>&nbsp;<a href="/employee/edit?id=${e.id}">修改</a></td>
	    		</tr>
    		</c:forEach>
    	</table>
    </form>
</body>
</html>
