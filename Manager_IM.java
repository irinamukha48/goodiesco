/**
 * This class is an abstraction for the manager.
 * The manager has their balance as an instance.
 *
 * @IMukhametzhanova
 * @1.8
 * 11/8/18
 */
public class Manager_IM
{
    private double money;

    /**
     * Constructor with zero balance
     */
    public Manager_IM ()
    {
        this.money = 0;
    }

    /**
     * Constructor with entered balance
     */
    public Manager_IM (int money)
    {
        this.money = money;
    }

    /**
     * Get the current balance
     */
    public double getMoney()
    {
        return money;
    }

    /**
     * Add to the balance
     */
    public void addMoney(double amt)
    {
        money += amt;
    }

    /**
     * Will allow for restock of snacks
     */
    public void restockS(Inventory_IM inv, int snackNum)
    {
        double cost = inv.getCostBulkS().get(snackNum);//get the cost of bulk

        if (cost > money){//not enough balance
            System.out.println("Your balance is insufficient, peasant");
        }else{
            double numBoughtDouble = cost / inv.getSnacks().get(snackNum).getCost();//divide the cost of bulk by cost of one to get the maximum number of units that can be bought
            int numBought = (int)Math.floor(numBoughtDouble);
            inv.addToStockS(snackNum, numBought);//add the new units
            money -= cost;
            System.out.println("Bought: " + inv.getSnacks().get(snackNum).getName());
            System.out.println("Your current balance is " + money);
        }
    }

    /**
     * Will allow for restock of drinks
     */
    public void restockD(Inventory_IM inv, int drinkNum)
    {
        double cost = inv.getCostBulkD().get(drinkNum);

        if (cost > money){
            System.out.println("Your balance is insufficient, peasant");
        }else{
            double numBoughtDouble = cost / inv.getDrinks().get(drinkNum).getCost();//divide the cost of bulk by cost of one to get the maximum number of units that can be bought
            int numBought = (int)Math.floor(numBoughtDouble);
            inv.addToStockD(drinkNum, numBought);//add the new units
            money -= cost;
            System.out.println("Bought: " + inv.getDrinks().get(drinkNum).getName());
            System.out.println("Your current balance is " + money);
        }
    }

    /**
     * Converts the object's info into string
     */
    public String toString()
    {
        String output = "Current balance (manager): $" + money;
        return output;
    }
}