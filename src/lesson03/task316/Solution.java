package lesson03.task316;


import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Collections;
import java.util.List;

public class Solution {

    public static void main(String[] args) throws IOException{
        String selectedOperation;
        String fileName;

        try(BufferedReader bf=new BufferedReader(new InputStreamReader(System.in))) {

            while (true){
                System.out.println("Enter number of operation or \"q\" for exit program");
                System.out.println("\t 1-Read from file");
                System.out.println("\t 2-Write to file");
                System.out.println("\t 3-Append to file");

                selectedOperation=bf.readLine();
                if (selectedOperation.equals("q")) {
                    break;
                }

                if (!(selectedOperation.equals("1")
                        || selectedOperation.equals("2")
                        || selectedOperation.equals("3")))
                {
                    System.out.println("Incorrect operation, try again");
                    continue;
                }

                System.out.println("Enter full path to file");
                fileName=bf.readLine();
                switch (selectedOperation){
                    case "1": {
                        readFromFileAndDisplayConsole(fileName);
                        break;
                    }
                    case "2": {
                        writeToFile(fileName,"Writed string ");
                        break;
                    }
                    case "3": {
                        appendToFile(fileName, "Appended string");
                        break;
                    }
                }
            }
        }

        System.out.println("\nThank you for using this program. Bye. \n");

    }



    private static void appendToFile(String fileName, String stringToAppend){

        try {
            Files.write(
                    Paths.get(fileName),
                    stringToAppend.getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error occurred: "+e);
            System.out.println("Try again");
        }
        System.out.println("Appending to file "+fileName+" successful \n");
    }

    private static void writeToFile(String fileName,String stringToWrite) {

        try {
            Files.write(Paths.get(fileName),stringToWrite.getBytes());
            System.out.println("Writing to file "+fileName+" successful \n");
        } catch (IOException e) {
            System.out.println("Error occurred: "+e);
            System.out.println("Try again");
        }
    }

    private static void readFromFileAndDisplayConsole(String fileName) {

        System.out.println("\nReading from file "+fileName+" ...\n");

        List<String> lines = Collections.emptyList();
        try
        {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        }
        catch (IOException e)
        {
            System.out.println("Error occurred: "+e);
            System.out.println("Try again\n");
            return;
        }
        lines.stream().forEach(System.out::println);
        System.out.println("\nReading from file "+fileName+" successful\n");
    }

}
