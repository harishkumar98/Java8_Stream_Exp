import java.util.*;
import java.util.stream.*;
public class ConvertMapToList{
    public static void main (String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(10, "apple");
        map.put(20, "banana");
        map.put(30, "watermelon");
        map.put(40, "papaya");
        map.put(50, "plum");
        
        List<Integer> k = new ArrayList<>();
        List<String> v = map.entrySet().stream()
                            .sorted(Map.Entry.<Integer, String>comparingByKey())
                            .peek(x->k.add(x.getKey()))
                            .map(x->x.getValue())
                            .collect(Collectors.toList());
        k.forEach(System.out::println);
        v.forEach(System.out::println);

    }
}