package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import db.Database;
import model.PostTag;

public class PostTagDao {

    static Connection con = Database.getConnection();

    public static void createPostTag(PostTag postTag) {
	PreparedStatement pst;
	String query = "INSERT INTO POST_TAG(ID, POSTID, TAG) VALUES (?,?,?)";

	postTag.setId(getMaxId());
	try {
	    pst = con.prepareStatement(query);
	    pst.setInt(1, postTag.getId());
	    pst.setInt(2, postTag.getPostId());
	    pst.setString(3, postTag.getTag());
	    pst.executeUpdate();
	    pst.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public static List<PostTag> getAllPostTag() {
	LinkedList<PostTag> lst = new LinkedList<>();
	String query = "SELECT ID, POSTID, TAG FROM POST_TAG";
	Statement st;

	try {
	    st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);

	    while (rs.next()) {
		PostTag postTag = new PostTag(rs.getInt("ID"), rs.getInt("POSTID"), rs.getString("TAG"));
		lst.add(postTag);
	    }
	    rs.close();
	    st.close();

	} catch (Exception e) {

	    e.printStackTrace();
	}
	return lst;

    }

    public static List<PostTag> getPostTagByPostId(int id) {
	PostTag postTag = null;
	LinkedList<PostTag> lst = new LinkedList<>();
	String query = "SELECT ID, POSTID, TAG FROM POST_TAG WHERE POSTID =" + id;
	Statement st;

	try {
	    st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);

	    while (rs.next()) {
		postTag = new PostTag(rs.getInt("ID"), rs.getInt("POSTID"), rs.getString("TAG"));
		lst.add(postTag);
	    }
	    rs.close();
	    st.close();

	} catch (Exception e) {

	    e.printStackTrace();
	}
	return lst;

    }

    public static void deletePostTagByPostId(int id) {
	PreparedStatement pst = null;

	String query = "DELETE FROM POST_TAG WHERE POSTID=?";
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
	String query = "SELECT MAX(ID) FROM POST_TAG";
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
}
