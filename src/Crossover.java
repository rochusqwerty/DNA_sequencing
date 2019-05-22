import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Crossover {
    List<Chromosome> listIn;
    List<Chromosome> listOut = new ArrayList<>();
    int min = 0, max;
    float bound = 0.2f;
    int start, end, randomIndex, randomchromo1, randomchromo2, lenOfSub1, lenOfSub2;
    String chromo1, chromo2, subchromo1, subchromo2, res;
    Chromosome chromoOut1, chromoOut2;
    String chromo1sub, chromo2sub;

    Crossover(List<Chromosome> list){
        this.listIn = list;
    }

    List<Chromosome> cross(){
        max = listIn.size();
        bound *= (float) max;
        for(int i = 0; i < bound; i++){
            randomchromo1 = ThreadLocalRandom.current().nextInt(min, max);
            chromo1 = new String(listIn.get(randomchromo1).sequence);

            do {
                randomchromo2 = ThreadLocalRandom.current().nextInt(min, max);
            } while (randomchromo2 == randomchromo1);

            chromo2 = new String(listIn.get(randomchromo2).sequence);

            start = (int) (chromo1.length()*0.2);
            end = chromo1.length() - start;
            randomIndex = ThreadLocalRandom.current().nextInt(start, end);

            chromo1sub = chromo1.substring(0,randomIndex);
            chromo2sub = chromo2.substring(randomIndex);

            lenOfSub1 = chromo1sub.length();
            lenOfSub2 = chromo2sub.length();

            for (int j = 9; j >= 0; j--) {
                if (chromo1sub.substring(lenOfSub1 - j).equals(chromo2sub.substring(0, j))) {
                    res = chromo1sub + chromo2sub.substring(j);
                    break;
                }
            }
            chromoOut1 = new Chromosome(res);
            chromoOut1.calculate_list_gens();
            chromoOut1.fix();
            listIn.add(chromoOut1);

            for (int j = 9; j >= 0; j--) {
                if (chromo2sub.substring(lenOfSub2 - j).equals(chromo1sub.substring(0, j))) {
                    res = chromo2sub + chromo1sub.substring(j);
                    break;
                }
            }
            chromoOut2 = new Chromosome(res);
            chromoOut2.calculate_list_gens();
            chromoOut2.fix();
            listIn.add(chromoOut2);
//            System.out.println("S1: " + listIn.get(randomchromo1).sequence.length() + ", S2: " + listIn.get(randomchromo2).sequence.length());
//            System.out.println("N1: " + chromoOut1.sequence.length() + ", N2: " + chromoOut2.sequence.length() + "\n");
        }

        return listIn;
    }

}
