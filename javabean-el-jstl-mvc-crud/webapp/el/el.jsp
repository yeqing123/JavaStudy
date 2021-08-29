<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%
        pageContext.setAttribute("msg", "pageContextValue");
        request.setAttribute("msg", "requestValue");
        session.setAttribute("msg", "sessionValue");
        application.setAttribute("msg", "applicationValue");
    %>
    <h2>不用EL查找属性值</h2>
    pageContext:  <%=pageContext.getAttribute("msg")%><br/>
    request:  <%=request.getAttribute("msg")%><br/>
    session:  <%=session.getAttribute("msg")%><br/>
    application:  <%=application.getAttribute("msg")%><br/>
    <%=pageContext.findAttribute("msg")%>
    
    <hr/>
    
    <h2>使用EL查找属性值</h2>
    ${msg}
    <hr/>
    <h2>EL的内置对象</h2>
    ${param.name}<br/>
    ${paramValues}<br/>
    ${header.name}<br/>
    ${cookie.JSESSIONID.value}<br/>
    ${headerValues}
    
    <hr/>
    
    <h2>获取Employee对象中属性的两种方式</h2>
    <h3>方式一：</h3>
    id：${emp.id}<br/>
    name：${emp.name}<br/>
    hobbys[0]：${emp.hobbys[0]}<br/>
    list.get(1)：${emp.list[1]}-->${emp.list.get(1)}<br/>
    map.get("s_name")：${emp.map.s_name}-->${emp.map.get("s_name")}<br/>
    <!-- 为属性名起一个有意义的英文单词或短语，很重要 -->
    emp.map.get("www.xiaomage.com")：${emp.map.www.xiaomage.com}<br/>
    <h3>方式二：</h3>
    id：${emp["id"]}<br/>
    name：${emp["name"]}<br/>
    hobbys[0]：${emp["hobbys"][0]}<br/>
    list.get(1)：${emp["list"][1]}-->${emp.list.get(1)}<br/>
    map.get("s_name")：${emp["map"]["s_name"]}-->${emp.map.get("s_name")}<br/>
    emp.map.get("www.xiaomage.com")：${emp["map"]["www.xiaomage.com"]}<br/>
    
    <hr/>
    
    <h2>使用EL获取上下文路径</h2>
    --><%=request.getContextPath()%><br/>
    -->${pageContext.getRequest().getContextPath()}<br/>
    -->${pageContext.request.contextPath}<br/>
    
    <hr/>
    
    <h2>El编写运算式，并做比较</h2>
    <h3>使用内置关键字empty判断是否为空</h3>
    <%
        pageContext.setAttribute("name", "AAA");
        pageContext.setAttribute("age", null);
        pageContext.setAttribute("gender", "");
        pageContext.setAttribute("list", new ArrayList<String>());
    %>
    name是否为空：${empty name}<br/>
    age是否为空：${empty age}<br/>
    gender是否为空：${empty gender}<br/>
         集合list是否为空：${empty list}<br/>
    
    <h3>使用El进行比较(num=5)：</h3>
    <%
    	pageContext.setAttribute("num", 5);
    %>
    num是否大于30：${num > 30}<br/>
    num是否小于30：${num < 30}<br/>
    num是否等于5：${num == 5}-->${num eq 5}<br/>
    num是否不等于5：${num != 5}<br/>
</body>
</html>