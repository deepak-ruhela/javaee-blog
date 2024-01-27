package servlet;

import java.io.IOException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import dao.UserDao;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
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
	try {

	    String userName = request.getParameter("username");
	    String userEmail = request.getParameter("useremail");
	    String userPassword = request.getParameter("userpassword");

	    if (UserDao.getUserByEmail(userEmail) == null) {
		User user = new User();
		user.setName(userName);
		user.setEmail(userEmail);
		user.setPassword(userPassword);
		UserDao.createUser(user);
	    }

	    else {
		User user = UserDao.getUserByEmail(userEmail);
		user.setName(userName);
		user.setEmail(userEmail);
		user.setPassword(userPassword);
		UserDao.updateUser(user);
	    }

	    response.sendRedirect(request.getContextPath() + "/admin/users.jsp");

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}