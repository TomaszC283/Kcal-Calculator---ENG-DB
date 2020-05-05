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
	
	
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	private JPanel panel4 = new JPanel();
	
	private JLabel header = new JLabel("Enter your profile Details :");
	private JLabel userName = new JLabel      ("     Username : ");
	private JLabel userBodyWeight = new JLabel("  Body weight : ");
	private JLabel userKcalGoal = new JLabel  (" Set kcal goal : ");
	
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
					window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		backgroundLabel.setBorder(new LineBorder(Color.white, 4));

		add(backgroundLabel);
		backgroundLabel.setLayout(new BorderLayout());
		backgroundLabel.add(paddingLabel1, BorderLayout.NORTH);
		backgroundLabel.add(paddingLabel2, BorderLayout.SOUTH);
		backgroundLabel.add(mainPanel, BorderLayout.CENTER);
		
		mainPanel.setLayout(new GridLayout(5,1));
		mainPanel.setOpaque(false);
		panel1.setOpaque(false);
		panel2.setOpaque(false);
		panel3.setOpaque(false);
		panel4.setOpaque(false);
		
		mainPanel.add(header);
		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);
		mainPanel.add(panel4);
		
		panel1.add(userName);
		panel1.add(userNameTF);
		panel2.add(userBodyWeight);
		panel2.add(userBodyWeightTF);
		panel3.add(userKcalGoal);
		panel3.add(userKcalGoalTF);
		panel4.add(addUserButton);
		panel4.add(returnButton);
		
		mainPanel.setBorder(new LineBorder(new Color(255,255,255,0),75));
		addUserButton.setBorder(new LineBorder(Color.WHITE, 2));
		returnButton.setBorder(new LineBorder(Color.WHITE, 2));
		
		addUserButton.setForeground(Color.WHITE);
		returnButton.setForeground(Color.WHITE);
		header.setForeground(Color.DARK_GRAY);
		userName.setForeground(Color.DARK_GRAY);
		userBodyWeight.setForeground(Color.DARK_GRAY);
		userKcalGoal.setForeground(Color.DARK_GRAY);
		
		addUserButton.setBackground(Color.DARK_GRAY);
		returnButton.setBackground(Color.DARK_GRAY);
		
		addUserButton.setFont(new Font("Dialog", Font.BOLD, 14));
		returnButton.setFont(new Font("Dialog", Font.BOLD, 14));
		userName.setFont(new Font("Dialog", Font.BOLD, 14));
		userBodyWeight.setFont(new Font("Dialog", Font.BOLD, 14));
		userKcalGoal.setFont(new Font("Dialog", Font.BOLD, 14));
		header.setFont(new Font("Dialog", Font.BOLD, 20));
		
		addUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!userNameTF.getText().equals("") && !userBodyWeightTF.getText().equals("") && !userKcalGoalTF.getText().equals("")) {
					
					try {

						String myDriver = "com.mysql.cj.jdbc.Driver";
						String myUrl = "jdbc:mysql://localhost:3306/kcal?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&characterEncoding=utf-8";
						Class.forName(myDriver);

						Connection conn = DriverManager.getConnection(myUrl, "root", "start00#");

						Statement st = conn.createStatement();
						
						userBW = Double.parseDouble(userBodyWeightTF.getText());
						userKG = Double.parseDouble(userKcalGoalTF.getText());
						DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
						Date date = new Date();
						st.executeUpdate(
								"INSERT INTO users (userName, bodyWeight, preferedKcal)"
										+ " VALUES ('" + userNameTF.getText() + "'," + userBW + "," + userKG + ")");
						
						ResultSet rs = st.executeQuery("SELECT * FROM users WHERE `userName` = '" + userNameTF.getText() + "'");
						
						while (rs.next()) {
							userID = rs.getInt("userID");
						}
						
						st = conn.createStatement();
						st.executeUpdate("INSERT INTO usersweight (userID, date, bodyWeight) VALUES (" + userID + ", '" + dateFormat.format(date) + "', " + userKG + ")");
						
						conn.close();
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
		
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
}
