/**
 *
 * Class that holds all needed information about an Ant
 * (it could be optimized with children classes, but this is not a point of an assignment)
 *
 */
public class Ant {
    //Ant's id
    private final int id;
    //Ant's X Coordinate
    private final double xCoordinate;
    //Ant's Y Coordinate
    private final double yCoordinate;
    //Ant's temp coordinate for the Delaunay Triangulation (more in the class description)
    private final double zCoordinate;
    //Ant's target weight (how much it can hold in a basket)
    private final int weight;
    //Ant's seeds weights (for red ones it shows how many seeds of each type it holds, for black ones it holds weights of each seed type)
    private final int[] weights;
    //Ant's parent ID for Union-Find in Kruskal
    private int parentIdMST;
    //Ant's current pair's id
    private int pairIdGS;
    //Ant's current pair's weight. We compare it to find most suited mate in Gale Shapley algorithm
    private double pairWeightGS;

    /**
     *
     * Constructor for Red Ant
     *
     * @param id Ant's id
     * @param xCoordinate x coordinate in space
     * @param yCoordinate y coordinate in space
     * @param weight basket weight
     */
    public Ant(int id, double xCoordinate, double yCoordinate, int weight){
        //Setting id
        this.id=id;
        //Setting parent id for Union-Find to itself (because by default each Ant points to itself)
        this.parentIdMST=id;
        //Setting pair id for Gale Shapley to -1 (because Ant has no mate by default)
        this.pairIdGS =-1;
        //Setting pair weight for Gale Shapley to MAΧ (because we need to compare it with new values and find minimal one)
        this.pairWeightGS=Double.MAX_VALUE;
        //Setting X Coordinate
        this.xCoordinate=xCoordinate;
        //Setting Y Coordinate
        this.yCoordinate=yCoordinate;
        //Counting additional coordinate for a Delaunay Triangulation
        this.zCoordinate=xCoordinate*xCoordinate+yCoordinate*yCoordinate;
        //Setting basket size to a given one
        this.weight=weight;
        //Setting seeds weight to 0 (because we don't know the values yet)
        this.weights=new int[5];
        for (int i=0; i<5; i++){
            this.weights[i]=0;
        }
    }

    /**
     *
     * Constructor for Black Ant
     *
     * @param id Ant's id
     * @param xCoordinate x coordinate in space
     * @param yCoordinate y coordinate in space
     * @param weights seeds weights
     */
    public Ant(int id, double xCoordinate, double yCoordinate, int[] weights){
        //Setting id
        this.id=id;
        //Setting parent id for Union-Find to itself (because by default each Ant points to itself)
        this.parentIdMST=id;
        //Setting pair id for Gale Shapley to -1 (because Ant has no mate by default)
        this.pairIdGS =-1;
        //Setting pair weight for Gale Shapley to MAΧ (because we need to compare it with new values and find minimal one)
        this.pairWeightGS=Double.MAX_VALUE;
        //Setting X Coordinate
        this.xCoordinate=xCoordinate;
        //Setting Y Coordinate
        this.yCoordinate=yCoordinate;
        //Counting additional coordinate for a Delaunay Triangulation
        this.zCoordinate=xCoordinate*xCoordinate+yCoordinate*yCoordinate;
        //Black Ants don't have target basket
        this.weight=-1;
        //Setting seeds weights to the given ones
        this.weights=new int[5];
        System.arraycopy(weights, 0, this.weights, 0, 5);
    }

    //This method checks if Ants are the same, based on their coordinates
    @Override
    public boolean equals(Object other){
        if (!(other instanceof Ant)) {
            return false;
        }
        Ant otherAnt = (Ant) other;
        return this.xCoordinate == otherAnt.getxCoordinate() && this.yCoordinate == otherAnt.getyCoordinate();
    }

    //This method was used for debugging purposes
    @Override
    public String toString(){
        if (id % 2 != 0)
            return ("id: " + id + ", X: " + xCoordinate + ", Y: " + yCoordinate + ", W: " + weight);
        else
            return ("id: " + id + ", X: " + xCoordinate + ", Y: " + yCoordinate + ", W: " + weights[0] + " " + weights[1] +  " " + weights[2] +  " " + weights[3] +  " " + weights[4]);
    }

    //Standard Getters and Setters for different values
    public int getId() {
        return id;
    }

    public int getParentIdMST() {
        return parentIdMST;
    }

    public void setParentIdMST(int parentIdMST) {
        this.parentIdMST = parentIdMST;
    }

    public double getPairWeightGS() {
        return pairWeightGS;
    }

    public void setPairWeightGS(double pairWeightGS) {
        this.pairWeightGS = pairWeightGS;
    }

    public int getPairIdGS() {
        return pairIdGS;
    }

    public void setPairIdGS(int pairIdGS) {
        this.pairIdGS = pairIdGS;
    }

    public double getxCoordinate() {
        return xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    public double getzCoordinate() {
        return zCoordinate;
    }

    public int getWeight() {
        return weight;
    }

    public int getObjectWeight(int id){
        return weights[id];
    }

    public void setObjectWeight(int id, int value){
        weights[id]=value;
    }
}