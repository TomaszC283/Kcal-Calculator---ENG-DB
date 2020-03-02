package TomaszC283.main.java.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

public class Statistics {

	private JFrame dpFrame;
	
	final JComboBox<String> searchCB = new JComboBox<>();
	
	ImageIcon deleteImage = new ImageIcon("src/TomaszC283/main/java/resources/delete.png");
	
	public void NewWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Statistics window = new Statistics();
					window.dpFrame.setVisible(true);
					dpFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Statistics() {

		dpFrame = new JFrame("Adding new Product to List");
		dpFrame.setSize(1300, 700);
		dpFrame.setLocation(100, 100);

		RefreshComboBox();
		
		// East panel - for graphs show with 4 checks box - Carbo/Whey/Fats/Kcal
		// Top Panel - searchings conditions ( JComboBox with options -> Date from ->
		// to, CheckBox to get only single Date or by ProductName )
		// soring Menu ( Order By and ASC / DESC )
		// Main Panel - JTable to view Data

		JPanel mainContainer = new JPanel();
		dpFrame.add(mainContainer);
		mainContainer.setLayout(new BorderLayout());
		mainContainer.setBackground(new Color(245, 255, 255));

		// Top panel Components
		JLabel mainLabel = new JLabel("Data display");
		mainLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		mainLabel.setForeground(Color.RED);

		JPanel topPanel = new JPanel();
		JPanel subTopPanel = new JPanel();

		JButton buttonSearch = new JButton("Search Data");
		
		JLabel searchLabel = new JLabel("Search conditions");
		JComboBox<String> searchConditionsCB = new JComboBox<>();

		final JDateChooser dateChooserFrom = new JDateChooser();
		final JDateChooser dateChooserTo = new JDateChooser();
		JLabel setDateFrom = new JLabel("Set Date FROM");
		JLabel setDateTo = new JLabel("Set Date TO");


		JLabel searchBy = new JLabel("Search by Product Name");

		JComboBox<String> setOrderByCB = new JComboBox<>();
		JLabel setOrderBy = new JLabel("Display data Order");

		// final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		mainContainer.add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout());

		JPanel mainLabelSubPanel = new JPanel();
		topPanel.add(mainLabelSubPanel, BorderLayout.NORTH);
		mainLabelSubPanel.add(mainLabel);
		topPanel.add(subTopPanel, BorderLayout.CENTER);
		subTopPanel.setLayout(new GridLayout(1, 6));

		subTopPanel.add(buttonSearch);
		
		JPanel subTopPanel1 = new JPanel();
		subTopPanel1.setLayout(new GridLayout(2, 1));
		subTopPanel.add(subTopPanel1);
		subTopPanel1.add(searchLabel);
		subTopPanel1.add(searchConditionsCB);

		JPanel subTopPanel2 = new JPanel();
		subTopPanel2.setLayout(new GridLayout(2, 1));
		subTopPanel.add(subTopPanel2);
		subTopPanel2.add(setDateFrom);
		subTopPanel2.add(dateChooserFrom);

		JPanel subTopPanel3 = new JPanel();
		subTopPanel3.setLayout(new GridLayout(2, 1));
		subTopPanel.add(subTopPanel3);
		subTopPanel3.add(setDateTo);
		subTopPanel3.add(dateChooserTo);

		JPanel subTopPanel4 = new JPanel();
		subTopPanel4.setLayout(new GridLayout(2, 1));
		subTopPanel.add(subTopPanel4);
		subTopPanel4.add(searchBy);
		subTopPanel4.add(searchCB);

		JPanel subTopPanel5 = new JPanel();
		subTopPanel5.setLayout(new GridLayout(2, 1));
		subTopPanel.add(subTopPanel5);
		subTopPanel5.add(setOrderBy);
		subTopPanel5.add(setOrderByCB);

		// Backgrounds & Borders
		mainLabelSubPanel.setBackground(new Color(255, 229, 255));
		topPanel.setBackground(new Color(245, 255, 255));
		subTopPanel.setBackground(new Color(245, 255, 255));
		subTopPanel1.setBackground(new Color(245, 255, 255));
		subTopPanel2.setBackground(new Color(245, 255, 255));
		subTopPanel3.setBackground(new Color(245, 255, 255));
		subTopPanel4.setBackground(new Color(245, 255, 255));
		subTopPanel5.setBackground(new Color(245, 255, 255));

		mainLabelSubPanel.setBorder(new LineBorder(Color.BLUE, 4));
		topPanel.setBorder(new LineBorder(new Color(245, 255, 255), 8));
		subTopPanel.setBorder(new LineBorder(new Color(245, 255, 255), 8));
		subTopPanel1.setBorder(new LineBorder(new Color(245, 255, 255), 8));
		subTopPanel2.setBorder(new LineBorder(new Color(245, 255, 255), 8));
		subTopPanel3.setBorder(new LineBorder(new Color(245, 255, 255), 8));
		subTopPanel4.setBorder(new LineBorder(new Color(245, 255, 255), 8));
		subTopPanel5.setBorder(new LineBorder(new Color(245, 255, 255), 8));

		searchConditionsCB.setBackground(new Color(255, 229, 255));
		setOrderByCB.setBackground(new Color(255, 229, 255));
		searchCB.setBackground(new Color(255, 229, 255));
		buttonSearch.setBackground(new Color(255, 229, 255));

		// DateChooser
		dateChooserFrom.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dateChooserFrom.setBounds(10, 10, 60, 10);
		dateChooserFrom.setDateFormatString("dd/MM/yyyy");

		dateChooserTo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dateChooserTo.setBounds(10, 10, 60, 10);
		dateChooserTo.setDateFormatString("dd/MM/yyyy");

		// ComboBox
		searchConditionsCB.addItem("Search by entering a single date");
		searchConditionsCB.addItem("Search by entering Date from - to");
		searchConditionsCB.addItem("Search by Product Name");
		
		// Main panel to review data
		JPanel centerPanel = new JPanel();
		mainContainer.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout());
		centerPanel.setBackground(new Color(245, 255, 255));

		// East Panel to review statistics and graphs
		JPanel eastPanel = new JPanel();
		mainContainer.add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new BorderLayout());
		eastPanel.setBackground(new Color(245, 255, 255));

	}
	
	private void RefreshComboBox() {
		try {

			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://localhost:3306/safanlamel";
			Class.forName(myDriver);

			Connection conn = DriverManager.getConnection(myUrl, "root", "lamel123");

			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM safanlamel.Products");

			searchCB.removeAllItems();

			while (rs.next()) {
				String name = rs.getString("ProductName");
				searchCB.addItem(name);
			}
			conn.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE,
					deleteImage);
		}
	}
}
