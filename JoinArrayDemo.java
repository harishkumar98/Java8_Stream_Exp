import java.util.*;
import java.util.stream.*;
public class JoinArray {
    public static void main(String args[]) {
        String [] ar1 = {"a","b","c"};
        String [] ar2 = {"d","e"};
        // Joining the Object Type Array
        String [] res1 = Stream.of(ar1, ar2).flatMap(Stream::of).toArray(String [] :: new);
        System.out.println(Arrays.toString(res1));
        Arrays.stream(res1).forEach(System.out::println);
        
        //Joining Primitive type Array
        int [] int1 = {1,2,3,4,5};
        int [] int2 = {6,7,8,9};
        int [] res2 = IntStream.concat(Arrays.stream(int1), Arrays.stream(int2)).toArray();
        
        System.out.println(Arrays.toString(res2));
        Arrays.stream(res2).forEach(System.out::print);
        for(int x: res2){
            System.out.println(x);
        }
    }
}