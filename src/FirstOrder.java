import java.util.ArrayList;
import java.util.List;

public class FirstOrder {
    List<Chromosome> list = new ArrayList<>();
    List<Integer> start = new ArrayList<>();

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
                    list.add(chromo);
                }

            }
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

}
