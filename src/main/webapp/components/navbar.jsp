<%@page import="model.User"%>
<header>
	<nav class="navbar navbar-expand-sm navbar-case bg-light">
		<!-- <a class="navbar-brand" href="#">Navbar</a> -->
		<a href="<%=request.getContextPath()%>"><img class="logo"
			src="<%=request.getContextPath()%>/images/logo.png"> </a> <a
			class="nav-link navbar-brand" href="<%=request.getContextPath()%>">JavaeEE
			Blog</a>
		<div>
			<button class="navbar-toggler btn" type="button"
				data-toggle="collapse" data-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"> <i class="fa fa-bars"></i>
					<i class="ion-navicon-round"></i>
				</span>
			</button>
		</div>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav ml-auto">

				<%
				String name = null;
				String role = null;
				int uId =0;
				if (session.getAttribute("user") != null) {

				    User user = (User) session.getAttribute("user");
				    name = user.getName();
				    role = user.getRole();
				    uId= user.getId();
				}

				if (role == null) {
				%>
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>">Home</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/login">Login</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/register">Register</a></li>


				<%
				} else {
				if (role.equals("USER")) {
				%>

				<%
				}
				if (role.equals("ADMIN")) {
				%>
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/page/users.jsp">Users</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/page/categories.jsp">Categories</a></li>
				<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/page/posts.jsp">Posts</a></li>
				<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/page/submission.jsp">Submission</a></li>
				<%
				}
				%>
				<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/page/myposts.jsp?strId=<%=uId%>">MyPosts</a></li>
				<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/page/article.jsp">Write</a></li>
				<li class="nav-item active"><a class="nav-link" href="user"><%=name%></a></li>
				
				<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/page/profile.jsp?strId=<%=uId%>">Account</a></li>
				<li class="nav-item active"><a class="nav-link"	href="<%=request.getContextPath()%>/LogoutServlet"> Logout</a></li>
				<%
				}
				%>

			</ul>
		</div>
	</nav>
</header>