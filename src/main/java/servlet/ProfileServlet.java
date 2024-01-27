package servlet;

import java.io.IOException;

import dao.UserDao;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
	super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @see Servlet#init(ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {
	// TODO Auto-generated method stub
    }

    /**
     * @see Servlet#destroy()
     */
    public void destroy() {
	// TODO Auto-generated method stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// TODO Auto-generated method stub
	response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String url = request.getContextPath() + "/page/profile.jsp?strId=";
	try {

	    String userName = request.getParameter("username");
	    String userEmail = request.getParameter("useremail");
	    String userPassword = request.getParameter("userpassword");

	    HttpSession session = request.getSession();

	    User user = (User) session.getAttribute("user");

	    if (!userName.equalsIgnoreCase(user.getName())) {
		user.setName(userName);
		session.setAttribute("message", "Name changed!!");
	    } else if (!userEmail.equalsIgnoreCase(user.getEmail())) {
		session.setAttribute("message", "To change email please contact support!");
	    } else if (!userPassword.equalsIgnoreCase(user.getPassword())) {
		user.setPassword(userPassword);
		session.setAttribute("message", "Password changed!");
	    }
	    UserDao.updateUser(user);
	    request.setAttribute("strId", user.getId());
	    url += user.getId();

	} catch (Exception e) {
	    e.printStackTrace();
	}
	response.sendRedirect(url);
	// request.getRequestDispatcher("/page/profile.jsp").forward(request, response);

    }

}
