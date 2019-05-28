import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 *
 * Just generates random formatted input for debugging
 *
 */
public class inputGenerator {

    public inputGenerator(int ants) throws IOException {
        FileWriter fileWriter = new FileWriter(new File("input.txt"));
        PrintWriter printWriter = new PrintWriter(fileWriter);
        Random random = new Random();

        for (int i = 1; i < ants + 1; i++) {
            printWriter.print(i + " " + random.nextDouble()*500 + " " + random.nextDouble()*500 + " ");
            if (i % 2 == 1) printWriter.print(random.nextInt(10000));
            else {
                for (int j = 0; j < 5; j++)
                    printWriter.print(random.nextInt(1000) + " ");
            }
            printWriter.println("");
        }

        printWriter.flush();
        printWriter.close();
        fileWriter.close();
        System.out.println("File created.");
    }
}
