<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form action="/request/save" method="POST">
    	狗狗的名字：<input type="text" name="dog.name"/><br/>
    	狗狗的年龄：<input type="text" name="dog.age"/><br/>
    	猫咪的名字：<input type="text" name="cat.name"/><br/>
    	猫咪的年龄：<input type="text" name="cat.age"/><br/>
    	<input type="submit" name="提交"/>
    </form>
</body>
</html>