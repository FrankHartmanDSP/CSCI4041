import java.util.ArrayList;

/**
 * Created by Hartman on 11/27/17.
 */
public class Node {
    private int value;
    private String color;
    private int d;//distance
    private Node pi;
    private ArrayList<Node> neighbors = new ArrayList<>();

    public int getValue(){
        return value;
    }
    public void setValue(int value){
        this.value=value;
    }
    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color=color;
    }
    public int getD(){
        return d;
    }
    public void setD(int d){
        this.d=d;
    }
    public Node getPi(){
        return pi;
    }
    public void setPi(Node pi){
        this.pi=pi;
    }
    public ArrayList<Node> getNeighbors(){
        return neighbors;
    }
    public void setNeighbors(ArrayList<Node> neighbors){
        this.neighbors=neighbors;
    }
}
