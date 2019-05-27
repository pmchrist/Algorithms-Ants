/**
 * Christos Perchanidis
 * AEM: 3194
 * pmchrist@csd.auth.gr
 * Aristotle University of Thessaloniki
 * May 2019
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/**
 *
 * This class holds all needed functions for the A part
 * Finds Minimum Spanning Tree with Kruskal Algorithm with help of Union-Find data structure
 *
 */
class KruskalUnionFind {

    //Setting pointers to original values
    private final TreeSet<GraphEdge> antsEdgesOriginal;
    private final ArrayList<Ant> antsVertOriginal;
    //Holds edges of MST
    private final ArrayList<GraphEdge> minimalSpanningTree;

    /**
     *
     * Constructor
     *
     * @param antsEdges edges of a graph for which we want to find Minimum Spanning Tree
     * @param antsVert original Ants
     */
    public KruskalUnionFind(TreeSet<GraphEdge> antsEdges, ArrayList<Ant> antsVert){
        this.antsEdgesOriginal = antsEdges;
        this.antsVertOriginal = antsVert;
        minimalSpanningTree = new ArrayList<>();
    }

    /**
     *
     * Finds Minimum Spanning Tree for the given Graph
     *
     * @return edges of a Minimum Spanning Tree
     */
    public ArrayList<GraphEdge> getMSP(){

        //Reading first edge into Iterator
        Iterator<GraphEdge> it = antsEdgesOriginal.descendingIterator();
        GraphEdge tempEdge = it.next();
        //Combining first edge's Ants(points) to a set(Perform Union (Set Point2 parent to Point1 parent))
        antsVertOriginal.get(tempEdge.getPoint2().getParentIdMST()-1).setParentIdMST(tempEdge.getPoint1().getParentIdMST());
        minimalSpanningTree.add(tempEdge);
        //We must perform this for all Ants
        while(it.hasNext()) {
            //Reading next edge into iterator
            tempEdge = it.next();
            //If Ants got different Parents, that means they belong to different sets, so we will unify them
            if (!(findParent(tempEdge.getPoint1()) == findParent(tempEdge.getPoint2()))){
                //Perform Union (Set Point2 parent to Point1 parent)
                antsVertOriginal.get(tempEdge.getPoint2().getParentIdMST()-1).setParentIdMST(tempEdge.getPoint1().getParentIdMST());
                //PushToTree
                minimalSpanningTree.add(tempEdge);
            }
        }
        return minimalSpanningTree;
    }

    /**
     *
     * This method returns to which set Ant belongs, also performs path compression for the set members
     *
     * @param vert Ant for which we want to find it's parentID (set)
     * @return int to which set belongs
     */
    private int findParent(Ant vert){
        //Saving address of the original Ant for path compression
        Ant originalVert = vert;
        //Set's number is an ID of parent of parent (Ant) that points to itself
        while (vert.getId() != vert.getParentIdMST()){
            vert = antsVertOriginal.get(vert.getParentIdMST()-1);
        }
        //Saving Group's ID
        int parentGroupID = vert.getId();
        //Saving original parentID to compress path for remaining Ants
        int nextVertID;
        //Path Compressing (setting parentID of each Ant in the "road to the final parent" to the "final parent" value)
        while (originalVert.getId() != originalVert.getParentIdMST()){
            nextVertID = originalVert.getParentIdMST()-1;
            originalVert.setParentIdMST(parentGroupID);
            originalVert = antsVertOriginal.get(nextVertID);
        }
        return parentGroupID;
    }

    /**
     *
     * Returns weight of the Minimum Spanning Tree
     *
     * @return weight of MST
     */
    public double getWeight(){
        Iterator<GraphEdge> it = minimalSpanningTree.iterator();
        double weight = 0;
        //We just find the sum of weights of all edges in a MST
        while (it.hasNext()){
            weight += it.next().getWeight();
        }
        return weight;
    }
}
