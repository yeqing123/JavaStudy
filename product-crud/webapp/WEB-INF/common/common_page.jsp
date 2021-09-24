<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script lang="javascript">
	function go(pageNo) {
		//找到id为currentPageId的组件
		document.getElementById("currentPageId").value=pageNo;
		//提交表单
		document.forms[0].submit();
	}
	function go2() {
		//提交表单
		document.forms[0].submit();
	}
</script>

<a href="javascript:go(1);">首页</a>
<a href="javascript:go(${pageResult.prevPage});">上页</a>
<c:forEach begin="${pageResult.pageIndex.startIndex}" end="${pageResult.pageIndex.endIndex}" step="1" var="index">
    <c:if test="${index==pageResult.currentPage}">
		<span style="color:red;font:bold;">${index}</span>
	</c:if>
    <c:if test="${index!=pageResult.currentPage}">
        <a href="javascript:go(${index});">${index}</a>
	</c:if>
</c:forEach>
<a href="javascript:go(${pageResult.nextPage});">下页</a>
<a href="javascript:go(${pageResult.totalPage});">末页</a>
&nbsp;&nbsp;
当前页${pageResult.currentPage}/${pageResult.totalPage}
&nbsp;&nbsp;
一共有${pageResult.totalCount}条数据
&nbsp;&nbsp;
跳转到<input type="number" id="currentPageId" name="currentPage" value="${pageResult.currentPage}" min="1" max="${pageResult.totalPage}" style="width:70px;"/>
<input type="button" value="GO" onclick="go2()"/>
&nbsp;&nbsp;
每页显示<select name="pageSize" onchange="go(1)">
	  	<c:forEach begin="2" end="10" step="3" var="number">
	  	    <option value="${number}" ${pageResult.pageSize==number?"selected":""}>${number}</option>
	  	</c:forEach>
	  </select>
条数据
