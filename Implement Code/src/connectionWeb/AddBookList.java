package connectionWeb;


import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import util.MySQLConnection;


import java.sql.*;


@WebServlet("/addBookList")
public class AddBookList extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		String barcode = request.getParameter("barcode");

		HttpSession s = request.getSession();
		String user = (String) s.getAttribute("User");
		
		Connection con = MySQLConnection.connection();
		
		String ID=null;
		try {
			Statement state = con.createStatement();
			

			String query = "SELECT ID FROM general_user WHERE name='"+user+"';";
			ResultSet rs = state.executeQuery(query);
			
			while (rs.next()) {
				ID = (String) rs.getString("ID");
			}
			
				state.close();
				con.close();
		} catch (SQLException e) {
			
		}
		
		con = MySQLConnection.connection();
		
		try {
			Statement state = con.createStatement();
				String query = "INSERT INTO book_list VALUES('" + user + "','" + ID+"','"
						+ barcode +"')";
				
				state.executeUpdate(query);
				state.close();
				con.close();
		} catch (SQLException e) {
			
		}
		response.sendRedirect("/library/search.jsp");
	}
}
