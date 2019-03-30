/**
 * This class is an abstraction for the inventory.
 * It takes in the text files as parameters for the constructor that follow the format mentioned
 * in the description in the driver.
 *
 * @IMukhametzhanova
 * @1.8
 * 11/8/18
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class Inventory_IM
{
    private ArrayList<Snack_IM> snacks = new ArrayList<Snack_IM>();//List of all snacks
    private ArrayList<Integer> snacksAmt = new ArrayList<Integer>();//Amount of each snack
    private ArrayList<Double> snackCostBulk = new ArrayList<Double>();//Cost of each bulk of a snack

    private ArrayList<Drink_IM> drinks = new ArrayList<Drink_IM>();//List of all drinks
    private ArrayList<Integer> drinksAmt = new ArrayList<Integer>();//Amount of each drink
    private ArrayList<Double> drinkCostBulk = new ArrayList<Double>();//Cost of each bulk of a drink

    /**
     * Constructor
     * Needs a text file with needed info
     */
    public Inventory_IM(String snackTxt, String drinkTxt)
    {
        File fileSnack = new File(snackTxt);//snacks.txt
        File fileDrink = new File(drinkTxt);//drinks.txt

        ArrayList<String> snackName = new ArrayList<String>();//will be put into a Snack_IM object
        ArrayList<Double> snackCostOne = new ArrayList<Double>();//will be put into a Snack_IM object

        ArrayList<String> drinkName = new ArrayList<String>();//will be put into a Drink_IM object
        ArrayList<Double> drinkCostOne = new ArrayList<Double>();//will be put into a Drink_IM object

        String [] vals;//for storing the file's info
        String line;


        try {
            //snacks.txt
            Scanner scanner = new Scanner(fileSnack);
            while (scanner.hasNextLine()) {//get each line's info
                line = scanner.nextLine();
                vals = line.split(",");
                snackName.add(vals[0]);//name
                snacksAmt.add(Integer.parseInt(vals[1]));//amount
                snackCostBulk.add(Double.parseDouble(vals[2]));//cost of bulk
                snackCostOne.add(Double.parseDouble(vals[3]));//cost of one
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //ArrayList of snacks
        for (int i = 0; i < snackName.size(); i++){
            snacks.add(new Snack_IM(snackName.get(i), snackCostOne.get(i)));
        }
        //drinks.txt
        try {
            Scanner scanner = new Scanner(fileDrink);
            while (scanner.hasNextLine()) {//get each line's info
                line = scanner.nextLine();
                vals = line.split(",");
                drinkName.add(vals[0]);//name
                drinksAmt.add(Integer.parseInt(vals[1]));//amount
                drinkCostBulk.add(Double.parseDouble(vals[2]));//cost of bulk
                drinkCostOne.add(Double.parseDouble(vals[3]));//cost of one
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //ArrayList of drinks
        for (int i = 0; i < drinkName.size(); i++){
            drinks.add(new Drink_IM(drinkName.get(i), drinkCostOne.get(i)));
        }
    }

    /**
     * Returns an Arraylist of Snack_IM objects
     */
    public ArrayList<Snack_IM> getSnacks()
    {
        return snacks;
    }

    /**
     * Returns an Arraylist of Drink_IM objects
     */
    public ArrayList<Drink_IM> getDrinks()
    {
        return drinks;
    }

    /**
     * Returns an ArrayList of the amount of each snack
     */
    public ArrayList<Integer> getSnackAmt()
    {
        return snacksAmt;
    }

    /**
     * Returns an ArrayList of the amount of each drink
     */
    public ArrayList<Integer> getDrinkAmt()
    {
        return drinksAmt;
    }

    /**
     * Returns an ArrayList of the cost of each bulk of a snack
     */
    public ArrayList<Double> getCostBulkS()
    {
        return snackCostBulk;
    }

    /**
     * Returns an ArrayList of the cost of each bulk of a drink
     */
    public ArrayList<Double> getCostBulkD()
    {
        return drinkCostBulk;
    }

    /**
     * When one snack is bought
     */
    public void boughtOneS(int snackNum, Manager_IM m)
    {
        if (snacksAmt.get(snackNum) <= 0){//If no snack left
            System.out.println("No more in stock");
        }else{
            int temp = snacksAmt.get(snackNum) - 1;//one unit bought
            snacksAmt.set(snackNum, temp);
            System.out.println("Bought: " + snacks.get(snackNum).getName());//prints what you bought
            m.addMoney(snacks.get(snackNum).getCost());//money goes to the manager's balance
        }
    }

    /**
     * When one drink is bought
     */
    public void boughtOneD(int drinkNum, Manager_IM m)
    {
        if (drinksAmt.get(drinkNum) <= 0){//If no drink left
            System.out.println("No more in stock");
        }else{
            int temp = drinksAmt.get(drinkNum) - 1;//one unit bought
            drinksAmt.set(drinkNum, temp);
            System.out.println("Bought: " + drinks.get(drinkNum).getName());//prints what you bought
            m.addMoney(drinks.get(drinkNum).getCost());//money goes to the manager's balance
        }
    }

    /**
     * Add snacks to the inventory
     */
    public void addToStockS(int snackNum, int amt)
    {
        int temp = snacksAmt.get(snackNum) + amt;
        snacksAmt.set(snackNum, temp);
    }

    /**
     * Add drinks to the inventory
     */
    public void addToStockD(int drinkNum, int amt)
    {
        int temp = drinksAmt.get(drinkNum) + amt;
        drinksAmt.set(drinkNum, temp);
    }

    /**
     * Saves the changes to the invetory after the program is complete
     */
    public void whenDone() throws IOException
    {
        String nameS = "snacks.txt";
        String nameD = "drinks.txt";

        //Erase current contents
        PrintWriter writerS = new PrintWriter("snacks.txt");
        writerS.print("");
        writerS.close();

        //Add new info
        WriteFile dataS = new WriteFile(nameS, true);
        for (int i = 0; i < snacks.size(); i++){
            String newLine = "";
            newLine = newLine + snacks.get(i).getName() + ",";
            newLine = newLine + Integer.toString(snacksAmt.get(i)) + ",";
            newLine = newLine + Double.toString(snackCostBulk.get(i)) + ",";
            newLine = newLine + Double.toString(snacks.get(i).getCost());
            dataS.writeToFile(newLine);
        }

        //Erase current contents
        PrintWriter writerD = new PrintWriter("drinks.txt");
        writerD.print("");
        writerD.close();

        //Add new info
        WriteFile dataD = new WriteFile(nameD, true);
        for (int i = 0; i < drinks.size(); i++){
            String newLine = "";
            newLine = newLine + drinks.get(i).getName() + ",";
            newLine = newLine + Integer.toString(drinksAmt.get(i)) + ",";
            newLine = newLine + Double.toString(drinkCostBulk.get(i)) + ",";
            newLine = newLine + Double.toString(drinks.get(i).getCost());
            dataD.writeToFile(newLine);
        }
    }

    /**
     * Print out the company's inventory
     */
    public void printStock()
    {
        for (int i = 0; i < snacks.size(); i++){
            System.out.printf("%13s%13d%13.0f%13.2f", snacks.get(i).getName(), snacksAmt.get(i), snackCostBulk.get(i), snacks.get(i).getCost());
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < drinks.size(); i++){
            System.out.printf("%13s%13d%13.0f%13.2f", drinks.get(i).getName(), drinksAmt.get(i), drinkCostBulk.get(i), drinks.get(i).getCost());
            System.out.println();
        }
    }
}