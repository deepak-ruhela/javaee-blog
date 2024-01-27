package auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.UserDao;

import java.io.IOException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import db.Database;

/**
 * Servlet implementation class LoginServlet
 */

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Connection con = Database.getConnection();
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
	super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @see Servlet#init(ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {
    }

    /**
     * @see Servlet#destroy()
     */
    public void destroy() {
	// TODO Auto-generated method stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	HttpSession session = request.getSession();

	String email = request.getParameter("email");
	String password = request.getParameter("password");

	try {
	    boolean userExist = UserDao.checkUserByEmailAndPassword(email, password);

	    if (userExist == true) {

		User user = UserDao.getUserByEmail(email);

		session.setAttribute("user", user);
		if (user.getRole().equalsIgnoreCase("admin")) {
		    response.sendRedirect(request.getContextPath() + "/admin/admin.jsp");
		    System.out.println("Admin login");
		} else {
		    response.sendRedirect(request.getContextPath());
		    System.out.println("User login");
		}

	    }

	    else {

		session.setAttribute("message", "The account with these credentials does not exist.");
		// response.sendRedirect(request.getContextPath() + "/login");
		request.getRequestDispatcher("/login").forward(request, response);
	    }

	} catch (Exception e) {
	    e.getMessage();
	}

    }
}
