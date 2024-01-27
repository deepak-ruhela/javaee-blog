<%@page import="model.User"%>
<%@page import="dao.UserDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<title>users</title>
<%@include file="../components/common_css_js.jsp"%>
</head>
<body>
	<%@include file="../components/navbar.jsp"%>
	<%
	List<User> userList = UserDao.getAllUsers();;
	request.setAttribute("userList", userList);
	%>
	<div class="container">

		<div class="pt-4 form-group text-center">
			<a href="updateUser.jsp"
				class="btn btn-outline-secondary btn-lg btn-default">New User</a>
		</div>


		<table id="usertable" class="table">

			<tr>

				<th>ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>

			<c:forEach var="user" items="${userList}">
				<tr>

					<td><c:out value="${user.id}" /></td>
					<td><c:out value="${user.name}" /></td>
					<td><c:out value="${user.email}" /></td>
					<td><a href="<%=request.getContextPath()%>/page/user-process.jsp?strId=${user.id}">Edit</a></td>
					<td><a href="<%=request.getContextPath()%>/UserDeleteServlet?strId=${user.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<%@include file="../components/footerbar.jsp"%>
</body>
</html>