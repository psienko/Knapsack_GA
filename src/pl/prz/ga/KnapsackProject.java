package pl.prz.ga;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class KnapsackProject {
	
	private static int knapsackVolume = 0;
	private static int lineNo = 0;
	
	public static void main(String[] args) {
		Population population = new Population();
	
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));
			String line;
			while ((line = br.readLine()) != null) {
				lineNo ++;
				if (lineNo == 1) {
					knapsackVolume = Integer.parseInt(line);
				} else {
					String [] params = line.split(" ");
					int iNo = Integer.parseInt(params[0]);
					double iVol = Double.parseDouble(params[1]);
					double iVal = Double.parseDouble(params[2]);
					Individual individual = new Individual(iNo, iVol, iVal);
					population.push(individual);
					System.out.println(population.getPopulation().get(0).getValue());
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

}
