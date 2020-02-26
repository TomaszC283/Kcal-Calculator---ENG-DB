package TomaszC283.main.java;

import TomaszC283.main.java.Products;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JOptionPane;

import TomaszC283.main.java.DailyProducts;

public class Main {
	public static void main(String[] args)
	{
		Products products = new Products();
		products.setProductCarbo(50);
		System.out.println(products.getProductCarbo());
		products.setProductCarbo(20);
		products.setProductCarbo(30);
		System.out.println(products.getProductCarbo());
	}
}
