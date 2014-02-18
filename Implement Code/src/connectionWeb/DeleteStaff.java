package connectionWeb;


import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import util.MySQLConnection;


import java.sql.*;


@WebServlet("/deleteStaff")
public class DeleteStaff extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String name = request.getParameter("name");
		String ID = request.getParameter("ID");


				Connection con = MySQLConnection.connection();
						
				try {
					Statement state = con.createStatement();
					

					String query = "DELETE FROM staff WHERE name='"+name+"' and ID='"+ID+"';";
					state.executeUpdate(query);
					
						state.close();
						con.close();
						session.setAttribute("DeleteStaff", "Yes");
				} catch (SQLException e) {
					session.setAttribute("DeleteStaff", "No");
				}
	
		response.sendRedirect("/library/people.jsp");
	}
}
