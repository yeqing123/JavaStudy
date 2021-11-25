<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

	session.getAttribute("errorMsg")： <%=session.getAttribute("errorMsg")%><br/>
	sessionScope.errorMsg： ${sessionScope.errorMsg}<br/>
	requestScope.msg： ${requestScope.errorMsg}