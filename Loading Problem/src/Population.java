import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Population implements Cloneable{

    public int populationSize;
    ArrayList<Load>  population;//Population of chromosomes, of size populationSize
    int generation = 0;//the number of the current generation
    int Capacity, Quota;
    private boolean runProgram = false;//if a file does not exist, or has not been open, runProgram = false, else true
    public Load fittestChromosome, secondFittestChromosome;

    public Population()
    {
        Initialize();
    }
    public Population(ArrayList<Load> population2)
    {
        this.population = (ArrayList<Load>) population2.clone();
    }
    public void Initialize()////100%
    {
        System.out.print("Enter a file path or name.... (for a \\, use two) : ");
        Scanner scan = new Scanner(System.in);//reads the input from the console
        Scanner file;
        try
        {
            file = new Scanner(new File(scan.next()));
        }
        catch (Exception e)
        {
            //if the file does not exist, then the program will print out the error, then terminate
            System.out.println(e.getMessage());
            runProgram = false;
            return;
        }
        //If the program gets to this point, then the file has exists
        runProgram = true;
        String start = file.next();
        this.Capacity = file.nextInt();//stores the max weight as the first value
        this.Quota = file.nextInt();//stores the max weight as the second value
        this.populationSize = file.nextInt();
        population = new ArrayList<>(populationSize);
        file.nextLine();//skips to the next line so that we can start reading the Objects
        while (file.hasNextLine())//While objects still exist in the file
        {
            String[] object = file.nextLine().split(" ");//splits the line, getting the name, weight, and value
            int weight = Integer.parseInt(object[1]);
            int value = Integer.parseInt(object[2]);
            Box box = new Box(object[0].trim().charAt(0), value, weight);
            Load.chromosomeOfBoxes.add(box);//adds the new box to the original bos list
        }
        int numberOfChromosomes = populationSize;//For efficiency purposes, we will use 10 chromosomes in our population
        while (numberOfChromosomes > 0)
        {
            Load load = new Load();
            population.add(load);//new Load() will automatically create a chromosome of size boxes.size, with random 0 & 1 values
            numberOfChromosomes--;//decrement the numberOfChromosomes variable
        }
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    public void FindFittest()
    {
        AllocateFitness();//First allocates each chromosome in the population its own fitness value
        Population pop = new Population(population);
        fittestChromosome = pop.population.get(0);
        for(Load load : pop.population)
        {
            if(load.fitness > fittestChromosome.fitness)
            {
                fittestChromosome = load;
            }

        }
        pop.population.remove(fittestChromosome);

        secondFittestChromosome = pop.population.get(0);
        for(Load load : pop.population)
        {
            if(load.fitness > secondFittestChromosome.fitness)
                secondFittestChromosome = load;
        }
    }
    public void AllocateFitness()
    {
        for(Load load : population)
        {
            load.CalculateFitness(Capacity, Quota);
        }
    }
    public void CountPopulationGeneration(){generation += 1;}
    public void PrintPopulation()
    {
        //Prints out the entire population
        for(Load load : population)
        {
            //load.printBoxesOfChromosome();
            load.printBoxesOfChromosome();
            System.out.println();
        }
    }
    public boolean TerminationCriteria()
    {

        if(!runProgram)
            return true;
        //This variable indicated that 70% of 10 chromosomes, which is
        final int minPopulationAcceptanceStandard = (int) (populationSize/2); //(int)(populationSize*0.5);
        ArrayList<Load> testPopulation = new ArrayList<>();
        AllocateFitness();
        for(Load load : population)
        {
            if(load.fitness > 0 & load.fitness >= Quota)
                testPopulation.add(load);
        }

        return (testPopulation.size() >= minPopulationAcceptanceStandard);
    }
    public Load Reproduce(Load parent1, Load parent2)
    {
        //This is basically our crossover function
        Random random = new Random(1);
        int crossOverPoint = random.nextInt(parent1.chromosome.size());

        ArrayList<Integer> child = new ArrayList<>();

        int i = 0;
        while (i <= crossOverPoint)
        {
            child.add(parent1.chromosome.get(i));
            i++;
        }
        while (i < parent2.chromosome.size())
        {
            child.add(parent2.chromosome.get(i));
            i++;
        }
        return new Load(child);
    }
    public Load Mutation(Load load)
    {
        Random random = new Random();

        int mutationPoint = random.nextInt(load.chromosome.size());
        if(load.chromosome.get(mutationPoint) == 0)
            load.chromosome.set(mutationPoint, 1);
        else
            load.chromosome.set(mutationPoint, 0);
        return load;
    }
}
