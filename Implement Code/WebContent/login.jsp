<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Log In</title>
</head>
<body>
	<form action="checkLogin" method="get">
		<div id="divLoginWindow">
			<table style="width: 100%;" border="0" cellpadding="2"
				cellspacing="0">
				<tr
					style="background-color: #e0f3d9; border-bottom: #bfe5b3 solid 2px">
					<td style="height: 38px; width: 100px;">&nbsp;<b>LOG IN</b></td>
					<td></td>
				</tr>
				<tr>
					<td colspan="2" style="height: 5px;"></td>
				</tr>
				<tr>
					<td align="right">&nbsp;Username:&nbsp;&nbsp;</td>
					<td><input type="text" name="Username" /></td>
				</tr>
				<tr>
					<td align="right">&nbsp;Password:&nbsp;&nbsp;</td>
					<td><input type="password" name="Password" /></td>
				</tr>
				<%
					session = request.getSession();
					if (session.getAttribute("Login") != null) {
						if (session.getAttribute("Login").equals("No")) {
				%>
				<tr>
					<td colspan=2 align="center"><font color="#FF0000">Input
							combination is incorrect!</font></td>
				</tr>
				<%
					}
					} else {
				%>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<%
						}
						session.setAttribute("Login", null);
					%>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit"
						value="Login" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
						type="reset" value="Reset" /></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>