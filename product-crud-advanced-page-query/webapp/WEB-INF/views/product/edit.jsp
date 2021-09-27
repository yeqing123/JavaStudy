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
	<form action="/product?cmd=save" method="post">
	     <input type="hidden" name="id" value="${p.id}"/>
		   商品名称：<input type="text" name="name" value="${p.name}" required/><br/>
		   商品简介：<textarea rows="10" cols="50" name="note" required style="resize: none;"/>${p.note}</textarea><br/>
		   商品单价：<input type="text" name="price" value="${p.price}" required/><br/>
		   商品库存：<input type="number" name="count" value="${p.count}" required/><br/>
		   商品分类：<select name="dir">
		   			<c:forEach items="${dirs}" var="dir">
		   				<option value="${dir.id}" ${p.dir==dir.id?"selected":""}>${dir.dirName}</option>
		   			</c:forEach>
		   		</select><br/>
	   	<input type="submit" value="${p==null?'新增':'修改'}"/>
	</form>
</body>
</html>