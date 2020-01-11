package apoPlaner2020;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class KalenderPanel extends JPanel{
	
	private DataModel dataModel;
	private Controller controller;
	private ArrayList<Mitarbeiter> mitarbeiterArrayList;
	JPanel panelMA = new JPanel(new GridLayout(0,1));		
	JPanel panelMo = new JPanel(new GridLayout(0,2));		
	JPanel panelDi = new JPanel(new GridLayout(0,2));
	JPanel panelMi = new JPanel(new GridLayout(0,2));
	JPanel panelDo = new JPanel(new GridLayout(0,2));
	JPanel panelFr = new JPanel(new GridLayout(0,2));
	JPanel wochePanel = new JPanel(new GridLayout(1,6));
	Datum datum;

	public KalenderPanel(DataModel dataModel) {
		this.dataModel = dataModel;
		this.mitarbeiterArrayList = dataModel.getMitarbeiterArrayList();
		datum = new Datum();
		setLayout(new GridLayout(0,1)); 
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public void refresh() {
		removeAll();
		panelMA.removeAll();
		panelMo.removeAll();
		panelDi.removeAll();
		panelMi.removeAll();
		panelDo.removeAll();
		panelFr.removeAll();
		wochePanel.removeAll();
		
		for (int woche = 1; woche <= 52; woche++)
		{
			panelMA.add(new JLabel("Woche " + woche));
			JLabel datumLabel = new JLabel(datum.gibMontagVonWocheX(woche) + "  -  " + datum.gibFreitagVonWocheX(woche));
			datumLabel.setBackground(new Color(255,236,139));
			datumLabel.setOpaque(true);
			panelMA.add(datumLabel);
	//		https://stackoverflow.com/questions/5654208/making-a-jbutton-invisible-but-clickable
			for (Mitarbeiter mitarbeiter : mitarbeiterArrayList)
			{
				JButton b = new MAButton(woche, mitarbeiter);
				panelMA.add(b);
				b.setOpaque(true);
				b.setBackground(new Color(255, 255, 255));
				b.setBorderPainted(true);
				b.addActionListener(controller);
				b.setRolloverEnabled(false); // wenn Maus drÃ¼berfÃ¤hrt keine Hervorhebung
			}
			
			panelMo.add(new JLabel("MONTAG"));
			JLabel datumLabelMontag = new JLabel(" " + datum.gibMontagVonWocheX(woche));
			if (datum.ferien[woche - 1] == 1) datumLabelMontag.setBackground(new Color(179,238,58));
			datumLabelMontag.setOpaque(true);
			panelMo.add(datumLabelMontag);
			panelMo.add(new JLabel("vormittag"));
			panelMo.add(new JLabel("nachmittag"));
	//		https://stackoverflow.com/questions/5654208/making-a-jbutton-invisible-but-clickable
			for (Mitarbeiter mitarbeiter : mitarbeiterArrayList)
			{
				for (int j = 0; j < 2; j++) {
					JButton b = new KalenderButton(woche, 0, j, mitarbeiter);
					panelMo.add(b);
					b.setOpaque(true);
					b.setBackground(gibFarbeFuerDienstplan(woche, 0, j, mitarbeiter));
					b.setBorderPainted(true);
					b.setRolloverEnabled(false); // wenn Maus drÃ¼berfÃ¤hrt keine Hervorhebung
					b.addActionListener(controller);
				}
			}
			panelMo.setBorder(new EmptyBorder(0, 10, 0, 10));
			
			panelDi.add(new JLabel("DIENSTAG"));
			JLabel datumLabelDienstag = new JLabel(" " + datum.gibDienstagVonWocheX(woche));
			if (datum.ferien[woche - 1] == 1) datumLabelDienstag.setBackground(new Color(179,238,58));
			datumLabelDienstag.setOpaque(true);
			panelDi.add(datumLabelDienstag);
			panelDi.add(new JLabel("vormittag"));
			panelDi.add(new JLabel("nachmittag"));
	//		https://stackoverflow.com/questions/5654208/making-a-jbutton-invisible-but-clickable
			for (Mitarbeiter mitarbeiter : mitarbeiterArrayList)
			{
				for (int j = 0; j < 2; j++) {
					JButton b = new KalenderButton(woche, 1, j, mitarbeiter);
					panelDi.add(b);
					b.setOpaque(true);
					b.setBackground(gibFarbeFuerDienstplan(woche, 1, j, mitarbeiter));
					b.setBorderPainted(true);
					b.setRolloverEnabled(false); // wenn Maus drÃ¼berfÃ¤hrt keine Hervorhebung
					b.addActionListener(controller);
				}
			}
			panelDi.setBorder(new EmptyBorder(0, 0, 0, 10));
			
			panelMi.add(new JLabel("MITTWOCH"));
			JLabel datumLabelMittwoch = new JLabel(" " + datum.gibMittwochVonWocheX(woche));
			if (datum.ferien[woche - 1] == 1) datumLabelMittwoch.setBackground(new Color(179,238,58));
			datumLabelMittwoch.setOpaque(true);
			panelMi.add(datumLabelMittwoch);
			panelMi.add(new JLabel("vormittag"));
			panelMi.add(new JLabel("nachmittag"));
	//		https://stackoverflow.com/questions/5654208/making-a-jbutton-invisible-but-clickable
			for (Mitarbeiter mitarbeiter : mitarbeiterArrayList)
			{
				for (int j = 0; j < 2; j++) 
				{
					JButton b = new KalenderButton(woche, 2, j, mitarbeiter);
					panelMi.add(b);
					b.setOpaque(true);
					b.setBackground(gibFarbeFuerDienstplan(woche, 2, j, mitarbeiter));
					b.setBorderPainted(true);
					b.setRolloverEnabled(false); // wenn Maus drÃ¼berfÃ¤hrt keine Hervorhebung
					b.addActionListener(controller);
				}
			}
			panelMi.setBorder(new EmptyBorder(0, 0, 0, 10));
			
			panelDo.add(new JLabel("DONNERSTAG"));
			JLabel datumLabelDonnerstag = new JLabel(" " + datum.gibDonnerstagVonWocheX(woche));
			if (datum.ferien[woche - 1] == 1) datumLabelDonnerstag.setBackground(new Color(179,238,58));
			datumLabelDonnerstag.setOpaque(true);
			panelDo.add(datumLabelDonnerstag);
			panelDo.add(new JLabel("vormittag"));
			panelDo.add(new JLabel("nachmittag"));
			//		https://stackoverflow.com/questions/5654208/making-a-jbutton-invisible-but-clickable
			for (Mitarbeiter mitarbeiter : mitarbeiterArrayList)
			{
				for (int j = 0; j < 2; j++) 
				{
					JButton b = new KalenderButton(woche, 3, j, mitarbeiter);
					panelDo.add(b);
					b.setOpaque(true);
					b.setBackground(gibFarbeFuerDienstplan(woche, 3, j, mitarbeiter));
					b.setBorderPainted(true);
					b.setRolloverEnabled(false); // wenn Maus drÃ¼berfÃ¤hrt keine Hervorhebung
					b.addActionListener(controller);
				}
			}
			panelDo.setBorder(new EmptyBorder(0, 0, 0, 10));
			
			panelFr.add(new JLabel("FREITAG"));
			JLabel datumLabelFreitag = new JLabel(" " + datum.gibFreitagVonWocheX(woche));
			if (datum.ferien[woche - 1] == 1) datumLabelFreitag.setBackground(new Color(179,238,58));
			datumLabelFreitag.setOpaque(true);
			panelFr.add(datumLabelFreitag);
			panelFr.add(new JLabel("vormittag"));
			panelFr.add(new JLabel("nachmittag"));
			//		https://stackoverflow.com/questions/5654208/making-a-jbutton-invisible-but-clickable
			for (Mitarbeiter mitarbeiter : mitarbeiterArrayList)
			{
				for (int j = 0; j < 2; j++) 
				{
					JButton b = new KalenderButton(woche, 4, j, mitarbeiter);
					panelFr.add(b);
					b.setOpaque(true);
					b.setBackground(gibFarbeFuerDienstplan(woche, 4, j, mitarbeiter));
					b.setBorderPainted(true);
					b.setRolloverEnabled(false); // wenn Maus drÃ¼berfÃ¤hrt keine Hervorhebung
					b.addActionListener(controller);
				}
			}
			panelFr.setBorder(new EmptyBorder(0, 0, 0, 10));
			
			wochePanel.add(panelMA);
			wochePanel.add(panelMo);
			wochePanel.add(panelDi);
			wochePanel.add(panelMi);
			wochePanel.add(panelDo);
			wochePanel.add(panelFr);

			add(wochePanel);
		}
		revalidate();
	}
	public Color gibFarbeFuerDienstplan(int woche, int tag, int vormNachm, Mitarbeiter mitarbeiter)
	{
		if(woche%2 == 0) {
			if (mitarbeiter.getDienstplan(woche).getGeradeWoche()[tag][vormNachm] == 1)
			{
				return new Color(80, 150, 230); //blau
			}
		} else {
			if (mitarbeiter.getDienstplan(woche).getUngeradeWoche()[tag][vormNachm] == 1)
			{
				return new Color(80, 150, 230); //blau
			}
		}
		return new Color(255, 255, 255);
	}

}
