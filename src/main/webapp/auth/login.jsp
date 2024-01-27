<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<%@include file="../components/common_css_js.jsp"%>
<!--      <script>
       function onSubmit(token) {
         document.getElementById("login-form").submit();
       }
     </script> -->
</head>
<body>
	<%@include file="../components/navbar.jsp"%>
	<div class="container-fluid">
		<div class="row mt-5">
			<div class="col-md-6 offset-md-3">
				<div class="card">
					<div class="card-body px-5">
						<h3 class="text-center my-3">Login</h3>
						<%@include file="../components/message.jsp"%>
						<form id="login-form" action="LoginServlet" method="post">

							<div class="form-group">
								<label for="name">Email </label> <input type="text" name="email"
									class="form-control" id="email" placeholder="Enter Here" required="required">
							</div>

							<div class="form-group">
								<label for="password">Password </label> <input type="password"
									name="password" class="form-control" id="password"
									placeholder="Enter Here" required="required">
							</div>
								<div class="form-check">

									<label class="form-check-label mt-2" for="rememberMe">
										<input class="form-check-input" type="checkbox"
										value="lsRememberMe" id="rememberMe"> Remember Me
									</label>

								</div>
							
			
							<div class="container-fluid text-center mt-2">
								<button type="submit" class="btn btn-outline-primary">Login</button>
								<a href="<%=request.getContextPath()%>" class="btn btn-outline-secondary">Back</a>
							</div>
						</form>

					</div>
					<div class="mt-4">
						<div class="d-flex justify-content-center links">
							Don't have an account? <a href="<%=request.getContextPath()%>/register" class="ml-2">Sign
								Up</a>
						</div>
						<div class="d-flex justify-content-center links">
							<a href="<%=request.getContextPath()%>/reset">Forgot your password?</a>
						</div>

					</div>
				</div>


			</div>

		</div>
	</div>
	<script type="text/javascript">
		const rmCheck = document.getElementById("rememberMe"), emailInput = document
				.getElementById("email"), passwordInput = document
				.getElementById("password");

		if (localStorage.checkbox && localStorage.checkbox !== "") {
			rmCheck.setAttribute("checked", "checked");
			emailInput.value = localStorage.email;
			passwordInput.value = localStorage.password;
		} else {
			rmCheck.removeAttribute("checked");
			emailInput.value = "";
			passwordInput.value = "";
		}

		function lsRememberMe() {
			if (rmCheck.checked && emailInput.value !== "") {
				localStorage.email = emailInput.value;
				localStorage.password = passwordInput.value;
				localStorage.checkbox = rmCheck.value;
			} else {
				localStorage.email = "";
				localStorage.password = "";
				localStorage.checkbox = "";
			}
		}
	</script>
	<%@include file="../components/footerbar.jsp"%>
</body>
</html>
