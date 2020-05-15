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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import TomaszC283.main.java.PasswordCrypt;

public class LoginWindow extends JFrame {

	// Icons
	ImageIcon background = new ImageIcon("src/TomaszC283/main/java/resources/add_product.jpg");
	static ImageIcon deleteImage = new ImageIcon("src/TomaszC283/main/java/resources/delete.png");

	// GUI
	private JLabel backgroundLabel;

	private JButton loginButton = new JButton("     Choose user     ");
	private JButton newUserButton = new JButton("     New user     ");
	private JButton editUserButton = new JButton("     Edit user     ");
	private JButton exitButton = new JButton(" Close application ");

	private JPanel userNamePanel;
	private JPanel userPasswordPanel;
	private JPanel mainPanel;
	private JPanel userButtonPanel;

	private JLabel header = new JLabel("     Fitness Calculator - Sing in   ");
	private JLabel userNameLabel = new JLabel("Username :  ");
	private JLabel password = new JLabel("Password :  ");

	private static JTextField userNameTF = new JTextField(15);
	private static JPasswordField passwordTF = new JPasswordField(15);

	// Variables
	public static int UserID;
	public static int UserKcalGoal;
	public static String userName;
	
	public static Set<String> productNameList = new TreeSet<>();
	public static Map<String, Double> productCarbsMap = new HashMap<>();
	public static Map<String, Double> productWheyMap = new HashMap<>();
	public static Map<String, Double> productFatsMap = new HashMap<>();
	
	private static String productName;
	private static double productCarbo;
	private static double productWhey;
	private static double productFats;

	public void NewWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setVisible(true);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		getRootPane().setDefaultButton(loginButton);
		
		// background
		backgroundLabel = new JLabel("", background, JLabel.CENTER);
		backgroundLabel.setBounds(0, 0, 550, 434);
		backgroundLabel.setBorder(new LineBorder(new Color(255, 255, 255, 0), 40));

		add(backgroundLabel);
		backgroundLabel.setLayout(new BorderLayout());

		mainPanel = new JPanel();
		userNamePanel = new JPanel();
		userPasswordPanel = new JPanel();
		userButtonPanel = new JPanel();

		mainPanel.setLayout(new GridLayout(7, 1, 10, 10));
		userNamePanel.setLayout(new GridLayout(1, 2));
		userPasswordPanel.setLayout(new GridLayout(1, 2));
		userButtonPanel.setLayout(new GridLayout(1, 2, 20, 0));

		mainPanel.setOpaque(false);
		userNamePanel.setOpaque(false);
		userPasswordPanel.setOpaque(false);
		userButtonPanel.setOpaque(false);

		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		password.setHorizontalAlignment(SwingConstants.CENTER);

		backgroundLabel.add(mainPanel, BorderLayout.CENTER);
		mainPanel.add(header);
		mainPanel.add(userNamePanel);
		userNamePanel.add(userNameLabel);
		userNamePanel.add(userNameTF);
		mainPanel.add(userPasswordPanel);
		userPasswordPanel.add(password);
		userPasswordPanel.add(passwordTF);
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
		userNameTF.setBorder(new LineBorder(Color.BLACK, 1));
		passwordTF.setBorder(new LineBorder(Color.BLACK, 1));

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
		userNameLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		password.setFont(new Font("Dialog", Font.BOLD, 15));

		// Actions
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (userVerification()) {
					checkUserDetails();
					CreateProductMaps();
					MainWindow nw = new MainWindow();
					nw.NewWindow();
					dispose();
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
				if (userVerification()) {
					checkUserDetails();
					EditUserWindow nw = new EditUserWindow();
					nw.NewWindow();
				}
			}
		});

		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	private static Boolean userVerification() {

		try {

			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://phpmyadmin47.lh.pl:3306/serwer58262_Kcal?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&characterEncoding=utf-8";
			Class.forName(myDriver);

			Connection conn = DriverManager.getConnection(myUrl, "serwer58262_Kcal", "kcal00#");

			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM users WHERE `userName` = '" + userNameTF.getText() + "'");

			if (rs.next()) {
				if (PasswordCrypt.passwordMatcher(new String(passwordTF.getPassword()), rs.getString("password"))) {
					return true;
				}

				else {
					JOptionPane.showMessageDialog(null, "   Incorect password!", "Error!",
							JOptionPane.INFORMATION_MESSAGE, deleteImage);
				}
			} else {
				JOptionPane.showMessageDialog(null, "   Username deosn't exists in database!", "Error!",
						JOptionPane.INFORMATION_MESSAGE, deleteImage);
			}
			conn.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE,
					deleteImage);
		}
		return false;
	}

	static void checkUserDetails() {

		userName = userNameTF.getText();

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
			MainWindow.KcalSlider.setMaximum(LoginWindow.UserKcalGoal + 1500);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE,
					deleteImage);
		}
	}
	
	
	public static void CreateProductMaps() {
		try {

			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://phpmyadmin47.lh.pl:3306/serwer58262_Kcal?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&characterEncoding=utf-8";
			Class.forName(myDriver);

			Connection conn = DriverManager.getConnection(myUrl, "serwer58262_Kcal", "kcal00#");

			Statement st = conn.createStatement();


			ResultSet rs = st.executeQuery("SELECT * FROM products WHERE `userID` = " + UserID + " ORDER By productname ASC");
			
			while (rs.next()) {
				productName = rs.getString("ProductName");
				productCarbo = rs.getDouble("ProductCarbo");
				productWhey = rs.getDouble("ProductWhey");
				productFats = rs.getDouble("ProductFats");
				
				productNameList.add(productName);
				productCarbsMap.put(productName, productCarbo);
				productWheyMap.put(productName, productWhey);
				productFatsMap.put(productName, productFats);
				
			}

			conn.close();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE,
					deleteImage);
		}
	}
}
