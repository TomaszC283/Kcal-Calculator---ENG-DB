package TomaszC283.main.java.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

public class Statistics extends JFrame {

	private JFrame dpFrame;

	private JLabel backgroundLabel;
	private JPanel fxPanel;
	private JPanel datePanel;
	private JPanel paddingPanel;
	private static JFXPanel caloriesPanel;
	private static JFXPanel macrosPanel;
	private static JFXPanel weightPanel;
	private static JRadioButton daysTrend;
	private static JRadioButton weeksTrend;
	private static JRadioButton monthsTrend;
	private static JLabel statisticTrendy;

	private static Map<String, Double> weightMap = new TreeMap<>();
	private static Map<String, Double> carbsMap = new TreeMap<>();
	private static Map<String, Double> wheysMap = new TreeMap<>();
	private static Map<String, Double> fatsMap = new TreeMap<>();
	private static Map<String, Integer> caloriesMap = new TreeMap<>();

	private static Object MapKey;
	private static String dateOfDB;
	private static String strToCompare = "";
	private static String dayToCompare = "";
	private static String weekOfDay;
	private static String monthOfDay;
	private static double summaryCarbsValue;
	private static double summaryWheysValue;
	private static double summaryFatsValue;
	private static double summaryWeightValue;
	private static int countSummaryValue;
	private static int startValue;

	private static XYChart.Series<String, Number> seriesWeight;
	private static XYChart.Series<String, Number> seriesCarbs;
	private static XYChart.Series<String, Number> seriesWheys;
	private static XYChart.Series<String, Number> seriesFats;
	private static XYChart.Series<String, Number> seriesCalories;

	static ImageIcon deleteImage = new ImageIcon("src/TomaszC283/main/java/resources/delete.png");
	ImageIcon background = new ImageIcon("src/TomaszC283/main/java/resources/sky_stats.jpg");

	public void NewWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dpFrame.setVisible(true);
					dpFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Statistics() {

		dpFrame = new JFrame("Adding new Product to List");
		dpFrame.setSize(1300, 813);
		dpFrame.setResizable(false);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - dpFrame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - dpFrame.getHeight()) / 2);
		dpFrame.setLocation(x, y);

		backgroundLabel = new JLabel("", background, JLabel.CENTER);
		backgroundLabel.setBounds(0, 0, 550, 434);
		backgroundLabel.setBorder(new LineBorder(new Color(255, 255, 255, 0), 50));

		dpFrame.add(backgroundLabel);

		fxPanel = new JPanel();
		datePanel = new JPanel();
		paddingPanel = new JPanel();
		caloriesPanel = new JFXPanel();
		macrosPanel = new JFXPanel();
		weightPanel = new JFXPanel();

		daysTrend = new JRadioButton("Days");
		weeksTrend = new JRadioButton("Weeks");
		monthsTrend = new JRadioButton("Months");
		daysTrend.setSelected(true);

		ButtonGroup groupButton = new ButtonGroup();
		statisticTrendy = new JLabel("Choose trends :");

		backgroundLabel.setLayout(new BorderLayout());
		fxPanel.setLayout(new GridLayout(3, 1, 0, 5));
		datePanel.setLayout(new GridLayout(10, 1, 10, 5));

		datePanel.setBorder(new LineBorder(new Color(255, 255, 255, 0), 20));
		paddingPanel.setBorder(new LineBorder(new Color(255, 255, 255, 0), 10));

		statisticTrendy.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));

		fxPanel.setOpaque(false);
		datePanel.setOpaque(false);
		paddingPanel.setOpaque(false);
		daysTrend.setOpaque(false);
		weeksTrend.setOpaque(false);
		monthsTrend.setOpaque(false);

		backgroundLabel.add(fxPanel, BorderLayout.CENTER);
		backgroundLabel.add(datePanel, BorderLayout.WEST);
		backgroundLabel.add(paddingPanel, BorderLayout.EAST);

		fxPanel.add(caloriesPanel);
		fxPanel.add(macrosPanel);
		fxPanel.add(weightPanel);
		groupButton.add(daysTrend);
		groupButton.add(weeksTrend);
		groupButton.add(monthsTrend);
		datePanel.add(statisticTrendy);
		datePanel.add(daysTrend);
		datePanel.add(weeksTrend);
		datePanel.add(monthsTrend);

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				initFX();
			}
		});

		daysTrend.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					radioCheckChanged();
				}
			}
		});

		weeksTrend.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					radioCheckChanged();
				}
			}
		});

		monthsTrend.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					radioCheckChanged();
				}
			}
		});
	}

	private static void radioCheckChanged() {
		weightMap.clear();
		carbsMap.clear();
		wheysMap.clear();
		fatsMap.clear();
		caloriesMap.clear();
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				initFX();
			}
		});
	}

	private static void initFX() {

		Scene scene = createMacrosChart();
		macrosPanel.setScene(scene);
		scene = createWeightChart();
		weightPanel.setScene(scene);
		scene = createCaloriesChart();
		caloriesPanel.setScene(scene);
	}

	private static Scene createCaloriesChart() {

		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
		yAxis.setForceZeroInRange(false);
		seriesCalories = new XYChart.Series<>();
		seriesCalories.setName("Calories");

		try {
			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://phpmyadmin47.lh.pl:3306/serwer58262_Kcal?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&characterEncoding=utf-8";
			Class.forName(myDriver);

			Connection conn = DriverManager.getConnection(myUrl, "serwer58262_Kcal", "kcal00#");

			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT * FROM dailyproducts WHERE userID = " + LoginWindow.UserID + " ORDER BY Date ASC");
			if (daysTrend.isSelected()) {
				while (rs.next()) {

					dateOfDB = rs.getString("Date");

					if (strToCompare.equals(dateOfDB)) {
						summaryCarbsValue += rs.getDouble("Carbo");
						summaryWheysValue += rs.getDouble("Whey");
						summaryFatsValue += rs.getDouble("Fats");
					} else {
						summaryCarbsValue = rs.getDouble("Carbo");
						summaryWheysValue = rs.getDouble("Whey");
						summaryFatsValue = rs.getDouble("Fats");
					}

					caloriesMap.put(dateOfDB,
							(int) Math.round((summaryCarbsValue * 4 + summaryWheysValue * 4 + summaryFatsValue * 9)));
					strToCompare = rs.getString("date");

					lineChart.setTitle(
							"Calories statistics divided into days - last " + caloriesMap.size() + " results");
				}
			}

			if (weeksTrend.isSelected()) {

				strToCompare = "";
				dayToCompare = "";

				while (rs.next()) {
					dateOfDB = rs.getString("date");
					weekOfDay = new SimpleDateFormat("w").format(new SimpleDateFormat("yyyy/MM/dd").parse(dateOfDB));
					if (!dayToCompare.equals(dateOfDB) && strToCompare.equals(weekOfDay)) {
						countSummaryValue++;
					}

					if (strToCompare.equals(weekOfDay)) {
						summaryCarbsValue += rs.getDouble("Carbo");
						summaryWheysValue += rs.getDouble("Whey");
						summaryFatsValue += rs.getDouble("Fats");
					}

					else {
						countSummaryValue = 1;
						summaryCarbsValue = rs.getDouble("Carbo");
						summaryWheysValue = rs.getDouble("Whey");
						summaryFatsValue = rs.getDouble("Fats");
					}
					caloriesMap.put(
							weekOfDay + " week - "
									+ new SimpleDateFormat("yyyy")
											.format(new SimpleDateFormat("yyyy/MM/dd").parse(dateOfDB)),
							(int) Math.round((summaryCarbsValue * 4 + summaryWheysValue * 4 + summaryFatsValue * 9)
									/ countSummaryValue));

					strToCompare = new SimpleDateFormat("w")
							.format(new SimpleDateFormat("yyyy/MM/dd").parse(rs.getString("date")));

					dayToCompare = rs.getString("date");
				}
				lineChart.setTitle(
						"Calories statistics divided into week in year - last " + caloriesMap.size() + " results");
			}

			if (monthsTrend.isSelected()) {

				strToCompare = "";
				dayToCompare = "";

				while (rs.next()) {
					dateOfDB = rs.getString("date");
					monthOfDay = new SimpleDateFormat("MMM").format(new SimpleDateFormat("yyyy/MM/dd").parse(dateOfDB));

					if (!dayToCompare.equals(dateOfDB) && strToCompare.equals(monthOfDay)) {
						countSummaryValue++;
					}

					if (strToCompare.equals(monthOfDay)) {
						summaryCarbsValue += rs.getDouble("Carbo");
						summaryWheysValue += rs.getDouble("Whey");
						summaryFatsValue += rs.getDouble("Fats");
					}

					else {
						countSummaryValue = 1;
						summaryCarbsValue = rs.getDouble("Carbo");
						summaryWheysValue = rs.getDouble("Whey");
						summaryFatsValue = rs.getDouble("Fats");
					}

					caloriesMap.put(
							new SimpleDateFormat("MMM yyyy").format(new SimpleDateFormat("yyyy/MM/dd").parse(dateOfDB)),
							(int) Math.round((summaryCarbsValue * 4 + summaryWheysValue * 4 + summaryFatsValue * 9)
									/ countSummaryValue));

					strToCompare = new SimpleDateFormat("MMM")
							.format(new SimpleDateFormat("yyyy/MM/dd").parse(rs.getString("date")));

					dayToCompare = rs.getString("date");
				}

				lineChart.setTitle("Calories statistics divided into months - last " + caloriesMap.size() + " results");
			}

			if (caloriesMap.size() <= 15) {
				startValue = 0;
			} else {
				startValue = caloriesMap.size() - 15;
			}

			for (int i = startValue; i < caloriesMap.size(); i++) {

				MapKey = caloriesMap.keySet().toArray()[i];
				seriesCalories.getData().add(new XYChart.Data<>(MapKey.toString(), caloriesMap.get(MapKey)));
			}

			conn.close();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE,
					deleteImage);
		}
		lineChart.getData().add(seriesCalories);
		VBox vbox = new VBox(lineChart);
		vbox.setBackground(Background.EMPTY);
		String style = "-fx-font-size: 1em;";
		vbox.setStyle(style);
		Scene scene = new Scene(vbox);
		scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
		scene.getStylesheets().add("style.css");
		return (scene);
	}

	@SuppressWarnings("unchecked")
	private static Scene createMacrosChart() {

		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
		yAxis.setForceZeroInRange(false);

		seriesCarbs = new XYChart.Series<>();
		seriesCarbs.setName(" Carbs");
		seriesWheys = new XYChart.Series<>();
		seriesWheys.setName(" Wheys");
		seriesFats = new XYChart.Series<>();
		seriesFats.setName(" Fats");

		try {
			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://phpmyadmin47.lh.pl:3306/serwer58262_Kcal?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&characterEncoding=utf-8";
			Class.forName(myDriver);

			Connection conn = DriverManager.getConnection(myUrl, "serwer58262_Kcal", "kcal00#");

			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT * FROM dailyproducts WHERE userID = " + LoginWindow.UserID + " ORDER BY Date ASC");

			if (daysTrend.isSelected()) {
				while (rs.next()) {
					dateOfDB = rs.getString("Date");

					if (strToCompare.equals(dateOfDB)) {
						summaryCarbsValue += rs.getDouble("Carbo");
						summaryWheysValue += rs.getDouble("Whey");
						summaryFatsValue += rs.getDouble("Fats");
					}

					else {
						summaryCarbsValue = rs.getDouble("Carbo");
						summaryWheysValue = rs.getDouble("Whey");
						summaryFatsValue = rs.getDouble("Fats");
					}

					carbsMap.put(dateOfDB,
							Double.parseDouble(String.format("%.1f", summaryCarbsValue).replace(",", ".")));

					wheysMap.put(dateOfDB,
							Double.parseDouble(String.format("%.1f", summaryWheysValue).replace(",", ".")));

					fatsMap.put(dateOfDB,
							Double.parseDouble(String.format("%.1f", summaryFatsValue).replace(",", ".")));

					strToCompare = rs.getString("date");

					lineChart.setTitle(
							"Macroelements statistics divided into days - last " + carbsMap.size() + " results");
				}
			}

			if (weeksTrend.isSelected()) {

				strToCompare = "";
				dayToCompare = "";

				while (rs.next()) {

					dateOfDB = rs.getString("date");
					weekOfDay = new SimpleDateFormat("w").format(new SimpleDateFormat("yyyy/MM/dd").parse(dateOfDB));

					if (!dayToCompare.equals(dateOfDB) && strToCompare.equals(weekOfDay)) {
						countSummaryValue++;
					}

					if (strToCompare.equals(weekOfDay)) {
						summaryCarbsValue += rs.getDouble("Carbo");
						summaryWheysValue += rs.getDouble("Whey");
						summaryFatsValue += rs.getDouble("Fats");
					}

					else {
						countSummaryValue = 1;
						summaryCarbsValue = rs.getDouble("Carbo");
						summaryWheysValue = rs.getDouble("Whey");
						summaryFatsValue = rs.getDouble("Fats");
					}

					carbsMap.put(
							weekOfDay + " week - "
									+ new SimpleDateFormat("yyyy")
											.format(new SimpleDateFormat("yyyy/MM/dd").parse(dateOfDB)),
							Double.parseDouble(
									String.format("%.1f", summaryCarbsValue / countSummaryValue).replace(",", ".")));

					wheysMap.put(
							weekOfDay + " week - "
									+ new SimpleDateFormat("yyyy")
											.format(new SimpleDateFormat("yyyy/MM/dd").parse(dateOfDB)),
							Double.parseDouble(
									String.format("%.1f", summaryWheysValue / countSummaryValue).replace(",", ".")));

					fatsMap.put(
							weekOfDay + " week - "
									+ new SimpleDateFormat("yyyy")
											.format(new SimpleDateFormat("yyyy/MM/dd").parse(dateOfDB)),
							Double.parseDouble(
									String.format("%.1f", summaryFatsValue / countSummaryValue).replace(",", ".")));

					strToCompare = new SimpleDateFormat("w")
							.format(new SimpleDateFormat("yyyy/MM/dd").parse(rs.getString("date")));

					dayToCompare = rs.getString("date");
				}

				lineChart
						.setTitle("Macroelements statistics divided into weeks - last " + carbsMap.size() + " results");
			}

			if (monthsTrend.isSelected()) {

				strToCompare = "";
				dayToCompare = "";

				while (rs.next()) {

					dateOfDB = rs.getString("date");
					monthOfDay = new SimpleDateFormat("MMM").format(new SimpleDateFormat("yyyy/MM/dd").parse(dateOfDB));

					if (dayToCompare.equals(dateOfDB) && strToCompare.equals(monthOfDay)) {
						countSummaryValue++;
					}

					if (strToCompare.equals(monthOfDay)) {
						summaryCarbsValue += rs.getDouble("Carbo");
						summaryWheysValue += rs.getDouble("Whey");
						summaryFatsValue += rs.getDouble("Fats");
					}

					else {
						countSummaryValue = 1;
						summaryCarbsValue = rs.getDouble("Carbo");
						summaryWheysValue = rs.getDouble("Whey");
						summaryFatsValue = rs.getDouble("Fats");
					}

					carbsMap.put(
							new SimpleDateFormat("MMM yyyy").format(new SimpleDateFormat("yyyy/MM/dd").parse(dateOfDB)),
							Double.parseDouble(
									String.format("%.1f", summaryCarbsValue / countSummaryValue).replace(",", ".")));

					wheysMap.put(
							new SimpleDateFormat("MMM yyyy").format(new SimpleDateFormat("yyyy/MM/dd").parse(dateOfDB)),
							Double.parseDouble(
									String.format("%.1f", summaryWheysValue / countSummaryValue).replace(",", ".")));

					fatsMap.put(
							new SimpleDateFormat("MMM yyyy").format(new SimpleDateFormat("yyyy/MM/dd").parse(dateOfDB)),
							Double.parseDouble(
									String.format("%.1f", summaryFatsValue / countSummaryValue).replace(",", ".")));

					strToCompare = new SimpleDateFormat("MMM")
							.format(new SimpleDateFormat("yyyy/MM/dd").parse(rs.getString("date")));

					dayToCompare = rs.getString("date");
				}

				lineChart.setTitle(
						"Macroelements statistics divided into months - last " + carbsMap.size() + " results");
			}

			if (carbsMap.size() <= 15) {
				startValue = 0;
			}

			else {
				startValue = carbsMap.size() - 15;
			}

			for (int i = startValue; i < carbsMap.size(); i++) {
				MapKey = carbsMap.keySet().toArray()[i];
				seriesCarbs.getData().add(new XYChart.Data<>(MapKey.toString(), carbsMap.get(MapKey)));

				MapKey = carbsMap.keySet().toArray()[i];
				seriesWheys.getData().add(new XYChart.Data<>(MapKey.toString(), wheysMap.get(MapKey)));

				MapKey = fatsMap.keySet().toArray()[i];
				seriesFats.getData().add(new XYChart.Data<>(MapKey.toString(), fatsMap.get(MapKey)));

			}

			conn.close();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE,
					deleteImage);
		}

		lineChart.getData().addAll(seriesCarbs, seriesWheys, seriesFats);
		VBox vbox = new VBox(lineChart);
		vbox.setBackground(Background.EMPTY);
		String style = "-fx-font-size: 1em;";
		vbox.setStyle(style);
		Scene scene = new Scene(vbox);
		scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
		scene.getStylesheets().add("style.css");
		return (scene);
	}

	@SuppressWarnings("unchecked")
	private static Scene createWeightChart() {

		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
		yAxis.setForceZeroInRange(false);

		seriesWeight = new XYChart.Series<>();
		seriesWeight.setName("Weight");

		try {
			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://phpmyadmin47.lh.pl:3306/serwer58262_Kcal?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&characterEncoding=utf-8";
			Class.forName(myDriver);

			Connection conn = DriverManager.getConnection(myUrl, "serwer58262_Kcal", "kcal00#");

			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT * FROM usersweight WHERE userID = " + LoginWindow.UserID + " ORDER BY date ASC");

			if (daysTrend.isSelected()) {
				while (rs.next()) {
					dateOfDB = rs.getString("date");
					weightMap.put(dateOfDB, rs.getDouble("bodyWeight"));
				}
				lineChart.setTitle("Weight statistics divided into days - last " + weightMap.size() + " results");
			}

			if (weeksTrend.isSelected()) {
				strToCompare = "";
				while (rs.next()) {
					dateOfDB = rs.getString("date");
					weekOfDay = new SimpleDateFormat("w").format(new SimpleDateFormat("yyyy/MM/dd").parse(dateOfDB));
					if (strToCompare.equals(weekOfDay)) {
						countSummaryValue++;
						summaryWeightValue += rs.getDouble("bodyWeight");
					} else {
						countSummaryValue = 1;
						summaryWeightValue = rs.getDouble("bodyWeight");
					}
					weightMap.put(
							weekOfDay + " week - "
									+ new SimpleDateFormat("yyyy")
											.format(new SimpleDateFormat("yyyy/MM/dd").parse(dateOfDB)),
							Double.parseDouble(
									String.format("%.1f", summaryWeightValue / countSummaryValue).replace(",", ".")));
					strToCompare = new SimpleDateFormat("w")
							.format(new SimpleDateFormat("yyyy/MM/dd").parse(rs.getString("date")));
				}
				lineChart.setTitle(
						"Weight statistics divided into week in year - last " + weightMap.size() + " results");
			}

			if (monthsTrend.isSelected()) {
				strToCompare = "";
				while (rs.next()) {
					dateOfDB = rs.getString("date");
					monthOfDay = new SimpleDateFormat("M").format(new SimpleDateFormat("yyyy/MM/dd").parse(dateOfDB));
					if (strToCompare.equals(monthOfDay)) {
						countSummaryValue++;
						summaryWeightValue += rs.getDouble("bodyWeight");
					} else {
						countSummaryValue = 1;
						summaryWeightValue = rs.getDouble("bodyWeight");
					}
					weightMap.put(
							new SimpleDateFormat("MMM yyyy").format(new SimpleDateFormat("yyyy/MM/dd").parse(dateOfDB)),
							Double.parseDouble(
									String.format("%.1f", summaryWeightValue / countSummaryValue).replace(",", ".")));
					strToCompare = new SimpleDateFormat("M")
							.format(new SimpleDateFormat("yyyy/MM/dd").parse(rs.getString("date")));
				}
				lineChart.setTitle("Weight statistics divided into months - last " + weightMap.size() + " results");
			}

			if (weightMap.size() <= 15) {
				for (int i = 0; i < weightMap.size(); i++) {
					MapKey = weightMap.keySet().toArray()[i];
					seriesWeight.getData().add(new XYChart.Data<>(MapKey.toString(), weightMap.get(MapKey)));
				}
			} else {
				for (int i = weightMap.size() - 15; i < weightMap.size(); i++) {
					MapKey = weightMap.keySet().toArray()[i];
					seriesWeight.getData().add(new XYChart.Data<>(MapKey.toString(), weightMap.get(MapKey)));
				}
			}

			conn.close();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE,
					deleteImage);
		}

		lineChart.getData().addAll(seriesWeight);

		VBox vbox = new VBox(lineChart);
		vbox.setBackground(Background.EMPTY);
		String style = "-fx-font-size: 1em;";
		vbox.setStyle(style);
		Scene scene = new Scene(vbox);
		scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
		scene.getStylesheets().add("style.css");

		return (scene);
	}
}