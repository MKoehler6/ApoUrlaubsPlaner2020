package apoPlaner2020;

import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
	
	

public class Hauptfenster extends JPanel {
	
	private ArrayList<Mitarbeiter> mitarbeiterArrayList;
	JPanel menu;
	JPanel main;
	JScrollPane scrollPane;
	JFrame fenster = new JFrame("Apotheken Urlaubsplaner");
	JButton test;
	Datum datum;
	JButton[][][] buttonArray = new JButton[52][5][2];
	DataModel dataModel;
	Controller controller;
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
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public void baueFensterNeu() 
	{
		main = new JPanel(new BorderLayout());
		scrollPane = new JScrollPane(kalenderPanel);
		menu = new JPanel(new GridLayout(9,1));
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
				new MAbearbeitenFenster(dataModel, controller);
			}
		});
		menu.add(maBearbeitenButton);
		JButton ferienEingeben = new JButton("Ferien eingeben");
		ferienEingeben.setOpaque(true);
		ferienEingeben.setBackground(new Color(120,210,85));
		ferienEingeben.setBorderPainted(true);
		ferienEingeben.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new FerienEingebenFenster(dataModel, controller);
			}
		});
		menu.add(ferienEingeben);
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
