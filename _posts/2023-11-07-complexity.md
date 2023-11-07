---
layout: post
title:  "Complexity"
author: gerritjvv
featured: true
categories: [ programming, design, architecture ]
image: assets/images/sawdust.jpg
---

# see https://htmx.org/essays/locality-of-behaviour/ 

## Overview

```
There are two ways of constructing a software design: One way is to make it so simple that there are obviously no deficiencies,
and the other way is to make it so complicated that there are no obvious deficiences. The first method is far more difficult.
``` 
-- Tony Hoare

This applies to engineering in general. A simple system won't give you a great CV though, so software engineers tend to always lean
towards creating overly complex systems.

Every single decision in software design has tradeoffs, they are there and cannot be ignored, a good engineer will weigh each carefully
a bad one will just add and add with no thought for the implications.

Complexity has a place though and should only be used if a less simpler system does not meet the problem domain's requirements, and even then
the tradeoffs inherent in a more complex solution should be weighed against the requirements. What is meant here is that many times the 
the tradeoffs are so negative that the requirements can be relaxed. An example would be spending an extra 10K on a realtime messaging system
for an application where only a small portion of the users use it and the company behind it would not make any extra revenue.

### Definition of complexity.

Complexity can be mistaken for "not familiar with", e.g a different language may be complex because you don't know it.

Here I take complexity to mean **the presence of numerous interrelated parts, making it challenging to grasp or handle comprehensively** 

For example:

If your not familiar with APL the following would be perceived as complex, while in reality it is a call that sorts an array.

```APL
V←3 1 4 1 5
⍋V
```

The more moving parts a system has and the more they are interrelated the more complex a system becomes to comprehend.

#### Complexity and abstraction

Abstracting can either lessen or increase the complexity of a system. For example, if the system has a send a notification, and there can be many implementations
like email, webhook, slack, SQS etc, then addressing this as "notifier" in your code with a common trait simplifies the understanding of the code using it.

```
fn send(context,  msg)
    context.notifier.send( msg )
```

But don't be fooled, a tradeoff is made and the complexity is deferred to somewhere else, be it in the need for dependency injection, a component system or just the extra indirection.
And now anyone that debugs this would need to figure out what notifier was called.  If you only need email notification, it may be simpler to observe YAGNI and just support email.

```
fn send(context, msg)
    context.email_notifier.send_email( msg )
```

When designing a solution, abstraction can be wonderful for speed of thought and flexibility in notation, but when getting to the implementation the increase in complexity for maintaining
and debugging such a system caused by the indirection may not be worth it, hence the YAGNI design principle.

### Indirection

Defers the details of some solution to later. It is mainly used as a tool to give flexibility.

Here are some examples:

 * In utility and library style code, a filter function can be implemented as 

```
fn filter (predicate, list)
   for item in list
      if predicate(item)
        yield item
```

This allows us to write a single filter function and apply to any list of any types.

  * You may wrap our storage implementation behind a separate API, now our main code calls the storage API and we can defer thinking about the actual storage solution till later.

```
[App] ---- [store item ] ---> [ API ] --> [ Memory ]
                                      --> [ Redis ]
                                      --> [ Postgres ]
                                      --> [ Cassandra ]
```

### Complexity tradeoffs

Increasing complexity means increasing in a negative way, some or all of the following:

 * Time to develop a solution
 * Cost of development
 * Cost of maintenance
 * Cost of operation
 * Comprehension
 * Support
 * Debug and Fix bugs
 * Onboarding future team members
 * Add new solutions
 * Deprecate or delete the existing solution
 * Presence of bugs

### Cost of flexibility

It can be argued that a more flexible system can reduce cost in the future because it is easier to change by allowing all possible "injections" of new solutions
in the existing domain logic. For example, take the Hexagonal Architecture, this design defines many layers in an application and clear barriers between each layer,
resulting in a system that is so flexibly complex that you can add and remove software part implementations with great flexibility. The only problem is this system
so so tedious to create and maintain and the maintenance of the duplication between layers (an indirect result of the layer barriers) become exponential, 
that adding 20 new attributes to a domain object that travels from the UI to the DB and has to travel through several layer barriers, can take days if not weeks, 
and debugging where and how a single attribute of a domain object could have changed becomes an impossible task. 

Increasing flexibility can actually limit flexibility by increasing complexity, creating a system overwelmingly time-consuming to create, maintain and comprehend, that the only
solution is to delete everything and start new.

### Flexibility with Generic objects

Here "Generic" means a solution that is capable of handling diverse inputs.

A system may become less complex and more flexible by making it more "generic". 
For example, breaking up repeated patterns into generic function like with map, filter, reduce, or representing data in such a way that we can operate on it
with the same reusable simple functions.

As an example, Clojure's design has been built around the idea of a few simple generic structures a Map, Sequence/List, 
and many generic functions that operate on them to add, put, remove. Then instead of having specific types all modeling is done
using these generic structures and functions. The combination works quite well and allows building systems that are both flexible and generic.

On the other hand, making some problems generic may remove them so far from the actual domain that they become unrecognisable as solutions to the problem.
The latter can be seen when Monads start making their appearance, in Scala, Haskell, but this same generic trap can also happen in non monadic code:

The following function is generic, but, it would be impossible to figure out the actual purpose of it:
```
do something(List<List<List<List<String>>>> input):
    return input.head.head.tail(2).last() 
```

A better approach and (more generic) would be:

```
do something(input):
    return flatten(input).last()
```


### Complexity to meet requirements

This is an old argument but valid when considering we live in the real world. 
Let us imagine a solution that is easy to comprehend; word count in a file.

Provided we are running on a linux based system, the least complex would be:

```bash
cat <file> | wc -w
```

That's it. Nothing more nothing less. 

Now let us imagine we want to do word count on 1TB of data just even once.
Now you need a system where the complexity explodes but it is warranted because of the impossibility of doing so using a single server and the two linux commands above.

You would need:

  * A distributed processing system be it Hadoop, Spark, Netezza, Big Query...
  * A way to ingest the data with efficient data loaders.
  * Think of a storage format, maybe Parquet, CSV, TSV, and compression LZO, GZIP, SNAPPY, BZIP2...
  * Think of encoding differences and serializers and deserializers.
  * and many more things...

But here again, we must always ask, what would happen if we do not meet the requirements? are most of the requirements really that, or are they nice to haves
or some ones dreamt up solution that does not fit with what the business or solution needs.



### Surface area

The less parts a system has the easier it is to comprehend. 
From an engineering perspective, comprehension trumps fancy techniques and speed in the long run, a rabbit vs tortoise situation. 
Marketing and hype is a different issue.

### Complexity as the product of problem solving and change.

Complexity is a by product of problem solving. When solving a problem we try to model the problem space and use known or new techniques to
solve it. Some techinques break the problem into smaller ones, others use abstraction or indirection, typing, or generic structures, external services or tools etc.
What we do not get taught in problem solving is that, all problem solving techniques introduce complexity. This means we need to solve the problem but
also cleanup this by product. It can never be 100% removed but if left as is, creates a mess, which will hinder future application of problem solving techiniques. 
Think of it as the sawdust when cutting wood to make a table, you create a solution but also a lot of waste and dirt, which should be cleaned up. The work place gets
filled with dirt (the project repository), and you either cleanup or need to find a new place eventually.

This "clean up" process is neglected in the software engineering process when planning tasks and time.

