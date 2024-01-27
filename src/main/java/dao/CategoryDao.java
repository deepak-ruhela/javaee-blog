package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import db.Database;
import model.Category;

public class CategoryDao {
    private static Connection con = Database.getConnection();

    public static void addCategory(Category category) {
	PreparedStatement pst;
	String query = "INSERT INTO CATEGORY(ID, NAME) VALUES (?,?)";
	category.setId(getCategoryMaxId());

	try {
	    pst = con.prepareStatement(query);
	    pst.setInt(1, category.getId());
	    pst.setString(2, category.getName());
	    pst.executeUpdate();
	    pst.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public static void deleteCategory(int id) {

	PreparedStatement pst1 = null;

	String query1 = "DELETE FROM CATEGORY WHERE ID=?";
	try {

	    pst1 = con.prepareStatement(query1);
	    pst1.setInt(1, id);
	    pst1.executeUpdate();
	    pst1.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public static Category getOneCategory(int id) {
	Category category = new Category();
	String query = "SELECT ID, NAME FROM CATEGORY WHERE ID =" + id;
	Statement st;
	try {
	    st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    while (rs.next()) {
		category.setId(rs.getInt("ID"));
		category.setName(rs.getString("NAME"));
	    }
	    rs.close();
	    st.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return category;
    }

    public static void updateCategory(Category category) {

	String sql = "UPDATE CATEGORY SET NAME = ? WHERE ID = ?";
	try {
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, category.getName());
	    ps.setInt(2, category.getId());
	    ps.executeUpdate();
	    ps.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public static List<Category> getAllCategories() {
	LinkedList<Category> lst = new LinkedList<>();
	String query = "SELECT ID, NAME FROM CATEGORY";
	Statement st;

	try {
	    st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);

	    while (rs.next()) {
		Category category = new Category(rs.getInt("ID"), rs.getString("NAME"));

		lst.add(category);
	    }
	    rs.close();
	    st.close();

	} catch (Exception e) {

	    e.printStackTrace();
	}
	return lst;

    }

    public static int getCategoryMaxId() {
	int Id = 0;
	PreparedStatement pst = null;
	String query = "SELECT MAX(ID) FROM CATEGORY";
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
