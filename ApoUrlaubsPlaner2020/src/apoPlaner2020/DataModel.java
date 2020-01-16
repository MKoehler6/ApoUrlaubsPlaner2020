package apoPlaner2020;

import java.util.ArrayList;

public class DataModel {
	
	private int jahr;
	private int[] ferien = new int[52];
	private ArrayList<Mitarbeiter> mitarbeiterArrayList;
	
	public DataModel() {
		mitarbeiterArrayList = new ArrayList<>();
	}
	
	public int getJahr() {
		return jahr;
	}
	
	public void setJahr(int jahr) {
		this.jahr = jahr;
	}
	
	public int getFerienInWocheX(int woche) {
		return ferien[woche];
	}
	
	public void setFerienInWocheX(int woche, int ferienOderNicht) {
		ferien[woche] = ferienOderNicht;
	}
	
	public void addMitarbeiter(Mitarbeiter mitarbeiter) {
		mitarbeiterArrayList.add(mitarbeiter);
	}
	
	public ArrayList<Mitarbeiter> getMitarbeiterArrayList() {
		return mitarbeiterArrayList;
	}
	
	public void loescheMA(Mitarbeiter mitarbeiter) {
		mitarbeiterArrayList.remove(mitarbeiter);
	}

}
