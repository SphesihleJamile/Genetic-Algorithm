# Genetic-Algorithm
I've always had a passion about how AI works, and how it is used to solve problems and find solutions. Even though I created an AI repository in the past, but I feel the need to create this new one. This AI project basically solves a transportation problem, where we have a lot of boxes to transport in a single vehicle, but that vehicle has a maximum weight of objects that it can take. So, here we are trying to find a collection of objects that have a weight below the capacity of the vehicle (max-weight) but also has a value that is greater than the quota that we want. The quota is the minimum value of objects that we want to transport at a single moment in time.

 	CHROMOSOME

In computer science we can explain a chromosome to be a set of genes.
I created a class called Box, that would represent each object as a box that would be transported in the transportation problem. I then created a class called Load, that represents a chromosome, since a chromosome is basically a set of which boxes are best to be transported.

In the Load class, I have a static chromosomeOfBoxes array list that will hold the original list of boxes, and chromosome array list that represents a chromosome has a list of binary numbers. 
	
  e.g. chromosome1 = {0, 1, 0, 0, 1, 1, 1, 0};

In this example, chromosome1 has 8 integer elements. This means that at an index i, if i = 0, then chromosome1 does not contain the box located at chromosomeOfBoxes.get(i), else if i = 1, then it contains the box at chromosomeofBoxes.get(i).


 	POPULATION SIZE
  
In the input.txt file, or any file that will be used as input, the third value from the top is the number of objects in the file. Instead of creating a population that has 100 or more chromosomes, I created a population that has the same amount of chromosomes as objects in the file. Basically, the population is a nxn matrix.

  FITNESS FUNCTION

 	Each object/box contains it’s own value, and weight. So to simplify things, I wrote a fitness function where each chromosomes fitness will equal to the total number of objects/boxes values that it contains. But there is a catch. If the chromosomes value is below the required quota, or the chromosomes total weight is above capacity, then the chromosome will be allocated a fitness value of 0.
  
  SELECTION METHOD
  
   	In order for any sort of reproduction to happen, the best chromosomes in the population have to be selected, because, they have a better chance of producing children that have a higher chance of surviving. Hence, for my selection method, I created a method called AllocateFittness() that will allocate each chromosome in the population a fitness, and a method called FindFittest() that will select the 2 most fittest parents in the population. These two parents will be used for reproduction or crossover to produce a child chromosome.
    
    MUTATION OPERATOR AND MUTATION RATE
    
 	After chromosome occurs and a child chromosome is produces, there is a probability that the child will go through reproduction. So I used the Random class to generate a number between 0 and the population size. Then I said that if that random number modulus (%) population size is less than half the population size, then the child should be mutated. Thus using a probability of 0.5.
 	For Mutation, I generated a random number between 1 and chromosome.size(). Then the specific gene at the random point will be changed.

  CROSSOVER POINT & CROSSOVER RATE

  	For my crossover, I created a random number between 1 and chromosome.size(). My seed is 1 because if the crossover point is generated to be 0, then a crossover wont be efficient. When this random number has been generated, the 2 parents that have been taken will interchange values that that point and a child will be produced.
    
    TERMINATION CRITERIA
    
   In order for us to have the best chromosome out of the population, we have to make sure that the population we choose from has chromosomes that have a fitness that is greater than 0. Hence, for my termination criteria, I said that if half of the population has chromosomes that are greater than 0, then it should terminate. The fittest chromosome will then be the chromosome that we’re looking for. Sample code has been provided below
    
