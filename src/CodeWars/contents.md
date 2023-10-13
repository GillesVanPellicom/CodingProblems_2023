# Table of contents - Codewars problems
### Codewars difficulty explanation.
Codewars uses a japanese-style difficulty system where 8 kyu is easiest and 1 kyu concidered hardest.
| **Kyu** | **Difficulty**   |
|---------|------------------|
| Kyu 8   | Trivial          |
| Kyu 7   | Very Easy        |
| Kyu 6   | Easy             |
| Kyu 5   | Medium           |
| Kyu 4   | Hard             |
| Kyu 3   | Very Hard        |
| Kyu 2   | Exceedingly Hard |
| Kyu 1   | Hellish          |

### 1) Breaking chocolate problem
###### Site Difficulty: 7 kyu / Perceived difficulty: Trivial
Your task is to split the chocolate bar of given dimension n x m into small squares. Each square is of size 1x1 and unbreakable. Implement a function that will return minimum number of breaks needed.
#### Personal notes:
All you have to do is create/guess the formula and return it.

### 2) Roman Numerals Helper
###### Site Difficulty: 4 kyu / Perceived difficulty: Medium
Write two functions that convert a roman numeral to and from an integer value.
Multiple roman numeral values will be tested for each function.
### Personal notes:
The difficulty is in pouring a set of steps already internally understood into a formal algorithm.
After a bit of trial and error I came up with a backwards-subtractive and an iterative approach.

### 3) Sort binary tree by levels
###### Site Difficulty: 4 kyu / Perceived difficulty: Hard
You are given a binary tree.
Your task is to return the list with elements from tree sorted by levels, which means the root element goes first, then root children (from left to right) are second and third, and so on.
### Personal notes:
The concept is easy: write a by-level traversal algorithm for a binary tree. 
For anyone who has ever taken a datastructures course, binary trees and traversal are the first topics.

I never got my first version to fully pass. My logic was sound since my second version is based on the same logic and that one does works.
The problem was that the unit tests, not public to me, were showing undefined behavior in some cases that caused the program to hallucinate levels and all values to be totally random each runtime.
I never managed to replicate the behavior codewars' test suites were uncovering but I managed to narrow the problem down to the recursive stage.

Since recursion was essential for the approach I was trying, eventually I started over, this time using no recursion but a queue. Exactly the same high-level design. It worked first time.

### 4) The Millionth Fibonacci Kata
###### Site Difficulty: 3 kyu / Perceived difficulty: Hard
In this kata you will have to calculate ```fib(n)```.
Write an algorithm that can handle ```n``` up to ```2000000```.
Your algorithm must output the exact integer answer, to full precision.
Also, it must correctly handle negative numbers as input.
### Personal notes:
First I had to figure out how to store F(2000000), since that number is way above the java integer limit.
After about 20 minutes of testing I felt confident enough about using BigInteger.

My first approach was a naive iterative one. Obviously this has an exponential time complexity of $O(2^n)$ so this was outside the given time constraints.
I tried optimizing the algorithm by adding if-cases to use more efficient algorithms when BigInts aren't required, but the time gains, although significant, were not nearly enough.

For my second attempt I first went trough some literature on the topic and I came across the formula $F(n)=\frac{\phi^n−\psi^n}{5}$, where $\phi = Golden Ratio$ and $\psi = \frac{1-\sqrt{5}}{2}$.
This formula calculates F(n) by making use of the properties of the golden ratio and its conjugate. Since there is no iteration, recursion or looping of any kind in this formula, the time complexity should be $O(1)$, so I gave it a try.
Unfortunately java isn't a scientific language and I had quite some trouble finding a good square root algorithm which works with BigIntegers among other issues. (I eventually landed on Newton's method)
The main issue with implementing this formula is that it makes use of decimals, meaning I have to work with doubles. Since the IEEE 754 standard isn't at all precise this solution also does not work in this case since absolute precision is a requirement.

For attempt three I went trough some more literature and I stumbled upon a matrix exponentiation approach. With a time complexity of $O(\log{n})$ I thought it was worth a try.
Since it was quite some time since I last worked with matrices, I made this version more elaborate then it needed to be in order to re-familiarize myself with matrices.
There was a large improvement in runtime compared to v2 but since this version was very unoptimized, it still didn't manage to calculate $F(2000000)$ within the given time constraints.

In attempt four all I had to do was simply optimize v3 and I'd be done, barring any unforeseen bugs. "Premature optimization is the root of all evil" and all that. 
This version passed the unit tests with flying colors, first time.
