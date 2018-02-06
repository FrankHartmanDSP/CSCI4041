#README Problem 2

** CODE DESCRIPTION **
In order to return the valid order of all the courses given their prerequesites, I used depth first search and treated all of the prerequistes as parents of the courses they are required for. In other words, if a course A requires course B as a prerequiste, then A is a neighbor of B.
After setting all the neighbors, we now have a directed graph g of all the courses (with prerequistes pointing to the classes they are required for). Running depth first search on this first sets the "color" of all class nodes to white, meaning they have not been visited. Then, each node in g that is white is visited via the dfsVisit method. Within dfsVisit, the current node is set to gray (meaning it is currently being visited), and then all of its neighbors are visited via recursive calls to dfsVisit. Then, the nodes color is set to black, meaning it has finished being visited. Finally, the finish time is incremented and recorded.
This way, the function will finish visiting the parent courses after its neighbors, giving all prereqs higher finish times than the course(s) they are prereqs for. Thus, we are able to sort the course nodes by finish time (largest to smallest) and they will be in a valid order.
I also used the pseudo-code found in the class text as a basis for some of my methods.
(Additional descriptions may be found in the .java file comments)

** ASSUMPTIONS **
-I am assuming that like the sample given, each course will be on its own line, followed by a colon, followed by any prerequistes separated by a space. For example,
1001:
1133:
2011:
1933:1133
4041:1933 2011
-I am also assuming that there are no circular prerequistes.

** HOW TO RUN **
As long as the input text file is in the same folder as the .java files and .sh file, the .sh file may be ran from the terminal using the command ./run.sh followed by the name and extension of the input text file (i.e. ./run.sh input.txt). If permission is denied, chmod 700 run.sh must be entered first.
output.txt will be a possible order of the courses (each separated by a space).