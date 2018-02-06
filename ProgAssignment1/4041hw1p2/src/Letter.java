/**
 * Created by Hartman on 10/12/17.
 */
public class Letter {
    //Member Data
    private int frequency;
    private char letter;
    private Letter left;
    private Letter right;

    //Constructor
    public Letter(int frequency, char letter, Letter left, Letter right){
        this.frequency = frequency;
        this.letter = letter;
        this.left = left;
        this.right = right;
    }

    //Getters and Setters
    public int getFrequency() {return this.frequency;}

    public void setFrequency(int frequency) { this.frequency = frequency;}

    public char getLetter() {return this.letter; }

    public void setLetter(char letter){ this.letter = letter;}

    public Letter getLeft() {return this.left;}

    public Letter getRight() {return this.right;}

    public void setLeft() {}
}
