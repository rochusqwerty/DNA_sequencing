import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Mutation {
    List<Chromosome> list;
    Chromosome randomchromo;
    List<Integer> list_gens, list_out;
    List<Integer> choice = new ArrayList<>();

    Mutation(List<Chromosome> list){
        this.list = list;
        this.randomchromo = new Chromosome();
        this.choice = new ArrayList<>();
    }

    List<Chromosome> mutate(){
        boolean changed = true;
        for(int i = 0; i < Main.numberOfMutation; i++){
            //randomchromo = list.get(ThreadLocalRandom.current().nextInt(0, list.size()));
            randomchromo = list.get(i);
            list_gens = new ArrayList<>(randomchromo.getList_gens());
            choice = new ArrayList<>();
//            System.out.println("MP1");
            for(int val = 0; val < 9; val++){
                if(!choice.isEmpty()) break;
                for (int q = 1; q < list_gens.size()-1; q++) {
                    //int index = middle1 + (q % 2 == 0 ? q / 2 : -(q / 2 + 1));
//                    System.out.println("MP2");
                    if(Main.matrix[list_gens.get(q)][list_gens.get(q + 1)] == val){
                        choice.add(q);
//                        System.out.println("MP3");
                    }
                }
            }
            int temp;
            for(int j = 0; j < choice.size() && changed; j++){
                temp = choice.get(j);
                list_out = new ArrayList<>(list_gens.subList(0, temp));
                Chromosome chromo = new Chromosome();
                chromo.setList_gens(list_out);
//                System.out.println("M1");
                chromo.fix();
//                System.out.println("M2");
                chromo.fitness_function();
//                System.out.println("M3");
                list.add(chromo);
            }


        }

        return list;
    }
}
