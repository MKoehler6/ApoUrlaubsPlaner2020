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
	
	

}
