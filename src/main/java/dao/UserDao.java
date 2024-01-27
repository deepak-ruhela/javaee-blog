package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import db.Database;
import model.User;

public class UserDao {

    private static Connection con = Database.getConnection();

    public static void createUser(User user) {
	PreparedStatement pst;
	String query = "INSERT INTO USER(ID, NAME, EMAIL, PASSWORD, ROLE) VALUES (?,?,?,?,?)";
	user.setId(getMaxId());
	try {
	    pst = con.prepareStatement(query);
	    pst.setInt(1, user.getId());
	    pst.setString(2, user.getName());
	    pst.setString(3, user.getEmail());
	    pst.setString(4, user.getPassword());
	    pst.setString(5, "USER");
	    pst.executeUpdate();
	    pst.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public static User getOneUser(int userid) {
	User user = new User();
	String query = "SELECT * FROM USER WHERE ID =" + userid;
	Statement st;
	try {
	    st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    while (rs.next()) {
		user.setId(rs.getInt(1));
		user.setName(rs.getString(2));
		user.setEmail(rs.getString(3));
		user.setPassword(rs.getString(4));
		user.setRole(rs.getString(4));
	    }
	    rs.close();
	    st.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return user;

    }

    public static String getNameByUserId(int userid) {
	String name = null;
	String query = "SELECT NAME FROM USER WHERE ID =" + userid;
	Statement st;
	try {
	    st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    if (rs.next()) {
		name = rs.getString("NAME");
	    }
	    rs.close();
	    st.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return name;

    }

    public static void deleteUser(int userId) {
	PreparedStatement pst;
	String query = "DELETE FROM USER WHERE ID= ?";
	try {
	    pst = con.prepareStatement(query);
	    pst.setInt(1, userId);
	    pst.executeUpdate();
	    pst.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public static boolean checkUserByEmailAndPassword(String email, String password) {
	PreparedStatement pst = null;
	ResultSet rs = null;
	boolean result = false;
	try {
	    pst = con.prepareStatement("SELECT * FROM USER WHERE EMAIL=? AND PASSWORD=?");
	    pst.setString(1, email);
	    pst.setString(2, password);
	    rs = pst.executeQuery();

	    if (rs.next()) {
		result = true;
	    }
	    rs.close();
	    pst.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return result;

    }

    public static List<User> getAllUsers() {
	List<User> lst = new LinkedList<User>();
	try {
	    PreparedStatement pst = con
		    .prepareStatement("SELECT ID, NAME, EMAIL, PASSWORD FROM USER  WHERE ROLE = \"USER\"");
	    ResultSet rs = pst.executeQuery();

	    while (rs.next()) {
		// User user = new User(UserId, UserName, UserEmail, UserPassword);
		User user = new User();
		user.setId(rs.getInt("ID"));
		user.setName(rs.getString("NAME"));
		user.setEmail(rs.getString("EMAIL"));
		user.setPassword(rs.getString("PASSWORD"));
		lst.add(user);

	    }
	    rs.close();
	    pst.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return lst;

    }

    public static void updateUser(User user) throws SQLException {

	String sql = "UPDATE USER SET NAME = ?, EMAIL = ?, PASSWORD = ? WHERE ID = ?";

	PreparedStatement ps = con.prepareStatement(sql);

	ps.setString(1, user.getName());
	ps.setString(2, user.getEmail());
	ps.setString(3, user.getPassword());
	ps.setInt(4, user.getId());

	ps.executeUpdate();
	ps.close();

    }

    public static void updateUserEmailByUserId(int userId, String userEmail) throws SQLException {

	String sql = "UPDATE USER SET EMAIL = ? WHERE ID = ?";

	PreparedStatement ps = con.prepareStatement(sql);

	ps.setString(1, userEmail);
	ps.setInt(2, userId);

	ps.executeUpdate();
	ps.close();

    }

    public static User getUserByEmail(String email) {
	User user = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	try {
	    pst = con.prepareStatement("SELECT ID, NAME, EMAIL, PASSWORD, ROLE FROM USER WHERE EMAIL=?");
	    pst.setString(1, email);
	    rs = pst.executeQuery();

	    if (rs.next()) {
		int userId = rs.getInt("ID");
		String userName = rs.getString("NAME");
		String userEmail = rs.getString("EMAIL");
		String userPassword = rs.getString("PASSWORD");
		String role = rs.getString("ROLE");
		user = new User(userId, userName, userEmail, userPassword, role);
	    }
	    rs.close();
	    pst.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return user;

    }

    public static int getUserIdByEmail(String email) {
	PreparedStatement pst = null;
	ResultSet rs = null;
	int userId = 0;
	try {
	    pst = con.prepareStatement("SELECT ID FROM USER WHERE EMAIL =?");
	    pst.setString(1, email);
	    rs = pst.executeQuery();
	    if (rs.next()) {
		userId = rs.getInt("ID");
	    }
	    rs.close();
	    pst.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return userId;

    }

    public static String getEmailByUserId(int id) {
	PreparedStatement pst = null;
	ResultSet rs = null;
	String email = null;
	try {
	    pst = con.prepareStatement("SELECT EMAIL FROM USER WHERE ID =?");
	    pst.setInt(1, id);
	    rs = pst.executeQuery();
	    if (rs.next()) {
		email = rs.getString("EMAIL");
	    }
	    rs.close();
	    pst.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return email;

    }

    public static String getUserNameByEmail(String email) {
	PreparedStatement pst = null;
	ResultSet rs = null;
	String userName = "";
	try {
	    pst = con.prepareStatement("SELECT NAME FROM USER WHERE EMAIL =?");
	    pst.setString(1, email);
	    rs = pst.executeQuery();
	    if (rs.next()) {
		userName = rs.getString("NAME");
	    }
	    rs.close();
	    pst.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return userName;

    }

    public static int updatePassword(int userId, String userPassword) throws SQLException {

	String sql = "UPDATE USER SET PASSWORD = ? WHERE ID = ?";

	PreparedStatement ps = con.prepareStatement(sql);

	ps.setString(1, userPassword);
	ps.setInt(2, userId);

	int result = ps.executeUpdate();
	ps.close();

	return result;
    }

    public int updateUsername(int userId, String userName) throws SQLException {

	String sql = "UPDATE USER SET NAME = ? WHERE ID = ?";

	PreparedStatement ps = con.prepareStatement(sql);

	ps.setString(1, userName);
	ps.setInt(2, userId);

	int result = ps.executeUpdate();
	ps.close();

	return result;
    }

    public static void updatePasswordByEmail(String userEmail, String userPassword) throws SQLException {

	String sql = "UPDATE USER SET PASSWORD = ? WHERE EMAIL = ?";

	PreparedStatement ps = con.prepareStatement(sql);

	ps.setString(1, userPassword);
	ps.setString(2, userEmail);

	ps.executeUpdate();
	ps.close();
    }

    public static int getMaxId() {
	int Id = 0;
	PreparedStatement pst = null;
	String query = "SELECT MAX(ID) FROM USER";
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
