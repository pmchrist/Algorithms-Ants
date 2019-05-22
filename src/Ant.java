public class Ant {
    private int id;
    private int xCoordinate;
    private int yCoordinate;
    private int zCoordinate;
    private int weight;
    private int[] weights;
    private int parentIdMST;
    private double pairWeightGS;
    private double pairWeightIdGS;

    public Ant(int id, int xCoordinate, int yCoordinate, int weight){
        this.id=id;
        this.parentIdMST=id;
        this.pairWeightIdGS=-1;
        this.pairWeightGS=Double.MAX_VALUE;
        this.xCoordinate=xCoordinate;
        this.yCoordinate=yCoordinate;
        zCoordinate=xCoordinate*xCoordinate+yCoordinate*yCoordinate;
        this.weight=weight;
    }

    public Ant(int id, int xCoordinate, int yCoordinate, int[] weights){
        this.id=id;
        this.parentIdMST=id;
        this.pairWeightIdGS=-1;
        this.pairWeightGS=Double.MAX_VALUE;
        this.xCoordinate=xCoordinate;
        this.yCoordinate=yCoordinate;
        zCoordinate=xCoordinate*xCoordinate+yCoordinate*yCoordinate;
        this.weights = new int[5];
        for (int i=0; i<5; i++){
            this.weights[i]=weights[i];
        }
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

    public double getPairWeightIdGS() {
        return pairWeightIdGS;
    }

    public void setPairWeightIdGS(double pairWeightIdGS) {
        this.pairWeightIdGS = pairWeightIdGS;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public int getzCoordinate() {
        return zCoordinate;
    }

    @Override
    public boolean equals(Object other){
        Ant otherAnt = (Ant) other;
        if (this.xCoordinate == otherAnt.getxCoordinate() && this.yCoordinate == otherAnt.getyCoordinate())
            return true;
        else
            return false;
    }

    @Override
    public String toString(){
        if (id % 2 != 0)
            return ("id: " + id + ", X: " + xCoordinate + ", Y: " + yCoordinate + ", W: " + weight);
        else
            return ("id: " + id + ", X: " + xCoordinate + ", Y: " + yCoordinate + ", W: " + weights[0] + " " + weights[1] +  " " + weights[2] +  " " + weights[3] +  " " + weights[4]);
    }
}