package grocerylist;

import java.sql.*;

import javax.swing.JOptionPane;

public class LoginDriver {

	private Connection myConn;
	private Statement myStmt;
	private String username;
	private String password;
	private ResultSet myRs;
	private boolean usernamefound = false;

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
	
	public boolean usernameFoundFromSQL() {
		try {
			myRs = myStmt.executeQuery("SELECT username FROM users WHERE username = '" + this.username + "'");
		} catch (SQLException errstmt) {
			JOptionPane.showMessageDialog(null, errstmt.getMessage() + "\nThere was a problem with the username provided.");
			System.exit(1);
		}
		
		try {
			if(myRs.getString("username").equals(this.username))
				return (usernamefound = true);
		} catch (SQLException erruser) {
			JOptionPane.showMessageDialog(null, erruser.getMessage() + "\nUsername not found.");
			System.exit(1);
		}
		return usernamefound;
	}
	
	public String getPasswordFromSQL() {
		try {
			myRs = myStmt.executeQuery("SELECT password FROM users WHERE username = '" + this.username + "'");
		} catch (SQLException errstmt) {
			JOptionPane.showMessageDialog(null, errstmt.getMessage() + "\nThere was a problem with the username provided.");
			System.exit(1);
		}
		
		try {
			return myRs.getString("password");
		} catch (SQLException erruser) {
			JOptionPane.showMessageDialog(null, erruser.getMessage() + "\nCouldn't get password.");
			System.exit(1);
		}
		return "";
	}
	
	public boolean verifyLogin() {
		if (usernamefound) {
			if (this.getPasswordFromSQL().equals(this.gethashedPassword()))
				return true;
		}
		return false;
	}

}
