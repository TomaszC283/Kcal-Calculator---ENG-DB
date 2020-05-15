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
import java.sql.Statement;

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

public class EditUserWindow extends JFrame {

	// Icons
	ImageIcon background = new ImageIcon("src/TomaszC283/main/java/resources/add_product.jpg");
	static ImageIcon deleteImage = new ImageIcon("src/TomaszC283/main/java/resources/delete.png");

	// GUI
	private JLabel backgroundLabel;
	private JPanel mainPanel = new JPanel();

	private JLabel header = new JLabel("    Change your ");
	private JLabel header2 = new JLabel(" profile details :");
	private JLabel userName = new JLabel("  New username :    ");
	private JLabel password = new JLabel(" New password :    ");
	private JLabel userKcalGoal = new JLabel(" New kcal goal :    ");

	private JTextField userNameTF = new JTextField(15);
	private JPasswordField passwordTF = new JPasswordField(15);
	private JTextField userKcalGoalTF = new JTextField(15);

	private JButton applyButton = new JButton("          Apply          ");
	private JButton returnButton = new JButton("          Abort          ");

	public void NewWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setVisible(true);
					setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	EditUserWindow() {
		super("Fitness Calculator - Edit user: " + LoginWindow.userName);
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
		backgroundLabel.setBorder(new LineBorder(new Color(255, 255, 255, 0), 40));

		add(backgroundLabel);
		backgroundLabel.setLayout(new BorderLayout());
		backgroundLabel.add(mainPanel, BorderLayout.CENTER);

		mainPanel.setLayout(new GridLayout(5, 2, 1, 10));
		mainPanel.setOpaque(false);

		mainPanel.add(header);
		mainPanel.add(header2);
		mainPanel.add(userName);
		mainPanel.add(userNameTF);
		mainPanel.add(password);
		mainPanel.add(passwordTF);
		mainPanel.add(userKcalGoal);
		mainPanel.add(userKcalGoalTF);
		mainPanel.add(applyButton);
		mainPanel.add(returnButton);

		mainPanel.setBorder(new LineBorder(new Color(255, 255, 255, 0), 75));
		applyButton.setBorder(new LineBorder(Color.WHITE, 2));
		returnButton.setBorder(new LineBorder(Color.WHITE, 2));

		applyButton.setForeground(Color.WHITE);
		returnButton.setForeground(Color.WHITE);

		userName.setHorizontalAlignment(SwingConstants.RIGHT);
		password.setHorizontalAlignment(SwingConstants.RIGHT);
		userKcalGoal.setHorizontalAlignment(SwingConstants.RIGHT);

		userNameTF.setBorder(new LineBorder(Color.BLACK, 1));
		userKcalGoalTF.setBorder(new LineBorder(Color.BLACK, 1));

		applyButton.setBackground(Color.DARK_GRAY);
		returnButton.setBackground(Color.DARK_GRAY);

		applyButton.setFont(new Font("Dialog", Font.BOLD, 14));
		returnButton.setFont(new Font("Dialog", Font.BOLD, 14));
		userName.setFont(new Font("Dialog", Font.BOLD, 14));
		password.setFont(new Font("Dialog", Font.BOLD, 14));
		userKcalGoal.setFont(new Font("Dialog", Font.BOLD, 14));
		header.setFont(new Font("Dialog", Font.BOLD, 19));
		header2.setFont(new Font("Dialog", Font.BOLD, 19));
		userNameTF.setText("");
		userKcalGoalTF.setText("");

		// Actions

		applyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {

					String myDriver = "com.mysql.cj.jdbc.Driver";
					String myUrl = "jdbc:mysql://phpmyadmin47.lh.pl:3306/serwer58262_Kcal?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&characterEncoding=utf-8";
					Class.forName(myDriver);

					Connection conn = DriverManager.getConnection(myUrl, "serwer58262_Kcal", "kcal00#");

					Statement st = conn.createStatement();

					if (!userNameTF.getText().equals("") && !userKcalGoalTF.getText().equals("")) {
						st.executeUpdate("UPDATE users SET userName = '" + userNameTF.getText() + "', preferedKcal = "
								+ Integer.parseInt(userKcalGoalTF.getText()) + " WHERE userName = '"
								+ LoginWindow.userName + "'");
						LoginWindow.userName = userNameTF.getText();
					} else if (!userNameTF.getText().equals("") && userKcalGoalTF.getText().equals("")) {
						st.executeUpdate("UPDATE users SET userName = '" + userNameTF.getText() + "' WHERE userName = '"
								+ LoginWindow.userName + "'");
						LoginWindow.userName = userNameTF.getText();
					} else if (userNameTF.getText().equals("") && !userKcalGoalTF.getText().equals("")) {
						st.executeUpdate("UPDATE users SET preferedKcal = " + Integer.parseInt(userKcalGoalTF.getText())
								+ " WHERE userName = '" + LoginWindow.userName + "'");
					}

					if (!(new String(passwordTF.getPassword()).equals(""))) {
						st.executeUpdate("UPDATE users SET password = '"
								+ PasswordCrypt.passwordEncoder(new String(passwordTF.getPassword()))
								+ "' WHERE userName = '" + LoginWindow.userName + "'");
					}
					conn.close();
					dispose();

					JOptionPane.showMessageDialog(null, "User " + userNameTF.getText() + " is successfully edited!",
							"Success!", JOptionPane.INFORMATION_MESSAGE, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE,
							deleteImage);
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
