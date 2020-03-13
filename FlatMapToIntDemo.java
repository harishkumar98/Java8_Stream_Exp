import java.util.*;
import java.util.stream.*;
public class FlatMapToIntDemo{
    public static void main (String[] args) {
        int [] arr = {1,2,3,4,5,6};
        Stream<int[]> stream= Stream.of(arr);
        IntStream res = stream.flatMapToInt(x->Arrays.stream(x));
        res.forEach(System.out::println);
    }
}