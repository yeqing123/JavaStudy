<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function go(pageNum) {
		document.getElementById("cp").value=pageNum;  //获取id等于“cp”的组件，并设置它的值
		document.forms[0].submit();  //提交表单
	}
</script>
</head>
<body>
           当前第[${pageInfo.pageNum}]页&nbsp;
   	<a href="javascript: go(1);">首页</a>&nbsp;
   	<a href="javascript: go(${pageInfo.prePage});">上页</a>&nbsp;
   	<c:forEach begin="${pageIndex.startIndex}" end="${pageIndex.endIndex}" step="1" var="index">
   		<c:if test="${index==pageInfo.pageNum}">
			<span style="color:red;">${index}</span>
		</c:if>
		<c:if test="${index!=pageInfo.pageNum}">
	   	 	<a href="javascript:go(${index});">${index}</a>
		</c:if>
   	</c:forEach>
   	<a href="javascript: go(${pageInfo.nextPage});">下页</a>&nbsp;
   	<a href="javascript: go(${pageInfo.pages});">末页</a>&nbsp;&nbsp;
   	一共查询到${pageInfo.total}条数据，每页最多显示
   	<select name="pageSize">
   		<c:forEach items="${qo.pageSizeList}" var="ps">
   			<option value="${ps}" ${ps==pageInfo.pageSize?"selected":""}>${ps}</option>
   		</c:forEach>
   	</select>
   	条数据&nbsp;&nbsp;
   	共计${pageInfo.pages}页&nbsp;跳转到第<input type="number" id="cp" name="currentPage" value="${pageInfo.pageNum}" style="width:40px;" min=1/>
   	<input type="submit" value="GO" style="background: orange; width: 40px;"/>
</body>
</html>