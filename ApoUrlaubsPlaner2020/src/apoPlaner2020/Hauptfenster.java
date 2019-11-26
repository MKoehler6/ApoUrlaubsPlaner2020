package apoPlaner2020;

import java.io.IOException;
import java.util.ArrayList;

public class Hauptfenster {
	
	private ArrayList<Mitarbeiter> mitarbeiterArrayList;
	
	public Hauptfenster(ArrayList<Mitarbeiter> mitarbeiterArrayList) {
		this.mitarbeiterArrayList = mitarbeiterArrayList;
	}
	
	public void konsolenAusgabe() {
		System.out.println("========================================================================================================");
		for (int woche = 0; woche < 4; woche++) {
			for (int ma = 0; ma < mitarbeiterArrayList.size(); ma++) {
				System.out.print(mitarbeiterArrayList.get(ma).getName() + " ");
				for (int wochentag = 0; wochentag < 5; wochentag++) {
					if (mitarbeiterArrayList.get(ma).tageArray[woche][wochentag].isUrlaub())
						System.out.print("U U  ");
					else {
						if (mitarbeiterArrayList.get(ma).tageArray[woche][wochentag].isDienstVormittag())
							System.out.print("D ");
						else System.out.print("_ ");
						if (mitarbeiterArrayList.get(ma).tageArray[woche][wochentag].isDienstNachmittag())
							System.out.print("D  ");
						else System.out.print("_  ");
					}
				}
				System.out.println();
			}
			System.out.println();
		}
	}

}
