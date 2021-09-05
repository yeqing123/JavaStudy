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
    <h2>雇员信息列表：</h2>
    <a href="/employee?cmd=edit">添加新雇员</a>
    <table border="1" width="80%" cellpadding="10" cellspacing="0">
    	<tr style="background-color:orange">
    		<th>编号</th>
    		<th>姓名</th>
    		<th>职务</th>
    		<th>月薪</th>
    		<th colspan="2">操作</th>
    	</tr>
    	<c:forEach items="${employees}" var="e" varStatus="vs">
	    	<tr style="background-color:${vs.count%2==0?'gray':''}">
	    	    <td>${e.id}</td>
	    	    <td>${e.name}</td>
	    	    <td>${e.job}</td>
	    	    <td>${e.salary}</td>
				<td style="text-align:center"><a href="/employee?cmd=delete&id=${e.id}">删除</a></td>
				<td style="text-align:center"><a href="/employee?cmd=edit&id=${e.id}">编辑</a></td>
	    	</tr>
    	</c:forEach>
    </table> 
</body>
</html>