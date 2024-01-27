<%@page import="dao.CategoryDao"%>
<%@page import="model.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<title>categories</title>
<%@include file="../components/common_css_js.jsp"%>
</head>
<body>
	<%@include file="../components/navbar.jsp"%>
<%-- 	<%
	List<Category> list = CategoryDao.getAllCategories();
	request.setAttribute("list", list);
	%>
	 --%><sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost/blogdb" user="root" password="root" />
	<sql:update dataSource="${snapshot}" var="count">
         Delete from category where id = ?;
         <sql:param value="${strId}" />
      </sql:update>
	<sql:query dataSource="${snapshot}" var="result">
         SELECT * from category;
      </sql:query>
	<div class="container">
		<div class="pt-4 form-group text-center">

			<a
				href="<%=request.getContextPath()%>/page/category-process.jsp?strId=0"
				class="btn btn-outline-secondary btn-lg btn-default">New
				Category</a>
		</div>
		<table id="tagtable" class="table">
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<%-- <c:forEach var="category" items="${list}"> --%>
			<c:forEach var = "row" items = "${result.rows}">
			<c:set var="strId" value="${row.id}"/>
				<tr>
					<td><c:out value="${row.id}" /></td>
					<td><c:out value="${row.name}" /></td>
					<td><a href="<%=request.getContextPath()%>/page/category-process.jsp?strId=${row.id}">Edit</a></td>
					<td> <a href="<%=request.getContextPath()%>/CategoryDeleteServlet?strId=${row.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<%@include file="../components/footerbar.jsp"%>
</body>
</html>