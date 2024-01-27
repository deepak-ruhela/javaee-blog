package helper;


import java.util.List;

import dao.CategoryDao;
import dao.FileDao;
import dao.MasterDao;
import dao.PostCategoryDao;
import model.Article;
import model.Category;
import model.File;

public class Main {

    public static void main(String[] args) {

	List<Article> list = MasterDao.getAllUnApproveArticles();
	File file = FileDao.getFileByPostId(4);
//	file.setName("test.jpg");
//	file.setType("IMAGE");
//	FileDao.updateFile(file);
	System.out.println(list);

    }
}
