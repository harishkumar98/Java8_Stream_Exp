public class PermuationOfString {
    public static String swap(String str, int i, int j){
        char [] ch = str.toCharArray();
        char temp =ch[i];
        ch[i]  = ch[j];
        ch[j] = temp;
        
        return String.valueOf(ch);
        
    }
    public static void permutations(String s, int start, int end){
        if(start == end-1)
            System.out.println(s);
        else{
            for(int i = start; i<end; i++){
                s = swap(s, start, i);
                permutations(s, start+1, end);
                s = swap(s, start, i);
            }
        }
    
    } 
    public static void main(String args[]) {
      String s = "abcd";
      int l = s.length();
      System.out.println("Permutations are");
      permutations(s,0,l);
    }
}