package lesson05.task520;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ExpansesHandler{

    public static Scanner sc=new Scanner(System.in);

    //added class variable
    private ArrayList<Integer> expenses;

    //added constructor
    public ExpansesHandler() {
        this.expenses = new ArrayList<>();
    }

    //added method
    public void readExpensesFromArray(Integer[] source){
        //in future we can read expenses from different sources
        Collections.addAll(expenses,source);
    }

    //added method
    public void printExpenses(){
        System.out.println(expenses+"\n");
    }

    //added method
    public void addExpenditure(int value) {
        expenses.add(value);
    }

    //added method
    public void deleteExpenditures() {


        System.out.println("You are about the delete all your expenses! \nConfirm again by selecting \'y\' ...\n");
        String con_choice = sc.next();
        if (con_choice.equals("y")) {
            expenses.clear();
            System.out.println("All your expenses are erased!\n");
        } else {
            System.out.println("Oops... try again!");
        }
    }


    public boolean isExpenseFounded(int searchingExpense) {
        return expenses.contains(searchingExpense);
    }


    private void sortExpenses() {
        Collections.sort(expenses);
    }


    public static void main(String[] args) throws InterruptedException{


        /*System.out.println("Hello World!");*/
        System.out.println("\n**************************************\n");
        System.out.println("\tWelcome to TheDesk \n");
        System.out.println("**************************************");


        ExpansesHandler expansesHandler=new ExpansesHandler();
        //in future we can read expenses from different sources

        expansesHandler.readExpensesFromArray(new Integer[]{1000,2300,4500,32000,110});

        //add main loop
        int options=0;

        while (true){

            System.out.println(
                    "1. I wish to review my expenditure ,\n" +
                            "2. I wish to add my expenditure,\n" +
                            "3. I wish to delete my expenditure,\n" +
                            "4. I wish to sort the expenditures,\n" +
                            "5. I wish to search for a particular expenditure,\n" +
                            "6. Close the application \n" +
                            "\nEnter your choice:\t");

            options=sc.nextInt();

            if (options==6) {
                System.out.println("Closing your application... \nThank you!");
                break;
            }

            switch (options){
                case 1:
                    System.out.println("Your saved expenses are listed below: \n");
                    expansesHandler.printExpenses();
                    break;
                case 2:
                    System.out.println("Enter the value to add your Expense: \n");
                    expansesHandler.addExpenditure(sc.nextInt());
                    System.out.println("Your value is updated\n");
                    expansesHandler.printExpenses();
                    break;
                case 3:
                    expansesHandler.deleteExpenditures();
                    break;
                case 4:
                    expansesHandler.sortExpenses();
                    System.out.println("Your expenses sorted in ascending order: \n");
                    expansesHandler.printExpenses();
                    break;
                case 5:

                    System.out.println("Enter the value to search: \n");
                    if (expansesHandler.isExpenseFounded(sc.nextInt()))
                        System.out.println("Expense found \n");
                    else
                        System.out.println("Expense not found \n");
                    break;
                default:
                    System.out.println("You have made an invalid choice!");
                    break;
            }

            Thread.sleep(1000);

        }

    }


}
