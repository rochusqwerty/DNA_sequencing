import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FirstOrder {
    List<Chromosome> list = new ArrayList<>();
    List<Integer> start = new ArrayList<>();
    int size = 0;
    int[] numberOfUses = new int[Main.list.size()];


    FirstOrder(){
    }

    List<Chromosome> create(){
        //flagi ustawione na 1 - do odwiedzenia
        for (int i = 0; i < Main.matrix[0].length; i++)
            start.add(1);
        //przeszukiwanie
        int gen;
        Chromosome chromo;
        for (int i = 0; i < Main.matrix[0].length; i++) {
            if(start.get(i)!=0){
                chromo = new Chromosome();
                gen = i;
                start.set(gen, 0);
                chromo.add(gen);
                gen = takeNext(gen);
                while(gen > 0){
                    start.set(gen, 0);
                    chromo.add(gen);
                    gen = takeNext(gen);
                }
                if(chromo.list_gens.size()>0) {
                    chromo.fitness_function();
                    list.add(chromo);
                }
            }
        }
        return list;
    }

    List<Chromosome> create2(){
        //flagi ustawione na 1 - do odwiedzenia
        for (int i = 0; i < Main.matrix[0].length; i++)
            start.add(1);
        //przeszukiwanie
        int gen;
        Chromosome chromo;
        for(int k = 0; k < Main.sizeOfPopulation; k++) {
            for (int i = 0; i < numberOfUses.length; i++) {
                numberOfUses[i] = 0;
            }
            chromo = new Chromosome();
            size = 10;
            for (int i = 0; i < Main.matrix[0].length && size < Main.sizeOfSequence; i++) {
                if (start.get(i) != 0) {
                    gen = i;
                    start.set(gen, 0);
                    chromo.add(gen);
                    numberOfUses[gen]=1;
                    while (size < Main.sizeOfSequence) {
                        gen = takeNext2(gen);
                        chromo.add(gen);
                        numberOfUses[gen]=1;
                    }
                }
            }
            Random randomGenerator = new Random();
            while (size < Main.sizeOfSequence) {
                gen = randomGenerator.nextInt(Main.matrix[0].length);
                chromo.add(gen);
                numberOfUses[gen]=1;
                gen = takeNext2(gen);
                while (size < Main.sizeOfSequence) {
                    chromo.add(gen);
                    gen = takeNext2(gen);
                    numberOfUses[gen]=1;
                }
            }
            if(size > Main.sizeOfSequence){
                chromo.del();
            }
            chromo.fitness_function();
            list.add(chromo);
            size = 10;
        }
        return list;
    }

    Integer takeNext(Integer current){
        for (int j = 0; j < Main.matrix[0].length; j++) {
            if(Main.matrix[current][j]==9) return j;
        }
        for (int j = 0; j < Main.matrix[0].length; j++) {
            if(Main.matrix[current][j]==8) return j;
        }
        return -1;
    }

    Integer takeNext2(Integer current){
        List<Integer> choice = new ArrayList<>();
        Random randomGenerator = new Random();
        int randomInt = 0;
        for (int i = 9; i > 0; i--) {
            for (int j = 0; j < Main.matrix[0].length; j++) {
                if(Main.matrix[current][j]==i && numberOfUses[j]==0) choice.add(j);
            }
            if (!choice.isEmpty()){
                randomInt = randomGenerator.nextInt(choice.size());
                size += 10-i;
                return choice.get(randomInt);
            }
        }
        size += 10;
        return randomGenerator.nextInt(Main.matrix[0].length);
    }

}
