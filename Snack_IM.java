/**
 * This class is an abstraction for a snack.
 * The snack has its name and cost as instances.
 *
 * @IMukhametzhanova
 * @1.8
 * 11/8/18
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Snack_IM
{
    private String name;
    private double cost;

    /**
     * Constructor that takes in the name and cost of one unit
     */
    public Snack_IM(String name, double cost)
    {
        this.name = name;
        this.cost = cost;
    }

    /**
     * Get the snack's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get the snack's cost (for one unit)
     */
    public double getCost()
    {
        return cost;
    }
}