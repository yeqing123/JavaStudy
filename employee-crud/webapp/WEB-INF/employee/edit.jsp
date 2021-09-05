<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form action="/employee?cmd=save" method="POST">
        <input type="hidden" name="id" value="${e.id}"/>
    	姓名：<input type="text" name="name" value="${e.name}" required /><br/>
    	职位：<input type="text" name="job" value="${e.job}" required /><br/>
    	月薪：<input type="number" name="salary" value="${e.salary}" required /><br/><br/>
    	<input type="submit" value="${e==null?'保存雇员信息':'修改雇员信息'}"/>
    </form> 
</body>
</html>