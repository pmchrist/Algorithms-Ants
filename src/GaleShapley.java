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
        int currentAntID = -1;
        boolean flag = false;

        while (foundPairs < antsVertOriginal.size()/2){

            flag = false;
            System.out.println(foundPairs);

            //If we have to restart
            currentAntID+=2;
            if (currentAntID > antsVertOriginal.size()) {
                currentAntID = 1;
            }

            currentAntVert = antsVertOriginal.get(currentAntID-1);
            //Checking if already got pair
            if (currentAntVert.getPairWeightIdGS() != -1){
                continue;
            }

            Iterator<GraphEdge> it = antsEdgesOriginal.descendingIterator();
            while (it.hasNext()){
                currentAntEdge = it.next();
                if (currentAntEdge.getPoint1() == currentAntVert || currentAntEdge.getPoint2() == currentAntVert){
                    //Possible better pair
                    if (currentAntEdge.getWeight() <= currentAntVert.getPairWeightGS()){
                        //If Better pair for both
                        if (currentAntEdge.getWeight() <= currentAntEdge.getPoint1().getPairWeightGS() &&
                                currentAntEdge.getWeight() <= currentAntEdge.getPoint2().getPairWeightGS()){
                            //Update Values
                            //Set previous pairs IDs to pairID -1
                            if (currentAntEdge.getPoint1().getPairWeightIdGS() != -1){
                                antsVertOriginal.get(currentAntEdge.getPoint1().getPairWeightIdGS()-1).setPairWeightIdGS(-1);
                                foundPairs--;
                            }
                            if (currentAntEdge.getPoint2().getPairWeightIdGS() != -1){
                                antsVertOriginal.get(currentAntEdge.getPoint2().getPairWeightIdGS()-1).setPairWeightIdGS(-1);
                                foundPairs--;
                            }
                            //Set pairs IDs to ids in edge
                            antsVertOriginal.get(currentAntEdge.getPoint1().getId()-1).setPairWeightIdGS(currentAntEdge.getPoint2().getId());
                            antsVertOriginal.get(currentAntEdge.getPoint2().getId()-1).setPairWeightIdGS(currentAntEdge.getPoint1().getId());
                            System.out.println(flag);
                            flag = true;
                        }
                    }
                }
            }
            if (flag){
                foundPairs++;
                foundPairs++;
            }
            System.out.println(foundPairs);
        }
    }
}
