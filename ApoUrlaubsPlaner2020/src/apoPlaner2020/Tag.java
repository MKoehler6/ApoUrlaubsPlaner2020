package apoPlaner2020;

import javax.swing.JButton;

public class Tag {
	
	private boolean isUrlaub = false;
	private boolean isDienstVormittag = false;
	private boolean isDienstNachmittag = false;
	
	public boolean isUrlaub() {
		return isUrlaub;
	}

	public void setUrlaub(boolean urlaub) {
		this.isUrlaub = urlaub;
	}

	public boolean isDienstVormittag() {
		return isDienstVormittag;
	}

	public void setDienstVormittag(boolean dienstVormittag) {
		this.isDienstVormittag = dienstVormittag;
	}

	public boolean isDienstNachmittag() {
		return isDienstNachmittag;
	}

	public void setDienstNachmittag(boolean dienstNachmittag) {
		this.isDienstNachmittag = dienstNachmittag;
	}
	
	
	
	
	

}
