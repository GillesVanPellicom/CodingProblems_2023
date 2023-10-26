# [The Millionth Fibonacci Kata](https://www.codewars.com/kata/53d40c1e2f13e331fc000c26)
###### Site Difficulty: 3 kyu / Perceived difficulty: Hard
In this kata you will have to calculate ```fib(n)``` where:

```
fib(0) := 0
fib(1) := 1
fib(n + 2) := fib(n + 1) + fib(n)
```
Write an algorithm that can handle ```n``` up to ```2000000```.

Your algorithm must output the exact integer answer, to full precision. 
Also, it must correctly handle negative numbers as input.

HINT I: Can you rearrange the equation``` fib(n + 2) = fib(n + 1) + fib(n)``` to find ```fib(n)``` if you already know ```fib(n + 1)``` and ```fib(n + 2)```? Use this to reason what value ```fib``` has to have for negative values.
### Personal notes:
First I had to figure out how to store $F(2000000)$, since that number is way above the java integer limit.
After about 20 minutes of testing I felt confident enough about using BigInteger.

My first approach was a naive iterative one. Obviously this has an exponential time complexity of $O(2^n)$ so this was outside the given time constraints.
I tried optimizing the algorithm by adding if-cases to use more efficient algorithms when BigInts aren't required, but the time gains, although significant, were not nearly enough.

For my second attempt I first went trough some literature on the topic and [I came across the formula](https://en.wikipedia.org/wiki/Fibonacci_sequence#Relation_to_the_golden_ratio) $F(n)=\frac{\phi^n−\psi^n}{5}$, where $\phi = Golden Ratio$ and $\psi = \frac{1-\sqrt{5}}{2}$.
This formula calculates $F(n)$ by making use of the properties of the golden ratio and its conjugate. Since there is no iteration, recursion or looping of any kind in this formula, the time complexity should be $O(1)$, so I gave it a try.
Unfortunately java isn't a scientific language and I had quite some trouble finding a good square root algorithm which works with BigIntegers among other issues. (I eventually landed on Newton's method)
The main issue with implementing this formula is that it makes use of decimals, meaning I have to work with doubles. Since the [IEEE 754](https://en.wikipedia.org/wiki/IEEE_754) standard isn't at all precise this solution also does not work in this case since absolute precision is a requirement.

For attempt three I went trough some more literature and I stumbled upon a [matrix exponentiation](https://en.wikipedia.org/wiki/Fibonacci_sequence#Matrix_form) approach. With a time complexity of $O(\log{n})$ I thought it was worth a try.
Since it was quite some time ago since I last worked with matrices, I made this version more elaborate then it needed to be in order to re-familiarize myself with matrices.
There was a large improvement in runtime compared to v2 but since this version was very unoptimized, it still didn't manage to calculate $F(2000000)$ within the given time constraints.

In attempt four all I had to do was simply optimize v3 and I'd be done, barring any unforeseen bugs. "Premature optimization is the root of all evil" and all that. 
This version passed the unit tests with flying colors, first time.