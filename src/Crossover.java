import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Crossover {
    List<Chromosome> listIn;
    List<Chromosome> listOut = new ArrayList<>();
    int min = 0, max, randomNum, randomNum1, steps, sample = 5, middle=0, middle1;
    float bound = 0.2f;
    List<Integer> chromo1 = new ArrayList<>(), chromo2 = new ArrayList<>(), newList = new ArrayList<>();
    boolean ans;
    Chromosome newChromo;

    Crossover(List<Chromosome> list){
        this.listIn = list;
    }

    List<Chromosome> cross(){
        max = listIn.size();
        bound *= (float) max;
        for(int i = 0; i< bound; i++){
            randomNum = ThreadLocalRandom.current().nextInt(min, max);
            chromo1 = listIn.get(randomNum).getList_gens();
            middle = chromo1.size() / 2;
            while(sample > 0){
                sample--;
                randomNum1 = ThreadLocalRandom.current().nextInt(min, max);
                while(randomNum == randomNum1){
                    randomNum1 = ThreadLocalRandom.current().nextInt(min, max);
                }
                chromo2 = listIn.get(randomNum1).getList_gens();

                steps = (int) (chromo2.size()-1 * 0.5);
                middle1 = chromo2.size() / 2;
                if((chromo2.size() > 5) && (chromo1.size() > 5)) {
                    for (int q = 0; q < steps; q++) {
                        System.out.println("Dane");
                        System.out.println(chromo1.size());
                        System.out.println(middle);
                        System.out.println(chromo2.size());
                        System.out.println(middle1);
                        int index = middle1 + (q % 2 == 0 ? q / 2 : -(q / 2 + 1));

                        if (chromo1.get(middle).equals(chromo2.get(index))) {
                            sample = 0;

                            System.out.println("Ch1: " + chromo1.size());
                            newChromo = new Chromosome();
                            newList.clear();
                            newList = chromo1.subList(0, middle);
                            //newList.addAll(chromo2.subList(index, chromo2.size()));
                            if (!newList.isEmpty()) {
                                System.out.println("Ch1: " + chromo1.size());
                                newChromo.list_gens = newList;
                                //listOut.add(newChromo);
                            }
                            System.out.println("Ch1: " + chromo1.size());

                            newChromo = new Chromosome();
                            newList.clear();
                            newList = chromo2.subList(0, index);
                            newList.addAll(chromo1.subList(middle, chromo1.size()));
                            if (!newList.isEmpty()) {
                                System.out.println("Ch2: " + chromo1.size());
                                newChromo.list_gens = newList;
                                listOut.add(newChromo);
                            }
                        }
                    }
                }
            }
        }

        listOut.addAll(listIn);
        return listOut;
    }

}
