import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Hartman on 11/28/17.
 */
public class Johnson {
    public static int[][] weights;
    public static int[][] wHat;

    public void relax(Node u, Node v, boolean wHat) {
        //relax determines if the updated distance is less than the current distance at v
        //if the updated distance is indeed less the current at v, we know the most efficient path is from u to v. So we update v's d value and add u as its parent
        if (v.getD() > u.getD() + weight(u, v, wHat)) {
            if(u.getD()!=2000000){
                v.setD(u.getD() + weight(u, v, wHat));
                v.setPi(u);
            }

        }

    }

    public int weight(Node u, Node v, boolean wHat) {//boolean wHat determines if check the original weights or the updated ones
        //both adjacency matrices are a nested array containing int values of the weights from u to v
        if (wHat) {
            return this.wHat[u.getValue()][v.getValue()];
        } else {
            return weights[u.getValue()][v.getValue()];
        }

    }

    public void initializeSingleSource(Node[] g, Node s) {
        //sets all distance values of each node to infinity and the pi (aka parent node) to null.
        for (int i = 0; i < g.length; i++) {
            g[i].setD(2000000);
            g[i].setPi(null);
        }
        s.setD(0);//set source distance to 0 because it is the starting point
    }

    public boolean bellmanFord(Node[] g, Node s) {//bellmanFord checks for a negative cycle in the graph
        initializeSingleSource(g, s);
        for (int i = 1; i < g.length - 1; i++) {
            for (int j = 0; j < g.length; j++) {
                for (int k = 0; k < g[j].getNeighbors().size(); k++) {
                    relax(g[j], g[j].getNeighbors().get(k), false);
                }
            }
        }
        for (int j = 0; j < g.length; j++) {
            for (int k = 0; k < g[j].getNeighbors().size(); k++) {
                if (g[j].getNeighbors().get(k).getD() > g[j].getD() + weight(g[j], g[j].getNeighbors().get(k), false)) {
                    return false;//negative cycle
                }
            }
        }
        return true;//no negative cycle
    }

    public int[][] john(Node[] g) {
        //compute gPrime
        Node s = new Node();//super node s! will connect to all other nodes with weight = 0
        Node[] gPrime = new Node[g.length + 1];
        int[][] weightsNew = new int[weights.length + 1][weights.length + 1];
        for (int i = 0; i < g.length; i++) {
            //adding all nodes as neighbors of s
            ArrayList<Node> temp = s.getNeighbors();
            temp.add(g[i]);
            s.setNeighbors(temp);
            //adding all nodes to gPrime
            gPrime[i] = g[i];
            //adding old weights to temp array
            for (int j = 0; j < g.length; j++) {
                weightsNew[i][j] = weights[i][j];
            }
            //updating weights to include s;
            weightsNew[i][g.length] = 2000000;
            weightsNew[g.length][i] = 0;
        }
        //adding s to gPrime and updating final weights
        s.setValue(g.length);
        gPrime[g.length] = s;
        weightsNew[g.length][g.length] = 0;
        //setting weights equal to temp array
        weights = weightsNew;
        //checking for negative-weight cycle
        if (!bellmanFord(gPrime, s)) {
            return null;
        } else {
            //calculating new weights (wHat) so they will all be non-negative. Then we can use dijkstra
            wHat = new int[weights.length][weights.length];
            for (int i = 0; i < gPrime.length; i++) {
                for (int j = 0; j < gPrime[i].getNeighbors().size(); j++) {
                    Node neighbor = gPrime[i].getNeighbors().get(j);
                    wHat[i][neighbor.getValue()] = weight(gPrime[i], gPrime[neighbor.getValue()], false) + gPrime[i].getD() - gPrime[neighbor.getValue()].getD();
                }
            }
            int[][] bigD = new int[g.length][g.length];//will be the all-pairs shortest paths
            Node[] gPrimeCopy = new Node[gPrime.length];//creating a copy of gPrime so that its distance values can be accessed after subsequent calls to dijkstra (for unweighting)
            for (int i = 0; i < gPrime.length; i++) {
                Node temp = new Node();
                temp.setNeighbors(gPrime[i].getNeighbors());
                temp.setValue(gPrime[i].getValue());
                temp.setD(gPrime[i].getD());
                gPrimeCopy[i] = temp;
            }
            //run dijkstra once from each vertex to determine shortest paths between all pairs of vertices
            for (int i = 0; i < g.length; i++) {
                dij(g, g[i]);
                for (int j = 0; j < g.length; j++) {
                    if (g[j].getD() == 2000000) {
                        bigD[i][j] = 2000000;
                    } else {
                        bigD[i][j] = g[j].getD() + gPrimeCopy[j].getD() - gPrimeCopy[i].getD();//dijkstra value and unweighting
                    }
                }
            }
            return bigD;
        }

    }

    public void dij(Node[] g, Node s) {
        initializeSingleSource(g, s);
        Node[] bigQ = new Node[1000000];
        //insert each node into a priority queue based off their distance values (initially they are all infinity except for the source vertex which is 0)
        for (int i = 0; i < g.length; i++) {
            PriorityQueue.Insert(bigQ, g[i]);
        }
        while (bigQ[0].getD() != 0) {
            //starting at source vertex, determine shortest path
            PriorityQueue.minHeapify(bigQ, 1);
            Node u = PriorityQueue.Extract_Min(bigQ);
            for (int j = 0; j < u.getNeighbors().size(); j++) {
                relax(u, u.getNeighbors().get(j), true);

            }
        }

    }

    public static void main(String[] args) {
        String result = "";
        Scanner s = null;
        try {
            s = new Scanner(new File(args[0]));
        } catch (IOException e) {
            System.out.println("No file \"" + args[0] + "\" found!");
        }
        int m = 0;//adjacency array size
        String line1 = s.nextLine(); //used to determine adjacency matrix size
        String copy = line1; //used since line1 will be destroyed in the process of determining the adjacency matrix size
        while (line1.indexOf(" ") != -1) {//determining adjacency matrix size
            line1 = line1.substring(line1.indexOf(" ") + 1);
            m++;
        }
        if (!line1.isEmpty()) {
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
        Johnson j = new Johnson();
        //running Johnson's algorithm on graph g
        int[][] bigD = j.john(g);
        //making result string
        if (bigD != null) {
            for (int i = 0; i < bigD.length; i++) {
                for (int k = 0; k < bigD.length; k++) {
                    if (k != bigD.length - 1) {
                        result += bigD[i][k] + " ";
                    } else {
                        result += bigD[i][k] + "";
                    }
                }
                if (i != bigD.length - 1) {
                    result += "\n";
                }
            }
        } else {
            result = "Negative cycle";
        }
        try {
            PrintWriter pw = new PrintWriter("output.txt");
            pw.println(result);
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error creating \"output.txt\"!");
        }


    }


}
