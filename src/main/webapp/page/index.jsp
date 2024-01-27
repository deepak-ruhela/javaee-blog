<%@page import="model.Master"%>
<%@page import="dao.MasterDao"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<title>Hubsblog</title>
<%@include file="../components/common_css_js.jsp"%>
<style type="text/css">
.banner-info, banner-image {
	margin: 30px 0;
}

/*Search css*/
.search-container {
	position: relative;
	z-index: 9;
}

.search-result {
	position: absolute;
	background: white;
	width: 100%;
	padding: 10px;
	/*border: 1px solid gray;*/
}
</style>
</head>
<body>
	<%@include file="../components/navbar.jsp"%>
	<div class="container-fluid">
		<div class="container">
			<div class="row">
				<div class="col banner-info">
					<!-- 	<h1 class="display-4 text-center">Hub's Blog</h1> -->
					<h1 class="text-center font-weight-normal" style="font-size: 10vw;">JavaEE
						Blog</h1>
				</div>

			</div>

			<div class="row">
				<div class="search-container container my-4">

					<form id="search-form"
						action="<%=request.getContextPath()%>/SearchServlet" method="post">
						<input type="text" id="searchName" class="form-control"
							placeholder="Search here" name="name">
						<div id="showList" class="search-result"></div>
					</form>

				</div>
			</div>
			<div class="container mt-3">

				<%
				List<Master> list = MasterDao.getAllMaster();
				request.setAttribute("list", list);
				%>
				<c:forEach var="entity" items="${list}">


					<div class="card">
						<div class="card-body">

							<div class="row">
								<div class="col-8 col">
									<h4 class="card-title">${entity.getTitle()}</h4>
									<p class="card-text">${fn:substring(entity.getContent(), 0, 17)}
									</p>

									<a
										href="<%=request.getContextPath()%>/page/post.jsp?strId=${entity.getId()}"
										class="btn btn-login btn-light">View</a>
								</div>
								<div class="col-4 col">

									<img src="/image/${entity.getImageName()}"
										class="img-fluid float-right card-img-top">

								</div>

							</div>

						</div>
					</div>
					<br>
				</c:forEach>
			</div>


		</div>
	</div>


	<script type="text/javascript">
		$(document).ready(function() {
			$('#searchName').keyup(function() {
				var search = $('#searchName').val();
				if (search !== '' && search !== null) {
					$.ajax({
						type : 'POST',
						url : '${pageContext.request.contextPath}/page/ajaxSearch.jsp',
						data : 'key=' + search,
						success : function(data) {
							$('#showList').html(data);
						}
					});
				} else {
					$('#showList').html('');
				}
			});
			$(document).on('click', 'li', function() {
				$('#searchName').val($(this).text());
				$('#showList').empty();
				if ($('#searchName').val() !== '') {
					$('#search-form').submit();
				}
			});
		});
	</script>
	<%@include file="/components/footerbar.jsp"%>
</body>
</html>