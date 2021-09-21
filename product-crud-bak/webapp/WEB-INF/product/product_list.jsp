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
    <h2>商品信息列表：</h2>
    <a href="/product?cmd=edit">添加商品信息</a>
    <table border="1" width="80%" cellpadding="10" cellspacing="0">
    	<tr style="background-color:orange">
    		<th>编号</th>
    		<th>名称</th>
    		<th>简介</th>
    		<th>价格</th>
    		<th>数量</th>
    		<th>所属目录</th>
    		<th>所属子目录</th>
    		<th colspan="2">操作</th>
    	</tr>
    	<c:forEach items="${products}" var="p" varStatus="vs">
	    	<tr style="background-color:${vs.count%2==0?'gray':''}">
	    	    <td>${p.id}</td>
	    	    <td>${p.name}</td>
	    	    <td style="width: 50%;">${p.note}</td>
	    	    <td>${p.price}</td>
	    	    <td>${p.count}</td>
	    	    <td>
		    	    <c:forEach items="${dirs}" var="d">
		    	    	${p.dir==d.id?d.dirName:""}
		    	    </c:forEach>
	    	    </td>
	    	    <td>
	    	    	<c:forEach items="${subDirs}" var="sd">
	    	    		${p.subDir==sd.id?sd.subDirName:""}
	    	    	</c:forEach>
				</td>
	    	    <td style="text-aglin:center"><a href="/product?cmd=delete&id=${p.id}">删除</a></td>
	    	    <td style="text-aglin:center"><a href="/product?cmd=edit&id=${p.id}">编辑</a></td>
	    	</tr>
    	</c:forEach>
    </table>
</body>
</html>