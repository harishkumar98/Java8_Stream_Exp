import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;

 class Card {
    
    public static final String [] ranks = {null,"Ace", "2", "3", "4", "5", "6", "7",
        "8", "9", "10", "Jack", "Queen", "King"};
        
    public static final String [] suits = {"Diamond", "Club", "Heart", "Spade"};
    
    public static Scanner sc = new Scanner(System.in);
    
    public static int MAX = 52;
    
    private int card_suit;
    private int card_rank;
    
    //Constructor to initialize the Card 
    public Card(int suit, int rank){
        card_rank = rank;
        card_suit = suit;
    }
    
    public Card(){
        
    }
   
    //setter and getters  ------------------1

    public void setSuit(int suit){
        card_suit = suit;
    }
    public void setRank(int rank){
        card_rank = rank;
    }
    
    public int getSuit(){
        return card_suit;
    }
    public int getRank(){
        return card_rank ;
    }
    
    // Printing the Cards ------------------- 2

    public String toString() {
        return ranks[card_rank] + " of " + suits[card_suit];
    }
    
    // Shuffling the Cards -------------------3

    public static void suffle(List<Card> cards){
        Collections.shuffle(cards);
        System.out.println("-------------After Suffling the cards are :------------------");
        cards.forEach(System.out::println);
    }
    
    // Adding the Cards in the deck ----------4 

    public static List<Card> addAllCard(){
        List<Card> l1 = new CopyOnWriteArrayList<>();
        for(int i = 0; i< Card.suits.length  ; i++){
          for(int j = 1; j< Card.ranks.length; j++){
              l1.add(new Card(i, j));
          }
        }
        return l1;
    }
    
    // Removing one or more card ------ 5
    
    public static List<Card> removeCard(List<Card> cards, int count){
        List<Card> removedCard = new CopyOnWriteArrayList<>();
        if(cards.size() == 0 || cards.size() < count ){
            System.out.println("Not Enough Cards in the Deck or The Deck is Empty");
        }
        else{
            if(0 <= cards.size() - count){
                IntStream.range(0, count).
                    forEach(x -> removedCard.add(cards.remove(cards.size()-1)) );
            }
            else
                System.out.println("there are not "+ count+ "cards in the deck");
        }
        System.out.println("Removed Card : " + removedCard+" Deck Size : " +cards.size());
        return removedCard;
    }
    
    // Adding the one or more cards in the deck ---- 6
    public static List<Card> addCard(List<Card> cards, int count, List<Card> avail){
        List<Card> addedCard = new CopyOnWriteArrayList<>();
         if(cards.size() == MAX ){
            System.out.println(" The Deck is Full");
         }
         else if(cards.size() == 0){
            cards = addAllCard();
            addedCard = cards;
         }
         else{
             if(cards.size()+count <= MAX){
                addedCard.addAll(avail);
                cards.addAll(addedCard);
                avail.clear();
             }
             else
                System.out.println("Deck Max Size : "+ MAX+ " with adding "+ count +" size is exceeding to :"+ (cards.size()+count));
         }
        // System.out.println("Added Card" + addedCard + cards.size() + "Cleared List : "+ avail);
        return addedCard;
    }
    
    // Playing Game ----------------------------------------------- 7

    public static void startGame1(List<Card> cards, int num){
        int total = cards.size();
        int card_pp = total / num;
        System.out.println("----------------Game Started 1----------------");
        List<Card> rem_cards = new CopyOnWriteArrayList<>();
        
        List<Card>  player =new CopyOnWriteArrayList();
        
        Map<Integer, Integer> winner = new ConcurrentHashMap<>();
        
        // Step 1 
        System.out.println("Enter the number of rounds to find the winner -- MAXIMUM ROUND : "+ card_pp);
        int round =  sc.nextInt();
        
        System.out.println("Game is starting for "+ round+" rounds");
        
        // Main logic to get the winner with drawing the cards from the deck
        IntStream.range(0, round)
                .forEach(x -> 
                {
                    
                    System.out.println("Round : "+ (x+1) + " ---- ");   
                    IntStream.range(0, num)
                            .forEach(i -> {
                                    List<Card> crd = removeCard(cards, 1);
                                    player.addAll(crd);
                                    rem_cards.addAll(crd);
                                });
                    
                    int max_rank =player.get(0).getRank();
                    int max_suit = player.get(0).getSuit();
                    for(Card cr : player){
                        if(max_rank < cr.getRank())
                            max_rank = cr.getRank();
                        if(max_suit < cr.getSuit())
                            max_suit = cr.getSuit();
                    }
                    
                    final int mr =max_rank;
                    final int ms =max_suit;
                    List<Card> exp = player.stream().filter(c-> c.getRank()== mr).collect(Collectors.toList());
                    Card mcart;
                    int z ;
                    if(exp.size() > 1){
                        mcart = exp.stream().filter(e -> e.getSuit() == ms).findAny().get();
                         z =player.indexOf(mcart);
                    }
                    else{
                        z = player.indexOf(exp.get(0));
                    }
                    System.out.println("Winner Index: "+z);
                    
                    if(winner.containsKey(z)){
                        winner.put(z, winner.get(z)+1);
                    }
                    else
                        winner.put(z, 1);   // finding the winner for each round
                   player.clear(); 
                });

        //2nd step Find the winner or winners for all rounds
        System.out.println(winner);
        Integer m = winner.entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue();
        Set<Integer> win_set = winner.keySet();
        for(int f : win_set){
            if(m == winner.get(f)){
                System.out.println("Winner is: Player "+(f+1) );
            }
        }
        
        //3rd step 
        // Adding the removed cards into the deck
        System.out.println("Added Cards after the game: \n"+ addCard(cards, rem_cards.size(), rem_cards));
    }

} // Card Class Ends Here

public class CardDeckDemo {
     
    public static void main(String args[]) {
      
    // Adding All Cards in the Deck
      List<Card> temp = new CopyOnWriteArrayList<>();
      temp = Card.addAllCard();
      
    //printing all the cards available in the Deck
      System.out.println("----------Before Suffling the Cards---------");
      temp.forEach(System.out::println);
      
    // suffling all the cards in the Deck
      
      System.out.println();
      Card.suffle(temp);
      
    //Adding the number of players to play the game --- 52 should be divisible by the number of players
    // I have taken number of players as 4
    
      int p_num = Card.sc.nextInt();
      //int p_num = 4;
      System.out.println();
      // start the game ---------------
      if(temp.size()%p_num == 0)
        Card.startGame1(temp, p_num);
      else
        System.out.println("Not accurate number of players for the game");
     
     // -------------END---------------
    }
}