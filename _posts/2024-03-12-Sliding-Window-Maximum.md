---
layout: post
title:  "Sliding Window Maximum"
author: gerritjvv
featured: true
categories: [maths, data structures, algorithms]
---

## Overview

The sliding window is an interesting problem where you need to apply several advanced computer science concepts and algorithms
to give an optimum solution.  There are many resources for solving this problem, but as with all puzzles and problems, the usefulness
lies in developing a method for solving problems rather than the solution itself.

Look back at [Methods of problem-solving](methods-of-problem-solving-power-set) for the steps we will take.


# 1.  Problem statement

**Write down the problem statement and the desired solution.**

Given a list of numbers, a sliding window of length k moves over the list from left to right, advancing one item to the right at a time.  Give the maximum number at each sliding window instance.

We start at window 1, calculate the maximum, store it, and then iterate to window 2.

```
W as window
n as
answer as max list 

for W in list
  answer append max(W)   
```

```

list = [1,3,-1,-3,5,3,6,7]
window-size = 3

| i   |  W             | Max |   
| 0   |  [1,  3, -1]   | 3   |
| 1   |  [3, -1, -3]   | 3   |
| 2   |  [-1,-3,  5]   | 5   |
| 3   |  [-3, 5,  3]   | 5   |
| 4   |  [5,  3,  6]   | 6   |
| 5   |  [3,  6,  7]   | 7   |
```

# 2.  Observations

The initial solution is simple, and for small lists, it would work. 
But for larger lists and windows, the total cost of re-iterating through the window on every item of the list
would grow.

Maybe we can study what is happening during the simple process to see if we can reduce the actual data stored in the window,
and perhaps we can push the maximum to a fixed position.


From the table above, we can observe that once a maximum number appears in the right-most window position `W.end`, it remains
the maximum throughout the whole window, till it reaches the leftmost window position, `W.start`. 

If we have a number `n`, we can remove any item in W smaller than `n`.  If `W` is empty, we can just add `n`.
By the end of this operation, we are guaranteed that `W` contains elements bigger than `n` only. 

```
n = next item
for w in W:
  if w < n
    remove from W
```

Testing 

```
W = [1,  3, -1] 
list = [1,3,-1,-3,5,3,6,7]

| i  |   n   |  W           |  W' drop all < n and append n  | max
| 0  |  -3   |  [1, 3, -1]  |  [3, -1, -3]                   | 3
| 1  |   5   |  [3, -1, -3] |  [5]                           | 5
| 2  |   3   |  [5]         |  [5, 3]                        | 5
| 3  |   6   |  [5, 3]      |  [6]                           | 6
| 4  |   7   |  [6]         |  [7]                           | 7
```

Trying to devise  a test where `W` contains elements bigger than `n`

```
W = [4]
list = [2, 3, 6, 3]

| i  |   n   |  W           |  W' drop all < n and append n  | max
| 0  |   2   | [4]          | [4, 2]                         | 4
| 1  |   3   | [4, 2]       | [4, 3]                         | 4
| 2  |   6   | [4, 3]       | [6]                            | 6
| 3  |   3   | [6]          | [6, 3]                         | 6
```

# 3.  More Observations

From the above, you may be able to observe more properties.

 * The leftmost position of W always contained the maximum item.  This means that after applying `drop all < n and append n`, `W` is sorted in decreasing order. 
 * Due to this ordering, it would be more efficient to compare the right-most item in `W` going to the left.
 * We have implicitly trimmed the frame by dropping any elements on the right that fall outside the window.
 * We didn't need to start with a filled window.

# 4.  Pseudo Logic and more observations

On a high level, we could implement this new logic as:

```python
list = input
W = [ list[0] ]

for n in list:
    W = W.drop( W.head < n ) + n # + is list append
    W = W.drop( where index from list is < current index - window-size) # trim to window size
    W[0] is max
```

All good so far, except for the index part.  

 * We know the index `n` holds in `list`.
 * We do not know the index that `w` holds in `list` unless we save this, i.e. `w` becomes `w = (index, n)` or just `w = index in list`.
 * With `w = index` we can get the value with `list[w]`.

# 5.  Implementation 

When we perform list operations that involve editing the start and end of a list, a good data structure is a doubly linked queue, or `deque`.
The time complexity for all deque operations is `O(1)`.

A deque can be implemented using a linked list or a circular array.

Here, we will provide the logic and assume the default implementation is provided.

```
Interface Deque (
  push_right( item )
  push_left( item )
  pop_right() -> item
  pop_left() -> item
  left() -> item
  right() -> item
  empty() -> bool
)

W = deque( [0] )
k = window size

for i in [1..k)
    n = list.get(i)
    while not W.empty() and list.get(W.right()) <= n
        W.popRight()
    
    W.push_right(i)
  
print(First max is list.get(W.left()))

for i in [k..len(list))
    n = list.get(i)
    while not W.empty() and list.get(W.right()) <= n
        W.popRight()
     
    while not W.empty() and W.left() <= i - k
        W.popLeft()
     
    W.push_right(i)
    
    print(Max is list.get(W.left()))
```

# Appendix

## Python implementation

```python
from typing import List
from collections import deque


def solve(values: List[int], window_size: int) -> List[int]:
    if not values:
        return []
    if len(values) == 1:
        return [values[0]]

    if len(values) == window_size:
        return [max(values)]

    W = deque([0])
    k = window_size
    ans = []

    for i in range(1, k):
        n = values[i]
        while W and values[W[-1]] <= n:
            W.pop()

        W.append(i)

    ans.append(values[W[0]])

    for i in range(k, len(values)):
        n = values[i]
        while W and values[W[-1]] <= n:
            W.pop()

        while W and W[0] <= i - k:
            W.popleft()

        W.append(i)

        ans.append(values[W[0]])
    return ans
```