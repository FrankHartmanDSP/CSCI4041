/**
 * Created by Hartman on 10/17/17.
 */
public class LinkedList<T extends Comparable<T>> {
    Node head;

    public LinkedList() {head = null;}

    public boolean add(T element) {
        if (element == null) {
            return false;
        }
        if (head == null) {
            head = new Node<T>(element, head);
            return true;
        }
        Node<T> n = head;
        while (n.getNext() != null) {
            n = n.getNext();
        }
        n.setNext(new Node<T>(element));
        return true;
    }

    public int size() {
        if (head == null) {
            return 0;
        }
        int size = 0;
        for (Node<T> n = head; n.getNext() != null; n = n.getNext()) {
            size++;
        }
        size++;
        return size;
    }

    public T get(int index) {
        if (head == null || index > (size() - 1)) {
            return null;
        }
        Node<T> n = head;
        for (int i = 0; i < index; i++) {
            n = n.getNext();
        }
        return n.getData();
    }
    public Node<T> getNode(int index) {
        if (head == null || index > (size() - 1)) {
            return null;
        }
        Node<T> n = head;
        for (int i = 0; i < index; i++) {
            n = n.getNext();
        }
        return n;
    }

    public T set(int index, T element) {
        if (element == null || index < 0 || index > (size() - 1)) {
            return null;
        }
        Node<T> n = head;
        for (int i = 0; i < size(); i++) {
            if (i == index) {
                T nCopy = n.getData();
                n.setData(element);
                return nCopy;
            }
            n = n.getNext();
        }

        return null;
    }

    public void sort() {
            for (int i = 1; i < size(); i++) {
                T n = get(i);
                /*for (int j = i - 1; j >= 0; j--) {
                    if (n.compareTo(get(j)) < 0) {
                        set(j + 1, get(j));
                        set(j, n);
                    } else {
                        break;
                    }
                }*/
                int j=i-1;
                while(j >= 0 && n.compareTo(get(j)) < 0){
                    set(j+1, get(j));
                    set(j,get(j-1));
                }
                set(j+1,n);
        }

    }

    public void print() {
        Node<T> n;
        if(head!=null){
            for (n = head; n.getNext() != null; n = n.getNext()) {
                System.out.print(n.getData() + ", ");
            }
            n.getNext();
            System.out.print(n.getData());
            System.out.println("");
        }
    }


}
