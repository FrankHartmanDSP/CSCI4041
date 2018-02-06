#README Problem 2

** CODE DESCRIPTION **
For my implementation of Huffman Coding, I resued my PriorityQueue from problem 1, but made it a min-priority queue rather than max. This allows me to extract the nodes with the smallest frequencies first, which is necessary to build the final tree in the proper order (with the highest frequency letters nearest the root). For the tree nodes, I used a class named Letter, which contains the character value, its frequency, and left and right children. However, since in the final tree only the leaves are actually letters and the rest are summations of frequencies, I used the character value 'F' to denote a node which is not a letter, but a summation of frequencies. Finally, I recursively figured out the binary representation of each letter given. To keep track of the priority queue size, I used the priority of the Letter node at index 0, since this index is never used in the priority queue.
(Additional descriptions may be found in the .java file comments)

** ASSUMPTIONS **
-I am assuming that like the sample given, the input passed will be a single string on one line.
-I am also assuming that only the 26 lowercase alphabet character may be passed to me in the input.
-I am also assuming that a left branch traversal equals 0, and a right branch traversal equals 1.

** HOW TO RUN **
As long as the input.txt is in the same folder as the .java files and .sh file, the .sh file may be ran from the terminal using the command ./run.sh (but if permission is denied, chmod 700 run.sh must be entered first)
output.txt will be the binary value of each letter according to Huffman coding.