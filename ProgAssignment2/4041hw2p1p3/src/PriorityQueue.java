/**
 * Created by Hartman on 11/27/17.
 */
public class PriorityQueue {
    public static void Insert(Node[] set, Node elem) {
        //the priority of set[0] is actually used to store the size of the priority queue
        if(set[0]==null){
            Node size = new Node();
            size.setD(0);
            size.setValue(-1);
            set[0]=size;
        }
        if (set[0].getD() + 1 == set.length) {
            Node[] temp = new Node[set.length * 2];
            for (int i = 0; i < set.length; i++) {
                temp[i] = set[i];
            }
            set = temp;
        }
        set[0].setD(set[0].getD() + 1);
        set[set[0].getD()] = elem;
        int index = set[0].getD();
        while (index > 1 && set[index / 2].getD() > set[index].getD()) {
            Node temp = set[index];
            set[index] = set[index / 2];
            set[index / 2] = temp;
            index = index / 2;
        }
    }

    public static Node Minimum(Node[] set) {
        //the priority of set[0] is actually used to store the size of the priority queue
        if (set[0] == null || set[0].getD() == 0) {
            System.out.println("error: heap underflow");
        }
        return set[1];

    }

    public static Node Extract_Min(Node[] set) {
        //the priority of set[0] is actually used to store the size of the priority queue
        if (set[0] == null || set[0].getD() == 0) {
            System.out.println("error: heap underflow");
            return null;
        }
        Node min = set[1];
        set[1] = set[set[0].getD()]; //make new min the last element in the priority queue array
        set[0].setD(set[0].getD() - 1); //size--
        minHeapify(set, 1); //reorder the priority queue to maintain the min-heap properties
        return min;
    }

    public static void minHeapify(Node[] set, int index) {
        //the priority of set[0] is actually used to store the size of the priority queue
        int leftIndex = 2 * index;
        int rightIndex = 2 * index + 1;
        int min; //index of min in this parent/left child/right child tree
        if (leftIndex <= set[0].getD() && set[leftIndex].getD() < set[index].getD()) {
            min = leftIndex; //min is left child
        } else {
            min = index; //min is parent
        }
        if (rightIndex <= set[0].getD() && set[rightIndex].getD() < set[min].getD()) {
            min = rightIndex; //min is right child
        }
        if (min != index) { //if min is parent, no need to swap as it obeys the min-heap property. Otherwise, swap the smallest child with the parent.
            Node temp = set[index];
            set[index] = set[min];
            set[min] = temp;
            minHeapify(set, min); //recursively continue down the tree until it is a min-heap
        }

    }
}
