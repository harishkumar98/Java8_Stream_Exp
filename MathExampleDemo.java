import java.util.*;
import java.util.stream.*;
public class MathExampleDemo {
    public static void main(String args[]) {
            // java 8 - check if an array contains certain value
            String [] alph = new String[]{"A","B","C","D"};
            boolean b = Arrays.stream(alph).anyMatch("A"::equals);
            if(b)
                System.out.println("A is avalable");
            else
                System.out.println("A is not avalable");

    }
}