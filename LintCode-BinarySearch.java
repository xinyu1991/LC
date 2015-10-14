/*
Sqrt(x) 
Implement int sqrt(int x).
Compute and return the square root of x.

Example
sqrt(3) = 1
sqrt(4) = 2
sqrt(5) = 2
sqrt(10) = 3

Challenge
O(log(x))
*/

class Solution {
    /**
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
        // write your code here
        if(x < 0) return -1;
        if(x <= 1) return x;
        
        int left = 1, right = x /2;
        while(left < right){
            int mid = (left + right) >> 1;
            if(mid == x/mid) return mid;
            if(mid < x/mid) left = mid+1;
            else right = mid-1;
        }
        return right > x/right ? right-1 : right;
    }
}

/*
Search Insert Position

Given a sorted array and a target value, return the index if the target is found. 
If not, return the index where it would be if it were inserted in order.
You may assume NO duplicates in the array.

Example
[1,3,5,6], 5 → 2

[1,3,5,6], 2 → 1

[1,3,5,6], 7 → 4

[1,3,5,6], 0 → 0

Challenge
O(log(n)) time
*/

public class Solution {
    /** 
     * param A : an integer sorted array
     * param target :  an integer to be inserted
     * return : an integer
     */
    public int searchInsert(int[] A, int target) {
        if(A == null || A.length == 0) return 0;
        int left = 0, right = A.length-1;
        while(left < right){
        	int mid = (left+right) >> 1;
        	if(A[mid] == target) return mid;
        	if(A[mid] < target) left = mid+1;
        	// Note: Use mid instead of (mid-1), because mid could be 0;
        	else right = mid;
        }
        return A[right] < target ? right+1 : right;
    }
}

/*
Search a 2D Matrix

Write an efficient algorithm that searches for a value in an m x n matrix.
This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.

Example
Consider the following matrix:

[
    [1, 3, 5, 7],
    [10, 11, 16, 20],
    [23, 30, 34, 50]
]
Given target = 3, return true.

Challenge
O(log(n) + log(m)) time
*/

public class Solution {
    /**
     * @param matrix, a list of lists of integers
     * @param target, an integer
     * @return a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) return false;
        int top = 0, bottom = matrix.length-1, left = 0, right = matrix[0].length-1;
        int level = -1;
        while(top < bottom){
        	int mid = (top+bottom)>>1;
        	if(matrix[mid][0] == target || matrix[mid][right] == target) return true;
        	if(matrix[mid][0] > target) bottom = mid;
        	else if(matrix[mid][right] > target){
        		level = mid;
        		break;
        	}
        	else top = mid+1;
        }
        if(level == -1) level = top;
        while(left < right){
        	int mid = (left+right)>>1;
        	if(matrix[level][mid] == target) return true;
        	if(matrix[level][mid] > target) right = mid;
        	else left = mid+1;
        }
        return matrix[level][left] == target;
    }
}


/*
Binary Search

For a given sorted array (ascending order) and a target number, find the first index of this number in O(log n) time complexity.

If the target number does not exist in the array, return -1.

Example
If the array is [1, 2, 3, 3, 4, 5, 10], for given target 3, return 2.

Challenge
If the count of numbers is bigger than 2^32, can your code work properly?
*/

class Solution {
    /**
     * @param nums: The integer array.
     * @param target: Target to find.
     * @return: The first position of target. Position starts from 0.
     */
    public int binarySearch(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length-1;
        while(left < right){
        	// Bit operation has lower priority than add.
        	int mid = left + ((right-left)>>1);
        	if(nums[mid] == target){
        		if(mid == 0 || nums[mid-1] != target) return mid;
        		right = mid-1;
        	}
        	else if(nums[mid] > target) right = mid;
        	else left = mid+1;
        }
        return nums[left] == target ? left : -1;
    }
}


/*
Wood Cut

Given n pieces of wood with length L[i] (integer array). 
Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length. 
What is the longest length you can get from the n pieces of wood? Given L & k, return the maximum length of the small pieces.

Example
For L=[232, 124, 456], k=7, return 114.

Note
You couldn't cut wood into float length.

Challenge
O(n log Len), where Len is the longest length of the wood.
*/

public class Solution {
    /** 
     *@param L: Given n pieces of wood with length L[i]
     *@param k: An integer
     *return: The maximum length of the small pieces.
     */
    public int woodCut(int[] L, int k) {
        if(L == null || L.length == 0) return 0;
        int left = 0, right = 0, res = -1;
        // Use long here because the element in L could be super large.
        long total = (long) 0;
        for(int len : L) total+=len;
        right = (int)(total/k);
        while(left < right){
        	int mid = left + ((right-left) >> 1);
        	// break here so that we can just check for right == 1 at the end.
        	if(mid == 0) break;
        	if(woodCount(L, mid) >= k){
        		left = mid+1;
        	}
        	else right = mid;
        }
        // If right is 0, there are no enough woods. return 0.
        if(right == 0) return 0;
        return woodCount(L, right) < k ? right-1 : right;
    }

    private int woodCount(int[] L, int mid){
    	int count = 0;
    	for(int len : L) count+=(len/mid);
    	return count;
    }
}


/*
Find Minimum in Rotated Sorted Array

33% Accepted
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

Have you met this question in a real interview? Yes
Example
Given [4, 5, 6, 7, 0, 1, 2] return 0

Note
You may assume no duplicate exists in the array.
*/

public class Solution {
    /**
     * @param num: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] num) {
        if(num == null || num.length == 0) return 0;
        if(num[0] <= num[num.length-1]) return num[0];
        int left = 0, right = num.length-1;
        while(left < right){
        	int mid = left + (right-left)/2;
        	if(num[mid] >= num[0]) left = mid+1;
        	else right = mid;
        }
        return num[right];
    }
}
