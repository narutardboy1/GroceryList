package grocerylistGUI;

import grocerylist.RegisterDriver;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RegisterGUI extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7759429362025760185L;
	
	public static final int WIDTH = 400;
	public static final int HEIGHT = 600;
	
	private RegisterDriver driver;
	
	//JPanels
	private JPanel borderpanel = new JPanel();
	private JPanel flowuserpass = new JPanel();
	private JPanel unpanel = new JPanel();
	private JPanel pwpanel = new JPanel();
	private JPanel vppanel = new JPanel();
	private JPanel empanel = new JPanel();
	private JPanel loginbtnpanel = new JPanel();
	private JPanel backbtnpanel = new JPanel();
	private JPanel filler = new JPanel();
	
	//Labels
	private JLabel usernamelabel = new JLabel("Username: ");
	private JLabel passwordlabel = new JLabel("Password: ");
	private JLabel verifylabel = new JLabel("Verify Password: ");
	private JLabel emaillabel = new JLabel("Email Address: ");
	
	//Fields
	private JTextField username = new JTextField();
	private JPasswordField password = new JPasswordField();
	private JPasswordField verifypassword = new JPasswordField();
	private JTextField email = new JTextField();
	
	//Buttons
	private JButton register = new JButton("Register!");
	private JButton back = new JButton("Back To Login");
	
	
	public RegisterGUI() {
		driver = new RegisterDriver();
		
		Container c = getContentPane();
		
		register.setPreferredSize(new Dimension(140, 30));
		register.addActionListener(this);
		back.setPreferredSize(new Dimension(140, 30));
		back.addActionListener(this);
		
		username.setPreferredSize(new Dimension(200, 20));
		password.setPreferredSize(new Dimension(200, 20));
		verifypassword.setPreferredSize(new Dimension(200, 20));
		email.setPreferredSize(new Dimension(200, 20));
		
		unpanel.add(usernamelabel);
		unpanel.add(username);
		
		pwpanel.add(passwordlabel);
		pwpanel.add(password);
		
		vppanel.add(verifylabel);
		vppanel.add(verifypassword);
		
		empanel.setPreferredSize(new Dimension(400, 50));
		empanel.add(emaillabel);
		empanel.add(email);
		
		loginbtnpanel.setPreferredSize(new Dimension(300, 50));
		loginbtnpanel.add(register);
		
		backbtnpanel.setPreferredSize(new Dimension(300, 50));
		backbtnpanel.add(back);
		
		flowuserpass.setLayout(new FlowLayout());
		flowuserpass.add(unpanel);
		flowuserpass.add(pwpanel);
		flowuserpass.add(vppanel);
		flowuserpass.add(empanel);
		flowuserpass.add(loginbtnpanel);
		flowuserpass.add(backbtnpanel);
		
		filler.setPreferredSize(new Dimension(1, 200));
		
		borderpanel.setLayout(new BorderLayout());
		borderpanel.add(filler, BorderLayout.NORTH);
		borderpanel.add(flowuserpass, BorderLayout.CENTER);
		
		c.add(borderpanel);
		
		setSize(WIDTH, HEIGHT);
	    centerWindow(this);
	    setTitle("Register!!!");
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setResizable(false);
	    setVisible(true);
	}
	
	public static void centerWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(register)) { //If user tries to register
			if (username.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Please enter your username.");
			if (username.getText().length() > 200) {
				JOptionPane.showMessageDialog(null, "Username must be shorter than 200 characters.");
			} else {
				driver.setUsername(username.getText());
			}
			if (String.valueOf(password.getPassword()).equals(""))
				JOptionPane.showMessageDialog(null, "Please enter your password.");
			if (String.valueOf(password.getPassword()).length() > 200) {
				JOptionPane.showMessageDialog(null, "Password must be shorter than 200 characters.");
			} else {
				driver.setPassword(String.valueOf(password.getPassword()));
			}
			if (String.valueOf(verifypassword.getPassword()).equals(""))
				JOptionPane.showMessageDialog(null, "Please verify your password.");
			if (String.valueOf(verifypassword.getPassword()).length() > 200) {
				JOptionPane.showMessageDialog(null, "Password must be shorter than 200 characters.");
			} else {
				driver.setVerifyPassword(String.valueOf(password.getPassword()));
			}
			if (email.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Please enter your email address.");
			if (username.getText().length() > 200) {
				JOptionPane.showMessageDialog(null, "email must be shorter than 200 characters.");
			} else {
				driver.setEmail(email.getText());
			}
			if (driver.createUser()) {
				JOptionPane.showMessageDialog(null, "Welcome!");
				goBack();
			} else {
				//if (driver.getBool())
				JOptionPane.showMessageDialog(null, "Login failed.");
			}
		} else if (e.getSource().equals(back)) { //If user tries to go back to login
			goBack();
		}
	}
	
	private void goBack() {
		this.setVisible(true);
		new LoginGUI();
	}
	

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			System.out.println("Unable to load Windows look and feel");
		}

		new RegisterGUI();

	}

}
