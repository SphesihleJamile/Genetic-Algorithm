import java.util.ArrayList;
import java.util.Random;

public class Load implements Cloneable, Comparable<Load>{
    public static ArrayList<Box> chromosomeOfBoxes = new ArrayList<>();
    public ArrayList<Integer> chromosome = new ArrayList<>();
    public int fitness = 0;

    public Load()
    {
        Random random = new Random();//random number object
        for(int i = 0; i < chromosomeOfBoxes.size(); i++)
        {
            int r = random.nextInt(2);//generates a random number between 0 and 1
            chromosome.add(r);//add the random 0 or 1 to the chromosome
        }
    }
    public Load(ArrayList<Integer> child)
    {
        chromosome.clear();
        for(int i : child)
        {
            chromosome.add(i);
        }
    }
    public void CalculateFitness(int capacity, int quota)/////100%
    {
        int weight = 0, value = 0, i = 0;
        for(Box box : chromosomeOfBoxes)
        {
            try {
                Box x = (Box) box.clone();
                if(this.chromosome.get(i) == 1)
                {
                    weight += x.weight;
                    value += x.value;
                    //System.out.println("Weight : " + x.weight + ", Value : " + x.value);
                    if(weight > capacity)
                    {
                        fitness = 0;
                        return;
                    }
                }
            }catch (java.lang.CloneNotSupportedException ex)
            {
                System.out.println(ex.getMessage());
            }
            i++;
        }
        if(value < quota)
            fitness = 0;
        else
            fitness = value;
    }
    public void printBoxesOfChromosome()//////100%
    {
        System.out.println();
        System.out.println("Present boxes in the chromosome : ");
        System.out.println("Fitness = " + this.fitness);
        int i = 0;
        for(Box box : chromosomeOfBoxes)
        {
            try{
                Box x = (Box) box.clone();
                if(chromosome.get(i) == 1)
                    System.out.println(x.toString());
            }catch (java.lang.CloneNotSupportedException ex)
            {
                System.out.println(ex.getMessage());
            }
            i++;
        }
    }
    public void PrintNaturalChromosome()//////100%
    {
        System.out.println("\nFitness = " + this.fitness);
        for (int chr : chromosome) {
            System.out.print(chr + " ");
        }
        System.out.println();
    }
    @Override
    public int compareTo(Load load) {
        if(this.fitness < load.fitness)
            return -1;
        else if(this.fitness > load.fitness)
            return 1;
        else
            return 0;
    }/////100%

}
