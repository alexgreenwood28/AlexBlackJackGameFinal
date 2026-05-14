//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class BlackJackGame {
    Card[] deck;

    public BlackJackGame() {
        deck = getShuffledDeck();
        for (int i = 0; i < deck.length; i++){
            deck[i].printInfo();
        }
    }

    public Card[] getShuffledDeck() {
        String[] suites = {"Hearts", "Clubs", "Diamonds", "Spades"};
        String[] names = {"Jack", "Queen", "King", "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        Card[] gameDeck = new Card[52];
        int placeInDeck = 0;
        //makes an unshuffled, sorted deck
        for (int i = 0; i < suites.length; i = i + 1) {
            for (int j = 0; j < names.length; j = j + 1) {
                gameDeck[placeInDeck] = new Card(names[j], suites[i]);
                placeInDeck = placeInDeck + 1;
            }

        }
        for(int i=0;i<gameDeck.length;i=i+1){
            int randomIndex = (int)(Math.random()* gameDeck.length);
            Card temp = gameDeck[i];
            gameDeck[i]=gameDeck[randomIndex];
            gameDeck[randomIndex] = temp;
        }
        return gameDeck;
    }


    public static void main(String[] args) {
        BlackJackGame game = new BlackJackGame();
    }
}

