/**
 * Created by Hartman on 10/18/17.
 */
public class BucketSortLinked {
    public static LinkedList[] bucketSort(LinkedList A, int n, int index){
        LinkedList[] B = new LinkedList[n];
        for(int i=0;i<n;i++){
            B[i]=new LinkedList();
        }
        for(int j=0;j<A.size();j++){
            String temp = (String)A.get(j);
            char ch = temp.charAt(index);
            int val = Character.getNumericValue(ch)-10;
            B[(val)].add(A.get(j));
        }
        for(int k=0;k<n;k++) {
            String temp = (String)A.get(k);
            char ch = temp.charAt(index);
            int val = Character.getNumericValue(ch)-10;
            if (B[(val)].size() >= 10) {
                Node newNode = new Node(B[(val)].get(10),B[(val)].getNode(11));
                B[(val)].getNode(9).setNext(null);
                LinkedList newLL = new LinkedList();
                //newLL.add
                //bucketSort(newNode, 26, index++));
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
        LinkedList[] arr = bucketSort(feed,26,0);
        for(int i=0; i<26;i++){
            System.out.println(i);arr[i].print();
        }
    }
}
