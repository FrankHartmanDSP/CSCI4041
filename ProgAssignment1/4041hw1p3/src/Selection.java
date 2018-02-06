/**
 * Created by Hartman on 10/11/17.
 */

import java.io.*;
import java.util.Scanner;

public class Selection {
    public static int select(int[] A, int start, int end, int i) {
        //i is the index of the element we are looking for + 1
        if (start == end) { //we have one element in this partition. This is sorted, and is the last option, so it must be the answer.
            return A[start];
        }
        int q = partition(A, start, end);
        int k = q - start + 1; //this is the index of the pivot + 1 (its position)
        if (i == k) {
            return A[q]; //pivot is the ith element in the array, so it is the answer
        } else if (i < k) {
            return select(A, start, q - 1, i); //if the position we are looking for is less than that of the pivot's, we continue down the subarray to its left
        } else {
            return select(A, q + 1, end, i - k); //otherwise, we continue down the subarray to its right
        }
    }

    public static int partition(int[] A, int start, int end) {
        int x = A[end]; //pivot is the last element
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (A[j] <= x) { //current is smaller than or equal to the pivot
                i = i + 1;
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        int temp = A[i + 1];
        A[i + 1] = A[end];
        A[end] = temp; //swap pivot into its sorted position
        return (i + 1); //return position of pivot
    }

    public static void main(String[] args) {
        int i = 0;
        int count = 0;
        int[] arrTemp = new int[1000000];

        //Reading from input.txt
        try {
            Scanner line1 = new Scanner(new File(args[0]));
            i = line1.nextInt(); //the ith element we are searching for
            line1.nextLine();
            String l2 = line1.nextLine(); //the 2nd line, containing the array we must select in
            Scanner line2 = new Scanner(l2);
            while (line2.hasNextInt()) {
                arrTemp[count] = line2.nextInt(); //parsing every integer in this into an array
                count++;
            }
            line1.close();
            line2.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file \"" + args[0] + "\" found!");
        }
        int[] arrFinal = new int[count]; //so we can pass an array of the correct size (rather than 1 million) into the method
        for (int j = 0; j < count; j++) {
            arrFinal[j] = arrTemp[j];
        }

        //Writing to the output text file
        int returner = select(arrFinal, 0, arrFinal.length - 1, i);
        try {
            PrintWriter pw = new PrintWriter("output.txt");
            pw.print(returner);
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error creating \"output.txt\"!");
        }

    }
}
