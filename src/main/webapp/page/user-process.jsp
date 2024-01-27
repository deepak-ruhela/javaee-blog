<%@page import="helper.PasswordAuth"%>
<%@page import="model.User"%>
<%@page import="dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>user</title>
<%@include file="../components/common_css_js.jsp"%>
</head>
<body>
	<%@include file="../components/navbar.jsp"%>

	<div class="row mt-5">
		<div class="col-md-6 offset-md-3">
			<div class="card">
				<div class="card-body px-5">
					<h3 class="text-center my-3">User Details</h3>
					<%
					int userId = 0;
					User user = null;
					if (request.getParameter("strId") != null) {
					    userId = Integer.parseInt(request.getParameter("strId"));
					    user = UserDao.getOneUser(userId);
					}
					%>
					<form action="<%=request.getContextPath()%>/UserServlet" method="post">

						<div class="form-group">
							<label for="userid">User Id </label> <input type="text"
								readonly="readonly" name="userid" class="form-control"
								id="userid" value="<%=user.getId()%>">
						</div>

						<div class="form-group">
							<label for="username">User Name </label> <input type="text"
								name="username" class="form-control" id="name"
								value=<%if (user.getName() != null) {
    out.println(user.getName());
}%>>
						</div>

						<div class="form-group">
							<label for="email">User Email </label> <input type="email"
								name="useremail" class="form-control" id="email"
								value=<%if (user.getEmail() != null) {
    out.println(user.getEmail());
}%>>
						</div>

						<div class="form-group">
							<label for="password">Password </label> <input type="password"
								name="userpassword" class="form-control" id="password"
								value="<%if (user.getPassword() != null) {
    out.println(user.getPassword());
}%>">
						</div>

						<div class="container">
							<button type="submit" class="btn btn-outline-success">Submit</button>
							<a class="btn btn-outline-secondary" href="users.jsp">Back</a>
						</div>


					</form>

				</div>
			</div>


		</div>

	</div>

	<%@include file="../components/footerbar.jsp"%>
</body>
</html>