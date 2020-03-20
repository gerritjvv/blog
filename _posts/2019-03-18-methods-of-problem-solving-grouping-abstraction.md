---
layout: post
title:  "Methods of problem solving - simplification"
author: gerritjvv
categories: [ problem-solving, reasoning]
image: assets/images/michael-dziedzic-dSyhpTGhNHg-unsplash.jpg
---

## Overview

In this post I'm going to explore a technique that I find useful when dealing with many many variables.
It's used an mathematics all the time and is quite simple.

There is only so much information the human brain can hold in one line of thought. So the idea is to group
several variables that form a single concept, lets call it A=`{v1, v2...}, into that concept A. Then in our design and reasoning
we can refer to that single concept A. 

## Example -- Solving for 3 points in a line

Lets explore a simple problem, but one that can lead to a complicated line of thought. 
We have a list of points, where each point has a x,y parameter. Find out if the list contains any 3 points that form
a straight line at any angle.

Tip: You can tackle the problem using [methods-of-problem-solving-power-set](https://gerritjvv.github.io/blog/methods-of-problem-solving-power-set/).
For brevity in the blog I'll skip a few steps.  

If the gradients of Points A and B, and Points B and C are the same, Points A, B, and C are all in a straight line.

**Naive first attempt**

We could write some code to keep track of the last point, and the for each new point calculate the gradient.
Also keeping track of the last gradient to use in the compare. But this becomes messy, and wastes brain cycles.

**Grouping and eliminating the non essential**

Now lets go back to our problem statement and our solution statement. 
If we have 2 gradients we can compare them. If they are the same, the points in those gradients are the same.

Lets forget about points first and just get Gradient1, and Gradient2. If they are the same we have 3 points on the same line.

This simplifies our reasoning quite a bit and our algorithm:

````python

If we have points [a, b, c ,d ,e ,f]

Calc G1 from a, b, and G2 b, c
Compare

Calc G1 from b, c and G2 c, d
Compare

Calc G1 from c, d and G2 d, e
Compare

Calc G1 from d, e and G2 e, f
Compare
````

We can see from the above that, we start at a point, then take 2 more, shift by one, and take two more,
until we either find two gradients that are the same, or till we can't take 3 points anymore.

This in pseudo code becomes:

```python

haveThreePointsInLine(points):
  for i < len(points) - 3:
    G1, G2 = gradients(i, points)
    i++

gradients(i, points):
  g1 = gradient(points[i], point[i+1])
  g2 = gradient(points[i+1], point[i+2])
  return G1, G2
```

# Summary 

I hope that you could see from the above simple example, how useful this technique can be.
If you have several small related points that complicates your thinking, group them into a single concept
and then reasoning at this higher level about the problem.

# More reading

  * https://betterexplained.com/articles/learning-to-learn-math-abstraction/
  * http://worrydream.com/LadderOfAbstraction/
  * https://en.wikipedia.org/wiki/Generalization
  * https://en.wikipedia.org/wiki/Abstraction