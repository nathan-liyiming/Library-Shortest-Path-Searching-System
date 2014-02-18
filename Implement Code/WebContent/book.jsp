<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="util.MySQLConnection"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>Books</title>
<link href="css/styles.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
function Auto() {
	
	<%HttpSession s = request.getSession();
			String user = (String) session.getAttribute("Staff");

			Connection con = MySQLConnection.connection();
			String position = "";
			try {
				Statement state = con.createStatement();

				String query = "SELECT * FROM staff WHERE name='" + user + "';";
				ResultSet rs = state.executeQuery(query);

				while (rs.next()) {
					position = (String) rs.getString("position");
					state.close();
					con.close();
				}
			} catch (Exception e) {

			}
			if (!position.equals("Curator")
					&& !position.equals("Dtabase Administer")
					&& !position.equals("Senior Librarian")) {
				response.sendRedirect("/library");
				
			}%>
		}
</script>
</head>
<body onload="Auto()">
	<!-- head begin-->
	<div id="head">
		<img src="images/head.gif" border="0" usemap="#Map" />
		<map name="Map" id="Map">
			<area shape="rect" coords="81,19,280,77" href="index.jsp" />
			<area shape="rect" coords="979,30,1031,53" href="index.jsp" />
			<area shape="rect" coords="1041,30,1081,53" href="#" />
			<area shape="rect" coords="1091,28,1147,54" href="logout" />
			<area shape="rect" coords="1154,25,1180,53" href="index.jsp" />
		</map>
	</div>
	<!-- head end-->
	<div class="mid">
		<ul class="mainNav">
			<li class="hover"><a href="index.jsp">Home</a></li>
			<li><a href="personal.jsp">Personal</a></li>
			<li><a href="search.jsp">Search</a></li>
			<li><a href="people.jsp">People</a></li>
			<li><a href="book.jsp">Books</a></li>
			<li><a href="maps.jsp">Maps</a></li>
			<li class="last"><a href="about.jsp">About us</a></li>
		</ul>
		<form action="addBook" method="post">
			<table align="center">
				<tr>
					<td>Title</td>
					<td><input type="text" name="title" /></td>
				</tr>
				<tr>
					<td>Author</td>
					<td><input type="text" name="author" /></td>
				</tr>
				<tr>
					<td>Publisher</td>
					<td><input type="text" name="publisher" /></td>
				</tr>
				<tr>
					<td>Barcode</td>
					<td><input type="text" name="barcode" /></td>
				</tr>
				<tr>
					<td>Status</td>
					<td><input type="text" name="status" /></td>
				</tr>
				<tr>
					<td>CallNumber</td>
					<td><input type="text" name="callNumber" /></td>
				</tr>
				<tr>
					<td>NameOfShelf</td>
					<td><input type="text" name="nameOfShelf" /></td>
				</tr>
				<tr>
					<td>LibraryName</td>
					<td><input type="text" name="libraryName" /></td>
				</tr>
				<tr>
					<td>Copy</td>
					<td><input type="text" name="copy" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Load" /></td>
					<td><input type="reset" value="Reset" /></td>
				</tr>
				<%
					if (s.getAttribute("AddBook") != null) {
						if (s.getAttribute("AddBook").equals("No")) {
				%>
				<tr>
					<td colspan="2"><font color="#FF0000">There is
							something wrong!</font></td>
				</tr>
				<%
					} else if (s.getAttribute("AddBook").equals("Yes")) {
				%>
				<tr>
					<td colspan="2">You have loaded into database successfully.</td>
				</tr>
				<%
					}
						s.setAttribute("AddBook", null);
					}
				%>

			</table>
		</form>

		<div id="foot">
			<img src="images/foot.gif" />
		</div>
	</div>
</body>
</html>