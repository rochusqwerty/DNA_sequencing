import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Mutation {
    List<Chromosome> list;
    Chromosome randomchromo;
    List<Integer> list_gens;
    List<Integer> choice = new ArrayList<>();

    Mutation(List<Chromosome> list){
        this.list = list;
        this.randomchromo = new Chromosome();
    }

    List<Chromosome> mutate(){
        boolean changed = true;
        for(int i = 0; i < Main.numberOfMutation; i++){
            randomchromo = list.get(ThreadLocalRandom.current().nextInt(0, list.size()));
            list_gens = new ArrayList<>(randomchromo.getList_gens());
            int steps = list_gens.size()-1;
            int middle1 = steps / 2;
            int tempworst;
            for(int val = 0; val < 9; val++){
                if(!choice.isEmpty()) break;
                for (int q = 0; q < steps; q++) {
                    int index = middle1 + (q % 2 == 0 ? q / 2 : -(q / 2 + 1));
                    if(index >= 0 && index < steps && Main.matrix[list_gens.get(index)][list_gens.get(index + 1)] == val){
                        choice.add(index);
                    }
                }
            }
            int temp;
            for(int j = 0; j < choice.size() && changed; j++){
                temp = choice.get(j);
                for(int k = 0; k < Main.list.size(); k++) {
                    if (Main.matrix[list_gens.get(temp)][list_gens.get(temp + 1)] < Main.matrix[k][list_gens.get(temp + 1)]) {
                        list_gens.set(temp, k);
                        changed = false;
                        break;
                    }
                }
                Chromosome chromo = new Chromosome();
                chromo.setList_gens(list_gens);
                chromo.updateSequence();
                chromo.fix();
                list.add(chromo);
            }


        }

        return list;
    }
}
