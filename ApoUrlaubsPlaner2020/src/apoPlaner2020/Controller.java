package apoPlaner2020;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Controller implements ActionListener {
	
	private Hauptfenster hauptfenster;
	private KalenderPanel kalenderPanel;
	private DataModel dataModel;
	private int woche;
	private int tag;
	private int vormNachm;
	
	
	public Controller(DataModel dataModel, KalenderPanel kalenderPanel, Hauptfenster hauptfenster) {
		this.dataModel = dataModel;
		this.hauptfenster = hauptfenster;
		this.kalenderPanel = kalenderPanel;
		hauptfenster.konsolenAusgabe();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonClicked = (JButton) e.getSource();
		if (buttonClicked instanceof MAButton) {
			woche = ((MAButton) buttonClicked).getWoche();
		} else if (buttonClicked instanceof KalenderButton) {
			woche = ((KalenderButton) buttonClicked).getWoche();
			tag = ((KalenderButton) buttonClicked).getTag();
			vormNachm = ((KalenderButton) buttonClicked).getVormNachm();
		}
	}
	
	public void updateView() {
		kalenderPanel.refresh();
		hauptfenster.konsolenAusgabe();
	}


	
	

}
