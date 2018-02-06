/**
 * Created by Hartman on 11/27/17.
 */

import java.lang.reflect.Array;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Dijkstra {
    private static int[][] weights;

    private void relax(Node u, Node v) {
        //relax determines if the updated distance is less than the current distance at v
        //this works because we have already determined the shortest distance to u and know the weight/distance from u to v from the adjacency matrix
        //if the updated distance is indeed less the current at v, we know the most efficient path is from u to v. So we update v's d value and add u as its parent
        if (v.getD() > u.getD() + weight(u, v)) {
            v.setD(u.getD() + weight(u, v));
            v.setPi(u);
        }
    }

    private void initializeSingleSource(Node[] g, Node s) {
        //sets all distance values of each node to infinity and the pi (aka parent node) to null.
        for (int i = 0; i < g.length; i++) {
            g[i].setD(2000000);
            g[i].setPi(null);
        }
        s.setD(0);//set source distance to 0 because it is the starting point
    }

    private int weight(Node u, Node v) {
        return weights[u.getValue()][v.getValue()];//a nested array containing int values of the weights from u to v
    }

    private void dij(Node[] g, Node s) {
        initializeSingleSource(g, s);
        Node[] bigQ = new Node[1000000];
        //insert each node into a priority queue based off their distance values (initially they are all infinity except for the source vertex which is 0)
        for (int i = 0; i < g.length; i++) {
            PriorityQueue.Insert(bigQ, g[i]);
        }
        while (bigQ[0].getD() != 0) {
            //starting at source vertex, determine shortest path
            Node u = PriorityQueue.Extract_Min(bigQ);
            for (int j = 0; j < u.getNeighbors().size(); j++) {
                relax(u, u.getNeighbors().get(j));

            }
        }

    }

    public static void main(String[] args) {
        String result = "";
        try {
            Scanner s = new Scanner(new File(args[0]));
            int destination;
            int source;
            int m = 0;//adjacency array size
            //grabbing source and destination from first line
            String line1 = s.nextLine();
            source = Integer.parseInt(line1.substring(0, line1.indexOf(" ")));
            destination = Integer.parseInt(line1.substring(line1.indexOf(" ") + 1));
            String line2 = s.nextLine(); //used to determine adjacency matrix size
            String copy = line2; //used since line1 will be destroyed in the process of determining the adjacency matrix size
            while (line2.indexOf(" ") != -1) {//determining adjacency matrix size
                line2 = line2.substring(line2.indexOf(" ") + 1);
                m++;
            }
            if (!line2.isEmpty()) {
                m++;
            }
            //we now know the adjacency matrix size, and therefore we know the number of nodes that will be in graph g
            weights = new int[m][m];
            Node[] g = new Node[m];
            ArrayList<Node> neighbors;
            //deal with the first row separately using if statements since nodes will be made then and not in later rows
            int row = 0;
            int col = 0;
            while (s.hasNextLine() || row == 0) {
                String line;
                if (row == 0) {
                    line = copy;
                } else {
                    line = s.nextLine();
                }
                neighbors = new ArrayList<>();
                while (line.indexOf(" ") != -1) {//creating nodes and filling adjacency matrix
                    String temp = line.substring(0, line.indexOf(" "));
                    line = line.substring(line.indexOf(" ") + 1);
                    weights[row][col] = Integer.parseInt(temp);
                    if (row == 0) {//if we are at row 0, we will create the nodes
                        Node nTemp = new Node();
                        nTemp.setValue(col);
                        g[col] = nTemp;
                    }
                    if (weights[row][col] != 0 && weights[row][col] != 2000000) {//if the weight from u to v is not 0 or infinity, we know v is a neighbor of u
                        neighbors.add(g[col]);
                    }
                    col++;
                }
                if (!line.isEmpty()) {//this is here because the while loop does not get the last element since there is no space after it.
                    weights[row][col] = Integer.parseInt(line);
                    if (row == 0) {
                        Node nTemp = new Node();
                        nTemp.setValue(col);
                        g[col] = nTemp;
                    }
                    if (weights[row][col] != 0 && weights[row][col] != 2000000) {
                        neighbors.add(g[col]);
                    }
                }
                g[row].setNeighbors(neighbors);
                row++;
                col = 0;
            }
            //Running dijkstra on the source vertex
            Dijkstra d = new Dijkstra();
            d.dij(g, g[source]);
            //final output
            Node dest = g[destination];
            result = "";
            while (dest != null) {//since pi is the parent and it is based off the shortest path (thanks to the relax method), we just need to print off every node's pi starting at the destination node until it is null.
                result = dest.getValue() + " " + result;
                dest = dest.getPi();
            }
            result = g[destination].getD() + ": " + result;
            result = result.substring(0, result.length() - 1);//chopping off trailing space
        } catch (IOException e) {
            System.out.println("No file \"" + args[0] + "\" found!");
        }
        //writing result to output.txt
        try {
            PrintWriter pw = new PrintWriter("output.txt");
            pw.println(result);
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error creating \"output.txt\"!");
        }

    }

}
