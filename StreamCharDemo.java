import java.util.*;
import java.util.stream.*;
public class StreamCharDemo {
    public static void main(String args[]) {
        String s = "harish kumar";
        
        char [] ch = s.toCharArray();
        System.out.println(ch);
        for(char c : ch){
            System.out.println(c);
        }
        
        // Using StreamChar
        s.chars().mapToObj(x->(char)x).forEach(System.out::print);
    }
}