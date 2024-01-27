<%@page import="model.User"%>
<%@page import="dao.UserDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%
int id = Integer.parseInt(request.getParameter("strId"));
User profileUser = UserDao.getOneUser(id);
%>
<!DOCTYPE html>
<html>
<head>
<title>user</title>
<%@include file="../components/common_css_js.jsp"%>
</head>
<body>
	<%@include file="../components/navbar.jsp"%>

	<div class="container">
		<div class="row mt-5">
			<div class="col-md-6 offset-md-3">
				<div class="card">
					<div class="card-body px-5">
						<h3 class="text-center my-3">Account Details</h3>
						<%@include file="../components/message.jsp"%>
						<form action="<%=request.getContextPath()%>/ProfileServlet" method="post">
							<div class="form-group">
								<label for="name">Name</label> <a
									class="btn btn-light btn-link float-right" onclick="editName()">Edit</a><input
									type="text" name="username" class="form-control" id="username"
									value="<%=profileUser.getName()%>" readonly="readonly">
							</div>
							<div class="form-group">
								<label for="email">Email</label> <a
									class="btn btn-light btn-link float-right"
									onclick="editEmail()">Edit</a><input type="text"
									name="useremail" class="form-control" id="useremail"
									value="<%=profileUser.getEmail()%>" readonly="readonly">
							</div>
							<div class="form-group">
								<label for="password">Password</label> <a
									class="btn btn-light btn-link float-right"
									onclick="editPassword()">Edit</a><input type="password"
									name="userpassword" class="form-control" id="userpassword"
									value="<%=profileUser.getPassword()%>" readonly="readonly">


							</div>


							<div class="container-fluid text-center">
								<button type="submit" class="btn btn-outline-primary">Submit</button>
								<a href="<%=request.getContextPath()%>" class="btn btn-outline-secondary">Close</a>
							</div>
						</form>


					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		function editName() {
			document.getElementById("username").readOnly = false;
			document.getElementById("useremail").readOnly = true;
			document.getElementById("userpassword").readOnly = true;
		}
		function editEmail() {
			document.getElementById("useremail").readOnly = false;
			document.getElementById("username").readOnly = true;
			document.getElementById("userpassword").readOnly = true;
		}
		function editPassword() {
			document.getElementById("userpassword").readOnly = false;
			document.getElementById("username").readOnly = true;
			document.getElementById("useremail").readOnly = true;
		}
	</script>


	<%@include file="../components/footerbar.jsp"%>
</body>
</html>