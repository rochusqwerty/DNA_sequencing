import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        if(args.length != 1) {
            System.err.println("Invalid command line, exactly one argument required");
            System.exit(1);
        }

        try {
            FileInputStream fstream = new FileInputStream(args[0]);
            System.out.println(fstream);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //load + matrix

        //gen_alg

    }
}
