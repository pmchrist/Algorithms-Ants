/*
  Christos Perchanidis
  AEM: 3194
  pmchrist@csd.auth.gr
  Aristotle University of Thessaloniki
  May 2019
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/**
 *
 * This class finds solution to the Stable Marriage Problem (Gale Shapley Algorithm kinda)
 * It passes all possible pairs for each Ant and finds the stable pair for him (searches for a new one only if Black Ant broke up with a Red one)
 * Uses lesser memory though
 *
 */
class GaleShapleyProposeToAll {

    //Setting pointers to the original values
    private final TreeSet<GraphEdge> antsEdgesOriginal;
    private final ArrayList<Ant> antsVertOriginal;

    /**
     *
     * Constructor
     *
     * @param antsEdges Graph in which we will search for the best mates (based on weight of edges)
     * @param antsVert original Ants (Points of the Graph)
     */
    public GaleShapleyProposeToAll(TreeSet<GraphEdge> antsEdges, ArrayList<Ant> antsVert){
        this.antsEdgesOriginal = antsEdges;
        this.antsVertOriginal = antsVert;
    }

    /**
     *
     * Finds stable pairs
     *
     */
    public void getAntsStablePairs() {

        //Setting start values
        int foundPairs = 0;
        Ant currentAntVert;
        GraphEdge currentAntEdge;

        //Until we find pairs for all Ants we won't stop
        while (foundPairs < antsVertOriginal.size()/2){
            //We will look for mates for Red Ants throw group of Black Ants
            for (int currentAntID=0; currentAntID<antsVertOriginal.size(); currentAntID += 2){
                //Setting Ant for checking
                currentAntVert = antsVertOriginal.get(currentAntID);
                //Checking if already got pair, then pass
                if (currentAntVert.getPairIdGS() != -1){
                    continue;
                }
                //Searching for a mate throw all Edges in Graph. Because our Ants must be connected to form a pair
                for (GraphEdge graphEdge : antsEdgesOriginal) {
                    currentAntEdge = graphEdge;
                    //If our point is in edge
                    if (currentAntEdge.getPoint1() == currentAntVert || currentAntEdge.getPoint2() == currentAntVert) {
                        //Possible better pair. This edge connects Ants of different color and has lesser weight then current pair
                        if (currentAntEdge.getWeight() < currentAntVert.getPairWeightGS() &&
                                currentAntEdge.getPoint1().getId() % 2 != currentAntEdge.getPoint2().getId() % 2) {
                            //If this new pair is better for both both
                            if (currentAntEdge.getWeight() < currentAntEdge.getPoint1().getPairWeightGS() &&
                                    currentAntEdge.getWeight() < currentAntEdge.getPoint2().getPairWeightGS()) {
                                //Update Values
                                //Set previous pairs IDs to pairID -1 (not existent). Update pairs found amount
                                if (currentAntEdge.getPoint1().getPairIdGS() != -1) {
                                    antsVertOriginal.get(currentAntEdge.getPoint1().getPairIdGS() - 1).setPairIdGS(-1);
                                    antsVertOriginal.get(currentAntEdge.getPoint1().getPairIdGS() - 1).setPairWeightGS(Double.MAX_VALUE);
                                    foundPairs--;
                                }
                                if (currentAntEdge.getPoint2().getPairIdGS() != -1) {
                                    antsVertOriginal.get(currentAntEdge.getPoint2().getPairIdGS() - 1).setPairIdGS(-1);
                                    antsVertOriginal.get(currentAntEdge.getPoint2().getPairIdGS() - 1).setPairWeightGS(Double.MAX_VALUE);
                                    foundPairs--;
                                }
                                //Set new pairs IDs to ids in edge and update current pair weight. Update pairs found amount
                                int temp1 = currentAntEdge.getPoint1().getId();
                                int temp2 = currentAntEdge.getPoint2().getId();
                                antsVertOriginal.get(temp1 - 1).setPairIdGS(temp2);
                                antsVertOriginal.get(temp1 - 1).setPairWeightGS(currentAntEdge.getWeight());
                                antsVertOriginal.get(temp2 - 1).setPairIdGS(temp1);
                                antsVertOriginal.get(temp2 - 1).setPairWeightGS(currentAntEdge.getWeight());
                                foundPairs++;
                            }
                        }
                    }
                }
            }
        }
    }
}
