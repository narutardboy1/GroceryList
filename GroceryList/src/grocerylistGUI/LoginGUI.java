package grocerylistGUI;

import grocerylist.LoginDriver;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginGUI extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7759429362025760185L;
	
	public static final int WIDTH = 400;
	public static final int HEIGHT = 600;
	
	private LoginDriver driver;
	
	//JPanels
	private JPanel borderpanel = new JPanel();
	private JPanel flowuserpass = new JPanel();
	private JPanel unpanel = new JPanel();
	private JPanel pwpanel = new JPanel();
	private JPanel loginbtnpanel = new JPanel();
	private JPanel registerbtnpanel = new JPanel();
	private JPanel filler = new JPanel();
	
	//Labels
	private JLabel usernamelabel = new JLabel("Username: ");
	private JLabel passwordlabel = new JLabel("Password: ");
	
	//Fields
	private JTextField username = new JTextField();
	private JPasswordField password = new JPasswordField();
	
	//Buttons
	private JButton button = new JButton("Login");
	private JButton register = new JButton("Register!");
	
	
	public LoginGUI() {
		driver = new LoginDriver();
		
		Container c = getContentPane();
		
		button.setPreferredSize(new Dimension(140, 30));
		button.addActionListener(this);
		register.setPreferredSize(new Dimension(140, 30));
		register.addActionListener(this);
		
		//TODO make sure un and pw don't exceed 200 chars
		username.setPreferredSize(new Dimension(200, 20));
		password.setPreferredSize(new Dimension(200, 20));
		
		unpanel.add(usernamelabel);
		unpanel.add(username);
		
		pwpanel.setPreferredSize(new Dimension(400, 50));
		pwpanel.add(passwordlabel);
		pwpanel.add(password);
		
		loginbtnpanel.setPreferredSize(new Dimension(300, 50));
		loginbtnpanel.add(button);
		
		registerbtnpanel.setPreferredSize(new Dimension(300, 50));
		registerbtnpanel.add(register);
		
		flowuserpass.setLayout(new FlowLayout());
		flowuserpass.add(unpanel);
		flowuserpass.add(pwpanel);
		flowuserpass.add(loginbtnpanel);
		flowuserpass.add(registerbtnpanel);
		
		filler.setPreferredSize(new Dimension(1, 200));
		
		borderpanel.setLayout(new BorderLayout());
		borderpanel.add(filler, BorderLayout.NORTH);
		borderpanel.add(flowuserpass, BorderLayout.CENTER);
		
		c.add(borderpanel);
		
		
		
		
		
		
		
		setSize(WIDTH, HEIGHT);
	    centerWindow(this);
	    setTitle("Login");
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
		if (e.getSource().equals(button)) { //If user tries to login
			if (username.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Please enter your username.");
			driver.setUsername(username.getText());
			if (String.valueOf(password.getPassword()).equals(""))
				JOptionPane.showMessageDialog(null, "Please enter your password.");
			driver.setPassword(String.valueOf(password.getPassword()));
			if (driver.verifyLogin()) {
				JOptionPane.showMessageDialog(null, "Login successful!!!");
			} else {
				JOptionPane.showMessageDialog(null, "Login failed.");
			}
		} else if (e.getSource().equals(register)) { //If user tries to register
			//TODO REGISTER!!!
		}
	}
	

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			System.out.println("Unable to load Windows look and feel");
		}

		new LoginGUI();

	}

}
