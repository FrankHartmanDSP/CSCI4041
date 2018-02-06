/**
 * Created by Hartman on 11/22/17.
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class FiniteAutomata {
    public static int[][] transFunc;
    public static ArrayList<Integer> matcher(String txt, int finalState, ArrayList<Character> sigma){
        int n = txt.length();
        int q=0;
        ArrayList<Integer> returner= new ArrayList<>();
        for(int i=0;i<n;i++){
            //parsing through each character in the input string
            //at each character, the next state is calculated based on the current state and if the current string character matches the current pattern character
            //if the current state equals the final state (i.e. pattern length), then we have found a match of the whole pattern
            //add the index where the match states to the returning arraylist
            q = transition(q,txt.charAt(i),sigma);
            if(q==finalState){
                    returner.add(i-finalState+1);

            }
        }
        return returner;
    }
    public static int transition(int state, Character current, ArrayList<Character> sigma){
        //returns the next state based on the current state and letter
        return transFunc[state][sigma.indexOf(current)];
    }
    public static void computeTransitionFunction(String pattern, ArrayList<Character> sigma){
        //creating a nested array which will be used to determine the next state based on the current character and state
        //the first dimension of this array will correspond to the current state, while the second dimension will correspond to the current character
        //the integer at transFunc[current state][current character] will then be the appropriate next state
        int m = pattern.length();
        int[][] returner = new int[m+1][sigma.size()];
        for(int q=0;q<=m;q++){
            for(int i=0;i<sigma.size();i++){
                int k = java.lang.Math.min(m,q+1);
                while((k>0)&&(!isSuffix(pattern.substring(0,k),pattern.substring(0,q)+sigma.get(i)))){
                    k = k-1;
                }
                returner[q][i]=k;
            }

        }
        transFunc = returner;


        return;

    }
    public static boolean isSuffix(String x, String y){
        return y.endsWith(x);
    }

    public static void main(String[] args) {
        String pattern = "";
        String txt = "";
        try {
            //grabbing input pattern and
            Scanner s = new Scanner(new File(args[0]));
            pattern = s.nextLine();
            txt = s.nextLine();

        } catch (Exception e) {
            System.out.println("No file \""+args[0]+"\" found!");
        }

        //creating arraylist of finite input alphabet
        ArrayList<Character> sigma = new ArrayList<>();
        int count =0;
        for(int i=0;i<txt.length();i++){
            //every time this encounters a new letter within the text, it adds it to sigma
            if(!sigma.contains(txt.charAt(i))){
                sigma.add(count,txt.charAt(i));
                count++;
            }
        }
        computeTransitionFunction(pattern,sigma);
        //returner1 is checking the pattern forward, returner2 is checking the pattern reversed
        ArrayList<Integer> returner1 = matcher(txt,pattern.length(),sigma);
        pattern = new StringBuilder(pattern).reverse().toString();
        computeTransitionFunction(pattern,sigma);
        ArrayList<Integer> returner2 = matcher(txt,pattern.length(),sigma);
        int i1=0;
        int i2=0;
        int size=0;
        String returner="";
        for(int i=0;i<transFunc.length;i++){
            for(int j=0;j<transFunc[0].length;j++){
                System.out.print(transFunc[i][j]+" ");
            }
            System.out.println("");
        }
        //creating final string of pattern index occurrences in order of least to greatest
        while(i1<returner1.size()&&i2<returner2.size()){
            if(returner1.get(i1)<returner2.get(i2)){
                if(size==0){//used so there is note a trailing space at the end
                    returner=returner+returner1.get(i1);
                    size+=1;
                }
                else{
                    returner=returner+" "+returner1.get(i1);
                }
                i1++;
            }
            else if(returner1.get(i1)>returner2.get(i2)){
                if(size==0){
                    returner=returner+returner2.get(i2);
                    size+=1;
                }
                else{
                    returner=returner+" "+returner2.get(i2);
                }
                i2++;
            }
            else{
                if(size==0){
                    returner=returner+returner2.get(i2);
                    returner=returner+" "+returner1.get(i1);
                    size+=1;
                }
                else{
                    returner=returner+" "+returner2.get(i2);
                    returner=returner+" "+returner1.get(i1);
                }
                i1++;
                i2++;
            }

        }
        //in case one returner is bigger than the other
        if(i1!=returner1.size()){
            while(i1<returner1.size()){
                if(size==0){
                    returner=returner+returner1.get(i1);
                    size+=1;
                }
                else{
                    returner=returner+" "+returner1.get(i1);
                }
                i1++;
            }
        }
        else if(i2!=returner2.size()){
            while(i2<returner2.size()) {
                if (size == 0) {
                    returner = returner + returner2.get(i2);
                    size += 1;
                } else {
                    returner = returner + " " + returner2.get(i2);
                }
                i2++;
            }
        }
        try {
            PrintWriter pw = new PrintWriter("output.txt");
            pw.println(returner);
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error creating \"output.txt\"!");
        }

    }
}
