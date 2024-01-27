package servlet;

import java.io.IOException;

import dao.ArticleDao;
import dao.CategoryDao;
import dao.FileDao;
import dao.PostCategoryDao;
import dao.PostTagDao;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.File;

/**
 * Servlet implementation class DeleteToolServlet
 */
@WebServlet("/ArticleDeleteServlet")
public class ArticleDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleDeleteServlet() {
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

	try {
	    int id = Integer.parseInt(request.getParameter("strId"));
	    ArticleDao.deleteArticle(id);
	    PostCategoryDao.deletePostCategoryByPostId(id);
	    PostTagDao.deletePostTagByPostId(id);
	    File file = FileDao.getFileByPostId(id);
	    FileDao.deleteFile(id);
	    FileDao.deleteImage(file.getName());
	} catch (Exception e) {
	    e.printStackTrace();
	}

	// request.getRequestDispatcher("/page/posts.jsp").forward(request, response);
	response.sendRedirect(request.getContextPath() + "/page/posts.jsp");

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doGet(request, response);

    }

}
