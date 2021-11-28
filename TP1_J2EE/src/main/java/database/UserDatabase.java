package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import metier.User;

public class UserDatabase {
	private String dburl = "jdbc:mysql://localhost:3306/tp1";
	private String dbuname = "root";
	private String dbpw = "";
	private String dbdriver = "com.mysql.jdbc.Driver";
	private DataSource dataSource;
	public void loadDriver(String dbDriver) {
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		loadDriver(dbdriver);
		Connection conn=null;
		try {
			conn = DriverManager.getConnection(dburl, dbuname, dbpw);
			System.out.println("You're connected to database");
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return conn;
		}
	}	
public String insert(User usr) {
	Connection conn = getConnection();
	String result = "Data entered successfully";
	String sql = "INSERT INTO tp1.users VALUES(?,?,?,?,?,?,?)";
	try {
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, usr.getFirstName());
		ps.setString(2, usr.getLastName());
		ps.setString(3, usr.getEmail());
		ps.setString(4, usr.getMobile());
		ps.setString(5, usr.getDateOfBirth());
		ps.setString(6, usr.getUsername());
		ps.setString(7, usr.getPassword());
		ps.executeUpdate();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		result = "Something went wrong !!";
	}
	return result;
}
public List<User> getStudents(String usrname, String password) throws Exception {
	
	User user = new User();
	
	Connection myConn = null;
	Statement myStmt = null;
	ResultSet myRs = null;
	
	try {
		// get a connection
		myConn = dataSource.getConnection();
		
		// create sql statement
		String sql = "select * from users where id = " + usrname;
		
		myStmt = myConn.createStatement();
		
		// execute query
		myRs = myStmt.executeQuery(sql);
		
		// process result set
		myRs.next()
			
			// retrieve data from result set row

			String firstName = myRs.getString("first_name");
			String lastName = myRs.getString("last_name");
			String 
			String email = myRs.getString("email");

			User tempUser = new User(fname, lname, dbirth, email, mobile, uname, password);

		
		return students;		
	}
	finally {
		// close JDBC objects
		close(myConn, myStmt, myRs);
	}		
}

private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

	try {
		if (myRs != null) {
			myRs.close();
		}
		
		if (myStmt != null) {
			myStmt.close();
		}
		
		if (myConn != null) {
			myConn.close();   // doesn't really close it ... just puts back in connection pool
		}
	}
	catch (Exception exc) {
		exc.printStackTrace();
	}
}
}
