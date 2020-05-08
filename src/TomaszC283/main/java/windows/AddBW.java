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

public class AddBW extends JFrame {

	// Icons
	ImageIcon background = new ImageIcon("src/TomaszC283/main/java/resources/add_product.jpg");
	static ImageIcon deleteImage = new ImageIcon("src/TomaszC283/main/java/resources/delete.png");

	// GUI
	private JLabel backgroundLabel;
	private JPanel mainPanel;
	private JButton applyButton = new JButton("Apply BW");
	private JButton returnButton = new JButton("Return");
	private JLabel padding = new JLabel("");
	private JLabel header = new JLabel("Enter your today's body weight :");
	private JTextField bwTF = new JTextField();
	AddBW() {
		super("Fitness Calculator - Add Today's BW");
		setSize(550, 434);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) (((dimension.getWidth() - 1147) / 2) + 100);
		int y = (int) (((dimension.getHeight() - 700) / 2) + 100);
		setLocation(x, y);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);

		// background
		backgroundLabel = new JLabel("", background, JLabel.CENTER);
		backgroundLabel.setBounds(0, 0, 550, 434);
		backgroundLabel.setBorder(new LineBorder(new Color(255, 255, 255, 0), 110));

		add(backgroundLabel);
		backgroundLabel.setLayout(new BorderLayout());

		mainPanel = new JPanel();
		mainPanel.setOpaque(false);

		backgroundLabel.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new GridLayout(5, 1, 10, 10));


		mainPanel.add(padding);
		mainPanel.add(header);
		mainPanel.add(bwTF);
		mainPanel.add(applyButton);
		mainPanel.add(returnButton);
		
		header.setForeground(Color.BLACK);
		header.setFont(new Font("Dialog", Font.BOLD, 20));

		bwTF.setBorder(new LineBorder(Color.BLACK, 1));
		bwTF.setFont(new Font("Dialog", Font.BOLD, 14));
		bwTF.setHorizontalAlignment(SwingConstants.CENTER);

		applyButton.setBackground(Color.DARK_GRAY);
		returnButton.setBackground(Color.DARK_GRAY);
		
		applyButton.setFont(new Font("Dialog", Font.BOLD, 14));
		returnButton.setFont(new Font("Dialog", Font.BOLD, 14));
		
		applyButton.setForeground(Color.WHITE);
		returnButton.setForeground(Color.WHITE);
		
		applyButton.setBorder(new LineBorder(Color.WHITE, 1));
		returnButton.setBorder(new LineBorder(Color.WHITE, 1));
		
		applyButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				try {

					String myDriver = "com.mysql.cj.jdbc.Driver";
					String myUrl = "jdbc:mysql://phpmyadmin47.lh.pl:3306/serwer58262_Kcal?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&characterEncoding=utf-8";
					Class.forName(myDriver);

					Connection conn = DriverManager.getConnection(myUrl, "serwer58262_Kcal", "kcal00#");

					Statement st = conn.createStatement();
					
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					Date date = new Date();
					
					st.executeUpdate(
							"INSERT INTO usersweight VALUES (" + LoginWindow.UserID + ", '" + dateFormat.format(date) + "', " + Double.parseDouble(bwTF.getText()) + ")");
					
					conn.close();
					dispose();
					
					JOptionPane.showMessageDialog(null, "BW is successfully added to the database" , "Success!", JOptionPane.INFORMATION_MESSAGE,
							null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE,
							deleteImage);
				}
				
			}

		});
		
		bwTF.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent evt) {


			}

			@Override
			public void keyTyped(KeyEvent evt) {

				char c = evt.getKeyChar();

				String weightString = bwTF.getText();
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

	public void NewWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBW window = new AddBW();
					window.setVisible(true);
					window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
