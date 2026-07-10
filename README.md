# Mars Robot Driver Application
The mars robot application is used to determine the resultant coordinates of robots on mars.

The robot instructions are processed sequentially using an input file.

The file specifies : 
- The grid size representing Mars in the first line.
- The list of robots with their initial coordinates followed by their instructions

The file location is specified using the application properties:
input.file.path=test.txt

##Techinal Specifications
- Java 26
- Spring Boot

##Test Results

Input:

5 3
1 1 E
RFRFRFRF

3 2 N
FRRFLLFFRRFLL

0 3 W
LLFFFLFLFL

Output:

![File 1 Test Results](test-results/testfile1.png)