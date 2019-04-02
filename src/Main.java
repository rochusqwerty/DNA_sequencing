import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Main {

    static List<String> loadFileAsList(String[] args) {
        if (args.length != 1) {
            System.err.println("Invalid command line, exactly one argument required");
            System.exit(1);
        }
        List<String> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    static int[][] createMatrixWeight(List<String> list) {
        int[][] matrix = new int [list.size()][list.size()];

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                String l = list.get(i);
                String l2 = list.get(j);
                if (l.substring(0, 8).equals(l2.substring(1, 9))) {
                    matrix[i][j] = 1;
                } else if (l.substring(0, 7).equals(l2.substring(2, 9))) {
                    matrix[i][j] = 2;
                } else if (l.substring(0, 6).equals(l2.substring(3, 9))) {
                    matrix[i][j] = 3;
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

    public static void main(String[] args) {

        List<String> list = loadFileAsList(args);

        int[][] matrix = createMatrixWeight(list);

        printMatrix(matrix);

        //gen_alg

    }
}
