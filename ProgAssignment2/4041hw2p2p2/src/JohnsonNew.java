import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Hartman on 12/1/17.
 */
public class JohnsonNew {
    public static int[][] weights;

    public void relax(Node u, Node v) {
        //relax determines if the updated distance is less than the current distance at v
        //if the updated distance is indeed less the current at v, we know the most efficient path is from u to v. So we update v's d value and add u as its parent
        if (v.getD() > u.getD() + weight(u, v)) {
                v.setD(u.getD() + weight(u, v));
                v.setPi(u);
        }


    }

    public int weight(Node u, Node v) {
        return weights[u.getValue()][v.getValue()];

    }

    public void initializeSingleSource(Node[] g, Node s) {
        //sets all distance values of each node to infinity and the pi (aka parent node) to null.
        for (int i = 0; i < g.length; i++) {
            g[i].setD(2000000);
            g[i].setPi(null);
        }
        s.setD(0);//set source distance to 0 because it is the starting point
    }

    public int[][] john(Node[] g, boolean negativeCycle) {
        if (negativeCycle) {
            return null;
        } else {
            int[][] bigD = new int[g.length][g.length];//will be the all-pairs shortest paths
            //run dijkstra once from each vertex to determine shortest paths between all pairs of vertices
            //changing the inner loop to only run on half of matrix (since it is undirected)
            for (int i = 0; i < g.length; i++) {
                dij(g, g[i]);
                for (int j = i; j < g.length; j++) {
                    if (i == j) {
                        bigD[i][j] = 0;
                    } else {
                        bigD[i][j] = g[j].getD();
                        bigD[j][i] = g[j].getD();

                    }
                }
            }
            return bigD;
        }


    }

    public int[][] johnNew(Node[] g, int newNode) {
        //We have already checked for a negative cycle in the main method, so no need to do that here
        int[][] bigD = new int[g.length][g.length];
        //Running dijkstra ONLY on the new nodes
        for (int i = g.length - newNode; i < g.length; i++) {
            dij(g, g[i]);
            for (int j = 0; j < g.length; j++) {
                if (i == j) {
                    bigD[i][j] = 0;
                } else {
                    bigD[i][j] = g[j].getD();
                    bigD[j][i] = g[j].getD();
                }
            }
        }
        return bigD;

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
                relax(u, u.getNeighbors().get(j));
            }

        }


    }

    public static void main(String[] args) {
        String result = "";
        boolean negativeCycle = false;//if any weight under 0 is encountered, we have a negative cycle (explained in readme)
        Scanner s = null;
        try {
            s = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
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
        while (row < m) {
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
                if (Integer.parseInt(temp) < 0) {//if we encounter any new weight which is less than 0, we know we have a negative cycle.
                    negativeCycle = true;
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
                if (Integer.parseInt(line) < 0) {
                    negativeCycle = true;
                }
                if (weights[row][col] != 0 && weights[row][col] != 2000000) {
                    neighbors.add(g[col]);
                }
            }
            g[row].setNeighbors(neighbors);
            row++;
            col = 0;
        }
        JohnsonNew j = new JohnsonNew();
        //running Johnson's algorithm on graph g
        int[][] bigD = j.john(g, negativeCycle);
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
        } else if (s.hasNextLine()) {//if there is a negative cycle in the first adjacency matrix, there will be one in the second too
            result = "Negative cycle\nNegative cycle";
        } else {
            result = "Negative cycle";
        }
        //rerunning for new node(s) in larger matrix
        if (s.hasNextLine() && bigD != null) {
            negativeCycle = false;
            line1 = s.nextLine();
            copy = line1;
            int newNode = 0;
            while (line1.indexOf(" ") != -1) {//determining new adjacency matrix size
                line1 = line1.substring(line1.indexOf(" ") + 1);
                newNode++;
            }
            if (line1.indexOf(" ") == -1 && !line1.isEmpty()) {
                newNode++;
            }
            newNode -= m;//the amount of new nodes added
            m += newNode;//the new adjacency matrix size, as well as the new graph size
            weights = new int[m][m];
            Node[] gNew = new Node[m];
            //getting old values from running Johnson's the first time into resized arrays
            for (int i = 0; i < bigD.length; i++) {
                gNew[i] = g[i];
                for (int k = i; k < bigD[0].length; k++) {
                    weights[i][k] = bigD[i][k];
                    weights[k][i] = bigD[k][i];
                }
            }
            //creating new nodes and adding them to the graph
            for (int i = 0; i < newNode; i++) {
                Node nTemp = new Node();
                nTemp.setValue(i + (m - newNode));
                gNew[i + (m - newNode)] = nTemp;
            }
            while (s.hasNextLine()||row == m - newNode) {
                String line;
                if (row == m - newNode) {//if this is the first row of the new adjacency matrix, copy must be used as the first line since the original was destroyed counting the new nodes
                    line = copy;
                } else {
                    line = s.nextLine();
                }
                neighbors = new ArrayList<>();
                ArrayList<Node> neighborsRev;//because this is an undirected, if an old node is a neighbor of a new node, the new node must also be a neighbor of the old node.
                while (line.indexOf(" ") != -1) {
                    String temp = line.substring(0, line.indexOf(" "));
                    line = line.substring(line.indexOf(" ") + 1);
                    weights[row][col] = Integer.parseInt(temp);
                    weights[col][row] = Integer.parseInt(temp);
                    if (Integer.parseInt(temp) < 0) {//if we encounter any new weight which is less than 0, we know we have a negative cycle.
                        negativeCycle = true;
                    }
                    if (weights[row][col] != 0 && weights[row][col] != 2000000) {
                        neighbors.add(gNew[col]);
                        if (col <= newNode) {//set new node as neighbor of old nodes
                            neighborsRev = gNew[col].getNeighbors();
                            neighborsRev.add(gNew[row]);
                            gNew[col].setNeighbors(neighborsRev);
                        }
                    }
                    col++;
                }
                if (!line.isEmpty()) {
                    weights[row][col] = Integer.parseInt(line);
                    weights[col][row] = Integer.parseInt(line);
                    if (Integer.parseInt(line) < 0) {
                        negativeCycle = true;
                    }
                    if (weights[row][col] != 0 && weights[row][col] != 2000000) {
                        neighbors.add(gNew[col]);
                        if (col <= newNode) {
                            neighborsRev = gNew[col].getNeighbors();
                            neighborsRev.add(gNew[row]);
                            gNew[col].setNeighbors(neighborsRev);
                        }
                    }
                }
                gNew[row].setNeighbors(neighbors);
                row++;
                col = 0;
            }

            result += "\n";
            if (negativeCycle) {
                result = result + "Negative cycle";
            } else {
                //run new johnson algorithm on just the new nodes
                int[][] bigDNew = j.johnNew(gNew, newNode);

                //check to see if new nodes provide shorter paths than the old nodes. Add smallest path to the new bigD.
                for (int a = 0; a < m - newNode; a++) {
                    for (int b = a; b < m - newNode; b++) {
                        bigDNew[a][b] = (int) (Double.POSITIVE_INFINITY);
                        for (int c = m - newNode; c < gNew.length; c++) {
                            if ((bigDNew[a][c] + bigDNew[c][b]) < bigD[a][b]) {
                                bigDNew[a][b] = bigDNew[a][c] + bigDNew[c][b];
                                bigDNew[b][a] = bigDNew[a][b];
                                bigD[a][b] = bigDNew[a][b];
                                bigD[b][a] = bigDNew[a][b];
                            }
                        }
                        if (bigDNew[a][b] == (int) (Double.POSITIVE_INFINITY)) {//if bigDNew[a][b] still equals positive infinity, then we know it has not been changed. This means the new nodes did not provide a shorter path, so set these values to the old bigD values.
                            bigDNew[a][b] = bigD[a][b];
                            bigDNew[b][a] = bigD[a][b];
                        }
                    }
                }
                //add second all pairs shortest paths matrix to the result
                for (int i = 0; i < bigDNew.length; i++) {
                    for (int k = 0; k < bigDNew.length; k++) {
                        if (k != bigDNew.length - 1) {
                            result += bigDNew[i][k] + " ";
                        } else {
                            result += bigDNew[i][k] + "";
                        }
                    }
                    if (i != bigDNew.length - 1) {
                        result += "\n";
                    }
                }

            }

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
