<%@page import="db.Database"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>

<%
if (request.getParameter("key") != null) //get "key" variable from jquery & ajax  part this line "data:'key='+search" and check not null 
{
  //  System.out.println("ajax called");
    String key = request.getParameter("key"); //get "key" variable store in created new "key" variable
    String wild = "%" + key + "%"; //remove "%" for use preparedstatement in query name like, and "key" variable store in "wild" variable for further use
    //   String wild = key + "%";

    try {

	Connection con = Database.getConnection();
	PreparedStatement pstmt = null; //create statement

	pstmt = con.prepareStatement("SELECT TITLE FROM ARTICLE WHERE TITLE LIKE ?"); //sql select query
	pstmt.setString(1, wild); //above created "wild" variable set in this
	ResultSet rs = pstmt.executeQuery(); //execute query and set in ResultSet object "rs".

	while (rs.next()) {
%>
<li class="list-group-item list-group-item-action"><%=rs.getString("TITLE")%></li>
<%
}
con.close(); //close connection
} catch (Exception e) {
e.printStackTrace();
}
}
%>