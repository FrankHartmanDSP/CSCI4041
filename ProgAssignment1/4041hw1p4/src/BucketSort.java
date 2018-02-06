/**
 * Created by Hartman on 10/17/17.
 */
public class BucketSort {
    public static LinkedList[] bucketSort(String[] A, int n, int index){
        LinkedList[] B = new LinkedList[n];
        for(int i=0;i<n;i++){
            B[i]=new LinkedList();
        }
        for(int j=0;j<A.length;j++){
            char temp = A[j].charAt(index);
            int val = Character.getNumericValue(temp)-10;
            B[(val)].add(A[j]);


        }
        for(int k=0;k<n;k++) {
            char temp = A[k].charAt(index);
            int val = Character.getNumericValue(temp) - 10;
            if (B[(val)].size() >= 10) {

                Node newNode = new Node(B[(val)].get(10),B[(val)].getNode(11));
                //bucketSort(temp2, 26, index++);
                B[k].sort();
            } else {
            }
        }
        return B;
    }

    public static void main(String[] args) {
        String[] testy = {"Azzz","Abba","Add","Ajj","Aaaa","Zoot"};
        LinkedList feed = new LinkedList();
        for(int j=0;j< testy.length;j++){
            feed.add(testy[j]);
        }
        LinkedList[] arr = bucketSort(testy,26,0);
        for(int i=0; i<26;i++){
                System.out.println(i);arr[i].print();
        }
    }
}
