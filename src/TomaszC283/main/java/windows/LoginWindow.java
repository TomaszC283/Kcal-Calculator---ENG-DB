package TomaszC283.main.java.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class LoginWindow extends JFrame {

	// Icons
	ImageIcon background = new ImageIcon("src/TomaszC283/main/java/resources/add_product.jpg");
	static ImageIcon deleteImage = new ImageIcon("src/TomaszC283/main/java/resources/delete.png");

	// GUI
	private JLabel backgroundLabel;

	JButton loginButton = new JButton("     Choose user     ");
	JButton newUserButton = new JButton("     New user     ");
	JButton editUserButton = new JButton("     Edit user     ");
	JButton exitButton = new JButton(" Close application ");

	JPanel mainPanel;
	JLabel header = new JLabel("      Select your username ");
	JPanel userButtonPanel = new JPanel();

	static JComboBox<String> userComboBox = new JComboBox<>();

	// Variables
	public static int UserID;
	public static int UserKcalGoal;
	public static String userName;

	public void NewWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					window.setVisible(true);
					window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public LoginWindow() {

		super("Fitness Calculator - User selection");
		setSize(550, 434);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - 550) / 2);
		int y = (int) ((dimension.getHeight() - 434) / 2);
		setLocation(x, y);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);

		// background
		backgroundLabel = new JLabel("", background, JLabel.CENTER);
		backgroundLabel.setBounds(0, 0, 550, 434);
		backgroundLabel.setBorder(new LineBorder(new Color(255,255,255,0),50));

		add(backgroundLabel);
		backgroundLabel.setLayout(new BorderLayout());

		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(5, 1, 10, 10));
		userButtonPanel.setLayout(new GridLayout(1,2, 20, 0));

		mainPanel.setOpaque(false);
		userButtonPanel.setOpaque(false);

		backgroundLabel.add(mainPanel, BorderLayout.CENTER);
		mainPanel.add(header);
		mainPanel.add(userComboBox);
		mainPanel.add(loginButton);
		mainPanel.add(userButtonPanel);
		userButtonPanel.add(newUserButton);
		userButtonPanel.add(editUserButton);
		mainPanel.add(exitButton);

		mainPanel.setBorder(new LineBorder(new Color(255, 255, 255, 0), 45));
		loginButton.setBorder(new LineBorder(Color.WHITE, 2));
		newUserButton.setBorder(new LineBorder(Color.WHITE, 2));
		editUserButton.setBorder(new LineBorder(Color.WHITE, 2));
		exitButton.setBorder(new LineBorder(Color.WHITE, 2));
		
		loginButton.setForeground(Color.WHITE);
		newUserButton.setForeground(Color.WHITE);
		editUserButton.setForeground(Color.WHITE);
		header.setForeground(Color.BLACK);
		exitButton.setForeground(Color.WHITE);
		
		loginButton.setBackground(Color.DARK_GRAY);
		newUserButton.setBackground(Color.DARK_GRAY);
		editUserButton.setBackground(Color.DARK_GRAY);
		exitButton.setBackground(Color.DARK_GRAY);
		
		loginButton.setFont(new Font("Dialog", Font.BOLD, 14));
		newUserButton.setFont(new Font("Dialog", Font.BOLD, 14));
		editUserButton.setFont(new Font("Dialog", Font.BOLD, 14));
		header.setFont(new Font("Dialog", Font.BOLD, 18));
		exitButton.setFont(new Font("Dialog", Font.BOLD, 14));
		
		((JLabel) userComboBox.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		refreshUsersCB();
		
		// Actions
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (userComboBox.getSelectedItem() != null) {

					checkUserDetails();
					MainWindow nw = new MainWindow();
					nw.NewWindow();
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "     No user have been selected!", "Error",
							JOptionPane.INFORMATION_MESSAGE, null);
				}
			}
		});

		newUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUserWindow nw = new AddUserWindow();
				nw.NewWindow();
			}
		});
		
		editUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userName = userComboBox.getSelectedItem().toString();
				EditUserWindow nw = new EditUserWindow();
				nw.NewWindow();
			}
		});
		
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	static void refreshUsersCB() {

		try {
			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://phpmyadmin47.lh.pl:3306/serwer58262_Kcal?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&characterEncoding=utf-8";
			Class.forName(myDriver);

			Connection conn = DriverManager.getConnection(myUrl, "serwer58262_Kcal", "kcal00#");

			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM users ORDER BY userName ASC");

			userComboBox.removeAllItems();

			while (rs.next()) {
				String name = rs.getString("userName");
				userComboBox.addItem(name);
			}
			conn.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE,
					deleteImage);
		}
	}

	static void checkUserDetails() {

		userName = userComboBox.getSelectedItem().toString();
		
		try {

			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://phpmyadmin47.lh.pl:3306/serwer58262_Kcal?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&characterEncoding=utf-8";
			Class.forName(myDriver);

			Connection conn = DriverManager.getConnection(myUrl, "serwer58262_Kcal", "kcal00#");

			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM users WHERE `userName` = '" + userName + "'");

			while (rs.next()) {
				UserID = rs.getInt("userID");
				UserKcalGoal = rs.getInt("preferedKcal");
			}
			conn.close();
			MainWindow.KcalSlider.setMaximum(LoginWindow.UserKcalGoal+1500);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE,
					deleteImage);
		}
	}
}
