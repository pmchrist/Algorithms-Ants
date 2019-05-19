public class Ant {
    private int id;
    private int xCoordinate;
    private int yCoordinate;
    private int weight;
    private int[] weights;

    public Ant(int id, int xCoordinate, int yCoordinate, int weight){
        this.id=id;
        this.xCoordinate=xCoordinate;
        this.yCoordinate=yCoordinate;
        this.weight=weight;
    }

    public Ant(int id, int xCoordinate, int yCoordinate, int[] weights){
        this.id=id;
        this.xCoordinate=xCoordinate;
        this.yCoordinate=yCoordinate;
        this.weights = new int[5];
        for (int i=0; i<5; i++){
            this.weights[i]=weights[i];
        }
    }
}