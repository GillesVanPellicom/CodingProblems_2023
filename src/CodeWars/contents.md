# Table of contents - Codewars problems
[![Codewars](https://www.codewars.com/users/GillesVanPellicom/badges/large)](https://www.codewars.com/users/GillesVanPellicom)
### [Codewars difficulty explanation](https://docs.codewars.com/gamification/ranks/)
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

### [1) Breaking chocolate problem](https://www.codewars.com/kata/534ea96ebb17181947000ada)
###### Site Difficulty: 7 kyu / Perceived difficulty: Trivial
Your task is to split the chocolate bar of given dimension n x m into small squares. Each square is of size 1x1 and unbreakable. Implement a function that will return minimum number of breaks needed.
#### Personal notes:
All you have to do is guess the formula and return it.

### [2) Roman Numerals Helper](https://www.codewars.com/kata/51b66044bce5799a7f000003)
###### Site Difficulty: 4 kyu / Perceived difficulty: Medium
Write two functions that convert a roman numeral to and from an integer value.
Multiple roman numeral values will be tested for each function.
### Personal notes:
The difficulty is in pouring a set of steps already internally understood into a formal algorithm.
After a bit of trial and error I came up with a backwards-subtractive and an iterative approach.

### [3) Sort binary tree by levels](https://www.codewars.com/kata/52bef5e3588c56132c0003bc)
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

### [4) The Millionth Fibonacci Kata](https://www.codewars.com/kata/53d40c1e2f13e331fc000c26)
###### Site Difficulty: 3 kyu / Perceived difficulty: Hard
In this kata you will have to calculate $fib(n)$.
Write an algorithm that can handle $n$ up to $2000000$
Your algorithm must output the exact integer answer, to full precision.
Also, it must correctly handle negative numbers as input.
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

### [7) Evaluate mathematical expression](https://www.codewars.com/kata/52a78825cdfc2cfc87000005/java)
##### Site Difficulty: 2 kyu / Perceived difficulty: Hard
Given a mathematical expression as a string you must return the result as a number.
### Personal notes:
This at first sight deceptively simple problem had me thinking for a bit. The bracket depth is the biggest obstacle to overcome. I thought about simply using a stack of some sorts to keep track of the brackets, but this would only work if I wanted to know if all brackets are closed. I could probably pour the expression into a tree and then use some implementation of [DFS](https://en.wikipedia.org/wiki/Depth-first_search), but then I remembered I like to avoid trees whenever I can since they are annoying to debug. 

After a while I remembered [POSTFIX](https://en.wikipedia.org/wiki/Reverse_Polish_notation) notation could be used by a computer to easily evaluate an expression since brackets don't exist in POSTFIX. This makes the expression linear and linear things are simple to work with.

So my plan was to write a tokenizer (with as output something like ```[a, +, b]```, pipe that into an INFIX to POSTFIX algorithm (```[a, +, b] -> [a, b, +]```) and pipe that to a standard POSTFIX evaluation algorithm to get my result.

I could make the tokenizer using regex and the evaluator is just a for loop and a stack. The only unknown was the notation converter. After some reading I came across [Dijkstra's Shunting Yard algorithm](https://en.wikipedia.org/wiki/Shunting_yard_algorithm). I've liked working with Dijkstra's algorithms in the past so this sold me. Also a deceptively simple algorithm. After messing arround with the implementation for a while I got it to work and convert my INFIX tokens generated by the tokenizer to POSTFIX.
The issue was that this only works when the input expression exclusively contains binary operators. E.g. ```a + b```. 
In POSTFIX would be ```a b +```. Now what if we introduce a unary operator such as in this example: ```-1```? The algorithm breaks down when we do this. It won't complain or throw any errors, but now the output will just be completely useless.

My first instinct was to minimize any inputs before it gets to the tokenizer. E.g. ```4 + (-3)``` becomes ```4 - 3```. After some trial and error I managed to get, I'd like to say, 20% success rates. The reason this is concidered a non-trivial issue in the first place is because it's hard to work with INFIX notation as a computer. Needless to say I promptly scrapped that idea.

After more time than I'd like to admit I figured out I could just check in the tokenizer that if the minus operator succeeds another operator, then it's a unary operator and I could just give it a different character as token to distinguis itself from the binary minus operator.

When trying to implement this I came to the conclusion that my code was not at all clean or modular enough to continue working on and that it was better to scrap some functions and start over with my freshly acquired knowledge.

The two biggest changes were the change over from a simple tokenizer to a [lexer](https://en.wikipedia.org/wiki/Lexical_analysis) that divides my string into lexemes, making it much easier to work with, and the use of enumerations for type safety. These two changes made it easier to modify and read the code.

After some debugging and unforeseen interactions my code passed all unit tests. 

Time complexity: $O(n)$

### [8) Insane Coloured Triangles](https://www.codewars.com/kata/5a331ea7ee1aae8f24000175)
###### Site Difficulty: 2 kyu / Perceived difficulty: Hard
A coloured triangle is created from a row of colours,
each of which is red, green or blue.
Successive rows, each containing one fewer colour than the last,
are generated by considering the two touching colours in the previous row.
If these colours are identical, the same colour is used in the new row.
If they are different, the missing colour is used in the new row.
This is continued until the final row, with only a single colour, is generated.
### Personal notes:
For my first version I went with a simple recursive approach. Whenever there is a fully constructed row, the $calculateRow()$ function gets called to calculate the next row.
It does so using the placement rules defined in $getColor()$. This approach has a quadratic time complexity $O(n^2)$, so it went outside of the time constraints for the large test suites $10000 <= length(row) <= 100000$.
