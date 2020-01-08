package apoPlaner2020;

import java.io.IOException;
import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
	
	

public class Hauptfenster extends JPanel {
	
	private ArrayList<Mitarbeiter> mitarbeiterArrayList;
	JPanel menu = new JPanel(new GridLayout(9,1));
	JPanel panelMA = new JPanel(new GridLayout(0,1));		
	JPanel panelMo = new JPanel(new GridLayout(0,2));		
	JPanel panelDi = new JPanel(new GridLayout(0,2));
	JPanel panelMi = new JPanel(new GridLayout(0,2));
	JPanel panelDo = new JPanel(new GridLayout(0,2));
	JPanel panelFr = new JPanel(new GridLayout(0,2));
	JPanel woche = new JPanel(new GridLayout(1,6));
	JPanel scrollbereich = new JPanel(new GridLayout(0,1));
	JPanel main = new JPanel(new BorderLayout());
	JFrame fenster = new JFrame("Apotheken Urlaubsplaner");
	JButton test;
	Datum datum;
	JButton[][][] buttonArray = new JButton[52][5][2];
	DataModel dataModel;
	KalenderPanel kalenderPanel;
		
	public Hauptfenster(DataModel dataModel, KalenderPanel kalenderPanel)
	{
		this.dataModel = dataModel;
		this.kalenderPanel = kalenderPanel;
		mitarbeiterArrayList = dataModel.getMitarbeiterArrayList();
		datum = new Datum();
		fenster.setSize(1300, 900);
		fenster.setLocationRelativeTo(null);
		baueFensterNeu();
		fenster.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) 
			{
//					try 
//					{
//						fenster.setVisible(false);
//						speichern.speichereDatei();
//						speichern.speichereJPG();
//					} catch (IOException ex) 
//					{
//						ex.printStackTrace();
//					}
				System.exit(0);
			}
		});
		fenster.setVisible(true);
	}
	
	public void baueFensterNeu() 
	{
		JScrollPane scrollPane = new JScrollPane(kalenderPanel);
		// Menuebereich
		JLabel menuLabel = new JLabel("Menü", JLabel.CENTER);
		menuLabel.setOpaque(true);
		menuLabel.setBackground(new Color(190, 190, 190));
		menuLabel.setFont(new Font("Arial", 1, 14));
		menu.add(menuLabel);
		JButton maBearbeitenButton = new JButton("Mitarbeiter bearbeiten");
		maBearbeitenButton.setOpaque(true);
		maBearbeitenButton.setBackground(new Color(120,210,85));
		maBearbeitenButton.setBorderPainted(true);
		maBearbeitenButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		menu.add(maBearbeitenButton);
		JButton dienstplanButton = new JButton("Dienstplan bearbeiten");
		dienstplanButton.setOpaque(true);
		dienstplanButton.setBackground(new Color(120,210,85));
		dienstplanButton.setBorderPainted(true);
		menu.add(dienstplanButton);
		main.add(menu, BorderLayout.WEST);
		main.add(scrollPane, BorderLayout.CENTER);
		fenster.add(main);
	}
	
	
	
	public void konsolenAusgabe() {
		System.out.println("========================================================================================================");
		for (int woche = 0; woche < 52; woche++) {
			for (Mitarbeiter ma : mitarbeiterArrayList) {
				System.out.print(ma.getName() + " ");
				for (int tag = 0; tag < 5; tag++) {
					if (ma.getTag(woche, tag).isUrlaub())
						System.out.print("U U  ");
					else {
						if (ma.getTag(woche, tag).isDienstVormittag())
							System.out.print("D ");
						else System.out.print("_ ");
						if (ma.getTag(woche, tag).isDienstNachmittag())
							System.out.print("D  ");
						else System.out.print("_  ");
					}
				}
				System.out.println();
			}
			System.out.println();
		}
	}

}
