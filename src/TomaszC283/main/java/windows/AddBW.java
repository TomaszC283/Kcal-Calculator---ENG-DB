package TomaszC283.main.java.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class AddBW extends JFrame {
	
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
	
	AddBW() {
		super("Fitness Calculator - Add Today's BW");
		setSize(550, 434);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) (((dimension.getWidth() - 1147) / 2)+100);
		int y = (int) (((dimension.getHeight() - 700) / 2)+100);
		setLocation(x, y);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);

		// background
		backgroundLabel = new JLabel("", background, JLabel.CENTER);
		backgroundLabel.setBounds(0, 0, 550, 434);
		backgroundLabel.setBorder(new LineBorder(new Color(255,255,255,0),110));

		add(backgroundLabel);
		backgroundLabel.setLayout(new BorderLayout());
		
		mainPanel = new JPanel();
		mainPanel.setOpaque(false);
		
		backgroundLabel.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new GridLayout(3, 1, 10, 10));
		
		JLabel header = new JLabel("Enter your today's body weight :");
		JTextField bwTF = new JTextField("30");
		JButton applyButton = new JButton("Apply BW");
		
		mainPanel.add(header);
		mainPanel.add(bwTF);
		mainPanel.add(applyButton);
		
		header.setForeground(Color.BLACK);
		header.setFont(new Font("Dialog", Font.BOLD, 20));
		
		bwTF.setBorder(new LineBorder(Color.BLACK, 1));
		
		applyButton.setBackground(Color.DARK_GRAY);
		applyButton.setFont(new Font("Dialog", Font.BOLD, 14));
		applyButton.setForeground(Color.WHITE);
		
		
		
	}
	
	public void NewWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBW window = new AddBW();
					window.setVisible(true);
					window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
