# [Insane Coloured Triangles](https://www.codewars.com/kata/5a331ea7ee1aae8f24000175)
###### Site Difficulty: 2 kyu / Perceived difficulty: Hard

A coloured triangle is created from a row of colours, 
each of which is red, green or blue. 
Successive rows, each containing one fewer colour than the last, 
are generated by considering the two touching colours in the previous row. 
If these colours are identical, the same colour is used in the new row. 
If they are different, the missing colour is used in the new row. 
This is continued until the final row, with only a single colour, is generated.

#### For example, different possibilities are:
```
Colour here:            G G        B G        R G        B R
Becomes colour here:     G          R          B          G
```
#### With a bigger example:
```
R R G B R G B B
 R B R G B R B
  G G B R G G
   G R G B G
    B B R R
     B G R
      R B
       G
```
You will be given the first row of the triangle as a string and its your job to return the final colour which would appear in the bottom row as a string. 
In the case of the example above, you would be given ```'RRGBRGBB'```, 
and you should return ```'G'```.

### Constraints
```
1 <= length(row) <= 10 ** 5
```
The input string will only contain the uppercase letters ```'B'```, ```'G'``` or ```'R'```.

The exact number of test cases will be as follows:

- ```100``` tests of ```100 <= length(row) <= 1000```
- ```100``` tests of ```1000 <= length(row) <= 10000```
- ```100``` tests of ```10000 <= length(row) <= 100000```
### Examples
```
Kata.triangle("B") == 'B'
Kata.triangle("GB") == 'R'
Kata.triangle("RRR") == 'R'
Kata.triangle("RGBG") == 'B'
Kata.triangle("RBRGBRB") == 'G'
Kata.triangle("RBRGBRBGGRRRBGBBBGG") == 'G'
```
## Personal notes:
For my first version I went with a simple recursive approach. Whenever there is a fully constructed row, the $calculateRow()$ function gets called to calculate the next row.
It does so using the placement rules defined in $getColor()$. This approach has a quadratic time complexity $O(n^2)$, so it went outside of the time constraints for the large test suites $10000 <= length(row) <= 100000$.