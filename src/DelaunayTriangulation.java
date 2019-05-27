/**
 * Christos Perchanidis
 * AEM: 3194
 * pmchrist@csd.auth.gr
 * Aristotle University of Thessaloniki
 * May 2019
 */

import java.util.ArrayList;
import java.util.TreeSet;

/**
 *
 * This class created Delaunay Triangulation of source graph (with all Ants), this simplifies future calculations
 * For the Minimum Spanning Tree in Kruskal
 * https://en.wikipedia.org/wiki/Euclidean_minimum_spanning_tree#Subtree_of_Delaunay_triangulation (in a nutshell Minimum Spanning Tree is a sub graph of Delaunay Triangulation)
 * For the Stable Marriage Problem
 * Delaunay Triangulation by definition connects Ants from the whole space with a minimal distance.
 * It creates 3*AntsNumber-6 connections/edges therefore it will for sure connect Red and Black Ant (there is 50%/50% balance of them).
 * Hence, it gives us Graph that is optimized and connects Red and Black Ants.
 * https://en.wikipedia.org/wiki/Delaunay_triangulation
 * https://www.gamedev.net/forums/topic/696172-max-number-of-triangles-and-edges-in-delaunay-triangulation/
 *
 */
class DelaunayTriangulation{

    /**
     *
     * Finding Delaunay Triangulation for the given set of Ants (points in Euclidean Space)
     *
     * @param Ants set of points to triangulate
     * @return TreeSet of edges in a graph of triangulated points
     */
    public TreeSet getEdges(ArrayList<Ant> Ants){
        //We need edges sorted, therefore using TreeSet
        TreeSet<GraphEdge> result = new TreeSet<>();

        //We can't triangulate 2 points, so we add just one edge and return it
        if(Ants.size() == 2){
            result.add(new GraphEdge(Ants.get(1), Ants.get(0)));
            return result;
        }

        //Computing Triangulation with a method described here
        //https://en.wikipedia.org/wiki/Delaunay_triangulation#Algorithms
        //Because this topic is hard by itself, I have referenced this code
        //https://stackoverflow.com/questions/5825089/how-does-this-code-for-delaunay-triangulation-work
        for(int i = 0; i < Ants.size() - 2; i++){
            for(int j = i + 1; j < Ants.size(); j++){
                for (int k = i + 1; k < Ants.size(); k++){
                    if(j == k){
                        continue;
                    }
                    double xn = (Ants.get(j).getyCoordinate() - Ants.get(i).getyCoordinate()) *
                            (Ants.get(k).getzCoordinate() - Ants.get(i).getzCoordinate()) -
                            (Ants.get(k).getyCoordinate() - Ants.get(i).getyCoordinate()) *
                            (Ants.get(j).getzCoordinate() - Ants.get(i).getzCoordinate());


                    double yn = (Ants.get(k).getxCoordinate() - Ants.get(i).getxCoordinate()) *
                            (Ants.get(j).getzCoordinate() - Ants.get(i).getzCoordinate()) -
                            (Ants.get(j).getxCoordinate() - Ants.get(i).getxCoordinate()) *
                            (Ants.get(k).getzCoordinate() - Ants.get(i).getzCoordinate());

                    double zn = (Ants.get(j).getxCoordinate() - Ants.get(i).getxCoordinate()) *
                            (Ants.get(k).getyCoordinate() - Ants.get(i).getyCoordinate()) -
                            (Ants.get(k).getxCoordinate() - Ants.get(i).getxCoordinate()) *
                            (Ants.get(j).getyCoordinate() - Ants.get(i).getyCoordinate());

                    boolean flag;
                    if(flag = (zn < 0 ? 1 : 0) != 0){
                        for (Ant ant : Ants) {
                            flag = (flag) && ((ant.getxCoordinate() - Ants.get(i).getxCoordinate()) * xn +
                                    (ant.getyCoordinate() - Ants.get(i).getyCoordinate()) * yn +
                                    (ant.getzCoordinate() - Ants.get(i).getzCoordinate()) * zn <= 0);
                        }
                    }

                    if (!flag){
                        continue;
                    }
                    result.add(new GraphEdge(Ants.get(j), Ants.get(i)));
                    result.add(new GraphEdge(Ants.get(k), Ants.get(j)));
                    result.add(new GraphEdge(Ants.get(i), Ants.get(k)));
                }
            }
        }
        return result;
    }

}