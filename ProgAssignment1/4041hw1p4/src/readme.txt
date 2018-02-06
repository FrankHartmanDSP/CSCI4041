#README Problem 4

** CODE DESCRIPTION **
For my implementation of a recursive BucketSort, I made the buckets of type ArrayList<String>, and the data structure that holds the buckets of type ArrayList<ArrayList<String>>. After parsing all the Strings passed to me in the input to an ArrayList<String>, I call bucketSort on this. bucketSort then goes through and arranges all of these strings into 26 separate buckets based on their first letter (so A goes in bucket 1, B in 2, etc.). Then, a for loop goes through each bucket, and if it contains more than 10 elements, recursively calls bucketSort to split it into sub-buckets. The offending bucket is then removed, and the sub-buckets are inserted back into the ArrayList<ArrayList<String>> starting at the index of the offending bucket. If a bucket contains more than 1 element, insertionSort is called on it so that it is sorted correctly. Thus, when bucketSort finished, we have a sorted ArrayList of ArrayList<String>>. Finally, I write this to the file, excluding any empty buckets.
(Additional descriptions may be found in the .java file comments)

** ASSUMPTIONS **
-I am assuming that no more than 10 of the same String will be passed in the input as this will lead to infinite recursion.
-I am also assuming that the input will be given as a single String, containing names with a capital first letter followed by a space. For example:
Candy Mahesh Rishi Svetovid

** HOW TO RUN **
As long as the input.txt is in the same folder as the .java files and .sh file, the .sh file may be ran from the terminal using the command ./run.sh (but if permission is denied, chmod 700 run.sh must be entered first)
output.txt will be the sorted names separated by spaces