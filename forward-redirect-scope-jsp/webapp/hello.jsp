<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/WEB-INF/404.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    北京时间：<%=new java.util.Date().toLocaleString()%>
  <%=request.getParameter("name") %>
  <%=config.getServletName()%>
  <jsp:forward page="/scope"></jsp:forward>
</body>
</html>