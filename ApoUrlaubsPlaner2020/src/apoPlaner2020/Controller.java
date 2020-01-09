package apoPlaner2020;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

public class Controller extends WindowAdapter implements ActionListener {
	
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
		kalenderPanel.refresh();
//		hauptfenster.baueFensterNeu();
		hauptfenster.fenster.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Button geklickt");
		JButton buttonClicked = (JButton) e.getSource();
		if (buttonClicked instanceof MAButton) {
			woche = ((MAButton) buttonClicked).getWoche();
			mitarbeiter = ((MAButton) buttonClicked).getMitarbeiter();
			System.out.println(woche + " " + mitarbeiter.getName());
		} else if (buttonClicked instanceof KalenderButton) {
			woche = ((KalenderButton) buttonClicked).getWoche();
			tag = ((KalenderButton) buttonClicked).getTag();
			vormNachm = ((KalenderButton) buttonClicked).getVormNachm();
		}
	}
	
	@Override
	public void windowClosing(WindowEvent e) 
	{
//			try 
//			{
//				fenster.setVisible(false);
//				speichern.speichereDatei();
//				speichern.speichereJPG();
//			} catch (IOException ex) 
//			{
//				ex.printStackTrace();
//			}
		System.out.println("Tschüs");
		System.exit(0);
	}
	
	public void updateView() {
		kalenderPanel.refresh();
//		hauptfenster.konsolenAusgabe();
	}

}
