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
            chromo1 = listIn.get(randomchromo1).getSequence();

            do {
                randomchromo2 = ThreadLocalRandom.current().nextInt(min, max);
            } while (randomchromo2 == randomchromo1);

            chromo2 = listIn.get(randomchromo2).getSequence();

            start = (int) (chromo1.length()*0.2);
            end = chromo1.length() - start;
            randomIndex = ThreadLocalRandom.current().nextInt(start, end);


            chromo1sub = chromo1;
            chromo2sub = chromo2;
            subchromo1 = chromo1sub.substring(0,randomIndex);
            subchromo2 = chromo2sub.substring(randomIndex, chromo2.length());

            lenOfSub1 = subchromo1.length();
            lenOfSub2 = subchromo2.length();

            for (int j = 9; j >= 0; j--) {
                chromo1sub = subchromo1;
                chromo2sub = subchromo2;

                if (chromo1sub.substring(lenOfSub1 - j, lenOfSub1).equals(chromo2sub.substring(0, j))) {
                    res = subchromo1 + subchromo2.substring(j, lenOfSub2);
                    break;
                }
            }
            chromoOut1 = new Chromosome(res);
            listIn.add(chromoOut1);


            for (int j = 9; j >= 0; j--) {
                chromo1sub = subchromo1;
                chromo2sub = subchromo2;
                if (chromo2sub.substring(lenOfSub2 - j, lenOfSub2).equals(chromo1sub.substring(0, j))) {
                    res = chromo2sub + chromo1sub.substring(j, lenOfSub1);
                    break;
                }
            }
            chromoOut2 = new Chromosome(res);
            listIn.add(chromoOut2);
        }

        return listIn;
    }

}
