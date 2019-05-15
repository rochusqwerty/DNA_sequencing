import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Crossover {
    List<Chromosome> listIn;
    List<Chromosome> listOut = new ArrayList<>();
    int min = 0, max, randomNum, randomNum1, steps, sample = 5, middle=0, middle1, index;
    float bound = 0.2f;
    List<Integer> chromo1 = new ArrayList<>(), chromo2 = new ArrayList<>(), newList = new ArrayList<>(), newList2 = new ArrayList<>();
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
            chromo1.clear();
            chromo2.clear();
            chromo1 = listIn.get(randomNum).getList_gens().stream().collect(Collectors.toList());
            middle = chromo1.size() / 2;
            while(sample > 0){
                sample--;
                randomNum1 = ThreadLocalRandom.current().nextInt(min, max);
                while(randomNum == randomNum1){
                    randomNum1 = ThreadLocalRandom.current().nextInt(min, max);
                }
                chromo2 = listIn.get(randomNum1).getList_gens().stream().collect(Collectors.toList());

                steps = (int) (chromo2.size()-1);
                middle1 = chromo2.size() / 2;
                if((chromo2.size() > 2) && (chromo1.size() > 2)) {
                    for (int q = 0; q < steps; q++) {
                        System.out.println("Dane");
                        System.out.println(chromo1.size());
                        System.out.println(middle);
                        System.out.println(chromo2.size());
                        System.out.println(middle1);
                        index = middle1 + (q % 2 == 0 ? q / 2 : -(q / 2 + 1));

                        if (chromo1.get(middle).equals(chromo2.get(index))) {
                            sample = 0;

                            newChromo = new Chromosome();
                            newList.clear();
                            newList = chromo1.stream().collect(Collectors.toList());
                            newList = newList.subList(0, middle);
                            newList2 = chromo2.stream().collect(Collectors.toList());
                            newList2 = newList2.subList(index, chromo2.size());
                            newList.addAll(newList2);
                            if (!newList.isEmpty()) {
                                newChromo.setList_gens(newList2);
                                listOut.add(newChromo);
                            }

                            newChromo = new Chromosome();
                            newList.clear();
                            newList = chromo2.stream().collect(Collectors.toList());
                            newList = newList.subList(0, index);
                            newList2 = chromo1.stream().collect(Collectors.toList());
                            newList2 = newList2.subList(middle, chromo1.size());
                            newList.addAll(newList2);
                            if (!newList.isEmpty()) {
                                newChromo.setList_gens(newList2);
                                listOut.add(newChromo);
                            }
                        }
                    }
                }
            }
        }

        for (Chromosome chro: listIn) {
            System.out.println("Chro1: " + chro.getList_gens().size());
        }
        for (Chromosome chro: listOut) {
            System.out.println("Chro2: " + chro.getList_gens().size());
        }
        listOut.addAll(listIn);
        for (Chromosome chro: listOut) {
            System.out.println("Chro join: " + chro.getList_gens().size());
        }
        return listOut;
    }

}
