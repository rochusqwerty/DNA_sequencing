import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {
    public static int[][] matrix;
    public static int sizeOfSequence = 209, sizeOfPopulation = 1000;
    public static List<String> list = new ArrayList<>();
    public static List<List<Integer>> listOfSimilarity = new ArrayList<>();


    static void loadFileAsList(String[] args) {
        if (args.length != 1) {
            System.err.println("Invalid command line, exactly one argument required");
            System.exit(1);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int[][] createMatrixWeight(List<String> list) {
        int[][] matrix = new int [list.size()][list.size()];

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                String l = list.get(j);
                String l2 = list.get(i);
                if (l.substring(0, 9).equals(l2.substring(1))) {
                    matrix[i][j] = 9;
                } else if (l.substring(0, 8).equals(l2.substring(2))) {
                    matrix[i][j] = 8;
                } else if (l.substring(0, 7).equals(l2.substring(3))) {
                    matrix[i][j] = 7;
                } else if (l.substring(0, 6).equals(l2.substring(4))) {
                    matrix[i][j] = 6;
                } else if (l.substring(0, 5).equals(l2.substring(5))) {
                    matrix[i][j] = 5;
                } else if (l.substring(0, 4).equals(l2.substring(6))) {
                    matrix[i][j] = 4;
                } else if (l.substring(0, 3).equals(l2.substring(7))) {
                    matrix[i][j] = 3;
                } else if (l.substring(0, 2).equals(l2.substring(8))) {
                    matrix[i][j] = 2;
                } else if (l.substring(0, 1).equals(l2.substring(9))) {
                    matrix[i][j] = 1;
                }
            }
        }
        return matrix;
    }

    static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    static List<List<Integer>>  createListOfSimilarity(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            List<Integer> insideList = new ArrayList<>();
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 9) {
                    insideList.add(j);
                }
            }
            listOfSimilarity.add(insideList);
        }
        return listOfSimilarity;
    }

    static void bestChromo( List<Chromosome> p){
        System.out.print( "The best:");
        for (Integer gen: p.get(0).list_gens) {
            System.out.print(gen.toString() + ": " + list.get(gen));
        }
    }

    static void summary( List<Chromosome> p) {
        System.out.println("Size of population: " + p.size());
        System.out.println("First: " + p.get(0).list_gens.size());
        for (Integer gen : p.get(0).list_gens) {
            System.out.println(gen.toString() + ": " + list.get(gen));
        }
        System.out.println("\n\nSecond: " + p.get(1).list_gens.size());
        for (Integer gen : p.get(1).list_gens) {
            System.out.println(gen.toString() + ": " + list.get(gen));
        }
    }

    public static void main(String[] args) {
        loadFileAsList(args);
        List<Chromosome> population;

        matrix = createMatrixWeight(list);

        createListOfSimilarity(matrix);
        FirstOrder first = new FirstOrder();
        population = first.create2();
        for (int i=0; i<1; i++){  //do zmiany 100, warunek zakończenia
            Mutation mut = new Mutation();
            Crossover cross = new Crossover(population);
            //population = cross.cross();
            //population = mut.mutate(population);      //TO DO
            for (Chromosome chromo: population) {
                chromo.fitness_function();
            }
            Collections.sort(population);
            //if(population.size() > 100)
                //population.subList(0, 100).clear();
        }
        summary(population);
    }


}
