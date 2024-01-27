<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Reset Password</title>
<%@include file="../components/common_css_js.jsp"%>
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>
	<%@include file="../components/navbar.jsp"%>
	<div class="container-fluid">
		<div class="row mt-5">
			<div class="col-md-6 offset-md-3">
				<div class="card">
					<%@include file="../components/message.jsp"%>
					<div class="card-body px-5">
						<h3 class="text-center my-3">Reset Your Password</h3>

						<form id="reset-form" action="ResetServlet" method="post">

							<div class="form-group">
								<label for="email">Email</label> <input type="email"
									name="email" class="form-control" id="email"
									placeholder="Enter Here" required="required">
							</div>

							<div class="form-group">
								<label for="userpassword">New Password </label> <input
									type="password" name="password" class="form-control"
									id="password" placeholder="Enter Here" required="required">
							</div>
							<div class="form-group">
								<label for="repassword">Confirm Password </label> <input
									type="password" name="repassword" class="form-control"
									id="repassword" placeholder="Enter Here" required="required">
							</div>
							<div class="g-recaptcha form-check mt-2"
								data-sitekey="6LcK_dMlAAAAAKY2VKe6uw5MYGi4U1vxsU5zQ3HH"></div>

							<div class="container text-center mt-2">
								<button type="submit" class="btn btn-outline-primary">Set
									New Password</button>
								<a href="login" class="btn btn-outline-secondary">Login</a>
							</div>
						</form>
					</div>
				</div>


			</div>

		</div>
	</div>
	<%@include file="../components/footerbar.jsp"%>
</body>
</html>