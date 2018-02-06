/**
 * Created by Hartman on 10/18/17.
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class BucketSortClass {
    public static ArrayList<ArrayList<String>> bucketSort(ArrayList<String> A, int n, int index) {
        ArrayList<ArrayList<String>> B = new ArrayList<>();
        //the final ArrayList B will be completely sorted using bucketSort.
        //each index of B will be a bucket for the Strings.
        //n is the number of buckets, while index keeps track of which character in the ArrayList of strings we are sorting by
        for (int i = 0; i < n; i++) {
            B.add(i, new ArrayList<String>());//create 26 empty buckets, since we are only dealing with alphabetic characters
        }
        for (int j = 0; j < A.size(); j++) {
            //find the index value of the current character (given by index) of the current String (given by j) in A
            String temp = A.get(j);
            char ch = temp.charAt(index);
            int val;
            if (index == 0) { //if index equals zero, we know we are on the first letter, therefore it is capital
                val = Character.getNumericValue(ch) - 10;
            } else { //otherwise the letter is lowercase
                val = ch - 'a';
            }
            B.get(val).add(temp); //add the string into the correct bucket based on the index of the current character
        }
        for (int k = 0; k < B.size(); k++) {
            if (B.get(k).size() > 10) { //for each bucket which has 11 or more elements, we must divide it into sub-buckets of size 10 or less
                ArrayList<ArrayList<String>> C = bucketSort(B.get(k), 26, index + 1); //we do this by calling bucket sort recursively with the offending bucket passed in, placing each of the strings in 26 new buckets based on the index of the next character (allowing for greater subdivision)
                B.remove(k); //the original offending bucket is then removed
                //the sub-buckets obtained from the recursion are then inserted back into the larger ArrayList of buckets starting at the original index of the offending bucket
                int count = 0; //keeps track of how many sub-buckets are inserted back in.
                for (int i = 0; i < C.size(); i++) {
                    if (C.get(i).size() != 0) { //if the sub-bucket is not empty, add it as a bucket in the larger ArrayList
                        B.add(k + count, C.get(i)); //k + count is used here so the next non-empty sub-bucket may be added next to the previous with no empty buckets in between
                        count++;
                    }

                }

            }
            if (B.get(k).size() > 1) { //for each bucket which is greater than 1 element (since any bucket with just 1 elem is trivially sorted), call insertion sort on it
                B.set(k, insertionSort(B.get(k)));
            }
        }

        return B;
    }

    public static ArrayList<String> insertionSort(ArrayList<String> A) {
        for (int i = 1; i < A.size(); i++) {
            String n = A.get(i);
            int j = i - 1;
            while (j >= 0 && n.compareTo(A.get(j)) < 0) {
                A.set(j + 1, A.get(j));
                j--;
            }
            A.set(j + 1, n);
        }
        return A;
    }

    public static void main(String[] args) {

        //Reading from input.txt
        ArrayList<String> feed = new ArrayList<>(); //used to feed all the String names from the text file into bucketSort
        try {
            Scanner input = new Scanner(new File(args[0]));
            while (input.hasNext()) {
                feed.add(input.next());
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file \"" + args[0] + "\" found!");
        }

        //Call bucket sort
        ArrayList<ArrayList<String>> arr = bucketSort(feed, 26, 0);

        //Writing to output.txt
        String returner = "";
        int count = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).size() != 0) { // so we don't print empty buckets
                for (int j = 0; j < arr.get(i).size(); j++) {
                    if (count == 0) { //if we are printing the first name, we don't want to add a space before it
                        returner = returner + arr.get(i).get(j);
                        count++;
                    } else {
                        returner = returner + " " + arr.get(i).get(j);
                    }

                }
            }
        }
        try {
            PrintWriter pw = new PrintWriter("output.txt");
            pw.print(returner);
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error creating \"output.txt\"!");
        }

    }
}
