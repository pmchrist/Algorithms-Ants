import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

//Implement Union and Find as it suppose to be

public class KruskalUnionFind {

    TreeSet<GraphEdge> antsEdgesOriginal;
    ArrayList<Ant> antsVertOriginal;
    ArrayList<GraphEdge> minimalSpanningTree;

    public KruskalUnionFind(TreeSet<GraphEdge> antsEdges, ArrayList<Ant> antsVert){
        this.antsEdgesOriginal = antsEdges;
        this.antsVertOriginal = antsVert;
        minimalSpanningTree = new ArrayList<>();
    }

    public ArrayList<GraphEdge> getMSP(){


        Iterator<GraphEdge> it = antsEdgesOriginal.descendingIterator();
        GraphEdge tempEdge = it.next();

        antsVertOriginal.get(tempEdge.getPoint2().getParentIdMST()-1).setParentIdMST(tempEdge.getPoint1().getParentIdMST());
        minimalSpanningTree.add(tempEdge);

        while(it.hasNext()) {

            tempEdge = it.next();

            if (!(findParent(tempEdge.getPoint1()) == findParent(tempEdge.getPoint2()))){
                //Perform Union (Set Point2 parent to Point1 parent)
                antsVertOriginal.get(tempEdge.getPoint2().getParentIdMST()-1).setParentIdMST(tempEdge.getPoint1().getParentIdMST());
                //PushToTree
                minimalSpanningTree.add(tempEdge);
            }
        }

        return minimalSpanningTree;
    }

    private int findParent(Ant vert){
        Ant originalVert = vert;
        while (vert.getId() != vert.getParentIdMST()){
            vert = antsVertOriginal.get(vert.getParentIdMST()-1);
        }
        int parentGroupID = vert.getId();
        int nextVertID;
        //Path Compressing
        while (originalVert.getId() != originalVert.getParentIdMST()){
            nextVertID = originalVert.getParentIdMST()-1;
            originalVert.setParentIdMST(parentGroupID);
            originalVert = antsVertOriginal.get(nextVertID);
        }
        return parentGroupID;
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
