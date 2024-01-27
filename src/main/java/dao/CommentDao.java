package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import db.Database;
import model.Comment;

public class CommentDao {

    static Connection con = Database.getConnection();

    public static void createComment(Comment comment) {
	PreparedStatement pst;
	String query = "INSERT INTO COMMENT(ID, POSTID, USERID, CONTENT, TIME) VALUES (?,?,?,?,?)";

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date date = new Date();
	String time = df.format(date);
	comment.setId(getMaxId());
	comment.setTime(time);
	try {
	    pst = con.prepareStatement(query);
	    pst.setInt(1, comment.getId());
	    pst.setInt(2, comment.getPostId());
	    pst.setInt(3, comment.getUserId());
	    pst.setString(4, comment.getContent());
	    pst.setString(5, comment.getTime());
	    pst.executeUpdate();
	    pst.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public static List<Comment> getCommentsByPostId(int id) {
	LinkedList<Comment> lst = new LinkedList<>();
	String query = "SELECT * FROM COMMENT WHERE POSTID = " + id;
	Statement st;

	try {
	    st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);

	    while (rs.next()) {
		Comment comment = new Comment(rs.getInt("ID"), rs.getInt("POSTID"), rs.getInt("USERID"),
			rs.getString("CONTENT"), rs.getString("TIME"));
		lst.add(comment);
	    }
	    rs.close();
	    st.close();

	} catch (Exception e) {

	    e.printStackTrace();
	}
	return lst;

    }

    public static void deleteComment(int id) {
	PreparedStatement pst = null;

	String query = "DELETE FROM COMMENT WHERE POSTID=?";
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
	String query = "SELECT MAX(ID) FROM COMMENT";
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
