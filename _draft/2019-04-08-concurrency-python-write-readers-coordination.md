---
layout: post
title:  "Concurrency - Coordinate single update, multiple readers in python"
author: gerritjvv
categories: [ concurrency, python]
image: assets/images/volodymyr-hryshchenko-inI8GnmS190-unsplash.jpg
---


# Overview

The problem is: You have a sqlite database (for local caching) that is shared between multiple python processes,
and you want to update it from a single process so that readers are not blocked.

You cannot just write to the sqlite database because on write it blocks all other readers.


## Solving

The idea is to maintain 2 database files, `[db_1, db_2]`. The writer will always update the database one ahead, and the readers
will read the database one before the writer.
 

We can use the current minute of the hour, and the modulo operation to determine which database to read.
e.g:

```
Reader db = hour % 2
Writer db = (hour+1) % 2
```


 
# Summary 


