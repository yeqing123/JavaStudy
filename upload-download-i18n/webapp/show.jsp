<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    注册名称：${u.username}<br/>
    注册邮箱：${u.email}<br/>
    注册头像的原始名称：${u.imageInfo.imageName}<br/>
    头像显示：<br/>
    <img src="/upload/${u.imageInfo.imageUrl}"/>
</body>
</html>