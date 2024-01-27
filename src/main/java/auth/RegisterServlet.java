package auth;

import java.io.IOException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import dao.UserDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
	super();
	// TODO Auto-generated constructor stub
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

//	    if (valid) {
	    String username = request.getParameter("name");
	    String useremail = request.getParameter("email");
	    String userpassword = request.getParameter("password");

	    if (UserDao.getUserByEmail(useremail) == null) {

		String otp = Email.sendOTP(username, useremail);
		System.out.println(otp + " +++++++++++++++++++++++otp");
		User user = new User();
		user.setName(username);
		user.setName(username);
		user.setEmail(useremail);
		user.setRole("USER");
		httpSession.setAttribute("otp", otp);
		httpSession.setAttribute("user", user);
		httpSession.setAttribute("auth", "set");
		response.sendRedirect(request.getContextPath() + "/auth/otp.jsp");

	    } else {

		httpSession.setAttribute("message", "Your account with this Email exist. Please Login!");
		response.sendRedirect(request.getContextPath() + "/login");

	    }
//	    }
//	    if (!valid) {
//		httpSession.setAttribute("message", "Captcha invalid!");
//		response.sendRedirect(request.getContextPath() + "/register");
//
//	    }

	} catch (Exception e) {
	    e.getMessage();

	}

    }

}
