#README Problem 4

** CODE DESCRIPTION **
In order to  find all pairs shortest paths, Johnson's algorithm must be implemented. For my implementation, I began by parsing through the given adjacency matrix to make my graph g. This involves determining the adjacency matrix size (size m), filling it with the proper weights, creating m new nodes, and assigning the correct neighbors based off of the adjacency matrix.
After graph g is made, Johnson's algorithm is called on g. Johnson's algorithm begins by creating a super node, which connects to all other nodes with a weight of 0. This is then added (along with all the other nodes) to a new graph entitled gPrime. After the new graph is made, bellman-ford algorithm is called on gPrime and the super node. Bellman-Ford is similar to Dijkstra in that it computes the shortest paths from the source node to all other nodes, but it differs in that it is able to detect a negative cycle. A negative cycle occurs when a cycle exists that has a negative total weight, meaning there is no shortest path for this cycle. The total weight would keep decreasing upon each traversal of the cycle. Thus, the algorithm cannot continue if a negative cycle exists.
Given that no negative cycle exists, we calculate new weight values for gPrime so that they will all be non-negative. This way we can use dijkstra to find all pairs shortest paths. Thus, for each node in g, we run dijkstra on it and saves its shortest paths in a nested array (as well as "unweighting" the new weight values we calculated previously in order to obtain non-negative weights). Thus we are left with a new matrix of all pairs shortest paths.
I also used the pseudo-code found in the class text as a basis for some of my methods.
For a more detailed explanation of dijkstra's algorithm, please see my problem 3 readme.txt file.
(Additional descriptions may be found in the .java file comments)

** ASSUMPTIONS **
-I am assuming that like the sample given, the input passed in will simply contain an adjacency matrix in the form of:
0 2000000 4
2 0 7
2000000 3 0
-I am also assuming that 2000000 represents infinity for the weights between nodes.
-Since I am using my priority queue from the previous programming assignment, I assume that it is still fine to create an array of size 1000000 for usage here. In other words, I assume that we will not be passed greater than one million nodes in a graph.

** HOW TO RUN **
As long as the input text file is in the same folder as the .java files and .sh file, the .sh file may be ran from the terminal using the command ./run.sh followed by the name and extension of the input text file (i.e. ./run.sh input.txt). If permission is denied, chmod 700 run.sh must be entered first.
output.txt will be the matrix of all pairs shortest paths obtained from Johnson's algorithm.