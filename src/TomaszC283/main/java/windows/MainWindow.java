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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import TomaszC283.main.java.DailyProducts;
import TomaszC283.main.java.Products;
import TomaszC283.main.java.windows.AddProductWindow;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

public class MainWindow extends JFrame {

	// GUI

	private static JComboBox<String> comboBox = new JComboBox<>();

	public static JSlider KcalSlider = new JSlider(0, LoginWindow.UserKcalGoal + 1000, 0);
	private static JTextField TotalCarboTF = new JTextField(13);
	private static JTextField TotalWheyTF = new JTextField(13);
	private static JTextField TotalFatsTF = new JTextField(13);
	private static JTextField TotalKcalTF = new JTextField(13);

	private JTextField WeightTF = new JTextField(9);
	private JTextField CarboTF = new JTextField(9);
	private JTextField WheyTF = new JTextField(9);
	private JTextField FatsTF = new JTextField(9);
	private JTextField KcalTF = new JTextField(9);
	private JTextField filterTF = new JTextField(9);

	// Icons
	static ImageIcon deleteImage = new ImageIcon("src/TomaszC283/main/java/resources/delete.png");
	ImageIcon carrotImage = new ImageIcon("src/TomaszC283/main/java/resources/carrot.png");
	ImageIcon plusImage = new ImageIcon("src/TomaszC283/main/java/resources/plus.png");
	ImageIcon cancelImage = new ImageIcon("src/TomaszC283/main/java/resources/cancel.png");
	ImageIcon upsImage = new ImageIcon("src/TomaszC283/main/java/resources/ups.png");
	ImageIcon removeImage = new ImageIcon("src/TomaszC283/main/java/resources/remove.png");
	ImageIcon statisticsImage = new ImageIcon("src/TomaszC283/main/java/resources/statistics.png");
	ImageIcon background = new ImageIcon("src/TomaszC283/main/java/resources/background.jpg");
	JLabel backgroundLabel;

	// Date today
	SimpleDateFormat date_sdf = new SimpleDateFormat("yyyy/MM/dd");
	Date date = new Date();
	String DateToday = date_sdf.format(date);

	// Products Class
	private DailyProducts dailyProducts = new DailyProducts();
	private Products products = new Products();
	private String DateFromDB;
	final DefaultTableModel model = new DefaultTableModel();
	final JFXPanel fxPanel = new JFXPanel();

	// Variables
	private double Weight;
	private double selectedProdCarbs;
	private double selectedProdWhey;
	private double selectedProdFats;
	private double Carbo;
	private double Whey;
	private double Fats;
	double tempValue;
	private String filterString;

	public void NewWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setVisible(true);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainWindow() {

		super("Fitness Calculator");
		setSize(1300, 731);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - 1147) / 2);
		int y = (int) ((dimension.getHeight() - 700) / 2);
		setLocation(x, y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// background
		backgroundLabel = new JLabel("", background, JLabel.CENTER);
		backgroundLabel.setBounds(0, 0, 1147, 700);
		backgroundLabel.setBorder(new LineBorder(new Color(255,255,255,0), 50));

		add(backgroundLabel);

		WeightTF.setBackground(Color.WHITE);
		CarboTF.setBackground(Color.WHITE);
		WheyTF.setBackground(Color.WHITE);
		FatsTF.setBackground(Color.WHITE);
		KcalTF.setBackground(Color.WHITE);
		filterTF.setBackground(Color.WHITE);
		TotalCarboTF.setBackground(Color.WHITE);
		TotalWheyTF.setBackground(Color.WHITE);
		TotalFatsTF.setBackground(Color.WHITE);
		TotalKcalTF.setBackground(Color.WHITE);

		WeightTF.setForeground(Color.BLACK);
		CarboTF.setForeground(Color.BLACK);
		WheyTF.setForeground(Color.BLACK);
		FatsTF.setForeground(Color.BLACK);
		KcalTF.setForeground(Color.BLACK);
		filterTF.setForeground(Color.BLACK);
		TotalCarboTF.setForeground(Color.BLACK);
		TotalWheyTF.setForeground(Color.BLACK);
		TotalFatsTF.setForeground(Color.BLACK);
		TotalKcalTF.setForeground(Color.BLACK);

		RefreshComboBox("");
		comboBox.setSelectedItem(null);

		// Create JTable
		String[] columnNames = { "Product", "Meal", "Weight", "Carbs", "Proteins", "Fats", "KCal" };

		model.setColumnIdentifiers(columnNames);
		model.fireTableDataChanged();

		final JTable tableList = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableList.setOpaque(false);
		((DefaultTableCellRenderer) tableList.getDefaultRenderer(Object.class)).setOpaque(false);

		tableList.setModel(model);
		tableList.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableList.setFillsViewportHeight(true);
		tableList.setGridColor(Color.WHITE);
		tableList.setRowSelectionAllowed(true);

		TableColumnModel columnModel = tableList.getColumnModel();
		columnModel.getColumn(0).setMinWidth(170);
		columnModel.getColumn(1).setMaxWidth(50);
		tableList.setRowHeight(22);

		DefaultTableCellRenderer Renderer = new DefaultTableCellRenderer();
		Renderer.setHorizontalAlignment(JLabel.LEFT);
		tableList.getColumnModel().getColumn(0).setCellRenderer(Renderer);

		DefaultTableCellRenderer Renderer2 = new DefaultTableCellRenderer();
		Renderer2.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 1; i < model.getColumnCount(); i++) {
			tableList.getColumnModel().getColumn(i).setCellRenderer(Renderer2);
		}

		JScrollPane scroll = new JScrollPane(tableList);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		tableList.setBackground(Color.LIGHT_GRAY);
		tableList.setForeground(Color.BLACK);
		tableList.setFont(new Font("Serif", Font.BOLD, 13));
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		scroll.setBorder(new LineBorder(Color.WHITE, 2));

		TotalCarboTF.setText("0");
		TotalWheyTF.setText("0");
		TotalFatsTF.setText("0");
		TotalKcalTF.setText("0");

		CheckDailyList();

		JTableHeader header = tableList.getTableHeader();
		header.setBackground(Color.DARK_GRAY);
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Serif", Font.BOLD, 13));
		header.setOpaque(false);

		// MainPanel

		backgroundLabel.setLayout(new BorderLayout(6, 3));

		// Buttons

		JButton ButtonAddNewProd = new JButton("    New product     ", carrotImage);
		JButton ButtonClearList = new JButton("     Clear list     ", cancelImage);
		JButton ButtonAddProd = new JButton("    Add to list     ", plusImage);
		JButton ButtonDeleteProd = new JButton("   Remove product   ", deleteImage);
		JButton ButtonRemoveProd = new JButton(" Remove from list  ", removeImage);
		JButton ButtonStatistics = new JButton("    Statistics      ", statisticsImage);
		JButton ButtonChangeUser = new JButton("        Change user       ");
		JButton ButtonAddBW = new JButton("      Add today's BW      ");

		ButtonAddNewProd.setBackground(Color.DARK_GRAY);
		ButtonClearList.setBackground(Color.DARK_GRAY);
		ButtonAddProd.setBackground(Color.DARK_GRAY);
		ButtonDeleteProd.setBackground(Color.DARK_GRAY);
		ButtonRemoveProd.setBackground(Color.DARK_GRAY);
		ButtonStatistics.setBackground(Color.DARK_GRAY);
		ButtonChangeUser.setBackground(Color.DARK_GRAY);
		ButtonAddBW.setBackground(Color.DARK_GRAY);

		ButtonAddNewProd.setForeground(Color.WHITE);
		ButtonClearList.setForeground(Color.WHITE);
		ButtonAddProd.setForeground(Color.WHITE);
		ButtonDeleteProd.setForeground(Color.WHITE);
		ButtonRemoveProd.setForeground(Color.WHITE);
		ButtonStatistics.setForeground(Color.WHITE);
		ButtonChangeUser.setForeground(Color.WHITE);
		ButtonAddBW.setForeground(Color.WHITE);

		ButtonAddNewProd.setBorder(new LineBorder(Color.WHITE, 1));
		ButtonClearList.setBorder(new LineBorder(Color.WHITE, 1));
		ButtonAddProd.setBorder(new LineBorder(Color.WHITE, 1));
		ButtonDeleteProd.setBorder(new LineBorder(Color.WHITE, 1));
		ButtonRemoveProd.setBorder(new LineBorder(Color.WHITE, 1));
		ButtonStatistics.setBorder(new LineBorder(Color.WHITE, 1));
		ButtonChangeUser.setBorder(new LineBorder(Color.WHITE, 1));
		ButtonAddBW.setBorder(new LineBorder(Color.WHITE, 1));

		ButtonAddNewProd.setFont(new Font("Dialog", Font.BOLD, 14));
		ButtonClearList.setFont(new Font("Dialog", Font.BOLD, 14));
		ButtonAddProd.setFont(new Font("Dialog", Font.BOLD, 14));
		ButtonDeleteProd.setFont(new Font("Dialog", Font.BOLD, 14));
		ButtonRemoveProd.setFont(new Font("Dialog", Font.BOLD, 14));
		ButtonStatistics.setFont(new Font("Dialog", Font.BOLD, 14));
		ButtonChangeUser.setFont(new Font("Dialog", Font.BOLD, 12));
		ButtonAddBW.setFont(new Font("Dialog", Font.BOLD, 12));

		// Top Panel
		JPanel AuxTopPanel0 = new JPanel();
		JPanel AuxTopPanel1 = new JPanel();
		JPanel AuxTopPanel2 = new JPanel();
		JPanel AuxTopPanel3 = new JPanel();
		JPanel AuxTopPanel4 = new JPanel();
		JPanel AuxTopPanel5 = new JPanel();
		JPanel AuxTopPanel6 = new JPanel();

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

		CarboTF.setDisabledTextColor(Color.BLACK);
		WheyTF.setDisabledTextColor(Color.BLACK);
		FatsTF.setDisabledTextColor(Color.BLACK);
		KcalTF.setDisabledTextColor(Color.BLACK);

		AuxTopPanel0.setOpaque(false);
		AuxTopPanel1.setOpaque(false);
		AuxTopPanel2.setOpaque(false);
		AuxTopPanel3.setOpaque(false);
		AuxTopPanel4.setOpaque(false);
		AuxTopPanel5.setOpaque(false);
		AuxTopPanel6.setOpaque(false);

		AuxTopPanel0.setLayout(new GridLayout(3, 1, 5, 5));
		AuxTopPanel1.setLayout(new GridLayout(2, 1));
		AuxTopPanel2.setLayout(new GridLayout(2, 1));
		AuxTopPanel3.setLayout(new GridLayout(2, 1));
		AuxTopPanel4.setLayout(new GridLayout(2, 1));
		AuxTopPanel5.setLayout(new GridLayout(2, 1));

		JLabel userName = new JLabel("    Hello " + LoginWindow.userName + " !");
		JLabel Tittle1 = new JLabel("       Weight : ");
		JLabel Tittle2 = new JLabel("        Carbs : ");
		JLabel Tittle3 = new JLabel("      Proteins :");
		JLabel Tittle4 = new JLabel("         Fats :");
		JLabel Tittle5 = new JLabel("      Calories :");
		JLabel Tittle6 = new JLabel("     Choose product :  ");
		JLabel Tittle6b = new JLabel("Filter product list :  ");

		userName.setFont(new Font("Dialog", Font.BOLD, 15));
		Tittle1.setFont(new Font("Dialog", Font.BOLD, 13));
		Tittle2.setFont(new Font("Dialog", Font.BOLD, 13));
		Tittle3.setFont(new Font("Dialog", Font.BOLD, 13));
		Tittle4.setFont(new Font("Dialog", Font.BOLD, 13));
		Tittle5.setFont(new Font("Dialog", Font.BOLD, 13));
		Tittle6.setFont(new Font("Dialog", Font.BOLD, 14));
		Tittle6b.setFont(new Font("Dialog", Font.BOLD, 14));

		Tittle6.setHorizontalAlignment(SwingConstants.RIGHT);
		Tittle6b.setHorizontalAlignment(SwingConstants.RIGHT);

		userName.setForeground(Color.BLACK);
		Tittle1.setForeground(Color.BLACK);
		Tittle2.setForeground(Color.BLACK);
		Tittle3.setForeground(Color.BLACK);
		Tittle4.setForeground(Color.BLACK);
		Tittle5.setForeground(Color.BLACK);
		Tittle6.setForeground(Color.BLACK);
		Tittle6b.setForeground(Color.BLACK);

		comboBox.setBackground(Color.WHITE);
		comboBox.setForeground(Color.BLACK);


		comboBox.setBorder(new LineBorder(Color.WHITE, 1));

		comboBox.setPrototypeDisplayValue("       Choose a product from list        ");
		((JLabel) comboBox.getRenderer()).setHorizontalAlignment(JLabel.LEFT);

		JPanel comboPanel = new JPanel();
		comboPanel.setOpaque(false);
		comboPanel.setLayout(new GridLayout(2, 1, 0, 10));

		JPanel comboLabels = new JPanel();
		comboLabels.setOpaque(false);
		comboLabels.setLayout(new GridLayout(2, 1, 0, 10));

		AuxTopPanel0.add(userName);
		AuxTopPanel0.add(ButtonChangeUser);
		AuxTopPanel0.add(ButtonAddBW);
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
		comboLabels.add(Tittle6b);
		comboLabels.add(Tittle6);
		AuxTopPanel6.add(comboLabels);
		comboPanel.add(filterTF);
		comboPanel.add(comboBox);
		AuxTopPanel6.add(comboPanel);
		AuxTopPanel6.add(ButtonAddNewProd);
		AuxTopPanel6.add(ButtonDeleteProd);

		JPanel topPanel1 = new JPanel();
		JPanel topPanel2 = new JPanel();
		JPanel topPanel3 = new JPanel();

		topPanel1.setOpaque(false);
		topPanel2.setOpaque(false);
		topPanel3.setOpaque(false);

		topPanel1.setLayout(new GridLayout(2, 1));

		backgroundLabel.add(topPanel1, BorderLayout.NORTH);
		topPanel1.add(topPanel2);
		topPanel2.add(AuxTopPanel0);
		topPanel2.add(AuxTopPanel6);
		topPanel1.add(topPanel3);
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
		middlePanel.setOpaque(false);

		KcalSlider.setMajorTickSpacing(400);
		KcalSlider.setMinorTickSpacing(200);
		KcalSlider.setPaintTicks(true);
		KcalSlider.setPaintLabels(true);
		KcalSlider.setForeground(Color.BLACK);
		KcalSlider.setOpaque(false);
		KcalSlider.setFont(new Font("Dialog", Font.BOLD, 11));

		backgroundLabel.add(middlePanel);
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

		bottomPanel1.setOpaque(false);
		bottomPanel2.setOpaque(false);
		bottomPanel3.setOpaque(false);
		panel8.setOpaque(false);
		panel9.setOpaque(false);
		panel10.setOpaque(false);
		panel11.setOpaque(false);

		panel8.setLayout(new GridLayout(2, 1));
		panel9.setLayout(new GridLayout(2, 1));
		panel10.setLayout(new GridLayout(2, 1));
		panel11.setLayout(new GridLayout(2, 1));
		backgroundLabel.add(bottomPanel1, BorderLayout.SOUTH);

		JLabel Tittle7 = new JLabel("  Summary : ");
		JLabel Tittle8 = new JLabel("            Carbs : ");
		JLabel Tittle9 = new JLabel("           Proteins :");
		JLabel Tittle10 = new JLabel("              Fats :");
		JLabel Tittle11 = new JLabel("           Calories :");

		TotalCarboTF.setHorizontalAlignment(JTextField.CENTER);
		TotalWheyTF.setHorizontalAlignment(JTextField.CENTER);
		TotalFatsTF.setHorizontalAlignment(JTextField.CENTER);
		TotalKcalTF.setHorizontalAlignment(JTextField.CENTER);

		TotalCarboTF.setDisabledTextColor(Color.BLACK);
		TotalWheyTF.setDisabledTextColor(Color.BLACK);
		TotalFatsTF.setDisabledTextColor(Color.BLACK);
		TotalKcalTF.setDisabledTextColor(Color.BLACK);

		TotalCarboTF.setEnabled(false);
		TotalWheyTF.setEnabled(false);
		TotalFatsTF.setEnabled(false);
		TotalKcalTF.setEnabled(false);

		Tittle7.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		Tittle8.setFont(new Font("Dialog", Font.BOLD, 13));
		Tittle9.setFont(new Font("Dialog", Font.BOLD, 13));
		Tittle10.setFont(new Font("Dialog", Font.BOLD, 13));
		Tittle11.setFont(new Font("Dialog", Font.BOLD, 13));

		Tittle7.setForeground(Color.BLACK);
		Tittle8.setForeground(Color.BLACK);
		Tittle9.setForeground(Color.BLACK);
		Tittle10.setForeground(Color.BLACK);
		Tittle11.setForeground(Color.BLACK);

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
		panelEast1.setOpaque(false);

		backgroundLabel.add(panelEast1, BorderLayout.EAST);
		panelEast1.setLayout(new BorderLayout());
		panelEast1.add(fxPanel, BorderLayout.CENTER);

		JPanel statsPanel = new JPanel();
		statsPanel.add(ButtonStatistics);
		panelEast1.add(statsPanel, BorderLayout.SOUTH);
		statsPanel.setOpaque(false);

		// West Panel ( for padding )
		JPanel panelWest = new JPanel();
		panelWest.setOpaque(false);
		JLabel paddingLabel = new JLabel("      ");
		panelWest.add(paddingLabel);
		backgroundLabel.add(panelWest, BorderLayout.WEST);

		// ---------------- Actions -------------- //

		ButtonAddBW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String myDriver = "com.mysql.cj.jdbc.Driver";
					String myUrl = "jdbc:mysql://phpmyadmin47.lh.pl:3306/serwer58262_Kcal?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&characterEncoding=utf-8";
					Class.forName(myDriver);

					Connection conn = DriverManager.getConnection(myUrl, "serwer58262_Kcal", "kcal00#");

					Statement st = conn.createStatement();

					ResultSet rs = st.executeQuery("SELECT * FROM usersweight WHERE `date` = '" + DateToday
							+ "' AND userID = " + LoginWindow.UserID);

					if (rs.next()) {
						JOptionPane.showMessageDialog(null,
								"Today your BW ( " + rs.getDouble("bodyweight") + " kg ) already have been added !  ",
								"Already added!", JOptionPane.INFORMATION_MESSAGE, null);
					} else {
						AddBW nw = new AddBW();
						nw.NewWindow();
					}

					conn.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE,
							deleteImage);
				}
			}
		});

		ButtonRemoveProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tableList.getSelectionModel().isSelectionEmpty())
					;

				else {
					int decision = JOptionPane.showConfirmDialog(null,
							"    Are you sure to remove selected meal from Daily meal list ?", "Warning",
							JOptionPane.YES_NO_OPTION);
					if (decision == 0) {
						try {
							int row = tableList.getSelectedRow();
							dailyProducts.setDate(DateToday);
							dailyProducts.setProductName(model.getValueAt(row, 0).toString());
							dailyProducts.setID(Integer
									.parseInt(getDateFromDB("ID", "dailyproducts", dailyProducts.getProductName())));
							dailyProducts.setWeight(
									Double.parseDouble(model.getValueAt(row, 2).toString().replace(" g", "")));
							dailyProducts.setCarbo(Double.parseDouble(model.getValueAt(row, 3).toString()));
							dailyProducts.setWhey(Double.parseDouble(model.getValueAt(row, 4).toString()));
							dailyProducts.setFats(Double.parseDouble(model.getValueAt(row, 5).toString()));

							String myDriver = "com.mysql.cj.jdbc.Driver";
							String myUrl = "jdbc:mysql://phpmyadmin47.lh.pl:3306/serwer58262_Kcal?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&characterEncoding=utf-8";
							Class.forName(myDriver);

							Connection conn = DriverManager.getConnection(myUrl, "serwer58262_Kcal", "kcal00#");

							Statement st = conn.createStatement();

							st.executeUpdate("DELETE FROM `dailyproducts` WHERE ID = " + dailyProducts.getID()
									+ " AND `ProductName` = '" + dailyProducts.getProductName() + "' AND `Date` = '"
									+ DateToday + "' AND `Carbo` = " + dailyProducts.getCarbo() + " AND `Whey` = "
									+ dailyProducts.getWhey() + " AND `Fats` = " + dailyProducts.getFats()
									+ " AND `Weight` = " + dailyProducts.getWeight());

							conn.close();

							model.removeRow(row);
							RefreshSummaryPanel();
							tableList.clearSelection();

						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!",
									JOptionPane.INFORMATION_MESSAGE, deleteImage);
						}
					}
				}
			}
		});

		ButtonDeleteProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int decision = JOptionPane.showConfirmDialog(null,
						"    Are you sure to remove " + comboBox.getSelectedItem().toString() + " from Database ?",
						"Warning", JOptionPane.YES_NO_OPTION);
				if (decision == 0) {
					try {
						products.setProductName(comboBox.getSelectedItem().toString());

						String myDriver = "com.mysql.cj.jdbc.Driver";
						String myUrl = "jdbc:mysql://phpmyadmin47.lh.pl:3306/serwer58262_Kcal?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&characterEncoding=utf-8";
						Class.forName(myDriver);

						Connection conn = DriverManager.getConnection(myUrl, "serwer58262_Kcal", "kcal00#");

						Statement st = conn.createStatement();

						String ValuesSTR = "'" + products.getProductName() + "'";

						st.executeUpdate("DELETE FROM `products` WHERE `ProductName` = " + ValuesSTR);

						conn.close();

						JOptionPane.showMessageDialog(null,
								products.getProductName() + " successful deleted from Database", "Success!",
								JOptionPane.INFORMATION_MESSAGE, deleteImage);

						LoginWindow.productNameList.remove(products.getProductName());
						LoginWindow.productCarbsMap.remove(products.getProductName());
						LoginWindow.productWheyMap.remove(products.getProductName());
						LoginWindow.productFatsMap.remove(products.getProductName());
						RefreshComboBox("");
						filterTF.setText("");
						
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE,
								deleteImage);
					}
				}
			}
		});

		ButtonAddNewProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddProductWindow nw = new AddProductWindow();
				nw.NewWindow();
			}
		});

		ButtonStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statistics nw = new Statistics();
				nw.NewWindow();
			}
		});

		ButtonChangeUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginWindow nw = new LoginWindow();
				nw.NewWindow();
			}
		});

		ButtonClearList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int decision = JOptionPane.showConfirmDialog(null,
						"    Are you sure you want to clear today's meal list? ", "Warning", JOptionPane.YES_NO_OPTION);
				if (decision == 0) {
					try {

						dailyProducts.setDate(DateToday);

						String myDriver = "com.mysql.cj.jdbc.Driver";
						String myUrl = "jdbc:mysql://phpmyadmin47.lh.pl:3306/serwer58262_Kcal?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&characterEncoding=utf-8";
						Class.forName(myDriver);

						Connection conn = DriverManager.getConnection(myUrl, "serwer58262_Kcal", "kcal00#");

						Statement st = conn.createStatement();

						String ValuesSTR = "'" + dailyProducts.getDate() + "'";

						st.executeUpdate("DELETE FROM `dailyproducts` WHERE `Date` = " + ValuesSTR + "AND `UserID` = "
								+ LoginWindow.UserID);

						conn.close();

						model.setRowCount(0);
						TotalCarboTF.setText("");
						TotalWheyTF.setText("");
						TotalFatsTF.setText("");
						TotalKcalTF.setText("");

						KcalSlider.setValue(0);
						RefreshSummaryPanel();

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE,
								deleteImage);
					}
				}
			}
		});

		filterTF.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				filterString = filterTF.getText().toLowerCase();
				RefreshComboBox(filterString);
			}

			@Override
			public void keyTyped(KeyEvent arg0) {

			}

		});

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
			public void keyReleased(KeyEvent evt) {

				char c = evt.getKeyChar();

				String weightString = WeightTF.getText();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
						|| (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_COMMA))) {
					evt.consume();
				}

				if (c == KeyEvent.VK_COMMA) {
					evt.setKeyChar((char) KeyEvent.VK_PERIOD);
				}

				if (weightString.equals("00")) {
					WeightTF.setText("");
				}

				if (weightString.equals(".")) {
					WeightTF.setText("0.");
				}

				if (weightString.length() > 1 && weightString.charAt(0) == KeyEvent.VK_0
						&& weightString.charAt(1) != KeyEvent.VK_PERIOD) {
					WeightTF.setText("0." + weightString.substring(1));
				}

				if (weightString.equals("")) {
					CarboTF.setText("0");
					WheyTF.setText("0");
					FatsTF.setText("0");
					KcalTF.setText("0");
				}

				if (!weightString.equals("")) {
					if (comboBox.getSelectedItem() != null) {

						double divWeight = (Double.parseDouble(WeightTF.getText())) / 100;

						CarboTF.setText(String.format("%.2f", selectedProdCarbs * divWeight));
						WheyTF.setText(String.format("%.2f", selectedProdWhey * divWeight));
						FatsTF.setText(String.format("%.2f", selectedProdFats * divWeight));

						dailyProducts.setCarbo(Double.parseDouble(CarboTF.getText().replace(",", ".")));
						dailyProducts.setWhey(Double.parseDouble(WheyTF.getText().replace(",", ".")));
						dailyProducts.setFats(Double.parseDouble(FatsTF.getText().replace(",", ".")));

						KcalTF.setText(String.format("%.2f", dailyProducts.getKcal()));
					}
				}

			}

			@Override
			public void keyTyped(KeyEvent evt) {

				char c = evt.getKeyChar();

				String weightString = WeightTF.getText();
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

		comboBox.addItemListener(new ItemListener() {
			// Listening if a new items of the combo box has been selected.
			public void itemStateChanged(ItemEvent event) {
				
				@SuppressWarnings("unchecked")
				JComboBox<String> comboBox =  (JComboBox<String>) event.getSource();

				if (event.getStateChange() == ItemEvent.SELECTED) {

					String prodName = comboBox.getSelectedItem().toString();
					
					selectedProdCarbs = LoginWindow.productCarbsMap.get(prodName);
					selectedProdWhey =  LoginWindow.productWheyMap.get(prodName);
					selectedProdFats = LoginWindow.productFatsMap.get(prodName);

					CarboTF.setText(String.format("%.2f", selectedProdCarbs));
					WheyTF.setText(String.format("%.2f", selectedProdWhey));
					FatsTF.setText(String.format("%.2f", selectedProdFats));
					KcalTF.setText(String.format("%.2f",
							(selectedProdCarbs * 4 + selectedProdWhey * 4 + selectedProdFats * 9)));
				}
			}
		});


		ButtonAddProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem() != null) {

					dailyProducts.setProductName(comboBox.getSelectedItem().toString());
					dailyProducts.setCarbo(Double.parseDouble(CarboTF.getText().replace(",", ".")));
					dailyProducts.setWhey(Double.parseDouble(WheyTF.getText().replace(",", ".")));
					dailyProducts.setFats(Double.parseDouble(FatsTF.getText().replace(",", ".")));
					dailyProducts.setWeight(Double.parseDouble(WeightTF.getText().replace(",", ".")));

					try {

						String myDriver = "com.mysql.cj.jdbc.Driver";
						String myUrl = "jdbc:mysql://phpmyadmin47.lh.pl:3306/serwer58262_Kcal?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&characterEncoding=utf-8";
						Class.forName(myDriver);

						Connection conn = DriverManager.getConnection(myUrl, "serwer58262_Kcal", "kcal00#");

						Statement st = conn.createStatement();

						String ValuesSTR = LoginWindow.UserID + ", '" + DateToday + "' , '"
								+ dailyProducts.getProductName() + "' ," + dailyProducts.getCarbo() + ", "
								+ dailyProducts.getWhey() + ", " + dailyProducts.getFats() + ", " + dailyProducts.getWeight();

						st.executeUpdate(
								"INSERT INTO dailyproducts (UserID, Date, ProductName, Carbo, Whey, Fats, Weight)"
										+ " VALUES (" + ValuesSTR + ")");

						conn.close();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE,
								deleteImage);
					}

					finally {

						String[] dateCurrentDay = new String[6];

						dateCurrentDay[0] = comboBox.getSelectedItem().toString();

						dateCurrentDay[1] = String.valueOf(Math.round(dailyProducts.getWeight())) + " g";

						dateCurrentDay[2] = String.valueOf(dailyProducts.getCarbo());

						dateCurrentDay[3] = String.valueOf(dailyProducts.getWhey());

						dateCurrentDay[4] = String.valueOf(dailyProducts.getFats());

						dateCurrentDay[5] = String
								.valueOf(Math.round(((dailyProducts.getCarbo() + dailyProducts.getWhey()) * 4)
										+ dailyProducts.getFats() * 9));

						model.addRow(dateCurrentDay);

						WeightTF.setText("100");
						CarboTF.setText(String.format("%.2f", selectedProdCarbs));
						WheyTF.setText(String.format("%.2f", selectedProdWhey));
						FatsTF.setText(String.format("%.2f", selectedProdFats));
						KcalTF.setText(String.format("%.2f",
								(selectedProdCarbs * 4 + selectedProdWhey * 4 + selectedProdFats * 9)));
						RefreshSummaryPanel();
					}
				}
			}
		});
	}

	private String getDateFromDB(String Value, String ColumnName, String ProductName) {
		try {

			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://phpmyadmin47.lh.pl:3306/serwer58262_Kcal?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&characterEncoding=utf-8";
			Class.forName(myDriver);

			Connection conn = DriverManager.getConnection(myUrl, "serwer58262_Kcal", "kcal00#");

			Statement st = conn.createStatement();

			ResultSet rs = st
					.executeQuery("SELECT * FROM " + ColumnName + " WHERE ProductName = '" + ProductName + "'");

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

	static void RefreshComboBox(String filter) {

		comboBox.removeAllItems();

		for (String name : LoginWindow.productNameList) {
			if ( name.toLowerCase().contains(filter.toLowerCase())) {
			comboBox.addItem(name);
			}
		}
	}

	private void RefreshSummaryPanel() {

		for (int count = 0; count < model.getRowCount(); count++) {
			dailyProducts.setCarbo(Double.parseDouble(model.getValueAt(count, 3).toString()));
			tempValue = tempValue + dailyProducts.getCarbo();
		}
		TotalCarboTF.setText((String.format("%.2f", tempValue)).replace(",", "."));
		tempValue = 0;

		for (int count = 0; count < model.getRowCount(); count++) {
			dailyProducts.setWhey(Double.parseDouble(model.getValueAt(count, 4).toString()));
			tempValue = tempValue + dailyProducts.getWhey();
		}
		TotalWheyTF.setText((String.format("%.2f", tempValue)).replace(",", "."));
		tempValue = 0;

		for (int count = 0; count < model.getRowCount(); count++) {
			dailyProducts.setFats(Double.parseDouble(model.getValueAt(count, 5).toString()));
			tempValue = tempValue + dailyProducts.getFats();
		}
		TotalFatsTF.setText((String.format("%.2f", tempValue)).replace(",", "."));

		Double kcalValue = Double.parseDouble(TotalCarboTF.getText()) * 4
				+ Double.parseDouble(TotalWheyTF.getText()) * 4 + Double.parseDouble(TotalFatsTF.getText()) * 9;

		TotalKcalTF.setText((String.format("%.2f", kcalValue)).replace(",", "."));
		tempValue = 0;

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				initFX(fxPanel);
			}
		});

		// Slider
		KcalSlider.setValue((int) Math.round(Double.parseDouble(TotalKcalTF.getText())));
		if (Double.parseDouble(TotalKcalTF.getText()) >= LoginWindow.UserKcalGoal + 500) {
			KcalSlider.setMaximum(LoginWindow.UserKcalGoal + 1500);
		}
		if (KcalSlider.getValue() > LoginWindow.UserKcalGoal)
			JOptionPane.showMessageDialog(null, LoginWindow.UserKcalGoal + " KCalories exceeded :-)!", "Ups...!",
					JOptionPane.INFORMATION_MESSAGE, upsImage);
	}

	private void CheckDailyList() {
		try {

			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://phpmyadmin47.lh.pl:3306/serwer58262_Kcal?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&characterEncoding=utf-8";
			Class.forName(myDriver);

			Connection conn = DriverManager.getConnection(myUrl, "serwer58262_Kcal", "kcal00#");

			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM dailyproducts WHERE `Date` = '" + DateToday
					+ "' AND `UserID` = " + LoginWindow.UserID);

			while (rs.next()) {

				Object[] dateCurrentDay = new String[6];

				dateCurrentDay[0] = rs.getString("ProductName");

				Weight = rs.getDouble("Weight");
				dateCurrentDay[1] = String.valueOf(Math.round(Weight)) + " g";

				Carbo = rs.getDouble("Carbo");
				dateCurrentDay[2] = String.valueOf(Carbo);

				Whey = rs.getDouble("Whey");
				dateCurrentDay[3] = String.valueOf(Whey);

				Fats = rs.getDouble("Fats");
				dateCurrentDay[4] = String.valueOf(Fats);

				dateCurrentDay[5] = String.valueOf(Math.round(((Carbo + Whey) * 4) + Fats * 9));

				model.addRow(dateCurrentDay);
			}
			conn.close();
			RefreshSummaryPanel();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE,
					deleteImage);
		}
	}

	private static void initFX(JFXPanel fxPanel) {
		Scene scene = createScene((int) Math.round(Double.parseDouble(TotalCarboTF.getText())),
				(int) Math.round(Double.parseDouble(TotalWheyTF.getText())),
				(int) Math.round(Double.parseDouble(TotalFatsTF.getText())));
		fxPanel.setScene(scene);
	}

	private static Scene createScene(int Carbo, int Whey, int Fats) {

		PieChart pieChart = new PieChart();

		PieChart.Data slice1 = new PieChart.Data("Carbs", 1);
		PieChart.Data slice2 = new PieChart.Data("Proteins", 1);
		PieChart.Data slice3 = new PieChart.Data("Fats", 1);

		if (Carbo != 0 || Whey != 0 || Fats != 0) {

			double total = Carbo + Whey + Fats;
			Fats = (int) (100 - Math.round(Carbo * 100 / total) - Math.round(Whey * 100 / total));

			slice1 = new PieChart.Data("Carbs " + Math.round(Carbo * 100 / total) + "%", Carbo);
			slice2 = new PieChart.Data("Whey " + Math.round(Whey * 100 / total) + "%", Whey);
			slice3 = new PieChart.Data("Fats " + Fats + "%", Fats);
		}

		else {

		}
		pieChart.getData().add(slice1);
		pieChart.getData().add(slice2);
		pieChart.getData().add(slice3);

		pieChart.setLabelLineLength(20);

		VBox vbox = new VBox(pieChart);
		vbox.setBackground(Background.EMPTY);
		String style = "-fx-font-size: 1em;";
		vbox.setStyle(style);
		Scene scene = new Scene(vbox, 400, 300);
		scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
		scene.getStylesheets().add("style.css");

		return (scene);
	}
}
