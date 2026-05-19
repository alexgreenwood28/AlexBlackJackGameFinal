//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv DON'T CHANGE! vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
// Graphics Libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
public class BasicGameApp implements Runnable, KeyListener {

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;


    //Variable Definition Section
    Card[] deck;
    Player player;
    Player dealer;
    int nextCard;       // which card to deal next
    int gameState;// 0=play, 1=win, 2=lose, 3=tie
    boolean dealerHidden = true; //hides dealers second card until stand

    public BasicGameApp() {
        setUpGraphics();

        deck = getShuffledDeck();
        player = new Player(deck[0], deck[1]);
        dealer = new Player(deck[2], deck[3]);
        nextCard = 4;
        gameState = 0;
    }

    public void moveThings() {

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
        for (int i = 0; i < gameDeck.length; i = i + 1) {
            int randomIndex = (int) (Math.random() * gameDeck.length);
            Card temp = gameDeck[i];
            gameDeck[i] = gameDeck[randomIndex];
            gameDeck[randomIndex] = temp;
        }
        return gameDeck;
    }

    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // background
        Image backgroundImage = getImage("Background.jpg");
        g.drawImage(backgroundImage, 0, 0, WIDTH, HEIGHT, null);

        Image cardImage = getImage("cardbackground.png");

        // title
        g.setColor(Color.PINK);
        g.setFont(new Font("Arial", Font.BOLD, 44));
        g.drawString("BLACKJACK", 370, 50);

        // dealer label
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString("Dealer", 50, 110);

        // dealer card 1
        if (dealer.numberOfCards >= 1) {
            g.drawImage(cardImage, 50, 130, 150, 150, null);
            if (dealer.hand[0].suite == "Hearts" || dealer.hand[0].suite == "Diamonds") {
                g.setColor(Color.RED);
            }
            else {
                g.setColor(Color.BLACK);
            }
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString(dealer.hand[0].name + " of", 65, 200);
            g.drawString(dealer.hand[0].suite, 65, 225);
        }

        // dealer card 2
        if (dealer.numberOfCards >= 2) {
            if (dealerHidden == true) {
                g.setColor(Color.BLUE);
                g.fillRect(210, 130, 150, 150);
            } else {
                g.drawImage(cardImage, 210, 130, 150, 150, null);
                if (dealer.hand[1].suite == "Hearts" || dealer.hand[1].suite == "Diamonds") {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.setFont(new Font("Arial", Font.BOLD, 18));
                g.drawString(dealer.hand[1].name + " of", 225, 200);
                g.drawString(dealer.hand[1].suite, 225, 225);
            }
        }


        // dealer card 3
        if (dealer.numberOfCards >= 3) {
            g.drawImage(cardImage, 370, 130, 150, 150, null);
            if (dealer.hand[2].suite == "Hearts" || dealer.hand[2].suite == "Diamonds") {
                g.setColor(Color.RED);
            }
            else {
                g.setColor(Color.BLACK);
            }
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString(dealer.hand[2].name + " of", 385, 200);
            g.drawString(dealer.hand[2].suite, 385, 225);
        }

        // dealer card 4
        if (dealer.numberOfCards >= 4) {
            g.drawImage(cardImage, 530, 130, 150, 150, null);
            if (dealer.hand[3].suite == "Hearts" || dealer.hand[3].suite == "Diamonds") {
                g.setColor(Color.RED);
            }
            else {
                g.setColor(Color.BLACK);
            }
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString(dealer.hand[3].name + " of", 545, 200);
            g.drawString(dealer.hand[3].suite, 545, 225);
        }

        // dealer card 5
        if (dealer.numberOfCards >= 5) {
            g.drawImage(cardImage, 690, 130, 150, 150, null);
            if (dealer.hand[4].suite == "Hearts" || dealer.hand[4].suite == "Diamonds") {
                g.setColor(Color.RED);
            }
            else {
                g.setColor(Color.BLACK);
            }
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString(dealer.hand[4].name + " of", 705, 200);
            g.drawString(dealer.hand[4].suite, 705, 225);
        }

        // dealer total
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 22));
        if (dealerHidden==true){
            g.drawString("Total: " + dealer.hand[0].name, 50, 320);
        }
        if (dealerHidden==false){
            g.drawString("Total: " + dealer.getHandValue(), 50, 320);
        }


        // player label
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString("You", 50, 390);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.drawString("Total: "+player.getHandValue(),50,600);

        // player card 1
        if (player.numberOfCards >= 1) {
            g.drawImage(cardImage, 50, 410, 150, 150, null);
            if (player.hand[0].suite == "Hearts" || player.hand[0].suite == "Diamonds") {
                g.setColor(Color.RED);
            }
            else {
                g.setColor(Color.BLACK);
            }
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString(player.hand[0].name + " of", 65, 480);
            g.drawString(player.hand[0].suite, 65, 505);
        }

        // player card 2
        if (player.numberOfCards >= 2) {
            g.drawImage(cardImage, 210, 410, 150, 150, null);
            if (player.hand[1].suite == "Hearts" || player.hand[1].suite == "Diamonds") {
                g.setColor(Color.RED);
            }
            else {
                g.setColor(Color.BLACK);
            }
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString(player.hand[1].name + " of", 225, 480);
            g.drawString(player.hand[1].suite, 225, 505);
        }

        // player card 3
        if (player.numberOfCards >= 3) {
            g.drawImage(cardImage, 370, 410, 150, 150, null);
            if (player.hand[2].suite == "Hearts" || player.hand[2].suite == "Diamonds") {
                g.setColor(Color.RED);
            }
            else {
                g.setColor(Color.BLACK);
            }
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString(player.hand[2].name + " of", 385, 480);
            g.drawString(player.hand[2].suite, 385, 505);
        }

        // player card 4
        if (player.numberOfCards >= 4) {
            g.drawImage(cardImage, 530, 410, 150, 150, null);
            if (player.hand[3].suite == "Hearts" || player.hand[3].suite == "Diamonds") {
                g.setColor(Color.RED);
            }
            else {
                g.setColor(Color.BLACK);
            }
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString(player.hand[3].name + " of", 545, 480);
            g.drawString(player.hand[3].suite, 545, 505);
        }

        // player card 5
        if (player.numberOfCards >= 5) {
            g.drawImage(cardImage, 690, 410, 150, 150, null);
            if (player.hand[4].suite == "Hearts" || player.hand[4].suite == "Diamonds") {
                g.setColor(Color.RED);
            }
            else {
                g.setColor(Color.BLACK);
            }
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString(player.hand[4].name + " of", 705, 480);
            g.drawString(player.hand[4].suite, 705, 505);
        }


        if (gameState == 0) {
            g.setFont(new Font("Arial", Font.BOLD, 26));
            g.setColor(Color.WHITE);
            g.drawString("Press H to Hit  |  S to Stand", 320, 660);
        }
        if (gameState == 1) {
            g.setFont(new Font("Arial", Font.BOLD, 26));
            g.setColor(Color.BLUE);
            g.drawString("YOU WIN! Press ENTER to play again", 270, 660);
        }
        if (gameState == 2) {
            g.setFont(new Font("Arial", Font.BOLD, 26));
            g.setColor(Color.PINK);
            g.drawString("Dealer wins. Press ENTER to play again", 250, 660);
        }
        if (gameState == 3) {
            g.setFont(new Font("Arial", Font.BOLD, 26));
            g.setColor(Color.WHITE);
            g.drawString("Tie. Press ENTER to play again", 320, 660);
        }


        g.dispose();
        bufferStrategy.show();
    }

    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv DON'T CHANGE! vvvvvvvvvvvvvvvvvv
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;

    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();
        new Thread(ex).start();
    }

    public void run() {
        while (true) {
            moveThings();
            render();
            pause(10);
        }
    }

    public void pause(int time ) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    private Image getImage(String filename){
        return Toolkit.getDefaultToolkit().getImage(filename);
    }

    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);
        canvas.addKeyListener(this);
        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
        System.out.println("DONE graphic setup");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key=e.getKeyCode();
      //  System.out.println(key);
        //Hit =H
        if(key==72 && gameState==0){
            player.hit(deck[nextCard]);
            nextCard=nextCard+1;
            if(player.getHandValue()>21){
                gameState=2;
            }
            else if(player.getHandValue()==21){
                gameState=1; //automatic win
            }
        }
        //Stand
        if(key==83&&gameState==0) {
            dealerHidden = false;
                while (dealer.getHandValue() < 17) {
                    dealer.hit(deck[nextCard]);
                    nextCard = nextCard + 1;
                }
                if (dealer.getHandValue() > 21) {
                    gameState = 1; //you win
                } else if (player.getHandValue() > dealer.getHandValue()) {
                    gameState = 1; //you win
                } else if (player.getHandValue() < dealer.getHandValue()) {
                    gameState = 2; //you lose
                } else if (player.getHandValue() == dealer.getHandValue()) {
                    gameState = 3; //tie
                }
            }
        // Enter means re-start game
        if(key == 10){
            gameState = 0;
            nextCard=4;
            dealerHidden=true;
            // Get new reshuffled deck
            deck = getShuffledDeck();
            player = new Player(deck[0], deck[1]);
            dealer = new Player(deck[2], deck[3]);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
}