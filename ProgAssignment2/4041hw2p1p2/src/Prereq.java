/**
 * Created by Hartman on 11/25/17.
 */
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
public class Prereq {
    public int time=0;
    public void dfs(ArrayList<ClassNode> g){
        //initially set all colors to White since no nodes have been visited and set all pi values to null since no parents have been discovered yet
        for(int i=0;i<g.size();i++){
            g.get(i).setColor("White");
            g.get(i).setPi(null);
        }
        for(int i=0;i<g.size();i++){
            //go through each node and visit it if it has not been visited yet
            if(g.get(i).getColor().equals("White")){
                dfsVisit(g.get(i));
            }
        }
    }
    private void dfsVisit(ClassNode u){
        //we are visiting a new node, so color it Gray and increase the time
        time = time +1;
        u.setD(time);
        u.setColor("Gray");
        ArrayList<ClassNode> v = u.getNeighbors();
        if(v.size()>0){
            for(int i=0;i<v.size();i++){
                if(v.get(i).getColor().equals("White")){
                    v.get(i).setPi(u);//parent of v is u
                    dfsVisit(v.get(i));//if a neighbor of u has not been visited yet, we make a recursive call and visit it
                }
            }
        }
        u.setColor("Black");//all neighbors of u have been visited, so it is finished. Color it Black and set the finish time.
        time = time +1;
        u.setF(time);
        //sort by finish time and return as output
    }
    public ArrayList<ClassNode> shortestPath(ArrayList<ClassNode> g){
        //insertion sort, but largest to smallest
        ClassNode key;
        int j;
        for(int i = 1; i<g.size();i++){
            key = g.get(i);
            j = i-1;
            while (j >=0 && g.get(j).getF()>key.getF()){

                g.set(j+1, g.get(j));
                j = j-1;
            }
            g.set(j+1,key);
        }
        return g;
    }
    public static void main(String[] args) {
        Scanner s = null;
        try {
            s = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            System.out.println("No file \""+args[0]+"\" found!");
        }
        ArrayList<ClassNode> g = new ArrayList<>();//graph g of all nodes
        ArrayList<String> strings = new ArrayList<>();//used to search by the string of the course # and return the correct index for usage in g
        while(s.hasNextLine()){//goes through entire input text file, and adds each course to g and its corresponding course# to the strings arraylist
            String line = s.nextLine();
            ClassNode temp = new ClassNode();
            temp.setCourseNumber(line.substring(0,line.indexOf(":")));
            g.add(temp);
            strings.add(line.substring(0,line.indexOf(":")));
        }
        Scanner s2 = null;
        try {
            s2 = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            System.out.println("No file \""+args[0]+"\" found!");
        }
        while(s2.hasNextLine()){//goes through entire text file and determines the "neighbors" (aka courses it is a prereq for) for each course in g
            String line = s2.nextLine();
            String neighbor = line.substring(0,line.indexOf(":"));
            ClassNode n = g.get(strings.indexOf(neighbor));//here strings is used to find the index of the neighbor course, which is the course the neighbors are a prereq for
            line = line.substring(line.indexOf(":")+1);
            while(line.indexOf(" ")!=-1){
                String temp = line.substring(0,line.indexOf(" "));
                line = line.substring(line.indexOf(" ")+1);
                int index = strings.indexOf(temp);//strings used to get index of prereq course
                ArrayList<ClassNode> current = g.get(index).getNeighbors();
                current.add(n);//updated neighbors arraylist
                g.get(index).setNeighbors(current);//add updated neighbors to parent
            }
            if(!line.isEmpty()){//since there is no space after the last prereq
                int index = strings.indexOf(line);
                ArrayList<ClassNode> current = g.get(index).getNeighbors();
                current.add(n);
                g.get(index).setNeighbors(current);
            }
            //now a neighbor has been added to the appropriate parent course node. In other words, the prereqs are now pointing to the course that follows them.
            //this process is repeated for every course in the input text file
        }
        Prereq testy = new Prereq();
        //run depth first search
        testy.dfs(g);
        //sort by finishing times (largest to smallest) obtained from dfs
        g = testy.shortestPath(g);
        //print off each course number in the new order
        String result="";
        for(int i=0;i<g.size();i++){
            result=(g.get(i).getCourseNumber())+" "+result;
        }
        result=result.substring(0,result.length()-1);
        try {
            PrintWriter pw = new PrintWriter("output.txt");
            pw.println(result);
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error creating \"output.txt\"!");
        }

    }
}
