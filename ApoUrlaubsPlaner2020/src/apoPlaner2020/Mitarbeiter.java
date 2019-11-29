package apoPlaner2020;

import java.util.ArrayList;

import javax.swing.JButton;

public class Mitarbeiter {
	
	private String name;
	ArrayList<Dienstplan> dienstplanArrayList = new ArrayList<>();
	Tag[][] tageArray = new Tag[52][5];
	JButton[] buttonMAArray = new JButton[52];
	
	public Mitarbeiter(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int gibAnzahlTageUrlaub() {
		int zaehler = 0;
		for (int woche = 0; woche < 52; woche++) {
			for (int tag = 0; tag < 5; tag++) {
				if (tageArray[woche][tag].isUrlaub()) zaehler++;
			}
			
		}
		return zaehler;
	}
	
	
	

}
