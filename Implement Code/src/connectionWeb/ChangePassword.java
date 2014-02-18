package connectionWeb;


import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import util.MD5Util;
import util.MySQLConnection;


import java.security.NoSuchAlgorithmException;
import java.sql.*;


@WebServlet("/changePassword")
public class ChangePassword extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String new1 = request.getParameter("new1");
		String new2 = request.getParameter("new2");

		if (new1.equals(new2)) {
			String oldUser = request.getParameter("oldUser");
			String oldStaff = request.getParameter("oldStaff");
			
			if (oldUser != null) {

				Connection con = MySQLConnection.connection();
				
				HttpSession s = request.getSession();
				String user = (String) s.getAttribute("User");
				
				String ID="";
				String email="";
				
				try {
					Statement state = con.createStatement();
					

					String query = "SELECT * FROM general_user WHERE name='"+user+"';";
					ResultSet rs = state.executeQuery(query);
					
					while (rs.next()) {
						ID = (String) rs.getString("ID");
						email = (String) rs.getString("email");
					}
					
						state.close();
						con.close();
				} catch (SQLException e) {
					session.setAttribute("ChangePassword", "No");
				}

				
				con = MySQLConnection.connection();
						
				try {
					Statement state = con.createStatement();
					

					String query = "DELETE FROM general_user WHERE name='"+user+"';";
					state.executeUpdate(query);
					
						state.close();
						con.close();
				} catch (SQLException e) {
					session.setAttribute("ChangePassword", "No");
				}
				
				
				try {
					MD5Util.addNewUser(user,Integer.parseInt(ID),email,new1);
					session.setAttribute("ChangePassword", "Yes");
				} catch (NoSuchAlgorithmException e) {
					session.setAttribute("ChangePassword", "No");
				}
			} else {

				Connection con = MySQLConnection.connection();
				
				HttpSession s = request.getSession();
				String user = (String) s.getAttribute("Staff");
				
				String ID="";
				String email="";
				String position="";
				
				try {
					Statement state = con.createStatement();
					

					String query = "SELECT * FROM staff WHERE name='"+user+"';";
					ResultSet rs = state.executeQuery(query);
					
					while (rs.next()) {
						ID = (String) rs.getString("ID");
						email = (String) rs.getString("email");
						position = (String) rs.getString("position");
					}
					
						state.close();
						con.close();
				} catch (SQLException e) {
					session.setAttribute("ChangePassword", "No");
				}

				con = MySQLConnection.connection();
				
				try {
					Statement state = con.createStatement();
					

					String query = "DELETE FROM staff WHERE name='"+user+"';";
					state.executeUpdate(query);
					
						state.close();
						con.close();
				} catch (SQLException e) {
					session.setAttribute("ChangePassword", "No");
				}
				
				try {
					MD5Util.addNewStaff(user,Integer.parseInt(ID),email,new1,position);
					session.setAttribute("ChangePassword", "Yes");
				} catch (NoSuchAlgorithmException e) {
					session.setAttribute("ChangePassword", "No");
				}
			}
		} else {
			session.setAttribute("ChangePassword", "No");
		}
		response.sendRedirect("/library/personal.jsp");
	}
}
