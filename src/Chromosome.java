import java.util.ArrayList;

public class Chromosome {
    ArrayList<Character>  list_gens;
    Integer fitness_score;

    public Chromosome() {
        this.list_gens = new ArrayList<Character>();
        this.fitness_score = 0;
    }

    Integer fitness_function(){

        return 0;
    }
}
