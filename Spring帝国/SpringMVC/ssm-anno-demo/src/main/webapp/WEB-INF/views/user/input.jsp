<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>编辑用户信息</h2>
	<form action="/user/saveOrUpdate" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${u.id}"/><br/>
		姓名：<input type="text" name="name" value="${u.name}"/><br/>
		年龄：<input type="text" name="age" value="${u.age}"/><br/>
		生日：<input type="date" id="born" name="bornDate" value='<fmt:formatDate value="${u.bornDate}" pattern="yyyy-MM-dd"/>'/><br/>
		头像：<img src="${u.headImg}"/>
		上传头像<input type="file" name="img"><br/>
		<input type="submit" value="提交"/>
	</form>
</body>
</html>