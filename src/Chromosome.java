import java.util.ArrayList;
import java.util.List;

public class Chromosome implements Comparable<Chromosome>{
    List<Integer> list_gens;
    Integer fitness_score;

    public List<Integer> getList_gens() {
        return list_gens;
    }

    public Integer getFitness_score() {
        return fitness_score;
    }

    public Chromosome() {
        this.list_gens = new ArrayList<>();
        this.fitness_score = 0;
    }

    void fitness_function(){
        fitness_score = 0;
        for (int i = 1; i < list_gens.size(); i++) {
            fitness_score += Main.matrix[i-1][i];
        }
    }

    public void add(int gen) {
        list_gens.add(gen);
    }

    @Override
    public int compareTo(Chromosome o) {
        return this.fitness_score.compareTo(o.fitness_score);
    }
}
