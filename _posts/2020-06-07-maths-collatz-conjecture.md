---
layout: post
title:  "Collatz Conjecture"
author: gerritjvv
featured: true
categories: [ problem-solving, numbers, maths ]
image: assets/images/Collatz-stopping-time.svg.png
---


# Overview

From https://en.m.wikipedia.org/wiki/Collatz_conjecture:

The Collatz conjecture is a conjecture in mathematics that concerns a sequence defined as follows: start with any positive integer n. Then each term is obtained from the previous term as follows: if the previous term is even, the next term is one half of the previous term. If the previous term is odd, the next term is 3 times the previous term plus 1. The conjecture is that no matter what value of n, the sequence will always reach 1. 


Below is my own personal take on it and thoughts, critiques and thoughts are welcome: `gerritjvv"At"gmail.com`.


# In code

```
Clojure:

(defn collatz [x]
   (if (= x 1)
     '()
     (let [x2  (if (even? x)
                    (long (/ x 2))
                    (inc (* x 3))) ]
           (lazy-seq 
              (cons x2 (collatz x2)))))) 
```
Use: `(collatz 10)`

Python:

```
def collatz(start):
    x = start
    while True:
       if x == 1:
          return 1
       
       if x % 2 == 0:
          x = int(x / 2)
          yield x
       else:
          #odd
          x = x * 3 + 1
          yield x
```         

Use: `list(collatz(10))`
          
# Reasoning

The following is my reasoning on different properties and ideas with regards to the conjecture.
A loose method of Proof by induction is used at times and at others I just explore the ideas.
 

# Exploratory

## Any number in the 'series' of 2x when halved recursively will end in 1.

Proof and helper code:
  
  ```
  Series 2x starting at 1 is:
     
  1, 2, 4, 8, 16...
  
  16 halved is 8 havled is 4 havled is 2 havled is 1
  
  A function to generate the series is:
  
  def f(start):
    x = start
    while True:
       x = x * 2
       yield x
```

Used as:

```python
from  itertools import islice
  
def take(n, iterable):
  return list(islice(iterable, n))

   
take(10, f(1))
   
=> [2, 4, 8, 16, 32, 64, 128, 256, 512, 1024]
```

What is interesting here is the series of `2x` is also the sum of each row in Pascal's triangle.

```
      1     => 1
     1 1    => 2
    1 2 1   => 4
   1 3 3 1  => 8
  1 4 6 4 1 => 16
``` 

 
## Odd + Odd + Odd + 1 is Even, and Odd + 1 is Even

 It follows that:
 
`Odd + Odd + Odd` is Odd
`Odd + 1` is Even

`Odd + Odd + Odd` is Event

So that:

If we take the Odd condition of the conjecture we can say:

`3x + 1` == `x+1` 


And write a simplification of the collaz conjecture is:

If even: `x/2`
If Odd: `x+1`

```
def collatz2(start):
    x = start
    while True:
       if x == 1:
          return 1
       
       if x % 2 == 0:
          x = int(x / 2)
          yield x
       else:
          #odd
          x = x + 1
          yield x
```

The cycle produced by `collatz2` is always shorter than the original `collatz` conjecture function.
And this makes sense, instead of multiplying by 3 we are in fact going directly to the closest even number.


## Collatz Series length

The number of steps required to reach one by the collatz conjecture also has some interesting prorperties.

### Halving mutliples of 100 and 5

| N | Length | Function |
| ---- | ----- | ----- | 
| 1600 | 29 |   `len([x for x in collatz(1600)])` |
| 800 | 28 |   `len([x for x in collatz(800)])` |
| 400 | 27 |   `len([x for x in collatz(400)])` |
| 200 | 26 |   `len([x for x in collatz(200)])` |
| 100 | 25 |   `len([x for x in collatz(100)])` |
| 50 | 24 |   `len([x for x in collatz(50)])`   |
| 25 | 23 |   `len([x for x in collatz(25)])`   |


Looking a the length N from 100 upwards its clear that this is the `2x` series from above multiplied by 100.



### Why does Odd  times 3 plus 1 eventually produce a number that is a in the series 2x?

It doesn't always produce such a number. It only always gives us an even number, and sometimes
the even number is in the series 2x, which is then recursively halvable to 1.


## Does multiplying 3 then adding 1 produce a different number for each odd number?


```
def odd(n):
    return n % 2 != 0

def odd_seq(start):
    assert odd(start)
    x = start
    while True:
       x = x + 2
       yield x

def n3_1(sq):
  for x in sq:
    yield x * 3 + 1

def half(sq):
   for x in sq:
     if x % 2 == 0:
        yield x
        
take(100, n3_1(odd_seq(1)))

```


Proof:

````
Each natural positive number is unique such that any x is one bigger than
the previous number and one smaller than the next number.

Multiplying  by 3 and adding one, always creates a unique number i.e 

x * 3 + 1 = y

(x+1) * 3 + 1 = p

y < p always.

Thus taking a sequence of increasing odd numbers as unput to the function n3_1 will always produce a
unique sequence of non overlapping numbers.

````


## All multiples of 10 can reduce to 1 eventually via to collatz function, and the collatz sequence produces numbers that eventually will hit a multiple of 10



Proof:

```

Part A:

The sequence of all odd numbers starting at 1, mapped onto the function f(x)=x*3 + 1 will generate a sequence
of even numbers that are 6 apart.  

l = take(100, n3_1(odd_seq(1)))

#l = [10, 16, 22, 28, 34, 40, 46, 52, 58, 64, 70, 76, 82, 88, 94, 100, 106, 112, 118, 124, 130, 136, 142, 148, 154, 160, 166, 172, 178, 184, 190, 196, 202, 208, 214, 220, 226, 232, 238, 244, 250, 256, 262, 268, 274, 280, 286, 292, 298, 304, 310, 316, 322, 328, 334, 340, 346, 352, 358, 364, 370, 376, 382, 388, 394, 400, 406, 412, 418, 424, 430, 436, 442, 448, 454, 460, 466, 472, 478, 484, 490, 496, 502, 508, 514, 520, 526, 532, 538, 544, 550, 556, 562, 568, 574, 580, 586, 592, 598, 604]

def diffs (ls):
    i = 0
    assert len(ls) % 2 == 0
    while i < len(ls)-1:
        a = ls[i]
        b = ls[i+1]
        yield b-a

diffs(l)
# [6, 6, 6, 6 .....]


Part B:

A sequence of multiples of 6 always lead to a multiple of 10 in steps of 5. 
i.e 6 * 5 = 30, 6 * 10 = 60, 6 * 15 = 90 and so on

list(collatz(15))
[46, 23, 70, 35, 106, 53, 160, 80, 40, 20, 10, 5, 16, 8, 4, 2, 1]
6:=> [5, 16, 8, 4, 2, 1]
6:=> [53, 160, 80, 40, 20, 10]
5:=> [46, 23, 70, 35, 106]

6 * 5 => 30
list(collatz(30))
[15, 46, 23, 70, 35, 106, 53, 160, 80, 40, 20, 10, 5, 16, 8, 4, 2, 1]

6:=> [5, 16, 8, 4, 2, 1]
6:=> [53, 160, 80, 40, 20, 10]
6:=> [15, 46, 23, 70, 35, 106]

6 * 10 => 60
list(collatz(60))

[30, 15, 46, 23, 70, 35, 106, 53, 160, 80, 40, 20, 10, 5, 16, 8, 4, 2, 1]
6:=> [5, 16, 8, 4, 2, 1]
6:=> [53, 160, 80, 40, 20, 10]
7:=> [30, 15, 46, 23, 70, 35, 106]


6 * 15 => 90
list(collatz(90))
[45, 136, 68, 34, 17, 52, 26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1]
6:=> [5, 16, 8, 4, 2, 1]
6:=> [52, 26, 13, 40, 20, 10]
5:=> [45, 136, 68, 34, 17]

Part C:

Any multiple of 5 or 10 reduces to 1 when applied to the collatz function.

By proof of:

Any multiple of 5 or 10 when halved (because it is even) or divided by 5 when odd will eventually reduce
to 10 or 5.
We know by proof of example that 10 or 5 can reduce to 1 when applied to the collatz function.  

```


# Summary

````
Starting at any number and applying the collatz function will always end in 1 because:  
  
  For any odd number:
      when applied to x*3+1 we get a unique even number, which is a multiple of 6. 
      multiples of 6 going forward will eventually reach a multiple of 10 in 5 steps.
      multiples of 10 when halved when even and divided by 5 when odd will eventually reach 10 or 5.
      we know by proof of example that 10 and 5 when applied to the collatz function reduce to 1.
  
  For any even number:
      when applied to x/2 we get either an odd number, which case we follow the logic above, 
      or yet another even number, if its an even number always we will eventually get to 10,8,6,4 or 2, 
      which we know when applied to the collatz dunctiona reduce to 1.
       
````

