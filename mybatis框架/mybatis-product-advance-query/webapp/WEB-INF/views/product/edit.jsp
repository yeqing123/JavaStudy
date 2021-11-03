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
	<form action="/products?cmd=saveOrUpdate" method="post">
		<input type="hidden" name="id" value="${p.pid}"/>   <!-- 隐藏标签，如果是它的值不为空，表示为修改操作，否则就是添加操作 -->
	    商品名称：<input type="text" name="name" value="${p.name}" required/><br/>
	    商品简介：<textarea name="note" required>${p.note}</textarea><br/>
	    商品单价：<input type="text" name="price" value="${p.price}" required/><br/>
	    商品库存：<input type="number" name="count" value="${p.count}" min=1 required/><br/>
	    商品分类：
	    <select name="dir">
	        <option value="-1"}>暂不分类</option>
	    	<c:forEach items="${dirs}" var="dir">
	    		<option value="${dir.id}" ${p!=null&&dir.id==p.subDir?"selected":""}>${dir.name}</option>
	    	</c:forEach>
	    </select><br/>
      <input type="submit" value="${p!=null?'修改商品信息':'添加商品'}"/>
    </form>
</body>
</html>