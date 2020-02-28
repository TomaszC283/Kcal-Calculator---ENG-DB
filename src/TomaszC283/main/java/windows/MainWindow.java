package TomaszC283.main.java.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
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
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import TomaszC283.main.java.DailyProducts;
import TomaszC283.main.java.Products;
import TomaszC283.main.java.windows.AddProductWindow;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	// GUI

	private static JComboBox<String> comboBox = new JComboBox<>();
	JComboBox<Integer> comboBoxNr = new JComboBox<>();

	private static JSlider KcalSlider = new JSlider(0, 3000, 0);
	private static JTextField TotalCarboTF = new JTextField(13);
	private static JTextField TotalWheyTF = new JTextField(13);
	private static JTextField TotalFatsTF = new JTextField(13);
	private static JTextField TotalKcalTF = new JTextField(13);

	final JTextField WeightTF = new JTextField(9);
	final JTextField CarboTF = new JTextField(9);
	final JTextField WheyTF = new JTextField(9);
	final JTextField FatsTF = new JTextField(9);
	final JTextField KcalTF = new JTextField(9);

	// Icons
	ImageIcon deleteImage = new ImageIcon("src/TomaszC283/main/java/resources/delete.png");
	ImageIcon carrotImage = new ImageIcon("src/TomaszC283/main/java/resources/carrot.png");
	ImageIcon plusImage = new ImageIcon("src/TomaszC283/main/java/resources/plus.png");
	ImageIcon cancelImage = new ImageIcon("src/TomaszC283/main/java/resources/cancel.png");
	ImageIcon upsImage = new ImageIcon("src/TomaszC283/main/java/resources/ups.png");
	ImageIcon removeImage = new ImageIcon("src/TomaszC283/main/java/resources/remove.png");

	// Date today
	SimpleDateFormat date_sdf = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	String DateToday = date_sdf.format(date);

	// Products Class
	private DailyProducts dailyProducts = new DailyProducts();
	private Products products = new Products();
	private String DateFromDB;
	final DefaultTableModel model = new DefaultTableModel();

	// Variables
	private int NoOfMeal;
	private double Weight;
	private double Carbo;
	private double Whey;
	private double Fats;
	double tempValue;

	public MainWindow() {

		super("Fitness Calculator");
		setSize(1100, 600);
		setLocation(100, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// Update combobox
		RefreshComboBox();

		// Create JTable
		String[] columnNames = { "Product Name", "Meal", "Weight", "Carbohydrates", "Proteins", "Fats", "KCalories" };
		
		model.setColumnIdentifiers(columnNames);
		model.fireTableDataChanged();
		
		final JTable tableList = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );

		
		tableList.setModel(model);
		tableList.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableList.setFillsViewportHeight(true);
		
		JScrollPane scroll = new JScrollPane(tableList);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		tableList.setBackground(new Color(245, 255, 255));
		tableList.setGridColor(Color.BLUE);
		tableList.setForeground(Color.RED);
		tableList.setFont(new Font("Serif", Font.PLAIN, 13));
		
		CheckDailyList();
		
		JTableHeader header = tableList.getTableHeader();
		header.setBackground(new Color(255, 229, 255));
		header.setForeground(Color.BLUE);
		header.setFont(new Font("Serif", Font.BOLD, 13));
		
		for ( int i = 0; i < model.getColumnCount(); i++)
		{
			tableList.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
		}
		
		// MainPanel

		Container mainContainer = getContentPane();
		mainContainer.setLayout(new BorderLayout(6, 3));
		mainContainer.setBackground(new Color(245, 255, 255));

		// Buttons

		JButton ButtonAddNewProd = new JButton("     Add new product     ", carrotImage);
		JButton ButtonClearList = new JButton("   Clear list   ", cancelImage);
		JButton ButtonAddProd = new JButton("    Add       ", plusImage);
		JButton ButtonDeleteProd = new JButton(" Remove Product from Database  ", deleteImage);
		JButton ButtonRemoveProd = new JButton("    Remove Meal from list  ", removeImage);

		ButtonAddNewProd.setBorder(new LineBorder(new Color(48, 213, 200), 2));
		ButtonClearList.setBorder(new LineBorder(new Color(48, 213, 200), 2));
		ButtonAddProd.setBorder(new LineBorder(new Color(48, 213, 200), 2));
		ButtonDeleteProd.setBorder(new LineBorder(new Color(48, 213, 200), 2));
		ButtonRemoveProd.setBorder(new LineBorder(new Color(48, 213, 200), 2));

		ButtonAddNewProd.setBackground(new Color(255, 229, 255));
		ButtonClearList.setBackground(new Color(255, 229, 255));
		ButtonAddProd.setBackground(new Color(255, 229, 255));
		ButtonDeleteProd.setBackground(new Color(255, 229, 255));
		ButtonRemoveProd.setBackground(new Color(255, 229, 255));

		// Top Panel

		JPanel AuxTopPanel1 = new JPanel();
		JPanel AuxTopPanel2 = new JPanel();
		JPanel AuxTopPanel3 = new JPanel();
		JPanel AuxTopPanel4 = new JPanel();
		JPanel AuxTopPanel5 = new JPanel();
		JPanel AuxTopPanel6 = new JPanel();
		JPanel AuxTopPanel7 = new JPanel();

		WeightTF.setBorder(new LineBorder(new Color(48, 213, 200), 2));
		CarboTF.setBorder(new LineBorder(new Color(48, 213, 200), 2));
		WheyTF.setBorder(new LineBorder(new Color(48, 213, 200), 2));
		FatsTF.setBorder(new LineBorder(new Color(48, 213, 200), 2));
		KcalTF.setBorder(new LineBorder(new Color(48, 213, 200), 2));

		WeightTF.setText("100");

		WeightTF.setHorizontalAlignment(JTextField.CENTER);
		CarboTF.setHorizontalAlignment(JTextField.CENTER);
		WheyTF.setHorizontalAlignment(JTextField.CENTER);
		FatsTF.setHorizontalAlignment(JTextField.CENTER);
		KcalTF.setHorizontalAlignment(JTextField.CENTER);

		CarboTF.setEnabled(false);
		WheyTF.setEnabled(false);
		FatsTF.setEnabled(false);
		KcalTF.setEnabled(false);

		WeightTF.setForeground(Color.RED);
		CarboTF.setDisabledTextColor(Color.RED);
		WheyTF.setDisabledTextColor(Color.RED);
		FatsTF.setDisabledTextColor(Color.RED);
		KcalTF.setDisabledTextColor(Color.RED);

		WeightTF.setBackground(new Color(204, 255, 255));
		CarboTF.setBackground(new Color(204, 255, 255));
		WheyTF.setBackground(new Color(204, 255, 255));
		FatsTF.setBackground(new Color(204, 255, 255));
		KcalTF.setBackground(new Color(204, 255, 255));

		AuxTopPanel1.setBackground(new Color(245, 255, 255));
		AuxTopPanel2.setBackground(new Color(245, 255, 255));
		AuxTopPanel3.setBackground(new Color(245, 255, 255));
		AuxTopPanel4.setBackground(new Color(245, 255, 255));
		AuxTopPanel5.setBackground(new Color(245, 255, 255));
		AuxTopPanel6.setBackground(new Color(245, 255, 255));
		AuxTopPanel7.setBackground(new Color(245, 255, 255));

		AuxTopPanel1.setLayout(new GridLayout(2, 1));
		AuxTopPanel2.setLayout(new GridLayout(2, 1));
		AuxTopPanel3.setLayout(new GridLayout(2, 1));
		AuxTopPanel4.setLayout(new GridLayout(2, 1));
		AuxTopPanel5.setLayout(new GridLayout(2, 1));
		AuxTopPanel7.setLayout(new GridLayout(2, 1));

		JLabel Tittle1 = new JLabel("   Product weight : ");
		JLabel Tittle2 = new JLabel("   Carbohybrates : ");
		JLabel Tittle3 = new JLabel("         Proteins :");
		JLabel Tittle4 = new JLabel("            Fats :");
		JLabel Tittle5 = new JLabel("        Calories :");
		JLabel Tittle6 = new JLabel("          Choose product from list : ");
		JLabel TittleNr = new JLabel("  No of Meal:  ");

		Tittle1.setFont(new Font("Helvetica", Font.BOLD, 11));
		Tittle2.setFont(new Font("Helvetica", Font.BOLD, 11));
		Tittle3.setFont(new Font("Helvetica", Font.BOLD, 11));
		Tittle4.setFont(new Font("Helvetica", Font.BOLD, 11));
		Tittle5.setFont(new Font("Helvetica", Font.BOLD, 11));
		Tittle6.setFont(new Font("Helvetica", Font.BOLD, 12));
		TittleNr.setFont(new Font("Helvetica", Font.BOLD, 12));

		Tittle1.setForeground(Color.RED);
		Tittle2.setForeground(Color.RED);
		Tittle3.setForeground(Color.RED);
		Tittle4.setForeground(Color.RED);
		Tittle5.setForeground(Color.RED);
		Tittle6.setForeground(Color.RED);
		TittleNr.setForeground(Color.RED);

		comboBox.setBackground(new Color(204, 255, 255));
		comboBox.setForeground(Color.RED);

		Integer[] MealNumber = { 1, 2, 3, 4, 5 };
		comboBoxNr.setModel(new DefaultComboBoxModel<Integer>(MealNumber));
		comboBoxNr.setForeground(Color.RED);
		comboBoxNr.setBackground(new Color(204, 255, 255));

		// Add products to list Area

		AuxTopPanel1.add(Tittle1);
		AuxTopPanel1.add(WeightTF);
		AuxTopPanel2.add(Tittle2);
		AuxTopPanel2.add(CarboTF);
		AuxTopPanel3.add(Tittle3);
		AuxTopPanel3.add(WheyTF);
		AuxTopPanel4.add(Tittle4);
		AuxTopPanel4.add(FatsTF);
		AuxTopPanel5.add(Tittle5);
		AuxTopPanel5.add(KcalTF);
		AuxTopPanel6.add(Tittle6);
		AuxTopPanel6.add(comboBox);
		AuxTopPanel6.add(ButtonAddNewProd);
		AuxTopPanel6.add(ButtonDeleteProd);
		AuxTopPanel7.add(TittleNr);
		AuxTopPanel7.add(comboBoxNr);

		JPanel topPanel1 = new JPanel();
		JPanel topPanel2 = new JPanel();
		JPanel topPanel3 = new JPanel();

		topPanel1.setBackground(new Color(245, 255, 255));
		topPanel2.setBackground(new Color(245, 255, 255));
		topPanel3.setBackground(new Color(245, 255, 255));

		topPanel1.setBorder(new LineBorder(new Color(48, 213, 200), 3));
		topPanel1.setLayout(new GridLayout(2, 1));
		comboBox.setBorder(new LineBorder(new Color(48, 213, 200), 2));

		mainContainer.add(topPanel1, BorderLayout.NORTH);
		topPanel1.add(topPanel2);
		topPanel2.add(AuxTopPanel6);
		topPanel1.add(topPanel3);
		topPanel3.add(AuxTopPanel7);
		topPanel3.add(AuxTopPanel1);
		topPanel3.add(AuxTopPanel2);
		topPanel3.add(AuxTopPanel3);
		topPanel3.add(AuxTopPanel4);
		topPanel3.add(AuxTopPanel5);
		topPanel3.add(ButtonAddProd);
		topPanel3.add(ButtonClearList);
		topPanel3.add(ButtonRemoveProd);

		// Middle Panel

		JPanel middlePanel = new JPanel();

		KcalSlider.setMajorTickSpacing(200);
		KcalSlider.setMinorTickSpacing(50);
		KcalSlider.setPaintTicks(true);
		KcalSlider.setPaintLabels(true);
		KcalSlider.setForeground(Color.RED);
		KcalSlider.setBackground(new Color(245, 255, 255));
		KcalSlider.setFont(new Font("Helvetica", Font.ITALIC, 11));

		mainContainer.add(middlePanel);
		middlePanel.setBorder(new LineBorder(new Color(48, 213, 200), 3));
		middlePanel.setLayout(new BorderLayout());
		middlePanel.add(scroll, BorderLayout.CENTER);
		middlePanel.add(KcalSlider, BorderLayout.SOUTH);

		// Bottom Panel

		JPanel bottomPanel1 = new JPanel();
		JPanel bottomPanel2 = new JPanel();
		JPanel bottomPanel3 = new JPanel();
		JPanel panel8 = new JPanel();
		JPanel panel9 = new JPanel();
		JPanel panel10 = new JPanel();
		JPanel panel11 = new JPanel();

		bottomPanel1.setBackground(new Color(245, 255, 255));
		bottomPanel2.setBackground(new Color(245, 255, 255));
		bottomPanel3.setBackground(new Color(245, 255, 255));
		panel8.setBackground(new Color(245, 255, 255));
		panel9.setBackground(new Color(245, 255, 255));
		panel10.setBackground(new Color(245, 255, 255));
		panel11.setBackground(new Color(245, 255, 255));

		panel8.setLayout(new GridLayout(2, 1));
		panel9.setLayout(new GridLayout(2, 1));
		panel10.setLayout(new GridLayout(2, 1));
		panel11.setLayout(new GridLayout(2, 1));
		bottomPanel1.setBorder(new LineBorder(new Color(48, 213, 200), 3));
		mainContainer.add(bottomPanel1, BorderLayout.SOUTH);

		JLabel Tittle7 = new JLabel("  Podsumowanie : ");
		JLabel Tittle8 = new JLabel("          Węglowodany : ");
		JLabel Tittle9 = new JLabel("                   Białko :");
		JLabel Tittle10 = new JLabel("                Tłuszcze :");
		JLabel Tittle11 = new JLabel("                 Kalorie :");

		TotalCarboTF.setBorder(new LineBorder(new Color(48, 213, 200), 2));
		TotalWheyTF.setBorder(new LineBorder(new Color(48, 213, 200), 2));
		TotalFatsTF.setBorder(new LineBorder(new Color(48, 213, 200), 2));
		TotalKcalTF.setBorder(new LineBorder(Color.RED, 2));

		TotalCarboTF.setHorizontalAlignment(JTextField.CENTER);
		TotalWheyTF.setHorizontalAlignment(JTextField.CENTER);
		TotalFatsTF.setHorizontalAlignment(JTextField.CENTER);
		TotalKcalTF.setHorizontalAlignment(JTextField.CENTER);

		TotalCarboTF.setBackground(new Color(204, 255, 255));
		TotalWheyTF.setBackground(new Color(204, 255, 255));
		TotalFatsTF.setBackground(new Color(204, 255, 255));
		TotalKcalTF.setBackground(new Color(204, 255, 255));

		TotalCarboTF.setDisabledTextColor(Color.RED);
		TotalWheyTF.setDisabledTextColor(Color.RED);
		TotalFatsTF.setDisabledTextColor(Color.RED);
		TotalKcalTF.setDisabledTextColor(Color.RED);

		TotalCarboTF.setEnabled(false);
		TotalWheyTF.setEnabled(false);
		TotalFatsTF.setEnabled(false);
		TotalKcalTF.setEnabled(false);

		Tittle7.setFont(new Font("Helvetica", Font.BOLD | Font.ITALIC, 13));
		Tittle8.setFont(new Font("Helvetica", Font.BOLD, 11));
		Tittle9.setFont(new Font("Helvetica", Font.BOLD, 11));
		Tittle10.setFont(new Font("Helvetica", Font.BOLD, 11));
		Tittle11.setFont(new Font("Helvetica", Font.BOLD, 11));

		Tittle7.setForeground(Color.BLUE);
		Tittle8.setForeground(Color.BLUE);
		Tittle9.setForeground(Color.BLUE);
		Tittle10.setForeground(Color.BLUE);
		Tittle11.setForeground(Color.BLUE);

		panel8.add(Tittle8);
		panel8.add(TotalCarboTF);
		panel9.add(Tittle9);
		panel9.add(TotalWheyTF);
		panel10.add(Tittle10);
		panel10.add(TotalFatsTF);
		panel11.add(Tittle11);
		panel11.add(TotalKcalTF);

		bottomPanel1.add(bottomPanel2);
		bottomPanel2.add(Tittle7);
		bottomPanel1.add(bottomPanel3);
		bottomPanel3.add(panel8);
		bottomPanel3.add(panel9);
		bottomPanel3.add(panel10);
		bottomPanel3.add(panel11);

		// East Panel
		JPanel panelEast1 = new JPanel();
		panelEast1.setBorder(new LineBorder(new Color(48, 213, 200), 3));
		panelEast1.setBackground(new Color(245, 255, 255));

		mainContainer.add(panelEast1, BorderLayout.EAST);

		// Actions

		// Removing Row from Product Daily List
		ButtonRemoveProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int decision = JOptionPane.showConfirmDialog(null,
						"    Are you sure to remove selected Row from Today's meal list ?", "Warning",
						JOptionPane.YES_NO_OPTION);
				if (decision == 0) {
					try {
						int row = tableList.getSelectedRow();
						dailyProducts.setDate(DateToday);
						dailyProducts.setProductName(model.getValueAt(row, 0).toString());
						dailyProducts.setID(Integer.parseInt(getDateFromDB("ID", "safanlamel", "DailyProducts", dailyProducts.getProductName())));
						dailyProducts
								.setMealNo(Integer.parseInt(model.getValueAt(row, 1).toString().replace("# ", "")));
						dailyProducts
								.setWeight(Double.parseDouble(model.getValueAt(row, 2).toString().replace(" g", "")));
						dailyProducts
								.setCarbo(Double.parseDouble(model.getValueAt(row, 3).toString()));
						dailyProducts
								.setWhey(Double.parseDouble(model.getValueAt(row, 4).toString()));
						dailyProducts
								.setFats(Double.parseDouble(model.getValueAt(row, 5).toString()));

						String myDriver = "org.gjt.mm.mysql.Driver";
						String myUrl = "jdbc:mysql://localhost:3306/safanlamel";
						Class.forName(myDriver);

						Connection conn = DriverManager.getConnection(myUrl, "root", "lamel123");

						Statement st = conn.createStatement();

						st.executeUpdate(
								"DELETE FROM `DailyProducts` WHERE ID = " + dailyProducts.getID() + " AND `ProductName` = '" + dailyProducts.getProductName()
										+ "' AND `Date` = '" + DateToday + "' AND `Carbo` = " + dailyProducts.getCarbo()
										+ " AND `Whey` = " + dailyProducts.getWhey() + " AND `Fat` = "
										+ dailyProducts.getFats() + " AND `Weight` = " + dailyProducts.getWeight());

						conn.close();

						model.removeRow(row);

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE,
								deleteImage);
					}
				}
			}
		});

		// Removing Product from Products Database
		ButtonDeleteProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int decision = JOptionPane.showConfirmDialog(null,
						"    Are you sure to remove " + comboBox.getSelectedItem().toString() + " from Database ?",
						"Warning", JOptionPane.YES_NO_OPTION);
				if (decision == 0) {
					try {
						products.setProductName(comboBox.getSelectedItem().toString());

						String myDriver = "org.gjt.mm.mysql.Driver";
						String myUrl = "jdbc:mysql://localhost:3306/safanlamel";
						Class.forName(myDriver);

						Connection conn = DriverManager.getConnection(myUrl, "root", "lamel123");

						Statement st = conn.createStatement();

						String ValuesSTR = "'" + products.getProductName() + "'";

						st.executeUpdate("DELETE FROM `Products` WHERE `ProductName` = " + ValuesSTR);

						conn.close();

						JOptionPane.showMessageDialog(null,
								products.getProductName() + " successful deleted from Database", "Success!",
								JOptionPane.INFORMATION_MESSAGE, deleteImage);

						RefreshComboBox();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE,
								deleteImage);
					}
				}
			}
		});

		// Opening New Window - Add new Product to Database
		ButtonAddNewProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddProductWindow nw = new AddProductWindow();
				nw.NewWindow();
			}
		});

		// Clear DailyProduct List
		ButtonClearList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int decision = JOptionPane.showConfirmDialog(null,
						"    Are you sure you want to clear today's meal list? ", "Warning", JOptionPane.YES_NO_OPTION);
				if (decision == 0) {
					try {

						dailyProducts.setDate(DateToday);

						String myDriver = "org.gjt.mm.mysql.Driver";
						String myUrl = "jdbc:mysql://localhost:3306/safanlamel";
						Class.forName(myDriver);

						Connection conn = DriverManager.getConnection(myUrl, "root", "lamel123");

						Statement st = conn.createStatement();

						String ValuesSTR = "'" + dailyProducts.getDate() + "'";

						st.executeUpdate("DELETE FROM `DailyProducts` WHERE `Date` = " + ValuesSTR);

						conn.close();

						model.setRowCount(0);
						TotalCarboTF.setText("");
						TotalWheyTF.setText("");
						TotalFatsTF.setText("");
						TotalKcalTF.setText("");

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE,
								deleteImage);
					}
				}
			}
		});

		// Text Field Focus + Math macroelements
		WeightTF.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				((JTextField) e.getSource()).selectAll();
			}

			@Override
			public void focusLost(FocusEvent e) {
			}
		});

		WeightTF.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
						|| (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_COMMA))) {
					evt.consume();
				}
				if (c == KeyEvent.VK_COMMA) {
					evt.setKeyChar((char) KeyEvent.VK_PERIOD);
				}
				if (WeightTF.getText() != null && !WeightTF.getText().isEmpty() && !WeightTF.getText().equals("")) {
					if (comboBox.getSelectedItem() != null && Double.parseDouble(WeightTF.getText()) != 0) {

						String carbo = getDateFromDB("ProductCarbo", "safanlamel", "Products",
								comboBox.getSelectedItem().toString());
						String whey = getDateFromDB("ProductWhey", "safanlamel", "Products",
								comboBox.getSelectedItem().toString());
						String fats = getDateFromDB("ProductFats", "safanlamel", "Products",
								comboBox.getSelectedItem().toString());

						double carboDouble = Double.parseDouble(carbo);
						double wheyDouble = Double.parseDouble(whey);
						double fatsDouble = Double.parseDouble(fats);

						double divWeight = (Double.parseDouble(WeightTF.getText())) / 100;

						CarboTF.setText(String.format("%.2f", carboDouble * divWeight));
						WheyTF.setText(String.format("%.2f", wheyDouble * divWeight));
						FatsTF.setText(String.format("%.2f", fatsDouble * divWeight));

						dailyProducts.setCarbo(Double.parseDouble(CarboTF.getText().replace(",", ".")));
						dailyProducts.setWhey(Double.parseDouble(WheyTF.getText().replace(",", ".")));
						dailyProducts.setFats(Double.parseDouble(FatsTF.getText().replace(",", ".")));

						KcalTF.setText(String.format("%.2f", dailyProducts.getKcal()));
					}
				}
			}
		});

		// Add Product to DailyProduct List
		ButtonAddProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem() != null) {

					dailyProducts.setProductName(comboBox.getSelectedItem().toString());
					dailyProducts.setCarbo(Double.parseDouble(CarboTF.getText().replace(",", ".")));
					dailyProducts.setWhey(Double.parseDouble(WheyTF.getText().replace(",", ".")));
					dailyProducts.setFats(Double.parseDouble(FatsTF.getText().replace(",", ".")));
					dailyProducts.setWeight(Double.parseDouble(WeightTF.getText().replace(",", ".")));
					dailyProducts.setMealNo(Integer.parseInt(comboBoxNr.getSelectedItem().toString()));

					try {

						String myDriver = "org.gjt.mm.mysql.Driver";
						String myUrl = "jdbc:mysql://localhost:3306/safanlamel";
						Class.forName(myDriver);

						Connection conn = DriverManager.getConnection(myUrl, "root", "lamel123");

						Statement st = conn.createStatement();

						String ValuesSTR = "'" + DateToday + "' , '" + dailyProducts.getProductName() + "' ,"
								+ dailyProducts.getCarbo() + ", " + dailyProducts.getWhey() + ", "
								+ dailyProducts.getFats() + ", " + dailyProducts.getMealNo() + ", "
								+ dailyProducts.getWeight();

						st.executeUpdate(
								"INSERT INTO DailyProducts (Date, ProductName, Carbo, Whey, Fat, MealNo, Weight)"
										+ " VALUES (" + ValuesSTR + ")");

						conn.close();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE,
								deleteImage);
					}

					finally {

						String[] dateCurrentDay = new String[7];

						dateCurrentDay[0] = comboBox.getSelectedItem().toString();

						dateCurrentDay[1] = "# " + String.valueOf(dailyProducts.getMealNo());

						dateCurrentDay[2] = String.valueOf(Math.round(dailyProducts.getWeight())) + " g";

						dateCurrentDay[3] = String.valueOf(dailyProducts.getCarbo());

						dateCurrentDay[4] = String.valueOf(dailyProducts.getWhey());

						dateCurrentDay[5] = String.valueOf(dailyProducts.getFats());

						dateCurrentDay[6] = String
								.valueOf(Math.round(((dailyProducts.getCarbo() + dailyProducts.getWhey()) * 4)
										+ dailyProducts.getFats() * 9));

						model.addRow(dateCurrentDay);

						WeightTF.setText("100");
						CarboTF.setText("");
						WheyTF.setText("");
						FatsTF.setText("");
						KcalTF.setText("");
						RefreshSummaryPanel();
					}
				}
			}
		});
	}

	private String getDateFromDB(String Value, String Table, String ColumnName, String ProductName) {
		try {

			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://localhost:3306/safanlamel";
			Class.forName(myDriver);

			Connection conn = DriverManager.getConnection(myUrl, "root", "lamel123");

			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT * FROM " + Table + "." + ColumnName + " WHERE ProductName = '" + ProductName + "'");

			while (rs.next()) {
				DateFromDB = rs.getString(Value);
			}

			conn.close();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE,
					deleteImage);
		}

		return DateFromDB;
	}

	private void RefreshComboBox() {
		try {

			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://localhost:3306/safanlamel";
			Class.forName(myDriver);

			Connection conn = DriverManager.getConnection(myUrl, "root", "lamel123");

			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM safanlamel.Products");

			comboBox.removeAllItems();

			while (rs.next()) {
				String name = rs.getString("ProductName");
				comboBox.addItem(name);
			}
			conn.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE,
					deleteImage);
		}
	}

	private void RefreshSummaryPanel() {

		for (int count = 0; count < model.getRowCount(); count++) {
			dailyProducts.setCarbo(Double.parseDouble(model.getValueAt(count, 3).toString()));
			tempValue = tempValue + dailyProducts.getCarbo();
		}
		TotalCarboTF.setText((String.format("%.2f", tempValue)).replace(",", "."));

		for (int count = 0; count < model.getRowCount(); count++) {
			dailyProducts.setWhey(Double.parseDouble(model.getValueAt(count, 4).toString()));
			tempValue = tempValue + dailyProducts.getWhey();
		}
		TotalWheyTF.setText((String.format("%.2f", tempValue)).replace(",", "."));

		for (int count = 0; count < model.getRowCount(); count++) {
			dailyProducts.setFats(Double.parseDouble(model.getValueAt(count, 5).toString()));
			tempValue = tempValue + dailyProducts.getFats();
		}
		TotalFatsTF.setText((String.format("%.2f", tempValue)).replace(",", "."));

		for (int count = 0; count < model.getRowCount(); count++) {
			dailyProducts.setKcal(Double.parseDouble(model.getValueAt(count, 6).toString()));
			tempValue = tempValue + dailyProducts.getKcal();
		}
		TotalKcalTF.setText(String.valueOf(Math.round(tempValue)));
	}

	private void CheckDailyList() {
		try {

			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://localhost:3306/safanlamel";
			Class.forName(myDriver);

			Connection conn = DriverManager.getConnection(myUrl, "root", "lamel123");

			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM safanlamel.DailyProducts WHERE `Date` = '" + DateToday + "'");

			while (rs.next()) {

				Object[] dateCurrentDay = new String[7];

				dateCurrentDay[0] = rs.getString("ProductName");

				NoOfMeal = rs.getInt("MealNo");
				dateCurrentDay[1] = "# " + String.valueOf(NoOfMeal);

				Weight = rs.getDouble("Weight");
				dateCurrentDay[2] = String.valueOf(Math.round(Weight)) + " g";

				Carbo = rs.getDouble("Carbo");
				dateCurrentDay[3] = String.valueOf(Carbo);

				Whey = rs.getDouble("Whey");
				dateCurrentDay[4] = String.valueOf(Whey);

				Fats = rs.getDouble("Fat");
				dateCurrentDay[5] = String.valueOf(Fats);

				dateCurrentDay[6] = String.valueOf(Math.round(((Carbo + Whey) * 4) + Fats * 9));

				model.addRow(dateCurrentDay);
			}
			conn.close();
			RefreshSummaryPanel();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE,
					deleteImage);
		}
	}
}
