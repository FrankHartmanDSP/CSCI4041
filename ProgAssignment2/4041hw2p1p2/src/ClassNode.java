/**
 * Created by Hartman on 11/26/17.
 */
import java.util.ArrayList;
public class ClassNode {
    private String courseNumber;
    private String color;
    private int d;//time of discover
    private int f;//time of finishing
    private ClassNode pi;
    private ArrayList<ClassNode> neighbors = new ArrayList<>();

    public String getCourseNumber(){
        return courseNumber;
    }
    public void setCourseNumber(String courseNumber){
        this.courseNumber=courseNumber;
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
    public int getF(){
        return f;
    }
    public void setF(int f){
        this.f=f;
    }
    public ClassNode getPi(){
        return pi;
    }
    public void setPi(ClassNode pi){
        this.pi=pi;
    }
    public ArrayList<ClassNode> getNeighbors(){
        return neighbors;
    }
    public void setNeighbors(ArrayList<ClassNode> neighbors){
        this.neighbors=neighbors;
    }
}
