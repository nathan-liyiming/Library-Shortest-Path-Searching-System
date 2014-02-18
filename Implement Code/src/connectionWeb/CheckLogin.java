package connectionWeb;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import util.MD5Util;


import java.security.NoSuchAlgorithmException;
import java.util.*;


@WebServlet("/checkLogin")
public class CheckLogin extends HttpServlet {

	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		response.setContentType("text/html");
		String username = request.getParameter("Username");
		String password = request.getParameter("Password");

		// check ...
		try {
			if (MD5Util.validStaffPassword(username, password)){
				HttpSession session = request.getSession();
				session.setAttribute("Staff", username);
				session.setMaxInactiveInterval(60*5);
				session.setAttribute("Login", "Yes");
			}else if(MD5Util.validUserPassword(username, password)){
				HttpSession session = request.getSession();
				session.setAttribute("User", username);
				session.setMaxInactiveInterval(60*5);
				session.setAttribute("Login", "Yes");
			}else{
				HttpSession session = request.getSession();
				session.setAttribute("Login", "No");
			}
		} catch (Exception e) {
			
		}
		response.sendRedirect("/library");
	}
}
