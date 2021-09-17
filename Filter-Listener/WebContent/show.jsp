<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.yeqing.domain.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
    if(request.getAttribute("USER_IN_REQUEST") != null) {
%>
    姓名：${requestScope.USER_IN_REQUEST.name}<br/>
    密码：${requestScope.USER_IN_REQUEST.password}
<% 
    	request.setAttribute("USER_IN_REQUEST", new User("叶青", "12345"));
        request.removeAttribute("USER_IN_REQUEST");
    }
%>
</body>
</html>