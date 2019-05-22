import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class GaleShapley {

    TreeSet<GraphEdge> antsEdgesOriginal;
    ArrayList<Ant> antsVertOriginal;
    ArrayList<GraphEdge> antsStablePairs;

    public GaleShapley(TreeSet<GraphEdge> antsEdges, ArrayList<Ant> antsVert){
        this.antsEdgesOriginal = antsEdges;
        this.antsVertOriginal = antsVert;
    }

    public void getAntsStablePairs() {

        int foundPairs = 0;
        Ant currentAntVert;
        GraphEdge currentAntEdge;
        int currentAntID = 1;

        while (foundPairs != antsVertOriginal.size()/2){

            currentAntVert = antsVertOriginal.get(currentAntID-1);
            //Check if already got pair
            if (currentAntVert.getPairWeightIdGS() != -1){
                continue;
            }

            Iterator<GraphEdge> it = antsEdgesOriginal.descendingIterator();
            while (it.hasNext()){
                currentAntEdge = it.next();
                if (currentAntEdge.getPoint1() == currentAntVert || currentAntEdge.getPoint2() == currentAntVert){
                    //Possible better pair
                    if (currentAntEdge.getWeight() < currentAntVert.getPairWeightIdGS()){
                        //If Better pair for both
                        if (currentAntEdge.getWeight() < currentAntEdge.getPoint1().getPairWeightGS() &&
                                currentAntEdge.getWeight() < currentAntEdge.getPoint2().getPairWeightGS()){
                            //Update Values
                            //Set previous pairs to pair -1
                            //Set pairs to ids in edge
                        }
                    }
                }

            }
            currentAntID += 2;
        }
    }
}
