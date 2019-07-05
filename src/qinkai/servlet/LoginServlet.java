package qinkai.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userType = request.getParameter("userType");
		if (userType.equals("student")) {
			request.getRequestDispatcher("StudentServlet").forward(request, response);
		} else if (userType.equals("teacher")) {
			request.getRequestDispatcher("TeacherServlet").forward(request, response);
		} else {
			request.getRequestDispatcher("AdminServlet").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
