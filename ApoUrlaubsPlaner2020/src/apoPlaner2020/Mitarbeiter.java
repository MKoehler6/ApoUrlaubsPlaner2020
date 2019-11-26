package apoPlaner2020;

import java.util.ArrayList;

public class Mitarbeiter {
	
	private String name;
	ArrayList<Dienstplan> dienstplanArrayList = new ArrayList<>();
	Tag[][] tageArray = new Tag[52][5];
	
	public Mitarbeiter(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	
	

}
