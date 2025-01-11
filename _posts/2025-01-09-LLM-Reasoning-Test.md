---
layout: post
title: "LLM Logic Drift — The striped elephant test"
author: gerritjvv
featured: true
categories: [ai, llm]
---

## Overview

Large Language Models (LLMs) are remarkable tools. When integrated into products with chat interfaces, they can almost convince you they possess intelligence.
As tools, they are extraordinary, yet, like any tool, they come with their pros and cons. At their core, LLMs—or AI, as they’re now marketed—are essentially sophisticated next-token predictors. 
The real danger of AI isn’t that it will become Skynet and dominate the earth. Rather, it’s the illusion of intelligence that can mislead people into delegating the critical act of thinking to a machine.

That said, I use LLMs and even pay for some AI/LLM services. Why? For the same reason, I invest in other engineering tools, like my laptop or my IDE subscription: they enhance my productivity. They are tools of the trade. But know your tools. Don't delegate your thinking to an LLM, just as you wouldn’t entrust it to your IDE’s autocomplete feature.

To illustrate my point, I devised a nonsensical test to see how long it would take for an LLM to skew logic.

## The Striped Elephant Logic Test

The aim of the test is to write a sentence with some conditions. There is nothing special about it other than, the LLM won't have seen it before, and the relationship my logic implies and does not imply is known by myself. 

*Step 1*: 

I start with a prompt:

```
work on the sentence to make it smoother "an elephant is blue and red because it has stripes and the reindeer has wholes of 2 due to a fight with a cat while the cat has two hooves and 2 paws."
```

Here I'm focusing on saying I have an elephant that is blue and red because it has stripes.

The LLM responds:

```
Here’s a smoother version of your sentence with improved flow and clarity:

"An elephant is blue and red because of its stripes, while the reindeer has two holes from a fight with a cat, which, intriguingly, has both two hooves and two paws."

Let me know if you'd like further adjustments!
```

Nice, it reads more concisely. The elephant is still blue and red because of its stripes.

*Step 2*: 

I prompt again, using the previous answer the LLM gave me:

```
work on the sentence to make a it smoother 
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
work on the sentence to make a it smoother
"An elephant is blue and red because of its stripes, while the reindeer bears two holes from a fight with a peculiar cat that strangely has both hooves and paws."
```

The LLM responds:

```
Here’s a smoother version of your sentence with improved flow and clarity:

"An elephant stands out with its blue and red stripes, a vibrant display of its unique markings. Nearby, a reindeer bears two noticeable wounds from a skirmish with a peculiar cat—a curious creature that oddly combines hooves and paws."

This version maintains the whimsical tone while enhancing clarity and flow. Let me know if you'd like further tweaks!
```

Do you see the issue?
Let's discuss the results.

## Test Results

*What happened to my elephant?*:

In the test, the LLM changed the elephant from being blue and red because of its stripes, to actually having blue and red stripes. In my mind, my elephant had black stripes or pink stripes. 
The fact is the original description said nothing about the color of the stripes, but it definitely didn't say that my elephant had blue and red stripes. Y

*And my reindeer?*

The reindeer went from, having two holes, and two hooves and two paws, to being nearby. The description didn't say it nearby, who knows it may be far. Now the reindeer has two wounds. The description didn't say they were wounds, but ok, maybe from the fight with the cat part this could be implied. The reindeer now "combines hooves and paws". But the crucial information of having 2 hooves and 2 paws was lost, which means the nonsensical animal could in the next iteration have how many of what?

The LLM has reduced and lost information. Not as bad as with the elephant though.



