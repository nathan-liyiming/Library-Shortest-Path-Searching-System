package connectionWeb;


import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import util.MySQLConnection;


import java.sql.*;


@WebServlet("/modifyStaff")
public class ModifyStaff extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String name = request.getParameter("name");
		String ID = request.getParameter("ID");
		String newPosition = request.getParameter("newPosition");


				Connection con = MySQLConnection.connection();
						
				try {
					Statement state = con.createStatement();
					

					String query = "UPDATE staff SET position='"+newPosition+"' WHERE name='"+name+"' and ID='"+ID+"';";
					state.executeUpdate(query);
					
						state.close();
						con.close();
						session.setAttribute("ModifyStaff", "Yes");
				} catch (SQLException e) {
					session.setAttribute("ModifyStaff", "No");
				}
	
		response.sendRedirect("/library/people.jsp");
	}
}
