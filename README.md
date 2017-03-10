# poker-hand-sorter
You are to build a command line program that takes, via STDIN, a "stream" of hands for a two player poker game. At the completion of the stream, your program should print to STDOUT the number of hands won by Player 1, and the number of hands won by Player 2.

# How to build
Simply build the .java using javac Sorter.java

Afterwards you should use piping to run the program passing the file poker-hands.txt through stdin, using the terminal or prompt.

On linux:
$ cat poker-hands.txt | java Sorter

On Windows:
type poker-hands.txt | java Sorter
