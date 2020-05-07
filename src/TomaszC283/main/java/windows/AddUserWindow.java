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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class AddUserWindow extends JFrame {

	// Icons
	ImageIcon background = new ImageIcon("src/TomaszC283/main/java/resources/add_product.jpg");
	static ImageIcon deleteImage = new ImageIcon("src/TomaszC283/main/java/resources/delete.png");

	// GUI
	private JLabel backgroundLabel;
	private JPanel mainPanel = new JPanel();
	private JLabel paddingLabel1 = new JLabel(" ");
	private JLabel paddingLabel2 = new JLabel(" ");
	
	private JLabel header = new JLabel("Enter your profile");
	private JLabel header2 = new JLabel(" details :");
	private JLabel userName = new JLabel      ("Username :  ");
	private JLabel userBodyWeight = new JLabel("Body weight :  ");
	private JLabel userKcalGoal = new JLabel  ("Set kcal goal :  ");
	
	private JTextField userNameTF = new JTextField(15);
	private JTextField userBodyWeightTF = new JTextField(15);
	private JTextField userKcalGoalTF = new JTextField(15);
	
	private JButton addUserButton = new JButton("          Apply          ");
	private JButton returnButton = new JButton("          Abort          ");
	
	// VARIABLES
	private int userID;
	private double userBW;
	private double userKG;
	
	public void NewWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUserWindow window = new AddUserWindow();
					window.setVisible(true);
					window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	AddUserWindow() {
		super("Fitness Calculator - Add new User");
		setSize(550,434);
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - 550) / 2);
	    int y = (int) ((dimension.getHeight() - 434) / 2);
	    setLocation(x, y);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		// background
		backgroundLabel = new JLabel("", background, JLabel.CENTER);
		backgroundLabel.setBounds(0,0,550,434);
		backgroundLabel.setBorder(new LineBorder(new Color(255,255,255,0), 30));

		add(backgroundLabel);
		backgroundLabel.setLayout(new BorderLayout());
		backgroundLabel.add(paddingLabel1, BorderLayout.NORTH);
		backgroundLabel.add(paddingLabel2, BorderLayout.SOUTH);
		backgroundLabel.add(header, BorderLayout.NORTH);
		backgroundLabel.add(mainPanel, BorderLayout.CENTER);
		
		mainPanel.setLayout(new GridLayout(5,2, 2, 12));
		mainPanel.setOpaque(false);
		
		mainPanel.add(header);
		mainPanel.add(header2);
		mainPanel.add(userName);
		mainPanel.add(userNameTF);
		mainPanel.add(userBodyWeight);
		mainPanel.add(userBodyWeightTF);
		mainPanel.add(userKcalGoal);
		mainPanel.add(userKcalGoalTF);
		mainPanel.add(addUserButton);
		mainPanel.add(returnButton);
		
		mainPanel.setBorder(new LineBorder(new Color(255,255,255,0),75));
		addUserButton.setBorder(new LineBorder(Color.WHITE, 1));
		returnButton.setBorder(new LineBorder(Color.WHITE, 1));
		
		addUserButton.setForeground(Color.WHITE);
		returnButton.setForeground(Color.WHITE);
		header.setForeground(Color.BLACK);
		header2.setForeground(Color.BLACK);
		userName.setForeground(Color.BLACK);
		userBodyWeight.setForeground(Color.BLACK);
		userKcalGoal.setForeground(Color.BLACK);
		addUserButton.setBackground(Color.DARK_GRAY);
		returnButton.setBackground(Color.DARK_GRAY);
		
		addUserButton.setFont(new Font("Dialog", Font.BOLD, 15));
		returnButton.setFont(new Font("Dialog", Font.BOLD, 15));
		userName.setFont(new Font("Dialog", Font.BOLD, 15));
		userBodyWeight.setFont(new Font("Dialog", Font.BOLD, 15));
		userKcalGoal.setFont(new Font("Dialog", Font.BOLD, 15));
		header.setFont(new Font("Dialog", Font.BOLD, 20));
		header2.setFont(new Font("Dialog", Font.BOLD, 20));
		
		userName.setHorizontalAlignment(SwingConstants.RIGHT);
		userBodyWeight.setHorizontalAlignment(SwingConstants.RIGHT);
		userKcalGoal.setHorizontalAlignment(SwingConstants.RIGHT);
		
		userNameTF.setBorder(new LineBorder(Color.BLACK, 1));
		userBodyWeightTF.setBorder(new LineBorder(Color.BLACK, 1));
		userKcalGoalTF.setBorder(new LineBorder(Color.BLACK, 1));
		
		addUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!userNameTF.getText().equals("") && !userBodyWeightTF.getText().equals("") && !userKcalGoalTF.getText().equals("")) {
					
					try {

						String myDriver = "com.mysql.cj.jdbc.Driver";
						String myUrl = "jdbc:mysql://phpmyadmin47.lh.pl:3306/serwer58262_Kcal?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&characterEncoding=utf-8";
						Class.forName(myDriver);

						Connection conn = DriverManager.getConnection(myUrl, "serwer58262", "start00#");

						Statement st = conn.createStatement();
						
						userBW = Double.parseDouble(userBodyWeightTF.getText());
						userKG = Double.parseDouble(userKcalGoalTF.getText());
						DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
						Date date = new Date();
						st.executeUpdate(
								"INSERT INTO users (userName, preferedKcal)"
										+ " VALUES ('" + userNameTF.getText() + "'," + userKG + ")");
						
						ResultSet rs = st.executeQuery("SELECT * FROM users WHERE `userName` = '" + userNameTF.getText() + "'");
						
						while (rs.next()) {
							userID = rs.getInt("userID");
						}
						
						st = conn.createStatement();
						st.executeUpdate("INSERT INTO usersweight (userID, date, bodyWeight) VALUES (" + userID + ", '" + dateFormat.format(date) + "', " + userBW + ")");
						
						conn.close();
						LoginWindow.refreshUsersCB();
						dispose();
						
						JOptionPane.showMessageDialog(null, "User " + userNameTF.getText() + " is successfully added to the database" , "Success!", JOptionPane.INFORMATION_MESSAGE,
								null);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE,
								deleteImage);
					}
				}
				
				else {
					JOptionPane.showMessageDialog(null, "     Complete all fields", "Error",
							JOptionPane.INFORMATION_MESSAGE, null);
				}
			}
		});
		
		userBodyWeightTF.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent evt) {


			}

			@Override
			public void keyTyped(KeyEvent evt) {

				char c = evt.getKeyChar();

				String weightString = userBodyWeightTF.getText();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
						|| (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_COMMA))) {
					evt.consume();
				}

				if (c == KeyEvent.VK_COMMA) {
					evt.setKeyChar((char) KeyEvent.VK_PERIOD);
				}
				
				if (weightString.contains(".") && c == KeyEvent.VK_PERIOD) {
					evt.consume();
				}
				
				if (weightString.contains(".") && c == KeyEvent.VK_COMMA) {
					evt.consume();
				}
			}
		});
		
		userKcalGoalTF.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent evt) {


			}

			@Override
			public void keyTyped(KeyEvent evt) {

				char c = evt.getKeyChar();

				String kcalGoalString = userKcalGoalTF.getText();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
						|| (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_COMMA))) {
					evt.consume();
				}

				if (c == KeyEvent.VK_COMMA) {
					evt.setKeyChar((char) KeyEvent.VK_PERIOD);
				}
				
				if (kcalGoalString.contains(".") && c == KeyEvent.VK_PERIOD) {
					evt.consume();
				}
				
				if (kcalGoalString.contains(".") && c == KeyEvent.VK_COMMA) {
					evt.consume();
				}
			}
		});
		
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
}
