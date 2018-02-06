#README Problem 3

** CODE DESCRIPTION **
In order to return the shortest path from a node u to another node v in a directed graph g, I used Dijkstra's algorithm. First, I determine the source and destination nodes from the first line of the input, then I parse through the following adjacency matrix to make my graph g. In order to keep track of the weights between nodes, I used a nested array where the first dimension represents node u and the second represents node v.
After making the graph g and storing the correct neighbors and weights, a call to dijkstra is made using g and the source node. This begins by setting the distance values of each node to infinity and their parents (pi value) to null (except for the source node, whose distance value is set to 0). Then, all the nodes are inserted into a min priority queue (based on distance values), and extract min is called until the queue is empty. Thus, the first node pulled out of the queue will be the source node.
For each of the extracted node's neighbors, a call to relax is made using the parent node u and child node v. Relax checks to see if the d value at u plus the weight from u to v is less than the d value of v, and if it is, it resets v's d value to (u.getD() + weight(u,v)) and resets v's pi to be u. This is currently the shortest path to v, since the shortest path to u has already been figured out and we know the weight from u to v. Furthermore, the usage of the min priority queue always ensures we are determining the path with the shortest distance.
This process continues until the queue is empty. Thus, the d values of each node are now the correct shortest path from the source node. All that must be done now is print of the pi values starting at the distance node until pi is null.
I also used the pseudo-code found in the class text as a basis for some of my methods.
(Additional descriptions may be found in the .java file comments)

** ASSUMPTIONS **
-I am assuming that like the sample given, the first line will be the source and destination nodes separated by a space. Following this will be the weights/adjacency matrix. For example:
0 1
0 2000000 4
2 0 7
2000000 3 0
-I am assuming no negative weights will be given in the adjacency matrix.
-I am also assuming that 2000000 represents infinity for the weights between nodes.
-Since I am using my priority queue from the previous programming assignment, I assume that it is still fine to create an array of size 1000000 for usage here. In other words, I assume that we will not be passed greater than one million nodes in a graph.

** HOW TO RUN **
As long as the input text file is in the same folder as the .java files and .sh file, the .sh file may be ran from the terminal using the command ./run.sh followed by the name and extension of the input text file (i.e. ./run.sh input.txt). If permission is denied, chmod 700 run.sh must be entered first.
output.txt will be the shortest path distance from the source node to the destination node, followed by a colon, followed by the actualy path where each node is separated by a space.