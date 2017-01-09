package grocerylist;

import java.sql.*;

import javax.swing.JOptionPane;

public class RegisterDriver {
	
	private Connection myConn;
	private Statement myStmt;
	private ResultSet myRs;
	private String username;
	private String password;
	private String verifypassword;
	private String email;
	
	public RegisterDriver() {
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
	
	public void setVerifyPassword(String verifypassword) {
		this.verifypassword = verifypassword;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean usernameFoundFromSQL() {
		try {
			myRs = myStmt.executeQuery("SELECT username FROM users WHERE username = '" + this.username + "'");
			if (myRs.next()) {
				JOptionPane.showMessageDialog(null, "Username already in use. Please enter another username or login with entered username.");
				return true;
			}
		} catch (SQLException errstmt) {	
			return false;
		}
		return false;
		
	}
	
	public boolean verifyPassword() {
		if (!this.password.equals(this.verifypassword)) {
			JOptionPane.showMessageDialog(null, "Passwords do not match. Please enter the same password for password and verification.");
			return true;
		}
		return false;
	}
	
	public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
	}
	
	public boolean processEmail() {
		if (!isValidEmailAddress(email)) {
			JOptionPane.showMessageDialog(null, "Please enter a valid email address!");
			return true;
		}
		return false;
	}
	
	public boolean emailFoundFromSQL() {
		try {
			myRs = myStmt.executeQuery("SELECT email FROM users WHERE email = '" + this.email + "'");
			if (myRs.next()) {
				System.out.println(myRs.getString("email"));
				JOptionPane.showMessageDialog(null, "Email address already in use. Please enter another email address or login with existing account.");
				return true;
			}
		} catch (SQLException errstmt) {	
			return false;
		}
		return false;
		
	}
	
	public boolean createUser() {
		if (!usernameFoundFromSQL() && !verifyPassword() && !processEmail() && !emailFoundFromSQL()) {
			try {
				myStmt.execute("INSERT INTO users(username, password, email) VALUES('" + this.username + "','" + String.valueOf(this.password.hashCode()) + "', '" + this.email + "')");
				return true;
			} catch (SQLException erruser) {
				JOptionPane.showMessageDialog(null, "Could not register user.");
			}
		}
		return false;
	}


}
