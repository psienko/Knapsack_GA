package pl.prz.ga;

public class Fitness {
	private static byte [] solution;
	
	public static byte [] getSolution() {
		return solution;
	}
	
	public static void generateFirstPopulation(int candidatesCount) {
		solution =  new byte[candidatesCount];
		for (int i = 0; i < candidatesCount; i++) {
			solution[i] = (byte) (Math.random() * 2); // generate 0 or 1
		}
	}

}
