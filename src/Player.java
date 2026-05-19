public class Player {
    Card[] hand;
    int numberOfCards;

    public Player(Card card1, Card card2){
        hand = new Card[12];
        numberOfCards=2;

        hand[0]=card1;
        hand[1]=card2;
    }
    public void hit(Card newCard){
        hand[numberOfCards]=newCard;
        numberOfCards=numberOfCards+1;
    }
    public void stand(){

    }
    public void printInfo() {
        for (int i = 0; i < numberOfCards; i++) {
            hand[i].printInfo();
        }
    }
    public int getHandValue(){
        int sum = 0;
        for(int i=0; i<numberOfCards; i=i+1){
            sum = sum + hand[i].value;
        }

        return sum;
    }
}
