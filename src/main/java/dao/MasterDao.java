package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import db.Database;
import model.Article;
import model.File;
import model.Master;

public class MasterDao {
    private static Connection con = Database.getConnection();

    public static void main(String[] args) {
	System.out.println(getAllMaster());
    }

    public static List<Master> getAllMaster() {

	List<Master> list = new LinkedList<Master>();

	String query = "SELECT ID, TITLE, CONTENT, AUTHORID, SLUG, TIME, ACTIVE FROM ARTICLE WHERE ACTIVE = 1";
	Statement st;
	try {
	    st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    while (rs.next()) {

		File file = new File();
		if(FileDao.getFileByPostId(rs.getInt("ID"))!=null) {
		    file=    FileDao.getFileByPostId(rs.getInt("ID"));
		}
		
		Master entity = new Master(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("CONTENT"),
			rs.getInt("AUTHORID"), rs.getString("SLUG"), rs.getString("TIME"), rs.getBoolean("ACTIVE"),
			file.getName());
		list.add(entity);

	    }
	    rs.close();
	    st.close();

	} catch (Exception e) {
	    e.getMessage();
	}

	return list;
    }

    public static List<Article> getAllUnApproveArticles() {

	List<Article> list = new LinkedList<Article>();

	String query = "SELECT ID, TITLE, CONTENT, AUTHORID, SLUG, TIME, ACTIVE FROM ARTICLE WHERE ACTIVE = 0";
	Statement st;
	try {
	    st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    while (rs.next()) {

		Article entity = new Article(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("CONTENT"),
			rs.getInt("AUTHORID"), rs.getString("SLUG"), rs.getString("TIME"), rs.getBoolean("ACTIVE"));
		list.add(entity);

	    }
	    rs.close();
	    st.close();

	} catch (Exception e) {
	    e.getMessage();
	}

	return list;
    }

    public static void publishArticle(int id) {

	String sql = "UPDATE ARTICLE SET ACTIVE = 1 WHERE ID = ?";

	try {
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setInt(1, id);
	    ps.executeUpdate();
	    ps.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void unpublishArticle(int id) {

	String sql = "UPDATE ARTICLE SET ACTIVE = 0 WHERE ID = ?";

	try {
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setInt(1, id);
	    ps.executeUpdate();
	    ps.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static List<Article> getArticlesByAuthorId(int id) {

	List<Article> list = new LinkedList<Article>();
	Article entity = null;

	String query = "SELECT ID, TITLE, CONTENT, AUTHORID, SLUG, TIME, ACTIVE FROM ARTICLE WHERE AUTHORID=" + id;
	Statement st;
	try {
	    st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    while (rs.next()) {

		entity = new Article(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("CONTENT"),
			rs.getInt("AUTHORID"), rs.getString("SLUG"), rs.getString("TIME"), rs.getBoolean("ACTIVE"));
		list.add(entity);

	    }
	    rs.close();
	    st.close();

	} catch (Exception e) {
	    e.getMessage();
	}

	return list;
    }
}
