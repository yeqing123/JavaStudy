<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form action="/student?cmd=save" method="POST">
        <input type="hidden" name="id" value="${s.id}"/>
		姓名：<input type="text" name="name" value="${s.name}" required/><br/>    
		年龄：<input type="number" name="age" value="${s.age}" required/><br/><br/>
		<input type="submit" value="${s==null?'保存学生信息':'修改学生信息'}">  
    </form>
</body>
</html>