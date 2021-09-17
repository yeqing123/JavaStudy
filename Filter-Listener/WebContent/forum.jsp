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
    <table border="1" cellpadding="10" cellspacing="0" width="80%">
    	<tr>
    		<th>标题</th>
    		<th>内容</th>
    	</tr>
    	<c:forEach items="${sessionScope.LIST_IN_SESSION}" var="speak">
	    	<tr>
	    		<td>${speak.title}</td>
	    		<td>${speak.content}</td>
	    	</tr>
	    </c:forEach>
    </table>
    <hr/>
    <form action="/deliver" method="POST">
    	标题：<input type="text" name="title" required/><br/>
    	内容：<textarea name="content" cols="30" rows="7" required style="resize: none; vertical-align: top;"></textarea><br/>
    	<input type="submit" value="提交"/>
    </form>
</body>
</html>