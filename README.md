# Hill Climbing

This is a java based implementation of two different versions of the Hill Climbing local search/optimization algorithm:
the classic Hill Climb and the Hill Climb With Random Restarts.
 
It is written in such a way that users implement the details about the potential solutions(state representation and 
scoring method) used in the local search problem and the supplied implemented algorithms perform the local 
search/optimization.


## Local Search/Optimization

Local searches attempted to find an optimal solution from a set of potential solutions. Given an initial potential
solution(random guess or educated guess) we generate a set of potential solutions by making local changes to the initial
potential solution. We evaluate these new potential solutions and score them to determine how optimal they are. We then
select the best potential solution(selecting best is dependent on the search algorithm). We then generate the next set
of potential solutions from the best potential solution that was just found. The process of generating new potential
solutions, scoring them, and selecting the best continues until a optimal solution is found or some termination 
condition is met.


## Hill Climbing and Hill Climbing With Random Restarts

The Hill Climbing algorithm and its variants are probably the most simple local search algorithms. In Hill Climbing the
the potential solution with the highest/lowest score is selected to continue the algorithm. Simply at each phase we 
select the best then continue the search using the best.

Normal Hill Climbing is very simple but suffers from two issues:
1. Local Maxima/Minima
2. Plateaus

Since Hill Climbing only makes local changes it is possible that the algorithm will get stuck in a local maxima/minima.
Local maxima/minimas are maximums or minimums that exist in a specified area but are not the global maximum or minimum.
You can imagine a function that has two peaks with one higher that the other. Depending on your initial starting point, 
it is possible that the algorithm will stop searching at the shorter peak. When the algorithm reaches a peak it can't
continue because the next set of potential solutions will not be better than the current.

Hill Climbing has a similar issue when a plateau is reached. When a plateau is reached at least one potential solutions 
will have a score equal to the current optimal solution while the others are worse. Since the new potential solutions 
are not better, then search will stop. If you are lucky the plateau will occur at the maximum/minimum, otherwise the
algorithm will stop before finding the true maximum/minimum.

To alleviate these problems a variant of Hill Climbing was created called Hill Climbing With Random Restarts. First,
we introduce two termination conditions: a desired optimal value and a threshold on the number of iterations the 
algorithm can run. Now when a solution is found with the desired optimal value the algorithms execution is stopped. If
a maxima/minima or plateau is found(that's not the desired optimal value) we randomly create a new state and begin hill
climbing at the new state. The best solution found over all searches is kept in case the desired optimal value is not
found. Restarting the search with a new random state allows the Hill Climbing algorithm to escape getting stuck in local
maxima/minima and plateaus.


## How To Use

The HillClimb and HillClimbRandRestart classes are responsible for performing the local search/optimization using the 
respective algorithm they are named after. Both classes must be provided an initial state(potential solution) and a set
of parameters used by the algorithms. Initial states(potential solutions) must be a class that implements 
IHillClimbProblem. This means each problem you want to be solved with one of the Hill Climbing algorithms will be 
represented by a class that implements IHillClimbProblem. The IHillClimbProblem has methods to score the state
(potential solution), get/set its score, and generate the next potential solutions. If HillClimbRandRestart is to be 
used, then a class that implements IHillClimbProbGenerator must be created which is responsible for creating random
states(potential solutions).

Inorder to perform a local search using HillClimb or HillClimbRandRestart you must:
1. Create a class that implements IHillClimbProblem which represents the problem being solved
2. Create a instance of HillClimbParams and specify algorithm parameters
3. (Optional) -  Create a class that implements IHillClimbProbGenerator if HillClimbRandRestart is being used
4. Create an instance of HillClimb or HillClimbRandRestart
4. Create an instance of HillClimb or HillClimbRandRestart
5. Call the optimize() method on HillClimb or HillClimbRandRestart to being optimization 

Below are some implemented local search problems to follow as examples.


## Implemented Local Search Problems

Each of these local search problems' have a demo implemented as a main method in their associated IHillClimbProblem 
implementations.


### NQueens

Given a N x N chess board containing N queens(restrict a single queen per column for simplicity), find an arrangement
queens on the board such that no queen is in conflict. Queens are in conflict if they may take another, that is if two 
queens are in the same row or are diagonal of one another. For the supplied implementation we limit the search's 
branching factor by only allowing one queen to be moved at a time. For a given NQueens board we start by iterating over
the columns. In each column we look at each new potential solution that can be generated by moving the queen in that
column to rows it does not currently occupy. Since we iterate over each column and each row currently not occupied in
that column, we end up with a N * (N-1) branching factor. That is, N * (N-1) potential solutions are generated on each
iteration.
