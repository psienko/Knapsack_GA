package pl.prz.ga;

public class Individual {
	private byte [] chromosome;
	private double totalValue;
	private double totalVolume;
	
	public byte [] getChromosome() {
		return chromosome;
	}
	
	public void setChromosome(byte [] chromosome) {
		this.chromosome = chromosome;
	}
	
	public double getTotalVolume() {
		return totalVolume;
	}
	
	public void setTotalVolume(double totalVolume) {
		this.totalVolume = totalVolume;
	}
	
	public double getTotalValue() {
		return totalValue;
	}
	
	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}
	
	public void setGene(int index, byte value) {
		chromosome[index] = value;
	}
	
	public byte getGene(int index) {
		return chromosome[index];
	}
	
	public Individual(byte [] chromosome) {
		this.chromosome = chromosome;
		totalVolume = 0.0;
		totalValue = 0.0;
	}
	
	public void printAll() {
		System.out.print("Value: " + totalValue + "\t | \t Volume: " + totalVolume + "\t | \t");
		this.printChromosome();
		System.out.println();
	}
	
	public void printChromosome() {
		for (byte gene : chromosome) {
			System.out.print(gene);
		}
	}
}
