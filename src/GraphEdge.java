public class GraphEdge implements Comparable<GraphEdge>{
    private Ant point1;
    private Ant point2;
    private double weight;

    public GraphEdge (Ant point1, Ant point2){
        this.point1 = point1;
        this.point2 = point2;
        weight = Math.sqrt(Math.pow(point2.getxCoordinate() - point1.getxCoordinate(), 2) + Math.pow(point2.getyCoordinate() - point1.getyCoordinate(), 2));
    }

    public Ant getPoint1() {
        return point1;
    }

    public Ant getPoint2() {
        return point2;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString(){
        return "" + point1.getId() + " " + point2.getId();
    }

    @Override
    public int compareTo(GraphEdge other) {
        if (other.getWeight() > weight){
            return 1;
        }else{
            if (other.getWeight() == weight){
                return 0;
            }else{
                return -1;
            }
        }
    }
}
