package apoPlaner2020;

import javax.swing.JButton;

public class MAButton extends JButton {

	private int woche;
	private Mitarbeiter mitarbeiter;
	
	public MAButton(int woche, Mitarbeiter mitarbeiter) {
		this.woche = woche;
		this.mitarbeiter = mitarbeiter;
	}

	public int getWoche() {
		return woche;
	}
	
	public Mitarbeiter getMitarbeiter() {
		return mitarbeiter;
	}
	
	
}
