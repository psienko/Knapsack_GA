package pl.prz.ga;

public class Individual {
	private int number;
	private double volume;
	private double value;
	
	public Individual(int number, double volume, double value) {
		this.number = number;
		this.volume = volume;
		this.value = value;
		
	}
	
	public int getNumber() {
		return number;
	}
	
	public double getVolume() {
		return volume;
	}
	
	public double getValue() {
		return value;
	}
}
