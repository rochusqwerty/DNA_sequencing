import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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

    public void addToListOut(List<Integer> chromo1, List<Integer> chromo2) {
        newChromo = new Chromosome();
        newList.clear();
        newList = new ArrayList<>(chromo1);
        newList = newList.subList(0, middle);
        newList2 = new ArrayList<>(chromo2);
        newList2 = newList2.subList(index, chromo2.size());
        newList.addAll(newList2);
        if (!newList.isEmpty()) {
            newChromo.setList_gens(newList2);
            listOut.add(newChromo);
        }
    }

    List<Chromosome> cross(){
        max = listIn.size();
        bound *= (float) max;
        for(int i = 0; i< bound; i++){
            //wybór pierwszego
            randomNum = ThreadLocalRandom.current().nextInt(min, max);
            chromo1 = new ArrayList<>(listIn.get(randomNum).getList_gens());
            //wybór środkowego elementu
            middle = chromo1.size() / 2;
            sample = 5; //TODO def parametrów w main
            while(sample > 0){
                sample--;
                //wybór drugiego`
                randomNum1 = ThreadLocalRandom.current().nextInt(min, max);
                while(randomNum == randomNum1){
                    randomNum1 = ThreadLocalRandom.current().nextInt(min, max);
                }
                chromo2 = new ArrayList<>(listIn.get(randomNum1).getList_gens());

                steps = (int) (chromo2.size()-1 * 0.5);
                middle1 = chromo2.size() / 2;
                if((chromo2.size() > 5) && (chromo1.size() > 5)) {
                    for (int q = 0; q < steps; q++) {
                        index = middle1 + (q % 2 == 0 ? q / 2 : -(q / 2 + 1));

                        if (chromo1.get(middle).equals(chromo2.get(index))) {
                            sample = 0;

                            addToListOut(chromo1, chromo2);
                            addToListOut(chromo2, chromo1);
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
