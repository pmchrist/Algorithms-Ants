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
        TreeSet<GraphEdge> tempSet = temp.getEdges(Ants);

        KruskalUnionFind MSTobject = new KruskalUnionFind(tempSet, Ants);
        ArrayList<GraphEdge> MSTgraph = MSTobject.getMSP();

        System.out.println(MSTobject.getWeight());
        //Right Output Formet with id Sorting
        Iterator<GraphEdge> itt = MSTgraph.iterator();
        while(itt.hasNext() ) {
            System.out.println(itt.next());
            //System.out.println(itt.next().getWeight());
        }
    }
}
