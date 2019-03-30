/**
 * This class is an abstraction for a drink.
 * The drink has its name and cost as instances.
 *
 * @IMukhametzhanova
 * @1.8
 * 11/8/18
 */
public class Drink_IM
{
    private String name;
    private double cost;

    /**
     * Constructor
     */
    public Drink_IM(String name, double cost)
    {
        this.name = name;
        this.cost = cost;
    }

    /**
     * Get the drink's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get the drink's cost (for one unit)
     */
    public double getCost()
    {
        return cost;
    }
}