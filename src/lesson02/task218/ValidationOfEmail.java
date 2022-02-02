package lesson02.task218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;

public class ValidationOfEmail {

    private static String[] getExistingEmails() {

        //creating Mock array
        String[] arr = new String[]{"a.orlov@mail.ru", "abc@google.com", "amit@hello.com"};
        return arr;
    }


    public static void main(String[] args) {

        String[] existingEmails = getExistingEmails();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            //main loop
            while (true) {

                System.out.println("Enter a validated E-mail ID . For exit enter \"q\" ");
                String result = bufferedReader.readLine().toLowerCase(Locale.ROOT);

                if (result.equals("q")) {
                    System.out.println("Thank you for using this program \n");
                    break;
                }

                long matches = Arrays.stream(existingEmails).filter(x -> x.equals(result)).count();

                if (matches > 0) System.out.println("This E-mail found in employees e-mails \n");
                else System.out.println("This E-mail NOT found in employees e-mails \n");


            }
        } catch (IOException e) {
            System.out.println("Something wrong");
        }
    }
}