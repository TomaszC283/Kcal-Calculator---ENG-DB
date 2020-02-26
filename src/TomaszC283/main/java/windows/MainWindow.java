package TomaszC283.main.java.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import TomaszC283.main.java.windows.AddProductWindow;

public class MainWindow extends JFrame {
	// Elemenety GUI

	private static JComboBox<String> comboBox = new JComboBox<String>();
	private static JSlider SuwakKalorii = new JSlider(0, 3000, 0);
	private static JTextField KWeglowodanyTotal = new JTextField(13);
	private static JTextField KBialkoTotal = new JTextField(13);
	private static JTextField KTluszczeTotal = new JTextField(13);
	private static JTextField KPodsumowanieTotal = new JTextField(13);
	private static JList<String> JListaGlowna = new JList<>();

	private static JTextField TXTPosilek1W = new JTextField(6);
	private static JTextField TXTPosilek1B = new JTextField(6);
	private static JTextField TXTPosilek1T = new JTextField(6);
	private static JTextField TXTPosilek2W = new JTextField(6);
	private static JTextField TXTPosilek2B = new JTextField(6);
	private static JTextField TXTPosilek2T = new JTextField(6);
	private static JTextField TXTPosilek3W = new JTextField(6);
	private static JTextField TXTPosilek3B = new JTextField(6);
	private static JTextField TXTPosilek3T = new JTextField(6);
	private static JTextField TXTPosilek4W = new JTextField(6);
	private static JTextField TXTPosilek4B = new JTextField(6);
	private static JTextField TXTPosilek4T = new JTextField(6);
	private static JTextField TXTPosilek5W = new JTextField(6);
	private static JTextField TXTPosilek5B = new JTextField(6);
	private static JTextField TXTPosilek5T = new JTextField(6);

	// Icons
	ImageIcon deleteImage = new ImageIcon("src/TomaszC283/main/java/resources/delete.png");
	ImageIcon carrotImage = new ImageIcon("src/TomaszC283/main/java/resources/carrot.png");
	ImageIcon plusImage = new ImageIcon("src/TomaszC283/main/java/resources/plus.png");
	ImageIcon cancelImage = new ImageIcon("src/TomaszC283/main/java/resources/cancel.png");
	ImageIcon upsImage = new ImageIcon("src/TomaszC283/main/java/resources/ups.png");
	ImageIcon removeImage = new ImageIcon("src/TomaszC283/main/java/resources/remove.png");

	public MainWindow() {

		super("Fitness Calculator");
		setSize(1100, 600);
		setLocation(100, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// MainPanel

		Container mainContainer = getContentPane();
		mainContainer.setLayout(new BorderLayout(6, 3));
		mainContainer.setBackground(new Color(245, 255, 255));

		// Bottoms

		JButton ButtonAddNewProd = new JButton("     Add new product     ", carrotImage);
		JButton ButtonClearList = new JButton("   Wyczyść listę   ", cancelImage);
		JButton ButtonAddProd = new JButton("    Dodaj       ", plusImage);
		JButton ButtonDeleteProd = new JButton("     Usuń produkt      ", deleteImage);
		JButton ButtonRemoveProd = new JButton("    Usuń posiłek z listy  ", removeImage);

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

		// GÓRNY PANEL

		JPanel AuxTopPanel1 = new JPanel();
		JPanel AuxTopPanel2 = new JPanel();
		JPanel AuxTopPanel3 = new JPanel();
		JPanel AuxTopPanel4 = new JPanel();
		JPanel AuxTopPanel5 = new JPanel();
		JPanel AuxTopPanel6 = new JPanel();
		JPanel AuxTopPanel7 = new JPanel();

		JTextField MasaTF = new JTextField(9);
		JTextField CarboTF = new JTextField(9);
		JTextField WheyTF = new JTextField(9);
		JTextField FatsTF = new JTextField(9);
		JTextField SumaTF = new JTextField(9);

		MasaTF.setBorder(new LineBorder(new Color(48, 213, 200), 2));
		CarboTF.setBorder(new LineBorder(new Color(48, 213, 200), 2));
		WheyTF.setBorder(new LineBorder(new Color(48, 213, 200), 2));
		FatsTF.setBorder(new LineBorder(new Color(48, 213, 200), 2));
		SumaTF.setBorder(new LineBorder(new Color(48, 213, 200), 2));

		MasaTF.setText("100");
		MasaTF.setHorizontalAlignment(JTextField.CENTER);
		CarboTF.setEnabled(false);
		WheyTF.setEnabled(false);
		FatsTF.setEnabled(false);
		SumaTF.setEnabled(false);

		MasaTF.setForeground(Color.RED);
		CarboTF.setDisabledTextColor(Color.RED);
		WheyTF.setDisabledTextColor(Color.RED);
		FatsTF.setDisabledTextColor(Color.RED);
		SumaTF.setDisabledTextColor(Color.RED);

		MasaTF.setBackground(new Color(204, 255, 255));
		CarboTF.setBackground(new Color(204, 255, 255));
		WheyTF.setBackground(new Color(204, 255, 255));
		FatsTF.setBackground(new Color(204, 255, 255));
		SumaTF.setBackground(new Color(204, 255, 255));

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
		JLabel TittleNr = new JLabel("  Nr of Meal:  ");

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

		String[] MealNumber = { "1", "2", "3", "4", "5" };
		JComboBox comboBoxNr = new JComboBox();
		comboBoxNr.setModel(new DefaultComboBoxModel(MealNumber));
		comboBoxNr.setForeground(Color.RED);
		comboBoxNr.setBackground(new Color(204, 255, 255));

		// Dodanie Produktu do listy

		AuxTopPanel1.add(Tittle1);
		AuxTopPanel1.add(MasaTF);
		AuxTopPanel2.add(Tittle2);
		AuxTopPanel2.add(CarboTF);
		AuxTopPanel3.add(Tittle3);
		AuxTopPanel3.add(WheyTF);
		AuxTopPanel4.add(Tittle4);
		AuxTopPanel4.add(FatsTF);
		AuxTopPanel5.add(Tittle5);
		AuxTopPanel5.add(SumaTF);
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

		// ŚRODKOWY PANEL

		JPanel middlePanel = new JPanel();
		JListaGlowna.setForeground(Color.BLUE);
		JListaGlowna.setBackground(new Color(245, 255, 255));
		JListaGlowna.setFont(new Font("Helvetica", Font.PLAIN, 12));

		SuwakKalorii.setMajorTickSpacing(200);
		SuwakKalorii.setMinorTickSpacing(50);
		SuwakKalorii.setPaintTicks(true);
		SuwakKalorii.setPaintLabels(true);
		SuwakKalorii.setForeground(Color.RED);
		SuwakKalorii.setBackground(new Color(245, 255, 255));
		SuwakKalorii.setFont(new Font("Helvetica", Font.ITALIC, 11));

		mainContainer.add(middlePanel);
		middlePanel.setBorder(new LineBorder(new Color(48, 213, 200), 3));
		middlePanel.setLayout(new BorderLayout());
		middlePanel.add(JListaGlowna, BorderLayout.CENTER);
		middlePanel.add(SuwakKalorii, BorderLayout.SOUTH);

		// DOLNY PANEL

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

		KWeglowodanyTotal.setBorder(new LineBorder(new Color(48, 213, 200), 2));
		KBialkoTotal.setBorder(new LineBorder(new Color(48, 213, 200), 2));
		KTluszczeTotal.setBorder(new LineBorder(new Color(48, 213, 200), 2));
		KPodsumowanieTotal.setBorder(new LineBorder(Color.RED, 2));

		KWeglowodanyTotal.setHorizontalAlignment(JTextField.CENTER);
		KBialkoTotal.setHorizontalAlignment(JTextField.CENTER);
		KTluszczeTotal.setHorizontalAlignment(JTextField.CENTER);
		KPodsumowanieTotal.setHorizontalAlignment(JTextField.CENTER);

		KWeglowodanyTotal.setBackground(new Color(204, 255, 255));
		KBialkoTotal.setBackground(new Color(204, 255, 255));
		KTluszczeTotal.setBackground(new Color(204, 255, 255));
		KPodsumowanieTotal.setBackground(new Color(204, 255, 255));

		KWeglowodanyTotal.setDisabledTextColor(Color.RED);
		KBialkoTotal.setDisabledTextColor(Color.RED);
		KTluszczeTotal.setDisabledTextColor(Color.RED);
		KPodsumowanieTotal.setDisabledTextColor(Color.RED);

		KWeglowodanyTotal.setEnabled(false);
		KBialkoTotal.setEnabled(false);
		KTluszczeTotal.setEnabled(false);
		KPodsumowanieTotal.setEnabled(false);

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
		panel8.add(KWeglowodanyTotal);
		panel9.add(Tittle9);
		panel9.add(KBialkoTotal);
		panel10.add(Tittle10);
		panel10.add(KTluszczeTotal);
		panel11.add(Tittle11);
		panel11.add(KPodsumowanieTotal);

		bottomPanel1.add(bottomPanel2);
		bottomPanel2.add(Tittle7);
		bottomPanel1.add(bottomPanel3);
		bottomPanel3.add(panel8);
		bottomPanel3.add(panel9);
		bottomPanel3.add(panel10);
		bottomPanel3.add(panel11);

		// Panel Boczny
		JPanel panelEast1 = new JPanel();
		JPanel panelEast2 = new JPanel();
		JPanel panelEast2a = new JPanel();
		JPanel panelEast3 = new JPanel();
		JPanel panelEast3a = new JPanel();
		JPanel panelEast4 = new JPanel();
		JPanel panelEast4a = new JPanel();
		JPanel panelEast5 = new JPanel();
		JPanel panelEast5a = new JPanel();
		JPanel panelEast6 = new JPanel();
		JPanel panelEast6a = new JPanel();

		panelEast1.setBorder(new LineBorder(new Color(48, 213, 200), 3));

		panelEast1.setBackground(new Color(245, 255, 255));
		panelEast2.setBackground(new Color(245, 255, 255));
		panelEast3.setBackground(new Color(245, 255, 255));
		panelEast4.setBackground(new Color(245, 255, 255));
		panelEast5.setBackground(new Color(245, 255, 255));
		panelEast6.setBackground(new Color(245, 255, 255));
		panelEast2a.setBackground(new Color(245, 255, 255));
		panelEast3a.setBackground(new Color(245, 255, 255));
		panelEast4a.setBackground(new Color(245, 255, 255));
		panelEast5a.setBackground(new Color(245, 255, 255));
		panelEast6a.setBackground(new Color(245, 255, 255));

		panelEast1.setLayout(new GridLayout(5, 1));
		panelEast2.setLayout(new GridLayout(3, 1));
		panelEast3.setLayout(new GridLayout(3, 1));
		panelEast4.setLayout(new GridLayout(3, 1));
		panelEast5.setLayout(new GridLayout(3, 1));
		panelEast6.setLayout(new GridLayout(3, 1));
		panelEast2a.setLayout(new GridLayout(1, 3));
		panelEast3a.setLayout(new GridLayout(1, 3));
		panelEast4a.setLayout(new GridLayout(1, 3));
		panelEast5a.setLayout(new GridLayout(1, 3));
		panelEast6a.setLayout(new GridLayout(1, 3));

		mainContainer.add(panelEast1, BorderLayout.EAST);
		panelEast1.add(panelEast2);
		panelEast1.add(panelEast3);
		panelEast1.add(panelEast4);
		panelEast1.add(panelEast5);
		panelEast1.add(panelEast6);

		JLabel Posilek1 = new JLabel("                         Posiłek 1                          ");
		JLabel Posilek2 = new JLabel("                         Posiłek 2                          ");
		JLabel Posilek3 = new JLabel("                         Posiłek 3                          ");
		JLabel Posilek4 = new JLabel("                         Posiłek 4                          ");
		JLabel Posilek5 = new JLabel("                         Posiłek 5                          ");
		JLabel Posilek1WBT = new JLabel("          W                   B                    T  ");
		JLabel Posilek2WBT = new JLabel("          W                   B                    T  ");
		JLabel Posilek3WBT = new JLabel("          W                   B                    T  ");
		JLabel Posilek4WBT = new JLabel("          W                   B                    T  ");
		JLabel Posilek5WBT = new JLabel("          W                   B                    T  ");

		Font underline = Posilek1.getFont();
		Map attributes = underline.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		Posilek1.setFont(underline.deriveFont(attributes));
		Posilek2.setFont(underline.deriveFont(attributes));
		Posilek3.setFont(underline.deriveFont(attributes));
		Posilek4.setFont(underline.deriveFont(attributes));
		Posilek5.setFont(underline.deriveFont(attributes));

		Posilek1.setForeground(Color.BLUE);
		Posilek2.setForeground(Color.BLUE);
		Posilek3.setForeground(Color.BLUE);
		Posilek4.setForeground(Color.BLUE);
		Posilek5.setForeground(Color.BLUE);
		Posilek1WBT.setForeground(Color.RED);
		Posilek2WBT.setForeground(Color.RED);
		Posilek3WBT.setForeground(Color.RED);
		Posilek4WBT.setForeground(Color.RED);
		Posilek5WBT.setForeground(Color.RED);

		TXTPosilek1W.setDisabledTextColor(Color.BLUE);
		TXTPosilek1B.setDisabledTextColor(Color.BLUE);
		TXTPosilek1T.setDisabledTextColor(Color.BLUE);
		TXTPosilek2W.setDisabledTextColor(Color.BLUE);
		TXTPosilek2B.setDisabledTextColor(Color.BLUE);
		TXTPosilek2T.setDisabledTextColor(Color.BLUE);
		TXTPosilek3W.setDisabledTextColor(Color.BLUE);
		TXTPosilek3B.setDisabledTextColor(Color.BLUE);
		TXTPosilek3T.setDisabledTextColor(Color.BLUE);
		TXTPosilek4W.setDisabledTextColor(Color.BLUE);
		TXTPosilek4B.setDisabledTextColor(Color.BLUE);
		TXTPosilek4T.setDisabledTextColor(Color.BLUE);
		TXTPosilek5W.setDisabledTextColor(Color.BLUE);
		TXTPosilek5B.setDisabledTextColor(Color.BLUE);
		TXTPosilek5T.setDisabledTextColor(Color.BLUE);

		TXTPosilek1W.setHorizontalAlignment(JTextField.CENTER);
		TXTPosilek1B.setHorizontalAlignment(JTextField.CENTER);
		TXTPosilek1T.setHorizontalAlignment(JTextField.CENTER);
		TXTPosilek2W.setHorizontalAlignment(JTextField.CENTER);
		TXTPosilek2B.setHorizontalAlignment(JTextField.CENTER);
		TXTPosilek2T.setHorizontalAlignment(JTextField.CENTER);
		TXTPosilek3W.setHorizontalAlignment(JTextField.CENTER);
		TXTPosilek3B.setHorizontalAlignment(JTextField.CENTER);
		TXTPosilek3T.setHorizontalAlignment(JTextField.CENTER);
		TXTPosilek4W.setHorizontalAlignment(JTextField.CENTER);
		TXTPosilek4B.setHorizontalAlignment(JTextField.CENTER);
		TXTPosilek4T.setHorizontalAlignment(JTextField.CENTER);
		TXTPosilek5W.setHorizontalAlignment(JTextField.CENTER);
		TXTPosilek5B.setHorizontalAlignment(JTextField.CENTER);
		TXTPosilek5T.setHorizontalAlignment(JTextField.CENTER);

		TXTPosilek1W.setBorder(new LineBorder(Color.BLUE, 1));
		TXTPosilek1B.setBorder(new LineBorder(Color.BLUE, 1));
		TXTPosilek1T.setBorder(new LineBorder(Color.BLUE, 1));
		TXTPosilek2W.setBorder(new LineBorder(Color.BLUE, 1));
		TXTPosilek2B.setBorder(new LineBorder(Color.BLUE, 1));
		TXTPosilek2T.setBorder(new LineBorder(Color.BLUE, 1));
		TXTPosilek3W.setBorder(new LineBorder(Color.BLUE, 1));
		TXTPosilek3B.setBorder(new LineBorder(Color.BLUE, 1));
		TXTPosilek3T.setBorder(new LineBorder(Color.BLUE, 1));
		TXTPosilek4W.setBorder(new LineBorder(Color.BLUE, 1));
		TXTPosilek4B.setBorder(new LineBorder(Color.BLUE, 1));
		TXTPosilek4T.setBorder(new LineBorder(Color.BLUE, 1));
		TXTPosilek5W.setBorder(new LineBorder(Color.BLUE, 1));
		TXTPosilek5B.setBorder(new LineBorder(Color.BLUE, 1));
		TXTPosilek5T.setBorder(new LineBorder(Color.BLUE, 1));

		TXTPosilek1W.setBackground(new Color(204, 255, 255));
		TXTPosilek1B.setBackground(new Color(204, 255, 255));
		TXTPosilek1T.setBackground(new Color(204, 255, 255));
		TXTPosilek2W.setBackground(new Color(204, 255, 255));
		TXTPosilek2B.setBackground(new Color(204, 255, 255));
		TXTPosilek2T.setBackground(new Color(204, 255, 255));
		TXTPosilek3W.setBackground(new Color(204, 255, 255));
		TXTPosilek3B.setBackground(new Color(204, 255, 255));
		TXTPosilek3T.setBackground(new Color(204, 255, 255));
		TXTPosilek4W.setBackground(new Color(204, 255, 255));
		TXTPosilek4B.setBackground(new Color(204, 255, 255));
		TXTPosilek4T.setBackground(new Color(204, 255, 255));
		TXTPosilek5W.setBackground(new Color(204, 255, 255));
		TXTPosilek5B.setBackground(new Color(204, 255, 255));
		TXTPosilek5T.setBackground(new Color(204, 255, 255));

		TXTPosilek1W.setEnabled(false);
		TXTPosilek1B.setEnabled(false);
		TXTPosilek1T.setEnabled(false);
		TXTPosilek2W.setEnabled(false);
		TXTPosilek2B.setEnabled(false);
		TXTPosilek2T.setEnabled(false);
		TXTPosilek3W.setEnabled(false);
		TXTPosilek3B.setEnabled(false);
		TXTPosilek3T.setEnabled(false);
		TXTPosilek4W.setEnabled(false);
		TXTPosilek4B.setEnabled(false);
		TXTPosilek4T.setEnabled(false);
		TXTPosilek5W.setEnabled(false);
		TXTPosilek5B.setEnabled(false);
		TXTPosilek5T.setEnabled(false);

		panelEast2.add(Posilek1);
		panelEast2.add(Posilek1WBT);
		panelEast2.add(panelEast2a);
		panelEast2a.add(TXTPosilek1W);
		panelEast2a.add(TXTPosilek1B);
		panelEast2a.add(TXTPosilek1T);

		panelEast3.add(Posilek2);
		panelEast3.add(Posilek2WBT);
		panelEast3.add(panelEast3a);
		panelEast3a.add(TXTPosilek2W);
		panelEast3a.add(TXTPosilek2B);
		panelEast3a.add(TXTPosilek2T);

		panelEast4.add(Posilek3);
		panelEast4.add(Posilek3WBT);
		panelEast4.add(panelEast4a);
		panelEast4a.add(TXTPosilek3W);
		panelEast4a.add(TXTPosilek3B);
		panelEast4a.add(TXTPosilek3T);

		panelEast5.add(Posilek4);
		panelEast5.add(Posilek4WBT);
		panelEast5.add(panelEast5a);
		panelEast5a.add(TXTPosilek4W);
		panelEast5a.add(TXTPosilek4B);
		panelEast5a.add(TXTPosilek4T);

		panelEast6.add(Posilek5);
		panelEast6.add(Posilek5WBT);
		panelEast6.add(panelEast6a);
		panelEast6a.add(TXTPosilek5W);
		panelEast6a.add(TXTPosilek5B);
		panelEast6a.add(TXTPosilek5T);
		
		// Actions
		
		// Otwieranie nowego Okna z dodawaniem produktu
		ButtonAddNewProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddProductWindow nw = new AddProductWindow();
				nw.NewWindow();
			}
		});
	}
}
