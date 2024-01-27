<%@page import="dao.ArticleDao"%>
<%@page import="model.Article"%>
<%@page import="dao.CategoryDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%

List<Article> list = ArticleDao.getAllArticles();
request.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<title>posts</title>
<%@include file="../components/common_css_js.jsp"%>
</head>
<body>
	<%@include file="../components/navbar.jsp"%>

	<div class="container">
		<div class="pt-4 form-group text-center">
			<a href="<%=request.getContextPath()%>/page/article.jsp"
				class="btn btn-outline-secondary btn-lg btn-default">Add New</a>
		</div>


		<table id="tooltable" class="table">
			<tr>
				<th>Id</th>
				<th>Title</th>
				<th>Content</th>
				<th>AuthorId</th>
				<th>Approve</th>
				<th>View</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<c:forEach var="entity" items="${list}">
				<c:set var="toolname" value="${tool.getToolName()}" />

				<tr>
					<td><c:out value="${entity.getId()}" /></td>
					<td><c:out value="${entity.getTitle()}" /></td>
					<td><c:out value="${entity.getContent()}" /></td>
					<td><c:out value="${entity.getAuthorId()}" /></td>
					<td><a	href="<%=request.getContextPath()%>/ArticleUnpublishServlet?strId=${entity.id}">Unapprove</a></td>
					<td><a	href="<%=request.getContextPath()%>/page/post.jsp?id=${entity.getId()}">View</a></td>
					<td><a	href="<%=request.getContextPath()%>/page/article.jsp?id=${entity.getId()}">Edit</a></td>
					<td><a href="<%=request.getContextPath()%>/ArticleDeleteServlet?strId=${entity.getId()}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<%@include file="../components/footerbar.jsp"%>
</body>
</html>