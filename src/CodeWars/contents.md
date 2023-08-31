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
The difficulty is pouring a set of steps already internally understood into a formal algorithm.
After a bit of trial and error I came up with a backwards-subtractive and an iterative approach.
### Sort binary tree by levels
###### Site Difficulty: 4 kyu / Perceived difficulty: Hard
You are given a binary tree.
Your task is to return the list with elements from tree sorted by levels, which means the root element goes first, then root children (from left to right) are second and third, and so on.
### Personal notes:
The concept is easy: write a by-level traversal algorithm for a binary tree. 
For anyone who has ever taken a datastructures course, binary trees and traversal are the first topics.
I never got my first version to fully pass. My logic was sound since my second version is based on the same logic and that one does works.
The problem was that the unit tests, not public to me, were showing undefined behavior in some cases that caused the program to hallucinate levels and all values to be totally random each runtime.
I never managed to replicate the behavior codewars' test suites were uncovering but I managed to narrow the problem down to the recursive stage.
Since recursion was essential for the approach I was trying, eventually I started over, this time using no recursion but a queue. Same high-level design. It worked first time.
