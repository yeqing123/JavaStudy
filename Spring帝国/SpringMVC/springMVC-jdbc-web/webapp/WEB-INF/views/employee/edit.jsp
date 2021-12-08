<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>编辑员工信息</h2>
	<c:forEach items="${errprs}" var="error">
		<span style="color:red">${error.defaultMessage}</span>
	</c:forEach>
	<form action="/employee/saveOrUpdate" method="post">
		<input type="hidden" name="id" value="${e.id}">
		<table border="1" cellspacing="0" cellpadding="10">
			<tr>
				<td>员工姓名</td>
				<td><input type="text" name="username" value="${e.username}"></td>
			</tr>
			<tr>
				<td>员工年龄</td>
				<td><input type="number" name="age" value="${e.age}"></td>
			</tr>
			<tr>
				<td>雇佣时间</td>
				<td><input type="date" name="hiredate" value='<fmt:formatDate value="${e.hiredate}" pattern="yyyy-MM-dd"/>'></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="${e.id==null?'添加新员工':'修改员工信息'}"></td>
			</tr>
		</table>
	</form>
	
	
	
	<!-- 使用springMVC自带的表单标签 -->
	<!-- modelAttribute是后台共享过来的模型数据的名称 -->
	<%-- <form:form action="/employee/saveOrUpdate" method="post" modelAttribute="e">
		<form:hidden path="id"/>
		<table border="1" cellspacing="0" cellpadding="10">
			<tr>
				<td>员工姓名</td>
				<td>
					<form:input path="username"/>
					<form:errors path="username" cssClass="text_error"/>
				</td>
			</tr>
			<tr>
				<td>员工年龄</td>
				<td>
					<form:input path="age"/>
					<form:errors path="age" cssClass="text_error"/>
				</td>
			</tr>
			<tr>
				<td>雇佣时间</td>
				<td>
					<form:input path="hiredate"/>
					<form:errors path="hiredate" cssClass="text_error"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="${e.id==null?'添加新员工':'修改员工信息'}"></td>
			</tr>
		</table>
	</form:form> --%>
</body>
</html>