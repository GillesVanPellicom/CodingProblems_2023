# [Sort binary tree by levels](https://www.codewars.com/kata/52bef5e3588c56132c0003bc)
###### Site Difficulty: 4 kyu / Perceived difficulty: Hard
You are given a binary tree.
Your task is to return the list with elements from tree sorted by levels, which means the root element goes first, then root children (from left to right) are second and third, and so on.

Return empty list if root is 'null'.
#### Example 1 - following tree:

                 2
            8        9
          1  3     4   5
Should return following list:

[2,8,9,1,3,4,5]
#### Example 2 - following tree:

                 1
            8        4
              3        5
                         7
Should return following list:

[1,8,4,3,5,7]
## Personal notes:
The concept is easy: write a by-level traversal algorithm for a binary tree. 
For anyone who has ever taken a datastructures course, binary trees and traversal are the first topics.

I never got my first version to fully pass. My logic was sound since my second version is based on the same logic and that one does works.
The problem was that the unit tests, not public to me, were showing undefined behavior in some cases that caused the program to hallucinate levels and all values to be totally random each runtime.
I never managed to replicate the behavior codewars' test suites were uncovering but I managed to narrow the problem down to the recursive stage.

Since recursion was essential for the approach I was trying, eventually I started over, this time using no recursion but a queue. Exactly the same high-level design. It worked first time.