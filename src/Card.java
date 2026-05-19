public class Card {
    int value;
    String suite;
    String name;

    public Card(String nameInput, String suiteInput){
        suite=suiteInput;
        name= nameInput;
        if (name == "Ace") {
            value=1;
        }
        else if (name =="King"){
            value=10;
        }
        else if (name=="Queen"){
            value=10;
        }
        else if(name=="Jack"){
            value=10;
        }
        else{
            value=Integer.parseInt(name);
        }
       // printInfo();
    }
    public void printInfo(){
        System.out.println(name + " of " + suite);

    }
}
