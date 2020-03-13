import java.util.*;
import java.util.stream.*;

public class FlatMapDemo{
	public static void main(String s[]){
		String [][] data = new String[][]{{"a","b"}, {"c","d"}};
		Stream<String[]> temp = Arrays.stream(data);
		Stream<String> d = temp.flatMap(x->Arrays.stream(x));
		//d.forEach(System.out::println);
		
		// filter operation
		Stream<String> res = d.filter(x-> "a".equals(x.toString()));
		res.forEach(System.out::println);
	}
}