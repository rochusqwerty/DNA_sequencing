import java.util.ArrayList;
import java.util.List;

public class Chromosome implements Comparable<Chromosome>{
    List<Integer> list_gens;
    Integer fitness_score;
    int[] numberOfUses;
    String sequence;

    public List<Integer> getList_gens() {
        return list_gens;
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

    public Integer size(){
        return list_gens.size();
    }

    void fitness_function(){
        for (int i = 0; i < numberOfUses.length; i++) {
            numberOfUses[i] = 1;
        }
        fitness_score = 0;
        sequence = Main.list.get(list_gens.get(0));
        numberOfUses[list_gens.get(0)] += 1;
        int el1, el2;
        for (int i = 1; i < list_gens.size(); i++) {
            el1 = list_gens.get(i-1);
            el2 = list_gens.get(i);
            fitness_score += (int)Math.ceil(Main.matrix[el1][el2] / (numberOfUses[el2] * 2.0));
            numberOfUses[el2] += 1;
            sequence += Main.list.get(el1).substring(Main.matrix[el1][el2]);
        }
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
