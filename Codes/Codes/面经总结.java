1. matrix class query / update.


2. Server A to Server B  backup huge file with small change. -> use Rsync.


3. Sparse Vector : hashTable to store the index -> value pair. Dotproduct will be O(n) by go through the keyset of one vector.

4. Linux diff, traverse the two input directory, find all the sub file, sub folder. Create relative path between input directory and files in it. Then, we can get two HashSets, Compare the items in both hashSet, 
Get group A : path/file in Set A, but not in Set B.
Group B: path/file in SetB, but not in Set A.
For files that have same relative path, compare the md5 hash of the file contents.

5. 有一排数量无限的object，每个object有两个状态，可以用true和false来表示，object的状态是可切换的，初始情况下所有object的状态都是false。
让你设计一个class，实现两个方法：isToggled(long idx), toggle(long start, long end)
Use an interval tree.

6. Spiral Matrix:
Keep track of RowBegin, RowEnd, ColBegin, ColEnd; Four forloop in a while loop (rowBegin <= rowEnd && ColBegin <= colEnd). until the matrix was all traversed.

7. Signature of number list (http://www.careercup.com/question?id=14912744):
Use a LinkedList, if is increasing just add the new Node to the back, if is the first decrese, just add it to the front of last node, if it is consecutive decrese, add it to the front of last decres node.
iidiiddii 
1->2->3 null,  1->2->4->3 4,    1->2->4->3->5->6  4, 1-2-4-3-5-7-6 7, 1-2-4-3-5-8-7-6 8, 1-2-4-3-5-8-7-6-9-10 iidiiddii.

This can also be solved by Topological sort, for iiddiid, it would be 1->2->3<-4<-5->6->7<-8, Topological sort with constrain that left(smaller) child goes first. 1-2-5-4-3-6-8-7 iiddiid

8. Give an array, find the count of Unique Binary Trees. DP.

9. ⼤大概就是，⼀一个数组, 1112223334445556677888...当中少2个数字，找到就⾏行了

10. ⼀一个数组
1,4,2,6.....
每次调⽤用⼀一个函数，按照数组⾥里⾯面的数字的⼤大⼩小，返回相应的 Index。
⽐比如， 上⾯面的例⼦子就是
1/13 的概率返回0,
4/13的概率返回1
先找cumulative probability［1, 5, 7, 13]， 然后弄个0〜～13
之间的random数字⽐比较过去找它的位置就好 (binary search)


11. 一队人打散后，每个人有一个高度，每个人记得原来前面有几个人比他高，没有重复⾼高度，求复原原始队列
Sort by Tvalue, then height, in increasing order. Then iterate from the beginning, insert value if Tvalue > 0

Example : Original 3, 5, 2, 4, 7 . Then, the Tvalue will be  0, 0, 2, 1, 0.
Sort the list by TValue, then height, Then the sorted list will be:

3 5 7 4 2
0 0 0 1 2

Then, insert people
3   3 5 7
0 , 0 0 0, 
When inserting (4,1), we need to watch the Tvalue, starting from the first element in the list, if the element is higher than 4, decrese TValue by 1, insert the node when Tvalue is 0.
3 5 4 7    3 5 2 4 7
0 0 1 0,   0 0 2 1 0

12. UTF Pattern validate. Bit operation  to match 10XXXXXX, use 10XXXXXX & 11000000 = 10000000

13. ⼀一个full binary tree的定义是每个node有0个或两个左右child，
给定树的⾼高度，求有多少种这样的binary tree

14. Leetcode 139,140: Word Break I, II