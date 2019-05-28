/*
  Christos Perchanidis
  AEM: 3194
  pmchrist@csd.auth.gr
  Aristotle University of Thessaloniki
  May 2019
 */

/**
 *
 * Class that represents Edge in Graph
 *
 */
class GraphEdge implements Comparable<GraphEdge>{
    private final Ant point1;
    private final Ant point2;
    private final double weight;

    /**
     *
     * Setter. It calculates edge's weight automatically with euclidean distance
     *
     * @param point1 Ant 1
     * @param point2 Ant 2
     */
    public GraphEdge (Ant point1, Ant point2){
        this.point1 = point1;
        this.point2 = point2;
        this.weight = Math.sqrt(Math.pow(point2.getxCoordinate() - point1.getxCoordinate(), 2) + Math.pow(point2.getyCoordinate() - point1.getyCoordinate(), 2));
    }

    //This method was used for debugging
    @Override
    public String toString(){
        return "" + point1.getId() + " " + point2.getId();
    }

    //Compares Edges based on their weights
    @Override
    public int compareTo(GraphEdge other) {
        return Double.compare(weight, other.getWeight());
    }

    //Standard Getters
    public Ant getPoint1() {
        return point1;
    }

    public Ant getPoint2() {
        return point2;
    }

    public double getWeight() {
        return weight;
    }
}
