package apoPlaner2020;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;

public class Controller extends WindowAdapter implements ActionListener {
	
	private final int VORM = 0;
	private final int NACHM = 1;
	private final Color ROT = new Color(255,69,0);
	private final Color BLAU = new Color(80, 150, 230);
	private final Color WEISS = new Color(255,255,255);
	private Hauptfenster hauptfenster;
	private KalenderPanel kalenderPanel;
	private DataModel dataModel;
	private int woche;
	private int tag;
	private int vormNachm;
	private Mitarbeiter mitarbeiter;
	private LadenUndSpeichern ladenUndSpeichern;
	
	
	public Controller(DataModel dataModel, KalenderPanel kalenderPanel, Hauptfenster hauptfenster) {
		this.dataModel = dataModel;
		this.hauptfenster = hauptfenster;
		this.kalenderPanel = kalenderPanel;
		ladenUndSpeichern = new LadenUndSpeichern(dataModel);
		ladenUndSpeichern.ladeDaten();
		kalenderPanel.setController(this);
		hauptfenster.setController(this);
		hauptfenster.fenster.addWindowListener(this);
		kalenderPanel.refresh();
		hauptfenster.fenster.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonClicked = (JButton) e.getSource();
		if (buttonClicked instanceof MAButton) {
			woche = ((MAButton) buttonClicked).getWoche();
			mitarbeiter = ((MAButton) buttonClicked).getMitarbeiter();
			System.out.println(woche + " " + mitarbeiter.getName());
		} else if (buttonClicked instanceof KalenderButton) {
			woche = ((KalenderButton) buttonClicked).getWoche();
			tag = ((KalenderButton) buttonClicked).getTag();
			vormNachm = ((KalenderButton) buttonClicked).getVormNachm();
			mitarbeiter = ((KalenderButton) buttonClicked).getMitarbeiter();
			if (mitarbeiter.getTag(woche-1, tag).isUrlaub()) {
				mitarbeiter.getTag(woche-1, tag).setUrlaub(false);
				setzeTagDienst(mitarbeiter, woche, tag);
			} else { 
				mitarbeiter.getTag(woche-1, tag).setUrlaub(true);
				setzeTagUrlaub(mitarbeiter, woche, tag);
			}
		}
	}
	
	private void setzeTagUrlaub(Mitarbeiter mitarbeiter, int woche, int tag) {
		mitarbeiter.getKalenderButton(woche, tag, VORM).setBackground(ROT);
		mitarbeiter.getKalenderButton(woche, tag, NACHM).setBackground(ROT);
	}
	private void setzeTagDienst(Mitarbeiter mitarbeiter, int woche, int tag) {
		mitarbeiter.getKalenderButton(woche, tag, VORM).setBackground(gibFarbeFuerDienstplan(woche, tag, VORM, mitarbeiter));
		mitarbeiter.getKalenderButton(woche, tag, NACHM).setBackground(gibFarbeFuerDienstplan(woche, tag, NACHM, mitarbeiter));
	}
	public Color gibFarbeFuerDienstplan(int woche, int tag, int vormNachm, Mitarbeiter mitarbeiter)
	{
		if(woche%2 == 0) {
			if (mitarbeiter.getDienstplan(woche).getGeradeWoche()[tag][vormNachm] == 1) {
				return BLAU;
			}
		} else {
			if (mitarbeiter.getDienstplan(woche).getUngeradeWoche()[tag][vormNachm] == 1) {
				return BLAU;
			}
		}
		return WEISS;
	}
	
	@Override
	public void windowClosing(WindowEvent e) 
	{
		ladenUndSpeichern.speichereDaten();
//		speichern.speichereJPG();
		System.out.println("Tschüs");
		System.exit(0);
	}
	
	public void updateView() {
		kalenderPanel.refresh();
	}

}
