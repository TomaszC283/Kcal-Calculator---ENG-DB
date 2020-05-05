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

	JPanel mainPanel;
	JLabel paddingPanel1 = new JLabel("    Select your username ");
	JLabel paddingPanel2 = new JLabel("  ");
	JLabel paddingPanel3 = new JLabel("                                   ");
	JLabel paddingPanel4 = new JLabel("                                   ");
	JLabel paddingPanel6 = new JLabel("  ");
	JLabel paddingPanel7 = new JLabel("  ");
	JLabel paddingPanel8 = new JLabel("  ");
	JLabel paddingPanel9 = new JLabel("  ");
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
		backgroundLabel.setBorder(new LineBorder(Color.white, 4));

		add(backgroundLabel);
		backgroundLabel.setLayout(new BorderLayout());

		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(9, 1));
		userButtonPanel.setLayout(new BorderLayout());

		mainPanel.setOpaque(false);
		userButtonPanel.setOpaque(false);

		backgroundLabel.add(mainPanel, BorderLayout.CENTER);
		backgroundLabel.add(paddingPanel3, BorderLayout.EAST);
		backgroundLabel.add(paddingPanel4, BorderLayout.WEST);
		mainPanel.add(paddingPanel8);
		mainPanel.add(paddingPanel1);
		mainPanel.add(userComboBox);
		mainPanel.add(paddingPanel6);
		mainPanel.add(loginButton);
		mainPanel.add(paddingPanel7);
		mainPanel.add(userButtonPanel);
		userButtonPanel.add(newUserButton, BorderLayout.WEST);
		userButtonPanel.add(editUserButton, BorderLayout.EAST);
		mainPanel.add(paddingPanel2);
		mainPanel.add(paddingPanel9);

		mainPanel.setBorder(new LineBorder(new Color(255, 255, 255, 0), 45));
		loginButton.setBorder(new LineBorder(Color.WHITE, 2));
		newUserButton.setBorder(new LineBorder(Color.WHITE, 2));
		editUserButton.setBorder(new LineBorder(Color.WHITE, 2));

		loginButton.setForeground(Color.WHITE);
		newUserButton.setForeground(Color.WHITE);
		editUserButton.setForeground(Color.WHITE);
		paddingPanel1.setForeground(Color.DARK_GRAY);

		loginButton.setBackground(Color.DARK_GRAY);
		newUserButton.setBackground(Color.DARK_GRAY);
		editUserButton.setBackground(Color.DARK_GRAY);

		loginButton.setFont(new Font("Dialog", Font.BOLD, 14));
		newUserButton.setFont(new Font("Dialog", Font.BOLD, 14));
		editUserButton.setFont(new Font("Dialog", Font.BOLD, 14));
		paddingPanel1.setFont(new Font("Dialog", Font.BOLD, 18));

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
	}

	static void refreshUsersCB() {

		try {
			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://localhost:3306/kcal?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&characterEncoding=utf-8";
			Class.forName(myDriver);

			Connection conn = DriverManager.getConnection(myUrl, "root", "start00#");

			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM Users ORDER BY userName ASC");

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
			String myUrl = "jdbc:mysql://localhost:3306/kcal?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&characterEncoding=utf-8";
			Class.forName(myDriver);

			Connection conn = DriverManager.getConnection(myUrl, "root", "start00#");

			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM Users WHERE `userName` = '" + userName + "'");

			while (rs.next()) {
				UserID = rs.getInt("userID");
				UserKcalGoal = rs.getInt("preferedKcal");
			}
			conn.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE,
					deleteImage);
		}
	}
}
