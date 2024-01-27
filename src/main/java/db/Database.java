package db;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Database {

    private static Database dbObject;

    private Database() {
    }

    public static synchronized final Connection getConnection() {
	Connection con = null;
	try {
	    MysqlDataSource ods = new MysqlDataSource();
	    ods.setURL("jdbc:mysql://localhost:3306/blogdb");
	    con = ods.getConnection("root", "root");

	  //  System.out.println("Connection Established");
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return con;
    }

    public static Database getInstance() {

	if (dbObject == null) {
	    dbObject = new Database();

	}

	return dbObject;

    }

}
