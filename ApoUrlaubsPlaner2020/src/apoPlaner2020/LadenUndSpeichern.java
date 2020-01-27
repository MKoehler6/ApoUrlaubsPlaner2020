package apoPlaner2020;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LadenUndSpeichern {
	
	String pfadInput = System.getProperty("user.home") + "/Dropbox/HP Laptop/Urlaubsplaner";
	String pfadOutput = System.getProperty("user.home") + "/Dropbox/HP Laptop/Urlaubsplaner";
	String pfadJPG = System.getProperty("user.home") + "/Dropbox/HP Laptop/UrlaubsplanerJPG";
	String pfadConfig = System.getProperty("user.home") + "/Dropbox/HP Laptop/UrlaubsplanerConfig.txt";
	File inputFile;
	File outputFile;
	File configFile;
	DataModel dataModel;
	ErstellenJPG erstellenJPG;
	
	public LadenUndSpeichern(DataModel dataModel) {
		this.dataModel = dataModel;
	}

	public void speichereDaten() {
		configFile = new File(pfadConfig);
		try (FileOutputStream fileOutputStream = new FileOutputStream(configFile)) {
			fileOutputStream.write(dataModel.getJahr());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		erstellenJPG = new ErstellenJPG(dataModel);
		outputFile = new File(pfadOutput + "20" + dataModel.getJahr() + ".txt");
		try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
//			Jahr speichern
			fileOutputStream.write(dataModel.getJahr());
//			Ferien speichern
			for (int woche = 0; woche < 52; woche++) {
				fileOutputStream.write(dataModel.getFerienInWocheX(woche));
			}
			int anzahlMA = dataModel.getMitarbeiterArrayList().size();
			fileOutputStream.write(anzahlMA);
			for (Mitarbeiter ma : dataModel.getMitarbeiterArrayList()) {
//				Namen speichern
				String name = ma.getName();
				for (int i = 0; i < 20; i++) {
					if (i < name.length()) {
						fileOutputStream.write(name.charAt(i));
					} else {
						fileOutputStream.write(0);
					}
				}
//				Urlaubstage speichern
				for (int woche = 0; woche < 52; woche++) {
					for (int tag = 0; tag < 5; tag++) {
						if (ma.getTag(woche, tag).isUrlaub()) {
							fileOutputStream.write(1);
						} else {
							fileOutputStream.write(0);
						}
					}
				}
//				Dienstplaene speichern
				int anzahlDienstplaene = ma.dienstplanArrayList.size();
				fileOutputStream.write(anzahlDienstplaene);
				for (Dienstplan dienstplan : ma.dienstplanArrayList) {
					fileOutputStream.write(dienstplan.getGueltigAb());
					for (int tag = 0; tag < 5; tag++) {
						for (int vormNachm = 0; vormNachm < 2; vormNachm++) {
							fileOutputStream.write(dienstplan.getGeradeWoche()[tag][vormNachm]);
						}
					}
					for (int tag = 0; tag < 5; tag++) {
						for (int vormNachm = 0; vormNachm < 2; vormNachm++) {
							fileOutputStream.write(dienstplan.getUngeradeWoche()[tag][vormNachm]);
						}
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
		configFile = new File(pfadConfig);
		try (FileInputStream fileInputStream = new FileInputStream(configFile)) {
			dataModel.setJahr(fileInputStream.read());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		inputFile = new File(pfadInput + "20" + dataModel.getJahr() + ".txt");
		if (inputFile.exists()) {
			try (FileInputStream fileInputStream = new FileInputStream(inputFile)) {
//				lade Jahr
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
//					Tage erstellen und Urlaub laden und im Tag speichern
					for (int woche = 0; woche < 52; woche++) {
						for (int tag = 0; tag < 5; tag++) {
							ma.setTag(woche, tag, fileInputStream.read() == 1);
						}
					}
//					Dienstplaene laden
					int anzahlDienstplaene = fileInputStream.read();
					for (int d = 0; d < anzahlDienstplaene; d++) 
					{
						Dienstplan dienstplan= new Dienstplan();
						dienstplan.setGueltigAb(fileInputStream.read());
						for (int tag = 0; tag < 5; tag++) {
							for (int vormNachm = 0; vormNachm < 2; vormNachm++) {
								dienstplan.getGeradeWoche()[tag][vormNachm] =
										fileInputStream.read();
							}
						}
						for (int tag = 0; tag < 5; tag++) {
							for (int vormNachm = 0; vormNachm < 2; vormNachm++) {
								dienstplan.getUngeradeWoche()[tag][vormNachm] =
										fileInputStream.read();
							}
						}
						ma.addDienstplan(dienstplan);
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void speichereJPG() 
	{
		BufferedImage fertigesJPG = erstellenJPG.renderFertigesJPG();
		File jpgFile = new File(pfadJPG + "20" + dataModel.getJahr() + ".jpg");
		try {
			ImageIO.write(fertigesJPG, "png", jpgFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
