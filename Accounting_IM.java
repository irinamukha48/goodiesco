/**
 * This class is an abstraction for accounting in the business.
 * It saves how much of each snack was sold and how much money in total was made from it
 *
 * @IMukhametzhanova
 * @1.8
 * 11/8/18
 */
import java.util.ArrayList;

public class Accounting_IM
{
    private ArrayList<Integer> snacksSold = new ArrayList<Integer>();//Arraylist for total units sold (snack)
    private ArrayList<Snack_IM> snacks = new ArrayList<Snack_IM>();//Arraylist for the snacks themselves
    private ArrayList<Double> eachSnackSales = new ArrayList<Double>();//Arraylist for how much money each snack was sold for in total

    private ArrayList<Integer> drinksSold = new ArrayList<Integer>();//Arraylist for total units sold (drink)
    private ArrayList<Drink_IM> drinks = new ArrayList<Drink_IM>();//Arraylist for the drinks themselves
    private ArrayList<Double> eachDrinkSales = new ArrayList<Double>();//Arraylist for how much money each drink was sold for in total

    /**
     * Constructor for objects of class Accounting_IM
     */
    public Accounting_IM(Inventory_IM inv)
    {
        snacks = (ArrayList) inv.getSnacks().clone();//Copy of snacks in the inventory
        for (int i = 0; i < snacks.size(); i++){
            snacksSold.add(0);//No units sold yet
            eachSnackSales.add(0.0);//No money made yet
        }
        drinks = (ArrayList) inv.getDrinks().clone();//Copy of drinks in the inventory
        for (int i = 0; i < drinks.size(); i++){
            drinksSold.add(0);//No units sold yet
            eachDrinkSales.add(0.0);//No money made yet
        }
    }

    /**
     * When a snack is bought, this method counts how much of that snack was sold in total and how much money
     * in total was made form it
     */
    public void boughtOneS(int snackNum)
    {
        int temp = snacksSold.get(snackNum) + 1; //one unit bought
        snacksSold.set(snackNum, temp);
        double temp1 = eachSnackSales.get(snackNum) + snacks.get(snackNum).getCost(); //how much money got from that unit
        eachSnackSales.set(snackNum,  temp1);
    }

    /**
     * When a drink is bought, this method counts how much of that drink was sold in total and how much money
     * in total was made form it
     */
    public void boughtOneD(int drinkNum)
    {
        int temp = drinksSold.get(drinkNum) + 1; //one unit bought
        drinksSold.set(drinkNum, temp);
        double temp1 = eachDrinkSales.get(drinkNum) + drinks.get(drinkNum).getCost(); //how much money got from that unit
        eachDrinkSales.set(drinkNum,  temp1);
    }

    /**
     * Print how much of a snack/drink was sold in a session and how much money in total was made from selling 
     * it
     */
    public void salesReport ()
    {
        for (int i = 0; i < snacks.size(); i++){
            System.out.printf("%13s%13d%13.2f", snacks.get(i).getName(), snacksSold.get(i), eachSnackSales.get(i));
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < drinks.size(); i++){
            System.out.printf("%13s%13d%13.2f", drinks.get(i).getName(), drinksSold.get(i), eachDrinkSales.get(i));
            System.out.println();
        }
    }
}