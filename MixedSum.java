import java.util.List;

public class MixedSum {

public int sum(List<?> mixed) {
      	try{
            int sum = mixed.stream()
                          .mapToInt(x -> Integer.parseInt(x.toString()))
                          .sum(); 
            return sum;            
        }catch(Exception e){
          return -1;
        }
		
}
}