package auth;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

import java.io.IOException;

import dao.UserDao;

/**
 * Servlet implementation class OTPServlet
 */
@WebServlet("/OTPServlet")
public class OTPServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OTPServlet() {
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
     * @see Servlet#getServletConfig()
     */
    public ServletConfig getServletConfig() {
	// TODO Auto-generated method stub
	return null;
    }

    /**
     * @see Servlet#getServletInfo()
     */
    public String getServletInfo() {
	// TODO Auto-generated method stub
	return null;
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
	// TODO Auto-generated method stub

	try {
	    HttpSession session = request.getSession();
	    String inputotp = request.getParameter("inputotp");
	    String otp = session.getAttribute("otp").toString();

	    if (!otp.equals(inputotp)) {
		session.setAttribute("message", "OTP is not Valid");
		response.sendRedirect(request.getContextPath() + "/auth/otp.jsp");
	    }

	    else if (inputotp.equals(otp)) {

		String auth = session.getAttribute("auth").toString();

		User user = (User) session.getAttribute("user");

		switch (auth) {
		case "set": {
		    UserDao.createUser(user);
		    session.setAttribute("message", "Registeration completed. Use Your Email and Password to Login");
		    break;
		}

		case "reset": {
		    UserDao.updateUser(user);
		    session.setAttribute("message", "Password changed. Use this password to login");
		    break;

		}

		case "email": {

		    String newemail = session.getAttribute("newemail").toString();
		    User user2 = UserDao.getUserByEmail(newemail);
		    if (user2 == null) {

			// UserDao.updateUser(user);
			session.setAttribute("message", "Email changed. Use this email to login");
			session.setAttribute("newemail", null);

		    } else {
			session.setAttribute("message",
				"This email already existed with another account please use another email.");
			response.sendRedirect(request.getContextPath() + "/auth.jsp");

		    }
		    break;
		}

		case "password": {
		    // UserDao.updateUser(user);
		    session.setAttribute("message", "Password changed. Use this password to login");
		    break;
		}

		default:
		    throw new IllegalArgumentException("Unexpected value: " + auth);
		}

		session.removeAttribute("user");
		session.removeAttribute("auth");
		session.removeAttribute("otp");
		response.sendRedirect(request.getContextPath() + "/login");

	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
