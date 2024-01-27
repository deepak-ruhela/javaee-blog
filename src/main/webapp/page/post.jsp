<%@page import="dao.CommentDao"%>
<%@page import="model.Comment"%>
<%@page import="dao.ArticleDao"%>
<%@page import="model.Article"%>
<%@page import="dao.UserDao"%>
<%@page import="dao.CategoryDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>post</title>
<%@include file="../components/common_css_js.jsp"%>
<link rel="stylesheet" href="css/imageHoverStyle.css">

<!-- Editor CSS JS-->
<link rel="stylesheet" href="../editormd/css/editormd.preview.css" />

<script src="../js/zepto.min.js"></script>
<script src="../editormd/editormd.js"></script>
<script src="../editormd/lib/marked.min.js"></script>
<script src="../editormd/lib/prettify.min.js"></script>
<script src="../editormd/lib/raphael.min.js"></script>
<script src="../editormd/lib/underscore.min.js"></script>
<script src="../editormd/lib/sequence-diagram.min.js"></script>
<script src="../editormd/lib/flowchart.min.js"></script>
<script src="../editormd/lib/jquery.flowchart.min.js"></script>
</head>
<body>
	<%@include file="../components/navbar.jsp"%>
	<%
	int strId = Integer.parseInt(request.getParameter("strId"));
	Article article = ArticleDao.getArticle(strId);
	request.setAttribute("article", article);
	List<Comment> listComment = CommentDao.getCommentsByPostId(strId);
	%>
	<c:set var="article" value="${article}" scope="request" />
	<%@include file="../components/message.jsp"%>
	<div class="container pt-4">
		<div class="row">
			<h1>${article.title}</h1>
		</div>
		<br>
		<div id="mdView" style="background: #eee;">
			<textarea id="article_content">${article.content}</textarea>
		</div>
	</div>
	<div class="container mt-5">
		<div class="d-flex justify-content-center row">
			<div class="col-md-8">
				<div class="d-flex flex-column comment-section">

					<!-- Comment section -->
					<div class="row">
						<div class="bg-white p-2">
							<div class="d-flex flex-row user-info">
								<img class="rounded-circle" src="../images/comment.jpg"
									width="40">
								<div class="d-flex flex-column justify-content-start ml-2">
									<span class="d-block font-weight-bold name">Marry
										Andrews</span><span class="date text-black-50">Jan 2020</span>
								</div>
							</div>
							<div class="mt-2">
								<p class="comment-text">Lorem ipsum dolor sit amet,
									consectetur adipiscing elit, sed do eiusmod tempor incididunt
									ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
									nostrud exercitation ullamco laboris nisi ut aliquip ex ea
									commodo consequat.</p>
							</div>
						</div>
						<div class="bg-white">
							<div class="d-flex flex-row fs-12">
								<div class="like p-2 cursor">
									<i class="fa fa-thumbs-o-up"></i><span class="ml-1">Like</span>
								</div>
								<div class="like p-2 cursor">
									<i class="fa fa-commenting-o"></i><span class="ml-1">Comment</span>
								</div>
								<div class="like p-2 cursor">
									<i class="fa fa-share"></i><span class="ml-1">Share</span>
								</div>
							</div>
						</div>

					</div>
					<!-- Loop start -->
					<%
					for (int i = 0; i < listComment.size(); i++) {
						Comment comment = listComment.get(i);
					%>
					<div class="row">
						<div class="bg-white p-2">
							<div class="d-flex flex-row user-info">
								<img class="rounded-circle" src="../images/comment.jpg"
									width="40">
								<div class="d-flex flex-column justify-content-start ml-2">
									<span class="d-block font-weight-bold name"><%=UserDao.getNameByUserId(comment.getUserId())%></span><span
										class="date text-black-50"><%=comment.getTime()%></span>
								</div>
							</div>
							<div class="mt-2">
								<p class="comment-text"><%=comment.getContent()%></p>
							</div>
						</div>
					</div>
					<br>
				</div>

			</div>

			<!-- Loop end -->
			<%
			}
			%>

			<div class="container">
				<form	action="<%=request.getContextPath()%>/CommentServlet?strId=<%=strId%>"
					method="post">


					<div class="bg-light p-2">
						<div class="d-flex flex-row align-items-start">
							<img class="rounded-circle" src="../images/user1.png" width="40">
							<textarea class="form-control ml-1 shadow-none textarea"
								name="comment"></textarea>
						</div>
						<div class="mt-2 text-right">
							<button class="btn btn-primary btn-sm shadow-none" type="submit">Post
								comment</button>
							<a class="btn btn-outline-primary btn-sm ml-1 shadow-none"
								href="<%=request.getContextPath()%>/page/post.jsp?strId=<%=strId%>"
								type="button">Cancel</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@include file="../components/footerbar.jsp"%>
	<script type="text/javascript">
		$(function mdToHtml() {
			//Get the content to display
			//var content = $("#article_content").text();			
			editormd.markdownToHTML("mdView", {
				htmlDecode : "style,script,iframe",
				emoji : true,
				taskList : true,
				tex : true, // Do not parse by default
				flowChart : true, // Do not parse by default
				sequenceDiagram : true, // Do not parse by default		
			});
		});
	</script>
</body>
</html>