/*
  Christos Perchanidis
  AEM: 3194
  pmchrist@csd.auth.gr
  Aristotle University of Thessaloniki
  May 2019
 */

import java.util.ArrayList;

/**
 *
 * Class that finds if it is possible to fill the basket of a Red Ant with Black Ant's seeds
 * aka Change-Making problem
 *
 */
class DynamicWeights{

    //Setting pointer to original Ants
    private final ArrayList<Ant> antsOriginal;

    //Constructor
    public DynamicWeights (ArrayList<Ant> Ants){
        antsOriginal = Ants;
    }

    /**
     *
     * This method finds how to fill basket of a Red Ant with seeds of Black Ant
     * if it is not possible, sets first seed number of a Red Ant to -1
     *
     */
    public void calculateWeights (){

        //For each Ant
        for (int i=0; i<antsOriginal.size();){
            //Because Ants pairs go just in a row, Ant-Target (Red with basket) is a first one and Ant-Weights (Black with seeds) is a second one.
            Ant currentAntTarget = antsOriginal.get(i++);
            Ant currentAntWeights = antsOriginal.get(i++);

            //Creating arrays for dynamic programming memoization
            //Here we keep how many seeds has been used until now (used for comparing optimal amount of seeds used)
            int[] weightSeedValues=new int[currentAntTarget.getWeight()+1];
            //Here we keep which seed was used lastly (used in backtracking the way backwards from target basket value)
            int[] weightSeedAmounts=new int[currentAntTarget.getWeight()+1];
            //Here we keep seed's id in array with seeds of Black Ant that was used lastly (used for backtracking for value saving in Red Ant array of used seeds)
            int[] weightSeedIDs=new int[currentAntTarget.getWeight()+1];

            //Setting stating array values
            for (int temp = 0; temp< currentAntTarget.getWeight()+1; temp++){
                weightSeedAmounts[temp]=Integer.MAX_VALUE;
                weightSeedValues[temp]=Integer.MAX_VALUE;
                weightSeedIDs[temp]=-1;
            }
            weightSeedAmounts[0]=0;
            weightSeedValues[0]=0;

            //For each weight value up to target (basket size)
            for (int sa = 1; sa< currentAntTarget.getWeight()+1; sa++){
                //for each seed weight in pair ant (black one)
                for (int sv=0; sv<5; sv++){
                    //if seed weight is lesser then current weight (we climb up to target) and can create solution (have a correct value for backtracking)
                    if (currentAntWeights.getObjectWeight(sv) <= sa && weightSeedValues[sa- currentAntWeights.getObjectWeight(sv)]< currentAntTarget.getWeight()+1){
                        //if seeds amount currently needed is more then seeds amount that we will have with use of this seed, update value
                        if (weightSeedAmounts[sa]>weightSeedAmounts[sa-currentAntWeights.getObjectWeight(sv)]+1){
                            //Saving used seeds amount
                            weightSeedAmounts[sa]=weightSeedAmounts[sa-currentAntWeights.getObjectWeight(sv)]+1;
                            //Saving weight of a better seed
                            weightSeedValues[sa]= currentAntWeights.getObjectWeight(sv);
                            //Saving used seed ID
                            weightSeedIDs[sa]=sv;
                        }
                    }
                }
            }

            //Trace path back and set values to target Ant's array
            int tempID=weightSeedValues.length-1;
            do{
                //if value is incorrect, that means there is no way to fill basket
                if (weightSeedIDs[tempID]<0){
                    currentAntTarget.setObjectWeight(0, -1);
                    break;
                }
                //Save Value
                currentAntTarget.setObjectWeight(weightSeedIDs[tempID], currentAntTarget.getObjectWeight(weightSeedIDs[tempID])+1);
                tempID-=weightSeedValues[tempID];
            }while(tempID!=0);
        }
    }
}
