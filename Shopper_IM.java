
/**
 * This class is an abstraction for the shopper.
 * The shopper has their balance as an instance.
 *
 * @IMukhametzhanova
 * @1.8
 * 11/8/18
 */
public class Shopper_IM
{
    private double money;

    /**
     * Constructor with zero balance
     */
    public Shopper_IM ()
    {
        money = 0;
    }

    /**
     * Constructor with entered balance
     */
    public Shopper_IM (double money)
    {
        this.money = money;
    }

    /**
     * Method to access the shopper's current balance
     */
    public double getMoney()
    {
        return money;
    }

    /**
     * When the shopper buys something.
     * Subtracts the cost from their current balance.
     */
    public boolean boughtOne(double cost)
    {
        boolean canBuy = false;
        if (cost > money) System.out.println("You don't have enough money to buy this");
        else {
            money -= cost;
            canBuy = true;
        }
        return canBuy;
    }

    /**
     * Converts the shopper's info to string
     */
    public String toString()
    {
        String output = "Current balance: $" + money;
        return output;
    }
}