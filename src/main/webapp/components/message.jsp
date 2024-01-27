<%
String message = (String) session.getAttribute("message");
if (message != null) {
%>

<div class="alert alert-warning alert-dismissible fade show"
	role="alert">
	<strong><%= message %></strong>
</div>

<%
session.removeAttribute("message");
}
%>