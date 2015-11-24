package pl.prz.ga;

import java.util.ArrayList;
import java.util.List;

public class Population {
	List<Individual> individuals = new ArrayList<Individual>();
	//Individual[] individuals;
	
	public void push(Individual individual) {
		individuals.add(individual);
	}
	
	public List<Individual> getPopulation() {
		return individuals;
	}

}
