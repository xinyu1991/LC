/* 1 Add Two Numbers My Submissions Question
Total Accepted: 98878 Total Submissions: 470739 Difficulty: Medium
You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
*/

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
    }
}

/* 2
Longest Palindromic Substring My Submissions Question
Total Accepted: 76831 Total Submissions: 362533 Difficulty: Medium
Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
*/
public class Solution {
    public String longestPalindrome(String s) {
        
    }
}

/* 3
Container With Most Water My Submissions Question
Total Accepted: 56928 Total Submissions: 175284 Difficulty: Medium
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container.
*/
public class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        int left = 0, right = height.length-1;
        while(left < right){
            max = Math.max(max, (right-left)*Math.min(height[left], height[right]));
            
            if(height[left] < height[right]) left++;
            else right--;
        }
        return max;
    }
}

//  4 Integer to Roman & Roman to Integer & 
// Integer to English Words


/* 5
Reverse Nodes in k-Group My Submissions Question
Total Accepted: 45787 Total Submissions: 177265 Difficulty: Hard
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
*/
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        
    }
}

// 6 Divide two integers without using multiplication, division and mod operator.

// If it is overflow, return MAX_INT.
public class Solution {
    public int divide(int dividend, int divisor) {
        
    }
}

/* 7
You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
*/
public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        
    }
}

/* 8
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
*/
public class Solution {
    public int longestValidParentheses(String s) {
        
    }
}

/* 9 
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
*/

public class Solution {
    public int search(int[] nums, int target) {
    }
}

// 10 Search Insert
/*
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
*/
public class Solution {
    public int searchInsert(int[] nums, int target) {
        
    }
}

// 11 Sudoku Solver

public class Solution {
    public void solveSudoku(char[][] board) {
        
    }
}

// 12 Trapping Rain Water

public class Solution {
    public int trap(int[] height) {
        
    }
}

/* 13  Multiply Strings My Submissions Question
Total Accepted: 45114 Total Submissions: 208912 Difficulty: Medium
Given two numbers represented as strings, return multiplication of the numbers as a string.

Note: The numbers can be arbitrarily large and are non-negative.*/
public class Solution {
    public String multiply(String num1, String num2) {
    }
}

/* 14 

Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
A solution set is: 
[1, 7] 
[1, 2, 5] 
[2, 6] 
[1, 1, 6] 
*/
public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    }
}

/* 15
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)*/
public class Solution {
    public int jump(int[] nums) {
    }
}


/* 16
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.*/
public class Solution {
    public int maxSubArray(int[] nums) {
    }
}

/* 17
The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.*/
public class Solution {
    public string GetPermutation(int n, int k) {
 
    }
}

/* 18
Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.
*/
public class Solution {
    public boolean isNumber(String s) {
    }
}

/* 19
Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.
*/
public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
    }
}

/* 20 
Sqrt(x) My Submissions Question
Total Accepted: 71240 Total Submissions: 298949 Difficulty: Medium
Implement int sqrt(int x).

Compute and return the square root of x.
*/
public class Solution {
    public int mySqrt1(int x){
    }
}

/* 21
Simplify Path My Submissions Question
Total Accepted: 39359 Total Submissions: 192210 Difficulty: Medium
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
*/
public class Solution {
    public String simplifyPath(String path) {
    }
}

/* 22 
Sort Colors My Submissions Question
Total Accepted: 73518 Total Submissions: 221727 Difficulty: Medium
Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.
*/
public class Solution {
    public void sortColors(int[] nums) {
    }
}

/* 23
Minimum Window Substring My Submissions Question
Total Accepted: 45015 Total Submissions: 230816 Difficulty: Hard
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
*/
public class Solution {
    public String minWindow(String s, String t) {
    }
}

/* 24
Word Search My Submissions Question
Total Accepted: 54301 Total Submissions: 256777 Difficulty: Medium
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
*/
public class Solution {
    public boolean exist(char[][] board, String word) {
    }
}

/* 25
Remove Duplicates from Sorted List II My Submissions Question
Total Accepted: 56244 Total Submissions: 220793 Difficulty: Medium
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.*/
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        
    }
}

/* 26
Largest Rectangle in Histogram
For example,
Given height = [2,1,5,6,2,3],
return 10.
*/
public class Solution {
    public int largestRectangleArea(int[] height) {
    
    }
}


/* 27
Maximal Rectangle My Submissions Question
Total Accepted: 33727 Total Submissions: 150093 Difficulty: Hard
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
*/
public class Solution {
    public int maximalRectangle(char[][] board) {
    }
}

/* 28
Scramble String
*/
public class Solution {
    public boolean isScramble(String s1, String s2) {
    }
}

/* 29
Decode Ways My Submissions Question
Total Accepted: 52171 Total Submissions: 314909 Difficulty: Medium
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
*/

public class Solution {
    public int numDecodings(String s) {
        
    }
}

/* 30
Unique Binary Search Trees II
*/


/* 31
Interleaving String
*/
public boolean isInterleave(String s1, String s2, String s3) {  
    }


/* 32
Construct Binary Tree from Inorder and Postorder Traversal My Submissions Question
Total Accepted: 42635 Total Submissions: 155341 Difficulty: Medium
Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
*/

public class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, postorder, postorder.length-1, 0, inorder.length-1);
    }
}

/* 33
Path Sum II My Submissions Question
Total Accepted: 61113 Total Submissions: 227041 Difficulty: Medium
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]
*/
public class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
    }
}

/* 34
Distinct Subsequences My Submissions Question
Total Accepted: 42019 Total Submissions: 153864 Difficulty: Hard
Given a string S and a string T, count the number of distinct subsequences of T in S.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.
*/
public class Solution {
    public int numDistinct(String s, String t) {
        
    }
}

/* 35
Binary Tree Maximum Path Sum My Submissions Question
Total Accepted: 52087 Total Submissions: 236249 Difficulty: Hard
Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path does not need to go through the root.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.
*/
public class Solution {
    int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
    }
}

/* 36
Word Ladder II My Submissions Question
Total Accepted: 33685 Total Submissions: 253009 Difficulty: Hard
Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note:
All words have the same length.
All words contain only lowercase alphabetic characters.
*/
public class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
    }
}

/* 37

Palindrome Partitioning II My Submissions Question
Total Accepted: 41221 Total Submissions: 200930 Difficulty: Hard
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
*/

public class Solution {
    public int minCut(String s) {

    }
}

/* 38
Clone Graph My Submissions Question
Total Accepted: 51372 Total Submissions: 210087 Difficulty: Medium
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
*/

public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    }
}

/* 39
Candy My Submissions Question
Total Accepted: 43374 Total Submissions: 204601 Difficulty: Hard
There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?
*/
public class Solution {
    public int candy(int[] ratings) {
        
    }
}

/* 40
Single Number II My Submissions Question
Total Accepted: 65567 Total Submissions: 183574 Difficulty: Medium
Given an array of integers, every element appears three times except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/
public class Solution {
    public int singleNumber(int[] nums) {
    }
}

/* 41
Word Break II My Submissions Question
Total Accepted: 43549 Total Submissions: 240709 Difficulty: Hard
Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].
*/
public class Solution {
    public List<String> wordBreak(String s, Set<String> wordDict) {
        
    }
}

/* 42 Find Minimum in Rotated Sorted Array II My Submissions Question
Total Accepted: 39031 Total Submissions: 119046 Difficulty: Hard
Follow up for "Find Minimum in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.*/
public class Solution {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length-1, mid = 0;
        
        return findMin(nums, l, r);
    }
}

/* 43
Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5
return the root of the binary tree [4,5,2,#,#,3,1].
   4
  / \
 5   2
    / \
   3   1  
*/

public class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        
    }
}

// 44 Given two strings S and T, determine if they are both one edit distance apart.
public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        
    }
}

// 45
/*
Maximum Gap My Submissions Question
Total Accepted: 24080 Total Submissions: 95874 Difficulty: Hard
Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
*/
public class Solution {
    public int maximumGap(int[] nums) {
    }
}

// 46
/*
Bitwise AND of Numbers Range My Submissions Question
Total Accepted: 22394 Total Submissions: 82050 Difficulty: Medium
Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

For example, given the range [5, 7], you should return 4.
*/

public class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        long i = 1<<31;
        long res = 0;
        while(i>0){
            if((m&i) == (n&i)){
                res |= m & i;
            }
            else{
                break;
            }
            i = i>>1;
        }
        return (int)res;
    }
}

// 47 Course Schedule I and II
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
    }
}

/* 48
Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.
*/
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        
    }
}

/* 49
Contains Duplicate III My Submissions Question
Total Accepted: 15996 Total Submissions: 94946 Difficulty: Medium
Given an array of integers, find out whether there are two distinct indices i and j in the array such that the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
*/
public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    }
}

/* 50
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.
*/
public class Solution {
    public List<Integer> majorityElement(int[] nums) {
    }
}

/* 51 Number of Digit One
Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

For example:
Given n = 13,
Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
*/
public class Solution {
    public int countDigitOne(int n) {
        
    }
}

/* 52 
Sliding Window Maximum My Submissions Question
Total Accepted: 14556 Total Submissions: 61429 Difficulty: Hard
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].
*/
public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
    }
}

/* 53
Different Ways to Add Parentheses My Submissions Question
Total Accepted: 10892 Total Submissions: 37433 Difficulty: Medium
Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.


Example 1
Input: "2-1-1".

((2-1)-1) = 0
(2-(1-1)) = 2
Output: [0, 2]


Example 2
Input: "2*3-4*5"

(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
Output: [-34, -14, -10, -10, 10]
*/
public class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<Integer>();

        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == '+' ||
               input.charAt(i) == '-' ||
               input.charAt(i) == '*'){
                List<Integer> leftPart = diffWaysToCompute(input.substring(0, i));
                List<Integer> rightPart = diffWaysToCompute(input.substring(i+1));
                for(Integer left : leftPart){
                    for(Integer right : rightPart){
                        int value = 0;
                        switch(input.charAt(i)){
                            case '+':
                                value = left + right;
                                break;
                            case '-':
                                value = left - right;
                                break;
                            case '*':
                                value = left * right;
                                break;
                        }
                        res.add(value);
                    }
                }
            }
        }

        // Do not contain any operator, just return the value.
        if(res.isEmpty()) res.add(Integer.valueOf(input));
        return res;
    }
}



// Single Number III

// Ugly Number II