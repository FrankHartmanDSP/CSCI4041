#README Problem 1

** CODE DESCRIPTION **
For my implementation of the priority queue, I used an array of customer nodes which stored the name and priority of each customer in the member data. At the start of my program, I made a customer node array of size 1,000,000 as the professor said no inputs will be larger than this. To keep track of size, I used the priority of the customer node at index 0, since this index is never used in the priority queue. I also used the pseudo-code found in the class text as a basis for some of my methods.
(Additional descriptions may be found in the .java file comments)

** ASSUMPTIONS **
-I am assuming that like the samples given, the input passed will be a customer and priority on each line in the format of:
NAME : PRIORITY
-I am also assuming that there will not be more than 999,999 customers added to the queue (since my array is of size 1,000,000 and the 0 index is not used to store customers).
-Note that I also broke my priority ties in a different order than the example given, which I assume is fine.

** HOW TO RUN **
As long as the input.txt is in the same folder as the .java files and .sh file, the .sh file may be ran from the terminal using the command ./run.sh (but if permission is denied, chmod 700 run.sh must be entered first)
output.txt will be the names of the customers in the correct order as described by the priority queue.