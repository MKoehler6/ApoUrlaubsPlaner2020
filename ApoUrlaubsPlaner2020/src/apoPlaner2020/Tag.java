package apoPlaner2020;

import javax.swing.JButton;

public class Tag {
	
	private int woche;
	private int wochentag;
	private boolean urlaub = false;
	private boolean dienstVormittag = false;
	private boolean dienstNachmittag = false;
	JButton buttonVormittag;
	JButton buttonNachmittag;
	
	public Tag(int woche, int wochentag) {
		this.woche = woche;
		this.wochentag = wochentag;
	}
	
	public void speicherButtonVormittag(JButton button) {
		buttonVormittag = button;
	}
	public void speicherButtonNachmittag(JButton button) {
		buttonNachmittag = button;
	}
	public JButton gibButtonVormittag() {
		return buttonVormittag;
	}
	public JButton gibButtonNachmittag() {
		return buttonNachmittag;
	}

	public boolean isUrlaub() {
		return urlaub;
	}

	public void setUrlaub(boolean urlaub) {
		this.urlaub = urlaub;
	}

	public boolean isDienstVormittag() {
		return dienstVormittag;
	}

	public void setDienstVormittag(boolean dienstVormittag) {
		this.dienstVormittag = dienstVormittag;
	}

	public boolean isDienstNachmittag() {
		return dienstNachmittag;
	}

	public void setDienstNachmittag(boolean dienstNachmittag) {
		this.dienstNachmittag = dienstNachmittag;
	}
	
	
	
	
	

}
