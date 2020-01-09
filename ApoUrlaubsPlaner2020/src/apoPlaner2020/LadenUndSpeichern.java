package apoPlaner2020;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class LadenUndSpeichern {
	
	String pfad = System.getProperty("user.home") + "/Dropbox/HP Laptop/Urlaubsplaner2020NeuTest.txt";
	String pfadJPG = System.getProperty("user.home") + "/Dropbox/HP Laptop/UrlaubsplanerJPG2020NeuTest.jpg";
	File inputFile;
	File outputFile;
	DataModel dataModel;
	
	public LadenUndSpeichern(DataModel dataModel) {
		this.dataModel = dataModel;
	}

	public void speichereDaten() {
		outputFile = new File(pfad);
		try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
			fileOutputStream.write(dataModel.getJahr());
			for (int woche = 0; woche < 52; woche++) {
				fileOutputStream.write(dataModel.getFerienInWocheX(woche));
			}
			int anzahlMA = 2;
			fileOutputStream.write(anzahlMA);
			String[] namen = {"Schmidt","Mähler"};
			for (int i = 0; i < anzahlMA; i++) {
//				Namen speichern
				String name = namen[i];
				for (int j = 0; j < 20; j++) {
					if (j < name.length()) {
						fileOutputStream.write(name.charAt(j));
					} else {
						fileOutputStream.write(0);
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ladeDaten() {
		inputFile = new File(pfad);
		if (inputFile.exists()) {
			System.out.println("Lade Daten");
			try (FileInputStream fileInputStream = new FileInputStream(inputFile)) {
				dataModel.setJahr(fileInputStream.read());
				for (int woche = 0; woche < 52; woche++) {
					dataModel.setFerienInWocheX(woche, fileInputStream.read());
				}
				int anzahlMA = fileInputStream.read();
				for (int i = 0; i < anzahlMA; i++) {
	//				Namen einlesen
					String name = "";
					int buchstabe;
					for (int j = 0; j < 20; j++) {
						if ((buchstabe = fileInputStream.read()) != 0) {
							name = name + (char)buchstabe;
						}
					}
					Mitarbeiter ma = new Mitarbeiter(name);
					dataModel.addMitarbeiter(ma);
					befuelleTageImJahrArray(ma);
					System.out.println(name);
				}
				
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void befuelleTageImJahrArray(Mitarbeiter mitarbeiter) {
		for (int woche = 0; woche < 52; woche++) {
			for (int tag = 0; tag < 5; tag++) {
//				TODO Daten vom Stream
				mitarbeiter.setTag(woche, tag, false);
			}
			
		}
	}
}
