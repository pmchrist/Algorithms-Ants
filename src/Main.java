import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException{

        ArrayList<Ant> Ants = new ArrayList<>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String currentLine = reader.readLine();
            int currentInt;
            while (currentLine != null) {
                Scanner scanner = new Scanner(currentLine);
                while (scanner.hasNext()) {
                    currentInt = scanner.nextInt();
                    if (currentInt % 2 != 0){
                        Ants.add(new Ant(currentInt, scanner.nextDouble(), scanner.nextDouble(), scanner.nextInt()));
                    }else {
                        Ants.add(new Ant(currentInt, scanner.nextDouble(), scanner.nextDouble(),
                                new int[]{scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt()}));
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

        int[][] antVertsID = new int[MSTgraph.size()][MSTgraph.size()];
        Iterator<GraphEdge> it = MSTgraph.iterator();
        int j=0;
        while(it.hasNext() ) {
            GraphEdge tempEdge = it.next();
            antVertsID[j][0] = tempEdge.getPoint1().getId();
            antVertsID[j][1] = tempEdge.getPoint2().getId();
            j++;
        }
        Arrays.sort(antVertsID, Comparator.comparingInt(a -> a[0]));


        FileWriter writer = new FileWriter("output1.txt");
        BufferedWriter buffer = new BufferedWriter(writer);
        buffer.write(MSTobject.getWeight() + "\n");
        for (int i=0; i<j; i++){
            buffer.write(antVertsID[i][0] + " " + antVertsID[i][1] + "\n");
        }
        writer.flush();
        buffer.flush();


        GaleShapley GSobject = new GaleShapley(tempSet, Ants);
        GSobject.getAntsStablePairs();

        writer = new FileWriter("output2.txt");
        buffer = new BufferedWriter(writer);
        for (int i=0; i<Ants.size(); i+=2){
            buffer.write(i+1 + " " + Ants.get(i).getPairIdGS() + "\n");
        }
        writer.flush();
        buffer.flush();


        DynamicWeights weightsObject = new DynamicWeights(Ants);
        weightsObject.calculateWeights();

        writer = new FileWriter("output3.txt");
        buffer = new BufferedWriter(writer);
        for (int i=0; i<Ants.size(); i+=2){
            if (Ants.get(i).getObjectWeight(0) == -1){
                continue;
            }
            buffer.write((i+1) + " " + (i+2) + " " + Ants.get(i).getObjectWeight(0) + " " + Ants.get(i).getObjectWeight(1) +  " " + Ants.get(i).getObjectWeight(2) +  " " + Ants.get(i).getObjectWeight(3) +  " " + Ants.get(i).getObjectWeight(4) + "\n");
        }
        writer.flush();
        buffer.flush();
        writer.close();
        buffer.close();
    }
}
