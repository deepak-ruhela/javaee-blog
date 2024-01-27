package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import db.Database;
import model.PostCategory;

public class PostCategoryDao {

    private static Connection con = Database.getConnection();

    public static void addPostCategory(PostCategory postcategory) {
	PreparedStatement pst;
	String query = "INSERT INTO POST_CATEGORY(ID, POSTID, CATEGORYID) VALUES (?,?,?)";
	postcategory.setId(getMaxId());
	try {
	    pst = con.prepareStatement(query);
	    pst.setInt(1, postcategory.getId());
	    pst.setInt(2, postcategory.getPostId());
	    pst.setInt(3, postcategory.getCategoryId());
	    pst.executeUpdate();
	    pst.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public static void updatePostCategory(PostCategory postcategory) {
	PreparedStatement pst;
	String query = "UPDATE POST_CATEGORY SET CATEGORYID = ? WHERE POSTID = ?";
	try {
	    pst = con.prepareStatement(query);
	    pst.setInt(1, postcategory.getCategoryId());
	    pst.setInt(2, postcategory.getPostId());
	    pst.executeUpdate();
	    pst.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public static int getCategoryIdByPostId(int id) {
	int categoryId = 0;
	String query = "SELECT CATEGORYID FROM POST_CATEGORY WHERE POSTID =" + id;
	Statement st;
	try {
	    st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    if (rs.next()) {
		categoryId = rs.getInt("CATEGORYID");
	    }
	    rs.close();
	    st.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return categoryId;
    }

    public static void deletePostCategoryByPostId(int id) {
	PreparedStatement pst = null;

	String query = "DELETE FROM POST_CATEGORY WHERE POSTID=?";
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
	String query = "SELECT MAX(ID) FROM POST_CATEGORY";
	try {
	    pst = con.prepareStatement(query);
	    ResultSet rs = pst.executeQuery();
	    if (rs.next()) {
		Id = rs.getInt(1);

	    }
	    rs.close();
	    pst.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return Id + 1;
    }
}
