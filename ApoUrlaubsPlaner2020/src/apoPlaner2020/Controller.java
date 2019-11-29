package apoPlaner2020;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
	
	Hauptfenster hauptfenster;
	DataModel dataModel;
	
	public Controller() {
		dataModel = new DataModel();
		hauptfenster = new Hauptfenster(dataModel.mitarbeiterArrayList, this);
		hauptfenster.konsolenAusgabe();
		new MAbearbeitenFenster(dataModel.mitarbeiterArrayList, this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void updateView() {
		updateTageArray();
		hauptfenster.konsolenAusgabe();
	}

	public void updateTageArray() {
		dataModel.updateTageArrayAlleMA();
	}
	
	

}
