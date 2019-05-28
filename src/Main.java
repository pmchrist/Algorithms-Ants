/*
  Christos Perchanidis
  AEM: 3194
  pmchrist@csd.auth.gr
  Aristotle University of Thessaloniki
  May 2019
 */

import java.io.*;
import java.util.*;

/**
 *
 * Main class, contains all commands to execute needed functions, file input and output
 *
 */
class Main {

    public static void main(String[] args) throws IOException{

        /*
        //Input Generator for Debugging
        inputGenerator creator = new inputGenerator(200);
        */

        //Array that contains all Ants
        ArrayList<Ant> Ants = new ArrayList<>();

        //File Input
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            //Reading line by line
            String currentLine = reader.readLine();
            //First input is always id and int. On this depends what type of constructor for the Ant will be called
            int currentInt;
            while (currentLine != null) {
                Scanner scanner = new Scanner(currentLine);
                scanner.useLocale(Locale.US);
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

        System.out.println("File read");

        /*
        //It was unused because time for creating Delaunay Triangulation is much higher then finding MST with all possible edges of graph
        //For optimization purposes we will use Graph that contains not all the edges but only necessary ones, it's created with help of the Delaunay Triangulation (more in the class)
        DelaunayTriangulation temp = new DelaunayTriangulation();
        TreeSet<GraphEdge> tempSet = temp.getEdges(Ants);

        System.out.println("Done Triangulation");
        */

        //Creating Array with edges that connect all Ants
        allEdges tempAll = new allEdges();
        TreeSet<GraphEdge> tempAllSet = tempAll.getEdges(Ants);

        System.out.println("Array Created");

        //Finding Minimum Spanning Tree
        KruskalUnionFind MSTobject = new KruskalUnionFind(tempAllSet, Ants);
        ArrayList<GraphEdge> MSTgraph = MSTobject.getMSP();

        //Formatting Output for A part. It is stored in temporary array, that is sorted by ids of Ants
        int[][] antVertsID = new int[MSTgraph.size()][MSTgraph.size()];
        Iterator<GraphEdge> it = MSTgraph.iterator();
        int j=0;
        while(it.hasNext() ) {
            //We are just getting ids if each Point of each edge in MST
            GraphEdge tempEdge = it.next();
            antVertsID[j][0] = tempEdge.getPoint1().getId();
            antVertsID[j][1] = tempEdge.getPoint2().getId();
            j++;
        }
        //Sorting array
        Arrays.sort(antVertsID, Comparator.comparingInt(a -> a[0]));


        //Writing the A part to a file
        FileWriter writer = new FileWriter("output1.txt");
        BufferedWriter buffer = new BufferedWriter(writer);
        //Outputting overall Graph Weight
        buffer.write(MSTobject.getWeight() + "\n");
        //Outputting
        for (int i=0; i<j; i++){
            buffer.write(antVertsID[i][0] + " " + antVertsID[i][1] + "\n");
        }
        writer.flush();
        buffer.flush();

        System.out.println("Part A done");


        /*
        //It was unused because it does not materialize the Gale Shapley algorithm as it must be done for the assignment purposes. Nonetheless I couldn't just cut it.
        //Finding Ant Pairs with Gale Shapley optimized for Memory
        GaleShapleyProposeToAll GSobject = new GaleShapleyProposeToAll(tempAllSet, Ants);
        GSobject.getAntsStablePairs();

        //Writing the B part to a file
        writer = new FileWriter("output2M.txt");
        buffer = new BufferedWriter(writer);
        //Each Ant holds information about it's mate
        for (int i=0; i<Ants.size(); i+=2){
            buffer.write(i+1 + " " + Ants.get(i).getPairIdGS() + "\n");
        }
        writer.flush();
        buffer.flush();

        System.out.println("Part B MEMORY done");
        */


        //Finding Ant Pairs with Gale Shapley
        GaleShapleyArrays GSobjectArr = new GaleShapleyArrays(tempAllSet, Ants);
        GSobjectArr.getAntsStablePairs();

        //Writing the B part to a file
        writer = new FileWriter("output2.txt");
        buffer = new BufferedWriter(writer);
        //Each Ant holds information about it's mate
        for (int i=0; i<Ants.size(); i+=2){
            buffer.write(i+1 + " " + Ants.get(i).getPairIdGS() + "\n");
        }
        writer.flush();
        buffer.flush();

        System.out.println("Part B done");



        //Finding optimal fillings for Ants
        DynamicWeights weightsObject = new DynamicWeights(Ants);
        weightsObject.calculateWeights();

        //Writing the C part to a file
        writer = new FileWriter("output3.txt");
        buffer = new BufferedWriter(writer);
        //Each Ant holds information about it's fillings
        for (int i=0; i<Ants.size(); i+=2){
            //If algorithm can't fill the basket it set's value of first seed amount to -1, therefore we won't output anything for this Ant
            if (Ants.get(i).getObjectWeight(0) == -1){
                continue;
            }
            buffer.write((i+1) + " " + (i+2) + " " + Ants.get(i).getObjectWeight(0) + " " + Ants.get(i).getObjectWeight(1) +  " " + Ants.get(i).getObjectWeight(2) +  " " + Ants.get(i).getObjectWeight(3) +  " " + Ants.get(i).getObjectWeight(4) + "\n");
        }
        writer.flush();
        buffer.flush();
        writer.close();
        buffer.close();

        System.out.println("Part C done");
    }
}
