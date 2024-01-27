<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Authentication</title>
<%@include file="../components/common_css_js.jsp"%>
</head>
<body>
	<%@include file="/components/navbar.jsp"%>
	<div class="container-fluid">

		<div class="row mt-5">
			<div class="col-md-6 offset-md-3">
				<div class="card">
					<div class="card-body px-5">
					<%@include file="../components/message.jsp"%>
						<!-- OTP Form -->
						<form action="<%=request.getContextPath()%>/OTPServlet" method="post">
							<div class="form-group">
								<label for="otp">Verify OTP </label> <input type="text"
									name="inputotp" class="form-control" id="inputotp"
									placeholder="Enter OTP">
							</div>

							<div class="container-fluid">
								<button type="submit" class="btn btn-outline-primary">Verify</button>
								<a href="<%=request.getContextPath( )%>" class="btn btn-outline-secondary">Close</a>
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