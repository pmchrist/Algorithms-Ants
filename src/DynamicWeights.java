import java.util.ArrayList;

public class DynamicWeights{

    ArrayList<Ant> antsOriginal;
    Ant currentAntTarget;
    Ant currentAntWeights;

    public DynamicWeights (ArrayList<Ant> Ants){
        antsOriginal = Ants;
    }

    public void calculateWeights (){

        for (int i=0; i<antsOriginal.size();){
            currentAntTarget=antsOriginal.get(i++);
            currentAntWeights=antsOriginal.get(i++);

            int[] weightSeedValues=new int[currentAntTarget.getWeight()+1];
            int[] weightSeedAmounts=new int[currentAntTarget.getWeight()+1];
            int[] weightSeedIDs=new int[currentAntTarget.getWeight()+1];

            for (int temp=0; temp<currentAntTarget.getWeight()+1; temp++){
                weightSeedAmounts[temp]=Integer.MAX_VALUE;
                weightSeedValues[temp]=Integer.MAX_VALUE;
                weightSeedIDs[temp]=-1;
            }
            weightSeedAmounts[0]=0;
            weightSeedValues[0]=0;

            //For each weight value up to target
            for (int sa=1; sa<currentAntTarget.getWeight()+1; sa++){
                //for each seed weight in pair ant
                for (int sv=0; sv<5; sv++){
                    //if seed weight is lesser then current weight (we go up to target) and can create solution
                    if (currentAntWeights.getObjectWeight(sv) <= sa && weightSeedValues[sa-currentAntWeights.getObjectWeight(sv)]<currentAntTarget.getWeight()+1){
                        //if current weight value is bigger or not existent we save values
                        if (sa-currentAntWeights.getObjectWeight(sv)<1 || weightSeedAmounts[sa]>weightSeedAmounts[sa-currentAntWeights.getObjectWeight(sv)]+1){
                            weightSeedAmounts[sa]=weightSeedAmounts[sa-currentAntWeights.getObjectWeight(sv)]+1;
                            weightSeedValues[sa]=currentAntWeights.getObjectWeight(sv);
                            weightSeedIDs[sa]=sv;
                        }
                    }
                }
            }

            //Trace path back and set values to target
            int tempID=weightSeedValues.length-1;
            do{

                if (weightSeedIDs[tempID]<0){
                    currentAntTarget.setObjectWeight(0, -1);
                    break;
                }

                currentAntTarget.setObjectWeight(weightSeedIDs[tempID], currentAntTarget.getObjectWeight(weightSeedIDs[tempID])+1);
                tempID-=weightSeedValues[tempID];
            }while(tempID!=0);
        }
    }

}
