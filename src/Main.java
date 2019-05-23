import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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


        Iterator<GraphEdge> titty = tempSet.iterator();
        while (titty.hasNext()){
            System.out.println(titty.next());
        }


        System.out.println("\n");
        KruskalUnionFind MSTobject = new KruskalUnionFind(tempSet, Ants);
        ArrayList<GraphEdge> MSTgraph = MSTobject.getMSP();

        int[][] antVertsID = new int[MSTgraph.size()][MSTgraph.size()];
        Iterator<GraphEdge> it = MSTgraph.iterator();
        int j=0;
        while(it.hasNext() ) {
            GraphEdge tempEdge = it.next();
            antVertsID[j][0] = tempEdge.getPoint1().getId();
            antVertsID[j][1] = tempEdge.getPoint2().getId();
            j++;
        }
        Arrays.sort(antVertsID, (a, b) -> Integer.compare(a[0], b[0]));

        System.out.println(MSTobject.getWeight());
        for (int i=0; i<j; i++){
            System.out.println(antVertsID[i][0] + " " + antVertsID[i][1]);
        }


        System.out.println("\n");
        GaleShapley GSobject = new GaleShapley(tempSet, Ants);
        GSobject.getAntsStablePairs();

        for (int i=0; i<Ants.size(); i+=2){
            System.out.println(i+1 + " " + Ants.get(i).getPairIdGS());
        }
    }
}
