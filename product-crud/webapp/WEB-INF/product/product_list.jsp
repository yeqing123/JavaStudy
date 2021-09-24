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
    <form action="/product" method="post">
    	商品名称：<input type="text" name="name" value="${qo.name}"/>
    	商品简介：<input type="text" name="note" value="${qo.note}"/>
    	价格区间：<input type="number" name="minprice" style="width:80px;" value="${qo.minprice}"/>
    		到<input type="number" name="maxprice" style="width:80px;" value="${qo.maxprice}"/>
                    商品分类：<select name="dir">
               		<option value="-1">所有分类</option>
               		<c:forEach items="${dirs}" var="d">
               			<option value="${d.id}" ${qo.dir==d.id?"selected":""}>${d.dirName}</option>
               		</c:forEach> 
               </select>
                    关键字：<input type="text" name="keyword" placeholder="商品名称或商品简介" value="${qo.keyword}"/>
    		<input type="submit" value="查询"/>
    <table border="1" width="80%" cellpadding="10" cellspacing="0">
    	<tr style="background-color:orange">
    		<th>编号</th>
    		<th>名称</th>
    		<th>简介</th>
    		<th>价格</th>
    		<th>数量</th>
    		<th>所属目录</th>
    	</tr>
    	<c:forEach items="${pageResult.listData}" var="p" varStatus="vs">
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
	    	</tr>
    	</c:forEach>
    	<tr>
    		<td colspan="6" align="center">
    			<%@include file="/WEB-INF/common/common_page.jsp"%>
    		</td>
    	</tr>
    </table>
    </form>
</body>
</html>