import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class GaleShapley {

    TreeSet<GraphEdge> antsEdgesOriginal;
    ArrayList<Ant> antsVertOriginal;

    public GaleShapley(TreeSet<GraphEdge> antsEdges, ArrayList<Ant> antsVert){
        this.antsEdgesOriginal = antsEdges;
        this.antsVertOriginal = antsVert;
    }

    public void getAntsStablePairs() {

        int foundPairs = 0;
        Ant currentAntVert;
        GraphEdge currentAntEdge;

        while (foundPairs < antsVertOriginal.size()){

            //System.out.println(foundPairs);

            for (int currentAntID=0; currentAntID<antsVertOriginal.size(); currentAntID += 2){

                currentAntVert = antsVertOriginal.get(currentAntID);
                //Checking if already got pair
                if (currentAntVert.getPairIdGS() != -1){
                    continue;
                }

                Iterator<GraphEdge> it = antsEdgesOriginal.descendingIterator();
                while (it.hasNext()){
                    currentAntEdge = it.next();
                    //If our point is in edge
                    if (currentAntEdge.getPoint1() == currentAntVert || currentAntEdge.getPoint2() == currentAntVert){
                        //Possible better pair
                        if (currentAntEdge.getWeight() < currentAntVert.getPairWeightGS() &&
                            currentAntEdge.getPoint1().getId() % 2 != currentAntEdge.getPoint2().getId() % 2){
                            //If Better pair for both
                            if (currentAntEdge.getWeight() < currentAntEdge.getPoint1().getPairWeightGS() &&
                                    currentAntEdge.getWeight() < currentAntEdge.getPoint2().getPairWeightGS()){
                                //Update Values
                                //Set previous pairs IDs to pairID -1
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
                                //Set pairs IDs to ids in edge
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
