package apoPlaner2020;

public class Tag {
	
	private int woche;
	private int wochentag;
	private boolean urlaub = false;
	private boolean dienstVormittag = false;
	private boolean dienstNachmittag = false;
	
	public Tag(int woche, int wochentag) {
		this.woche = woche;
		this.wochentag = wochentag;
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
