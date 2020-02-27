package TomaszC283.main.java.windows;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
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

public class AddProductWindow {

	private JFrame dpFrame;
	Products products = new Products();
	private boolean AddSuccess;
	
	// Icons
	ImageIcon deleteImage = new ImageIcon("src/TomaszC283/main/java/resources/delete.png");
	ImageIcon plusImage = new ImageIcon("src/TomaszC283/main/java/resources/plus.png");
	
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
		
		dpFrame = new JFrame("Adding new Product to List");
		dpFrame.setBounds(200, 200, 400, 230);
		
		JLabel ProductName = new JLabel("  Product name : ");
		JLabel macroLabel = new JLabel("   Enter the amount of macroelements per 100g : ");
		JLabel CarboMacro = new JLabel("  Carbohybrates : ");
		JLabel WheyMacro = new JLabel("          Proteins : ");
		JLabel FatsMacro = new JLabel("               Fats : ");

		final JTextField ProductNameTF = new JTextField(16);
		final JTextField CarboTF = new JTextField(10);
		final JTextField WheyTF = new JTextField(10);
		final JTextField FatsTF = new JTextField(10);

		ProductNameTF.setForeground(Color.RED);
		CarboTF.setForeground(Color.RED);
		WheyTF.setForeground(Color.RED);
		FatsTF.setForeground(Color.RED);

		CarboTF.setText("0");
		WheyTF.setText("0");
		FatsTF.setText("0");

		ProductNameTF.setBackground(new Color(204, 255, 255));
		CarboTF.setBackground(new Color(204, 255, 255));
		WheyTF.setBackground(new Color(204, 255, 255));
		FatsTF.setBackground(new Color(204, 255, 255));

		ProductNameTF.setBorder(new LineBorder(new Color(48, 213, 200), 2));
		CarboTF.setBorder(new LineBorder(new Color(48, 213, 200), 2));
		WheyTF.setBorder(new LineBorder(new Color(48, 213, 200), 2));
		FatsTF.setBorder(new LineBorder(new Color(48, 213, 200), 2));

		ProductNameTF.setHorizontalAlignment(JTextField.CENTER);
		CarboTF.setHorizontalAlignment(JTextField.CENTER);
		WheyTF.setHorizontalAlignment(JTextField.CENTER);
		FatsTF.setHorizontalAlignment(JTextField.CENTER);

		JPanel AuxTopPanel1 = new JPanel();
		JPanel AuxTopPanel2 = new JPanel();
		JPanel AuxTopPanel3 = new JPanel();
		JPanel AuxTopPanel4 = new JPanel();
		JPanel AuxTopPanel4a = new JPanel();
		JPanel AuxTopPanel4b = new JPanel();
		JPanel AuxTopPanel4c = new JPanel();
		JPanel AuxTopPanel5 = new JPanel();

		AuxTopPanel1.setLayout(new GridLayout(4, 1));
		AuxTopPanel4a.setLayout(new GridLayout(2, 1));
		AuxTopPanel4b.setLayout(new GridLayout(2, 1));
		AuxTopPanel4c.setLayout(new GridLayout(2, 1));

		AuxTopPanel1.setBackground(new Color(245, 255, 255));
		AuxTopPanel2.setBackground(new Color(245, 255, 255));
		AuxTopPanel3.setBackground(new Color(245, 255, 255));
		AuxTopPanel4.setBackground(new Color(245, 255, 255));
		AuxTopPanel4a.setBackground(new Color(245, 255, 255));
		AuxTopPanel4b.setBackground(new Color(245, 255, 255));
		AuxTopPanel4c.setBackground(new Color(245, 255, 255));
		AuxTopPanel5.setBackground(new Color(245, 255, 255));

		JButton AddButton = new JButton("   Add Product to Database      ", plusImage);
		AddButton.setBackground(new Color(255, 229, 255));
		AddButton.setBorder(new LineBorder(new Color(48, 213, 200), 2));

		AuxTopPanel1.setBorder(new LineBorder(new Color(48, 213, 200), 3));

		ProductName.setFont(new Font("Helvetica", Font.BOLD | Font.BOLD, 13));
		macroLabel.setFont(new Font("Helvetica", Font.BOLD | Font.BOLD, 13));
		CarboMacro.setFont(new Font("Helvetica", Font.BOLD, 12));
		WheyMacro.setFont(new Font("Helvetica", Font.BOLD, 12));
		FatsMacro.setFont(new Font("Helvetica", Font.BOLD, 12));

		ProductName.setForeground(Color.RED);
		macroLabel.setForeground(Color.RED);
		CarboMacro.setForeground(Color.RED);
		WheyMacro.setForeground(Color.RED);
		FatsMacro.setForeground(Color.RED);

		dpFrame.add(AuxTopPanel1);

		AuxTopPanel1.add(AuxTopPanel2);
		AuxTopPanel1.add(AuxTopPanel3);
		AuxTopPanel1.add(AuxTopPanel4);
		AuxTopPanel1.add(AuxTopPanel5);
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
				}
			}
		});
	}
	
	private void AddProductToDB()
	{
		try
	    {
	      String myDriver = "org.gjt.mm.mysql.Driver";
	      String myUrl = "jdbc:mysql://localhost:3306/safanlamel";
	      Class.forName(myDriver);
	      
	      Connection conn = DriverManager.getConnection(myUrl, "root", "lamel123");
	      
	      Statement st = conn.createStatement();
	      
	      String ValuesSTR = products.getProductName() + "' , " + products.getProductCarbo() + ", " + products.getProductWhey() +", " + products.getProductFats();

	      st.executeUpdate("INSERT INTO Products (ProductName, ProductCarbo, ProductWhey, ProductFats)"
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
