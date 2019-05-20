import java.util.ArrayList;
import java.util.TreeSet;

public class DelaunayTriangulation{

    public TreeSet getEdges(ArrayList<Ant> Ants){
        TreeSet<GraphEdge> result = new TreeSet<GraphEdge>();

        if(Ants.size() == 2){
            result.add(new GraphEdge(Ants.get(0), Ants.get(1)));
            return result;
        }

        for(int i = 0; i < Ants.size() - 2; i++){
            for(int j = i + 1; j < Ants.size(); j++){
                for (int k = i + 1; k < Ants.size(); k++){
                    if(j == k){
                        continue;
                    }
                    int xn = (Ants.get(j).getyCoordinate() - Ants.get(i).getyCoordinate()) *
                            (Ants.get(k).getzCoordinate() - Ants.get(i).getzCoordinate()) -
                            (Ants.get(k).getyCoordinate() - Ants.get(i).getyCoordinate()) *
                            (Ants.get(j).getzCoordinate() - Ants.get(i).getzCoordinate());


                    int yn = (Ants.get(k).getxCoordinate() - Ants.get(i).getxCoordinate()) *
                            (Ants.get(j).getzCoordinate() - Ants.get(i).getzCoordinate()) -
                            (Ants.get(j).getxCoordinate() - Ants.get(i).getxCoordinate()) *
                            (Ants.get(k).getzCoordinate() - Ants.get(i).getzCoordinate());

                    int zn = (Ants.get(j).getxCoordinate() - Ants.get(i).getxCoordinate()) *
                            (Ants.get(k).getyCoordinate() - Ants.get(i).getyCoordinate()) -
                            (Ants.get(k).getxCoordinate() - Ants.get(i).getxCoordinate()) *
                            (Ants.get(j).getyCoordinate() - Ants.get(i).getyCoordinate());

                    boolean flag;
                    if(flag = (zn < 0 ? 1 : 0) != 0){
                        for(int m = 0; m < Ants.size(); m++){
                            flag = (flag) && ((Ants.get(m).getxCoordinate() - Ants.get(i).getxCoordinate()) * xn +
                                                (Ants.get(m).getyCoordinate() - Ants.get(i).getyCoordinate()) * yn +
                                                (Ants.get(m).getzCoordinate() - Ants.get(i).getzCoordinate()) * zn <= 0);
                        }
                    }

                    if (!flag){
                        continue;
                    }
                    result.add(new GraphEdge(Ants.get(i), Ants.get(j)));
                    result.add(new GraphEdge(Ants.get(j), Ants.get(k)));
                    result.add(new GraphEdge(Ants.get(k), Ants.get(i)));

                }
            }
        }
        return result;
    }

}