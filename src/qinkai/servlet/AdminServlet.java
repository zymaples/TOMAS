package qinkai.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qinkai.entity.Admin;
import qinkai.entity.Answer;
import qinkai.entity.College;
import qinkai.entity.Course;
import qinkai.entity.Message;
import qinkai.entity.Student;
import qinkai.entity.Teacher;
import qinkai.service.AdminService;
import qinkai.service.StudentService;
import qinkai.service.TeacherService;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method.equals("login")) {
			login(request, response);
		} else if (method.equals("logOut")) {
			logOut(request, response);
		} else if (method.equals("showCourseList")) {
			showCourseList(request, response);
		} else if (method.equals("updateCourseUI")) {
			updateCourseUI(request, response);
		} else if (method.equals("updateCourse")) {
			updateCourse(request, response);
		} else if (method.equals("delCourse")) {
			delCourse(request, response);
		} else if (method.equals("addCourse")) {
			addCourse(request, response);
		} else if (method.equals("showTeacherList")) {
			showTeacherList(request, response);
		} else if (method.equals("updateTeacherUI")) {
			updateTeacherUI(request, response);
		} else if (method.equals("updateTeacher")) {
			updateTeacher(request, response);
		} else if (method.equals("addTeacher")) {
			addTeacher(request, response);
		} else if (method.equals("delTeacher")) {
			delTeacher(request, response);
		} else if (method.equals("showTeacherCourseList")) {
			showTeacherCourseList(request, response);
		} else if (method.equals("showCollegeList")) {
			showCollegeList(request, response);
		} else if (method.equals("updateCollege")) {
			updateCollege(request, response);
		} else if (method.equals("addCollege")) {
			addCollege(request, response);
		} else if (method.equals("delCollege")) {
			delCollege(request, response);
		} else if (method.equals("showMsgList")) {
			showMsgList(request, response);
		} else if (method.equals("updateMsgUI")) {
			updateMsgUI(request, response);
		} else if (method.equals("updateMsg")) {
			updateMsg(request, response);
		} else if (method.equals("delMsg")) {
			delMsg(request, response);
		} else if (method.equals("showAnswerList")) {
			showAnswerList(request, response);
		} else if (method.equals("updateAnswer")) {
			updateAnswer(request, response);
		} else if (method.equals("delAnswer")) {
			delAnswer(request, response);
		} else if (method.equals("checkPassword")) {
			checkPassword(request, response);
		} else if (method.equals("changePassword")) {
			changePassword(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String password= request.getParameter("password");
			AdminService adminService = new AdminService();
			Admin admin = adminService.login(username, password);
			if (admin != null) {
				request.getSession().setAttribute("admin", admin);
				response.sendRedirect("admin/home.jsp");
			} else {
				request.setAttribute("msg", "用户名或密码错误");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
	}
	
	protected void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = ((Admin)request.getSession().getAttribute("admin")).getUsername();
			String password= request.getParameter("newPassword");
			AdminService adminService = new AdminService();
			boolean isSuccess = adminService.changePassword(username, password);
			if (isSuccess) {
				request.setAttribute("msg", "修改成功！");
				request.getRequestDispatcher("admin/changePassword.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void checkPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String username = ((Admin)request.getSession().getAttribute("admin")).getUsername();
			String password = request.getParameter("password");
			AdminService adminService = new AdminService();
			Admin admin = adminService.login(username, password);
			if (admin == null) {
				response.getWriter().println(0);
			} else {
				response.getWriter().println(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void showCourseList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AdminService adminService = new AdminService();
			String collegeName = request.getParameter("collegeName");
			String teacherName = request.getParameter("teacherName");
			List<Course> list = adminService.showCourseList(collegeName, teacherName);
			request.setAttribute("list", list);
			request.getRequestDispatcher("admin/courseList.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void updateCourseUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int cid = Integer.parseInt(request.getParameter("cid"));
			AdminService adminService = new AdminService();
			Course course = adminService.findCourse(cid);
			request.setAttribute("course", course);
			request.getRequestDispatcher("admin/updateCourse.jsp").forward(request, response);;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void updateCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int cid = Integer.parseInt(request.getParameter("cid"));
			String cname = request.getParameter("cname");
			String tuname = request.getParameter("tuname");
			int coid = Integer.parseInt(request.getParameter("coid"));
			String introduction = request.getParameter("introduction");
			AdminService adminService = new AdminService();
			adminService.updateCourse(cid, cname, tuname, coid, introduction);
			showCourseList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void delCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int cid = Integer.parseInt(request.getParameter("cid"));
			AdminService adminService = new AdminService();
			adminService.delCourse(cid);
			showCourseList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void addCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int cid = Integer.parseInt(request.getParameter("cid"));
			String cname = request.getParameter("cname");
			String tuname = request.getParameter("tuname");
			int coid = Integer.parseInt(request.getParameter("coid"));
			String introduction = request.getParameter("introduction");
			AdminService adminService = new AdminService();
			adminService.addCourse(cid, cname, introduction, tuname, coid);
			showCourseList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void showTeacherList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AdminService adminService = new AdminService();
			List<Teacher> list = adminService.showTeacherList();
			request.setAttribute("list", list);
			request.getRequestDispatcher("admin/teacherList.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void updateTeacherUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			AdminService adminService = new AdminService();
			Teacher teacher = adminService.findTeacher(username);
			request.setAttribute("teacher", teacher);
			request.getRequestDispatcher("admin/updateTeacher.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void updateTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String realname = request.getParameter("realname");
			String position = request.getParameter("position");
			String introduction = request.getParameter("introduction");
			int coid = Integer.parseInt(request.getParameter("coid"));
			AdminService adminService = new AdminService();
			adminService.updateTeacher(username, realname, position, introduction, coid);
			showTeacherList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void addTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String realname = request.getParameter("realname");
			String position = request.getParameter("position");
			String introduction = request.getParameter("introduction");
			int coid = Integer.parseInt(request.getParameter("coid"));
			AdminService adminService = new AdminService();
			adminService.addTeacher(username, realname, position, introduction, coid);
			showTeacherList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void delTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			AdminService adminService = new AdminService();
			adminService.delTeacher(username);
			showTeacherList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void showTeacherCourseList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			AdminService adminService = new AdminService();
			List<Course> list = adminService.showTeacherCourseList(username);
			request.setAttribute("list", list);
			request.getRequestDispatcher("admin/courseList.jsp").forward(request, response);;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void showCollegeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AdminService adminService = new AdminService();
			List<College> list = adminService.showCollegeList();
			request.setAttribute("list", list);
			request.getRequestDispatcher("admin/collegeList.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void updateCollege(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int coid = Integer.parseInt(request.getParameter("coid"));
			String coname = request.getParameter("coname");
			AdminService adminService = new AdminService();
			adminService.updateCollege(coid, coname);
			showCollegeList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void addCollege(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int coid = Integer.parseInt(request.getParameter("coid"));
			String coname = request.getParameter("coname");
			AdminService adminService = new AdminService();
			adminService.addCollege(coid, coname);
			showCollegeList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void delCollege(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int coid = Integer.parseInt(request.getParameter("coid"));
			AdminService adminService = new AdminService();
			adminService.delCollege(coid);
			showCollegeList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void showMsgList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String courseName = request.getParameter("courseName");
			String teacherName = request.getParameter("teacherName");
			String keywords = request.getParameter("keywords");
			AdminService adminService = new AdminService();
			List<Message> list = adminService.showMsgList(courseName, teacherName, keywords);
			request.setAttribute("list", list);
			request.getRequestDispatcher("admin/msgList.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void updateMsgUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int mid = Integer.parseInt(request.getParameter("mid"));
			StudentService studentService = new StudentService();
			Message message = studentService.findMessageById(mid);
			request.setAttribute("message", message);
			request.getRequestDispatcher("admin/updateMsg.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void updateMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int mid = Integer.parseInt(request.getParameter("mid"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			AdminService adminService = new AdminService();
			boolean isSuccess = adminService.updateMsg(mid, title, content);
			if (isSuccess) {
				showMsgList(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void delMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int mid = Integer.parseInt(request.getParameter("mid"));
			AdminService adminService = new AdminService();
			boolean isSuccess = adminService.delMsg(mid);
			if (isSuccess) {
				showMsgList(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void showAnswerList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AdminService adminService = new AdminService();
			List<Answer> list = adminService.showAnswerList();
			request.setAttribute("list", list);
			request.getRequestDispatcher("admin/answerList.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void updateAnswer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int aid = Integer.parseInt(request.getParameter("aid"));
			String content = request.getParameter("content");
			AdminService adminService = new AdminService();
			adminService.updateAnswer(aid, content);
			showAnswerList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void delAnswer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int aid = Integer.parseInt(request.getParameter("aid"));
			AdminService adminService = new AdminService();
			adminService.delAnswer(aid);
			showAnswerList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
