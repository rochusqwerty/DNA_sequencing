import java.util.List;

public class FirstOrder {
    List<Chromosome> list;
    List<Integer> start = null;

    FirstOrder(){
    }

    List<Chromosome> create(){
        //flagi ustawione na 1 - do odwiedzenia
        for (int i = 0; i < Main.matrix.length; i++)
            start.add(1);

        //przeszukiwanie
        int gen;
        Chromosome chromo;
        for (int i = 0; i < Main.matrix.length; i++) {
            if(start.get(i)!=0){
                chromo = new Chromosome();
                gen = i;
                chromo.add(gen);
                do{
                    gen = takeNext(gen);
                    start.set(gen, 0);
                    if(gen >= 0) chromo.add(gen);
                }while(gen >= 0);
                list.add(chromo);
                chromo = null;
            }
        }
        return list;
    }

    Integer takeNext(Integer current){
        for (int j = 0; j < Main.matrix[0].length; j++) {
            if(Main.matrix[current][j]==1) return j;
        }
        return -1;
    }

}
