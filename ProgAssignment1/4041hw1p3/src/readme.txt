#README problem 3

** CODE DESCRIPTION **
For my implementation of Select(i), I used two methods: select and partition. The basis of these two functions was inspired by the pseudo-code found in the class text. Partition is essentially a partial application of QuickSort, since it selects a pivot as the last element, and ends with the pivot sorted in its final place with all elements smaller to the left of its index and all element larger to the right of its index. Therefore, select takes the position found in partition and only searches down the left or right half of the array depending on whether the ith element we are selecting is less than or equal to that of the pivot's position. If the position is equal to i, we know we have found the ith element and we return.
(Additional descriptions may be found in the .java file comments)

** ASSUMPTIONS **
-I am assuming that like the sample given, the input passed will have the single integer value of the element at the ith position we are selecting on the first line, and the array we are selecting in on the second line (in the format of) INTEGER SPACE INTEGER SPACE...etc. For example:
7
4 6 3 -10

** HOW TO RUN **
As long as the input.txt is in the same folder as the .java files and .sh file, the .sh file may be ran from the terminal using the command ./run.sh (but if permission is denied, chmod 700 run.sh must be entered first)
output.txt will be a single integer, which is the number found at the ith position given in the input.