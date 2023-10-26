# [Roman Numerals Helper](https://www.codewars.com/kata/51b66044bce5799a7f000003)
###### Site Difficulty: 4 kyu / Perceived difficulty: Medium
Write two functions that convert a roman numeral to and from an integer value. 
Multiple roman numeral values will be tested for each function.

Modern Roman numerals are written by expressing each digit separately starting with the left most digit and skipping any digit with a value of zero. 
In Roman numerals 1990 is rendered: 1000=M, 900=CM, 90=XC; resulting in MCMXC. 2008 is written as 2000=MM, 8=VIII; or MMVIII. 
1666 uses each Roman symbol in descending order: MDCLXVI.

In this kata 4 should be represented as IV, NOT as IIII (the "watchmaker's four").
### Constraints
- Input range : 1 <= n < 4000
### Personal notes:
The difficulty is in pouring a set of steps already internally understood into a formal algorithm.
After a bit of trial and error I came up with a backwards-subtractive and an iterative approach.