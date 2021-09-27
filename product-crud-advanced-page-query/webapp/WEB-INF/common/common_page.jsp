<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	function go(pageNo) {
		//获取id为currentPageId的组件：<input type="text" id="currentPageId" ...>
		document.getElementById("currentPageId").value=pageNo;
		//提交表单
		document.forms[0].submit();
	}
</script>

<a href="javascript:go(1);">首页</a>
<a href="javascript:go(${pageResult.prevPage});">上页</a>
<c:forEach begin="${pageResult.pageIndex.startIndex}" end="${pageResult.pageIndex.endIndex}" step="1" var="index">
	<c:if test="${index==pageResult.currentPage}">
		<span style="color:red;">${index}</span>
	</c:if>
	<c:if test="${index!=pageResult.currentPage}">
	    <a href="javascript:go(${index});">${index}</a>
	</c:if>
</c:forEach>
<a href="javascript:go(${pageResult.nextPage});">下页</a>
<a href="javascript:go(${pageResult.totalPage});">末页</a>
&nbsp;
当前页：[${pageResult.currentPage}/${pageResult.totalPage}]
&nbsp;&nbsp;
一共有 ${pageResult.totalCount} 条数据
&nbsp;&nbsp;
跳转到第<input type="number" id="currentPageId" name="currentPage" value="${pageResult.currentPage}" min="1" max="${pageResult.totalPage}" style="width:70px;"/>
<input type="submit" value=" GO "/>
&nbsp;&nbsp;
每页最多显示<select name="pageSize" onchange="javascript:go(1)">
			<c:forEach begin="1" end="10" step="2" var="size">
				<option value="${size}" ${pageResult.pageSize==size?"selected":""}>${size}</option>
			</c:forEach>
		</select>