package apoPlaner2020;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;

public class Mitarbeiter {
	
	private String name;
	ArrayList<Dienstplan> dienstplanArrayList = new ArrayList<>();
	int urlaubstageAnzahl; 
	private Tag[][] tageImJahrArray = new Tag[52][5]; // 52 Wochen, 5 Tage	
	
	public Mitarbeiter(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Tag getTag(int woche, int tag) {
		return tageImJahrArray[woche][tag];
	}
	
	public void setTag(int woche, int tag, boolean isUrlaub) {
		Tag t = new Tag();
		t.setUrlaub(isUrlaub);
		tageImJahrArray[woche][tag] = t;
//		TODO Dienst vormittag und nachmittag einfügen
	}
	
	public void addDienstplan(Dienstplan dienstplan) {
		dienstplanArrayList.add(dienstplan);
	}
	
	public int gibAnzahlTageUrlaub() {
		int zaehler = 0;
		for (int woche = 0; woche < 52; woche++) {
			for (int tag = 0; tag < 5; tag++) {
				if (tageImJahrArray[woche][tag].isUrlaub()) zaehler++;
			}
		}
		return zaehler;
	}
	
	public void setAlleTageInArrayNeu() {
		for (int woche = 0; woche < 52; woche++) {
			for (int tag = 0; tag < 5; tag++) {
				setTag(woche, tag, false);
			}
		}
	}
	
	public Dienstplan getDienstplan(int woche) {
		for (int i = dienstplanArrayList.size()-1; i >= 0; i--) {
			if (woche >= dienstplanArrayList.get(i).getGueltigAb()) {
				return dienstplanArrayList.get(i);
			}
		}
		
		return dienstplanArrayList.get(dienstplanArrayList.size()-1);
	}

}
