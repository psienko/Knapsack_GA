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
	
	public Individual getIndividual(int index) {
		return individuals.get(index);
	}
	
	public void setIndividual(int index, Individual individual) {
		individuals.set(index, individual);
	}
	
	public int size() {
		return individuals.size();
	}

}
