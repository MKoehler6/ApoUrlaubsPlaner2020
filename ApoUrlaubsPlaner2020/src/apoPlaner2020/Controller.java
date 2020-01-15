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
			int zaehlerUrlaubstageInWoche = 0;
			for (int tag = 0; tag < 5; tag++) {
				if (mitarbeiter.getTag(woche-1, tag).isUrlaub()) zaehlerUrlaubstageInWoche++;
			}
			if (zaehlerUrlaubstageInWoche < 5) {
				setzeWocheUrlaub(mitarbeiter, woche);
				aktualisiereUrlaubAufMAButtons();
			} else {
				setzeWocheDienst(mitarbeiter, woche);
				aktualisiereUrlaubAufMAButtons();
			}
		} else if (buttonClicked instanceof KalenderButton) {
			woche = ((KalenderButton) buttonClicked).getWoche();
			tag = ((KalenderButton) buttonClicked).getTag();
			vormNachm = ((KalenderButton) buttonClicked).getVormNachm();
			mitarbeiter = ((KalenderButton) buttonClicked).getMitarbeiter();
			if (mitarbeiter.getTag(woche-1, tag).isUrlaub()) {
				setzeTagDienst(mitarbeiter, woche, tag);
				aktualisiereUrlaubAufMAButtons();
			} else { 
				setzeTagUrlaub(mitarbeiter, woche, tag);
				aktualisiereUrlaubAufMAButtons();
			}
		}
	}
	
	private void setzeTagUrlaub(Mitarbeiter mitarbeiter, int woche, int tag) {
		mitarbeiter.getKalenderButton(woche, tag, VORM).setBackground(ROT);
		mitarbeiter.getKalenderButton(woche, tag, NACHM).setBackground(ROT);
		mitarbeiter.setTagUrlaub(woche, tag);
	}
	private void setzeTagDienst(Mitarbeiter mitarbeiter, int woche, int tag) {
		mitarbeiter.getKalenderButton(woche, tag, VORM).setBackground(gibFarbeFuerDienstplan(woche, tag, VORM, mitarbeiter));
		mitarbeiter.getKalenderButton(woche, tag, NACHM).setBackground(gibFarbeFuerDienstplan(woche, tag, NACHM, mitarbeiter));
		mitarbeiter.setTagDienst(woche, tag);
	}
	private void setzeWocheUrlaub(Mitarbeiter mitarbeiter, int woche) {
		for (int tag = 0; tag < 5; tag++) {
			if (!mitarbeiter.getTag(woche-1, tag).isUrlaub()) {
				mitarbeiter.getKalenderButton(woche, tag, VORM).setBackground(ROT);
				mitarbeiter.getKalenderButton(woche, tag, NACHM).setBackground(ROT);
				mitarbeiter.setTagUrlaub(woche, tag);
			}
		}
	}
	private void setzeWocheDienst(Mitarbeiter mitarbeiter, int woche) {
		for (int tag = 0; tag < 5; tag++) {
			if (mitarbeiter.getTag(woche-1, tag).isUrlaub()) {
				mitarbeiter.getKalenderButton(woche, tag, VORM).setBackground(gibFarbeFuerDienstplan(woche, tag, VORM, mitarbeiter));
				mitarbeiter.getKalenderButton(woche, tag, NACHM).setBackground(gibFarbeFuerDienstplan(woche, tag, NACHM, mitarbeiter));
				mitarbeiter.setTagDienst(woche, tag);
			}
		}
	}
	public void aktualisiereUrlaubAufMAButtons() {
		for (Mitarbeiter mitarbeiter : dataModel.getMitarbeiterArrayList()) {
			for (int woche = 1; woche <= 52; woche++) {
				mitarbeiter.getMAButton(woche).setTageUrlaubAufButton();
			}
		}
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
		System.exit(0);
	}
	
	public void updateView() {
		kalenderPanel.refresh();
	}

}
