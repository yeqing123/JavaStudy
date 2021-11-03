<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
		function del() {
			return confirm("是否确认删除？");
		}
		function exit() {
			return confirm("是否确认退出？");
		}
	</script>
</head>
<body>
    <h2>商品信息列表</h2>
    <div align="left">
    	管理员名称：${u.username}  <a href="/products?cmd=exit" onclick="javascript:return exit();">退出</a>
    </div>
    <form action="/products" method="POST">
        商品名称：<input type="text" name="name" value="${qo.name}"/>
        商品简介：<input type="text" name="note" value="${qo.note}"/>
         价格区间：<input type="number" name="minprice" value="${qo.minprice}" min=1 style="width:70px"/>到
         <input type="number" name="maxprice" value="${qo.maxprice}" style="width:70px"/>    
         关键字：<input type="text" name="keyword" value="${qo.keyword}"/>
         商品分类：<select name="dir">
          	<option value="-1">所有分类</option>
          	<c:forEach items="${dirs}" var="dir">
	          	<option value="${dir.id}" ${dir.id==qo.dir?"selected":""}>${dir.name}</option>
          	</c:forEach>
         </select>
         <button id="query" style="background: orange;"><font>查询</font></button>
         <a href="/products?cmd=edit">添加</a>
    	<table border="1" cellpadding="10" cellspacing="0" style="width: 80%;">
    		<tr style="background-color:gray;">
    			<th>编号</th>
    			<th>名称</th>
    			<th>简介</th>
    			<th>单价</th>
    			<th>库存</th>
    			<th>所属分类</th>
    			<th colspan="2">操作</th>
    		</tr>
    		<c:forEach items="${pageInfo.list}" var="p" varStatus="vs">
	    		<tr style="background: ${vs.count%2==0?'orange':''};">
	    			<td>${p.pid}</td>
	    			<td>${p.name}</td>
	    			<td>${p.note}</td>
	    			<td>${p.price}</td>
	    			<td>${p.count}</td>
	    			<td>
	    				<c:forEach items="${dirs}" var="dir">
	    					${p.subDir==dir.id?dir.name:''}
	    				</c:forEach>
	    			</td>
	    			<td><a href="/products?cmd=edit&id=${p.pid}">修改</a></td>
	    			<td><a href="/products?cmd=delete&id=${p.pid}" onclick="javascript: return del();">删除</a></td>
	    		</tr>
    		</c:forEach>
    	</table>
    	<jsp:include page="/WEB-INF/views/common/common_page.jsp"/>
    </form>
</body>
</html>