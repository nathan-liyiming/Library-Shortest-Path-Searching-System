package connectionWeb;


import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import util.MD5Util;



@WebServlet("/addStaff")
public class AddStaff extends HttpServlet{

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();

		String name = request.getParameter("name");
		String ID = request.getParameter("ID");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String position = request.getParameter("position");

		try {
			MD5Util.addNewStaff(name,Integer.parseInt(ID),email,password,position);
			session.setAttribute("AddStaff", "Yes");
		} catch (Exception e) {
			session.setAttribute("AddStaff", "No");
		}
	
		response.sendRedirect("/library/people.jsp");
	}
}
