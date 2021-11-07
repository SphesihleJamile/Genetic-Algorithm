import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgorithmApp {

    public Load Genetic_Algorithm(Population population)
    {
        do
        {
            population.FindFittest();
            ArrayList<Load> population2 = new ArrayList<>();
            population.CountPopulationGeneration();
            for(int i = 1; i <= population.populationSize; i++)
            {
                Load parent1 = population.fittestChromosome, parent2 = population.secondFittestChromosome;
                Load child = population.Reproduce(parent1, parent2);
                Random random = new Random();
                if(random.nextInt() % population.populationSize < (population.populationSize/2))
                    child = population.Mutation(child);
                population2.add(child);
            }
            population.population = (ArrayList<Load>) population2.clone();
        }while (!population.TerminationCriteria());
        population.FindFittest();
        return population.fittestChromosome;
    }

    public static void main(String[] args)
    {
        Population population = new Population();
        GeneticAlgorithmApp algo = new GeneticAlgorithmApp();
        Load load = algo.Genetic_Algorithm(population);
        //population.PrintPopulation();

        System.out.println();
        System.out.println("Generation : " + population.generation);
        System.out.println();
        System.out.println("The solution has been found, bellow is the fittest chromosome");
        System.out.println();
        load.printBoxesOfChromosome();
    }
}
