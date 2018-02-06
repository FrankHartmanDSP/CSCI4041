import java.io.*;
import java.util.Scanner;

/**
 * Created by Hartman on 10/11/17.
 */
public class Huffman {
    public static Letter[] counter(String original){
        //this method counts the amount of letters for each letter from a-z
        Letter[] returner;
        char[] letters = new char[26];
        int[] freqs = new int[26]; //freqs = frequencies
        int finalSize = 0;
        for(int i=0; i<original.length(); i++){
            char ch = original.charAt(i);
            int pos = ch - 'a'; //obtaining index based on character value
            letters[pos]=ch;
            freqs[pos]++;
            if(freqs[pos]==1){
                finalSize++; //counts each time a new letter is encountered, so the final array may be the size of only the letters included.
            }
        }
        returner = new Letter[finalSize];
        int returnerIterator = 0;
        for(int i=0; i<26; i++){
            if(freqs[i]!=0){
                returner[returnerIterator]=new Letter(freqs[i],letters[i], null, null);
                returnerIterator++;
            }
        }
        //We now have an array of Letter nodes, which contain the frequency and character. This array is only the size of the amount of letters in the original string.
        //the left and right children will be used to build the tree and create the string.
        return returner;
    }
    public static Letter buildTree(Letter[] unsorted){ //partially based on the psuedo-code found in the class text
        Letter[] sorted = new Letter[unsorted.length+1];
        sorted[0] = new Letter(0, 'S', null, null); //S for size. The priority of the 0 index is used to store the size of the priority queue.
        for(int i = 0; i<unsorted.length; i++){
            PriorityQueue.Insert(sorted,unsorted[i]); //this takes the array created from counter (which is in alphabetical order), and resorts it into a minimum priority queue.
        }
        int size = sorted[0].getFrequency();
        for(int i=0; i<size-1;i++){
            Letter z;
            Letter x = PriorityQueue.Extract_Min(sorted);
            Letter y = PriorityQueue.Extract_Min(sorted);
            z = new Letter(x.getFrequency()+y.getFrequency(),'F', x, y); //F for frequency, since this new node is not a letter but actually the two children's frequencies added together.
            PriorityQueue.Insert(sorted,z);
        }
        return PriorityQueue.Extract_Min(sorted);
    }
    public static String getString(Letter current, String result){
        //recursively creates the binary value of each letter in the tree
        String finalString = "";
        if(current.getLeft()==null&&current.getRight()==null){
            //we have reached a leaf of the tree, so we know this is a
            finalString += current.getLetter()+": "+result +"\n";
        }
        if (current.getLeft()!=null){
            //if we travel left, a 0 is added to the result
            finalString += getString(current.getLeft(), result+"0");
        }
        if (current.getRight()!=null){
            //if we travel right, a 1 is added to the result
            finalString +=  getString(current.getRight(), result+"1");
        }
        return finalString;

    }

    public static void main(String[] args) {
        String initial="";
        try {
            Scanner line = new Scanner(new File(args[0]));
            initial = line.next();
            line.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file \""+args[0]+"\" found!");
        }
        Letter[] huff = counter(initial);
        Letter root = buildTree(huff);
        String returner = getString(root,"");
        try {
            PrintWriter pw = new PrintWriter("output.txt");
            pw.print(returner);
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error creating \"output.txt\"!");
        }





    }
}
