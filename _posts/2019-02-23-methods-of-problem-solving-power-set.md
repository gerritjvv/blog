---
layout: post
title:  "Methods of problem solving - Power Set"
author: gerritjvv
categories: [ problem-solving, reasoning]
image: assets/images/aes-encryption-keys-password-hashing.jpg
featured: true
---


# Overview

In this post I explore several methods of solving the same problem. The interesting part here is not the actual problem 
but the method used to solve it.

I think of method here as a path towards a solution. So from now on I'll refer to problem solving as solution finding.

## Why?

We learn mostly to solve problems by practice, which is good, but we rarely stop to think about the actions taken in the solution finding.

By not taking the time to reflect on the actual process we risk making solution finding an almost by chance or random action. We know how to solve X
but how to solve y and z?. There is one big risk here though, by making a process out of something we risk making it dull i.e non creative
and avoid looking at other angles which my lead to even better solutions. There is never a silver bullet unfortunately.  


## Power set

As an example I'll use the problem of generating the [Power Set](https://en.wikipedia.org/wiki/Power_set) of any given set, to show and study
how different methods can be applied in solving it.

 For any set S, the power set is all the subsets of S.
 For example:
 
 ```
 PowerSet of S={a, b, c}
 
 Is: {a}, {a, c}, {a, b, c}, {a, b}, {b}, {b, c}, {c}, {}
 
 The length of a power set is 2^n where n = len(S)
 ```
 
# By visual observation and discovery

This is the most common means of approaching solution finding. We try to model the elements and processes of the problem and then
visualise what transformations needs to be applied to reach the solution.

Here the following items are important:

  * [Abstraction](https://en.wikipedia.org/wiki/Abstraction)
  * Visual diagrams or drawings
  * Notation either mathematical or pseudo code 

The method:
  
  1. Write down the problem statement, and the desired solution  
     * In this step research is important. Study the problem space, check for existing solutions and studies on solutions.
     * Search engines, books, papers, existing open source code can help.
     * Be careful here not to immediately converge on a solution. Existing solutions can narrow our mindset down to a single
     solution and hinder us from exploring more.
   
  2. We divide the problem and solution into elements and processes  
  3. Visualise the different steps from starting state to the solution state
  4. Maybe write out transformations visually or in pseudo code form
     * Here we might draw several Hypothesis and try them out, its through trial and error that we normally find good transformations
  5. Reflect on similarities, patterns, commonalities
  6. And repeat
     * Repeat normally at step 3, but a repeat of step 1 or 2 might be needed several times
  

The method is an open cycle that is only stopped once we feel comfortable (objective) that we:
  * understand the problem at hand
  * the processes defined reaches a solution
  * and that the processes are repeatable for any similar problem, i.e we can generalize it

## Iteration 1 - N
### Step 1

Write down the problem statement and desired solution:

```
 PowerSet of S={a, b, c}
 
 Is: {a}, {a, c}, {a, b, c}, {a, b}, {b}, {b, c}, {c}, {}
 
 The length of a power set is 2^n where n = len(S)

```
 
 Readup on:
   * https://en.wikipedia.org/wiki/Power_set
   * https://www.geeksforgeeks.org/power-set/
   

### Step 2, 3, 4 & 5


We can observe that the solution involves one of set combinations, i.e somewhere we had to start at
all the combinations (permutations) of a, b, c and then narrow down to the final solution.

So we write it down all the different ways we can write `[a, b, c]`:

The total number of different ways a list `[a, b, c]` can be written down is:

```
N x N x N => n^n
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

And we count them to make sure there's no error, here we need 27.

If we take from the description that the powerset made up of sets of S. We can try to apply a "set" transformation to each
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

We notice that there are many duplicates, and we can learn or know that for two sets to be equal they only need
to contain the same elements, and order does not matter, i.e `{a, b} == {b, c}`.

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
We can test with:
 The number of sets should be 2^N - 1 which is `2^3 - 1 => 8 - 1 => 7` 

#### Note on steps:

It took me several iterations, a few errors and a jog on the treadmill to come to the final explanation above.
Solution finding is a search problem where you can run into many dead ends or work on false or errored calculations.

Its important to lay down your thinking in steps, work through the results, rest, comeback and rework and so on.


## Iteration N + 1

Pseudo code time:


We can build from the discoveries above and write this down in simple processes:

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

If not then we have to think up a way to generate all the combinations for any set we need:

So we start a new thought process and go back to step 1 again:

### Step 1 -- combinations

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
    a list of n indices `[x, y, z]`, the value of each index points to the letter in the list `[a, b, c]`.  
    
  We iterate over all the possible values of z from 0 to 2, and when we reach 2 we increment y and set z = 0, if y is == 2
  we increment x and set y to 0. When x > 2 we stop.
  
  ```
   [a, a, a]  => Indices => [0, 0, 0]
   [a, a, b]  => Indices => [0, 0, 1] 
   [a, a, c]  => Indices => [0, 0, 2]
     
   [a, b, a]  => Indices => [0, 1, 0]
   [a, b, b]  => Indices => [0, 1, 1]
   [a, b, c]  => Indices => [0, 1, 2]
     
   [a, c, a]  => Indices => [0, 3, 0]
   [a, c, b]  => Indices => [0, 0, 1]
   [a, c, c]  => Indices => [0, 0, 2]
        
   [b, a, a]  => Indices => [1, 0, 0]
  ```

### pseudo code

  For a set S the possible combinations with order and repetitions are:
    l = list of integers at 0 of len S
    
   ```
    l = createIndicesAtZero(len = len(S) )
    maxI = len(S) - 1
          
    while l[0] < len(S):
        
      if l[maxI] > maxI:
          ;; when we reach the max counter value, scan backwards carrying over the 1 increment
             
          ;; scan through all indices except l[0], l[i-1] will increment it
          for i = maxI; i > 0; i--: 
            if l[i] > maxI:
               l[i-1]++ ;; increment next
               l[i] = 0 ;; reset
      else:
        print copyFromIndices( S, l )
        l[maxI]++
            
   ```
   
Note: After having worked a few times over the pseudo code and some simulations, we can try out writing this in 
our language of choice. The thought process is not over yet, expect bugs and several iterations before reaching
a solution.




Important show:

Elaborate on the above in step notation

also in set notation
S = { {x, y, z} | x ∈ {a, b, c}, y ∈ {a,b,c}, z ∈ {a,b,c}, {x,y,z} ∉ S }  

# By finding patterns from other domains

# By recursive functions bottom up

```clojure

```