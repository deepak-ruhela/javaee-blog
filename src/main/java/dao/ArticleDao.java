package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import db.Database;
import model.Article;

public class ArticleDao {

    private static Connection con = Database.getConnection();

    public static void createArticle(Article article) {
	PreparedStatement pst;
	String query = "INSERT INTO ARTICLE(ID, TITLE, CONTENT, AUTHORID, SLUG, TIME) VALUES (?,?,?,?,?,?)";
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date date = new Date();
	String time = df.format(date);
	String slug = article.getTitle();
	slug = slug.replace(' ', '-');
	article.setId(getMaxId());
	article.setSlug(slug);
	article.setTime(time);

	try {
	    pst = con.prepareStatement(query);
	    pst.setInt(1, article.getId());
	    pst.setString(2, article.getTitle());
	    pst.setString(3, article.getContent());
	    pst.setInt(4, article.getAuthorId());
	    pst.setString(5, article.getSlug());
	    pst.setString(6, article.getTime());
	    pst.executeUpdate();
	    pst.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public static Article getArticle(int id) {
	Article article = new Article();

	String query = "SELECT ID, TITLE, CONTENT, AUTHORID, SLUG, TIME FROM ARTICLE WHERE ID =" + id;
	Statement st;
	try {
	    st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    while (rs.next()) {

//		entity = new Article(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("CONTENT"),
//			rs.getInt("AUTHORID"), rs.getString("SLUG"), rs.getString("TIME"));
		article.setId(rs.getInt("ID"));
		article.setTitle(rs.getString("TITLE"));
		article.setContent(rs.getString("CONTENT"));
		article.setAuthorId(rs.getInt("AUTHORID"));
		article.setSlug(rs.getString("SLUG"));
		article.setTime(rs.getString("TIME"));

	    }
	    rs.close();
	    st.close();

	} catch (Exception e) {
	    e.getMessage();
	}

	return article;
    }

    public static List<Article> getAllArticles() {

	List<Article> list = new LinkedList<Article>();
	Article entity = null;

	String query = "SELECT ID, TITLE, CONTENT, AUTHORID, SLUG, TIME, ACTIVE FROM ARTICLE WHERE ACTIVE=1";
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

    public static void updateArticle(Article entity) {
	String sql = "UPDATE ARTICLE SET TITLE= ?, CONTENT= ?, AUTHORID = ?, TIME = ? WHERE ID = ?";

	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	try {
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, entity.getTitle());
	    ps.setString(2, entity.getContent());
	    ps.setInt(3, entity.getAuthorId());
	    ps.setString(4, timestamp.toString());
	    ps.setInt(5, entity.getId());
	    ps.executeUpdate();
	    ps.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }

    public static void deleteArticle(int id) {
	PreparedStatement pst = null;

	String query = "DELETE FROM ARTICLE WHERE ID=?";
	try {

	    pst = con.prepareStatement(query);
	    pst.setInt(1, id);
	    pst.executeUpdate();
	    pst.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    private static int getMaxId() {
	int Id = 0;
	PreparedStatement pst = null;
	String query = "SELECT MAX(ID) FROM ARTICLE";
	try {
	    pst = con.prepareStatement(query);
	    ResultSet rs = pst.executeQuery();
	    if (rs.next()) {
		Id = rs.getInt(1);

	    }
	    rs.close();
	    pst.close();

	} catch (Exception e) {
	    e.getMessage();
	}
	return Id + 1;
    }

    public int getIdByName(String name) {
	int id = 0;
	PreparedStatement pst;
	ResultSet resultSet;
	try {
	    pst = con.prepareStatement("SELECT ID FROM ARTICLE WHERE TITLE=?");
	    pst.setString(1, name);
	    resultSet = pst.executeQuery();
	    if (resultSet.next()) {
		id = resultSet.getInt("ID");
	    }
	    pst.close();
	    resultSet.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return id;
    }

}
