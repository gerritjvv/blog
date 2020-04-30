---
layout: post
title:  "Methods of problem solving - Counting odd numbers"
author: gerritjvv
featured: true
categories: [ problem-solving, numbers, thinking-tools ]
image: assets/images/charlotte-coneybeer-R8Oakk_lLP8-unsplash.jpg
---


# Overview

When doing arithmetic, using little square boxes, squares and rectangles, can be very useful
to help understand the "shape" of numbers, and to come up with an equation to a number problem.

Yes numbers have shapes.

e.g a square:

````bash
x x
x x
````


The problem explored here is an algorithm for the sum of all odd numbers from `1 - n`.


# Explore the problem space

Odd numbers are `1, 3, 5, 7...` any number not divisible by 2.


How do they look visually?

```bash

     x    xx    xxx
x   xx   xxx   xxxx

1    3     5      7

```

From this we can infer the following:

  * Any odd number is an even number + 1
      ```  
           x
      2 => x    
                x
      2 + 1 => xx
      ```
    
  * Starting at any odd number, adding 2 to it gives the second odd number
      ```
          x      x
      x + x  => xx
      
       x   x      xx
      xx + x  => xxx
      ```
    
  * The sum of any 2 odd numbers gives an even number 
      ```             
                    
                    
       x    xx       xxxx 
      xx + xxx  =>   xxxx
       3 +   5  =>      8
      ```
    
  * The sum of any 3 odd numbers gives us an odd number 
    ```
      x    xx  xxx       xxxxxxx 
     xx + xxx xxxx  =>  xxxxxxxx
      3     5    7            15
    ```
    
    
# Simple recursive solution

From the above pattern we can come up with a simple recursive function.

The sum of odd numbers is always the current odd number plus the sum of the odd numbers before it.
The difference between any successive 2 odd numbers is 2.

```bash
N must be odd i.e rem(N/2) != 0

sum_odd [N] = N + sum_odd( N - 2 )

```

**Python**

```python
def sum_odd(n):
   if not n%2:
      return Exception(f"{n} is not an odd number")
   if n == 1:
      return 1
   else:
      return n + sum_odd(n-2)
```

**Clojure**

```clojure
(defn sumOdd [n]
  (when (= (rem n 2) 0) 
  (throw (RuntimeException. (str n " is not an odd number"))))
     
  (loop [acc 0 i n]
     (if (> 2 i) 
       (+ acc 1) 
       (recur (+ acc i) (- i 2)))))
```


On a computer this function is pretty quick, and for most practical purposes we could be done.  
Simple easy and we've solved our problem.

If we look further though, there is more insight we can gain by playing around.  


# What is the sum of all odd numbers 1..2n?


Lets look at what `2n` means.

If we have `n=3`, then `2n` is `1, 3, 5`.  
The sum of `2n` is the sum of the odd numbers `1 .. 2n-1` because `2n` gives us an even number `6`.  

If we sum these up we get `1 + 3 + 5 => 9`.  

This number `9` is the square of `3`.  


Lets use our blocks visualisation:

````bash
    x   xx 
x  xx  xxx
1   3    5

=> If we add them together, and try to form a square:

                xxx
 xx      xx     xxx
 xx     xxx  => xxx
1+3     5         9 

=> And visually we can confirm its 3 x 3

````

Lets try another number:

If we have `n=7` then `2n` is `1, 3, 5, 7, 9, 11, 13`.

If we sum these up using our algorithm above we get `sum_odd(13) => 49`.  

This is seems like `7 * 7`.  

When we explore other combinations of `2n` e.g `2*23` we get:  

```bash
For n = 23

2 * n => 46

So we sum up odd numbers 1 ... 2n-1 => 1 ... 45:

sum_odd(45) => 529

The square root of 529 is 23
```  
 

From our examples above we can say that: The sum of all odd numbers `1..2n` is `n * n`.


*Some more insight:*


We could've also observed that when we add two odd numbers, the sum is the `square` of the `number`  
of odd numbers added together.

We add 2 numbers `1` and `3`

```bash
1 + 3 => 4
```

which is `2 * 2`


We add 3 numbers `1`, `3`, `5`

```
1 + 3 + 5 => 9
```

which is `3 * 3`

So in general we can formulate that:


```
The sum of the first N odd numbers is always

N * N
```


# Summary 

Apart from having fun with numbers. 

I hope I could show you that using little squares to form basic shapes to represent numbers is an interesting
tool for helping discover and visualise interesting properties about numbers and equations.
 

