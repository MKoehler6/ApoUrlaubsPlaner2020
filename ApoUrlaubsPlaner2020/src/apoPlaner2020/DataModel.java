package apoPlaner2020;

import java.util.ArrayList;

public class DataModel {
	
	int jahr = 2019;
	int[] ferien = {1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,1};
	ArrayList<Mitarbeiter> mitarbeiterArrayList = new ArrayList<>();
	
	public DataModel() {
		
		ladeDaten();
	}

	private void ladeDaten() {
		Dienstplan testDienstplan1 = new Dienstplan();
		testDienstplan1.setGueltigAb(0);
		int[][] testGerade = {{1,1}, {1,0}, {0,1}, {1,1}, {1,0}};
		int[][] testUnGerade = {{1,1}, {1,1}, {1,0}, {1,0}, {1,1}};
		testDienstplan1.setGeradeWoche(testGerade);
		testDienstplan1.setUngeradeWoche(testUnGerade);
		
		Dienstplan testDienstplan2 = new Dienstplan();
		testDienstplan2.setGueltigAb(2);
		int[][] testGerade2 = {{1,1}, {1,0}, {0,1}, {0,0}, {0,0}};
		int[][] testUnGerade2 = {{1,1}, {1,1}, {1,0}, {0,0}, {0,0}};
		testDienstplan2.setGeradeWoche(testGerade2);
		testDienstplan2.setUngeradeWoche(testUnGerade2);
		
		Dienstplan testDienstplan3 = new Dienstplan();
		testDienstplan3.setGueltigAb(0);
		int[][] testGerade3 = {{1,1}, {0,0}, {0,1}, {0,1}, {0,1}};
		int[][] testUnGerade3 = {{0,1}, {0,1}, {0,0}, {0,1}, {0,1}};
		testDienstplan3.setGeradeWoche(testGerade3);
		testDienstplan3.setUngeradeWoche(testUnGerade3);
		
//		MA test1
		Mitarbeiter testMitarbeiter1 = new Mitarbeiter("test1");
		testMitarbeiter1.dienstplanArrayList.add(testDienstplan1);
		testMitarbeiter1.dienstplanArrayList.add(testDienstplan2);
		
		updateDienstplanInTageArray(testMitarbeiter1);
		
		updateUrlaubInTageArray(testMitarbeiter1);
		
		mitarbeiterArrayList.add(testMitarbeiter1);
		
//		MA test2
		Mitarbeiter testMitarbeiter2 = new Mitarbeiter("test2");
		testMitarbeiter2.dienstplanArrayList.add(testDienstplan3);
		
		updateDienstplanInTageArray(testMitarbeiter2);
		
		updateUrlaubInTageArray(testMitarbeiter2);
		
		mitarbeiterArrayList.add(testMitarbeiter2);
	}

	private int getAktuellerDienstplanFuerMA(int woche, Mitarbeiter testMitarbeiter) {
		for (int i = testMitarbeiter.dienstplanArrayList.size()-1; i >= 0; i--) {
			if (woche >= testMitarbeiter.dienstplanArrayList.get(i).getGueltigAb()) 
				return i;
		}
		return 0;
	}
	
	public void updateTageArrayAlleMA() {
		for (Mitarbeiter mitarbeiter : mitarbeiterArrayList) {
			updateDienstplanInTageArray(mitarbeiter);
			updateUrlaubInTageArray(mitarbeiter);
		}
	}
	
	public void updateDienstplanInTageArray(Mitarbeiter mitarbeiter) {
		for (int woche = 0; woche < 52; woche++) {
			int dienstplan = getAktuellerDienstplanFuerMA(woche, mitarbeiter);
			for (int wochentag = 0; wochentag < 5; wochentag++) {
				Tag tag = new Tag(woche, wochentag);
				if (woche%2 == 0) {
					if (mitarbeiter.dienstplanArrayList.get(dienstplan).getGeradeWoche()[wochentag][0] == 1)
						tag.setDienstVormittag(true);
					if (mitarbeiter.dienstplanArrayList.get(dienstplan).getGeradeWoche()[wochentag][1] == 1)
						tag.setDienstNachmittag(true);
				}
				if (woche%2 != 0) {
					if (mitarbeiter.dienstplanArrayList.get(dienstplan).getUngeradeWoche()[wochentag][0] == 1)
						tag.setDienstVormittag(true);
					if (mitarbeiter.dienstplanArrayList.get(dienstplan).getUngeradeWoche()[wochentag][1] == 1)
						tag.setDienstNachmittag(true);
				}
				mitarbeiter.tageArray[woche][wochentag] = tag;
			}
		}
	}
	
	public void updateUrlaubInTageArray(Mitarbeiter mitarbeiter) {
		if (mitarbeiter.getName().equals("test1")) {
			mitarbeiter.tageArray[0][1].setUrlaub(true);
			mitarbeiter.tageArray[0][3].setUrlaub(true);
			mitarbeiter.tageArray[1][0].setUrlaub(true);
			mitarbeiter.tageArray[1][1].setUrlaub(true);
			mitarbeiter.tageArray[1][2].setUrlaub(true);
		} else {
			mitarbeiter.tageArray[1][0].setUrlaub(true);
			mitarbeiter.tageArray[1][1].setUrlaub(true);
		}
	}

}
