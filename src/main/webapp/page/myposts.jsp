<%@page import="dao.MasterDao"%>
<%@page import="dao.ArticleDao"%>
<%@page import="model.Article"%>
<%@page import="dao.CategoryDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%
int id = Integer.parseInt(request.getParameter("strId"));
List<Article> list = MasterDao.getArticlesByAuthorId(id);
request.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<title>myposts</title>
<%@include file="../components/common_css_js.jsp"%>
</head>
<body>
	<%@include file="../components/navbar.jsp"%>

	<div class="container-fluid">
		<div class="container">
			<div class="pt-4 form-group text-center">
				<a href="<%=request.getContextPath()%>/page/article.jsp"
					class="btn btn-outline-secondary btn-lg btn-default">Add New</a>
			</div>


			<table id="tooltable" class="table">
				<tr>
					<th>Title</th>
					<th>View</th>
					<th>Edit</th>
				</tr>
				<c:forEach var="entity" items="${list}">
					<c:set var="toolname" value="${tool.getToolName()}" />

					<tr>
						<td><c:out value="${entity.getTitle()}" /></td>
						<td><a
							href="<%=request.getContextPath()%>/page/post.jsp?strId=${entity.getId()}">View</a></td>
						<td><a
							href="<%=request.getContextPath()%>/page/article.jsp?strId=${entity.getId()}">Edit</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<%@include file="../components/footerbar.jsp"%>
</body>
</html>