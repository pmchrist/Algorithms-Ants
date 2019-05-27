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
 * This class finds solution to the Stable Marriage Problem (Gale Shapley Algorithm)
 *
 */
class GaleShapley {

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
    public GaleShapley(TreeSet<GraphEdge> antsEdges, ArrayList<Ant> antsVert){
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
        while (foundPairs < antsVertOriginal.size()){
            //We will look for mates for Red Ants throw group of Black Ants
            for (int currentAntID=0; currentAntID<antsVertOriginal.size(); currentAntID += 2){
                //Setting Ant for checking
                currentAntVert = antsVertOriginal.get(currentAntID);
                //Checking if already got pair, then pass
                if (currentAntVert.getPairIdGS() != -1){
                    continue;
                }
                //Searching for a mate throw all Edges in Graph. Because our Ants must be connected to form a pair
                Iterator<GraphEdge> it = antsEdgesOriginal.descendingIterator();
                while (it.hasNext()){
                    currentAntEdge = it.next();
                    //If our point is in edge
                    if (currentAntEdge.getPoint1() == currentAntVert || currentAntEdge.getPoint2() == currentAntVert){
                        //Possible better pair. This edge connects Ants of different color and has lesser weight then current pair
                        if (currentAntEdge.getWeight() < currentAntVert.getPairWeightGS() &&
                            currentAntEdge.getPoint1().getId() % 2 != currentAntEdge.getPoint2().getId() % 2){
                            //If this new pair is better for both both
                            if (currentAntEdge.getWeight() < currentAntEdge.getPoint1().getPairWeightGS() &&
                                    currentAntEdge.getWeight() < currentAntEdge.getPoint2().getPairWeightGS()){
                                //Update Values
                                //Set previous pairs IDs to pairID -1 (not existent). Update pairs found amount
                                if (currentAntEdge.getPoint1().getPairIdGS() != -1){
                                    antsVertOriginal.get(currentAntEdge.getPoint1().getPairIdGS()-1).setPairIdGS(-1);
                                    antsVertOriginal.get(currentAntEdge.getPoint1().getPairIdGS()-1).setPairWeightGS(Double.MAX_VALUE);
                                    foundPairs--;
                                }
                                if (currentAntEdge.getPoint2().getPairIdGS() != -1){
                                    antsVertOriginal.get(currentAntEdge.getPoint2().getPairIdGS()-1).setPairIdGS(-1);
                                    antsVertOriginal.get(currentAntEdge.getPoint2().getPairIdGS()-1).setPairWeightGS(Double.MAX_VALUE);
                                    foundPairs--;
                                }
                                //Set new pairs IDs to ids in edge and update current pair weight. Update pairs found amount
                                antsVertOriginal.get(currentAntEdge.getPoint1().getId()-1).setPairIdGS(currentAntEdge.getPoint2().getId());
                                antsVertOriginal.get(currentAntEdge.getPoint1().getId()-1).setPairWeightGS(currentAntEdge.getWeight());
                                antsVertOriginal.get(currentAntEdge.getPoint2().getId()-1).setPairIdGS(currentAntEdge.getPoint1().getId());
                                antsVertOriginal.get(currentAntEdge.getPoint2().getId()-1).setPairWeightGS(currentAntEdge.getWeight());
                                foundPairs++;
                                foundPairs++;
                            }
                        }
                    }
                }
            }
        }
    }
}
