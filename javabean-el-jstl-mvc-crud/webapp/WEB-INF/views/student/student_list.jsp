<%@page import="javax.swing.text.StyledEditorKit.ForegroundAction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List" import="com.yeqing._05_jsp.smis.domain.Student"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>所有学生的信息列表：</h1>
    <table border="1" width="80%" cellpadding="10" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>姓名</th>
            <th>年龄</th>
        </tr>
        <% 
            List<Student> list = (List)request.getAttribute("students"); 
            for(Student stu : list) {
        %>
        <tr>
            <td><%=stu.getId()%></td>
            <td><%=stu.getName() %></td>
            <td><%=stu.getAge() %></td>
        </tr>
        <% 
            }
        %>
    </table>
</body>
</html>