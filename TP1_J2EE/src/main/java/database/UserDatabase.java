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
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		loadDriver(dbdriver);
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dburl, dbuname, dbpw);
			System.out.println("You're connected to database");
			return conn;
		} catch (SQLException e) {
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
			// close JDBC objects
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			result = "Something went wrong !!";
		}
		return result;
	}

	public User getUser(String usrname, String password) throws Exception {

		User user = null;
		Connection myconn = null;
		Statement mystmt = null;
		ResultSet myrs = null;

		try {
			// get a connection
			myconn = dataSource.getConnection();

			// create sql statement
			String sql = "SELECT dateOfBirth, firstName, lastName, mobile, email, userName, password FROM users where id = "
					+ usrname;

			mystmt = myconn.createStatement();

			// execute query
			myrs = mystmt.executeQuery(sql);

			// process result set
			myrs.next();

			// retrieve data from result set row

			String fname = myrs.getString("firstName");
			String lname = myrs.getString("lastName");
			String dbirth = myrs.getString("dateOfBirth");
			String email = myrs.getString("email");
			String mobile = myrs.getString("mobile");
			String uname = myrs.getString("dateOfBirth");
			String pass = myrs.getString("password");

			if (password == pass && usrname == uname && uname != null) {
				user = new User(fname, lname, dbirth, email, mobile, uname, pass);
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return user;
		}
	}
}