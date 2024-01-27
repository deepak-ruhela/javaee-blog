<%@page import="dao.CategoryDao"%>
<%@page import="model.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>category-process</title>
<%@include file="../components/common_css_js.jsp"%>
</head>
<body>
	<%@include file="../components/navbar.jsp"%>
	<%
	Category c = new Category();
	if (Integer.parseInt(request.getParameter("strId")) != 0) {
	    int strId = Integer.parseInt(request.getParameter("strId"));
	    c = CategoryDao.getOneCategory(strId);
	}
	%>
	<div class="container-fluid">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header custom-bg text-dark">
					<h5 class="modal-title" id="exampleModalLabel">Category
						Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="<%=request.getContextPath() %>/CategoryServlet" method="post">
						<div class="form-group">
							<label for="Id">Id</label> <input type="text" name="id"
								class="form-control" id="id" readonly="readonly"
								value="<%if (c.getId() != 0) {
    out.println(c.getId());
}else{out.println(0);}%>">
						</div>
						<div class="form-group">
							<label for="Category">Category Name</label> <input type="text"
								name="name" class="form-control" id="name"
								value="<%if (c.getName() != null) {
    out.println(c.getName());
}%>">
						</div>
						<div class="container text-center">
							<button type="submit" class="btn btn-outline-success">Submit</button>
							<a href="category.jsp" class="btn btn-outline-secondary">Back</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@include file="../components/footerbar.jsp"%>
</body>
</html>