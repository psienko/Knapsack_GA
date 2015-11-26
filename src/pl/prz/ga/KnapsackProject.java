package pl.prz.ga;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KnapsackProject {
	
	private static double knapsackVolume = 0;
	private static int lineNo = 0;
	private static Population readItems = new Population();
	private static List<Item> items = new ArrayList<Item>();
	
	public static byte[] combine(byte[] a, byte[] b){
        int length = a.length + b.length;
        byte[] result = new byte[length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

	
	public static void readInputFile() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));
			String line;
			while ((line = br.readLine()) != null) {
				lineNo ++;
				if (lineNo == 1) {
					knapsackVolume = Double.parseDouble(line);
				} else {
					String [] params = line.split("\t");
					int iNo = Integer.parseInt(params[0]);
					double iVol = Double.parseDouble(params[1]);
					double iVal = Double.parseDouble(params[2]);
					Item item = new Item(iNo, iVol, iVal);
					items.add(item);
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFound");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		int numberOfChromosomeInPopulation = 10;
		int maxNumberOfGeneration = 10;
		int generation = 1;
		double mutationRange = 0.01;
		double bestValue = 0.0;
		int bestIndividual = 0;
		double sumValue = 0.0;
		
		Population population = new Population();
		readInputFile();
		
		
		for(int i=0; i < numberOfChromosomeInPopulation; i++) {
			Fitness.generateFirstPopulation(items.size());
			Individual individual = new Individual(Fitness.getSolution());
			population.push(individual);
		}
		
		// Main loop
		while(generation <= maxNumberOfGeneration){
			System.out.println("GENERATION : " + Integer.toString(generation));
			// Calculate value and weight
			for(int individualIndex = 0; individualIndex < population.getPopulation().size(); individualIndex++) {
				double totalVolume = 0.0;
				double totalValue = 0.0;
				Individual individual = population.getIndividual(individualIndex);
				byte [] chromosome = individual.getChromosome();
				for(int geneIndex = 0; geneIndex < chromosome.length; geneIndex++) {
					if (chromosome[geneIndex] == 1) {
						totalVolume += items.get(geneIndex).getVolume();
						totalValue += items.get(geneIndex).getValue();
					}
				}
				if (totalVolume <= knapsackVolume) {
					if (totalValue >= bestValue) {
						bestValue = totalValue;
						bestIndividual = individualIndex;
					}
				} else {
					totalValue = 0.0; //individual will be remove from population
				}
				individual.setTotalValue(totalValue);
				individual.setTotalVolume(totalVolume);
				sumValue += totalValue;
				System.out.print("Value: " + individual.getTotalValue() + " \t Volume: " + individual.getTotalVolume() + " ");
				for(byte test : individual.getChromosome()) {
					System.out.print(test);
				}
				System.out.println();
			}
			
			// Roulette wheel algorithm used to proportionately creating next generation
			Population newGeneration = new Population();
			double rndSum;
			double rnd = 0;
			int rndSelected;
			
			Individual elitist = new Individual(population.getIndividual(bestIndividual).getChromosome());
			
			for(int i = 0; i<numberOfChromosomeInPopulation -1; i++) {
				rndSum = 0.0;
				
				rnd = (Math.random() * population.totalValue());
				rndSelected = 0;
				
				for(Individual individual : population.getPopulation()) {
					double relValue = individual.getTotalValue();
					rndSum =+ relValue;
					if (rndSum > rnd) {
						break;
					} else {
						rndSelected ++;
					}
				}
				if (rndSelected < population.size()) {
					newGeneration.push(new Individual(population.getIndividual(rndSelected).getChromosome()));
				} else {
					newGeneration.push(new Individual(population.getIndividual(rndSelected-1).getChromosome()));
				}
			}
			
			// Replace old generation with new
			population = newGeneration;
			generation ++;
			
			// Randomly select two knapsacks
			int rndInd1 = (int) (Math.random() * population.getPopulation().size());
			int rndInd2 = rndInd1;
			while (rndInd2 == rndInd1) {
				rndInd2 = (int) (Math.random() * population.getPopulation().size());
			}
			
			// Perform crossover
			int point = (int) (Math.random() * items.size()); // choose number from 0 to items count
			byte [] chromosomesInRndInd1 = population.getIndividual(rndInd1).getChromosome();
			byte [] chromosomesInRndInd2 = population.getIndividual(rndInd2).getChromosome();
			byte [] front1 = Arrays.copyOfRange(chromosomesInRndInd1, 0, point);		// part of parent chromosome from 0 to point
			byte [] front2 = Arrays.copyOfRange(chromosomesInRndInd2, 0, point);
			byte [] back1 = Arrays.copyOfRange(chromosomesInRndInd1, point, chromosomesInRndInd1.length); //chromosomesInRndInd1.length - 1
			byte [] back2 = Arrays.copyOfRange(chromosomesInRndInd2, point, chromosomesInRndInd2.length);
			byte [] newChromosome1 = combine(front1, back2);
			byte [] newChromosome2 = combine(front2, back1);
			
			Individual newIndividual1 = new Individual(newChromosome1);
			Individual newIndividual2 = new Individual(newChromosome2);
			population.setIndividual(rndInd1, newIndividual1);
			population.setIndividual(rndInd2, newIndividual2);
			
			// Perform mutation
			for(Individual individual : population.getPopulation()) {
				for(int geneIndex=0; geneIndex < individual.getChromosome().length; geneIndex ++ ) {
					if (Math.random() < mutationRange) { // Successful mutation for gen with geneIndex index
						if (individual.getGene(geneIndex) == 0)
							individual.setGene(geneIndex, (byte) 1);
						else
							individual.setGene(geneIndex, (byte) 0);
					}
				}
			}
			population.push(elitist);
			
		}
		
	
		System.out.println("\n    BEST VALUE "+ bestValue);	
	}
	

}
