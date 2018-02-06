/**
 * Created by Hartman on 10/12/17.
 */
public class PqOrig {
    public static void Insert(int[] set, int elem){
        set[0]=set[0]+1;
        set[set[0]] = elem;
        int index = set[0];
        while(index > 1 && set[index/2] < set[index]){
            int temp = set[index];
            set[index] = set[index/2];
            set[index/2] = temp;
            index = index/2;
        }
        //heapIncreaseKey(set, set[0], elem);
    }
    public static int Maximum(int[] set){
        if(set[0]==0){
            System.out.println("error: heap underflow");
        }
        return set[1];

    }

    public static int Extract_Max(int[] set){
        //set[0] is the heap size
        if(set[0]==0){
            System.out.println("error: heap underflow");
            return 0;
        }
        int max = set[1];
        set[1]=set[set[0]];
        set[0]=(set[0]-1);
        maxHeapify(set,1);
        return max;
    }
    public static void maxHeapify(int[] set, int index){
        int leftIndex=2*index;
        int rightIndex=2*index+1;
        int max;
        if(leftIndex<=set[0] && set[leftIndex]>set[index]){
            max = leftIndex;
        }
        else{
            max = index;
        }
        if(rightIndex <= set[0] && set[rightIndex] > set[max]){
            max = rightIndex;
        }
        if(max!=index){
            int temp = set[index];
            set[index] = set[max];
            set[max] = temp;
            maxHeapify(set,max);
        }

    }
    /*public static void heapIncreaseKey(int set[], int index, int key){
        if(key<set[index]){
            System.out.println("error: value index smaller than key");
            return;
        }
        set[index] = key;
        while(index > 1 && set[index/2] < set[index]){
            int temp = set[index];
            set[index] = set[index/2];
            set[index/2] = temp;
            index = index/2;
        }

    }*/
    public static void print(int[] set){
        for(int i=0;i<set[0]+1;i++){
            System.out.println("index: " + i + "val: "+ set[i]+", ");
        }
    }

    public static void main(String[] args) {
        int[] pq = new int[1000000];
        Insert(pq, 5);
        Insert(pq, 3);
        Insert(pq, 8);
        Insert(pq, 12);
        print(pq);
        Extract_Max(pq);
        print(pq);
    }
}
