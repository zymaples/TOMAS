package qinkai.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qinkai.entity.Answer;
import qinkai.entity.Course;
import qinkai.entity.Message;
import qinkai.entity.PageModel;
import qinkai.entity.Student;
import qinkai.service.StudentService;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method.equals("login")) {
			login(request, response);
		} else if (method.equals("showIndex")) {
			showIndex(request, response);
		} else if (method.equals("register")) {
			register(request, response);
		} else if (method.equals("changePassword")) {
			changePassword(request, response);
		} else if (method.equals("showCourseList")) {
			showCourseList(request, response);
		} else if (method.equals("askUI")) {
			askUI(request, response);
		} else if (method.equals("saveMsg")) {
			saveMsg(request, response);
		} else if (method.equals("showCourseInfo")) {
			showCourseInfo(request, response);
		} else if (method.equals("logOut")) {
			logOut(request, response);
		} else if (method.equals("showMyMsgList")) {
			showMyMsgList(request, response);
		} else if (method.equals("showMsgList")) {
			showMsgList(request, response);
		} else if (method.equals("updateMsgUI")) {
			updateMsgUI(request, response); 
		} else if (method.equals("updateMsg")) {
			updateMsg(request, response);
		} else if (method.equals("delMsg")) {
			delMsg(request, response);
		} else if (method.equals("checkPassword")) {
			checkPassword(request, response);
		} else if (method.equals("checkUsername")) {
			checkUsername(request, response);
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
			StudentService studentServcie = new StudentService();
			Student student = studentServcie.login(username, password);
			if (student != null) {
				request.getSession().setAttribute("student", student);
				showIndex(request, response);
			} else {
				request.setAttribute("msg", "用户名或密码错误");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void showIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			StudentService studentService = new StudentService();
			Student student = (Student) request.getSession().getAttribute("student");
			List<Answer> list = studentService.showAnswerList(student.getUsername());
			List<Message> msgs = studentService.findAllMsgs();
			request.setAttribute("msgs", msgs);
			request.setAttribute("list", list);
			request.setAttribute("cnt", list.size());
			request.getRequestDispatcher("student/home.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String realname = request.getParameter("realname");
			StudentService studentService = new StudentService();
			boolean isSuccess = studentService.register(username, password, realname);
			if (isSuccess) {
				request.setAttribute("msg", "注册成功请登录");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void checkPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String username = ((Student)request.getSession().getAttribute("student")).getUsername();
			String password = request.getParameter("password");
			StudentService studentService = new StudentService();
			Student student = studentService.login(username, password);
			if (student == null) {
				response.getWriter().println(0);
			} else {
				response.getWriter().println(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void checkUsername(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			StudentService studentService = new StudentService();
			boolean isExist = studentService.checkUsername(username);
			if (isExist == true) {
				response.getWriter().println(0);
			} else {
				response.getWriter().println(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void changePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String username = ((Student)request.getSession().getAttribute("student")).getUsername();
			String password = request.getParameter("newPassword");
			StudentService studentService = new StudentService();
			boolean isSuccess = studentService.changePassword(username, password);
			if (isSuccess) {
				request.setAttribute("msg", "修改成功！");
				request.getRequestDispatcher("student/changePassword.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void showCourseList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentService studentService = new StudentService();
		try {
			String collegeName = request.getParameter("collegeName");
			String teacherName = request.getParameter("teacherName");
			System.out.println(collegeName + teacherName);
			List<Course> list = studentService.showCourseList(collegeName, teacherName);
			request.getSession().setAttribute("list", list);
			request.getRequestDispatcher("courseList.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void showCourseInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int cid = Integer.parseInt(request.getParameter("cid"));
			String username = ((Student)(request.getSession()).getAttribute("student")).getUsername();
			StudentService studentService = new StudentService();
			boolean isHave = studentService.checkAuthority(username, cid);
			if (isHave) {
				Course course = studentService.findCourse(cid);
				List<Message> list = studentService.findMessageAndAnswer(cid);
				request.setAttribute("course", course);
				request.setAttribute("list", list);
				request.getRequestDispatcher("courseInfo.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("student/noAuthority.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void askUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int cid = Integer.parseInt(request.getParameter("cid"));
			String username = ((Student)(request.getSession()).getAttribute("student")).getUsername();
			StudentService studentService = new StudentService();
			boolean isHave = studentService.checkAuthority(username, cid);
			if (isHave) {
				Course course = studentService.findCourse(cid);
				request.setAttribute("course", course);
				request.getRequestDispatcher("student/ask.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("student/noAuthority.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void updateMsgUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int mid = Integer.parseInt(request.getParameter("mid"));
			StudentService studentService = new StudentService();
			Message message = studentService.findMessageById(mid);
			request.setAttribute("message", message);
			request.getRequestDispatcher("student/updateMsg.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void saveMsg(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int cid = Integer.parseInt(request.getParameter("cid"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String username = ((Student) request.getSession().getAttribute("student")).getUsername();
			StudentService studentService = new StudentService();
			boolean isSuccess = studentService.saveMsg(title, content, cid, username);
			if (isSuccess) {
				showMyMsgList(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void delMsg(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int mid = Integer.parseInt(request.getParameter("mid"));
			StudentService studentService = new StudentService();
			boolean isSuccess = studentService.delMsg(mid);
			System.out.println(isSuccess);
			if (isSuccess) {
				showMyMsgList(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void updateMsg(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int mid = Integer.parseInt(request.getParameter("mid"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			StudentService studentService = new StudentService();
			boolean isSuccess = studentService.updateMsg(mid, title, content);
			if (isSuccess) {
				showMyMsgList(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void logOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect("index.jsp");
	}
	
	protected void showMyMsgList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			StudentService studentService = new StudentService();
			Student student = (Student) request.getSession().getAttribute("student");
			List<Message> list = studentService.showMyMsgs(student.getUsername());
			request.setAttribute("list", list);
			request.getRequestDispatcher("student/stuMsgList.jsp").forward(request, response);;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void showMsgList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String courseName = request.getParameter("courseName");
			String teacherName = request.getParameter("teacherName");
			String keywords = request.getParameter("keywords");
			int currentPage = Integer.parseInt(request.getParameter("currentPage"));
			StudentService studentService = new StudentService();
			PageModel pageModel = studentService.showMsgListWithPage(courseName, teacherName, keywords, currentPage);
			request.setAttribute("pageModel", pageModel);
			request.getRequestDispatcher("student/msgList.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
