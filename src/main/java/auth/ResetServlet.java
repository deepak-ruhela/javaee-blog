package auth;

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
 * Servlet implementation class ResetServlet
 */
@WebServlet("/ResetServlet")
public class ResetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetServlet() {
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

	try {

	    String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
	    boolean valid = VerifyCaptcha.verify(gRecaptchaResponse);
	    HttpSession httpSession = request.getSession();

	    String useremail = request.getParameter("email");
	    String password = request.getParameter("password");
	    String repassword = request.getParameter("repassword");

	    if (!password.equals(repassword)) {
		httpSession.setAttribute("message",
			"Your passwords do not match with each other, so please type the same passwords below");
		response.sendRedirect(request.getContextPath() + "/reset");

	    }

	    if (password.equals(repassword)) {

		User user = UserDao.getUserByEmail(useremail);

		if (user != null) {
		    user.setPassword(password);
		    String otp = Email.sendOTP(user.getName(), useremail);
		    System.out.println(otp + " +++++++++++++++++++++++otp");
		    httpSession.setAttribute("otp", otp);
		    httpSession.setAttribute("auth", "reset");
		    response.sendRedirect(request.getContextPath() + "/auth/otp.jsp");

		}

		else {
		    httpSession.setAttribute("message",
			    "Your are not having any account with the given email, so please use the different email or register with this email...");
		    response.sendRedirect(request.getContextPath() + "/reset");

		}

	    }

	} catch (Exception e) {
	    e.getMessage();

	}
    }
}
