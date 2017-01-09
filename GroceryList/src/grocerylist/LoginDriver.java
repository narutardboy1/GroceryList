package grocerylist;

import java.sql.*;

import javax.swing.JOptionPane;

public class LoginDriver {

	private Connection myConn;
	private Statement myStmt;
	private String username;
	private String password;
	private ResultSet myRs;

	public LoginDriver() {
		try { //Try to get connection to SQL Server. Throws SQLException if connection is refused for any reason.
			myConn = DriverManager.getConnection("jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9152919", "sql9152919", "4nz4bn34zL");
		} catch (SQLException cnc) {
			JOptionPane.showMessageDialog(null, cnc.getMessage());
			System.exit(1);
		}
		
		try { //Try to create statement from connection. Throws SQLException if statement cannot be created.
			myStmt = myConn.createStatement();
		} catch (SQLException errstmt) {
			JOptionPane.showMessageDialog(null, errstmt.getMessage());
			System.exit(1);
		}
		
		try {
			myRs = myStmt.executeQuery("SELECT password FROM users WHERE username = 'narutardboy2' ");
		} catch (SQLException errstmt) {
			JOptionPane.showMessageDialog(null, errstmt.getMessage());
		}
		
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String gethashedPassword() {
		if (this.password == null)
			return "";
		return String.valueOf(password.hashCode());
	}

}
