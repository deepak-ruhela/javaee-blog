<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
if (session.getAttribute("user") != null) {
    User user = (User) session.getAttribute("user");
    if (!user.getRole().equals("ADMIN")) {
	session.setAttribute("message", "You are not admin!");
	response.sendRedirect(request.getContextPath());
    }
} else {
    session.setAttribute("message", "You are not logged in! Login First");
    response.sendRedirect("login");
}
%>
<!DOCTYPE html>

<html>
<head>
<title>admin</title>
<%@include file="../components/common_css_js.jsp"%>
<style>
</style>
</head>
<body>
	<%@include file="../components/navbar.jsp"%>
	<div class="container-fluid"></div>


</body>
</html>