import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Crossover {
    private List<Chromosome> listIn;


    Crossover(List<Chromosome> list){
        this.listIn = list;
    }

    List<Chromosome> cross(){

        List<Chromosome> listOut = new ArrayList<>();

        int min = 0, max, middle1, middle2,steps, sample, index;
        float bound = 0.2f;
        int randomNum1, randomNum2;
        List<Integer> chromo1, chromo2, newList1, newList2;
        Chromosome newChromo;

        max = listIn.size();
        bound *= (float) max;

        for(int i = 0; i < bound; i++){
            randomNum1 = ThreadLocalRandom.current().nextInt(min, max);
            chromo1 = new ArrayList<>(listIn.get(randomNum1).getList_gens());

            middle1 = chromo1.size() / 2;
            sample = 5;

            while(sample > 0) {
                sample --;
                do {
                    randomNum2 = ThreadLocalRandom.current().nextInt(min, max);
                } while (randomNum2 == randomNum1);

                chromo2 = new ArrayList<>(listIn.get(randomNum2).getList_gens());

                steps = chromo2.size();
                middle2 = chromo2.size() / 2;

                if((chromo2.size() > 2) && (chromo1.size() > 2)) {
                    for (int q = 0; q < steps; q++) {
                        index = middle2 + (q % 2 == 0 ? q / 2 : -(q / 2 + 1));

                        if (chromo1.get(middle1).equals(chromo2.get(index))) {
                            sample = 0;
                            newList1 = new ArrayList<>(chromo1);
                            newList1 = newList1.subList(0, middle1);
                            newList2 = new ArrayList<>(chromo2);
                            newList2 = newList2.subList(index, chromo2.size());
                            newList1.addAll(newList2);
                            if (!newList1.isEmpty()) {
                                newChromo = new Chromosome();
                                newChromo.setList_gens(newList1);
                                newChromo.fix2();
                                listOut.add(newChromo);
                            }


                            newList2 = new ArrayList<>(chromo2);
                            newList2 = newList2.subList(0, index);
                            newList1 = new ArrayList<>(chromo1);
                            newList1 = newList1.subList(middle1, chromo1.size());
                            newList2.addAll(newList1);
                            if (!newList2.isEmpty()) {
                                newChromo = new Chromosome();
                                newChromo.setList_gens(newList2);
                                newChromo.fix2();
                                listOut.add(newChromo);
                            }
                        }
                    }
                }
            }

        }
//        for (Chromosome chro: listIn) {
//            System.out.println("Chro1: " + chro.getList_gens().size());
//        }
//        for (Chromosome chro: listOut) {
//            System.out.println("Chro2: " + chro.getList_gens().size());
//        }
//        listOut.addAll(listIn);
//        for (Chromosome chro: listOut) {
//            System.out.println("Chro join: " + chro.getList_gens().size());
//        }
        listIn.addAll(listOut);
        return listIn;
    }

}
