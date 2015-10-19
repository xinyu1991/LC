/*
Medium Longest Consecutive Sequence

33% Accepted
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Have you met this question in a real interview? Yes
Example
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Clarification
Your algorithm should run in O(n) complexity.
*/

public class Solution {
    /**
     * @param nums: A list of integers
     * @return an integer
     */
    public int longestConsecutive(int[] num) {
        HashMap<Integer, Integer> lengthMap  = new HashMap();
        int max = 0;
        for(int n : num){
        	if(lengthMap.containsKey(n)) continue;
        	int leftLength = lengthMap.containsKey(n-1) ? lengthMap.get(n-1) : 0;
        	int rightLength = lengthMap.containsKey(n+1) ? lengthMap.get(n+1) : 0;
        	int curLen = leftLength+rightLength+1;
        	lengthMap.put(n, curLen);
        	if(leftLength != 0) lengthMap.put(n-leftLength, curLen);
        	if(rightLength != 0) lengthMap.put(n+rightLength, curLen);
        	max = Math.max(max, curLen);
        }
        return max;
    }
}

/*
Medium Merge k Sorted Lists

26% Accepted
Merge k sorted linked lists and return it as one sorted list.

Analyze and describe its complexity.

Example
Given lists:

[
  2->4->null,
  null,
  -1->null
],
return -1->2->4->null.
*/

public class Solution {
    /**
     * @param lists: a list of ListNode
     * @return: The head of one sorted list.
     */
    public ListNode mergeKLists(List<ListNode> lists) {  
        // write your code here
    }
}

/*
Medium Implement Queue by Two Stacks

40% Accepted
As the title described, you should only use two stacks to implement a queue's actions.

The queue should support push(element), pop() and top() where pop is pop the first(a.k.a front) element in the queue.

Both pop and top methods should return the value of first element.

Have you met this question in a real interview? Yes
Example
push(1)
pop()     // return 1
push(2)
push(3)
top()     // return 2
pop()     // return 2
Challenge
implement it by two stacks, do not use any other data structure and push, pop and top should be O(1) by AVERAGE.
*/
public class Queue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public Queue() {
       // do initialization if necessary
       stack1 = new Stack();
       stack2 = new Stack();
    }
    
    public void push(int element) {
        // write your code here
        stack1.push(element);
    }

    public int pop() {
        // write your code here
        if(!stack2.isEmpty()) return stack2.pop();
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    public int top() {
        // write your code here
        if(!stack2.isEmpty()) return stack2.peek();
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        return stack2.peek();
    }
}

/*
Medium Min Stack

30% Accepted
Implement a stack with min() function, which will return the smallest number in the stack.

It should support push, pop and min operation all in O(1) cost.

Have you met this question in a real interview? Yes
Example
push(1)
pop()   // return 1
push(2)
push(3)
min()   // return 2
push(1)
min()   // return 1
Note
min operation will never be called if there is no number in the stack.
*/
public class MinStack {
    
    public MinStack() {
        // do initialize if necessary
    }

    public void push(int number) {
        // write your code here
    }

    public int pop() {
        // write your code here
    }

    public int min() {
        // write your code here
    }
}

/*
Medium Ugly Number

21% Accepted
Ugly number is a number that only have factors 3, 5 and 7.

Design an algorithm to find the Kth ugly number. The first 5 ugly numbers are 3, 5, 7, 9, 15 ...

Have you met this question in a real interview? Yes
Example
If K=4, return 9.

Challenge
O(K log K) or O(K) time.
*/

class Solution {
    /**
     * @param k: The number k.
     * @return: The kth prime number as description.
     */
    public long kthPrimeNumber(int k) {
        // write your code here
    }
};

/*
Hard Word Search II

19% Accepted
Given a matrix of lower alphabets and a dictionary. Find all words in the dictionary that can be found in the matrix. A word can start from any position in the matrix and go left/right/up/down to the adjacent position. 


Have you met this question in a real interview? Yes
Example
Given matrix:
doaf
agai
dcan
and dictionary:
{"dog", "dad", "dgdg", "can", "again"}

return {"dog", "dad", "can", "again"}


dog:
doaf
agai
dcan
dad:
doaf
agai
dcan
can:
doaf
agai
dcan
again:
doaf
agai
dcan
Challenge
Using trie to implement your algorithm.
*/

public class Solution {
    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
        // write your code here
    }
}

/*
Hard Largest Rectangle in Histogram

24% Accepted
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

histogram

Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

histogram

The largest rectangle is shown in the shaded area, which has area = 10 unit.

Have you met this question in a real interview? Yes
Example
Given height = [2,1,5,6,2,3],
return 10.
*/

public class Solution {
    /**
     * @param height: A list of integer
     * @return: The area of largest rectangle in the histogram
     */
    public int largestRectangleArea(int[] height) {
        // write your code here
    }
}


/*
Hard Data Stream Median

24% Accepted
Numbers keep coming, return the median of numbers at every time a new number added.

Have you met this question in a real interview? Yes
Example
For numbers coming list: [1, 2, 3, 4, 5], return [1, 1, 2, 2, 3].

For numbers coming list: [4, 5, 1, 3, 2, 6, 0], return [4, 4, 4, 3, 3, 3, 3].

For numbers coming list: [2, 20, 100], return [2, 2, 20].

Challenge
Total run time in O(nlogn).

Clarification
What's the definition of Median? - Median is the number that in the middle of a sorted array.
If there are n numbers in a sorted array A, the median is A[(n - 1) / 2]. For example, if A=[1,2,3], median is 2. If A=[1,19], median is 1.
*/

public class Solution {
    /**
     * @param nums: A list of integers.
     * @return: the median of numbers
     */
    public int[] medianII(int[] nums) {
        // write your code here
    }
}