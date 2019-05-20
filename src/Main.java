import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args){

        ArrayList<Ant> Ants = new ArrayList<>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String currentLine = reader.readLine();
            int currentInt;
            while (currentLine != null) {
                Scanner scanner = new Scanner(currentLine);
                while (scanner.hasNext()) {
                    if (scanner.hasNextInt()) {
                        currentInt = scanner.nextInt();
                        if (currentInt % 2 != 0){
                            Ants.add(new Ant(currentInt, scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
                        }else {
                            Ants.add(new Ant(currentInt, scanner.nextInt(), scanner.nextInt(),
                                    new int[]{scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt()}));
                        }
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

        DelaunayTriangulation temp = new DelaunayTriangulation();
        TreeSet tempSet = temp.getEdges(Ants);

        Iterator<GraphEdge> it = tempSet.iterator();
        while(it.hasNext() ) {
            System.out.println(it.next());
        }
    }
}
