#README Problem 1

** CODE DESCRIPTION **
For my implementation of a Finite Automata string matcher, I begin by creating my sigma (or finite input alphabet). To do this, I create an ArrayList which contains every character encountered in the input string. Then, I use this sigma and the input pattern to compute my transition function. The transition function is used to examine the current character and determine based on the previous transition function computations what "state" we are at in matching the whole pattern. This is a nested array where the first dimension corresponds to the current state and the second dimension corresponds to the current character. Therefore, when the current state q and current character i are passed in, the int at transFunc[q][i] is the appropriate next state.
When the current state equals the pattern length, we know that we have found the whole pattern in the string. Therefore, I add the index of this occurrence to an arraylist, and after performing the same process on the reversed pattern, return all the indexes in order of least to greatest.
I also used the pseudo-code found in the class text as a basis for some of my methods.
(Additional descriptions may be found in the .java file comments)

** ASSUMPTIONS **
-I am assuming that like the sample given, the input passed will be the pattern on the first line and the string we are searching in on the second line.
-I am also assuming that the input given will only consist of the 26 lowercase alphabet characters.

** HOW TO RUN **
As long as the input text file is in the same folder as the .java files and .sh file, the .sh file may be ran from the terminal using the command ./run.sh followed by the name and extension of the input text file (i.e. ./run.sh input.txt). If permission is denied, chmod 700 run.sh must be entered first.
output.txt will be the indexes of the pattern occurances in order of least to greatest.