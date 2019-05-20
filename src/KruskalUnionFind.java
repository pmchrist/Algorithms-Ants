import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

//Implement Union and Find as it suppose to be

public class KruskalUnionFind {

    TreeSet<GraphEdge> antsEdgesOriginal;
    HashSet<Ant> antsAdded;
    ArrayList<GraphEdge> minimalSpanningTree;

    public KruskalUnionFind(TreeSet<GraphEdge> antsEdges){
        this.antsEdgesOriginal = antsEdges;
        minimalSpanningTree = new ArrayList<>();
        antsAdded = new HashSet<>();
    }

    public ArrayList<GraphEdge> getMSP(){

        Iterator<GraphEdge> it = antsEdgesOriginal.descendingIterator();

        GraphEdge tempEdge = it.next();

        minimalSpanningTree.add(tempEdge);
        antsAdded.add(tempEdge.getPoint1());
        antsAdded.add(tempEdge.getPoint2());

        while(it.hasNext()) {

            tempEdge = it.next();

            if (!(antsAdded.contains(tempEdge.getPoint1()) && antsAdded.contains(tempEdge.getPoint1()))){
                minimalSpanningTree.add(tempEdge);
                antsAdded.add(tempEdge.getPoint1());
                antsAdded.add(tempEdge.getPoint2());
            }
        }

        return minimalSpanningTree;
    }

    public double getWeight(){
        Iterator<GraphEdge> it = minimalSpanningTree.iterator();
        double weight = 0;
        while (it.hasNext()){
            weight += it.next().getWeight();
        }
        return weight;
    }
}
