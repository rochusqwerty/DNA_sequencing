import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Chromosome implements Comparable<Chromosome>{
    List<Integer> list_gens;
    Integer fitness_score;
    int[] numberOfUses;
    String sequence;

    public List<Integer> getList_gens() {
        return list_gens;
    }
    public String doSequence() {
        for (Integer i : list_gens) {
            while (i<list_gens.size())
                sequence += Main.matrix[i][i+1];
        }
        return sequence;
    }

    public void setList_gens(List<Integer> list_gens) {
        this.list_gens = list_gens;
    }

    public Integer getFitness_score() {
        return fitness_score;
    }

    public Chromosome() {
        this.list_gens = new ArrayList<>();
        this.fitness_score = 0;
        this.numberOfUses = new int[Main.list.size()];
    }

    public Chromosome(String sequence) {
        this.sequence = sequence;
        this.list_gens = new ArrayList<>();
        this.fitness_score = 0;
        this.numberOfUses = new int[Main.list.size()];
    }

    public Integer size(){
        return list_gens.size();
    }

    void fitness_function(){
        for (int i = 0; i < numberOfUses.length; i++) {
            numberOfUses[i] = 1;
        }
        fitness_score = 0;
        sequence = Main.list.get(list_gens.get(0));
        //numberOfUses[list_gens.get(0)] += 1;
        numberOfUses[list_gens.get(0)] =2;
        int el1, el2;
        for (int i = 1; i < list_gens.size(); i++) {
            el1 = list_gens.get(i-1);
            el2 = list_gens.get(i);
            if(numberOfUses[el2] == 1){
                fitness_score += Main.matrix[el1][el2];
                numberOfUses[el2] = 2;
            }
//            fitness_score += (int)Math.ceil(Main.matrix[el1][el2] / ((float)numberOfUses[el2] * 5.0));
//            numberOfUses[el2] += 1;
            sequence += Main.list.get(el2).substring(Main.matrix[el1][el2]);
        }
        //System.out.println("FF: " + sequence.length());
    }

    //Update list_gens, based on sequence
    void calculate_list_gens(){
        for(int i = 0; i < sequence.length() - 9; i++){
            for(int j = 0; j < Main.list.size(); j++){
                if(sequence.substring(i,i+10).equals(Main.list.get(j)))
                    add(j);
            }
        }
    }

    void fix2() {
        while(list_gens.size() > Main.predictedOutput-1) {
            del();
        }
        while(list_gens.size() < Main.predictedOutput-1) {
            List<Integer> neww = Main.listOfSimilarity.get(list_gens.get(list_gens.size()-1));
            if (neww.isEmpty()){
                int randomNum1 = ThreadLocalRandom.current().nextInt(0, Main.matrix.length);
                list_gens.add(randomNum1);
            } else
                list_gens.add(neww.get(0));
        }
    }


    void fix(){
        updateSequence();
        int changed = 0;
        while(sequence.length() > Main.sizeOfSequence){
            del();
            updateSequence();
        }
//        System.out.println("C0");
        while(sequence.length() < Main.sizeOfSequence){
            List<Integer> choice = new ArrayList<>();
            Random randomGenerator = new Random();
            int randomInt = 0;
//            System.out.println("C1");
            for (int i = 9; i>=0 && choice.isEmpty(); i--) {
//                System.out.println("C2a");
                for (int j = 0; j < numberOfUses.length; j++) {
//                    System.out.println("C2b");
                    if(Main.matrix[list_gens.get(list_gens.size()-1)][j]==i && numberOfUses[j]==1){
                        choice.add(j);
//                        System.out.println("C2c");
                    }
                }
                changed = 0;
                if (!choice.isEmpty()){
//                    System.out.println("C3");
                    randomInt = randomGenerator.nextInt(choice.size());
                    add(choice.get(randomInt));
                    updateSequence();
                    changed = 1;
                    break;
                }
            }
            if(changed == 0){
//                System.out.println("C4");
                randomInt = randomGenerator.nextInt(Main.list.size());
                add(randomInt);
                updateSequence();
                changed = 1;
            }
        }
//        System.out.println("C5");
        if(sequence.length() > Main.sizeOfSequence) {
            del();
            updateSequence();
        }
        //System.out.println("F: " + sequence.length());
    }

    void updateSequence(){
        for (int i = 0; i < numberOfUses.length; i++) {
            numberOfUses[i] = 1;
        }
        numberOfUses[list_gens.get(0)] =2;
        sequence = Main.list.get(list_gens.get(0));
        for (int i = 1; i < list_gens.size(); i++) {
            sequence += Main.list.get(list_gens.get(i)).substring(Main.matrix[list_gens.get(i-1)][list_gens.get(i)]);
            numberOfUses[list_gens.get(i)] =2;
        }
        //System.out.println(sequence.length());
    }

    public void add(int gen) {
        list_gens.add(gen);
    }
    public void del() {
        list_gens.remove(list_gens.size()-1);
    }

    @Override
    public int compareTo(Chromosome o) {
        return o.fitness_score.compareTo(this.fitness_score);
    }
}
