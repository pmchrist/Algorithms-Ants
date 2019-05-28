/*
  Christos Perchanidis
  AEM: 3194
  pmchrist@csd.auth.gr
  Aristotle University of Thessaloniki
  May 2019
 */

import java.util.*;

/**
 *
 * This class finds solution to the Stable Marriage Problem (Gale Shapley Algorithm)
 *
 */

class GaleShapleyArrays {

    //Setting pointers to the original values
    private final TreeSet<GraphEdge> antsEdgesOriginal;
    private final ArrayList<Ant> antsVertOriginal;

    /**
     * Constructor
     *
     * @param antsEdges Graph in which we will search for the best mates (based on weight of edges)
     * @param antsVert  original Ants (Points of the Graph)
     */
    public GaleShapleyArrays(TreeSet<GraphEdge> antsEdges, ArrayList<Ant> antsVert) {
        this.antsEdgesOriginal = antsEdges;
        this.antsVertOriginal = antsVert;
    }

    /**
     * Finds stable pairs
     */
    public void getAntsStablePairs() {

        //Reference size for all created arrays
        int size = antsVertOriginal.size();

        //Setting pair preferences. We will hold information about preferences of an each Ant in an sorted array
        //Because we already have all edges sorted by their weight (distance between Ants). We will first save preferences as an array of edges. No need for sorting again
        ArrayList<ArrayList<GraphEdge>> pairsEdgeTemp = new ArrayList<>(size);
        GraphEdge currentAntEdge;
        //Creating array sorted by preference for each Ant
        for (int i = 0; i < size; i++) {
            //Searching for possible mates throw all Edges in Graph. We must find only suitable edges
            //Initializing ant's preference list
            pairsEdgeTemp.add(new ArrayList<>(size));
            //Cycling all possible pairs
            for (GraphEdge graphEdge : antsEdgesOriginal) {
                currentAntEdge = graphEdge;
                //If our Ant is in the edge and the edge is between Red and Black one, add the edge
                if ((currentAntEdge.getPoint1().getId() == (i + 1) || currentAntEdge.getPoint2().getId() == (i + 1)) &&
                        currentAntEdge.getPoint1().getId() % 2 != currentAntEdge.getPoint2().getId() % 2) {
                    pairsEdgeTemp.get(i).add(currentAntEdge);
                }
            }
        }

        //Now we need to extract information from Edges into 2d array of Ants. For easier access
        Ant[][] pairs = new Ant[size][size];
        for (int i = 0; i < size; i++) {
            //We just need to loop throw all sorted edges and save right Ants (edge points)
            int j = 0;
            for (GraphEdge graphEdge : pairsEdgeTemp.get(i)) {
                currentAntEdge = graphEdge;
                //We don't know if Target Ant is a point1 or point2 in edge, therefore to save correct pair into array we must check who is who
                if (currentAntEdge.getPoint1().getId() == (i + 1)) {
                    pairs[i][j] = pairsEdgeTemp.get(i).get(j).getPoint2();
                } else {
                    pairs[i][j] = pairsEdgeTemp.get(i).get(j).getPoint1();
                }
                j++;
            }
        }

        //We must keep information about current mate of an Ant. To check if we find a better one
        //At start everyone got the worst possible mate
        int[] pairsPivots = new int[size];
        for (int i=0; i<size; i++){
            pairsPivots[i] = Integer.MAX_VALUE;
        }

        //Let's the dance begin. They will ask each other to make a pair, until there is no Red Ant who didn't have best possible stable pair
        //Loop will finish when no one can make a new proposal
        boolean haveBeenMadeProposal = true;
        while (haveBeenMadeProposal) {
            haveBeenMadeProposal = false;
            for (int i = 0; i < size; i += 2) {
                haveBeenMadeProposal = false;
                //If Ant got pair, he doesn't need a new one. Because it is already the best possible stable one
                if (antsVertOriginal.get(i).getPairIdGS() != -1){
                    continue;
                }
                //Let's find a new pair. We need to loop throw Ant's preference list
                for (int j = 0; j < size / 2; j++) {
                    //No need to continue loop if we find a better pair
                    if (haveBeenMadeProposal) break;
                    //Check if it is a better pair
                    Ant active = antsVertOriginal.get(i);
                    Ant passive = pairs[i][j];
                    //Checking array of preferences of a passive
                    for (int k = 0; k < size / 2; k++) {
                        //Found active in an array of preferences of passive
                        if (pairs[passive.getId() - 1][k].equals(active)) {
                            //if pair is not better
                            if (pairsPivots[passive.getId() - 1] < k) {
                                break;
                            } else {
                                //If pair is better we need to update values
                                //BreakUp with previous partners if any (reset their preference pivot and set pair id to -1)
                                if (active.getPairIdGS() != -1) {
                                    active.setPairIdGS(-1);
                                    pairsPivots[active.getId()-1] = Integer.MAX_VALUE;
                                }
                                if (passive.getPairIdGS() != -1) {
                                    passive.setPairIdGS(-1);
                                    pairsPivots[passive.getId()-1] = Integer.MAX_VALUE;
                                }
                                //Update active's current pair preference pivot and final mate id
                                active.setPairIdGS(passive.getId());
                                pairsPivots[active.getId()-1] = j;
                                //Update passive's current pair preference pivot and final mate id
                                passive.setPairIdGS(active.getId());
                                pairsPivots[passive.getId()-1] = k;
                                //We made an update so we can exit from some loops
                                haveBeenMadeProposal = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}

