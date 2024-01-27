package servlet;

import java.io.IOException;

import dao.ArticleDao;
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
import jakarta.servlet.http.Part;
import model.Article;
import model.File;
import model.PostCategory;
import model.PostTag;
import model.User;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleServlet() {
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

//	request.setAttribute("name", "Deepak Ruhela");

	int id = 0;
	if (request.getParameter("id") != null) {
	    id = Integer.parseInt(request.getParameter("id"));
	}

	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String[] tags = request.getParameterValues("tags");
	int categoryId = Integer.parseInt(request.getParameter("tagsSelector"));
	Part filePart = request.getPart("imageInput");

	// for adding article
	if (id == 0 || request.getParameter("id") == null) {

	    System.out.println("Running if ++++++++++++++++++++++++");
	    // add article
	    Article article = new Article();
	    article.setTitle(title);
	    article.setContent(content);
	    HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("user");
	    article.setAuthorId(user.getId());

	    ArticleDao.createArticle(article);
	    System.out.println("article.getId() " + article.getId());
	    id = article.getId();
	    // add category
	    PostCategory postCategory = new PostCategory();
	    postCategory.setCategoryId(categoryId);
	    postCategory.setPostId(id);
	    PostCategoryDao.addPostCategory(postCategory);

	    // add tags
	    for (String str : tags) {
		PostTag postTag = new PostTag();
		postTag.setTag(str);
		postTag.setPostId(id);
		PostTagDao.createPostTag(postTag);
	    }
	    // saving file and name
	    if (filePart.getSubmittedFileName() != "") {
		String fileName = FileDao.saveImage(filePart);
		File file = new File();
		file.setPostId(id);
		file.setName(fileName);
		file.setType("IMAGE");
		FileDao.createFile(file);
	    }
	}

	else {
	    System.out.println("Running else ++++++++++++++++++++++++");
	    // update article
	    Article article = ArticleDao.getArticle(id);
	    article.setTitle(title);
	    article.setContent(content);

	    ArticleDao.updateArticle(article);

	    // update category
	    PostCategory postCategory = new PostCategory();
	    postCategory.setCategoryId(categoryId);
	    postCategory.setPostId(id);
	    PostCategoryDao.updatePostCategory(postCategory);

	    // update tags
	    PostTagDao.deletePostTagByPostId(id);
	    for (String str : tags) {
		PostTag postTag = new PostTag();
		postTag.setTag(str);
		postTag.setPostId(id);
		PostTagDao.createPostTag(postTag);

	    }
	    // saving file and name
	    if (filePart.getSubmittedFileName() != "") {
		String fileName = FileDao.saveImage(filePart);

		if (FileDao.getFileByPostId(id) == null) {
		    System.out.println("Image Running for add ++++++++++++++++++++++++");
		    File file = new File();
		    file.setPostId(id);
		    file.setName(fileName);
		    file.setType("IMAGE");
		    FileDao.createFile(file);
		} else {
		    System.out.println("Image Running update ++++++++++++++++++++++++");
		    File file = FileDao.getFileByPostId(id);
		    file.setName(fileName);
		    file.setType("IMAGE");
		    FileDao.updateFile(file);
		}

	    }

	}
	// System.out.println(id + System.lineSeparator() + title +
	// System.lineSeparator() + content);
	response.sendRedirect(request.getContextPath() + "/page/posts.jsp");

	// request.getRequestDispatcher("/page/posts.jsp").forward(request, response);

    }

}
