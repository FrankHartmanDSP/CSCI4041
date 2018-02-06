/**
 * Created by Hartman on 10/10/17.
 */
import java.io.*;
import java.util.Scanner;
public class PriorityQueue {

    public static void Insert(Customer[] set, Customer elem){
        //the priority of set[0] is actually used to store the size of the priority queue
        if(set[0]==null){
            Customer size = new Customer(0, "size");
            set[0]=size;
        }
        set[0].setPriority(set[0].getPriority()+1); //size++
        set[set[0].getPriority()] = elem; //last spot in array is the new elem
        int index = set[0].getPriority();
        while(index > 1 && set[index/2].getPriority() < set[index].getPriority()){ //checking to see if parent is smaller than child, and swapping if so (until root is reached)
            Customer temp = set[index];
            set[index] = set[index/2];
            set[index/2] = temp;
            index = index/2;
        }
    }
    public static String Maximum(Customer[] set){
        //the priority of set[0] is actually used to store the size of the priority queue
        if(set[0]==null || set[0].getPriority()==0){
            System.out.println("error: heap underflow");
        }
        return set[1].getName();

    }

    public static Customer Extract_Max(Customer[] set){
        //the priority of set[0] is actually used to store the size of the priority queue
        if(set[0]==null || set[0].getPriority()==0){
            System.out.println("error: heap underflow");
            return null;
        }
        Customer max = set[1];
        set[1]=set[set[0].getPriority()]; //make new max the last element in the priority queue array
        set[0].setPriority(set[0].getPriority()-1); //size--
        maxHeapify(set,1); //reorder the priority queue to maintain the max-heap properties
        return max;
    }
    public static void maxHeapify(Customer[] set, int index){
        //the priority of set[0] is actually used to store the size of the priority queue
        int leftIndex=2*index;
        int rightIndex=2*index+1;
        int max; //index of max in this parent/left child/right child tree
        if(leftIndex<=set[0].getPriority() && set[leftIndex].getPriority()>set[index].getPriority()){
            max = leftIndex; //max is left child
        }
        else{
            max = index; //max is parent
        }
        if(rightIndex <= set[0].getPriority() && set[rightIndex].getPriority() > set[max].getPriority()){
            max = rightIndex; //max is right child
        }
        if(max!=index){ //if max is parent, no need to swap as it obeys the max-heap property. Otherwise, swap the largest child with the parent.
            Customer temp = set[index];
            set[index] = set[max];
            set[max] = temp;
            maxHeapify(set,max); //recursively continue down the tree until it is a max-heap
        }

    }
    public static void print(Customer[] set){ //for debugging purposes
        for(int i=1;i<set[0].getPriority()+1;i++){
            System.out.println(set[i].getName()+" ");
        }
    }

    public static void main(String[] args) {
        Customer[] pq = new Customer[1000000];
        //Reading the input text file
        try {
            Scanner input = new Scanner(new File(args[0]));
            while(input.hasNextLine()){
                String line = input.nextLine();
                Insert(pq, new Customer(Integer.parseInt(line.substring(line.indexOf(":")+2)),line.substring(0,line.indexOf(":")-1)));
            }
            input.close();
        } catch (IOException e) {
            System.out.println("No file \""+args[0]+"\" found!");
        }
        //Writing to the output text file
        String returner = "";
        int originalSize = pq[0].getPriority();
        for(int i=0; i < originalSize; i++){
            returner+= Extract_Max(pq).getName();
            if(i!=originalSize-1){
                returner+=" ";
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
