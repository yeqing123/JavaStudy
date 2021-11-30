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
    <h2>员工信息列表</h2>
    <form action="" method="post">
    	<table border="1" width="80%">
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
	    			<td><a rel="">删除</a>&nbsp;<a rel="">修改</a></td>
	    		</tr>
    		</c:forEach>
    	</table>
    </form>
</body>
</html>
