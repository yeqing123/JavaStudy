<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <!-- 动态包含，并给被包含的页面传递数据 -->
    <jsp:include page="top.jsp">
      <jsp:param name="username" value="Lucy"/>
    </jsp:include>
    <br/>
     main部分代码
     <br/>
     <jsp:include page="foot.jsp"/>
     ================================================================<br/>
     <!-- 静态包含，无法传递数据 -->
    <%@include file="top.jsp"%>
    <br/>
     main部分代码
     <br/>
     <%@include file="foot.jsp"%>
</body>
</html>