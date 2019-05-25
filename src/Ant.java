
public class Ant {
    private final int id;
    private final double xCoordinate;
    private final double yCoordinate;
    private final double zCoordinate;
    private final int weight;
    private final int[] weights;
    private int parentIdMST;
    private double pairWeightGS;
    private int pairIdGS;

    public Ant(int id, double xCoordinate, double yCoordinate, int weight){
        this.id=id;
        this.parentIdMST=id;
        this.pairIdGS =-1;
        this.pairWeightGS=Double.MAX_VALUE;
        this.xCoordinate=xCoordinate;
        this.yCoordinate=yCoordinate;
        zCoordinate=xCoordinate*xCoordinate+yCoordinate*yCoordinate;
        this.weight=weight;
        this.weights=new int[5];
        for (int i=0; i<5; i++){
            this.weights[i]=0;
        }
    }

    public Ant(int id, double xCoordinate, double yCoordinate, int[] weights){
        this.id=id;
        this.parentIdMST=id;
        this.pairIdGS =-1;
        this.pairWeightGS=Double.MAX_VALUE;
        this.xCoordinate=xCoordinate;
        this.yCoordinate=yCoordinate;
        zCoordinate=xCoordinate*xCoordinate+yCoordinate*yCoordinate;
        this.weight=-1;
        this.weights=new int[5];
        System.arraycopy(weights, 0, this.weights, 0, 5);
    }

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

    @Override
    public boolean equals(Object other){
        if (!(other instanceof Ant)) {
            return false;
        }
        Ant otherAnt = (Ant) other;
        return this.xCoordinate == otherAnt.getxCoordinate() && this.yCoordinate == otherAnt.getyCoordinate();
    }

    @Override
    public String toString(){
        if (id % 2 != 0)
            return ("id: " + id + ", X: " + xCoordinate + ", Y: " + yCoordinate + ", W: " + weight);
        else
            return ("id: " + id + ", X: " + xCoordinate + ", Y: " + yCoordinate + ", W: " + weights[0] + " " + weights[1] +  " " + weights[2] +  " " + weights[3] +  " " + weights[4]);
    }
}