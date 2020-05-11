package TomaszC283.main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import TomaszC283.main.java.windows.LoginWindow;

public class Main {
	
	public static Set<String> productNameList = new TreeSet<>();
	public static Map<String, Double> productCarbsMap = new HashMap<>();
	public static Map<String, Double> productWheyMap = new HashMap<>();
	public static Map<String, Double> productFatsMap = new HashMap<>();
	static ImageIcon deleteImage = new ImageIcon("src/TomaszC283/main/java/resources/delete.png");
	private static String productName;
	private static double productCarbo;
	private static double productWhey;
	private static double productFats;
	
	public static void main(String[] args) {

		LoginWindow lw = new LoginWindow();
		lw.setVisible(true);
		
		CreateProductMaps();
	}
	
	public static void CreateProductMaps() {
		try {

			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://phpmyadmin47.lh.pl:3306/serwer58262_Kcal?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&characterEncoding=utf-8";
			Class.forName(myDriver);

			Connection conn = DriverManager.getConnection(myUrl, "serwer58262_Kcal", "kcal00#");

			Statement st = conn.createStatement();


			ResultSet rs = st.executeQuery("SELECT * FROM products ORDER By ProductName ASC");
			
			while (rs.next()) {
				productName = rs.getString("ProductName");
				productCarbo = rs.getDouble("ProductCarbo");
				productWhey = rs.getDouble("ProductWhey");
				productFats = rs.getDouble("ProductFats");
				
				productNameList.add(productName);
				productCarbsMap.put(productName, productCarbo);
				productWheyMap.put(productName, productWhey);
				productFatsMap.put(productName, productFats);
				
			}

			conn.close();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.INFORMATION_MESSAGE,
					deleteImage);
		}
	}
}
