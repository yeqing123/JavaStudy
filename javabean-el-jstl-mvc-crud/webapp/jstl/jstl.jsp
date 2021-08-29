<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%-- 判断如果num属性小于10，则输出一句话 --%>
    <c:if test="${num < 10}">
        num大于10
    </c:if>
    
    <%-- 判断如果num属性小于10，则将结果保存在变量result中，并设定该变量的作用域为当前页面 --%>
    <c:if test="${num < 10}" var="result" scope="page">
        ${result}
    </c:if>
    
    <%-- 使用<c:choose/>标签选择 输出，当满足条件时的语句--%>
   	<c:choose>
   		<c:when test="${num < 5}">
   			<p>num小于5</p>
   		</c:when>
   		<c:when test="${num > 5}">
   			<p>num大于5</p>
   		</c:when>
   		<c:otherwise>
   			<p>num等于5</p>
   		</c:otherwise>
   	</c:choose>
   	
   	<%-- 使用<c:forEach/>标签，从1输出到10 --%>
   	<c:forEach begin="1" end="10" step="1" var="number">
   		${number}
   	</c:forEach>
   	
   	<%-- 使用<c:forEach/>标签，迭代一个集合中的所有数据 --%>
   	<%
   		pageContext.setAttribute("list", Arrays.asList("AA","BB","CC","DD"));
   	%>
   	<br/>
   	<c:forEach items="${list}" var="ele">
   		${ele}<br/>
   	</c:forEach>
   	
   	<br/>
   	<%-- 使用国际化标签库，按照中国人的习惯输出一个时间对象 --%>
   	<%
   		pageContext.setAttribute("date", new java.util.Date());
   	%>
   	<fmt:formatDate value="${date}"  pattern="yyyy-MM-dd HH:mm:ss"/>
</body>
</html>