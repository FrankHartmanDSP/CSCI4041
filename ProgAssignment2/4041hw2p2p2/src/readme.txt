#README Problem 5

** CODE DESCRIPTION **
For the first of two possible adjacency matricies we are passed in this problem, Johnson's algorithm is ran on it similar to problem 4 to return all pairs shortest paths. However, the fact that the adjacency matrix is undirected makes a few things much simpler. First of all, we know that if any negative weight is encountered at any point, there is a negative cycle. This is because the graph is undirected, and if there is a negative edge from node u to node v, then there is also a negative edge from node v to node u. Thus, you can continually bounce between the two nodes searching for the shortest path. So with this knowledge, we know we don't need to run bellman-ford at all, but instead must check for any negative weights in the adjacency matrix.
Furthermore, since we don't need to run bellman-ford, we also don't need to calculate the super node/gPrime necessary for this method. The absence of negative weights also means that we do not need to reweight at any point.
So for the first adjacency matrix, Johnson's simply runs dijkstra for each node in g and saves its shortest paths to the other nodes in a nested array.
For the second of the two possible adjacency matrices, we only need to run dijkstra for the new nodes (using the new graph g containing all nodes). Also, the same principle of negative weights meaning a negative cycle follows for this matrix since it is also undirected.
The final step is then checking whether the pairs shortest paths of the new nodes lead to shorter paths for any of the old nodes. If they do, update the old paths with the new ones. 
I also used the pseudo-code found in the class text as a basis for some of my methods.
For a more detailed explanation of dijkstra's algorithm, please see my problem 3 readme.txt file.
(Additional descriptions may be found in the .java file comments)

** ASSUMPTIONS **
-I am assuming that like the samples given, the input passed in will contain at most 2 adjacency matricies, with the first adjacency matrix passed, followed by a list of new vertexes in the format of their corresponding rows in the adjacency matrix. For example,
0 2 1
2 0 4
1 4 0
2000000 4 6 0 1
2 1 3 1 0
-I am also assuming that 2000000 represents infinity for the weights between nodes.
-Since I am using my priority queue from the previous programming assignment, I assume that it is still fine to create an array of size 1000000 for usage here. In other words, I assume that we will not be passed greater than one million nodes in a graph.

** HOW TO RUN **
As long as the input text file is in the same folder as the .java files and .sh file, the .sh file may be ran from the terminal using the command ./run.sh followed by the name and extension of the input text file (i.e. ./run.sh input.txt). If permission is denied, chmod 700 run.sh must be entered first.
output.txt will be the matrix of all pairs shortest paths obtained from Johnson's algorithm for the first adjacency matrix, possibly followed the second matrix obtained from running Johnson's algorithm on the larger adjacency matrix.