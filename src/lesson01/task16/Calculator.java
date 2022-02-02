package lesson01.task16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {


    public static double inputNumber(){

        double result;
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            try {
                result = Double.parseDouble(bufferedReader.readLine());
                break;
            } catch (IOException|NumberFormatException e) {
                System.out.println("Invalid number, try again");
            }

        }

        return result;

    }

    public static String inputString(){
        String result;
        Scanner scanner=new Scanner(System.in);
        result = scanner.next();
        return result;
    }

    public static String inputOperation(){
        String result;

        System.out.println("Enter operation( \"+\" or \"-\" or \"*\" or \"/\")");
        Scanner scanner=new Scanner(System.in);

        while (true) {
            result = scanner.next();
            if (result.length()==1 &&(
                    result.equals("+")
                            ||result.equals("-")
                            ||result.equals("*")
                            ||result.equals("/")
            ))
                break;
            else
                System.out.println("Invalid operation. Try again");
        }

        return result;
    }

    private static double calculate(double firstNumber, double secondNumber, String operation){

        double result=0;

        switch (operation){
            case "+":
                result=firstNumber+secondNumber;
                break;
            case "-":
                result=firstNumber-secondNumber;
                break;
            case "*":
                result=firstNumber*secondNumber;
                break;
            case "/":
                result=firstNumber/secondNumber;
                break;
        }


        return result;

    }


    public static void main(String[] args) {

        double firstNumber,secondNumber, result;
        String outputFirstNumber, outputSecondNumber, outputResult, operation;

        //main loop
        while (true) {
            System.out.println("For calculating new expression enter any string. For exit enter \"q\" ");
            operation=inputString();
            if (operation.equals("q")) {
                break;
            }
            System.out.println("Enter firs number" );
            firstNumber=inputNumber();
            operation=inputOperation();
            System.out.println("Enter second number" );
            secondNumber=inputNumber();

            if (secondNumber==0&&operation.equals("/")){
                System.out.println("Incorrect operation. Division by zero.");
                continue;
            }

            result=calculate(firstNumber,secondNumber,operation);

            // ++ explicit type casting
            Boolean firstNumberIsLong=((long)firstNumber-firstNumber==0);
            Boolean secondNumberIsLong=((long)secondNumber-secondNumber==0);
            Boolean resultNumberIsLong=((long)result-result==0);

            if (firstNumberIsLong)
                outputFirstNumber=""+(long)firstNumber;
            else
                outputFirstNumber=""+firstNumber;


            if (secondNumberIsLong)
                outputSecondNumber=""+(long)secondNumber;
            else
                outputSecondNumber=""+secondNumber;


            if (resultNumberIsLong)
                outputResult=""+(long)result;
            else
                outputResult=""+result;

            //--explicit type casting

            System.out.println("\n"+outputFirstNumber+operation+outputSecondNumber+"="+outputResult+"\n");

        }
    }



}
