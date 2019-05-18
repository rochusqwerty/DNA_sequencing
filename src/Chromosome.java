import java.util.ArrayList;
import java.util.List;

public class Chromosome implements Comparable<Chromosome>{
    List<Integer> list_gens;
    Integer fitness_score;
    String genes;

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
    }

    public Integer size(){
        return list_gens.size();
    }

    void fitness_function(){
        fitness_score = 0;
        for (int i = 1; i < list_gens.size(); i++) {
            fitness_score += Main.matrix[list_gens.get(i-1)][list_gens.get(i)];
        }
    }

    public void add(int gen) {
        list_gens.add(gen);
    }

    @Override
    public int compareTo(Chromosome o) {
        return o.fitness_score.compareTo(this.fitness_score);
    }
}
