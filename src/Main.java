import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        //Create Array List of Ants


        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String currentLine = reader.readLine();
            while (currentLine != null) {
                System.out.println(currentLine);

                Scanner scanner = new Scanner(currentLine);
                while (scanner.hasNext()) {
                    if (scanner.hasNextInt()) {

                        //Complete arraylist of Ants

                    }
                }
                scanner.close();

                // read next currentLine
                currentLine = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
