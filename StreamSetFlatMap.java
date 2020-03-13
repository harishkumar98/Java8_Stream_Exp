import java.util.*;
import java.util.stream.*;

class Student{
    String name;
    Set<String> book;
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setBook(Set<String> book){
        this.book=book;
    }
    public Set<String> getBook(){
        return book;
    }
    
    public void addBook(String book){
        if(this.book == null)
            this.book = new HashSet<>();
        
        this.book.add(book);
    }
}
public class StreamSetFlatMap{
    public static void main(String s[]){
        Student obj1 = new Student();
        obj1.setName("mkyong");
        obj1.addBook("Java 8 in Action");
        obj1.addBook("Spring Boot in Action");
        obj1.addBook("Effective Java (2nd Edition)");
        
        Student obj2 = new Student();
        obj2.setName("zilap");
        obj2.addBook("Learning Python, 5th Edition");
        obj2.addBook("Effective Java (2nd Edition)");
        
        List<Student> list = new ArrayList<>();
        list.add(obj1);
        list.add(obj2);
        
        //Collecting the books
        
        List<String> collect = list.stream()
                                    .map(x->x.getBook())
                                    .flatMap(x->x.stream())
                                    .distinct()
                                    .collect(Collectors.toList());
                                    
        collect.forEach(System.out::println);
    }
}