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
    <form action="/product?cmd=save" method="POST">
        <input type="hidden" name="id" value="${p.id}" required/>
	<table border="1" cellpadding="10" cellspacing="0">
		<tr>
	    	<td>商品名称</td>
	    	<td><input type="text" name="name" value="${p.name}" required/></td>
	    </tr>
    	<tr>
	    	<td>商品简介</td>
	    	<td><textarea name="note" cols="50" rows="6" required="required" draggable="false" >${p.note}</textarea></td>
    	</tr>
    	<tr>
	    	<td>商品价格</td>
	    	<td><input type="text" name="price" value="${p.price}" required/></td>
    	</tr>
    	<tr>
	    	<td>商品数量</td>
	    	<td><input type="number" name="count" value="${p.count}" required/></td>
    	</tr>
    	<tr>
	    	<td>所属目录</td>
	    	<td>
	    		<select name="dir" required>
	    		    <c:forEach items="${dirList}" var="d">
	    				<option value="${d.id}" ${p.dir==d.id?"selected":""}>${d.dirName}</option>
					</c:forEach>
	    		</select>
	    	</td>
    	</tr>
    	<tr>
	    	<td>所属子目录</td>
	    	<td>
	    	    <select name="subDir" required>
	    		    <c:forEach items="${subDirList}" var="sd">
	    				<option value="${sd.id}" ${p.subDir==sd.id?"selected":""}>${sd.subDirName}</option>
					</c:forEach>
	    		</select>
	    	</td>
    	</tr>
    	<tr>
    		<td colspan="2"><input type="submit" value="${p.id==null?'保存商品信息':'修改商品信息'}"/></td>
    	</tr>
    </table>
    </form>
</body>
</html>