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
	JPanel main = new JPanel(new BorderLayout());
	JFrame fenster = new JFrame("Apotheken Urlaubsplaner");
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
			for (int i = 0; i < mitarbeiterArrayList.size(); i++)
			{
				for (int j = 0; j < 2; j++) {
					JButton b = new KalenderButton(woche, 0, j, mitarbeiterArrayList.get(i));
					panelMo.add(b);
					b.setOpaque(true);
					b.setBackground(gibFarbeFuerDienstplan(0, 0, woche));
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
			for (int i = 0; i < mitarbeiterArrayList.size(); i++)
			{
				for (int j = 0; j < 2; j++) {
					JButton b = new KalenderButton(woche, 1, j, mitarbeiterArrayList.get(i));
					panelDi.add(b);
					b.setOpaque(true);
					b.setBackground(gibFarbeFuerDienstplan(0, 0, woche));
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
			for (int i = 0; i < mitarbeiterArrayList.size(); i++)
			{
				for (int j = 0; j < 2; j++) 
				{
					JButton b = new KalenderButton(woche, 2, j, mitarbeiterArrayList.get(i));
					panelMi.add(b);
					b.setOpaque(true);
					b.setBackground(gibFarbeFuerDienstplan(0, 0, woche));
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
			for (int i = 0; i < mitarbeiterArrayList.size(); i++)
			{
				for (int j = 0; j < 2; j++) 
				{
					JButton b = new KalenderButton(woche, 3, j, mitarbeiterArrayList.get(i));
					panelDo.add(b);
					b.setOpaque(true);
					b.setBackground(gibFarbeFuerDienstplan(0, 0, woche));
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
			for (int i = 0; i < mitarbeiterArrayList.size(); i++)
			{
				for (int j = 0; j < 2; j++) 
				{
					JButton b = new KalenderButton(woche, 4, j, mitarbeiterArrayList.get(i));
					panelFr.add(b);
					b.setOpaque(true);
					b.setBackground(gibFarbeFuerDienstplan(0, 0, woche));
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
	public Color gibFarbeFuerDienstplan(int mitarbeiter, int tag, int woche)
	{
//		int geradeUngerade = woche%2;
//		if (dienstplan[mitarbeiter/2][geradeUngerade][tag][mitarbeiter%2] == 1)
//		{
//			return new Color(80, 150, 230); //blau
//		}
//		if (dienstplan[mitarbeiter/2][geradeUngerade][tag][mitarbeiter%2] == 0)
//		{
//			return new Color(255, 255, 255);
//		}
		return new Color(255, 255, 255);
	}

}
