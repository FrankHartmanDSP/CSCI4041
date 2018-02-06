/**
 * Created by Hartman on 10/12/17.
 */
public class PriorityQueue {

    public static void Insert(Letter[] set, Letter elem) {
        //the priority of set[0] is actually used to store the size of the priority queue
        if (set[0].getFrequency() + 1 == set.length) {
            Letter[] temp = new Letter[set.length * 2];
            for (int i = 0; i < set.length; i++) {
                temp[i] = set[i];
            }
            set = temp;
        }
        set[0].setFrequency(set[0].getFrequency() + 1);
        set[set[0].getFrequency()] = elem;
        int index = set[0].getFrequency();
        while (index > 1 && set[index / 2].getFrequency() > set[index].getFrequency()) {
            Letter temp = set[index];
            set[index] = set[index / 2];
            set[index / 2] = temp;
            index = index / 2;
        }
    }

    public static char Minimum(Letter[] set) {
        //the priority of set[0] is actually used to store the size of the priority queue
        if (set[0] == null || set[0].getFrequency() == 0) {
            System.out.println("error: heap underflow");
        }
        return set[1].getLetter();

    }

    public static Letter Extract_Min(Letter[] set) {
        //the priority of set[0] is actually used to store the size of the priority queue
        if (set[0] == null || set[0].getFrequency() == 0) {
            System.out.println("error: heap underflow");
            return null;
        }
        Letter min = set[1];
        set[1] = set[set[0].getFrequency()]; //make new min the last element in the priority queue array
        set[0].setFrequency(set[0].getFrequency() - 1); //size--
        minHeapify(set, 1); //reorder the priority queue to maintain the min-heap properties
        return min;
    }

    public static void minHeapify(Letter[] set, int index) {
        //the priority of set[0] is actually used to store the size of the priority queue
        int leftIndex = 2 * index;
        int rightIndex = 2 * index + 1;
        int min; //index of min in this parent/left child/right child tree
        if (leftIndex <= set[0].getFrequency() && set[leftIndex].getFrequency() < set[index].getFrequency()) {
            min = leftIndex; //min is left child
        } else {
            min = index; //min is parent
        }
        if (rightIndex <= set[0].getFrequency() && set[rightIndex].getFrequency() < set[min].getFrequency()) {
            min = rightIndex; //min is right child
        }
        if (min != index) { //if min is parent, no need to swap as it obeys the min-heap property. Otherwise, swap the smallest child with the parent.
            Letter temp = set[index];
            set[index] = set[min];
            set[min] = temp;
            minHeapify(set, min); //recursively continue down the tree until it is a min-heap
        }

    }


}
