<%@page import="model.Article"%>
<%@page import="dao.MasterDao"%>
<%@page import="dao.CategoryDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%
List<Article> list = MasterDao.getAllUnApproveArticles();
request.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<title>submission</title>
<%@include file="../components/common_css_js.jsp"%>
</head>
<body>
	<%@include file="../components/navbar.jsp"%>
	<div class="container">
		<div class="pt-4 form-group text-center">
			<a href="updateTool.jsp"
				class="btn btn-outline-secondary btn-lg btn-default">Add Tool</a>
		</div>


		<table id="tooltable" class="table">
			<tr>
				<th>Id</th>
				<th>Title</th>
				<th>Content</th>
				<th>Approve</th>
				<th>View</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<c:forEach var="entity" items="${list}">
				<tr>
					<td><c:out value="${entity.id}" /></td>
					<td><c:out value="${entity.title}" /></td>
					<td><c:out value="${entity.content}" /></td>
					<td><a	href="<%=request.getContextPath()%>/ArticlePublishServlet?strId=${entity.id}">Approve</a></td>
					<td><a	href="<%=request.getContextPath()%>/page/post.jsp?strId=${entity.getId()}">View</a></td>
					<td><a	href="<%=request.getContextPath()%>/page/article.jsp?strId=${entity.getId()}">Edit</a></td>
					<td><a href="<%=request.getContextPath()%>/ArticleDeleteServlet?strId=${entity.getId()}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>

	</div>
	<%@include file="../components/footerbar.jsp"%>
</body>
</html>