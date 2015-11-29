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
	private static List<Item> items = new ArrayList<Item>();
	

	
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
		readInputFile();
		GeneticAlgorithm.evolve(items, knapsackVolume);	
	}
	

}
