---
layout: post
title:  "Methods of problem solving - Power Set (1)"
author: gerritjvv
categories: [ problem-solving, reasoning]
image: assets/images/volodymyr-hryshchenko-inI8GnmS190-unsplash.jpg
---


# Overview

In this post series I explore several methods of solving the same problem using an iterative process.     
The interesting part here is not the actual problem but the process used to solve it.  


I chose the power set of a set as the problem to solve, because it seemed interesting, and is normally far from the every
day problems we programmers need to solve. Why on earth would you choose that then? you might ask. Well its simple, it is
a programming problem, but being something that we do not frequently solve, we cannot just pull a solution from memory.

**Note on performance**

The solution devised here, work well for small numbers of `n < 7`. 
This is because its based of generating all possible combinations and thus has a complexity of `O(n^k)`.

I do strongly believe that is is more important to first understand the problem at hand, and have analysed it
from multiple angles, before trying to design the most optimum solution. People might agree or not agree, but the focus
of this article is "methods of thinking". We will at a later stage go over complexity analysis. 


## Why study the actual process?

We learn mostly to solve problems by practice, which is good, but we rarely stop to think about the actions taken in the solution finding.

By not taking the time to reflect on the actual process we risk making solution finding an almost by chance or random action. We know how to solve X
but how to solve Y and Z ?.  


## Solving for the Power set

As an example I'll use the problem of generating the [Power Set](https://en.wikipedia.org/wiki/Power_set) of any given set, to show and study
how different methods can be applied in solving it.
 
# By visual observation and discovery

This is the most common means of approaching solution finding. We try to model the elements and processes of the problem and then
visualise what transformations needs to be applied to reach the solution.

We make use of the following tools:

  * [Abstraction](https://en.wikipedia.org/wiki/Abstraction)
  * Visual diagrams or drawings
  * Notation, either mathematical or pseudo code 

The method:
  
  1. Write down the problem statement, and the desired solution.  
     * In this step research is important. Study the problem space, check for existing solutions and studies on solutions.
     * Be careful here not to immediately converge on a solution. We are exploring.
   
  2. Divide the problem and solution into elements and processes.  
  
  3. Visualise the different steps from starting state to the solution state.
  
  4. Maybe write out transformations visually or in pseudo code form.
     * Here we might draw several Hypothesis and try them out, its through trial and error that we normally find good transformations.
  
  5. Reflect on similarities, patterns, commonalities.
  
  6. And repeat.
     * Repeat normally at step 3, but a repeat of step 1 or 2 might be needed several times.
  

The method is an open cycle that is only stopped once we feel comfortable that we:
  * Understand the problem at hand.
  * The processes defined reaches a solution.
  * And that the processes are repeatable for any similar problem.

## Iteration 1
### Step 1

Write down the problem statement and desired solution:

```

 For any set S, the power set is all the subsets of S.
 For example:

 PowerSet of S={a, b, c}
 
 Is: {a}, {a, c}, {a, b, c}, {a, b}, {b}, {b, c}, {c}, {}
 
 The length of a power set is 2^n where n = len(S)

```
 
 Readup on:
   * https://en.wikipedia.org/wiki/Power_set
   * https://www.geeksforgeeks.org/power-set/
   

### Step 2, 3, 4 & 5


We can observe that the solution involves one of combinations/permutations, i.e somewhere we had to start at
all the combinations of a, b, c and then narrow down to the final solution.

So we write down all the different ways we can write `[a, b, c]`:

The total number of different ways a list `[a, b, c]` can be written down is:

```
n x n x n => n^n
Where n is the number of items in the list

i.e [a, b, c] can be written down 3 x 3 x 3 => 3^3 => 27 times.
```


```
0  = [ a, a, a ]
1  = [ a, a, b ]
2  = [ a, a, c ]
3  = [ a, b, a ]
4  = [ a, b, b ]
5  = [ a, b, c ]
6  = [ a, c, a ]
7  = [ a, c, b ]
8  = [ a, c, c ]

9  = [ b, a, a ]
10 = [ b, a, b ]
11 = [ b, a, c ]
12 = [ b, b, a ]
13 = [ b, b, b ]
14 = [ b, b, c ]
15 = [ b, c, a ]
16 = [ b, c, b ]
17 = [ b, c, c ]


18 = [ c, a, a ]
19 = [ c, a, b ]
20 = [ c, a, c ]
21 = [ c, b, a ]
22 = [ c, b, b ]
23 = [ c, b, c ]
24 = [ c, c, a ]
25 = [ c, c, b ]
26 = [ c, c, c ]

```

If we take from the description that the powerset is made up of sets of S. We can try to apply a "set" transformation to each
combination.

```
0  = [ a, a, a ]  => Set => {a}
1  = [ a, a, b ]  => Set => {a, b}  
2  = [ a, a, c ]  => Set => {a, c}
3  = [ a, b, a ]  => Set => {a, b}
4  = [ a, b, b ]  => Set => {a, b}
5  = [ a, b, c ]  => Set => {a, b, c}
6  = [ a, c, a ]  => Set => {a, c}
7  = [ a, c, b ]  => Set => {a, b, c}
8  = [ a, c, c ]  => Set => {a, c}

9  = [ b, a, a ]  => Set => {a, b}
10 = [ b, a, b ]  => Set => {a, b}
11 = [ b, a, c ]  => Set => {a, b, c}
12 = [ b, b, a ]  => Set => {a, b}
13 = [ b, b, b ]  => Set => {b}
14 = [ b, b, c ]  => Set => {b, c}
15 = [ b, c, a ]  => Set => {a, b, c}
16 = [ b, c, b ]  => Set => {b, c}
17 = [ b, c, c ]  => Set => {b, c}

18 = [ c, a, a ]  => Set => {a, c}
19 = [ c, a, b ]  => Set => {a, b, c}
20 = [ c, a, c ]  => Set => {a, c}
21 = [ c, b, a ]  => Set => {a, b, c}
22 = [ c, b, b ]  => Set => {b, c}
23 = [ c, b, c ]  => Set => {b, c}
24 = [ c, c, a ]  => Set => {a, c}
25 = [ c, c, b ]  => Set => {b, c}
26 = [ c, c, c ]  => Set => {c}
```

We notice that there are many duplicates, and we know that for two sets to be equal they only need
to contain the same elements, and order does not matter, i.e  
 
 `{a, b} == {b, c}`.

So we remove the duplicates: 

```
0  = [ a, a, a ]  => Set => {a}
1  = [ a, a, b ]  => Set => {a, b}  
2  = [ a, a, c ]  => Set => {a, c}
3  = [ a, b, c ]  => Set => {a, b, c}
4  = [ b, b, b ]  => Set => {b}
5  = [ b, b, c ]  => Set => {b, c}
6  = [ c, c, c ]  => Set => {c}
```

This together with the empty set `{}` gives us the power set.

We can check with:  

 The number of sets should be 2^N - 1 which is `2^3 - 1 => 8 - 1 => 7` 

#### Note on steps:

It took me several iterations, a few errors and a jog on the treadmill to come to the final explanation above.
Solution finding is a search problem where you can run into many dead ends or work on false or errored calculations.
This is normal and even a required part of the solution finding process.    

Its important to lay down your thinking in steps, work through the results, rest, comeback, rework and so on.
Do not rush. Solutions finding should be done at a walking pace, and never at a running pace.  

## Iteration N + 1

Pseudo code time:


We can build from the discoveries above and write this down in simple pseudo code:

1. Take a set
2. Generate all combinations 
3. Transform each combination into a Set
4. Remove Duplicates


```
For a set S, the power set P is:

c = combinations( S )
subsets = distinct( map(Set, c ) )
```


## Iteration N + 2

If your language of choice has a library to generate the combinations, and or would support some basic
functional programming the translation above is easy. 

But where is the fun in that? So I will assume there is none or that we just want to learn and see if we 
can apply the same process above to generating all combinations.


So we start a new thought process and go back to step 1 again:

### Iteration N -- combinations

#### Step 1 -- combinations


Problem statement:

  Generate all the possible combinations that items in a set of length n can take up in a list of length n.
  Order is important and repetition is allowed.
  
  e.g 
  ```
   {a, b, c} in the list [x, y, z] would be
   
   [a, a, a]
   [a, a, b]
   [a, a, c]
   
   [a, b, a]
   [a, b, b]
   [a, b, c]
   
   [a, c, a]
   [a, c, b]
   [a, c, c]
      
   [b, a, a]
   ...
      
  ``` 
     
### Step 2, 3, 4 & 5 -- combinations

  We can try and model the above with indices, where we have: 
   
    a list of n indices [x, y, z],
    
    the value of each index points to the letter in the list  
    [a, b, c]
    
    Where [0, 0, 0] means [a, a, a] and [1, 1, 1] means [b, b, b]   
    
  We iterate over all the possible values of z from 0 to 2:
    
    [x, y, 0]
    [x, y, 1]
    [x, y, 2]
            
   
  and when we reach 2 we increment `y` and set `z = 0`, if `y == 2`
  we increment `x` and set `y to 0`.  
  
  When `x > 2` we stop.
  
  ```
   [a, a, a]  => Indices => [0, 0, 0]
   [a, a, b]  => Indices => [0, 0, 1] 
   [a, a, c]  => Indices => [0, 0, 2]
     
   [a, b, a]  => Indices => [0, 1, 0]
   [a, b, b]  => Indices => [0, 1, 1]
   [a, b, c]  => Indices => [0, 1, 2]
     
   [a, c, a]  => Indices => [0, 2, 0]
   [a, c, b]  => Indices => [0, 0, 1]
   [a, c, c]  => Indices => [0, 0, 2]
        
   [b, a, a]  => Indices => [1, 0, 0]
   ...
   [c, c, c]  => Indices => [2, 2, 2]
      
  ```
### Iteration N + 1 -- combinations

Pseudo code

   ```
    indices = createIndicesAtZero(len = len(S) )
    maxI = len(S) - 1
          
    while indices[0] < len(S):
        
      if indices[maxI] > maxI:
          ;; when we reach the max counter value, scan backwards carrying over the 1 increment
             
          ;; scan through all indices except indices[0], indices[i-1] will increment it
          for i = maxI; i > 0; i--: 
            if indices[i] > maxI:
               indices[i-1]++ ;; increment next
               indices[i] = 0 ;; reset
      else:
        print copyFromIndices( S, indices )
        indices[maxI]++
            
   ```
   
Note: After having worked a few times over the pseudo code and some simulations, we can try out writing this in 
our language of choice. The thought process is not over yet, expect bugs and several iterations before reaching
a solution.



## Iteration N + 3 -- back to the Power Set

Implementing:

```
c = combinations( S )
subsets = distinct( map(Set, c ) )
```

You can find the implementation code with tests here:

  * [Java](https://github.com/gerritjvv/blog/tree/post-power-set/code/powerset/java/powerset)
  * [Clojure](https://github.com/gerritjvv/blog/tree/post-power-set/code/powerset/clojure/powerset)


# Summary 

So even though we might get lost in thought of the actual problem itself, I hope that I could show a simple
but practical method for solution finding itself.

There are other ways to visualise the problem and a multitude of solutions.
The ideal way is to study the solution with multiple visualisation techniques and look at it from various angles,

E.g If we look at the Power Set result, we can see that the actual subsets may be represented by binary numbers
that increase from zero to N.

Some of the techniques that can be used to explore any problem space are:

 * By finding patterns from other domains
 * By recursive functional approach
 * Tree presentations
 * Set constructors
 

These are topics for another blog post :).


