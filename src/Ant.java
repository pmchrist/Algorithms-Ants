import java.util.Arrays;

public class Ant {
    private int id;
    private int xCoordinate;
    private int yCoordinate;
    private int zCoordinate;
    private int weight;
    private int[] weights;
    private int parentIdMST;
    private double pairWeightGS;
    private int pairIdGS;

    public Ant(int id, int xCoordinate, int yCoordinate, int weight){
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

    public Ant(int id, int xCoordinate, int yCoordinate, int[] weights){
        this.id=id;
        this.parentIdMST=id;
        this.pairIdGS =-1;
        this.pairWeightGS=Double.MAX_VALUE;
        this.xCoordinate=xCoordinate;
        this.yCoordinate=yCoordinate;
        zCoordinate=xCoordinate*xCoordinate+yCoordinate*yCoordinate;
        this.weight=-1;
        this.weights=new int[5];
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

    public int getPairIdGS() {
        return pairIdGS;
    }

    public void setPairIdGS(int pairIdGS) {
        this.pairIdGS = pairIdGS;
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
        Ant otherAnt = (Ant) other;
        if (this.xCoordinate == otherAnt.getxCoordinate() && this.yCoordinate == otherAnt.getyCoordinate())
            return true;
        else
            return false;
    }

    @Override
    public String toString(){

        return ("W: " + weights[0] + " " + weights[1] +  " " + weights[2] +  " " + weights[3] +  " " + weights[4]);

        /*
        if (id % 2 != 0)
            return ("id: " + id + ", X: " + xCoordinate + ", Y: " + yCoordinate + ", W: " + weight);
        else
            return ("id: " + id + ", X: " + xCoordinate + ", Y: " + yCoordinate + ", W: " + weights[0] + " " + weights[1] +  " " + weights[2] +  " " + weights[3] +  " " + weights[4]);
        */
    }
}