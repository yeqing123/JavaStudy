<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function confirmDel() {
		return confirm("是否确定删除该条数据？");
	}
	function query() {
		//将id等于currentPageId的组件的值变成1，表示在执行高级查询时将页面跳到第一页
		document.getElementById("currentPageId").value="1";
		//提交表单
		document.forms[0].submit();
	}
</script>
</head>
<body>
    <h2>商品信息列表：</h2>
    <a href="/product?cmd=edit">添加新商品</a><br/>
	<form action="/product" method="post">
		商品名称：<input type="text" name="name" value="${qo.name}"/>
		商品简介：<input type="text" name="note" value="${qo.note}"/>
		价格区间：<input type="number" name="minprice" value="${qo.minprice}" min="1" style="width:70px;"/>到
			<input type="number" name="maxprice" value="${qo.maxprice}" style="width:70px;"/>
	          商品分类：<select name="dir">
	                <option value="-1">所有分类</option>
	          		<c:forEach items="${dirs}" var="dir">
	          			<option value="${dir.id}" ${dir.id==qo.dir?"selected":""}>${dir.dirName}</option>
	          		</c:forEach>
	          </select>
	    <input type="button" value=" 查询 " onclick="query();" style="background-color:orange;"/>
		<table border="1" cellpadding="10" cellspacing="0" width="80%">
			<tr style="background-color:gray;">
				<th>编号</th>
				<th>名称</th>
				<th>简介</th>
				<th>单价</th>
				<th>库存</th>
				<th>分类</th>
				<th colspan=2>操作</th>
			</tr>
			<c:forEach items="${pageResult.listData}" var="p" varStatus="vs">
				<tr style='background-color:${vs.count%2==0?"orange":""};'>
					<td>${p.id}</td>
					<td>${p.name}</td>
					<td>${p.note}</td>
					<td>${p.price}</td>
					<td>${p.count}</td>
					<td>
						<c:forEach items="${dirs}" var="dir">
							${p.dir==dir.id?dir.dirName:""}
						</c:forEach>
					</td>
					<td><a href="/product?cmd=delete&id=${p.id}" onClick="confirmDel();">删除</a></td>
					<td><a href="/product?cmd=edit&id=${p.id}">编辑</a></td>
				</tr>
			</c:forEach>
		</table>
		<jsp:include page="/WEB-INF/common/common_page.jsp"></jsp:include>
	</form>
</body>
</html>