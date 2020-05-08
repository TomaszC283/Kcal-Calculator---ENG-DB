package TomaszC283.main.java.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import TomaszC283.main.java.Products;

public class AddProductWindow extends JFrame {

	private JFrame dpFrame;
	Products products = new Products();
	private boolean AddSuccess;
	private JLabel backgroundLabel;
	
	// Icons
	ImageIcon deleteImage = new ImageIcon("src/TomaszC283/main/java/resources/delete.png");
	ImageIcon background = new ImageIcon("src/TomaszC283/main/java/resources/add_product.jpg");
	
	public void NewWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddProductWindow window = new AddProductWindow();
					window.dpFrame.setVisible(true);
					dpFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	AddProductWindow() {
		
		dpFrame = new JFrame("Add new product to database");
		dpFrame.setSize(550, 434);
		dpFrame.setResizable(false);
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - dpFrame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - dpFrame.getHeight()) / 2);
	    dpFrame.setLocation(x, y);
		
		
		backgroundLabel = new JLabel("", background, JLabel.CENTER);
		backgroundLabel.setBounds(0,0,550,434);
		backgroundLabel.setBorder(new LineBorder(new Color(255,255,255,0), 80));
		
		dpFrame.add(backgroundLabel);
		
		JLabel ProductName = new JLabel("  Product name : ");
		JLabel macroLabel = new JLabel("   Enter the amount of macroelements per 100g : ");
		JLabel CarboMacro = new JLabel("  Carbohybrates : ");
		JLabel WheyMacro = new JLabel("      Proteins : ");
		JLabel FatsMacro = new JLabel("         Fats : ");

		final JTextField ProductNameTF = new JTextField(16);
		final JTextField CarboTF = new JTextField(10);
		final JTextField WheyTF = new JTextField(10);
		final JTextField FatsTF = new JTextField(10);

		ProductNameTF.setBackground(Color.WHITE);
		CarboTF.setBackground(Color.WHITE);
		WheyTF.setBackground(Color.WHITE);
		FatsTF.setBackground(Color.WHITE);
		
		ProductNameTF.setForeground(Color.DARK_GRAY);
		CarboTF.setForeground(Color.DARK_GRAY);
		WheyTF.setForeground(Color.DARK_GRAY);
		FatsTF.setForeground(Color.DARK_GRAY);

		CarboTF.setText("0");
		WheyTF.setText("0");
		FatsTF.setText("0");

		ProductNameTF.setBackground(Color.WHITE);
		CarboTF.setBackground(Color.WHITE);
		WheyTF.setBackground(Color.WHITE);
		FatsTF.setBackground(Color.WHITE);
		
		ProductNameTF.setBorder(new LineBorder(Color.BLACK, 1));
		CarboTF.setBorder(new LineBorder(Color.BLACK, 1));
		WheyTF.setBorder(new LineBorder(Color.BLACK, 1));
		FatsTF.setBorder(new LineBorder(Color.BLACK, 1));

		ProductNameTF.setHorizontalAlignment(JTextField.CENTER);
		CarboTF.setHorizontalAlignment(JTextField.CENTER);
		WheyTF.setHorizontalAlignment(JTextField.CENTER);
		FatsTF.setHorizontalAlignment(JTextField.CENTER);

		JPanel AuxTopPanel2 = new JPanel();
		JPanel AuxTopPanel3 = new JPanel();
		JPanel AuxTopPanel4 = new JPanel();
		JPanel AuxTopPanel4a = new JPanel();
		JPanel AuxTopPanel4b = new JPanel();
		JPanel AuxTopPanel4c = new JPanel();
		JPanel AuxTopPanel5 = new JPanel();

		backgroundLabel.setLayout(new GridLayout(4, 1, 2, 5));
		AuxTopPanel4a.setLayout(new GridLayout(2, 1, 0, 5));
		AuxTopPanel4b.setLayout(new GridLayout(2, 1, 0, 5));
		AuxTopPanel4c.setLayout(new GridLayout(2, 1, 0, 5));
		AuxTopPanel5.setLayout(new GridLayout(1, 2, 10,10));
		AuxTopPanel5.setBorder(new LineBorder(new Color(255,255,255,0), 10));
		
		AuxTopPanel2.setOpaque(false);
		AuxTopPanel3.setOpaque(false);
		AuxTopPanel4.setOpaque(false);
		AuxTopPanel4a.setOpaque(false);
		AuxTopPanel4b.setOpaque(false);
		AuxTopPanel4c.setOpaque(false);
		AuxTopPanel5.setOpaque(false);

		JButton AddButton = new JButton("Add new product");
		JButton returnButton = new JButton(" Return ");
		
		AddButton.setBackground(Color.DARK_GRAY);
		returnButton.setBackground(Color.DARK_GRAY);
		
		AddButton.setForeground(Color.WHITE);
		returnButton.setForeground(Color.WHITE);
		
		AddButton.setBorder(new LineBorder(Color.WHITE, 1));
		returnButton.setBorder(new LineBorder(Color.WHITE, 1));
		
		AddButton.setFont(new Font("Dialog", Font.BOLD, 14));
		returnButton.setFont(new Font("Dialog", Font.BOLD, 14));

		ProductName.setFont(new Font("Dialog", Font.BOLD | Font.BOLD, 17));
		macroLabel.setFont(new Font("Dialog", Font.BOLD | Font.BOLD, 17));
		CarboMacro.setFont(new Font("Dialog", Font.BOLD, 16));
		WheyMacro.setFont(new Font("Dialog", Font.BOLD, 16));
		FatsMacro.setFont(new Font("Dialog", Font.BOLD, 16));

		ProductName.setForeground(Color.BLACK);
		macroLabel.setForeground(Color.BLACK);
		CarboMacro.setForeground(Color.BLACK);
		WheyMacro.setForeground(Color.BLACK);
		FatsMacro.setForeground(Color.BLACK);

		backgroundLabel.add(AuxTopPanel2);
		backgroundLabel.add(AuxTopPanel3);
		backgroundLabel.add(AuxTopPanel4);
		backgroundLabel.add(AuxTopPanel5);
		
		AuxTopPanel2.add(ProductName);
		AuxTopPanel2.add(ProductNameTF);
		AuxTopPanel3.add(macroLabel);
		AuxTopPanel4a.add(CarboMacro);
		AuxTopPanel4a.add(CarboTF);
		AuxTopPanel4b.add(WheyMacro);
		AuxTopPanel4b.add(WheyTF);
		AuxTopPanel4c.add(FatsMacro);
		AuxTopPanel4c.add(FatsTF);
		AuxTopPanel4.add(AuxTopPanel4a);
		AuxTopPanel4.add(AuxTopPanel4b);
		AuxTopPanel4.add(AuxTopPanel4c);
		AuxTopPanel5.add(AddButton);
		AuxTopPanel5.add(returnButton);
		
		// Actions

		ProductNameTF.addFocusListener(new FocusListener() {
			
			public void focusGained(FocusEvent e) {
				((JTextField) e.getSource()).selectAll();
			}
			public void focusLost(FocusEvent e) {
			}
		});

		CarboTF.addFocusListener(new FocusListener() {
			
			public void focusGained(FocusEvent e) {
				((JTextField) e.getSource()).selectAll();
			}

			public void focusLost(FocusEvent e) {
			}
		});

		WheyTF.addFocusListener(new FocusListener() {
			
			public void focusGained(FocusEvent e) {
				((JTextField) e.getSource()).selectAll();
			}
			public void focusLost(FocusEvent e) {
			}
		});

		FatsTF.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				((JTextField) e.getSource()).selectAll();
			}

			public void focusLost(FocusEvent e) {
			}
		});

		CarboTF.addKeyListener(new KeyListener() {
			
			public void keyPressed(KeyEvent e) {

			}
			public void keyReleased(KeyEvent e) {

			}
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
						|| (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_COMMA))) {
					evt.consume();
				}
				if (c == KeyEvent.VK_COMMA) {
					evt.setKeyChar((char) KeyEvent.VK_PERIOD);
				}
			}
		});

		WheyTF.addKeyListener(new KeyListener() {
			
			public void keyPressed(KeyEvent e) {

			}
			public void keyReleased(KeyEvent e) {

			}
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
						|| (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_COMMA))) {
					evt.consume();
				}
				if (c == KeyEvent.VK_COMMA) {
					evt.setKeyChar((char) KeyEvent.VK_PERIOD);
				}
			}
		});

		FatsTF.addKeyListener(new KeyListener() {
			
			public void keyPressed(KeyEvent e) {

			}
			public void keyReleased(KeyEvent e) {

			}
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
						|| (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_COMMA))) {
					evt.consume();
				}
				if (c == KeyEvent.VK_COMMA) {
					evt.setKeyChar((char) KeyEvent.VK_PERIOD);
				}
			}
		});
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				products.setProductName(ProductNameTF.getText());
				products.setProductCarbo(Double.parseDouble(CarboTF.getText()));
				products.setProductWhey(Double.parseDouble(WheyTF.getText()));
				products.setProductFats(Double.parseDouble(FatsTF.getText()));
				
				if (!products.getProductName().equals("Product Name"))
				{
				if (products.getProductCarbo() == 0 || products.getProductWhey() == 0 || products.getProductFats() == 0)	
				{
					int decision = JOptionPane.showConfirmDialog(null,
							"    Are you sure, that one of Macroelements equals 0 ?", "Warning",
							JOptionPane.YES_NO_OPTION);
					if (decision == 0) {
						AddProductToDB();
					}
				}
				else
				{
					AddProductToDB();
				}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You forgot to enter valid product name!", "Wrong Products name!",
							JOptionPane.INFORMATION_MESSAGE, deleteImage);
				}
				
				if (AddSuccess == true)
				{
					ProductNameTF.setText("Product Name");
					CarboTF.setText("0");
					WheyTF.setText("0");
					FatsTF.setText("0");
					AddSuccess = false;
					MainWindow.RefreshComboBox();
				}
			}
		});
		
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dpFrame.dispose();
			}
		});
	}
	
	private void AddProductToDB()
	{
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://phpmyadmin47.lh.pl:3306/serwer58262_Kcal?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&characterEncoding=utf-8";
	      Class.forName(myDriver);
	      
	      Connection conn = DriverManager.getConnection(myUrl, "serwer58262_Kcal", "kcal00#");
	      
	      Statement st = conn.createStatement();
	      
	      String ValuesSTR = products.getProductName() + "' , " + products.getProductCarbo() + ", " + products.getProductWhey() +", " + products.getProductFats();

	      st.executeUpdate("INSERT INTO products (ProductName, ProductCarbo, ProductWhey, ProductFats)"
	          + " VALUES ('" + ValuesSTR + ")");
	      
	      conn.close(); 
	    }
			    
	    catch (Exception e)
	    {
	    	JOptionPane.showMessageDialog(null, e.getMessage(), "Error!",
			JOptionPane.INFORMATION_MESSAGE, deleteImage);
	    }
			    
		finally 
		{
			AddSuccess = true;    	
		}
	}
	
}
