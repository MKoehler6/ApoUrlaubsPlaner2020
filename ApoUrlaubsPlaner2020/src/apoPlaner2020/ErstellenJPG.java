package apoPlaner2020;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.crypto.Data;

public class ErstellenJPG {

	BufferedImage fertigesJPG;
	int[] pixels;
	DataModel dataModel;
	Datum datum;
	int woche = 0;
	int zaehlerButtons = 0;
	String[] wochenTage = {"Mo", "Di", "Mi", "Do", "Fr"};
	private static int ANZAHL_MA;
	
	public ErstellenJPG(DataModel dataModel)
	{
		this.dataModel = dataModel;
		ANZAHL_MA = dataModel.getMitarbeiterArrayList().size();
		datum = new Datum(dataModel);
		fertigesJPG = new BufferedImage(3100, 400 * ANZAHL_MA, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) fertigesJPG.getRaster().getDataBuffer()).getData();
	}
	
	public BufferedImage renderFertigesJPG()
	{
		renderBackground();
		for (int zeile = 1; zeile <= 11; zeile++)
			for (int spalte = 1; spalte <= 5; spalte++)
				if (!(zeile == 11 && spalte > 2))
				{
					woche++;
					zaehlerButtons = zaehlerButtons + 12;
					for (int i = 0; i < ANZAHL_MA; i++) // Mitarbeiter
						renderImage(createImageWithText(dataModel.getMitarbeiterArrayList().get(i).getName(), 100, 25, Font.BOLD, 10), 10 + 620*(spalte-1), 36 + 26 * i + 33*ANZAHL_MA*(zeile-1));
					for (int i = 0; i < ANZAHL_MA; i++) // Urlaubstage
						renderImage(createImageWithText(dataModel.getMitarbeiterArrayList().get(i).gibAnzahlTageUrlaub() + "", 25, 25, Font.PLAIN, 3), 112 + 620*(spalte-1), 36 + 26 * i + 33*ANZAHL_MA*(zeile-1));
					for (int tag = 0; tag < 5; tag++) // Wochentage
					{ 
						String datum = gibDatumDesTages(tag, woche);
						renderImage(createImageWithText(wochenTage[tag] + "    " + datum, 91, 25, Font.BOLD, 5), 139 + 93 * tag + 620*(spalte-1), 36 - 26 + 33*ANZAHL_MA*(zeile-1));
						for (int ma = 0; ma < ANZAHL_MA; ma++) 
							for (int vormNachm = 0; vormNachm < 2; vormNachm++) // vormittag/nachmittag
							{
								renderImage(erstelleDienstplanFeld(45, 25, ma, woche-1, tag, vormNachm), 139 + 93*tag + 620*(spalte-1) + vormNachm*46, 36 + 26*ma + 33*ANZAHL_MA*(zeile-1));
								zaehlerButtons++;
							}
					}
				}
			
		return fertigesJPG;
	}
	
	public void renderBackground()
	{
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = 0x5e5e5e;
	}
	
	public void renderImage(BufferedImage image, int xPos, int yPos)
	{
		int [] imagePixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		renderArray(imagePixels, image.getWidth(), image.getHeight(), xPos, yPos);
	}
	
	private void renderArray(int[] renderPixels, int renderWidth, int renderHeight, int xPos, int yPos)
	{
		for (int y = 0; y < renderHeight; y++)
			for (int x = 0; x < renderWidth; x++)
				setPixel(renderPixels[y * renderWidth + x], x + xPos, y + yPos);
	}
	
	private void setPixel(int pixel, int x, int y)
	{
		int pixelIndex = x + y * fertigesJPG.getWidth();
		if (pixels.length > pixelIndex)
			pixels[pixelIndex] = pixel;						
	}

	private BufferedImage createImageWithText(String text, int width, int height, int thickness, int textbeginn)
	{
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		int[] imagePixel = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		if ((dataModel.getFerienInWocheX(woche - 1) == 1) && width == 91)
		{
			for (int i = 0; i < imagePixel.length; i++)
				imagePixel[i] = 0xb3ee3a; // gr�n			
		} else {
			for (int i = 0; i < imagePixel.length; i++)
				imagePixel[i] = 0xffffff;
		}
		Graphics2D imgG2D = image.createGraphics();
		imgG2D.setFont(new Font("TimesRoman", thickness, 16));
		imgG2D.setColor(new Color(0x000000));
		imgG2D.drawChars(text.toCharArray(), 0,  text.length(), textbeginn, 18);
		return image;
	}
	private BufferedImage erstelleDienstplanFeld(int width, int height, int maNr, int woche, int tag, int vormNachm)
	{
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		int[] imagePixel = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		if (dataModel.getMitarbeiterArrayList().get(maNr).getTag(woche, tag).isUrlaub())
		{
			for (int i = 0; i < imagePixel.length; i++)
				imagePixel[i] = 0xff3030; // rot			
		} else if ((woche+1)%2 == 0) {
			if (dataModel.getMitarbeiterArrayList().get(maNr).getDienstplan(woche+1).getGeradeWoche()[tag][vormNachm] == 1) {
				for (int i = 0; i < imagePixel.length; i++)
					imagePixel[i] = 0x5096e6; // blau
			} else {
				for (int i = 0; i < imagePixel.length; i++)
					imagePixel[i] = 0xffffff; // weiss
			}
		} else {
			if (dataModel.getMitarbeiterArrayList().get(maNr).getDienstplan(woche+1).getUngeradeWoche()[tag][vormNachm] == 1) {
				for (int i = 0; i < imagePixel.length; i++)
					imagePixel[i] = 0x5096e6; // blau
			} else {
				for (int i = 0; i < imagePixel.length; i++)
					imagePixel[i] = 0xffffff; // weiss
			}
		}
		
		return image;
	}
	private String gibDatumDesTages(int tag, int week)
	{
		switch (tag)
		{
		case 0 : return datum.gibMontagVonWocheX(week);
		case 1 : return datum.gibDienstagVonWocheX(week);
		case 2 : return datum.gibMittwochVonWocheX(week);
		case 3 : return datum.gibDonnerstagVonWocheX(week);
		case 4 : return datum.gibFreitagVonWocheX(week);
		}
		return null;
	}
}
