---
layout: post
title: "LLM Logic Drift — The striped elephant test"
author: gerritjvv
featured: true
categories: [ai, llm]
---

## Overview

Large language models (LLM)s are remarkable tools. And when they are wrapped into a product with a chat interface can almost trick you into believing they are intelligent.
I'll reiterate, as a tool they are remarkable, but as with every tool, there are pros and cons. At its foundation LLMs or AI, as it is being sold now, are just next token guessers. 
That's right, they guess the next token based on previous tokens and fill it in. The problem is it cannot reason about the next token, it just probabilistically selects the next match.

The true danger of AI is not that it will turn into Sky net and rule the earth, but rather that people will think it is intelligent, therefore delegating the act of thinking to it. 
Now that said, I use LLMs and pay for some services. Why? for the same reason, I pay for many other engineering tools like my expensive laptop, or IDE subscription. They are tools for my trade.
But I will not delegate my thinking process to an LLM the same as I won't delegate it to my IDE's auto complete feature.

To show my point, I made a nonsensical test to see how long it takes for an LLM to skew logic. 

## The Striped Elephant Logic Test

The aim of the test is to write a sentence with some conditions. There is nothing special about it other than, the LLM won't have seen it before, and the relationship my logic implies and does not imply is known by myself. 

*Step 1*: 

I start with a prompt:

```
work on the sentence to make it moother "an elephant is blue and red because it has stripes and the reindeer has wholes of 2 due to a fight with a cat while the cat has two hooves and 2 paws."
```

Here I'm focusing on saying I have an elephant that is blue and red because it has stripes.

The LLM responds:

```
Here’s a smoother version of your sentence with improved flow and clarity:

"An elephant is blue and red because of its stripes, while the reindeer has two holes from a fight with a cat, which, intriguingly, has both two hooves and two paws."

Let me know if you'd like further adjustments!
```

Nice, it reads more concisely. The elephant is still blue and red because of its stripes. Which is the same to what I intended to say.

*Step 2*: 

I prompt again, using the previous answer the LLM gave me:

```
work on the sentence to make a it moother 
"An elephant is blue and red because of its stripes, while the reindeer has two holes from a fight with a cat, which, intriguingly, has both two hooves and two paws."
```

The LLM responds:

```
Here’s an improved version with more fluidity and balance:
"An elephant is blue and red because of its stripes, while the reindeer bears two holes from a fight with a peculiar cat that strangely has both hooves and paws."
```

Still going good. Our elephant hasn't changed.


*Step 3*:

```
work on the sentence to make a it moother
"An elephant is blue and red because of its stripes, while the reindeer bears two holes from a fight with a peculiar cat that strangely has both hooves and paws."
```

The LLM responds:

```
Here’s a smoother version of your sentence with improved flow and clarity:

"An elephant stands out with its blue and red stripes, a vibrant display of its unique markings. Nearby, a reindeer bears two noticeable wounds from a skirmish with a peculiar cat—a curious creature that oddly combines hooves and paws."

This version maintains the whimsical tone while enhancing clarity and flow. Let me know if you'd like further tweaks!
```

If the issue isn't evident, you may want to read the summary.

## Test Results

*What happened to my elephant?*:

In the test, the LLM changed the elephant from being blue and red because of its stripes, to actually having blue and red stripes. In my mind, my elephant had black stripes or pink stripes. 
The fact is my description said nothing about the color of the stripes, but I know this, never did I say that my elephant had blue and red stripes.


*And my reindeer?*

The reindeer went from, having two holes, and two hooves and two paws, to being nearby. I never said he was nearby. Now he has two wounds. I never said they were wounds, but ok, maybe from the fight with the cat this could be implied. 
My reindeer now combines hooves and paws. But the crucial information of having 2 hooves and 2 paws was lost, which means the nonsensical animal could in the next iteration have how many of what?
The LLM has reduced and lost information. Not as bad as with the elephant though.


## Summary

I knew the result would eventually skew, as the LLM makes change on change. This is part of how the tool works, and you never know when the next token may skew the previous meaning.

Now I could've told the LLM what the stripes were, but that would defeat the purpose of the test. 

Think about the following cases:

 1. Software project: You have a software project of hundreds of thousands of these little situations were a slight skew can change the intention of the software. It gets worse when software is composed and a single skew can have an exaggerated effect on the software somewhere else.
 2. Technical documentation: Imagine a technical documentation like servicing an airplain part, or reading a spec where the technical specification is crucial for selecting the correct part to avoid some failure.
 3. Legal documents: Letting an LLM write evidence that skews and skews, could make someone a murderer or innocent—all probabilistically.

So am I against LLMs. Definitely not. I used them frequently and they are great. I'm against the hype monster and people who should know better pushing the hype.

