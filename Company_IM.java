/**
 * This is a simulation of a company that sells snacks and drinks.
 * It takes in two test files: snacks.txt and drinks.txt, which follow this format:
 * name,amount,cost of bulk,cost of one
 * You can choose if you are the manager or the customer.
 * The manager can generate a sales report, view the inventory, restock on a snack or drink and view
 * their current balance.
 * The customer can buy one snack or drink and view their balance.
 * Enter the number of the choice you want to make.
 *
 * NOTE: This project requires the WriteFile class
 *
 * @IMukhametzhanova
 * @1.8
 * 11/8/18
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Company_IM
{
    public static void main(String [] args)
    {
        Inventory_IM inv = new Inventory_IM("C:\\Users\\imukh\\IdeaProjects\\GoodiesCo\\src\\snacks.txt", "C:\\Users\\imukh\\IdeaProjects\\GoodiesCo\\src\\drinks.txt");//new inventory object
        Accounting_IM a = new Accounting_IM(inv);//new accounting object
        inv.printStock();
        Manager_IM m = new Manager_IM(30);//new manager object (with 30 dollars)
        Shopper_IM s = new Shopper_IM(100);//new shopper object (with 100 dollars)
        Scanner sc = new Scanner(System.in);
        Scanner scStr = new Scanner(System.in);

        String notSwitch = "y";
        int choice = 0;
        int choiceJob;
        int choiceBuy;
        int choiceSnack;
        int snackAmt;

        while (choice != 3) {//while not quitting
            System.out.println("Please select your role:\n1) Manager\n2) Customer\n3) Quit");
            choice = sc.nextInt();
            while (choice != 1 && choice != 2 && choice != 3){//if none of the choices
                System.out.println("Enter a number form 1 to 3");
                choice = sc.nextInt();
            }
            if (choice == 1){//manager
                System.out.println("Hello, manager! Your current balance is " + m.getMoney());
                notSwitch = "y";
                while (notSwitch.equals("y")){//while not quitting
                    System.out.println("Please select your action:\n1) Sales report\n2) View stock\n3) Restock\n4) View balance\n5) Quit");
                    choiceJob = sc.nextInt();
                    /**
                     * If 1 is chosen, the record of all sales will be shown, possibly calling Inventory class method
                     * If 2 is chosen, Inventory will call a method that displays the text file for the stored food/drinks
                     * If 3 is chosen, restock
                     * If 4 is chosen, current balance is shown
                     * If 5 is chosen, back to the main menu
                     */
                    if (choiceJob == 1) a.salesReport();
                    else if (choiceJob == 2) inv.printStock();
                    else if (choiceJob == 3) {
                        System.out.println("Snacks or drinks?\n1) Snack\n2) Drink");
                        choiceJob = sc.nextInt();
                        if (choiceJob == 1){
                            System.out.println("Which snack?");
                            for (int i = 0; i < inv.getSnacks().size(); i++){
                                System.out.println(i + ": " + inv.getSnacks().get(i).getName());
                            }
                            choiceSnack = sc.nextInt();
                            m.restockS(inv, choiceSnack);
                        }else if (choiceJob == 2){
                            System.out.println("Which drink?");
                            for (int i = 0; i < inv.getDrinks().size(); i++){
                                System.out.println(i + ": " + inv.getDrinks().get(i).getName());
                            }
                            choiceSnack = sc.nextInt();
                            m.restockD(inv, choiceSnack);
                        }
                    }else if (choiceJob == 4) System.out.println(m.toString());
                    else if (choiceJob == 5) {
                        notSwitch = "n";
                    }
                    System.out.println();
                }
            }else if (choice == 2){//customer
                System.out.println("Hello, customer!");
                notSwitch = "y";
                while (notSwitch.equals("y")){
                    System.out.println("Please select your action:\n1) Buy a snack\n2) Buy a drink\n3) View balance\n4) Quit");
                    choiceJob = sc.nextInt();
                    /**
                     * If 1 is chosen, user will be show a list of available items that they can choose from
                     * If 2 is chosen, their current balance will be shown
                     */
                    if (choiceJob == 1)
                    {
                        System.out.println("Which snack?");
                        for (int i = 0; i < inv.getSnacks().size(); i++){
                            System.out.println(i + ": " + inv.getSnacks().get(i).getName());
                        }
                        choiceSnack = sc.nextInt();
                        if (s.boughtOne(inv.getSnacks().get(choiceSnack).getCost())){
                            inv.boughtOneS(choiceSnack, m);
                            a.boughtOneS(choiceSnack);
                        }
                    }else if (choiceJob == 2){
                        System.out.println("Which drink?");
                        for (int i = 0; i < inv.getDrinks().size(); i++){
                            System.out.println(i + ": " + inv.getDrinks().get(i).getName());
                        }
                        choiceSnack = sc.nextInt();
                        if (s.boughtOne(inv.getDrinks().get(choiceSnack).getCost())){
                            inv.boughtOneD(choiceSnack, m);
                            a.boughtOneD(choiceSnack);
                        }
                    }else if (choiceJob == 3)
                    {
                        System.out.println(s.toString());
                    }else if (choiceJob == 4) notSwitch = "n";
                    System.out.println();
                }
            }
        }
        //Save the changes
        try {
            inv.whenDone();
        }catch (IOException e){
            System.out.println("Couldn't");
        }
    }
}
/**
 Chips           54           25         0.75
 Pop corn           45           22         0.75
 Pop Corners           45           22         0.75
 Veggie Sticks           45           21         0.75
 Rice Krispies           45           20         0.50
 Cookies           45           36         0.75
 Granola Bars           45           40         0.50
 Fruit Snacks           45           28         0.50
 Snack Bars           45           41         0.50

 Sunkist           45           39         1.25
 Coca Cola           45           39         1.25
 Ice Tea           45           38         1.25
 Yoohoo           45           35         0.50
 Sprite           45           38         1.25
 Ginger Ale           45           35         1.25
 Dr. Pepper           45           30         1.25
 Diet Coke           45           38         1.25
 Water           45           35         1.00
 Please select your role:
 1) Manager
 2) Customer
 3) Quit
 1
 Hello, manager! Your current balance is 30.0
 Please select your action:
 1) Sales report
 2) View stock
 3) Restock
 4) View balance
 5) Quit
 2
 Chips           54           25         0.75
 Pop corn           45           22         0.75
 Pop Corners           45           22         0.75
 Veggie Sticks           45           21         0.75
 Rice Krispies           45           20         0.50
 Cookies           45           36         0.75
 Granola Bars           45           40         0.50
 Fruit Snacks           45           28         0.50
 Snack Bars           45           41         0.50

 Sunkist           45           39         1.25
 Coca Cola           45           39         1.25
 Ice Tea           45           38         1.25
 Yoohoo           45           35         0.50
 Sprite           45           38         1.25
 Ginger Ale           45           35         1.25
 Dr. Pepper           45           30         1.25
 Diet Coke           45           38         1.25
 Water           45           35         1.00

 Please select your action:
 1) Sales report
 2) View stock
 3) Restock
 4) View balance
 5) Quit
 5

 Please select your role:
 1) Manager
 2) Customer
 3) Quit
 2
 Hello, customer!
 Please select your action:
 1) Buy a snack
 2) Buy a drink
 3) View balance
 4) Quit
 1
 Which snack?
 0: Chips
 1: Pop corn
 2: Pop Corners
 3: Veggie Sticks
 4: Rice Krispies
 5: Cookies
 6: Granola Bars
 7: Fruit Snacks
 8: Snack Bars
 2
 Bought: Pop Corners

 Please select your action:
 1) Buy a snack
 2) Buy a drink
 3) View balance
 4) Quit
 1
 Which snack?
 0: Chips
 1: Pop corn
 2: Pop Corners
 3: Veggie Sticks
 4: Rice Krispies
 5: Cookies
 6: Granola Bars
 7: Fruit Snacks
 8: Snack Bars
 2
 Bought: Pop Corners

 Please select your action:
 1) Buy a snack
 2) Buy a drink
 3) View balance
 4) Quit
 1
 Which snack?
 0: Chips
 1: Pop corn
 2: Pop Corners
 3: Veggie Sticks
 4: Rice Krispies
 5: Cookies
 6: Granola Bars
 7: Fruit Snacks
 8: Snack Bars
 2
 Bought: Pop Corners

 Please select your action:
 1) Buy a snack
 2) Buy a drink
 3) View balance
 4) Quit
 4

 Please select your role:
 1) Manager
 2) Customer
 3) Quit
 1
 Hello, manager! Your current balance is 32.25
 Please select your action:
 1) Sales report
 2) View stock
 3) Restock
 4) View balance
 5) Quit
 2
 Chips           54           25         0.75
 Pop corn           45           22         0.75
 Pop Corners           42           22         0.75
 Veggie Sticks           45           21         0.75
 Rice Krispies           45           20         0.50
 Cookies           45           36         0.75
 Granola Bars           45           40         0.50
 Fruit Snacks           45           28         0.50
 Snack Bars           45           41         0.50

 Sunkist           45           39         1.25
 Coca Cola           45           39         1.25
 Ice Tea           45           38         1.25
 Yoohoo           45           35         0.50
 Sprite           45           38         1.25
 Ginger Ale           45           35         1.25
 Dr. Pepper           45           30         1.25
 Diet Coke           45           38         1.25
 Water           45           35         1.00

 Please select your action:
 1) Sales report
 2) View stock
 3) Restock
 4) View balance
 5) Quit
 5

 Please select your role:
 1) Manager
 2) Customer
 3) Quit
 3



 /////////////////////////////////////////////////////////////////////////////////////////////////////
 Chips           54           25         0.75
 Pop corn           45           22         0.75
 Pop Corners           42           22         0.75
 Veggie Sticks           45           21         0.75
 Rice Krispies           45           20         0.50
 Cookies           45           36         0.75
 Granola Bars           45           40         0.50
 Fruit Snacks           45           28         0.50
 Snack Bars           45           41         0.50

 Sunkist           45           39         1.25
 Coca Cola           45           39         1.25
 Ice Tea           45           38         1.25
 Yoohoo           45           35         0.50
 Sprite           45           38         1.25
 Ginger Ale           45           35         1.25
 Dr. Pepper           45           30         1.25
 Diet Coke           45           38         1.25
 Water           45           35         1.00
 Please select your role:
 1) Manager
 2) Customer
 3) Quit
 1
 Hello, manager! Your current balance is 30.0
 Please select your action:
 1) Sales report
 2) View stock
 3) Restock
 4) View balance
 5) Quit
 2
 Chips           54           25         0.75
 Pop corn           45           22         0.75
 Pop Corners           42           22         0.75
 Veggie Sticks           45           21         0.75
 Rice Krispies           45           20         0.50
 Cookies           45           36         0.75
 Granola Bars           45           40         0.50
 Fruit Snacks           45           28         0.50
 Snack Bars           45           41         0.50

 Sunkist           45           39         1.25
 Coca Cola           45           39         1.25
 Ice Tea           45           38         1.25
 Yoohoo           45           35         0.50
 Sprite           45           38         1.25
 Ginger Ale           45           35         1.25
 Dr. Pepper           45           30         1.25
 Diet Coke           45           38         1.25
 Water           45           35         1.00

 Please select your action:
 1) Sales report
 2) View stock
 3) Restock
 4) View balance
 5) Quit
 5

 Please select your role:
 1) Manager
 2) Customer
 3) Quit
 3
 */