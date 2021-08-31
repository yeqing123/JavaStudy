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
		<input type="hidden" name="id" value="${student.id}"/>  <%-- 定义一个隐藏标签，保存学生的id，用于后台识别是执行保存还是修改学生信息的操作 --%>
  	  	姓名：<input type="text" name="name" value="${student.name}" required/><br/>
    	年龄：<input type="number" name="age" value="${student.age}" required/><br/><br/>
    	<input type="submit" value="${student!=null?'修改学生信息':'保存学生信息'}"/>
    </form>
</body>
</html>