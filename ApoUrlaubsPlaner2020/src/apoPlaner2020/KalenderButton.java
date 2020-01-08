package apoPlaner2020;

import javax.swing.JButton;

public class KalenderButton extends JButton {
	
	private int woche;
	private int tag;
	private int vormNachm;
	private Mitarbeiter mitarbeiter;
	
	public KalenderButton(int woche, int tag, int vormNachm, Mitarbeiter mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
		this.woche = woche;
		this.tag = tag;
		this.vormNachm = vormNachm; // 0 vormittag 1 nachmittag
	}

	public int getWoche() {
		return woche;
	}

	public int getTag() {
		return tag;
	}

	public int getVormNachm() {
		return vormNachm;
	}
	
	public Mitarbeiter getMitarbeiter() {
		return mitarbeiter;
	}
	
}
