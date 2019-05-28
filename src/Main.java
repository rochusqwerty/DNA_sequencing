import java.io.*;
import java.util.*;


public class Main {
    public static int[][] matrix;
    public static int sizeOfSequence, sizeOfPopulation;
    public static List<String> list = new ArrayList<>();
    public static List<List<Integer>> listOfSimilarity = new ArrayList<>();
    public static int numberOfMutation = 200;
    public static int predictedOutput;


    static void loadDataFromFile(String args) {
        try (BufferedReader br = new BufferedReader(new FileReader(args))) {
            String line;
            list = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static List<List<String>> loadFilesAsList(String[] args) {
        List<String> listOfDirectories;
        List<List<String>> listOfFiles_good = new ArrayList<>();
        listOfDirectories = Arrays.asList("b_negatyw_powt","b_pozyt_kon", "b_negatyw_los", "b_pozyt_los");
        for (String dir: listOfDirectories) {
            String pathname = "ins/" + dir;
            File folder = new File(pathname);
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    List<String> path = new ArrayList<>();
                    path.add(pathname);
                    path.add(listOfFiles[i].getName());
                    listOfFiles_good.add(path);
                }
            }
        }
        return listOfFiles_good;
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

    static void writeToFile( List<Chromosome> p, int predictedOutput, String file, File outputFile) {
        try {
            FileWriter fr = new FileWriter(outputFile, true);
            fr.write(file + "\n");
            float percent = ((float)counter(p.get(1).list_gens)/predictedOutput)*100;
            System.out.println(percent);
            fr.write(percent + "\n");
            fr.close();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    static void summary( List<Chromosome> p, int predictedOutput) {
        System.out.println("Size of population: " + p.size());
        System.out.println("Size of list: " + Main.list.size());
        System.out.println("First: " + p.get(0).list_gens.size());
        float percent = ((float)counter(p.get(0).list_gens)/predictedOutput)*100;
        System.out.println("percent:" + percent);
//        for (Integer gen : p.get(0).list_gens) {
//            System.out.println(gen.toString() + ": " + list.get(gen));
//        }
    }

    static int counter( List<Integer> gens) {
        int[] numberOfUses = new int[Main.list.size()];
        int counter = 0;
        for (int i = 0; i < numberOfUses.length; i++) {
            numberOfUses[i] = 0;
        }
        for (Integer gen : gens) {
            numberOfUses[gen]++;
        }
        for (int i = 0; i < numberOfUses.length; i++) {
            if(numberOfUses[i] > 0) counter++;
        }
        return counter;
    }

    public static void main(String[] args) {
        List<List<String>> listOfFiles = loadFilesAsList(args);
        File outputFile = new File("results.txt");
        for (List<String> file : listOfFiles) {
            String[] output = file.get(1).split("\\.");
            output = output[0].split("\\_");
            int indexOfInst = Integer.parseInt(output[0]);
            char sign;
            if (output[1].indexOf('+') > 0) {
                sign = '+';
            } else {
                sign = '-';
            }
            output = output[1].split("[-\\+]");
            int spectrum = Integer.parseInt(output[0]);
            int err = Integer.parseInt(output[1]);
            sizeOfPopulation = spectrum * 4;
            numberOfMutation = spectrum / 2;
            String allNameFile = file.get(0) + "/" + file.get(1);
            loadDataFromFile(allNameFile);
            System.out.println(allNameFile);
            sizeOfSequence = spectrum + 9;
            if (sign == '+') {
                predictedOutput = sizeOfSequence - 9;
            } else {
                predictedOutput = sizeOfSequence - err - 9;
            }
            List<Chromosome> population;
            matrix = createMatrixWeight(list);
            createListOfSimilarity(matrix);


            FirstOrder first = new FirstOrder();
            population = first.create2();
            for (Chromosome chromo: population) {
                chromo.fitness_function();
            }
            Collections.sort(population);
            summary(population, predictedOutput);
            for (int i=0; i<5; i++){
                Crossover cross = new Crossover(population);
                population = cross.cross();
                Mutation mut = new Mutation(population);
                population = mut.mutate();
                Collections.sort(population);
                if(population.size() > sizeOfPopulation/8)
                    population = population.subList(0, sizeOfPopulation/8);
                System.out.println(population.size());
            }
            summary(population, predictedOutput);
            System.out.println("\n\n");
            writeToFile(population, predictedOutput, allNameFile, outputFile);
        }
    }


}
