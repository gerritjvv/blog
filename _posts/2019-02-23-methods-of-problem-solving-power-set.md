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
  4. Maybe write out transformations in pseudo code form
     * Here we might draw several Hypothesis and try them out, its through trial and error that we normally find good transformations
  5. Reflect on similarities, patterns, commonalities
  6. And repeat
     * Repeat normally at step 3, but a repeat of step 1 or 2 might be needed several times
  

The method is an open cycle that is only stopped once we feel comfortable (objective) that we:
  * understand the problem at hand
  * the processes defined reaches a solution
  * and that the processes are repeatable for any similar problem, i.e we can generalize it


## Step 1

Write down the problem statement and desired solution:

```
 PowerSet of S={a, b, c}
 
 Is: {a}, {a, c}, {a, b, c}, {a, b}, {b}, {b, c}, {c}, {}
 
 The length of a power set is 2^n where n = len(S)

```
 
 Readup on:
   * https://en.wikipedia.org/wiki/Power_set
   * https://www.geeksforgeeks.org/power-set/
   

## Step 2 & 3


We can observe that the solution involves one of set combinations, i.e somewhere we had to start at
all the combinations of a, b, c and then narrow down to the final solution.

The number of all combinations of a list is 2^n, in our case its 2^3 = 8.

So we write it down:

```
1 = | a, b, c |
2 = | b, b, c |
3 = | b, b, b |
4 = | c, b, c |
5 = | c, c, c |
6 = | a, c, c |
7 = | a, b, b |
8 = | a, a, a |
```

And we count them to make sure there's no error.

If we take from the description that the powerset made up of sets of S. We can try to apply a "set" transformation to each
combination.

```
1 = | a, b, c | => Set => {a, b, c}
2 = | b, b, c | => Set => {b, c}
3 = | b, b, b | => Set => {b}
4 = | c, b, c | => Set => {b, c}
5 = | c, c, c | => Set => {c}
6 = | a, c, c | => Set => {a, c}
7 = | a, b, b | => Set => {a, b}
8 = | a, a, a | => Set => {a}
```

We notice that item #2 and #4 are the same set so we eliminate one.

```
1 = | a, b, c | => Set => {a, b, c}
2 = | b, b, b | => Set => {b}
3 = | c, b, c | => Set => {b, c}
4 = | c, c, c | => Set => {c}
5 = | a, c, c | => Set => {a, c}
6 = | a, b, b | => Set => {a, b}
7 = | a, a, a | => Set => {a}
```

This together with the empty set `{}` gives us the power set.


  

# By finding patterns from other domains

# By recursive functions bottom up

```clojure

```