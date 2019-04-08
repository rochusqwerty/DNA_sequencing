import java.util.ArrayList;
import java.util.List;

public class Chromosome {
    List<Integer> list_gens;
    Integer fitness_score;

    public Chromosome() {
        this.list_gens = null;
        this.fitness_score = 0;
    }

    Integer fitness_function(){
        fitness_score = 0;
        for (int i = 1; i < list_gens.size(); i++) {
            fitness_score += Main.matrix[i-1][i];
        }
        return fitness_score;
    }

    public void add(int gen) {
        list_gens.add(gen);
    }
}
