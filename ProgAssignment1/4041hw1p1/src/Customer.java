/**
 * Created by Hartman on 10/12/17.
 */
public class Customer {

    //Member Data
    private int priority;
    private String name;

    //Constructor
    public Customer(int priority, String name){
        this.priority = priority;
        this.name = name;
    }

    //Getters and Setters
    public int getPriority() {return this.priority;}

    public void setPriority(int priority) { this.priority = priority;}

    public String getName() {return this.name; }

    public void setName(String name){ this.name = name;}
}
