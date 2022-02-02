package final01.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


//one point to get access to input stream.

public class StringHelper {

    private static BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));

    public static String readString(){
        String result;
        while (true){
            try {
                result=bufferedReader.readLine();
                return result;
            } catch (IOException e) {
                System.out.println("Error with reading string.");
            }
        }
    }


}
