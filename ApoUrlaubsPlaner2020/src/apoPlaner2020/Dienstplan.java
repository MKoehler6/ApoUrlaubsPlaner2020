package apoPlaner2020;

public class Dienstplan {
	
	private int gueltigAb;
	private int[][] geradeWoche = new int[5][2];
	private int[][] ungeradeWoche = new int[5][2];
	
	
	public int getGueltigAb() {
		return gueltigAb;
	}
	public void setGueltigAb(int gueltigAb) {
		this.gueltigAb = gueltigAb;
	}
	public int[][] getGeradeWoche() {
		return geradeWoche;
	}
	public void setGeradeWoche(int[][] geradeWoche) {
		this.geradeWoche = geradeWoche;
	}
	public int[][] getUngeradeWoche() {
		return ungeradeWoche;
	}
	public void setUngeradeWoche(int[][] ungeradeWoche) {
		this.ungeradeWoche = ungeradeWoche;
	}
	
	public void kontrollausgabe() {
		System.out.println("gerade");
		for (int i = 0; i <5; i++) {
			System.out.println(geradeWoche[i][0] + " " + geradeWoche[i][1]);
		}
		System.out.println("ungerade");
		for (int i = 0; i <5; i++) {
			System.out.println(ungeradeWoche[i][0] + " " + ungeradeWoche[i][1]);
		}
	}
	
	

}
