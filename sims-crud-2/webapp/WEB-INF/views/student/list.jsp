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
    <h2>学生信息列表</h2>
   
    <a href="/student?cmd=edit">添加学生信息</a>
    <table border="1" width="80%" cellpadding="10" cellspacing="0">
    	<tr style="background-color: orange">
    	    <th>编号</th>
    	    <th>姓名</th>
    	    <th>年龄</th>
    	    <th colspan="2">操作</th>
    	</tr>
    	<c:forEach items="${students}" var="s" varStatus="vs">
	    	<tr style="background-color: ${vs.count%2==0?'gray':''}">
	    	    <td>${s.id}</td>
	    	    <td>${s.name}</td>
	    	    <td>${s.age}</td>
	    	    <td style="text-align:center;"><a href="/student?cmd=delete&id=${s.id}">删除</a></td>
	    	    <td style="text-align:center"><a href="/student?cmd=edit&id=${s.id}">编辑</a></td>
	    	</tr>
    	</c:forEach>
    </table>
     
</body>
</html>