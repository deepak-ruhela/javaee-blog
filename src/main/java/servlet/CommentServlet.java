package servlet;

import java.io.IOException;

import dao.CommentDao;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Comment;
import model.User;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
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
	response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	try {

	    HttpSession session = request.getSession();
	    if (session.getAttribute("user") != null) {

		User user = (User) session.getAttribute("user");
		String content = request.getParameter("comment");
		int postId = Integer.parseInt(request.getParameter("strId"));
		Comment comment = new Comment();
		comment.setUserId(user.getId());
		comment.setPostId(postId);
		comment.setContent(content);
		CommentDao.createComment(comment);
	    }

	    else {

		session.setAttribute("message", "Login to comment");
		System.out.println("Login to comment");
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}

	response.sendRedirect(request.getContextPath() + "/page/post.jsp?strId=" + request.getParameter("strId"));
	// request.getRequestDispatcher("/page/post.jsp?strId=" +
	// request.getParameter("strId")).forward(request,
	// response);

    }

}
