import java.util.*;
import java.util.stream.*;

class Hosting{
    int id;
    String name;
    int webs;
    
    Hosting(int id, String name, int webs){
        this.id = id;
        this.name = name;
        this.webs = webs;
    }
    
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getWebs(){
        return webs;
    }
}
public class ConvertListToMap{
    public static void main (String[] args) {
        List<Hosting> list = new ArrayList<>();
        list.add(new Hosting(1, "google.com", 80000));
        list.add(new Hosting(2, "twitter.com", 90000));
        list.add(new Hosting(3, "facebooke.com", 70000));
        list.add(new Hosting(4, "apple.com", 50000));
        list.add(new Hosting(5, "yahoo.com", 20000));
       
        Map<Integer, String> res1 = list.stream()
                                        .collect(Collectors.toMap(Hosting::getId, Hosting::getName));
        System.out.println("Result1 : "+ res1);
        
        Map<String, Integer> res2 = list.stream()
                                        .collect(Collectors.toMap(Hosting::getName, Hosting::getWebs));
        System.out.println("Result2 : "+res2);
        
        Map<Integer, Integer> res3 = list.stream()
                                            .collect(Collectors.toMap(x-> x.getId(), x->x.getWebs()));
        System.out.println("Result3 : "+res3);
        
        // If there is a duplicated key then
        list.add(new Hosting(6, "yahoo.com", 40000));
        Map<String, Integer> res4 = list.stream()
                                        .collect(Collectors.toMap(Hosting::getName, Hosting::getWebs, (old, newv)->newv));
        System.out.println("Result4 : "+res4);
        
        //List to Map Sort and Collect
        Map res5 = list.stream()
                        .sorted(Comparator.comparingInt(Hosting::getWebs))
                        .collect(Collectors.toMap(Hosting::getName, Hosting::getWebs, (oldv, newv)-> newv, LinkedHashMap::new));
         System.out.println("Result5 : "+res5);                
        
    }
}