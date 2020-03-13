import java.util.*;
import java.util.stream.*;
public class AreSameSquaredArray {
	
	public static boolean comp(int[] a, int[] b) {
  
  return  (a != null && b != null) && (a.length == b.length ) && Arrays.equals(Arrays.stream(a).map(i -> i*i).sorted().toArray(),  Arrays.stream(b).map(i-> i).sorted().toArray());
  }
}