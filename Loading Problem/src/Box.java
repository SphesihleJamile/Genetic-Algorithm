import java.util.Random;

public class Box implements Cloneable, Comparable<Box>{

    //This box class represents a single gene in a chromosome
    public char name;//The name of the box as a character
    public int weight;//THe weight of the box as an int value
    public int value;//The value of the box as an integer value

    public Box(char name, int value, int weight)
    {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(Box box) {
        if(value > box.value)
            return 1;
        else if(value < box.value)
            return -1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return
                "Name : " + name +
                        ", weight : " + weight +
                        ", value : " + value;
    }
}
