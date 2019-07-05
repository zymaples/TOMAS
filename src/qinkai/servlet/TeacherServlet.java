package qinkai.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qinkai.entity.Answer;
import qinkai.entity.Authority;
import qinkai.entity.Course;
import qinkai.entity.Message;
import qinkai.entity.Teacher;
import qinkai.service.TeacherService;

@WebServlet("/TeacherServlet")
public class TeacherServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method.equals("login")) {
			login(request, response);
		} else if (method.equals("changePassword")) {
			changePassword(request, response);
		} else if (method.equals("showIndex")) {
			showIndex(request, response);
		} else if (method.equals("showCourseInfo")) {
			showCourseInfo(request, response);
		} else if (method.equals("logOut")) {
			logOut(request, response);
		} else if (method.equals("answer")) {
			answer(request, response);
		} else if (method.equals("showMyAnswerList")) {
			showMyAnswerList(request, response);
		} else if (method.equals("delAnswer")) {
			delAnswer(request, response);
		} else if (method.equals("updateAnswer")) {
			updateAnswer(request, response);
		} else if (method.equals("showMsgList")) {
			showMsgList(request, response);
		} else if (method.equals("delMsg")) {
			delMsg(request, response);
		} else if (method.equals("delMsgIn")) {
			delMsgIn(request, response);
		} else if (method.equals("checkPassword")) {
			checkPassword(request, response);
		} else if (method.equals("showAuthorityList")) {
			showAuthorityList(request, response);
		} else if (method.equals("delAuthority")) {
			delAuthority(request, response);
		} else if (method.equals("addAuthority")) {
			addAuthority(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			TeacherService teacherService = new TeacherService();
			Teacher teacher = teacherService.login(username, password);
			if (teacher != null) {
				request.getSession().setAttribute("teacher", teacher);
				showIndex(request, response);
			} else {
				request.setAttribute("msg", "用户名或密码错误");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void changePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String username = ((Teacher)request.getSession().getAttribute("teacher")).getUsername();
			String newPassword = request.getParameter("newPassword");
			TeacherService teacherService = new TeacherService();
			boolean isSuccess = teacherService.changePassword(username, newPassword);
			if (isSuccess) {
				request.setAttribute("msg", "修改成功！");
				request.getRequestDispatcher("teacher/changePassword.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void checkPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String username = ((Teacher)request.getSession().getAttribute("teacher")).getUsername();
			String password = request.getParameter("password");
			TeacherService teacherService = new TeacherService();
			Teacher teacher = teacherService.login(username, password);
			if (teacher == null) {
				response.getWriter().println(0);
			} else {
				response.getWriter().println(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void showIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			TeacherService teacherService = new TeacherService();
			Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
			List<Message> list = teacherService.showUnansweredMsgList(teacher.getUsername());
			List<Course> courseList = teacherService.showMyCourseList(teacher.getUsername());
			request.setAttribute("courseList", courseList);
			request.setAttribute("list", list);
			request.setAttribute("cnt", list.size());
			
			RequestDispatcher rd = request.getRequestDispatcher("teacher/home.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void showCourseInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int cid = Integer.parseInt(request.getParameter("cid"));
			TeacherService teacherService = new TeacherService();
			Course course = teacherService.findCourse(cid);
			List<Message> list = teacherService.findMessageAndAnswer(cid);
			request.setAttribute("course", course);
			request.setAttribute("list", list);
			request.getRequestDispatcher("courseInfo.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void showMyAnswerList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			TeacherService teacherService = new TeacherService();
			Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
			List<Answer> list = teacherService.showMyAnswerList(teacher.getUsername());
			request.setAttribute("list", list);
			request.getRequestDispatcher("teacher/answerList.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void logOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect("index.jsp");
	}
	
	protected void answer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String content = request.getParameter("content");
			int mid = Integer.parseInt(request.getParameter("mid"));
			TeacherService teacherService = new TeacherService();
			boolean isSuccess = teacherService.answer(content, mid);
			if (isSuccess) {
				showCourseInfo(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void delAnswer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int aid = Integer.parseInt(request.getParameter("aid"));
			TeacherService teacherService = new TeacherService();
			teacherService.delAnswer(aid);
			showMyAnswerList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void updateAnswer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int aid = Integer.parseInt(request.getParameter("aid"));
			String content = request.getParameter("content");
			TeacherService teacherService = new TeacherService();
			teacherService.updateAnswer(aid, content);
			showMyAnswerList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void showMsgList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
			TeacherService teacherService = new TeacherService();
			List<Message> list = teacherService.showMsgList(teacher.getUsername());
			request.setAttribute("list", list);
			request.getRequestDispatcher("teacher/msgList.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void delMsg(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int mid = Integer.parseInt(request.getParameter("mid"));
			TeacherService teacherService = new TeacherService();
			teacherService.delMsg(mid);
			showMsgList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void delMsgIn(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int mid = Integer.parseInt(request.getParameter("mid"));
			TeacherService teacherService = new TeacherService();
			teacherService.delMsg(mid);
			showCourseInfo(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void showAuthorityList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int cid = Integer.parseInt(request.getParameter("cid"));
			String suname = request.getParameter("suname");
			Teacher teacher = (Teacher)(request.getSession().getAttribute("teacher"));
			TeacherService teacherService = new TeacherService();
			List<Course> courseList = teacherService.showMyCourseList(teacher.getUsername());
			List<Authority> list = teacherService.showAuthorityList(cid, suname, teacher.getUsername());
			request.setAttribute("list", list);
			request.setAttribute("courseList", courseList);
			request.getRequestDispatcher("teacher/authorityList.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void delAuthority(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int cid = Integer.parseInt(request.getParameter("cid"));
			String suname = request.getParameter("suname");
			TeacherService teacherService = new TeacherService();
			boolean isSuccess = teacherService.delAuthority(cid, suname);
			if (isSuccess) {
				showAuthorityList(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void addAuthority(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int cid = Integer.parseInt(request.getParameter("cid"));
			String suname = request.getParameter("suname");
			TeacherService teacherService = new TeacherService();
			boolean isSuccess = teacherService.addAuthority(cid, suname);
			if (isSuccess) {
				showAuthorityList(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
