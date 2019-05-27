/*
  Christos Perchanidis
  AEM: 3194
  pmchrist@csd.auth.gr
  Aristotle University of Thessaloniki
  May 2019
 */

import java.util.ArrayList;
import java.util.TreeSet;

/**
 *
 * This class creates graph that we use in the whole project later
 *
 */
class allEdges{

    /**
     *
     * Finding graph that connects all Ants
     *
     * @param Ants set of points to connect
     * @return TreeSet of edges in a graph
     */
    public TreeSet<GraphEdge> getEdges(ArrayList<Ant> Ants){
        //We need edges sorted, therefore using TreeSet
        TreeSet<GraphEdge> result = new TreeSet<>();

        for (int i=0; i<Ants.size(); i++){
            System.out.println(i);
            for (int j=i+1; j<Ants.size(); j++){
                result.add(new GraphEdge(Ants.get(i), Ants.get(j)));
            }
        }
        return result;
    }

}