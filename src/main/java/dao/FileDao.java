package dao;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import db.Database;
import helper.MyProperties;
import jakarta.servlet.http.Part;
import model.File;

public class FileDao {

    private static Connection con = Database.getConnection();

    public static void createFile(File file) {
	PreparedStatement pst;
	String query = "INSERT INTO FILE(ID, POSTID, NAME, TYPE) VALUES (?,?,?,?)";
	file.setId(getMaxId());
	try {
	    pst = con.prepareStatement(query);
	    pst.setInt(1, file.getId());
	    pst.setInt(2, file.getPostId());
	    pst.setString(3, file.getName());
	    pst.setString(4, file.getType());
	    pst.executeUpdate();
	    pst.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public static void updateFile(File file) {
	PreparedStatement pst;
	String query = "UPDATE FILE SET NAME= ?, TYPE= ? WHERE ID = ?";

	try {
	    pst = con.prepareStatement(query);
	    pst.setString(1, file.getName());
	    pst.setString(2, file.getType());
	    pst.setInt(3, file.getId());
	    pst.executeUpdate();
	    pst.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static File getFileByPostId(int id) {
	File file = null;
	String query = "SELECT ID, POSTID, NAME, TYPE FROM FILE WHERE POSTID =" + id;
	Statement st;
	try {
	    st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    if (rs.next()) {
		file = new File();
		file.setId(rs.getInt("ID"));
		file.setPostId(rs.getInt("POSTID"));
		file.setName(rs.getString("NAME"));
		file.setType(rs.getString("TYPE"));

	    }
	    rs.close();
	    st.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return file;
    }

    public static String saveImage(Part filePart) {

	String fileName = null;
	if (filePart.getName() != null) {
	    String SubmittedFileName = filePart.getSubmittedFileName();

	    fileName = SubmittedFileName;
	    fileName = SubmittedFileName.replace(' ', '-');
	    String path = MyProperties.STORAGE_PATH + fileName;
	    try {

		FileOutputStream fos = new FileOutputStream(path);
		InputStream is = filePart.getInputStream();

		byte[] data = new byte[is.available()];
		is.read(data);
		fos.write(data);
		fos.close();
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	}
	return fileName;
    }

    public static void deleteImage(String imageName) {

	Path imagesPath = Paths.get(MyProperties.STORAGE_PATH + imageName);

	try {
	    Files.delete(imagesPath);
	    System.out.println("File " + imagesPath.toAbsolutePath().toString() + " successfully removed");
	} catch (Exception e) {
	    System.err.println("Unable to delete " + imagesPath.toAbsolutePath().toString() + " due to...");
	    e.printStackTrace();
	}

    }

    public static void deleteFile(int postId) {
	PreparedStatement pst = null;
	String query = "DELETE FROM FILE WHERE POSTID=?";
	try {
	    pst = con.prepareStatement(query);
	    pst.setInt(1, postId);
	    pst.executeUpdate();
	    pst.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    private static int getMaxId() {
	int Id = 0;
	PreparedStatement pst = null;
	String query = "SELECT MAX(ID) FROM FILE";
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
